package com.hanergy.reportForms.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hanergy.reportForms.commons.enums.EnumBeSelectedAgencyCheckStatus;
import com.hanergy.reportForms.commons.enums.EnumBeSelectedSelfCheckStatus;
import com.hanergy.reportForms.commons.enums.EnumBeSelectedStatus;
import com.hanergy.reportForms.commons.enums.EnumCheckItemId;
import com.hanergy.reportForms.commons.enums.EnumVerifyLevel;
import com.hanergy.reportForms.commons.enums.EnumVerifySource;
import com.hanergy.reportForms.commons.enums.EnumVerifyStatus;
import com.hanergy.reportForms.commons.enums.ResultEnum;
import com.hanergy.reportForms.commons.utils.*;
import com.hanergy.reportForms.commons.utils.cont.DefaultConst;
import com.hanergy.reportForms.entity.entrust.report.CandidateReportEntity;
import com.hanergy.reportForms.entity.entrust.report.CheckItemInfo;
import com.hanergy.reportForms.entity.entrust.report.EntrustParamsInfo;
import com.hanergy.reportForms.entity.entrust.report.EntrustReportParamsResp;
import com.hanergy.reportForms.entity.entrust.report.UpLoadReportEntity;
import com.hanergy.reportForms.entity.entrust.report.UpLoadReportInfo;
import com.hanergy.reportForms.entity.entrust.report.wrapper.BadRecordWrapper;
import com.hanergy.reportForms.entity.entrust.report.wrapper.CommercialProfitWrapper;
import com.hanergy.reportForms.entity.entrust.report.wrapper.CourtProceedsWrapper;
import com.hanergy.reportForms.entity.entrust.report.wrapper.CredentialsWrapper;
import com.hanergy.reportForms.entity.entrust.report.wrapper.EducationBackgroundWrapper;
import com.hanergy.reportForms.entity.entrust.report.wrapper.FinancialIrregularitiesWrapper;
import com.hanergy.reportForms.mapper.BadRecordMapper;
import com.hanergy.reportForms.mapper.BeSelectedMapper;
import com.hanergy.reportForms.mapper.CheckMapper;
import com.hanergy.reportForms.mapper.CheckProjectMapper;
import com.hanergy.reportForms.mapper.CheckStatisticsMapper;
import com.hanergy.reportForms.mapper.CommercialProfitMapper;
import com.hanergy.reportForms.mapper.CourtProceedsMapper;
import com.hanergy.reportForms.mapper.CredentialsMapper;
import com.hanergy.reportForms.mapper.EducationBackgroundMapper;
import com.hanergy.reportForms.mapper.EntrustReportMapper;
import com.hanergy.reportForms.mapper.ExperienceMapper;
import com.hanergy.reportForms.mapper.FinancialIrregularitiesMapper;
import com.hanergy.reportForms.mapper.ProjectMapper;
import com.hanergy.reportForms.mapper.entity.*;
import com.hanergy.reportForms.service.IEntrsutReportService;
import com.hanergy.reportForms.service.IExperienceService;
import com.hanergy.reportForms.service.IGenerateCodeService;
import com.hanergy.reportForms.service.IUploadService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * <p>
 * 第三方报告录入
 * </p>
 *
 * @author 房帅
 * @since 2018-09-20
 */
@Service
public class EntrsutReportServiceImpl implements IEntrsutReportService {

	private static Logger logger = LogManager.getLogger();

	@Autowired
	private EntrustReportMapper entrustReportMapper;


	@Autowired
	private IExperienceService experienceService;

	@Autowired
	private CredentialsMapper credentialsMapper;
	@Autowired
	private CheckProjectMapper checkProjectMapper;

	@Autowired
	private IGenerateCodeService generateCodeService;
	@Autowired
	private EducationBackgroundMapper educationBackgroundMapper;
	
	@Autowired
	private ExperienceMapper experienceMapper;
	@Autowired
	private BadRecordMapper badRecordMapper;
	
	@Autowired
	private CommercialProfitMapper commercialProfitMapper;
	
	@Autowired
	private CourtProceedsMapper courtProceedsMapper;
	
	@Autowired
	private FinancialIrregularitiesMapper financialIrregularitiesMapper;
	
	@Autowired
	private CheckMapper checkMapper;
	
	
	@Autowired
	private ProjectMapper projectMapper;
	
	@Autowired
	private BeSelectedMapper beSelectedMapper;
	
	@Autowired
	private CheckStatisticsMapper checkStatisticsMapper;
	@Autowired
	private IUploadService uploadService;
	
	@Override
	public JSONObject getEntrustReportByAgency(Integer type,Long verify_unit, Integer verifySources, Integer page, Integer pageSize,
			Integer checkStatus, Long beSelected, String keyword) {
		List<CandidateReportEntity> result = null;
		Integer total = 0;
		
		List<Integer> checkStatusArr = new ArrayList<Integer>();
		/*checkStatusArr.add(EnumBeSelectedStatus.COMPLETED.getCode());
		checkStatusArr.add(EnumBeSelectedStatus.BACKGROUND_PROCESSING.getCode());*/
		List<Integer>  verifyStatusArr = null;
		Date startTime = null;
		Date endTime = null;
		if(type == 1){
			verifyStatusArr = new ArrayList<>();
			verifyStatusArr.add(EnumVerifyStatus.SAVE_EDITION.getCode());
			verifyStatusArr.add(EnumVerifyStatus.FIRST_EDITION.getCode());
		}else if(type == 2){
			Date[] monthDate = DateUtils.formatCurrentAllMonth();
			startTime =monthDate[0];
			endTime =monthDate[1];
			verifyStatusArr = new ArrayList<>();
			verifyStatusArr.add(EnumVerifyStatus.FINAL_EDITION.getCode());
		}
		
		try {
			result = entrustReportMapper.getEntrustReportList(verify_unit, verifyStatusArr, checkStatusArr,EnumBeSelectedAgencyCheckStatus.CHECKING.getCode(), 
								keyword,(page-1)*pageSize,page*pageSize,startTime,endTime);
			total = entrustReportMapper.getEntrustReportCount(verify_unit, verifyStatusArr, checkStatusArr,EnumBeSelectedAgencyCheckStatus.CHECKING.getCode(),
								keyword,startTime,endTime);
		} catch (Exception e) {
			e.printStackTrace();
		}
 
		JSONObject resResult = ResResult.getResResult();
		resResult.put("status", 1);
		resResult.put("msg", "请求成功!");
		resResult.put("data", result);
		resResult.put("total", total);
		resResult.put("totalPages", (total / pageSize) + 1);
		resResult.put("current", page);
		resResult.put("pageSize", pageSize);
		return resResult;
	}

	@Override
	public EntrustReportParamsResp getEntrustReportParams(Long check_id, Long verify_unit) {
		// 背调负责人获取详情只需要传
		List<EntrustParamsInfo> list = entrustReportMapper.getEntrustParamInfo(check_id, null, verify_unit);
		// agencyPlanMapper
		return new EntrustReportParamsResp((list));
	}

