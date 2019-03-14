package spittr.config.ctrl;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.Part;
import javax.validation.Valid;

import org.springframework.data.mongodb.repository.ExistsQuery;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;

import spittr.config.datasource.Spitter;

//@RestController使用该注解代替Controller可以省略每个方法中的@ResponseBody 和@@RequestBody 注解
//它会为控制器应用消息转换功能
@RestController
//@ControllerAdvice该注解包含(@ExceptionHandler,@InitBinder,@ModelAttribute)
//该注解所作用的类里所包含的那些子注解会作用到整个应用程序所控制的@RequestMapping
//也就是说在该注解类内创建@ExceptionHandler那么所有@RequestMapping下的异常都会统一到@ExceptionHandler下
@ControllerAdvice
public class HomeController {

	//@Valid注解表示启动User的 notNull size 校验
	@RequestMapping("xxx")
	public String home (@Valid User user) {
		System.out.println(user.getUserName());
		return "home";
	}
	
	//{}代表着变量部分
	//实际中想访问这个请求的路径只需要localhost:8080/xx就可以了至于中括号中的参数可以是任意参数
	//如请求localhost:8080/xx/123456
	//那么123456就是spittleId的值 它会随着请求带入进来并赋值给ss
	//注解@PathVariable就是获取请求中占位符所带入的参数，如果@PathVariable注解没有value属性那么
	//它默认入参参数名与占位符参数名相同
	//produces 属性表明该请求只接受Accpet头部信息中表述类型为json的请求，哪怕URL的请求是POST
	//但是如果表述类型不是json那么就不会进入到该方法中，或是直接返回406响应
	//还有一个consumes属性 它与produces属性类似 只是查询的是请求头中Content-Type信息
	@RequestMapping(value="xx/{spittleId}", produces="application/json", method = RequestMethod.POST)
	
	@ResponseBody
	//@ResponseBody 跳过了将对象放入到模型中然后渲染到视图的过程
	//而是直接将对象资源发送给客户端，并转换成客户端可接受的表述形式，具体客户端需要哪种表述形式，DispatcherServlet会在请求中Accpet头部去找到对应信息
	//@RequestBody 该注解是针对访问方法的请求，查询请求中的Content-Type 头部，并查找到能将请求体转换为Spitter参数类型的消息转换器
	public String home (@PathVariable("spittleId") String ss, @RequestBody Spitter s) {
		return "home";
	}
	
	
	@RequestMapping("/multipart")
	//通过@RequestPart注解来指定照片参数用byte[]数组接收
	//可以使用spring提供的multipartFile接口来代替byte数组接收照片。
	//它可以更全面的获取照片的信息并输出
	
	//part接口与multipartFile接口的功能其实大致相同
	//只是如果要使用multipartFile接口要在控制器中加入multipartResolver 但是part就不需要
	public String processRegistration (@RequestPart("profilePicture") byte[] profilePicture,
									   @RequestPart("file")MultipartFile file, @RequestPart("part")Part part) 
									   throws IllegalStateException, IOException {
		
		//采用multipartFile接口可以很方便的将前端的照片保存在本地临时文件中
		file.transferTo(new File("/src/main" + file.getOriginalFilename()));
		return "";
	}
	
	//异常处理
	@ExceptionHandler(Exception.class)
	public String test (RedirectAttributes model) {
		User u = new User();
		//通过addFlashAttribute方法可以在重定向中转发除了int string之外的对象参数
		model.addFlashAttribute("user", u);
		return "error/xxx";
	}
}
