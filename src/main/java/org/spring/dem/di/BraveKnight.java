package org.spring.dem.di;

public class BraveKnight implements Knight{

	private Quest quest;
	
	/** ����ʵ��Quest�ӿ� ���ܷ������������� �����spring ����ע���ԭ��*/
	public BraveKnight (Quest quest) {
		this.quest = quest;
	}
	
	public void embarkOnQuest () {
		quest.toString();
	}
}
