package org.yefei.qa.mock;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan(basePackages = {"org.yefei.qa.mock"})
@EnableScheduling
public class MockServerRestApplication  extends SpringBootServletInitializer {

	public static void main(String[] args) {
        SpringApplication.run(MockServerRestApplication.class, args);
	}

	@Configuration
	@MapperScan(basePackages = {"org.yefei.qa.mock.model.gen.dao"}, sqlSessionFactoryRef="sqlSessionFactoryBean")
	class DBConfig{}


}
