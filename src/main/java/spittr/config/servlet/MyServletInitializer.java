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
		//ע��servlet
//		Dynamic myServlet = servletContext.addServlet("myServlet", (Class<? extends Servlet>) MyServlet.class);
//		//ӳ��servlet
//		myServlet.addMapping("/");
		
		//���������Ŀ�м�����Ƭ�ϴ��Ĺ��ܣ�
		//��ô�����������д���StandardServletMultipartResolver֮��
		//��Ҫ���muitipar��Ƭ���ϴ���һЩ��������
		//���ǿ��Խ���Щ������DispatcherServletǰ�˿���������һ��
		//���ﶨ�������Ƭ�ϴ�����ʱ�ļ�·��
		//2097152 ��ʾ�ϴ���Ƭ�����������2M(Ĭ��û������)
		//4194304��ʾ����multipart��������������4M(Ĭ��û������)
		//0��ʾ�����ϴ�����Ƭ����д�뵽��ʱ�ļ�Ŀ¼��(0��Ĭ��ֵ�� ��������һ����С����Ƭ�ſ��Դ浽��ʱĿ¼��)
//		myServlet.setMultipartConfig(new MultipartConfigElement("/src/mian", 2097152, 4194304, 0));
//		
//		//ע��filter
//		javax.servlet.FilterRegistration.Dynamic myFilter = 
//				servletContext.addFilter("myServlet", (Class<? extends Filter>)MyServlet.class);
//		//ӳ��filter·��
//		myFilter.addMappingForServletNames(null, false, "/user/***");
	}

}
