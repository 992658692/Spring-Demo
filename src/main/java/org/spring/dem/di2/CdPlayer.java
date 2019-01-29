package org.spring.dem.di2;

import org.springframework.beans.factory.annotation.Autowired;

public class CdPlayer implements MediaPlayer{

	private CompactDisc cd;
	
	//@Autowiredע���������ע���κ���ҪCompactDisc����bean�ĵط�
	//�����ǹ��췽������set������
	//����CompactDiscΪ����ӿڣ�����Spring�д�����SgtPeppers����ע�����
	//��ע�⻹��һ��required���ԣ������Ի���Spring��û�ж�Ӧbean��ʱ�������bean����δװ��״̬
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
