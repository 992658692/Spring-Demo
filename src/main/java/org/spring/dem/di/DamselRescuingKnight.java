package org.spring.dem.di;

public class DamselRescuingKnight implements Knight{

	private RescueDamselQuest quest;
	
	/** 最初的对象管理模式太过死板*/
	public DamselRescuingKnight () {
		quest = new RescueDamselQuest();
	}
	
	public void embarkOnQuest () {
		quest.toString();
	}
}
