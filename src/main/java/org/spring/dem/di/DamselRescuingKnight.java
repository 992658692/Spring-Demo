package org.spring.dem.di;

public class DamselRescuingKnight implements Knight{

	private RescueDamselQuest quest;
	
	/** ����Ķ������ģʽ̫������*/
	public DamselRescuingKnight () {
		quest = new RescueDamselQuest();
	}
	
	public void embarkOnQuest () {
		quest.toString();
	}
}
