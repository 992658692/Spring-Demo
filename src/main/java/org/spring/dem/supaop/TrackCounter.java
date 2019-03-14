package org.spring.dem.supaop;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

public class TrackCounter {

	private Map<Integer, Integer> trackCounts = new HashMap<Integer, Integer>();
	
	//定义切点时可以定义切点的参数类型为int
	//参数名用args(trackNumber)限定符来表示
	@Pointcut("execution(** org.spring.dem.supaop.TrackCounter.trackPlayed(int) && args(trackNumber))")
	public void trackPlayed (int trackNumber) {
	}
	
	//通知中也可以使用切点的参数(以方法名(参数名))这样的方式将参数带入到通知内，这样这个前置通知就可以使用该参数了
	@Before("trackPlayed(trackNumber)")
	public void countTrack (int trackNumber) {
		int currentCount = getPlayCount(trackNumber);
		trackCounts.put(trackNumber, currentCount + 1);
	}
	
	public int getPlayCount (int trackNumber) {
		return trackCounts.containsKey(trackNumber)?trackCounts.get(trackNumber) : 0;
	}
}
