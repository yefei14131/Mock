package org.yefei.qa.mock;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.yefei.qa.mock.config.ApplicationContextStaticProvider;

@SpringBootApplication
@ComponentScan(basePackages = {"org.yefei.qa.mock"})
public class MockAdminApplication  extends SpringBootServletInitializer {

	public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(MockAdminApplication.class, args);
        ApplicationContextStaticProvider.setApplicationContext(ctx);
	}


	@Configuration
	@MapperScan(basePackages = {"org.yefei.qa.mock.model.gen.dao", "org.yefei.qa.mock.mapper.dao"}, sqlSessionFactoryRef="sqlSessionFactoryBean")
	class DBConfig{}

}
