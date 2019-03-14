package spittr.config.datasource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

//Document标注的类可以借助MongoTemplate或自动生成的Repository进行持久化
@Document
public class Order_Mongo {
	
	@Autowired
	//将MongoTemplate注入使用
	private MongoOperations mongo;
	

	@Id
	//@Id注解表示文档的ID
	private String id;
	
	@Field("xxx")
	//@Field注解可以将customer映射到文档中的xxx域
	//如果没有使用注解指明的话那么字段名与文档中的域相同
	private String customer;
	
	private String type;
	
	//NoSql中的集合不会单独存放，而是直接在该文档中保存的 也是以一个集合的形式存在该类对应的文档中
	private List<Object> item;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Object> getItem() {
		return item;
	}

	public void setItem(List<Object> item) {
		this.item = item;
	}
}
