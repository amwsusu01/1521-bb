package com.hanergy.reportForms.config;

import com.hanergy.reportForms.commons.enums.EnumAgencyType;

import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @ClassName CorsConfiguration
 * @Description TODO 解决跨域问题
 * @Author DURONGHONG
 * @DATE 2018/9/19 11:54
 * @Version 1.0
 **/
public class CorsConfig {


    private CorsConfiguration buildConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*"); // 1允许任何域名使用
        corsConfiguration.addAllowedHeader("*"); // 2允许任何头
        corsConfiguration.addAllowedMethod("*"); // 3允许任何方法（post、get等）
        return corsConfiguration;
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", buildConfig()); // 4
        return new CorsFilter(source);
    }

    @Bean
    public EnumAgencyType enumAgencyType(){
        return EnumAgencyType.getEnum(0);
    }
}
