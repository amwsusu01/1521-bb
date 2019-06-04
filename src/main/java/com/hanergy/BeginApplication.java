package com.hanergy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@ImportResource(locations = {"classpath:datasourcemysql.xml"})
@ServletComponentScan
@EnableScheduling
public class BeginApplication {

    private static Logger logger = LogManager.getLogger(BeginApplication.class);

    public static void main(String[] args) {
        logger.info("============================1521报表系统开始启动==================================");
        System.setProperty("es.set.netty.runtime.available.processors", "false");
        SpringApplication.run(BeginApplication.class);
        logger.info("============================1521报表系统启动完成==================================");
    }
    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
