package spittr.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoFactoryBean;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.Mongo;

@Configuration
//扫描该包下带有repository的mongodb类
@EnableMongoRepositories(basePackages={"spittr.config.datasource"})
public class MongoDBConfig {

	@SuppressWarnings("deprecation")
	@Bean
	//建立数据库连接
	//如果单纯使用MongoClient去创立连接的话必然还要处理对应抛出的异常
	//由于MongoFactoryBean是一个工厂所以我们将构建工作交给工厂并不需要管理异常
	public MongoFactoryBean mongo() {
		MongoFactoryBean mongo = new MongoFactoryBean();
		mongo.setHost("localhost");
		return mongo;
	}
	
	@Bean
	//这个Mongodb的模版即使我们在项目中没有使用，也必须要创建一个它的对象
	//因为repositoty自动化生成的底层功能会使用到它 所以我们必须要声明这样的类
	public MongoOperations mongoTemplate(Mongo mongo) {
		//mongo是连接对象，OrderDB是数据库名
		return new MongoTemplate(mongo, "OrderDB");
	}
}
