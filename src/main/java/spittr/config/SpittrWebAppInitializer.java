package spittr.config;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;


//AbstractAnnotationConfigDispatcherServletInitializer
//使用该类去创建前端控制器的时候web.xml需要配置web版本(版本必须3.0以上才可以去xml化)
//利用该方式创建的缺点就是错粗排查困难(项目没有依赖mavenjar包 但是单纯启动不报错，只有web.xml显示创建的时候才会在控制台提示错误)

//WebApplicationInitializer web3.0 去xml化的必备接口  容器会扫描实现该接口的类
public class SpittrWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	//当继承AbstractAnnotationConfigDispatcherServletInitializer
	//的时候会创建DispatcherServlet和ContextLoadListener
	//所以继承这个类的时候就会重写3个方法
	
	@Override
	//加载服务层与数据持久层的bean
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[]{RootConfig.class};
	}

	@Override
	//加载控制层，试图解析器，处理器映射等相关的bean
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[]{WebConfig.class};
	}

	@Override
	//将DispatcherServlet映射到"/"
	//控制器的请求，/表示所有请求都可以进入到控制机 然后下发给ctrl层
	protected String[] getServletMappings() {
		return new String[]{"/*"};
	}
	
	@Override
	//设置对multipart的支持，将上传文件的临时目录存储在/src/main下
	protected void customizeRegistration (Dynamic registration) {
		registration.setMultipartConfig(new MultipartConfigElement("/src/main"));
	}
	
/*	@Override
	//也可以以这样的方式创建fileter 该方式可以传入多个filter 而且不需要映射路径
	//映射的路径与DispatchServlet一致
	protected Filter[] getServletFilters () {
		return new Filter[]{(Filter) new MyServlet()} ;
	}*/

}
