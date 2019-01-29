package org.spring.dem.aop;

import org.spring.dem.di.Knight;
import org.spring.dem.di.Quest;

public class BraveKnight implements Knight{

	private Quest quest;
	
	private Minstrel minstrel;
	
	public BraveKnight (Quest quest, Minstrel minstrel) {
		this.quest = quest;
		this.minstrel = minstrel;
	}
	
	public void embarkOnQuest () {
		System.out.println(11);
	}
}
