package spittr.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoFactoryBean;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.Mongo;

@Configuration
//ɨ��ð��´���repository��mongodb��
@EnableMongoRepositories(basePackages={"spittr.config.datasource"})
public class MongoDBConfig {

	@SuppressWarnings("deprecation")
	@Bean
	//�������ݿ�����
	//�������ʹ��MongoClientȥ�������ӵĻ���Ȼ��Ҫ�����Ӧ�׳����쳣
	//����MongoFactoryBean��һ�������������ǽ�����������������������Ҫ�����쳣
	public MongoFactoryBean mongo() {
		MongoFactoryBean mongo = new MongoFactoryBean();
		mongo.setHost("localhost");
		return mongo;
	}
	
	@Bean
	//���Mongodb��ģ�漴ʹ��������Ŀ��û��ʹ�ã�Ҳ����Ҫ����һ�����Ķ���
	//��Ϊrepositoty�Զ������ɵĵײ㹦�ܻ�ʹ�õ��� �������Ǳ���Ҫ������������
	public MongoOperations mongoTemplate(Mongo mongo) {
		//mongo�����Ӷ���OrderDB�����ݿ���
		return new MongoTemplate(mongo, "OrderDB");
	}
}
