package org.yefei.qa.mock;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableAutoConfiguration
@EnableScheduling
@ComponentScan(basePackages = {"org.yefei.qa.mock"})
public class SchedulerApplication {

	public static void main(String[] args) {
        SpringApplication.run(SchedulerApplication.class, args);
	}


	@Configuration
	@MapperScan(basePackages = {"org.yefei.qa.mock/model/gen/dao"}, sqlSessionFactoryRef="sqlSessionFactoryBean")
	class DBConfig{}


}
