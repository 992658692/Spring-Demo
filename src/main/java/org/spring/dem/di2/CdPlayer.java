package org.spring.dem.di2;

import org.springframework.beans.factory.annotation.Autowired;

public class CdPlayer implements MediaPlayer{

	private CompactDisc cd;
	
	//@Autowired注解可以用来注入任何需要CompactDisc对象bean的地方
	//不管是构造方法还是set方法。
	//由于CompactDisc为基类接口，所以Spring中创建的SgtPeppers可以注入进来
	//该注解还有一个required属性，该属性会在Spring中没有对应bean的时候，让这个bean处于未装配状态
	@Autowired(required=false)
	public CdPlayer (CompactDisc cd) {
		this.cd = cd;
	}
	
	@Autowired
	public void setCd(CompactDisc cd) {
		this.cd = cd;
	}

	public void play () {
		cd.play();
	}
}
