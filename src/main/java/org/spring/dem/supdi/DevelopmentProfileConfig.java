package org.spring.dem.supdi;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Configuration
//Profileע��������Ǳ�ʶ�����ڵ�beanֻ����dev profile�����ʱ��
//�Ż���Ч������������������б�bean��ע�ķ������ᱻ����
//�����ע����Spring3.2֮��Ϳ��������ھ���ķ����ϣ������÷�Χ��С

/** 
 *	��web.xml��ͨ��������������������Ӧ������
 * <context-param>
 * 	<param-name>spring.profiles.default</param-name>
 * 	<para-value>dev</param-value>
 * </context-param>
 * ����һ�ַ�ʽ��@ActiveProfiles�ķ�ʽ������(û�ж�Ӧ����jar�Ͳ���ʾ��)
 * */
@Profile("dev")

//@Conditionalע��Ĺ����ǵ����㣬ע����������ʱ����Żᱻ����
//conditional�����Կ���������ʵ��Condition�ӿڵ���
//Ȼ��ʵ�ָýӿڵ����ʵ��һ��matches����matches��������true��ʱ��
//��Żᱻ���� ���������Ϊfalse �����ᱻspring����
@Conditional(MagicExistsCondition.class)
//@Primaryע�������ǵ�һ��Ӧ���г��ֶ����Ψһ��bean��ʱ�� ��ص���������ѡ��ע���µ�bean
//��Ȼ���ע���ǲ���ͬʱ�Զ��ʵ��ͬ���ӿڵ�beanʹ�õ� ��Ȼ�ֻ�����µ�����������
@Primary()
public class DevelopmentProfileConfig {

}
