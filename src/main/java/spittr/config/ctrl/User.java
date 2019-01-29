package spittr.config.ctrl;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class User {

	//��Ҫ����Ŀ������JSR303ע�����Ҫ����<mvc:annotation-driven />
	//@notNullע���ʾ�����User�������ܲ����Ļ���ôuserName��������Ϊ���ҳ���Ҫ��5-10֮��
	@NotNull(message="���ֲ���Ϊ��")
	@Size(min=5, max=10)
	private String userName;
	
	private String password;
	
	public String getUserName () {
		return this.userName;
	}
	
	public User () {
		System.out.println("user");
	}
}
