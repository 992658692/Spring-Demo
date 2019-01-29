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

//��ע�������POJO��һ������
@Aspect
//���������Զ�����
//����Ϊÿ��@Aspectע�ⴴ��һ������Ȼ��Χ���е���ƥ���bean��ִ��ǰ���֪ͨ������
@EnableAspectJAutoProxy
public class Audience {
	
	//AOP�����ִ��˳��
	//around==��before==���е㷽��==��around==��after==��AfterThrowing==��AfterReturning
	
	//����AOP aop1(order1) aop2(order2)
	//order������aop��ִ��˳�� ��1==��2
	//aop1.around==aop1.befor==>aop2.around==>aop2.befor==>aop2.around==>aop2.after==>aop1.around==>aop1.after
	
	//�����������@Aspectע����ʹ��@beanע��Ļ� ��ô
	//��ôSpring��������ʵ��������������ݱ���������@befor @after��Щע�ⶼ���ᱻ����
	//�������һ�������ʵ��POJO�Ĺ��ܣ���ʵ�����湦�ܣ�����Ҫ�����Զ�������
	@Bean
	public Audience audience() {
		return new Audience();
	}
	
	//��ʵ�÷��������ݲ�����Ҫ������÷���ֻΪ����@Pointcutע��ȥ���������ڷ����ľ���ʵ����ʵ��û�б�������ʹ�õ�
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
			//ProceedingJoinPoint�ӿ��Ǳ������
			//�ýӿڵ�������ִ���е���ָ��ķ���
			//�������м�ָ��performce()����
			//���û�д���������� ��ô��֪ͨ�ͻᱻ������Ҳ��˵���û�иýӿڲ���ȥִ���е㷽���Ļ�
			//��ô�е��Ӧ�ķ����Ͳ��ᱻ����
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
