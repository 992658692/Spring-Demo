package org.spring.dem.supaop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

//该注解表明该POJO是一个切面
@Aspect
//启动切面自动代理
//它会为每个@Aspect注解创建一个代，然后围绕切点所匹配的bean，执行前后的通知方法。
@EnableAspectJAutoProxy
public class Audience {
	
	//AOP切面的执行顺序
	//around==》before==》切点方法==》around==》after==》AfterThrowing==》AfterReturning
	
	//多重AOP aop1(order1) aop2(order2)
	//order来定义aop的执行顺序 从1==》2
	//aop1.around==aop1.befor==>aop2.around==>aop2.befor==>aop2.around==>aop2.after==>aop1.around==>aop1.after
	
	//如果单纯的在@Aspect注解中使用@bean注解的话 那么
	//那么Spring容器中其实不会有切面的内容被解析，即@befor @after这些注解都不会被解析
	//如果想让一个切面既实现POJO的功能，又实现切面功能，必须要启动自动代理功能
	@Bean
	public Audience audience() {
		return new Audience();
	}
	
	//其实该方法的内容并不重要，定义该方法只为了让@Pointcut注解去依附，至于方法的具体实现其实并没有被真正的使用到
	@Pointcut("execution(** org.spring.dem.supaop.Performance.perform(..))")
	public void performce() {}

	@Before("performce()")
	public void befor () {
		System.out.println("beafor-Audience");
	}
	
	@After("performce()")
	public void after () {
		System.out.println("after-Audience");
	}
	
	@Before("performce()")
	public void befor_t () {
		System.out.println("befor-Audience-t");
	}
	
	@AfterReturning("performce()")
	public void afterRun () {
		System.out.println("afterRun-Audience");
	}
	
	@AfterThrowing("performce()")
	public void afterThrow () {
		System.out.println("afterThrow-Audience");
	}
	
	@Around("performce()")
	public void around (ProceedingJoinPoint pj) {
		try {
			//ProceedingJoinPoint接口是必填参数
			//该接口的作用是执行切点所指向的方法
			//该例子中即指向performce()方法
			//如果没有传入这个参数 那么该通知就会被阻塞，也就说如果没有该接口参数去执行切点方法的话
			//那么切点对应的方法就不会被调用
			System.out.println("befor-Audience");
			pj.proceed();
			System.out.println("after-Audience");
		} catch (Exception e) {
			System.out.println("afterThrow-Audience");
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}
