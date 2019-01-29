package org.spring.dem.supdi;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
//Scope�������崴����bean��������
//singleton������
//prototype���ǵ���
//session:webӦ���лỰ����bean
//request:webӦ�������󴴽�bean
//�Ự������bean�Ĵ�����⣺
//�����������Ӿ��ǹ��ﳵϵͳ�����õ����Ŵ������ﳵ��bean��ʱ����ô���е��û����ʵĶ���ͬһ�����ﳵ����������
//���÷ǵ�����ģʽ���������ﳵ��bean�Ļ�����ô���ܿ�����һ��Ӧ���лᴴ���ü�����ͬ�Ĺ��ﳵ��
//����һ���û����ܾͻ��кü������ﳵ
//�������ʱ����ûỰ������bean����õķ�����һ���ػ���Ӧ����һ��bean����ôһ���û��ӷ���Ӧ�ÿ�ʼ���Ƴ�����ֻ��һ���Ự
//���һỰ��bean��������Ŀ������ʱ�򴴽��ģ��������û�����Ự֮�󴴽���
//�������Ŀ������ʱ���б��bean���������bean�Ļ� ��ôϵͳ�������Ựbean����һ������
//����������bean������������������ϡ�
//���������ǽӿڵĻ�������ScopedProxyMode.INTERFACES��������������ýӿ��Ǵ���
//�������಻�ǽӿڶ���ʵ����Ļ���ô����Ҫ��ScopedProxyMode.TARGET_CLASS������������
@Scope(value=WebApplicationContext.SCOPE_SESSION,
		proxyMode=ScopedProxyMode.TARGET_CLASS)
public class Notepad {

}