	@Override
	public EntrustReportParamsResp getBackCheckReportParams(Long candidateId) {

		// 背调负责人获取详情只需要传
		
		List<Integer> checkStatusArr = new ArrayList<>();
		//添加候选人查询条件：check_status
		checkStatusArr.add(EnumBeSelectedStatus.GRANT_AUTHORIZATION.getCode());
		checkStatusArr.add(EnumBeSelectedStatus.INFORMATION_FILLING.getCode());
		checkStatusArr.add(EnumBeSelectedStatus.CHECK_OVER.getCode());
		checkStatusArr.add(EnumBeSelectedStatus.START_UP.getCode());
		List<EntrustParamsInfo> list = entrustReportMapper.getBackCheckEntrustInfo(candidateId, checkStatusArr);
		// agencyPlanMapper
		return new EntrustReportParamsResp((list));
	}

	@Override
	public EntrustReportParamsResp getEntrustReportDetail(Long check_id, Long candidateId, Integer queryType) throws Exception {
		// 查询背调负责人查看的详情时 ：candidateId必填，却 queryType = 2,watchRole = 1
		// 其他方查看的详情时 ：check_id必填,queryType = 1,watchRole = 对应查看

		EntrustReportParamsResp result = null;
		if (queryType == 1) {
			result = getEntrustReportParams(check_id, null);
		} else if (queryType == 2) {
			result = getBackCheckReportParams(candidateId);
		}
		if (result == null || result.getParamInfo().isEmpty()) {
			logger.error("getEntrustReportDetail  列表对应的详情不存在！！！！");
			return null;
		}
		// 查询履历
		logger.info("   result : " + JSONObject.toJSON(result));
		//
		
		for (EntrustParamsInfo obj : result.getParamInfo()) {
			Map<Long,CheckItemInfo> itemInfoMap = new HashMap<>();
			//封装检查项信息
			for(CheckItemInfo item :obj.getItemList()){
				itemInfoMap.put(item.getId(), item);
			}
			
			//封装身份信息内容
			QueryWrapper<Credentials> credentialsQueryWrapper = new QueryWrapper<Credentials>();
			credentialsQueryWrapper.eq("check_id", obj.getCheckId());
			credentialsQueryWrapper.eq("verify_source", obj.getVerify_source());
			List<Credentials> credentialsList = credentialsMapper.selectList(credentialsQueryWrapper);
			List<CredentialsWrapper> credentialsChangList = new ArrayList<>();
			for(Credentials obj1: credentialsList){
				String certificationBody = null;
				Date investigationTime = null;
				if(itemInfoMap.get(EnumCheckItemId.IDENTITY_VERIFICATION.getCode()) != null){
					certificationBody = itemInfoMap.get(EnumCheckItemId.IDENTITY_VERIFICATION.getCode()).getCertificationBody();
					investigationTime = itemInfoMap.get(EnumCheckItemId.IDENTITY_VERIFICATION.getCode()).getInvestigationTime();
				}
				CredentialsWrapper info = new CredentialsWrapper();
				AdapterUtil.fatherToChild(obj1,info);
				info.setCertificationBody(certificationBody);
				info.setInvestigationTime(investigationTime);
				credentialsChangList.add(info);
			}
			obj.setCredentials(credentialsChangList);
			
			
			//封装教育背景信息
			QueryWrapper<EducationBackground> educationBackgroundQueryWrapper = new QueryWrapper<EducationBackground>();
			educationBackgroundQueryWrapper.eq("check_id", obj.getCheckId());
			educationBackgroundQueryWrapper.eq("verify_source", obj.getVerify_source());
			List<EducationBackground> EducationBackgroundList = educationBackgroundMapper.selectList(educationBackgroundQueryWrapper);
			List<EducationBackgroundWrapper> educationBackgroundChangList = new ArrayList<>();
			for(EducationBackground obj1: EducationBackgroundList){
				String certificationBody = null;
				Date investigationTime = null;
				if(itemInfoMap.get(EnumCheckItemId.EDUCATION_VERIFICATION.getCode()) != null ){
					certificationBody = itemInfoMap.get(EnumCheckItemId.IDENTITY_VERIFICATION.getCode()).getCertificationBody();
					investigationTime = itemInfoMap.get(EnumCheckItemId.IDENTITY_VERIFICATION.getCode()).getInvestigationTime();
				}else if(itemInfoMap.get(EnumCheckItemId.DEGREE_VERIFICATION.getCode()) != null){
					certificationBody = itemInfoMap.get(EnumCheckItemId.DEGREE_VERIFICATION.getCode()).getCertificationBody();
					investigationTime = itemInfoMap.get(EnumCheckItemId.DEGREE_VERIFICATION.getCode()).getInvestigationTime();
				}
				EducationBackgroundWrapper info = new EducationBackgroundWrapper();
				AdapterUtil.fatherToChild(obj1,info);
				info.setCertificationBody(certificationBody);
				info.setInvestigationTime(investigationTime);
				educationBackgroundChangList.add(info);
			}
			obj.setEucationBackground(educationBackgroundChangList );
			
			
			//封装经历
			QueryWrapper<Experience> queryWrapper = new QueryWrapper<Experience>();
			queryWrapper.eq("check_id", obj.getCheckId());
			queryWrapper.eq("verify_source", obj.getVerify_source());
			queryWrapper.orderByDesc("entry");
			List<Experience> experienceList = experienceService.list(queryWrapper);// 目前没有对简历进行排序
			logger.info("experienceService.list   result : " + JSONObject.toJSON(experienceList) );
			 
			Experience experienceOne = new Experience();
			Experience experienceTwo = new Experience();
			Experience experienceThree = new Experience();
			 for(int i = 0 ; i < experienceList.size() ; i++ ){
				 if(i == 0){
					 experienceOne = experienceList.get(i);
					 //获取项目经验
					 QueryWrapper<Project> projectQueryWrapper = new QueryWrapper<>();
					 projectQueryWrapper.eq("experience", experienceOne.getId());
					 experienceOne.setProjects(projectMapper.selectList(projectQueryWrapper)); 
				 }else if( i == 1){
					 experienceTwo = experienceList.get(i);
					 QueryWrapper<Project> projectQueryWrapper = new QueryWrapper<>();
					 projectQueryWrapper.eq("experience", experienceTwo.getId());
					 experienceTwo.setProjects(projectMapper.selectList(projectQueryWrapper)); 
				 }else if(i == 2){
					 experienceThree =  experienceList.get(i);
					 QueryWrapper<Project> projectQueryWrapper = new QueryWrapper<>();
					 projectQueryWrapper.eq("experience", experienceThree.getId());
					 experienceThree.setProjects(projectMapper.selectList(projectQueryWrapper)); 
				 }
			 }
			 obj.setExperiencesOne(experienceOne);
			 obj.setExperiencesTwo(experienceTwo);
			 obj.setExperiencesThree(experienceThree);
			//封装不良记录
			 	QueryWrapper<BadRecord> badRecordQueryWrapper = new QueryWrapper<BadRecord>();
				badRecordQueryWrapper.eq("check_id", obj.getCheckId());
				badRecordQueryWrapper.eq("verify_source", obj.getVerify_source());
				List<BadRecord> BadRecordList =badRecordMapper.selectList(badRecordQueryWrapper);
				List<BadRecordWrapper> badRecordChangList = new ArrayList<>();
				for(BadRecord obj1: BadRecordList){
					String certificationBody = null;
					Date investigationTime = null;
					if(itemInfoMap.get(EnumCheckItemId.BAD_RECORD.getCode()) != null ){
						certificationBody = itemInfoMap.get(EnumCheckItemId.BAD_RECORD.getCode()).getCertificationBody();
						investigationTime = itemInfoMap.get(EnumCheckItemId.BAD_RECORD.getCode()).getInvestigationTime();
					} 
					BadRecordWrapper info = new BadRecordWrapper();
					AdapterUtil.fatherToChild(obj1,info);
					info.setCertificationBody(certificationBody);
					info.setInvestigationTime(investigationTime);
					badRecordChangList.add(info);
				}
			
			 obj.setBadRecord(badRecordChangList);
			 
			 
			//封装商业利益
			QueryWrapper<CommercialProfit> commercialProfitQueryWrapper = new QueryWrapper<CommercialProfit>();
			commercialProfitQueryWrapper.eq("check_id", obj.getCheckId());
			commercialProfitQueryWrapper.eq("verify_source", obj.getVerify_source());
			List<CommercialProfit> CommercialProfitList =commercialProfitMapper.selectList(commercialProfitQueryWrapper);
			List<CommercialProfitWrapper> commercialProfitChangList = new ArrayList<>();
			for(CommercialProfit obj1: CommercialProfitList){
				String certificationBody = null;
				Date investigationTime = null;
				if(itemInfoMap.get(EnumCheckItemId.COMMERCIAL_PROFIT.getCode()) != null ){
					certificationBody = itemInfoMap.get(EnumCheckItemId.COMMERCIAL_PROFIT.getCode()).getCertificationBody();
					investigationTime = itemInfoMap.get(EnumCheckItemId.COMMERCIAL_PROFIT.getCode()).getInvestigationTime();
				} 
				CommercialProfitWrapper info = new CommercialProfitWrapper();
				AdapterUtil.fatherToChild(obj1,info);
				info.setCertificationBody(certificationBody);
				info.setInvestigationTime(investigationTime);
				commercialProfitChangList.add(info);
			}
			obj.setCommercialProfits(commercialProfitChangList);
			
			//封装法院诉讼
			QueryWrapper<CourtProceeds> courtProceedsQueryWrapper = new QueryWrapper<CourtProceeds>();
			courtProceedsQueryWrapper.eq("check_id", obj.getCheckId());
			courtProceedsQueryWrapper.eq("verify_source", obj.getVerify_source());
			List<CourtProceeds> CourtProceedsList = courtProceedsMapper.selectList(courtProceedsQueryWrapper);
			List<CourtProceedsWrapper> courtProceedsChangList = new ArrayList<>();
			for(CourtProceeds obj1: CourtProceedsList){
				String certificationBody = null;
				Date investigationTime = null;
				if(itemInfoMap.get(EnumCheckItemId.LITIGATION.getCode()) != null ){
					certificationBody = itemInfoMap.get(EnumCheckItemId.LITIGATION.getCode()).getCertificationBody();
					investigationTime = itemInfoMap.get(EnumCheckItemId.LITIGATION.getCode()).getInvestigationTime();
				} 
				CourtProceedsWrapper info = new CourtProceedsWrapper();
				AdapterUtil.fatherToChild(obj1,info);
				info.setCertificationBody(certificationBody);
				info.setInvestigationTime(investigationTime);
				courtProceedsChangList.add(info);
			}
			obj.setCourtProceeds(courtProceedsChangList);
			
			//封装金融违规
			QueryWrapper<FinancialIrregularities> financialIrregularitiesQueryWrapper = new QueryWrapper<FinancialIrregularities>();
			financialIrregularitiesQueryWrapper.eq("check_id", obj.getCheckId());
			financialIrregularitiesQueryWrapper.eq("verify_source", obj.getVerify_source());
			List<FinancialIrregularities> FinancialIrregularitiesList = financialIrregularitiesMapper.selectList(financialIrregularitiesQueryWrapper);
			List<FinancialIrregularitiesWrapper> financialIrregularitiesChangeList = new ArrayList<>();
			for(FinancialIrregularities obj1: FinancialIrregularitiesList){
				String certificationBody = null;
				Date investigationTime = null;
				if(itemInfoMap.get(EnumCheckItemId.FINANCIAL_IRREGULARITIES.getCode()) != null ){
					certificationBody = itemInfoMap.get(EnumCheckItemId.FINANCIAL_IRREGULARITIES.getCode()).getCertificationBody();
					investigationTime = itemInfoMap.get(EnumCheckItemId.FINANCIAL_IRREGULARITIES.getCode()).getInvestigationTime();
				} 
				FinancialIrregularitiesWrapper info = new FinancialIrregularitiesWrapper();
				AdapterUtil.fatherToChild(obj1,info);
				info.setCertificationBody(certificationBody);
				info.setInvestigationTime(investigationTime);
				financialIrregularitiesChangeList.add(info);
			}
			
			obj.setFinancialIrregularities(financialIrregularitiesChangeList);
		}	 
		
		return result;
		
	}
 
	
	
	
	
