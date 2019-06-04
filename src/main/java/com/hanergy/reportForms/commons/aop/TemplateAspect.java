package com.hanergy.reportForms.commons.aop;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;

import com.hanergy.reportForms.service.ICredentialsService;

//@Aspect
//@Configuration
public class TemplateAspect {

    @Autowired
    private ICredentialsService credentialsService;

    private static Logger logger = LogManager.getLogger(TemplateAspect.class);


    @Pointcut("@annotation(com.hanergy.reportForms.commons.aop.Template)")
    public void cachePointcut() {
    }

    @Around(value = "cachePointcut() && @annotation(template)")
    public Object aroundUser(ProceedingJoinPoint point, Template user) throws Throwable {
        return point.proceed();
    }


}
