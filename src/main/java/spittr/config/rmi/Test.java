package spittr.config.rmi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import spittr.config.datasource.Spitter;

public class Test {

	@Autowired
	//����RMI������spring��ע��spitterService��bean����
	private SpitterService spitterService;
	
	public void test () {
		System.out.println(spitterService);
	}
}