	@Override
	public JSONObject upLoadReport(UpLoadReportInfo upLoadReportInfo) {
		//判断当前用户是否存在，待定
		logger.info("upLoadReport  params:"+JSONObject.toJSON(upLoadReportInfo) );
		if( upLoadReportInfo.getUserInfo()==null ||upLoadReportInfo.getCheckId()==null || upLoadReportInfo.getBeSelected() == null || upLoadReportInfo.getUserInfo().getUserType() == null ){
			return ResResult.getResultEnum(ResultEnum.PARAM_INVALID);
		}
		
		long beSelected = upLoadReportInfo.getBeSelected();
		Long checkId = upLoadReportInfo.getCheckId();
		Integer verify_level = upLoadReportInfo.getVerify_level();
		Integer verify_status = upLoadReportInfo.getVerify_status();
		Integer verify_source = upLoadReportInfo.getUserInfo().getUserType();
		//如果当点记录为终版状态，则不允许其进行操作
		if(!this.judgeCheckInfoStatueIsOperation(checkId)){
			return ResResult.getResultEnum(ResultEnum.CHECK_INFO_ILLEGAL_SUBMIT);
		}
		Map<Long,Object> itemInfoMap = new HashMap<Long,Object>(); 
		this.removeAllCheckInfoByCheckId(checkId,verify_source,beSelected);
		//插入或修改身份信息
		for(CredentialsWrapper obj:upLoadReportInfo.getCredentials()){
			if(obj.getVerifySource()!=null && !obj.getVerifySource().equals(verify_source)){
				
				continue;
			}
			itemInfoMap.put(EnumCheckItemId.IDENTITY_VERIFICATION.getCode(), obj);
			obj.setBeSelected(beSelected);
			obj.setVerifySource(verify_source);
			obj.setCheckId(checkId);
				obj.setId(generateCodeService.getSingleId());
				credentialsMapper.insert(obj);
		}
		//插入或修改教育背景
		for(EducationBackgroundWrapper obj :upLoadReportInfo.getEucationBackground()){
			if(obj.getVerifySource()!=null && !obj.getVerifySource().equals(verify_source)){
				continue;
			}
			itemInfoMap.put(EnumCheckItemId.EDUCATION_VERIFICATION.getCode(), obj);
			obj.setBeSelected(beSelected);
			obj.setVerifySource(verify_source);
			obj.setCheckId(checkId);
				obj.setId(generateCodeService.getSingleId());
				educationBackgroundMapper.insert(obj);
			
		}
		
		logger.info("upLoadReportInfo.getExperiencesOne():"+JSONObject.toJSONString(upLoadReportInfo.getExperiencesOne()));
		//插入第一段经历
		if(upLoadReportInfo.getExperiencesOne() != null && this.checkExpirenceInfoInvilad(upLoadReportInfo.getExperiencesOne())){
			if(upLoadReportInfo.getExperiencesOne().getVerifySource()!=null && !upLoadReportInfo.getExperiencesOne().getVerifySource().equals(verify_source)){
			}else{
				Experience one = upLoadReportInfo.getExperiencesOne();
				Long expirenceOneId = generateCodeService.getSingleId();
				one.setBeSelected(beSelected);
				one.setVerifySource(verify_source);
				one.setCheckId(checkId);
				one.setExpirenceSeq(1);
				one.setId(expirenceOneId);
				experienceMapper.insert(one);
				if(one.getProjects() !=null && !one.getProjects().isEmpty()){
					for(Project obj:one.getProjects()){
						obj.setId(generateCodeService.getSingleId());
						obj.setExperience(expirenceOneId);
						obj.insert();
					}
				}	
			}
			
		}
		logger.info("upLoadReportInfo.getExperiencesTwo():"+JSONObject.toJSONString(upLoadReportInfo.getExperiencesTwo()));

		//插入第二段工作经历
		if(upLoadReportInfo.getExperiencesTwo() != null && this.checkExpirenceInfoInvilad(upLoadReportInfo.getExperiencesTwo())){
			if(upLoadReportInfo.getExperiencesOne().getVerifySource()!=null && !upLoadReportInfo.getExperiencesOne().getVerifySource().equals(verify_source)){
			}else{
				Experience two = upLoadReportInfo.getExperiencesTwo();
				Long expirenceTowId = generateCodeService.getSingleId();
				two.setBeSelected(beSelected);
				two.setVerifySource(verify_source);
				two.setCheckId(checkId);
				two.setExpirenceSeq(2);
				two.setId(expirenceTowId);
				logger.info("experienceMapper.insert(two)  param:"+JSONObject.toJSONString(two));

				experienceMapper.insert(two);
				if(two.getProjects() !=null && !two.getProjects().isEmpty()){
					for(Project obj:two.getProjects()){
						obj.setId(generateCodeService.getSingleId());
						obj.setExperience(expirenceTowId);
						obj.insert();
					}
				}
			}
			
		}
		logger.info("upLoadReportInfo.getExperiencesThree():"+JSONObject.toJSONString(upLoadReportInfo.getExperiencesThree()));

		//第三段工作经历
		if(upLoadReportInfo.getExperiencesThree() != null &&  this.checkExpirenceInfoInvilad(upLoadReportInfo.getExperiencesTwo())){
			if(upLoadReportInfo.getExperiencesOne().getVerifySource()!=null && !upLoadReportInfo.getExperiencesOne().getVerifySource().equals(verify_source)){
			}else{
				Experience three = upLoadReportInfo.getExperiencesThree();
				Long expirenceThreeId = generateCodeService.getSingleId();
				three.setBeSelected(beSelected);
				three.setVerifySource(verify_source);
				three.setCheckId(checkId);
				three.setExpirenceSeq(3);
				three.setId(expirenceThreeId);
				logger.info("experienceMapper.insert(three)  param:"+JSONObject.toJSONString(three));

				experienceMapper.insert(three);
				if(three.getProjects()!=null && !three.getProjects().isEmpty()){
					for(Project obj:three.getProjects()){
						obj.setId(generateCodeService.getSingleId());
						obj.setExperience(expirenceThreeId);
						obj.insert();
					}
				}
			}
		}
		//插入或修改不良记录
		for(BadRecordWrapper obj :upLoadReportInfo.getBadRecord()){
			if(obj.getVerifySource()!=null && !obj.getVerifySource().equals(verify_source)){
				continue;
			}
			itemInfoMap.put(EnumCheckItemId.BAD_RECORD.getCode(), obj);
			obj.setBeSelected(beSelected);
			obj.setVerifySource(verify_source);
			obj.setCheckId(checkId);
				obj.setId(generateCodeService.getSingleId());
				badRecordMapper.insert(obj);
		}
		//插入或修改商业利益
		for(CommercialProfitWrapper obj :upLoadReportInfo.getCommercialProfits()){
			if(obj.getVerifySource()!=null && !obj.getVerifySource().equals(verify_source)){
				continue;
			}
			itemInfoMap.put(EnumCheckItemId.COMMERCIAL_PROFIT.getCode(), obj);
			obj.setBeSelected(beSelected);
			obj.setVerifySource(verify_source);
			obj.setCheckId(checkId);
				obj.setId(generateCodeService.getSingleId());
				commercialProfitMapper.insert(obj);
		}
		//插入或修改法院诉讼
		for(CourtProceedsWrapper obj :upLoadReportInfo.getCourtProceeds()){
			if(obj.getVerifySource()!=null && !obj.getVerifySource().equals(verify_source)){
				continue;
			}
			itemInfoMap.put(EnumCheckItemId.LITIGATION.getCode(), obj);
			obj.setBeSelected(beSelected);
			obj.setVerifySource(verify_source);
			obj.setCheckId(checkId);
				obj.setId(generateCodeService.getSingleId());
				courtProceedsMapper.insert(obj);
		}
		//插入或修改金融诉讼
		for(FinancialIrregularitiesWrapper obj :upLoadReportInfo.getFinancialIrregularities()){
			if(obj.getVerifySource()!=null && !obj.getVerifySource().equals(verify_source)){
				continue;
			}
			itemInfoMap.put(EnumCheckItemId.FINANCIAL_IRREGULARITIES.getCode(), obj);
			obj.setBeSelected(beSelected);
			obj.setVerifySource(verify_source);
			obj.setCheckId(checkId);
				obj.setId(generateCodeService.getSingleId());
				financialIrregularitiesMapper.insert(obj);
		}
		//插入检查项
		logger.info("itemInfoMap  ::"+ JSONObject.toJSON(itemInfoMap));
	for(CheckItemInfo obj:upLoadReportInfo.getItemList()){
		Object mapInfo = itemInfoMap.get(obj.getId());
		logger.info("mapInfo   itemId :  "+obj.getId() +"   ,   mapInfo:" +mapInfo);
		Date investigationTime = null;
		String cerificationBody = null;
		if(mapInfo != null){
			if(mapInfo instanceof CredentialsWrapper || mapInfo  instanceof  Credentials){
				CredentialsWrapper info =(CredentialsWrapper) mapInfo;
				investigationTime = info.getInvestigationTime();
				cerificationBody = info.getCertificationBody();
			}else if(mapInfo instanceof EducationBackgroundWrapper || mapInfo  instanceof  EducationBackground ){
				EducationBackgroundWrapper info =(EducationBackgroundWrapper) mapInfo;
				investigationTime = info.getInvestigationTime();
				cerificationBody = info.getCertificationBody();
			}else if(mapInfo instanceof BadRecordWrapper || mapInfo  instanceof  BadRecord ){
				BadRecordWrapper info =(BadRecordWrapper) mapInfo;
				investigationTime = info.getInvestigationTime();
				cerificationBody = info.getCertificationBody();
			}else if(mapInfo instanceof CommercialProfitWrapper || mapInfo  instanceof   CommercialProfit){
				CommercialProfitWrapper info =(CommercialProfitWrapper) mapInfo;
				investigationTime = info.getInvestigationTime();
				cerificationBody = info.getCertificationBody();
			}else if(mapInfo instanceof CourtProceedsWrapper || mapInfo  instanceof   CourtProceeds){
				CourtProceedsWrapper info =(CourtProceedsWrapper) mapInfo;
				investigationTime = info.getInvestigationTime();
				cerificationBody = info.getCertificationBody();
			}else if(mapInfo instanceof FinancialIrregularitiesWrapper || mapInfo  instanceof   FinancialIrregularities){
				FinancialIrregularitiesWrapper info =(FinancialIrregularitiesWrapper) mapInfo;
				investigationTime = info.getInvestigationTime();
				cerificationBody = info.getCertificationBody();
			}
		}
		
		CheckProject checkProject = new CheckProject();
		checkProject.setCheckId(checkId);
		checkProject.setVerifySource(verify_source);
		checkProject.setVerifyExplain(obj.getVerifyExplain());
		checkProject.setVerifyLevel(obj.getVerifyLevel());
		checkProject.setVerifyContent(obj.getVerifyContent());
		checkProject.setInvestigationTime(investigationTime );
		checkProject.setCertificationBody(cerificationBody);
		checkProject.setCheckitemId(obj.getId());
			checkProject.setId(generateCodeService.getSingleId());
			logger.info("checkProjectMapper.insert(checkProject)  params:"+ JSONObject.toJSON(checkProject) );
			checkProject.insert();
	}
	
		//最后修改背调状态
		if(this.updateCheckInfo(checkId, verify_source, verify_status,verify_level,beSelected)){
			return ResResult.success();
		 }
			return 	ResResult.failed();
	}

