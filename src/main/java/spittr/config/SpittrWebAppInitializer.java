package spittr.config;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;


//AbstractAnnotationConfigDispatcherServletInitializer
//ʹ�ø���ȥ����ǰ�˿�������ʱ��web.xml��Ҫ����web�汾(�汾����3.0���ϲſ���ȥxml��)
//���ø÷�ʽ������ȱ����Ǵ���Ų�����(��Ŀû������mavenjar�� ���ǵ�������������ֻ��web.xml��ʾ������ʱ��Ż��ڿ���̨��ʾ����)

//WebApplicationInitializer web3.0 ȥxml���ıر��ӿ�  ������ɨ��ʵ�ָýӿڵ���
public class SpittrWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	//���̳�AbstractAnnotationConfigDispatcherServletInitializer
	//��ʱ��ᴴ��DispatcherServlet��ContextLoadListener
	//���Լ̳�������ʱ��ͻ���д3������
	
	@Override
	//���ط���������ݳ־ò��bean
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[]{RootConfig.class};
	}

	@Override
	//���ؿ��Ʋ㣬��ͼ��������������ӳ�����ص�bean
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[]{WebConfig.class};
	}

	@Override
	//��DispatcherServletӳ�䵽"/"
	//������������/��ʾ�������󶼿��Խ��뵽���ƻ� Ȼ���·���ctrl��
	protected String[] getServletMappings() {
		return new String[]{"/*"};
	}
	
	@Override
	//���ö�multipart��֧�֣����ϴ��ļ�����ʱĿ¼�洢��/src/main��
	protected void customizeRegistration (Dynamic registration) {
		registration.setMultipartConfig(new MultipartConfigElement("/src/main"));
	}
	
/*	@Override
	//Ҳ�����������ķ�ʽ����fileter �÷�ʽ���Դ�����filter ���Ҳ���Ҫӳ��·��
	//ӳ���·����DispatchServletһ��
	protected Filter[] getServletFilters () {
		return new Filter[]{(Filter) new MyServlet()} ;
	}*/

}
