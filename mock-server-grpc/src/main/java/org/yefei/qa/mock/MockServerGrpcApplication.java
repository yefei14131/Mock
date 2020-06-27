package org.yefei.qa.mock;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.net.InetAddress;
import java.net.UnknownHostException;

//import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * Spring Boot 启动器
 */
@Slf4j
@SpringBootApplication
@ComponentScan(basePackages = {"org.yefei.qa.mock"})
@EnableScheduling
public class MockServerGrpcApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(MockServerGrpcApplication.class, args);

		try {
			System.setProperty("hostName", InetAddress.getLocalHost().getHostName());
		} catch (UnknownHostException e) {
			log.error("项目启动类异常 exception is:{}", ExceptionUtils.getStackTrace(e));
		}
	}

	@Configuration
	@MapperScan(basePackages = {"org.yefei.qa.mock.model.gen.dao"}, sqlSessionFactoryRef="sqlSessionFactoryBean")
	class DBConfig{}


}