	@Override
	public JSONObject upLoadEntrsutReport(UpLoadReportEntity upLoadReportEntity) {
		
		if(upLoadReportEntity == null ){
			logger.error("upLoadEntrsutReport  params : upLoadReportEntity  is  null!!!");
			return ResResult.getResultEnum(ResultEnum.PARAM_INVALID);
		}
		if(StrUtil.isEmpty(upLoadReportEntity.getOrderNumber())  ){
			logger.error("upLoadEntrsutReport  params : orderNumber  is  empty!!!");
			return ResResult.getResultEnum(ResultEnum.PARAM_INVALID);
		}
		logger.info("upLoadReport  params:"+ JSONObject.toJSON(upLoadReportEntity));
		Long checkId = null;
		Long beSelected = null;
		String agecyName = "";
		if(upLoadReportEntity.getCheckId() == null || upLoadReportEntity.getCheckId() <= 0  ){
			//根据report获取check
			QueryWrapper<Check> checkQuery = new QueryWrapper<>();
			checkQuery.eq("order_number", upLoadReportEntity.getOrderNumber());
			Check check = checkMapper.selectOne(checkQuery);
			checkId = check.getId();
			beSelected = check.getBeSelected();
			agecyName = check.getVerifyName();
		}else{
			checkId = upLoadReportEntity.getCheckId();
			beSelected = upLoadReportEntity.getBeSelected();
		}
		
		if(!this.judgeCheckInfoStatueIsOperation(checkId)){
			return ResResult.getResultEnum(ResultEnum.CHECK_INFO_ILLEGAL_SUBMIT);
		}	
		Integer verify_status = upLoadReportEntity.getVerify_status();
		Integer verify_source = EnumVerifySource.AGENCY.getCode();
		Integer verify_level = upLoadReportEntity.getVerify_level();


		this.removeAllCheckInfoByCheckId(checkId,verify_source,beSelected);
		//插入或修改身份信息
		if(CollectionUtils.isNotEmpty(upLoadReportEntity.getCredentials())){
			for(Credentials obj:upLoadReportEntity.getCredentials()){
				obj.setBeSelected(beSelected);
				obj.setVerifySource(verify_source);
				obj.setCheckId(checkId);
				obj.setId(generateCodeService.getSingleId());
				credentialsMapper.insert(obj);
			}
		}

		//插入或修改教育背景
		if(CollectionUtils.isNotEmpty(upLoadReportEntity.getEucationBackground())){
			for(EducationBackground obj :upLoadReportEntity.getEucationBackground()){
				obj.setBeSelected(beSelected);
				obj.setVerifySource(verify_source);
				obj.setCheckId(checkId);
				obj.setId(generateCodeService.getSingleId());
				educationBackgroundMapper.insert(obj);
			}
		}

		
		//插入第一段经历
		if(upLoadReportEntity.getExperiencesOne() != null){
			Experience one = upLoadReportEntity.getExperiencesOne();
			Long expirenceOneId = generateCodeService.getSingleId();
			one.setBeSelected(beSelected);
			one.setVerifySource(verify_source);
			one.setCheckId(checkId);
			one.setId(generateCodeService.getSingleId());
			one.setExpirenceSeq(1);
			experienceMapper.insert(one);
			if(!one.getProjects().isEmpty()){
				for(Project obj:one.getProjects()){
					obj.setId(generateCodeService.getSingleId());
					obj.setExperience(expirenceOneId);
					obj.insert();
				}
			}
		}
		//插入第二段工作经历
		if(upLoadReportEntity.getExperiencesTwo() != null){
			Experience two = upLoadReportEntity.getExperiencesTwo();
			Long expirencetwoId = generateCodeService.getSingleId();
			two.setBeSelected(beSelected);
			two.setVerifySource(verify_source);
			two.setCheckId(checkId);
			two.setExpirenceSeq(2);
			two.setId(generateCodeService.getSingleId());
			experienceMapper.insert(two);
			if(!two.getProjects().isEmpty()){
				for(Project obj:two.getProjects()){
					obj.setId(generateCodeService.getSingleId());
					obj.setExperience(expirencetwoId);
					obj.insert();
				}
			}
		}
		//第三段工作经历
		if(upLoadReportEntity.getExperiencesThree() != null){
			Experience three = upLoadReportEntity.getExperiencesThree();
			Long expirenceThreeId = generateCodeService.getSingleId();
			three.setBeSelected(beSelected);
			three.setVerifySource(verify_source);
			three.setCheckId(checkId);
			three.setExpirenceSeq(3);
			three.setId(generateCodeService.getSingleId());
			experienceMapper.insert(three);
			if(!three.getProjects().isEmpty()){
				for(Project obj:three.getProjects()){
					obj.setId(generateCodeService.getSingleId());
					obj.setExperience(expirenceThreeId);
					obj.insert();
				}
			}
		}
		//插入或修改不良记录
		if(CollectionUtils.isNotEmpty(upLoadReportEntity.getBadRecord())){
			for(BadRecord  obj :upLoadReportEntity.getBadRecord()){
				obj.setBeSelected(beSelected);
				obj.setVerifySource(verify_source);
				obj.setCheckId(checkId);
				obj.setId(generateCodeService.getSingleId());
				badRecordMapper.insert(obj);
			}
		}

		//插入或修改商业利益
		if(CollectionUtils.isNotEmpty(upLoadReportEntity.getCommercialProfits())){
			for(CommercialProfit obj :upLoadReportEntity.getCommercialProfits()){
				obj.setBeSelected(beSelected);
				obj.setVerifySource(verify_source);
				obj.setCheckId(checkId);
				obj.setId(generateCodeService.getSingleId());
				commercialProfitMapper.insert(obj);
			}
		}

		//插入或修改法院诉讼
		if(CollectionUtils.isNotEmpty(upLoadReportEntity.getCourtProceeds())){
			for(CourtProceeds  obj :upLoadReportEntity.getCourtProceeds()){
				obj.setBeSelected(beSelected);
				obj.setVerifySource(verify_source);
				obj.setCheckId(checkId);
				obj.setId(generateCodeService.getSingleId());
				courtProceedsMapper.insert(obj);
			}
		}

		//插入或修改金融诉讼
		if(CollectionUtils.isNotEmpty(upLoadReportEntity.getFinancialIrregularities())){
			for(FinancialIrregularities  obj :upLoadReportEntity.getFinancialIrregularities()){
				obj.setBeSelected(beSelected);
				obj.setVerifySource(verify_source);
				obj.setCheckId(checkId);
				obj.setId(generateCodeService.getSingleId());
				financialIrregularitiesMapper.insert(obj);
			}
		}

		//更新检查项核实信息
		if(CollectionUtils.isNotEmpty(upLoadReportEntity.getItemList())){
			for(CheckItemInfo obj:upLoadReportEntity.getItemList()){
				//根据检查项的编码获取item的id
				CheckProject checkProject = new CheckProject();
				checkProject.setId(generateCodeService.getSingleId());
				checkProject.setCheckId(checkId);
				checkProject.setVerifySource(EnumVerifySource.AGENCY.getCode());
				checkProject.setVerifyExplain(obj.getVerifyExplain());
				checkProject.setVerifyLevel(obj.getVerifyLevel());
				checkProject.setVerifyContent(obj.getVerifyContent());
				checkProject.setInvestigationTime(obj.getInvestigationTime() );
				checkProject.setCertificationBody(obj.getCertificationBody());
				checkProject.setCheckitemId(obj.getId());
				checkProjectMapper.insert(checkProject);

			}
		}

		
		
		//实现将file上传fastdfs
		if(this.updateCheckInfo(checkId, verify_source, verify_status,verify_level,beSelected)){
			File file = upLoadReportEntity.getFile();
			try {
				if(file!=null){
					String fileName = file.getName();
					MultipartFile multipartFile = new MockMultipartFile(fileName,new FileInputStream(file));
					String uploadFile = uploadService.uploadFile(multipartFile);
					LogUtils.getPlatformLogger().info("获取报告文件成功,背调Id:"+checkId+",文件名称:"+ fileName);

					Document document = new Document();
					document.setId(generateCodeService.getSingleId());
					document.setName(fileName);
					document.setUrl(uploadFile);
					document.setFileType(fileName.substring(fileName.lastIndexOf(".")));
					document.setServiceId(checkId);
					document.insert();
				}else{
					LogUtils.getExceptionLogger().error("["+agecyName+"]"+"获取报告文件失败,背调Id:"+checkId);
				}

			} catch (IOException e) {
				e.printStackTrace();
				LogUtils.getExceptionLogger().error(e.getMessage(),e);
			}


			return ResResult.success();
		 }
			return 	ResResult.failed();
			
		
	}
	
