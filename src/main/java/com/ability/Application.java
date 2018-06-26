package com.ability;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class Application{
	/**
	 * 实现SpringBootServletInitializer可以让spring-boot项目在web容器中运行
	 */
//	@Override extends SpringBootServletInitializer // 开启通用注解扫描
//	@ComponentScan
//	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//		builder.sources(this.getClass());
//		return super.configure(builder);
//	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

	}
}