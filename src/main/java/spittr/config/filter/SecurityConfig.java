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
//该注解本身没有什么具体作用
@EnableWebSecurity
//Spring Security最关键的还是要实现WebSecurityConfigurer 或者一个实现该接口的子类
//一个Spring security的基本配置
//配置用户存储(重载configure(AuthenticationManagerBuilder auth))
//指定哪些请求需要认证， 哪些请求不需要认证，以及所需的权限(重载 configure(HttpSecurity http))
//指定一个自定义登录页，来代替原来的默认登录页
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private DataSource dataSource;

	@Override
	//重载， 配置user-detail服务
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		//这种入参方式既可以满足多参数封装 又不会导致入参数量太多将参数顺序搞混
		//inMemoryAuthentication()该方法用来启用用户内存
		//这个方法的功能是启用内存并往里添加2个用户 user以及admin 还定义了这2个用户的密码权限等
		//该内存方式适用于开发及测试 如果是正式生产环境最好还是利用数据库保存
//		auth.inMemoryAuthentication()
//			.withUser("user").password("password").roles("USER").and()
//			.withUser("admin").password("password").roles("USER","ADMIN");
		
		//可以以这样的方式让Spring Security使用数据库为用户存储
		//使用jdbc自定义sql的时候，必须要遵循一点规则：所有的查询用户名都是唯一参数
		auth.jdbcAuthentication().dataSource(dataSource)
			//认证查询：单条用户信息
			.usersByUsernameQuery("select username, password, role from sys_user")
			//权限查询：0条或多条该用户对应的权限信息数据
			.authoritiesByUsernameQuery("select username, password, role form sys_user  where username = ?")
			//群组权限查询：可以查询对应群组下的 权限
			.groupAuthoritiesByUsername("select * from sys_user where username = ?")
			//密码转码器
			.passwordEncoder(new StandardPasswordEncoder(""));
		
		//LDAP是一个数据库，但是又不是一个数据库。说他是数据库，因为他是一个数据存储的东西。但是说他不是数据库，是因为他的作用没有数据库这么强大，而是一个目录。
		//为了理解，给一个例子就是电话簿（黄页）
		auth.ldapAuthentication()
			//为基础查询提供条件 
			//声明用户在名为people的组织单元下查询
			.userSearchBase("ou=people")
			//用户条件
			.userSearchFilter("uid={0}")
			//声明组在名为groups的组织单元下查询
			.groupSearchBase("ou=groups")
			//组条件
			.groupSearchFilter("member={0}")
			
			//encoder是一种密码转化器
			//attribute是将密码与制定属性对比，默认是userpassword
			.passwordCompare()
			.passwordEncoder(new Md5PasswordEncoder())
			.passwordAttribute("passcode");	
	}
	
	@Override
	//重载， 配置如何通过拦截器保护请求
	protected void configure(HttpSecurity http) throws Exception {
		//这个是http拦截的默认配置
		//通过调用authorizeRequests() anyRequest() authenticated()来要求所有进入应用的http请求都要进行验证
		//formLogin() 用来配置支持表单登录
		//httpBasic() 用来配置支持HTTP Basic认证
		http.authorizeRequests().anyRequest().authenticated().and().formLogin().and().httpBasic();
		
		http.authorizeRequests()
			//定义spitters/me需要进行权限认证
			//也可以使用通配符**
			.antMatchers("/spitters/me").authenticated()
			//更具体的规定post类型的spitters请求需要认证
			.antMatchers(HttpMethod.POST, "/spittles").authenticated()
			//anyRequest().permitAll()表示其余的请求都不需要认证
			.anyRequest().permitAll();
	}
	
	@Override
	//重载，配置Spring Security 的Filter链
	public void configure(WebSecurity web) {
		
	}
}
