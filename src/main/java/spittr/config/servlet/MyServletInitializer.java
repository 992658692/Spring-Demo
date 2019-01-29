package spittr.config.servlet;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.WebApplicationInitializer;

public class MyServletInitializer implements WebApplicationInitializer{

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		//注册servlet
//		Dynamic myServlet = servletContext.addServlet("myServlet", (Class<? extends Servlet>) MyServlet.class);
//		//映射servlet
//		myServlet.addMapping("/");
		
		//如果想在项目中加入照片上传的功能，
		//那么除了在容器中创建StandardServletMultipartResolver之外
		//还要针对muitipar照片的上传做一些参数设置
		//我们可以将这些配置与DispatcherServlet前端控制器绑定在一起
		//这里定义的是照片上传的临时文件路径
		//2097152 表示上传照片的最大容量是2M(默认没有限制)
		//4194304表示整个multipart请求的最大容量是4M(默认没有限制)
		//0表示所有上传的照片都会写入到临时文件目录下(0是默认值， 可以设置一定大小的照片才可以存到临时目录下)
//		myServlet.setMultipartConfig(new MultipartConfigElement("/src/mian", 2097152, 4194304, 0));
//		
//		//注册filter
//		javax.servlet.FilterRegistration.Dynamic myFilter = 
//				servletContext.addFilter("myServlet", (Class<? extends Filter>)MyServlet.class);
//		//映射filter路径
//		myFilter.addMappingForServletNames(null, false, "/user/***");
	}

}