	/**
	 * 删除所有背调报告的记录（更具checkId）
	 * @param checkId
	 */
	private void removeAllCheckInfoByCheckId(Long checkId,Integer verifySource ,Long BeSelectedId){
		//删除所有个人信息记录
		UpdateWrapper<Credentials> credentialsProjectUpdateWrapper = new UpdateWrapper<Credentials>();
		credentialsProjectUpdateWrapper.eq("check_id", checkId);
		credentialsProjectUpdateWrapper.eq("verify_source", verifySource);
		credentialsProjectUpdateWrapper.eq("beSelected", BeSelectedId);
		credentialsMapper.delete(credentialsProjectUpdateWrapper);
		//删除所有背景调查信息记录
		UpdateWrapper<EducationBackground> educationBackgroundProjectUpdateWrapper = new UpdateWrapper<EducationBackground>();
		educationBackgroundProjectUpdateWrapper.eq("check_id", checkId);
		educationBackgroundProjectUpdateWrapper.eq("verify_source", verifySource);
		educationBackgroundProjectUpdateWrapper.eq("beSelected", BeSelectedId);
		educationBackgroundMapper.delete(educationBackgroundProjectUpdateWrapper);
		//删除所有工作经记录以及其对应的项目经验
		QueryWrapper<Experience> experienceProjectQueryWrapper = new QueryWrapper<Experience>();
		experienceProjectQueryWrapper.eq("check_id", checkId);
		experienceProjectQueryWrapper.eq("verify_source", verifySource);
		experienceProjectQueryWrapper.eq("beSelected", BeSelectedId);
		List<Experience> experienceList = experienceMapper.selectList(experienceProjectQueryWrapper);
		if(!experienceList.isEmpty()){
			List<Long>  experienceIdList = new ArrayList<>();
			for(Experience obj:experienceList){
				experienceIdList.add(obj.getId());
			}
			projectMapper.deleteProjcetByIdArr(experienceIdList);
		}
		
		UpdateWrapper<Experience> experienceProjectUpdateWrapper = new UpdateWrapper<Experience>();
		experienceProjectUpdateWrapper.eq("check_id", checkId);
		experienceProjectUpdateWrapper.eq("verify_source", verifySource);
		experienceProjectUpdateWrapper.eq("beSelected", BeSelectedId);
		experienceMapper.delete(experienceProjectUpdateWrapper);
		//删除所有不良记录的信息记录
		UpdateWrapper<BadRecord> badRecordUpdateWrapper = new UpdateWrapper<BadRecord>();
		badRecordUpdateWrapper.eq("check_id", checkId);
		badRecordUpdateWrapper.eq("verify_source", verifySource);
		badRecordUpdateWrapper.eq("beSelected", BeSelectedId);
		badRecordMapper.delete(badRecordUpdateWrapper);
		//删除所有商业利益的信息记录
		UpdateWrapper<CommercialProfit> commercialProfitUpdateWrapper = new UpdateWrapper<CommercialProfit>();
		commercialProfitUpdateWrapper.eq("check_id", checkId);
		commercialProfitUpdateWrapper.eq("verify_source", verifySource);
		commercialProfitUpdateWrapper.eq("beSelected", BeSelectedId);
		commercialProfitMapper.delete(commercialProfitUpdateWrapper);
		//删除所有法院诉讼信息记录
		UpdateWrapper<CourtProceeds> courtProceedsUpdateWrapper = new UpdateWrapper<CourtProceeds>();
		courtProceedsUpdateWrapper.eq("check_id", checkId);
		courtProceedsUpdateWrapper.eq("verify_source", verifySource);
		courtProceedsUpdateWrapper.eq("beSelected", BeSelectedId);
		courtProceedsMapper.delete(courtProceedsUpdateWrapper);
		//删除所有金融投诉信息记录
		UpdateWrapper<FinancialIrregularities> financialIrregularitiesUpdateWrapper = new UpdateWrapper<FinancialIrregularities>();
		financialIrregularitiesUpdateWrapper.eq("check_id", checkId);
		financialIrregularitiesUpdateWrapper.eq("verify_source", verifySource);
		financialIrregularitiesUpdateWrapper.eq("beSelected", BeSelectedId);
		financialIrregularitiesMapper.delete(financialIrregularitiesUpdateWrapper);
		//删除所有背调项信息的记录
		UpdateWrapper<CheckProject> checkProjectUpdateWrapper = new UpdateWrapper<CheckProject>();
		checkProjectUpdateWrapper.eq("check_id", checkId);
		checkProjectUpdateWrapper.eq("verify_source", verifySource);
		checkProjectMapper.delete(checkProjectUpdateWrapper);
		
	}
	
