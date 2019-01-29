package spittr.config.rmi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import spittr.config.datasource.Spitter;

public class Test {

	@Autowired
	//利用RMI就能往spring中注入spitterService的bean对象
	private SpitterService spitterService;
	
	public void test () {
		System.out.println(spitterService);
	}
}
