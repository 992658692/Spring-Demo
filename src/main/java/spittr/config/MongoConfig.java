package spittr.config;

import java.util.Arrays;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

/**
 * ����Ĺ�����MongoDBConfig������ͬ
 * �������ʽ�Ĵ���MongoTemplate
 * 
 *
 */
@Configuration
@EnableMongoRepositories("spittr.config.datasource")
public class MongoConfig extends AbstractMongoConfiguration{

	@Override
	//�������ݿ���
	protected String getDatabaseName() {
		return "OrderDB";
	}

	@Override
	//�������ݿ�����
	public Mongo mongo() throws Exception {
		MongoCredential mongoCred = MongoCredential.createCredential("username", "OrderDB", "password".toCharArray());
		return new MongoClient(new ServerAddress("localhost", 27017),Arrays.asList(mongoCred));
	}

}
