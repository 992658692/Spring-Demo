package spittr.config.rest;

import org.springframework.context.annotation.Bean;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;

//由于这种解析器类型有不少缺陷，还是推荐使用Spring的消息转换器
public class ContentNegotiation {

	@Bean
	//当在webConfig中设置完解析器的默认类型之后
	//需要将默认的类型注入到该解析器中来
	public ViewResolver cnViewResolver(ContentNegotiationManager cnm) {
		//视图解析器
		//它会考虑到Accept头部信息并使用它所请求的媒体类型，但是它会首先查看URL的文件拓展名
		//如果URL在结尾处有文件拓展名的话，该解析器将会根据文件拓展名确定所需类型。
		//例如拓展名是.json的话，那么所需的内容类型必须是'application/json'
		//如果根据文件拓展名不能得到任何媒体类型的话，才会开始考虑Accept头部信息
		//最后如果连Accept头部信息也没有对应类型的话，该解析器会使用'/'作为默认内容类型
		//一旦内容类型确认后，它就将该逻辑视图名解析为渲染模式的View，但是它是交由其他的视图
		//解析器解析的，它本身不具备解析功能！
		
		//该解析器的优点就是能完全与控制层剥离开，使得相同的一套控制器，就能产生不同类型的内容
		//但是该解析器只能决定资源如何渲染到客户端，并不能涉及客户端发什么内容给控制器
		//而且只能渲染模型给客户端，而不是资源
		ContentNegotiatingViewResolver cnvr = new ContentNegotiatingViewResolver();
		cnvr.setContentNegotiationManager(cnm);
		return cnvr;
	}
}
