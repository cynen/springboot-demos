package com.bazn.vip.multi;


import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * AOP切面,动态切换数据源.
 */

@Aspect
@Component
public class DataSourceAspect {

    @Before("@annotation(ds)")
    public void beforeDataSource(DataSource ds){
        DataSourceType value = ds.value();
        DataSourceContextHolder.setContextHolder(value);
        System.out.println("当前数据源是:" + value);
    }

    @After("@annotation(ds)")
    public void afterDataSource(DataSource ds){
        DataSourceContextHolder.clearContextHolder();
    }


}
