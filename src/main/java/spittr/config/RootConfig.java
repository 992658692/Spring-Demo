package spittr.config;

import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
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
//ɨ��dao�ӿ�
@MapperScan("org.jade.dao")
//ɨ���Ӧ��properties Ȼ��ͨ��ע��Environment�����
@PropertySource("classpath:application.properties")
public class RootConfig {
	
	@Autowired
    private Environment env;
	
	@Bean
	public User user () {
		System.out.println("rootConfig");
		return new User();
	}
	
	//��ʵ�������ws  ������mvc�Ļ���ʵ����Ҫ�ֶ��������ݿ����� 
	//���ݿ��������ÿ�ʼ
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
	//���ݿ��������ý���

	//���������е�ͼƬ
	@Bean
	public MultipartResolver multipartResolverSta () {
		return new StandardServletMultipartResolver();
	}
	
	//Servlet������3.0�����ϰ汾ʱ
	//����ѡ������ķ�ʽ��������Ƭ
	@Bean
	public MultipartResolver multipartResolverCom () throws IOException {
		CommonsMultipartResolver multipart = new CommonsMultipartResolver();
		multipart.setUploadTempDir(new FileSystemResource("/src/main"));
		multipart.setMaxInMemorySize(0);//�κ���Ƭ��������ʱ
		multipart.setMaxUploadSize(2097152);
		return multipart;
	}
	
	@Bean
	public JdbcTemplate jdbcTemplate() {
		return new JdbcTemplate(dataSources());
	}
	
	//ʹ��JNDI�������ݿ������Դ
	@Bean
	public JndiObjectFactoryBean dataSource() {
		JndiObjectFactoryBean jndi = new JndiObjectFactoryBean();
		//����jndi����Դ����
		jndi.setJndiName("jdbc/spittr");
		//�ò�������Ϊtrue֮��jdni���Զ���jndiName��չ��java:comp/env/jdbc/spittr
		jndi.setResourceRef(true);
		return jndi;
	}
	
	//profile����ָ�������ͬ������Դ���ڲ�ͬ�Ļ���ʹ��
	//����Ҫ�����ĸ�profile�����ͨ��2�ַ�ʽ���� web.xml��ClassPathXmlApplicationContext
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
		ds.setInitialSize(5);//��ʼ��������
		
		//Spring ��֧�����������Դ�����࣬�������ǲ���֧�����ӳع��ܣ���������������������
		//��������ÿ����������ʱ�����½�һ�����ӣ�����DriverManagerDataSource������˵��û��һ�����ӵĸ���
		DriverManagerDataSource dmd = new DriverManagerDataSource();
		
		//������DriverManagerDataSource���ƣ�������ֱ��ʹ�õ���JDBC������
		SimpleDriverDataSource sdds = new SimpleDriverDataSource();
		
		//������ÿ������ʱ���᷵��ͬһ�����ӣ����Կ�����ֻ��һ�����ӵĳ���
		SingleConnectionDataSource scds = new SingleConnectionDataSource();
		return ds;
	}
	
	//����hibernate
	@Bean
	public LocalSessionFactoryBean sessionFatory() {
		LocalSessionFactoryBean sfb = new LocalSessionFactoryBean();
		//��������Դ
		sfb.setDataSource(getDataSource());
		//����hibernate�ĳ־û����� 
		sfb.setMappingResources(new String[]{"spring.xmls"});
		Properties props = new Properties();
		//�������ò���ϸ�ڣ�ʹ����H2���ݿⲢ�Ұ���H2Dialect������sql
		props.setProperty("dialect", "org.hibernate.dialect.H2Dialect");
		sfb.setHibernateProperties(props);
		//
		sfb.setAnnotatedClasses(new Class<?>[]{});
		return sfb;
	}
	
	
	//�����������͵�JPA
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerBean(DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
		LocalContainerEntityManagerFactoryBean emfb = new LocalContainerEntityManagerFactoryBean();
		emfb.setDataSource(dataSource);
		//jpaVendorAdapter����ָ����ʹ���ĸ����̵�JPAʵ��(EclipseLinkJpaVendorAdapter HibernamteJpaVendorAdapter OpenJpaVendorAdapter)
		emfb.setJpaVendorAdapter(jpaVendorAdapter);
		emfb.setPackagesToScan("spittr.config");//ɨ���Ӧ���µ��ļ�����@Entityע�����
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
