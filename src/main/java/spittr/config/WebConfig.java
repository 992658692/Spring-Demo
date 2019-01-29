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
//����mvcע��
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
	//����ContentNegotiatingViewResolver��ͼ�������ķ���֮һ��
	//����һ��ContentNegotiationManager��bean
	//���ظ÷������ܴ���һ��ContentNegotiationManager
	//��ContentNegotiationConfigurer�����ܶ�ContentNegotiationManager��һЩ��������
	//�ð������ǽ�Ĭ����������Ϊapplication/json
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		configurer.defaultContentType(MediaType.APPLICATION_JSON);
	}
}
