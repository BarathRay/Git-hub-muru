package com.intellect;
import javax.sql.DataSource;

import org.h2.jdbcx.JdbcDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.validation.Validator;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.intellect.validators.UserValidator;
import com.intellect.validators.UserValidator2;

@Configuration
@SpringBootApplication(scanBasePackages="com.intellect")
@EnableWebMvc
public class SpringBootApp {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootApp.class, args);
	}


	@Autowired
	private Environment environment;
	
	
	@Bean
	public DataSource dataSource()
	{
		JdbcDataSource datasource=new JdbcDataSource();
		datasource.setUrl(environment.getProperty("spring.datasource.url"));
		datasource.setUser(environment.getProperty("spring.datasource.username"));
		return datasource;
	}
	
	@Bean
	public JdbcTemplate jdbcTemplate()
	{
		JdbcTemplate jdbcTemplate=new JdbcTemplate(dataSource());
		return jdbcTemplate;
	}
	
	@Bean("userval")
	public Validator userValidator()
	{
		Validator bean = new UserValidator();
		return bean;
	}
	
	@Bean("userval2")
	public Validator userValidator2()
	{
		Validator bean = new UserValidator2();
		return bean;
	}
	
	@Bean
	public ResourceBundleMessageSource messageSource()
	{
		ResourceBundleMessageSource msg=new ResourceBundleMessageSource();
		msg.setBasename("messages");
		return msg;
		
	}

}
