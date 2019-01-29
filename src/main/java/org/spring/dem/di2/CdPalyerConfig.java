package org.spring.dem.di2;

import org.spring.dem.di.Knight;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

//@Configuration���������Ǹ�������
@Configuration
//@ComponentScan ����Spring���ɨ��
//���û��ָ��ɨ�跶Χ�Ļ���Ĭ�ϵķ�Χ�Ǹ����Ӧ�İ����Ӱ���
//��ɨ����Щ��Χ�ڴ���@Componentע����ಢΪ���Ǵ���һ��bean
//������Զ���ɨ�跶Χ�Ļ���������basePackages������ָ����ɨ��İ���·���������Ǹ�������·�������Զ��Ÿ���
//����һ��basePackageClasses���ԣ��������࣬��˼ָɨ��ָ�����Ӧ�İ�,Ҳ�������ʽ�Զ�����ʽ�ֿ�
/**�Ƽ��������ʽ��ɨ�裬�����String·������ʽ�����ع��Ĺ����������·�������ı� �ͱȽ����Կ��ƴ����������*/
@ComponentScan(basePackageClasses = {Knight.class})
//����ͨ��Importע�����������������javaConfig��
@Import(CdConfig.class)
//����ͨ��ImportResourceע��������xml�������ļ�
@ImportResource("classpath:spring-di.xml")
public class CdPalyerConfig {
	
	//��ʾװ��bean
	//��ʾװ�����Զ�ɨ�������
	//��ʾװ����Ե�������ڵ�����������jar�������������޷��ڵ���������������ɨ����ע��
	//���ʱ�����Ҫ����ʾװ��ķ�ʽ��spring������ʽ�Ĵ���bean
	
	//@Beanע��Ὣnew�Ķ��󷵻ظ�Spring����
	//Ҳ����ͨ��name���Ըı�ö�����Spring�����е�ID
	@Bean
	public CompactDisc sgtPepper () {
		return new SgtPeppers();
	}
	
	//CdPlayer�Ĵ�����Ҫ����sgtPepper
	//����Spring�ǵ����ģ���������ķ������ã������ǵ���java�ϵķ�������
	//���ǵ�sgtPepper��ʹ��beanע��֮����ôSpring�ͻ�������������bean����ʱ�����ĵ���
	//��ȷ��ֱ�ӷ���sgtPepper�������Ķ��󡣶�����û��������Ҫ����һ���������
	/*@Bean
	public CdPlayer cdPlayer () {
		return new CdPlayer(sgtPepper());
	}*/
	
	//����ε���ʽ�ŵ�
	//����Spring�����Լ���������Ѱ��ƥ���beanע�����
	//���ʱ�����bean�Ĵ�����ʽ��û��Ҫ���ˣ�������xml�д����ģ�Ҳ�������Զ�ɨ���ʱ�򴴽���
	//��֮����εķ�ʽ���Ժ��Ӷ���Ĵ���;������һ����Ҫjavaconfig�д�����bean
	@Bean
	public CdPlayer cdPlayer (CompactDisc dc) {
		return new CdPlayer(dc);
	}
}
