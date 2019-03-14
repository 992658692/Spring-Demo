package spittr.config.ctrl;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class User {

	//想要在项目中启用JSR303注解必须要开启<mvc:annotation-driven />
	//@notNull注解表示如果用User类来接受参数的话那么userName参数不能为空且长度要在5-10之间
	@NotNull(message="名字不能为空")
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
