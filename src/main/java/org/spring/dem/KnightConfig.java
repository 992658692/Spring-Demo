package org.spring.dem;

import org.spring.dem.di.BraveKnight;
import org.spring.dem.di.Knight;
import org.spring.dem.di.PrintStream;
import org.spring.dem.di.Quest;
import org.spring.dem.di.SlayDragonQuest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KnightConfig {

	@Bean
	public Knight knight() {
		return new BraveKnight(quest());
	}
	
	@Bean
	public Quest quest() {
		return new SlayDragonQuest(new PrintStream());
	}
}
