package org.spring.dem.di;

public class BraveKnight implements Knight{

	private Quest quest;
	
	/** 凡是实现Quest接口 就能符合条件被创建 这就是spring 构造注入的原型*/
	public BraveKnight (Quest quest) {
		this.quest = quest;
	}
	
	public void embarkOnQuest () {
		quest.toString();
	}
}
