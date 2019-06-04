package com.hanergy.reportForms.config;

import com.hanergy.reportForms.commons.utils.SpringBeanUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName ServeltConfig
 * @Description TODO
 * @Author DURONGHONG
 * @DATE 2018/9/14 16:57
 * @Version 1.0
 **/
@Component
public class ServeltConfig {

    @Autowired
    private ApplicationContext applicationContext;

    /**
     * 初始化SpringBeanUtils
     *
     * @return
     */
    @Bean
    public SpringBeanUtils springBeanUtils() {
        SpringBeanUtils beanUtils = new SpringBeanUtils();
        beanUtils.setApplicationContext(applicationContext);
        return beanUtils;
    }
}
