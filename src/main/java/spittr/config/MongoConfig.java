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
 * 该类的功能与MongoDBConfig功能相同
 * 该类会隐式的创建MongoTemplate
 * 
 *
 */
@Configuration
@EnableMongoRepositories("spittr.config.datasource")
public class MongoConfig extends AbstractMongoConfiguration{

	@Override
	//返回数据库名
	protected String getDatabaseName() {
		return "OrderDB";
	}

	@Override
	//返回数据库连接
	public Mongo mongo() throws Exception {
		MongoCredential mongoCred = MongoCredential.createCredential("username", "OrderDB", "password".toCharArray());
		return new MongoClient(new ServerAddress("localhost", 27017),Arrays.asList(mongoCred));
	}

}
