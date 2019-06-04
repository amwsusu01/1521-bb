package com.hanergy.reportForms.commons.aop;

import com.hanergy.reportForms.config.DataSourceType;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DataSourceAop {
    @Before("execution(* com.hanergy.reportForms.service.*.*(..))")
    public void setDataSourceAss() {
        DataSourceType.setDataBaseType(DataSourceType.DataBaseType.ass);
    }

    @Before("execution(* com.hanergy.reportForms.service.problem..*.*(..))")
    public void setDataSourceAsm() {
        DataSourceType.setDataBaseType(DataSourceType.DataBaseType.asm);
    }

    @Before("execution(* com.hanergy.reportForms.service.detailcollect..*.*(..)) || execution(* com.hanergy.reportForms.service.personnelflow..*.*(..))")
    public void setDataSourceAsn() {
        DataSourceType.setDataBaseType(DataSourceType.DataBaseType.asn);
    }

    @Before("execution(* com.hanergy.reportForms.service.product..*.*(..))")
    public void setDataSourceAddRecord() {
        DataSourceType.setDataBaseType(DataSourceType.DataBaseType.addrecord);
    }
}
