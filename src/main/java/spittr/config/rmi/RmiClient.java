package spittr.config.rmi;

import org.springframework.context.annotation.Bean;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;
import org.springframework.remoting.rmi.RmiServiceExporter;

public class RmiClient {
	

	@Bean
	//创建RMI服务
	public RmiServiceExporter rmiExporter(SpitterService spitterService) {
		RmiServiceExporter rmiExprter = new RmiServiceExporter();
		rmiExprter.setService(spitterService);//把一个bean放到RMI，使得该bean发布为一个RMI服务
		rmiExprter.setServiceName("spitterService");//定义RMI的服务
		rmiExprter.setServiceInterface(SpitterService.class);//该RMI为所有实现该接口的服务
		rmiExprter.setRegistryHost("rmi.spitter.com");//定义RMI注册表
		rmiExprter.setRegistryPort(1099);//定义RMI端口1099是默认RMI端口
		return rmiExprter;
	}
	
	@Bean
	//创建RMI代理，通过代理来调用SpitterService的RMI服务
	public RmiProxyFactoryBean spitterService() {
		RmiProxyFactoryBean rmiProxy = new RmiProxyFactoryBean();
		//rmi是通讯协议 localhost是指本机 SpitterService是指服务名
		rmiProxy.setServiceUrl("rmi://localhost/SpitterService");
		rmiProxy.setServiceInterface(SpitterService.class);
		return rmiProxy;
	}
}
