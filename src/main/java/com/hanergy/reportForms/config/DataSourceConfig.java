package com.hanergy.reportForms.config;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@Configuration
@MapperScan(basePackages = "com.hanergy.reportForms.mapper", sqlSessionFactoryRef = "SqlSessionFactory")
public class DataSourceConfig {
    @Primary
    @Bean(name = "DataSource1")
    @ConfigurationProperties(prefix = "spring.datasource.ass")
    public DataSource getDateSource1() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "DataSource2")
    @ConfigurationProperties(prefix = "spring.datasource.asm")
    public DataSource getDateSource2() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "DataSource3")
    @ConfigurationProperties(prefix = "spring.datasource.asn")
    public DataSource getDateSource3() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "DataSource4")
    @ConfigurationProperties(prefix = "spring.datasource.addrecord")
    public DataSource getDateSource4() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "dynamicDataSource")
    public DynamicDataSource DataSource(@Qualifier("DataSource1") DataSource dataSource1,
                                        @Qualifier("DataSource2") DataSource dataSource2,
                                        @Qualifier("DataSource3") DataSource dataSource3,
                                        @Qualifier("DataSource4") DataSource dataSource4) {
        Map<Object, Object> targetDataSource = new HashMap<>();
        targetDataSource.put(DataSourceType.DataBaseType.ass, dataSource1);
        targetDataSource.put(DataSourceType.DataBaseType.asm, dataSource2);
        targetDataSource.put(DataSourceType.DataBaseType.asn, dataSource3);
        targetDataSource.put(DataSourceType.DataBaseType.addrecord, dataSource4);
        DynamicDataSource dataSource = new DynamicDataSource();
        dataSource.setTargetDataSources(targetDataSource);
        dataSource.setDefaultTargetDataSource(dataSource1);
        return dataSource;
    }

    @Bean(name = "SqlSessionFactory")
    public SqlSessionFactory test1SqlSessionFactory(@Qualifier("dynamicDataSource") DataSource dynamicDataSource)
            throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dynamicDataSource);
        bean.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources("classpath:/mybatis/*.xml"));
        return bean.getObject();
    }
}
