package org.spring.dem.di2;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.stereotype.Component;

//@Component ע�������������Spring ������ҪΪ����ഴ��bean
//���������spirng�е�idΪ������������ֻ������ĸ�����СдsgtPeppers
//Ҳ����Ϊ���id�Զ���һ������
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