	private boolean  judgeCheckInfoStatueIsOperation(Long checkId){
		if(checkId == null || checkId <= 0){
			return false;
		}
		QueryWrapper<Check> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("id", checkId);
		Check checkResult = checkMapper.selectOne(queryWrapper);
		if(checkResult == null || checkResult.getVerifyStatus() == null){
			return true;
		}
		if(EnumVerifyStatus.FINAL_EDITION.getCode() == checkResult.getVerifyStatus()  ){
			 return false;
		}
		return true;
	}
	private boolean updateCheckInfo(Long checkId,Integer verify_source,Integer verify_status,Integer verify_level,Long beSelectedId){
		//最后修改背调状态
				Check info = new Check();
				info.setId(checkId);
				info.setVerifySource(verify_source);
				info.setVerifyStatus(verify_status);
				info.setVerifyLevel(verify_level);
				logger.info("info:"+JSONObject.toJSONString(info));
				if (verify_status != null){
					if(verify_status == EnumVerifyStatus.FIRST_EDITION.getCode()){
						info.setMiddleDate(DateUtils.getCurrentDate());
						logger.info("verify_status:"+EnumVerifyStatus.FIRST_EDITION.getCode());
					}else if(verify_status == EnumVerifyStatus.FINAL_EDITION.getCode()){
						logger.info("verify_status:"+EnumVerifyStatus.FINAL_EDITION.getCode());
						info.setCompleteDate(DateUtils.getCurrentDate());
						this.updateBeSeletedCheckStatusToBeVerify(verify_source,beSelectedId,checkId,verify_level);
						
					}
					
				}else{
					info.setSubmitDate(DateUtils.getCurrentDate());
				} 
		return checkMapper.updateById(info) > 0;
		
	}

	
	@Override
	public JSONObject getEntrustReportStatistics(Long verifyUnit) {
		Map<Integer,Integer> map = new HashMap<>();
		List<Integer> verifyStatusArr = null;
		Date startTime = null;
		Date endTime = null;
		List<Integer> checkStatusArr = new ArrayList<Integer>();
		/*checkStatusArr.add(EnumBeSelectedStatus.COMPLETED.getCode());
		checkStatusArr.add(EnumBeSelectedStatus.BACKGROUND_PROCESSING.getCode());*/
		verifyStatusArr = new ArrayList<>();
		verifyStatusArr.add(EnumVerifyStatus.SAVE_EDITION.getCode());
		verifyStatusArr.add(EnumVerifyStatus.FIRST_EDITION.getCode());
		Integer countTypeOne  = entrustReportMapper.getEntrustReportCount(verifyUnit, verifyStatusArr, checkStatusArr,
				EnumBeSelectedAgencyCheckStatus.CHECKING.getCode(), null, startTime, endTime);
		map.put(1, countTypeOne);
		Date[] monthDate = DateUtils.formatCurrentAllMonth();
		startTime =monthDate[0];
		endTime =monthDate[1];
		verifyStatusArr = new ArrayList<>();
		verifyStatusArr.add(EnumVerifyStatus.FINAL_EDITION.getCode());
		Integer countTypeTwo  = entrustReportMapper.getEntrustReportCount(verifyUnit, verifyStatusArr, checkStatusArr,
				EnumBeSelectedAgencyCheckStatus.CHECKING.getCode(), null, startTime, endTime);
		map.put(2, countTypeTwo);
		return ResResult.success(map,"请求成功!");
	}
  
	
	
