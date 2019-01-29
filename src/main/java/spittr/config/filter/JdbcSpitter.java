package spittr.config.filter;

import javax.inject.Inject;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcSpitter implements JdbcSpitterRepository{

	private JdbcOperations jdbcOperations;
	
	
	//@Inject注解可以在JdbcSpitter创建的时候注入一个jdbcOperations对象
	@Inject
	public JdbcSpitter(JdbcOperations jdbcOperations) {
		this.jdbcOperations = jdbcOperations;
	}
	
}
