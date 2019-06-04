package com.hanergy.reportForms.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class MybatisPlusConfig {


    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer configurer = new MapperScannerConfigurer();
        configurer.setBasePackage("com.hanergy.reportForms.mapper*");
        return configurer;
    }

    @Bean
    public PerformanceInterceptor performanceInterceptor(){
        PerformanceInterceptor interceptor = new PerformanceInterceptor();
        interceptor.setWriteInLog(true);
        interceptor.setFormat(true);
        return interceptor;
    }
}
