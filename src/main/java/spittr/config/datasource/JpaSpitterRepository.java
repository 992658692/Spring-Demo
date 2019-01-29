package spittr.config.datasource;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class JpaSpitterRepository implements SpitterRepository{

	//��ע�Ⲣ����������ע��һ�����󣬶���ʹ��һ��EntityManager�Ĵ���
	//������EntityManager���뵱ǰ�������һ���
	//���ڸ�ע�Ⲣ����spring�Դ��� ������ʹ�ø�ע�����Ҫ���spirng��ɨ��
	@PersistenceContext
	private EntityManager em;//ע��EntityManager
	
	
	//ʹ��EntityManager
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
