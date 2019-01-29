package org.spring.dem;

import org.spring.dem.aop.BraveKnight;
import org.spring.dem.di.Knight;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class KnightMain {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = 
				new ClassPathXmlApplicationContext("knight-aop.xml");
		BraveKnight k = context.getBean(BraveKnight.class);
		k.embarkOnQuest();
		context.close();
	}
}
