package spittr.config.rmi;

import org.springframework.context.annotation.Bean;

import com.caucho.hessian.server.HessianServlet;

//����Hession�ǻ���HTTP�ģ�����������뵼��Hession�������Ҫ�����ڲ�����֮�⣬����Ҫʵ��һ��SpringMvc�Ŀ�����

public class HessianServiceExporter extends HessianServlet{
	
	@Bean
	public HessianServiceExporter hessionExportedSpitterSerivce(SpitterService spitter) {
		HessianServiceExporter exporter = new HessianServiceExporter();
		//hession��RMI��������ǲ���Ҫ����ServiceName
		exporter.setService(spitter);
		exporter.setAPIClass(SpitterService.class);
		return exporter;
	}

}
