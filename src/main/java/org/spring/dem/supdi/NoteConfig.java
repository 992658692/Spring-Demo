package org.spring.dem.supdi;

import org.spring.dem.di2.BlankDisc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
//@PropertySource����properties�����ļ�
//���ļ��ڵ����ݼ��ص�Environment�����в�����һ��������bean����
@PropertySource("classpath:app.properties")
public class NoteConfig {

	@Autowired
	private Environment env;
	
	@Bean
	public BlankDisc disc () {
		return new BlankDisc(
				env.getProperty("disc.title"),
				env.getProperty("disc.artist"));
	}
}
