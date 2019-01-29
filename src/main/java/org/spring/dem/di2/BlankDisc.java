package org.spring.dem.di2;

import java.util.List;

public class BlankDisc implements CompactDisc{

	private String title;
	private String artist;
	private List<String> tracks;
	
	public BlankDisc (String title, String artist, List<String> tracks) {
		this.title = title;
		this.artist = artist;
		this.tracks = tracks;
	}
	
	public BlankDisc(String title, String artist) {
		this.title = title;
		this.artist = artist;
	}

	@Override
	public void play() {
		System.out.println("Playing" + title + "by" + artist);
		for (String s : tracks) {
			System.out.println("-Tracks:" + s);
		}
	}
}
