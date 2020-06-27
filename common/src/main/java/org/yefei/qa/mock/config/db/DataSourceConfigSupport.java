package org.yefei.qa.mock.config.db;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.WebStatFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.FilterRegistrationBean;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Created by yefei on 2018/8/1.
 */
@Slf4j
public class DataSourceConfigSupport {
    
    private static final long TIME_OUT = 15000; //超时时间15秒 单位ms

    public static DataSource getDataSource(String driverClassName, String url, String username, String password, int initialSize, int minIdle, int maxActive, String validationQuery) {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(driverClassName);
        druidDataSource.setUrl(url);
        druidDataSource.setUsername(username);
        druidDataSource.setPassword(password);
        druidDataSource.setInitialSize(initialSize);
        druidDataSource.setMinIdle(minIdle);
        druidDataSource.setMaxActive(maxActive);
        druidDataSource.setValidationQuery(validationQuery);
        druidDataSource.setMaxWait(TIME_OUT); //设置超时时间为15秒
        try {
            log.debug("Setting 'application.yml' into DataSource");
            druidDataSource.setFilters("stat, wall");
        } catch (SQLException e) {
            throw new IllegalStateException("Could not initial DataSource\n" + e);
        }
        return druidDataSource;
    }

    public static FilterRegistrationBean getFilterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        log.debug("Registered druid filter");
        return filterRegistrationBean;
    }
}
