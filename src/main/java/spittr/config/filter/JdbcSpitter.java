package spittr.config.filter;

import javax.inject.Inject;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcSpitter implements JdbcSpitterRepository{

	private JdbcOperations jdbcOperations;
	
	
	//@Injectע�������JdbcSpitter������ʱ��ע��һ��jdbcOperations����
	@Inject
	public JdbcSpitter(JdbcOperations jdbcOperations) {
		this.jdbcOperations = jdbcOperations;
	}
	
}
