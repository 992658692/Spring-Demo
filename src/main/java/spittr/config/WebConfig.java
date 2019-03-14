package spittr.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;

import spittr.config.ctrl.User;

@Configuration
//启动mvc注解<mvc:annotation-driven/>
@EnableWebMvc
@ComponentScan("spittr.config.ctrl")
public class WebConfig extends WebMvcConfigurerAdapter {
	
	@Bean
	public User user () {
		return new User();
	}
	
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	
	@Override
	//创建ContentNegotiatingViewResolver视图解析器的方法之一：
	//声明一个ContentNegotiationManager的bean
	//重载该方法就能创建一个ContentNegotiationManager
	//而ContentNegotiationConfigurer对象能对ContentNegotiationManager做一些类型设置
	//该案例中是将默认内容设置为application/json
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		configurer.defaultContentType(MediaType.APPLICATION_JSON);
	}
}
