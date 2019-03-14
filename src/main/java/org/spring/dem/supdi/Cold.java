package org.spring.dem.supdi;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.beans.factory.annotation.Qualifier;

//自定义限定符注解，这样就可以用@cold注解代替@Qualifier("cold")
//自定义的限定符注解，可以摆脱字符串的限制 也可以java中规定的相同的注解只能作用一次的限制
@Target({ElementType.CONSTRUCTOR, ElementType.FIELD,
		 ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Qualifier
public @interface Cold {

}
