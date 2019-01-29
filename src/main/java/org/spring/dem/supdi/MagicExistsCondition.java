package org.spring.dem.supdi;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class MagicExistsCondition implements Condition{

	//Spring4.0֮��@profileע�����ͨ��@Conditionalע���Conditionʵ����ʵ�����Ĺ��ܵ�
	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		Environment env = (Environment) context.getEnvironment();
		return env.containsProperty("magic");
	}
	
	//ConditionContext�ӿ�
	//getRegistry()���ص�BeanDefinitionRegistry���bean����
	//getBeanFactory()���ص�ConfigurableListableBeanFactory���bean�Ƿ���ڣ�����̽��bean������
	//getEnvironment()���ص�Environment��黷�������Ƿ�����Լ�����ֵ��ʲô
	//getResourceLoader()���ص�ResourceLoader�����ص���Դ
	//getClassLoader()���ص�ClassLoader���ز�������Ƿ����
	
	
	//AnnotatedTypeMetadata�ӿ�
	//�ýӿ��������Ǽ��ϵͳ�д�@bean��ע���ϻ�������ʲôע��
	//����isAnnotated()�����������ܹ��жϴ���@Beanע��ķ����ǲ��ǻ��������ض���ע�⡣������������Щ������
	//�����ܹ����@Beanע��ķ���������ע�������

}
