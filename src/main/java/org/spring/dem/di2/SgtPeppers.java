package org.spring.dem.di2;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.stereotype.Component;

//@Component 注解就是用来告诉Spring 容器，要为这个类创建bean
//而这个类在spirng中的id为这个类的类名，只是首字母变成了小写sgtPeppers
//也可以为这个id自定义一个名字
@Component
public class SgtPeppers implements CompactDisc, Condition{

	private static String title = "Sgt. Pepper's Lonely Hearts Club Band";
	private static String artist = "The Beatles";
	
	public void play () {
		System.out.println("Playing" + title + "by" + artist);
	}

	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		return false;
	}
}
