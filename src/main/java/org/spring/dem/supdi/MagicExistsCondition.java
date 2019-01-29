package org.spring.dem.supdi;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class MagicExistsCondition implements Condition{

	//Spring4.0之后@profile注解就是通过@Conditional注解和Condition实现来实现它的功能的
	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		Environment env = (Environment) context.getEnvironment();
		return env.containsProperty("magic");
	}
	
	//ConditionContext接口
	//getRegistry()返回的BeanDefinitionRegistry检查bean定义
	//getBeanFactory()返回的ConfigurableListableBeanFactory检查bean是否存在，甚至探查bean的属性
	//getEnvironment()返回的Environment检查环境变量是否存在以及它的值是什么
	//getResourceLoader()返回的ResourceLoader所加载的资源
	//getClassLoader()返回的ClassLoader加载并检查类是否存在
	
	
	//AnnotatedTypeMetadata接口
	//该接口能让我们检查系统中带@bean的注解上还有其他什么注解
	//借助isAnnotated()方法，我们能够判断带有@Bean注解的方法是不是还有其他特定的注解。借助其他的那些方法，
	//我们能够检查@Bean注解的方法上其他注解的属性

}
