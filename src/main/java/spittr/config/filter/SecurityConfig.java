package spittr.config.filter;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

@Configuration
//��ע�Ȿ��û��ʲô��������
@EnableWebSecurity
//Spring Security��ؼ��Ļ���Ҫʵ��WebSecurityConfigurer ����һ��ʵ�ָýӿڵ�����
//һ��Spring security�Ļ�������
//�����û��洢(����configure(AuthenticationManagerBuilder auth))
//ָ����Щ������Ҫ��֤�� ��Щ������Ҫ��֤���Լ������Ȩ��(���� configure(HttpSecurity http))
//ָ��һ���Զ����¼ҳ��������ԭ����Ĭ�ϵ�¼ҳ
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private DataSource dataSource;

	@Override
	//���أ� ����user-detail����
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		//������η�ʽ�ȿ�������������װ �ֲ��ᵼ���������̫�ཫ����˳����
		//inMemoryAuthentication()�÷������������û��ڴ�
		//��������Ĺ����������ڴ沢�������2���û� user�Լ�admin ����������2���û�������Ȩ�޵�
		//���ڴ淽ʽ�����ڿ��������� �������ʽ����������û����������ݿⱣ��
//		auth.inMemoryAuthentication()
//			.withUser("user").password("password").roles("USER").and()
//			.withUser("admin").password("password").roles("USER","ADMIN");
		
		//�����������ķ�ʽ��Spring Securityʹ�����ݿ�Ϊ�û��洢
		//ʹ��jdbc�Զ���sql��ʱ�򣬱���Ҫ��ѭһ��������еĲ�ѯ�û�������Ψһ����
		auth.jdbcAuthentication().dataSource(dataSource)
			//��֤��ѯ�������û���Ϣ
			.usersByUsernameQuery("select username, password, role from sys_user")
			//Ȩ�޲�ѯ��0����������û���Ӧ��Ȩ����Ϣ����
			.authoritiesByUsernameQuery("select username, password, role form sys_user  where username = ?")
			//Ⱥ��Ȩ�޲�ѯ�����Բ�ѯ��ӦȺ���µ� Ȩ��
			.groupAuthoritiesByUsername("select * from sys_user where username = ?")
			//����ת����
			.passwordEncoder(new StandardPasswordEncoder(""));
		
		//LDAP��һ�����ݿ⣬�����ֲ���һ�����ݿ⡣˵�������ݿ⣬��Ϊ����һ�����ݴ洢�Ķ���������˵���������ݿ⣬����Ϊ��������û�����ݿ���ôǿ�󣬶���һ��Ŀ¼��
		//Ϊ����⣬��һ�����Ӿ��ǵ绰������ҳ��
		auth.ldapAuthentication()
			//Ϊ������ѯ�ṩ���� 
			//�����û�����Ϊpeople����֯��Ԫ�²�ѯ
			.userSearchBase("ou=people")
			//�û�����
			.userSearchFilter("uid={0}")
			//����������Ϊgroups����֯��Ԫ�²�ѯ
			.groupSearchBase("ou=groups")
			//������
			.groupSearchFilter("member={0}")
			
			//encoder��һ������ת����
			//attribute�ǽ��������ƶ����ԶԱȣ�Ĭ����userpassword
			.passwordCompare()
			.passwordEncoder(new Md5PasswordEncoder())
			.passwordAttribute("passcode");	
	}
	
	@Override
	//���أ� �������ͨ����������������
	protected void configure(HttpSecurity http) throws Exception {
		//�����http���ص�Ĭ������
		//ͨ������authorizeRequests() anyRequest() authenticated()��Ҫ�����н���Ӧ�õ�http����Ҫ������֤
		//formLogin() ��������֧�ֱ���¼
		//httpBasic() ��������֧��HTTP Basic��֤
		http.authorizeRequests().anyRequest().authenticated().and().formLogin().and().httpBasic();
		
		http.authorizeRequests()
			//����spitters/me��Ҫ����Ȩ����֤
			//Ҳ����ʹ��ͨ���**
			.antMatchers("/spitters/me").authenticated()
			//������Ĺ涨post���͵�spitters������Ҫ��֤
			.antMatchers(HttpMethod.POST, "/spittles").authenticated()
			//anyRequest().permitAll()��ʾ��������󶼲���Ҫ��֤
			.anyRequest().permitAll();
	}
	
	@Override
	//���أ�����Spring Security ��Filter��
	public void configure(WebSecurity web) {
		
	}
}
