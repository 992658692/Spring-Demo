package spittr.config.rest;

import org.springframework.context.annotation.Bean;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;

//�������ֽ����������в���ȱ�ݣ������Ƽ�ʹ��Spring����Ϣת����
public class ContentNegotiation {

	@Bean
	//����webConfig���������������Ĭ������֮��
	//��Ҫ��Ĭ�ϵ�����ע�뵽�ý���������
	public ViewResolver cnViewResolver(ContentNegotiationManager cnm) {
		//��ͼ������
		//���ῼ�ǵ�Acceptͷ����Ϣ��ʹ�����������ý�����ͣ������������Ȳ鿴URL���ļ���չ��
		//���URL�ڽ�β�����ļ���չ���Ļ����ý�������������ļ���չ��ȷ���������͡�
		//������չ����.json�Ļ�����ô������������ͱ�����'application/json'
		//��������ļ���չ�����ܵõ��κ�ý�����͵Ļ����ŻῪʼ����Acceptͷ����Ϣ
		//��������Acceptͷ����ϢҲû�ж�Ӧ���͵Ļ����ý�������ʹ��'/'��ΪĬ����������
		//һ����������ȷ�Ϻ����ͽ����߼���ͼ������Ϊ��Ⱦģʽ��View���������ǽ�����������ͼ
		//�����������ģ��������߱��������ܣ�
		
		//�ý��������ŵ��������ȫ����Ʋ���뿪��ʹ����ͬ��һ�׿����������ܲ�����ͬ���͵�����
		//���Ǹý�����ֻ�ܾ�����Դ�����Ⱦ���ͻ��ˣ��������漰�ͻ��˷�ʲô���ݸ�������
		//����ֻ����Ⱦģ�͸��ͻ��ˣ���������Դ
		ContentNegotiatingViewResolver cnvr = new ContentNegotiatingViewResolver();
		cnvr.setContentNegotiationManager(cnm);
		return cnvr;
	}
}
