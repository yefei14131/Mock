/**
 * Copyright (c) 2014 http://www.jieqianhua.com
 */
package org.yefei.qa.mock.config.db;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * Created by yefei on 2018/8/2.
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "datasource")
@Slf4j
public class DataSourceConfiguration {
    private String driverClassName;

    private String url;

    private String username;

    private String password;

    private int initialSize;

    private int maxActive;

    private int minIdle;

    private String validationQuery;

//    @Bean(initMethod = "init", destroyMethod = "close")
    @Bean
    public DataSource druidDataSource() {
        return DataSourceConfigSupport.getDataSource(
                driverClassName,
                url,
                username,
                password,
                initialSize,
                minIdle,
                maxActive,
                validationQuery);
    }

    @Bean
    public SqlSessionFactory sqlSessionFactoryBean() throws Exception {

        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();

        //Can no longer use application.properties for mybatis configuration settings, so this is a hack for
        //application.properties: mybatis.configuration.map-underscore-to-camel-case=true
//        org.apache.ibatis.session.Configuration ibatisConfiguration = new org.apache.ibatis.session.Configuration();
//        ibatisConfiguration.setMapUnderscoreToCamelCase(true);
//        sqlSessionFactoryBean.setConfiguration(ibatisConfiguration);

        sqlSessionFactoryBean.setDataSource(druidDataSource());
        return sqlSessionFactoryBean.getObject();
    }

}
