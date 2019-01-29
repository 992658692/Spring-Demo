package org.spring.dem.supaop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;

@Aspect
public class EncoreableIntroducer {

	//由于Performance是一个接口，所以+号表示所有实现这个接口的子类，而不是指向单一的实现
	//而defaultImpl指的是encoreable接口的具体实现类
	@DeclareParents(value="org.spring.dem.supaop.Performance+",
						defaultImpl=EncoreableImpl.class)
	public static Encoreable encoreable;
}
