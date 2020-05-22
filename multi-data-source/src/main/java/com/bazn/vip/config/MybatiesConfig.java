package com.bazn.vip.config;

import com.bazn.vip.multi.DataSourceType;
import com.bazn.vip.multi.DynamicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 配置 mybaties的配置文件,
 * 主要是配置2个数据源 DataSource 用于 替换数据源.
 */

@Configuration
public class MybatiesConfig {


    /**
     * 给 DataSource命名
     * @return
     */
    @Bean("db1DataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.db1")
    public DataSource db1DataSource(){
        return DataSourceBuilder.create().build();
    }

    /**
     *  给另外一个数据源命令 (IOC中的bean的名称)
     * @return
     */
    @Bean("db2DataSource")
    @ConfigurationProperties(prefix = "spring.datasource.db2")
    public DataSource db2DataSource(){
        return DataSourceBuilder.create().build();
    }

    /**
     * 将2个数据源,注入到 动态数据源列表中.
     * @param db1DataSource
     * @param db2DataSource
     * @return
     */
    @Bean
    public DynamicDataSource dataSource(@Qualifier("db1DataSource") DataSource db1DataSource,
                                        @Qualifier("db2DataSource") DataSource db2DataSource){
        Map map = new HashMap();
        map.put(DataSourceType.DB1,db1DataSource);
        map.put(DataSourceType.DB2,db2DataSource);

        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        dynamicDataSource.setTargetDataSources(map);
        dynamicDataSource.setDefaultTargetDataSource(db1DataSource);
        return dynamicDataSource;
    }


    @Bean
    public SqlSessionFactory sqlSessionFactory(DynamicDataSource dataSource) throws Exception{
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        // Resource[] resources = new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*/*.xml");
        return factoryBean.getObject();
    }


}
