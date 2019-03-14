package spittr.config;

import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

import spittr.config.ctrl.User;

@Configuration
@ComponentScan(basePackages={"spittr.config.ctrl"}, excludeFilters={@Filter(type=FilterType.ANNOTATION)})
//扫描dao接口
@MapperScan("org.jade.dao")
//扫描对应的properties 然后通过注入Environment类调用
@PropertySource("classpath:application.properties")
public class RootConfig {
	
	@Autowired
    private Environment env;
	
	@Bean
	public User user () {
		System.out.println("rootConfig");
		return new User();
	}
	
	//其实如果不是ws  单纯的mvc的话其实不需要手动配置数据库连接 
	//数据库连接配置开始
	@Bean
	public DataSource getDataSource () {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
		dataSource.setUrl(env.getProperty("spring.datasource.url"));
		dataSource.setUsername(env.getProperty("spring.datasource.username"));
		dataSource.setPassword(env.getProperty("spring.datasource.password"));
		return dataSource;
	}
	
	@Bean
	public DataSourceTransactionManager transactionManager() {
		return new DataSourceTransactionManager(getDataSource());
	}
	
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(getDataSource());
		return sessionFactory.getObject();
	}
	//数据库连接配置结束

	//处理请求中的图片
	@Bean
	public MultipartResolver multipartResolverSta () {
		return new StandardServletMultipartResolver();
	}
	
	//Servlet容器非3.0及以上版本时
	//可以选择替代的方式来处理照片
	@Bean
	public MultipartResolver multipartResolverCom () throws IOException {
		CommonsMultipartResolver multipart = new CommonsMultipartResolver();
		multipart.setUploadTempDir(new FileSystemResource("/src/main"));
		multipart.setMaxInMemorySize(0);//任何照片都存入临时
		multipart.setMaxUploadSize(2097152);
		return multipart;
	}
	
	@Bean
	public JdbcTemplate jdbcTemplate() {
		return new JdbcTemplate(dataSources());
	}
	
	//使用JNDI配置数据库的数据源
	@Bean
	public JndiObjectFactoryBean dataSource() {
		JndiObjectFactoryBean jndi = new JndiObjectFactoryBean();
		//配置jndi的资源名称
		jndi.setJndiName("jdbc/spittr");
		//该参数设置为true之后，jdni会自动将jndiName扩展成java:comp/env/jdbc/spittr
		jndi.setResourceRef(true);
		return jndi;
	}
	
	//profile可以指定多个不同的数据源用于不同的环境使用
	//具体要唤醒哪个profile则可以通过2种方式激活 web.xml和ClassPathXmlApplicationContext
	//<context-param>
	//<param-name>spring.profiles.active</param-name>
	//<param-value>dev,production</param-value>
	//</context-param>
	
	//ClassPathXmlApplicationContext.setActiveProfiles();
	@Profile("xxx")
	@Bean
	public BasicDataSource dataSources() {
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		ds.setUrl("jdbc:mysql://111.231.58.67:3306/test?useUnicode=true&characterEncoding=utf8");
		ds.setUsername("root");
		ds.setPassword("root");
		ds.setInitialSize(5);//初始化连接数
		
		//Spring 还支持其余的数据源配置类，但是他们并不支持连接池功能，对于生产环境并不适用
		//该配置在每个请求连接时都会新建一个连接，对于DriverManagerDataSource配置来说并没有一个池子的概念
		DriverManagerDataSource dmd = new DriverManagerDataSource();
		
		//该类与DriverManagerDataSource类似，但是它直接使用的是JDBC的驱动
		SimpleDriverDataSource sdds = new SimpleDriverDataSource();
		
		//该类在每个请求时都会返回同一个连接，可以看作是只有一个连接的池子
		SingleConnectionDataSource scds = new SingleConnectionDataSource();
		return ds;
	}
	
	//配置hibernate
	@Bean
	public LocalSessionFactoryBean sessionFatory() {
		LocalSessionFactoryBean sfb = new LocalSessionFactoryBean();
		//配置数据源
		sfb.setDataSource(getDataSource());
		//配置hibernate的持久化策略 
		sfb.setMappingResources(new String[]{"spring.xmls"});
		Properties props = new Properties();
		//这里配置操作细节，使用了H2数据库并且按照H2Dialect来构建sql
		props.setProperty("dialect", "org.hibernate.dialect.H2Dialect");
		sfb.setHibernateProperties(props);
		//
		sfb.setAnnotatedClasses(new Class<?>[]{});
		return sfb;
	}
	
	
	//容器管理类型的JPA
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerBean(DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
		LocalContainerEntityManagerFactoryBean emfb = new LocalContainerEntityManagerFactoryBean();
		emfb.setDataSource(dataSource);
		//jpaVendorAdapter属性指定了使用哪个厂商的JPA实现(EclipseLinkJpaVendorAdapter HibernamteJpaVendorAdapter OpenJpaVendorAdapter)
		emfb.setJpaVendorAdapter(jpaVendorAdapter);
		emfb.setPackagesToScan("spittr.config");//扫描对应包下的文件带有@Entity注解的类
		return emfb;
	}
	
	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
//		adapter.setDatabase("MYSQL");
		adapter.setShowSql(true);
		adapter.setGenerateDdl(false);
		adapter.setDatabasePlatform("org.hibernate.dialect.HSQLDialect");
		
		return adapter;
	}
	
	
}