	private void  updateBeSeletedCheckStatusToBeVerify(Integer verify_source,Long beSelectedId,Long checkId,Integer verify_level){
		
		
		//判断当前候选人关联的所有背调记录中（除了当前的背调记录）是否核实状态是否都为终版
		QueryWrapper<Check> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("beSelected", beSelectedId);
		queryWrapper.notIn("id", checkId);
		List<Check> list = checkMapper.selectList(queryWrapper);
		logger.info("获取当前候选人："+beSelectedId+"  ,(除了当前的背调记录："+checkId+" )  的所有背调记录："+ JSONObject.toJSONString(list));

		if(list.isEmpty()){
			 if(EnumVerifySource.SF.getCode().equals(verify_source)){
					logger.info("获取当前候选人："+beSelectedId+ ",  只进行自主背调：check_id：   "+checkId);
					//如果是红灯，check_status变为1，pass = 0
					 if(EnumVerifyLevel.RED.getCode() == verify_level){
						logger.info("获取当前候选人："+beSelectedId+ ",  只进行自主背调：check_id：   "+checkId +",  并且只进行给与红灯,直接完成背调");

						 //如果是红灯，check_status变为1，pass = 0
						 BeSelected info = new BeSelected();
							info.setId(beSelectedId);
							info.setCheckStatus(EnumBeSelectedStatus.COMPLETED.getCode());
							info.setCheckStatusTime(new Date());
							info.setPass(0);
							info.setVerifyLevel(verify_level);
							beSelectedMapper.updateById(info);
					 }else if(EnumVerifyLevel.GREEN.getCode() == verify_level){
						//绿灯变为7,staffstatus = 1 ,
							//否则变为7,staffstatus3 = 0,进入招聘负责人已审批状态
							logger.info("获取当前候选人："+beSelectedId+ ",  只进行自主背调：check_id：   "+checkId +",  并且只进行给与绿灯，无需招聘负责人给灯，需要背调负责人审核");
						 BeSelected info = new BeSelected();
							info.setId(beSelectedId);
							info.setCheckStatus(EnumBeSelectedStatus.CHECK_OVER.getCode());
							info.setCheckStatusTime(new Date());
							info.setStaffStatus(1);
							info.setStaffStatusDate(DateUtils.getCurrentDate());
							info.setStaffStatus3(0);
							info.setStaffStatusDate3(DateUtils.getCurrentDate());
							info.setStaffStatusDate(DateUtils.getCurrentDate());
							info.setVerifyLevel(verify_level);
							beSelectedMapper.updateById(info);
					 }else if(EnumVerifyLevel.BLUE.getCode() == verify_level){
						//其他变为7
							//否则变为7,staffstatus = 0,进入招聘负责人待审批状态
							logger.info("获取当前候选人："+beSelectedId+ ",  只进行自主背调：check_id：   "+checkId +",  并且只进行给与蓝灯，需要招聘负责人判断是否通过 ");
						 BeSelected info = new BeSelected();
							info.setId(beSelectedId);
							info.setStaffStatus(0);
							info.setStaffStatusDate(DateUtils.getCurrentDate());
							info.setCheckStatus(EnumBeSelectedStatus.CHECK_OVER.getCode());
							info.setCheckStatusTime(new Date());
							info.setVerifyLevel(verify_level);
							beSelectedMapper.updateById(info);
					 }else {
						 //黄灯
							//否则变为7,staffstatus3 = 0,进入招聘负责人待审批状态
							logger.info("获取当前候选人："+beSelectedId+ ",  只进行自主背调：check_id：   "+checkId +",  并且只进行给与黄灯，需要招聘负责人判断是否需要进行提交审批 ");
						 BeSelected info = new BeSelected();
							info.setId(beSelectedId);
							info.setStaffStatus(0);
							info.setStaffStatusDate(DateUtils.getCurrentDate());
							info.setCheckStatus(EnumBeSelectedStatus.CHECK_OVER.getCode());
							info.setCheckStatusTime(new Date());
							info.setVerifyLevel(verify_level);
							beSelectedMapper.updateById(info);
						 
					 }

			 }else{
				 BeSelected info = new BeSelected();
					info.setId(beSelectedId);
					info.setCheckStatus(EnumBeSelectedStatus.CHECK_OVER.getCode());
					info.setCheckStatusTime(new Date());
					info.setStaffStatus3(0);
					info.setStaffStatusDate3(DateUtils.getCurrentDate());
					info.setVerifyLevel(verify_level);
					beSelectedMapper.updateById(info);
				//否则变为7,staffstatus3 = 0,进入背调负责人待审批状态
					logger.info("获取当前候选人："+beSelectedId+ ",  只进行第三方中介背调：check_id：   "+checkId);

			 }
		}else{
			Integer num = 0;
			for(Check obj:list){
				if(obj.getVerifyStatus() == EnumVerifyStatus.FINAL_EDITION.getCode()){
					num ++;
				}
			}
		//自主背调最后一个完成提交终版
		if(list.size() == num){
			logger.info("获取当前候选人："+beSelectedId+",所有的背调过程都完成了，需要进入下一阶段："+EnumBeSelectedStatus.CHECK_OVER.getMsg() );
			//如果都为终版，则将对应beSelected记录中的状态修改为7，
			BeSelected info = new BeSelected();
			info.setId(beSelectedId);
			info.setCheckStatus(EnumBeSelectedStatus.CHECK_OVER.getCode());
			info.setStaffStatus3(0);
			info.setStaffStatusDate3(DateUtils.getCurrentDate());
			info.setCheckStatusTime(new Date());
			if(beSelectedMapper.updateById(info) > 0){
				/*//同时更新check统计边中的数量：根据登陆的recruitLeaderId
				//将状态为212和222数量都减一
				//
				List<Integer> checkStatusArr = new ArrayList<>();
				checkStatusArr.add(EnumBeSelectedSelfCheckStatus.WORKCHECK.getCode());
				checkStatusArr.add(EnumBeSelectedAgencyCheckStatus.CHECKING.getCode());
				checkStatisticsMapper.updateCheckStatistcsCountByUserIdStatus(DefaultConst.DEFAULT_DECREACE_OPERATION, checkStatusArr, userId);
				//将状态7加1
				QueryWrapper<CheckStatistics> checkStatisticsQueryWrapper = new QueryWrapper<>();
				checkStatisticsQueryWrapper.eq("user_id", userId);
				checkStatisticsQueryWrapper.eq("check_status", EnumBeSelectedStatus.CHECK_OVER.getCode());
				List<CheckStatistics> checkStatisticsList = checkStatisticsMapper.selectList(checkStatisticsQueryWrapper);
				if(checkStatisticsList == null || checkStatisticsList.isEmpty()){
					CheckStatistics entity = new CheckStatistics();
					entity.setId(generateCodeService.getSingleId());
					entity.setCheckStatus(EnumBeSelectedStatus.CHECK_OVER.getCode());
					entity.setUserId(userId);
					entity.setStatistics(DefaultConst.DEFAULT_INCREACE_OPERATION);
					checkStatisticsMapper.insert(entity);
				}else{
					List<Integer> checkStatusArr1 = new ArrayList<>();
					checkStatusArr1.add(EnumBeSelectedStatus.CHECK_OVER.getCode());
					checkStatisticsMapper.updateCheckStatistcsCountByUserIdStatus(DefaultConst.DEFAULT_INCREACE_OPERATION, checkStatusArr1, userId);
				}*/
				
			}
		}else{
			//第三方来的报告只有一份？
			if(EnumVerifySource.SF.getCode()  == verify_source){
				//将当前的211-1，212+1
				/*List<Integer> decreaceStatus = new ArrayList<>();
				decreaceStatus.add(EnumBeSelectedSelfCheckStatus.ONLINE.getCode());
				checkStatisticsMapper.updateCheckStatistcsCountByUserIdStatus(DefaultConst.DEFAULT_DECREACE_OPERATION, decreaceStatus, userId);
				List<Integer> increaceStatus = new ArrayList<>();
				increaceStatus.add(EnumBeSelectedSelfCheckStatus.WORKCHECK.getCode());
				checkStatisticsMapper.updateCheckStatistcsCountByUserIdStatus(DefaultConst.DEFAULT_INCREACE_OPERATION, increaceStatus, userId);*/
				BeSelected entity = new BeSelected();
				entity.setId(beSelectedId);
				entity.setSelfCheckStatus(EnumBeSelectedSelfCheckStatus.WORKCHECK.getCode());
				beSelectedMapper.updateById(entity);
			}else{
				//将当前的222-1
				/*List<Integer> decreaceStatus = new ArrayList<>();
				decreaceStatus.add(EnumBeSelectedAgencyCheckStatus.CHECKING.getCode());
				checkStatisticsMapper.updateCheckStatistcsCountByUserIdStatus(DefaultConst.DEFAULT_DECREACE_OPERATION, decreaceStatus, userId);*/
				
			}
			
		 }
		}
		
		
	}
	
	
	
	private boolean checkExpirenceInfoInvilad(Experience  experience){
		if(StrUtil.isEmpty(experience.getName()) || StrUtil.isEmpty(experience.getAddress())|| StrUtil.isEmpty(experience.getVisitor())){
			return false;
		}
		
		return true;
		
		
		
	}
}
