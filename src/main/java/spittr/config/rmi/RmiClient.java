package spittr.config.rmi;

import org.springframework.context.annotation.Bean;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;
import org.springframework.remoting.rmi.RmiServiceExporter;

public class RmiClient {
	

	@Bean
	//����RMI����
	public RmiServiceExporter rmiExporter(SpitterService spitterService) {
		RmiServiceExporter rmiExprter = new RmiServiceExporter();
		rmiExprter.setService(spitterService);//��һ��bean�ŵ�RMI��ʹ�ø�bean����Ϊһ��RMI����
		rmiExprter.setServiceName("spitterService");//����RMI�ķ���
		rmiExprter.setServiceInterface(SpitterService.class);//��RMIΪ����ʵ�ָýӿڵķ���
		rmiExprter.setRegistryHost("rmi.spitter.com");//����RMIע���
		rmiExprter.setRegistryPort(1099);//����RMI�˿�1099��Ĭ��RMI�˿�
		return rmiExprter;
	}
	
	@Bean
	//����RMI����ͨ������������SpitterService��RMI����
	public RmiProxyFactoryBean spitterService() {
		RmiProxyFactoryBean rmiProxy = new RmiProxyFactoryBean();
		//rmi��ͨѶЭ�� localhost��ָ���� SpitterService��ָ������
		rmiProxy.setServiceUrl("rmi://localhost/SpitterService");
		rmiProxy.setServiceInterface(SpitterService.class);
		return rmiProxy;
	}
}
