package org.spring.dem.supaop;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

public class TrackCounter {

	private Map<Integer, Integer> trackCounts = new HashMap<Integer, Integer>();
	
	//�����е�ʱ���Զ����е�Ĳ�������Ϊint
	//��������args(trackNumber)�޶�������ʾ
	@Pointcut("execution(** org.spring.dem.supaop.TrackCounter.trackPlayed(int) && args(trackNumber))")
	public void trackPlayed (int trackNumber) {
	}
	
	//֪ͨ��Ҳ����ʹ���е�Ĳ���(�Է�����(������))�����ķ�ʽ���������뵽֪ͨ�ڣ��������ǰ��֪ͨ�Ϳ���ʹ�øò�����
	@Before("trackPlayed(trackNumber)")
	public void countTrack (int trackNumber) {
		int currentCount = getPlayCount(trackNumber);
		trackCounts.put(trackNumber, currentCount + 1);
	}
	
	public int getPlayCount (int trackNumber) {
		return trackCounts.containsKey(trackNumber)?trackCounts.get(trackNumber) : 0;
	}
}
