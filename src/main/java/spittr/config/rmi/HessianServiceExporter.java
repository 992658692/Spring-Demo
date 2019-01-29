package spittr.config.rmi;

import org.springframework.context.annotation.Bean;

import com.caucho.hessian.server.HessianServlet;

//由于Hession是基于HTTP的，所以如果你想导出Hession服务除了要配置内部服务之外，还需要实现一个SpringMvc的控制器

public class HessianServiceExporter extends HessianServlet{
	
	@Bean
	public HessianServiceExporter hessionExportedSpitterSerivce(SpitterService spitter) {
		HessianServiceExporter exporter = new HessianServiceExporter();
		//hession与RMI的区别就是不需要设置ServiceName
		exporter.setService(spitter);
		exporter.setAPIClass(SpitterService.class);
		return exporter;
	}

}
