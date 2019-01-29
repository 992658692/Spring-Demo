package org.spring.dem.supaop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;

@Aspect
public class EncoreableIntroducer {

	//����Performance��һ���ӿڣ�����+�ű�ʾ����ʵ������ӿڵ����࣬������ָ��һ��ʵ��
	//��defaultImplָ����encoreable�ӿڵľ���ʵ����
	@DeclareParents(value="org.spring.dem.supaop.Performance+",
						defaultImpl=EncoreableImpl.class)
	public static Encoreable encoreable;
}
