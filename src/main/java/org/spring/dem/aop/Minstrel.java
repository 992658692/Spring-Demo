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
		//befor֮ǰ
		System.out.println("around");
		System.out.println("around-befor");
		//befor֮��after֮ǰ
		pj.proceed();
		//after֮ǰ
		System.out.println("around");
	}
}
