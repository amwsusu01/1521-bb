package com.hanergy.reportForms.commons.utils;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 数据库生成JAVA类
 */
public class MybatisPlusUtils {

    public static String[] getAllTables() {
        List<String> list = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://192.168.18.202:3306/hanergy_background?useUnicode=true&characterEncoding=utf-8&useSSL=false", "root", "root123");
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet tables = metaData.getTables(null, null, null, new String[]{"TABLE"});
            while (tables.next()) {
                list.add(tables.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list.toArray(new String[]{});
    }

    /**
     * 自动代码生成类
     *
     * @param includeTables
     * @param excludeTables
     */
    public static void generateMysql(String[] includeTables, String[] excludeTables, Boolean db1) {
        AutoGenerator autoGenerator = new AutoGenerator();
        /**
         * 数据库配置
         */
//        if (db1) {
//            buildDb1(autoGenerator);
//        } else {
//            buildDb2(autoGenerator);
//        }

        buildDb3(autoGenerator);
        /**
         * 数据库表配置
         */
        StrategyConfig strategyConfig = new StrategyConfig();
        // 驼峰命名
        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
        // 需要生成的表名
//        includeTables = new String[]{"user"};
        // 不需要生成的表名
//        excludeTables = new String[]{};
        strategyConfig.setInclude(includeTables);
        strategyConfig.setExclude(excludeTables);
        autoGenerator.setStrategy(strategyConfig);

        /**
         * 全局配置
         */
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setAuthor("fangshuai");
        globalConfig.setDateType(DateType.ONLY_DATE);
        globalConfig.setActiveRecord(true);
        globalConfig.setIdType(IdType.INPUT);
        globalConfig.setOutputDir("D:/test");
        globalConfig.setMapperName("%sMapper");
        globalConfig.setServiceName("I%sService");
        globalConfig.setServiceImplName("%sServiceImpl");
        globalConfig.setXmlName("%sMapper");
        globalConfig.setSwagger2(true);
        globalConfig.setFileOverride(true);
        globalConfig.setBaseColumnList(false);
        globalConfig.setBaseResultMap(false);
        globalConfig.setEnableCache(false);
        autoGenerator.setGlobalConfig(globalConfig);

        /**
         * 包名配置
         */
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent("");
        if (db1) {
            packageConfig.setEntity("com.hanergy.reportForms.mapper.entity");
            packageConfig.setMapper("com.hanergy.reportForms.mapper");
            packageConfig.setXml("mybatis");
            packageConfig.setService("com.hanergy.reportForms.service");
            packageConfig.setServiceImpl("com.hanergy.reportForms.service.impl");
            packageConfig.setController("com.hanergy.reportForms.controller");
        } else {
            packageConfig.setEntity("com.hanergy.reportForms.db2.mapper.entity");
            packageConfig.setMapper("com.hanergy.reportForms.db2.mapper");
            packageConfig.setXml("mybatis/db2");
            packageConfig.setService("com.hanergy.reportForms.db2.service");
            packageConfig.setServiceImpl("com.hanergy.reportForms.db2.service.impl");
            packageConfig.setController("com.hanergy.reportForms.db2.controller");
        }


        autoGenerator.setPackageInfo(packageConfig);
        // 采用默认模板,自定义模板参考@see com.baomidou.mybatisplus.generator.config.TemplateConfig
//        TemplateConfig templateConfig = new TemplateConfig();
        autoGenerator.setTemplateEngine(new FreemarkerTemplateEngine());
        autoGenerator.execute();
    }

    private static void buildDb1(AutoGenerator autoGenerator) {
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL);
        dataSourceConfig.setDriverName("com.mysql.jdbc.Driver");
        dataSourceConfig.setUsername("root");
        dataSourceConfig.setPassword("root123");
        dataSourceConfig.setUrl("jdbc:mysql://192.168.18.202:3306/hanergy_background?useUnicode=true&characterEncoding=utf-8&useSSL=false");
        autoGenerator.setDataSource(dataSourceConfig);
    }
    private static void buildDb3(AutoGenerator autoGenerator) {
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL);
        dataSourceConfig.setDriverName("com.mysql.jdbc.Driver");
        dataSourceConfig.setUsername("ycroot");
        dataSourceConfig.setPassword("hnyc1!");
        dataSourceConfig.setUrl("jdbc:mysql://10.4.237.111:3306/hanergy_background?useUnicode=true&characterEncoding=utf-8&useSSL=false");
        autoGenerator.setDataSource(dataSourceConfig);
    }



    private static void buildDb2(AutoGenerator autoGenerator) {
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL);
        dataSourceConfig.setDriverName("com.mysql.jdbc.Driver");
        dataSourceConfig.setUsername("root");
        dataSourceConfig.setPassword("root123");
        dataSourceConfig.setUrl("jdbc:mysql://127.0.0.1:3306/renren_fast?useUnicode=true&characterEncoding=utf-8&useSSL=false");
        autoGenerator.setDataSource(dataSourceConfig);
    }

    public static void main(String[] args) {
//        generateMysql(new String[]{},new String[]{});
//        String user = String.format("%sMapper", "User");
//        System.out.println(user);

//        String[] allTables = getAllTables();
//        System.out.println(JSONObject.toJSONString(allTables));

//        generateMysql(new String[]{"check","beSelected","experience"},null);
//        generateMysql(new String[]{"agency","checkTemplate","function","role","role_function","template","user"},null);
//        generateMysql(new String[]{"agency","beSelected","check","checkItem","checkTemplate","credentials","education_background","experience","function","role","role_function","template","user"},null);
//        generateMysql(new String[]{"user_role"}, null, true);
        generateMysql(new String[]{"dict_major"}, null, true);

//        String[] tables = getAllTables();
//        System.out.println(JSONObject.toJSONString(tables));
    }

}
