package pe.edu.upeu.gth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.jasperreports.JasperReportsMultiFormatView;
import org.springframework.web.servlet.view.jasperreports.JasperReportsViewResolver;


import pe.edu.upeu.gth.properties.globalProperties;

import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.context.annotation.FilterType;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan(basePackages = "pe.edu.upeu.gth")
@EnableScheduling
public class AppConfig extends WebMvcConfigurerAdapter {
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// TODO Auto-generated method stub
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
		super.addResourceHandlers(registry);
	}

	@Bean
	public JasperReportsViewResolver getJasperReportsViewResolver() {
		JasperReportsViewResolver resolver = new JasperReportsViewResolver();
		resolver.setPrefix("classpath:/jasperreports/");
		resolver.setSuffix(".jasper");
		resolver.setReportDataKey("datasource");
		resolver.setViewNames("*_report");
		resolver.setViewClass(JasperReportsMultiFormatView.class);
		resolver.setOrder(1);
		return resolver;
	}
	
	
	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/jsp/");
		viewResolver.setSuffix(".jsp");
		viewResolver.setOrder(2);
		return viewResolver;
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Bean
    public static DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
        dataSource.setUrl("jdbc:oracle:thin:@" + globalProperties.HOSTNAME + ":" + globalProperties.PORT + ":" + globalProperties.SID);
        dataSource.setUsername(globalProperties.USER);
        dataSource.setPassword(globalProperties.USER_PWD);
        return dataSource;
    }
	
	

    @Bean
    public MultipartResolver multipartResolver() throws IOException {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize((1024 * 1024) * 10);
        return multipartResolver;
    }

	
    
//    CONFIGURACION DE MULTIPARTRESOLVER PARA TRABAJAR CONN ARCHUIVOS--
    /*@Bean(name = "multipartResolver")
    public CommonsMultipartResolver getResolver() throws IOException {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        // no limit
        resolver.setMaxUploadSize(500000);
        
        return resolver;
    }*/
    @Bean
    public JavaMailSender getMailSender(){
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
         
        //Using gmail
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername("harolcotac@gmail.com");
        mailSender.setPassword("gppcztdqrcanvpbg");
         
        Properties javaMailProperties = new Properties();
        javaMailProperties.put("mail.smtp.starttls.enable", "true");
        javaMailProperties.put("mail.smtp.auth", "true");
        javaMailProperties.put("mail.transport.protocol", "smtp");
        javaMailProperties.put("mail.debug", "true");//Prints out everything on screen
         
        mailSender.setJavaMailProperties(javaMailProperties);
        return mailSender;
    }
	
//	@Bean
//    public PedidoDAO getemailDao() {
//        return new PedidoDaoImp(getDataSource());
//    }
	
}
