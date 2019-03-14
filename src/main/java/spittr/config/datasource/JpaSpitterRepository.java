package spittr.config.datasource;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class JpaSpitterRepository implements SpitterRepository{

	//该注解并不会真正的注入一个对象，而是使用一个EntityManager的代理
	//真正的EntityManager是与当前事务绑定在一起的
	//由于该注解并不是spring自带的 所以想使用该注解必须要配合spirng的扫描
	@PersistenceContext
	private EntityManager em;//注入EntityManager
	
	
	//使用EntityManager
	public void addSpitter(Spitter spitter) {
		em.persist(spitter);
	}
	
	public Spitter getSpitterById (long id) {
		return em.find(Spitter.class, id);
	}
	
	public void saveSpitter(Spitter spitter) {
		em.merge(spitter);
	}
}
