package org.spring.dem.di2;

import org.spring.dem.di.Knight;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

//@Configuration生命该类是个配置类
@Configuration
//@ComponentScan 启动Spring组件扫描
//如果没有指定扫描范围的话，默认的范围是该类对应的包及子包。
//会扫描这些范围内带有@Component注解的类并为他们创建一个bean
//如果想自定义扫描范围的话可以利用basePackages属性来指定想扫描的包的路径，参数是个数组多个路径可以以逗号隔开
//还有一个basePackageClasses属性，参数是类，意思指扫描指定类对应的包,也是数组格式以逗号形式分开
/**推荐以类的形式来扫描，如果以String路径的形式，在重构的过程中如果包路径发生改变 就比较难以控制错误的蔓延了*/
@ComponentScan(basePackageClasses = {Knight.class})
//可以通过Import注解来引入其余的配置javaConfig类
@Import(CdConfig.class)
//可以通过ImportResource注解来引入xml的配置文件
@ImportResource("classpath:spring-di.xml")
public class CdPalyerConfig {
	
	//显示装配bean
	//显示装配与自动扫描的区别：
	//显示装配更对的是针对于第三方依赖的jar包，由于我们无法在第三方的组件中添加扫描与注入
	//这个时候更需要以显示装配的方式往spring中声明式的创建bean
	
	//@Bean注解会将new的对象返回给Spring容器
	//也可以通过name属性改变该对象在Spring容器中的ID
	@Bean
	public CompactDisc sgtPepper () {
		return new SgtPeppers();
	}
	
	//CdPlayer的创建需要依赖sgtPepper
	//由于Spring是单利的，所以这里的方法调用，并不是单纯java上的方法调用
	//而是当sgtPepper上使用bean注解之后，那么Spring就会拦截所有其他bean创建时对它的调用
	//并确保直接返回sgtPepper所创建的对象。而不是没个依赖都要调用一次这个方法
	/*@Bean
	public CdPlayer cdPlayer () {
		return new CdPlayer(sgtPepper());
	}*/
	
	//以入参的形式优点
	//这样Spring会在自己的容器中寻找匹配的bean注入进来
	//这个时候这个bean的创建形式就没有要求了，可以是xml中创建的，也可以是自动扫描的时候创建的
	//总之以入参的方式可以忽视对象的创建途径，不一定非要javaconfig中创建的bean
	@Bean
	public CdPlayer cdPlayer (CompactDisc dc) {
		return new CdPlayer(dc);
	}
}
