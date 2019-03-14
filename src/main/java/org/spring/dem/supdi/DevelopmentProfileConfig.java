package org.spring.dem.supdi;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Configuration
//Profile注解的作用是标识该类内的bean只有在dev profile激活的时候
//才会生效，被创建，否则该类中被bean标注的方法都会被忽视
//而这个注解在Spring3.2之后就可以作用在具体的方法上，将作用范围缩小

/** 
 *	在web.xml中通过服务器解析来启动对应的配置
 * <context-param>
 * 	<param-name>spring.profiles.default</param-name>
 * 	<para-value>dev</param-value>
 * </context-param>
 * 还有一种方式是@ActiveProfiles的方式来启动(没有对应依赖jar就不演示了)
 * */
@Profile("dev")

//@Conditional注解的功能是当满足，注解内条件类时该类才会被创建
//conditional的属性可以是所有实现Condition接口的类
//然后实现该接口的类会实现一个matches，当matches方法返回true的时候
//类才会被创建 ，如果返回为false 则该类会被spring忽视
@Conditional(MagicExistsCondition.class)
//@Primary注解解决的是当一个应用中出现多个不唯一的bean的时候 相关的依赖会首选该注解下的bean
//当然这个注解是不能同时对多个实现同个接口的bean使用的 不然又会产生新的歧义性问题
@Primary()
public class DevelopmentProfileConfig {

}
