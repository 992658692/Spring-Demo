package org.spring.dem.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.spring.dem.di.PrintStream;

public class Minstrel {

	private PrintStream stream;
	
	public Minstrel (PrintStream stream) {
		this.stream = stream;
	}
	
	public void singBeforeQuest () {
		System.out.println("befor-Minstrel");
	}
	
	public void singAfterQuest () {
		System.out.println("after-Minstrel");
	}
	
	public void singAroundQuest (ProceedingJoinPoint pj) throws Throwable {
		//befor之前
		System.out.println("around");
		System.out.println("around-befor");
		//befor之后after之前
		pj.proceed();
		//after之前
		System.out.println("around");
	}
}
