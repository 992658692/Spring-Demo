package spittr.config.datasource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

//Document��ע������Խ���MongoTemplate���Զ����ɵ�Repository���г־û�
@Document
public class Order_Mongo {
	
	@Autowired
	//��MongoTemplateע��ʹ��
	private MongoOperations mongo;
	

	@Id
	//@Idע���ʾ�ĵ���ID
	private String id;
	
	@Field("xxx")
	//@Fieldע����Խ�customerӳ�䵽�ĵ��е�xxx��
	//���û��ʹ��ע��ָ���Ļ���ô�ֶ������ĵ��е�����ͬ
	private String customer;
	
	private String type;
	
	//NoSql�еļ��ϲ��ᵥ����ţ�����ֱ���ڸ��ĵ��б���� Ҳ����һ�����ϵ���ʽ���ڸ����Ӧ���ĵ���
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
