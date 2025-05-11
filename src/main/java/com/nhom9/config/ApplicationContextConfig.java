package com.nhom9.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.nhom9")
@PropertySource(value = { "classpath:db.properties" })
public class ApplicationContextConfig {

	@Autowired
	Environment env;

	@Bean
	public static PropertySourcesPlaceholderConfigurer configurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		viewResolver.setContentType("text/html; charset=UTF-8");
		return viewResolver;
	}

	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource bundleMessageSource = new ReloadableResourceBundleMessageSource();
		bundleMessageSource.setBasename("classpath:messages");
		bundleMessageSource.setDefaultEncoding("UTF-8");
		return bundleMessageSource;
	}

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
		driverManagerDataSource.setDriverClassName(env.getProperty("sqlserver.driver"));
		driverManagerDataSource.setUrl(env.getProperty("sqlserver.url"));
		driverManagerDataSource.setUsername(env.getProperty("sqlserver.username"));
		driverManagerDataSource.setPassword(env.getProperty("sqlserver.password"));
		return driverManagerDataSource;
	}

	@Bean
	public JdbcTemplate jdbcTemplate() {
		return new JdbcTemplate(dataSource());
	}

	@Bean(name = "multipartResolver")
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setMaxUploadSize(10485760); // 10MB
		return multipartResolver;
	}
	
	@Bean
	public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername("tranhungbinh17052003@gmail.com"); //email real
        mailSender.setPassword("lusx ducl pope wnia"); //password app password

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true"); // Chế độ debug

        return mailSender;
    }
	

}
