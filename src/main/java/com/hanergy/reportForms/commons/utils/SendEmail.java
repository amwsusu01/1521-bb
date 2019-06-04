package com.hanergy.reportForms.commons.utils;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

public class SendEmail {


    @Autowired
    private static JavaMailSender javaMailSender;
    
	private static String psmUrl = "jdbc:mysql://192.168.19.233:3306/han_hr_h3c?useUnicode=true&characterEncoding=utf-8&useSSL=false" ;
	private static String psmUserName = "root" ;
	private static String psmPassword = "root" ;
	
    private static final String HOST = "mail.hanergy.com";
    private static final Integer PORT = 587;
    private static final String USERNAME = "gxbi";
    private static final String PASSWORD = "1qaz@WSX";
    private static final String EMAILFORM = "gxbi@hanergy.com";
    
    private static JavaMailSenderImpl mailSender = createMailSender();
	
	public static void main(String[] args)  throws Exception {
		try {
			/*warning("一级预警" ,"yujing1");
			 warning("二级预警" ,"yujing2");*/
			warning("三级预警" ,"yujing3");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 预警发送邮件
	 * @return
	 */
   private static void warning(String warningLevel , String yujing) throws Exception {
		Connection conn = DriverManager.getConnection(psmUrl, psmUserName, psmPassword);
		try {
	        Calendar calendar = Calendar.getInstance();
	        calendar.add(Calendar.DATE,-1);
	        Date time = calendar.getTime();
	        String yesterday = new SimpleDateFormat("yyyy-MM-dd").format(time);
	        String today = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

			//预警记录
			String sql = " select a.* from gxyth_daoqi_skip a where a.in_date=(select date_sub(current_date(),interval 1 day)) and a."+yujing+" is not null and a.fd_id not in (select m.fd_id from mail_send_record m where m.warning_level= '"+warningLevel+"') ";
			List<Map<String, Object>> list = DBUtil.queryMapList(conn, sql);
			//预警条数
			String countSql = " select count(*) as count from gxyth_daoqi_skip a where a.in_date=(select date_sub(current_date(),interval 1 day)) and a."+yujing+" is not null and a.fd_id not in (select m.fd_id from mail_send_record m where m.warning_level= '"+warningLevel+"') ";
	        List<Map<String, Object>> countList = DBUtil.queryMapList(conn, countSql );
			//预警用户列表
			String userSql = " select p.* from mail_notifier p left JOIN mail_template d on p.templateId = d.id where d.warning_level ='"+warningLevel+"' ";
			List<Map<String, Object>> userList = DBUtil.queryMapList(conn, userSql);
			//预警数量
	        Object count = countList.get(0).get("count");
			//标题
			String title = "预警提醒：产供销一体化 "+warningLevel+"("+count+")"  ;
			//内容
			String content = "您好， 产供销一体化("+yesterday+")预警提醒，更详细的数据信息，请使用电脑进入汉能大数据分析平台：http://192.168.19.212/BigdataPlatform " +"<br>";
			if(list !=null && list.size()>0 && userList !=null && userList.size() > 0){
				for(int k = 0 ;k< list.size() ;k++){
					String fd_id = (String) list.get(k).get("fd_id");
					String FD_CAIGOUSHENQING = (String) list.get(k).get("FD_CAIGOUSHENQING");
					String FD_WULIAOMIAOSHU = (String) list.get(k).get("FD_WULIAOMIAOSHU");
					Object daoqi =  list.get(k).get("daoqi");
					String insertSql = " INSERT INTO mail_send_record( fd_id, warning_level ) VALUES(?,?)";
					DBUtil.update(conn, insertSql ,fd_id , warningLevel);
					content = content +  "<br>" + "采购单号为:"+FD_CAIGOUSHENQING+"的物料 '"+FD_WULIAOMIAOSHU+"',已经到期'"+daoqi+"'天。          ";
				}
				for(int i = 0 ; i< userList.size() ; i++){
					//预警人邮箱
					String email = (String) userList.get(i).get("email");
					if(!StringUtils.isBlank(email)){
						Boolean info = sendHtmlMail(email , title , content);
						//发送邮件后，新增记录。
						if(info){
							//成功
							String insertRecordSql = " INSERT INTO mail_record( warning_level ,beginDate ,content,title,sendEmail ,is_send ) VALUES(?,?,?,?,?,?)";
							DBUtil.update(conn, insertRecordSql, warningLevel, today ,content ,title,email ,"1");
						}else{
							String insertRecordSql = " INSERT INTO mail_record( warning_level ,beginDate ,content,title,sendEmail ,is_send ) VALUES(?,?,?,?,?,?)";
							DBUtil.update(conn, insertRecordSql, warningLevel, today ,content ,title,email ,"0");
						}	
						System.out.println("发送成功");
					}
				}
			}
			} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (null != conn)
				conn.close();
		}
	}
	
	
    /**
    * 邮件发送器
    *
    * @return 配置好的工具
    */
   private static JavaMailSenderImpl createMailSender() {
       JavaMailSenderImpl sender = new JavaMailSenderImpl();
       sender.setHost(HOST);
       sender.setPort(PORT);
       sender.setUsername(USERNAME);
       sender.setPassword(PASSWORD);
       sender.setDefaultEncoding("Utf-8");
       Properties p = new Properties();
       p.setProperty("mail.smtp.timeout", "25000");
       p.setProperty("mail.smtp.auth", "false");
       sender.setJavaMailProperties(p);
       return sender;
   }

   /**
    * 发送邮件
    *
    * @param to 接受人
    * @param subject 主题
    * @param html 发送内容
    * @throws MessagingException 异常
    * @throws UnsupportedEncodingException 异常
    */
	public static Boolean sendHtmlMail(String to, String subject, String html) throws MessagingException, UnsupportedEncodingException {
		Boolean flage = false;
		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			// 设置utf-8或GBK编码，否则邮件会有乱码
			MimeMessageHelper messageHelper = new MimeMessageHelper( mimeMessage, true, "UTF-8");
			messageHelper.setFrom(EMAILFORM, "系统名称");
			messageHelper.setTo(to);
			messageHelper.setSubject(subject);
			messageHelper.setText(html, true);
			mailSender.send(mimeMessage);
			flage = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flage;
	}
    
}
