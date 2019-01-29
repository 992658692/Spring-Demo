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

//@RestControllerʹ�ø�ע�����Controller����ʡ��ÿ�������е�@ResponseBody ��@@RequestBody ע��
//����Ϊ������Ӧ����Ϣת������
@RestController
//@ControllerAdvice��ע�����(@ExceptionHandler,@InitBinder,@ModelAttribute)
//��ע�������õ���������������Щ��ע������õ�����Ӧ�ó��������Ƶ�@RequestMapping
//Ҳ����˵�ڸ�ע�����ڴ���@ExceptionHandler��ô����@RequestMapping�µ��쳣����ͳһ��@ExceptionHandler��
@ControllerAdvice
public class HomeController {

	//@Validע���ʾ����User�� notNull size У��
	@RequestMapping("xxx")
	public String home (@Valid User user) {
		System.out.println(user.getUserName());
		return "home";
	}
	
	//{}�����ű�������
	//ʵ�����������������·��ֻ��Ҫlocalhost:8080/xx�Ϳ����������������еĲ����������������
	//������localhost:8080/xx/123456
	//��ô123456����spittleId��ֵ ����������������������ֵ��ss
	//ע��@PathVariable���ǻ�ȡ������ռλ��������Ĳ��������@PathVariableע��û��value������ô
	//��Ĭ����β�������ռλ����������ͬ
	//produces ���Ա���������ֻ����Accpetͷ����Ϣ�б�������Ϊjson����������URL��������POST
	//��������������Ͳ���json��ô�Ͳ�����뵽�÷����У�����ֱ�ӷ���406��Ӧ
	//����һ��consumes���� ����produces�������� ֻ�ǲ�ѯ��������ͷ��Content-Type��Ϣ
	@RequestMapping(value="xx/{spittleId}", produces="application/json", method = RequestMethod.POST)
	
	@ResponseBody
	//@ResponseBody �����˽�������뵽ģ����Ȼ����Ⱦ����ͼ�Ĺ���
	//����ֱ�ӽ�������Դ���͸��ͻ��ˣ���ת���ɿͻ��˿ɽ��ܵı�����ʽ������ͻ�����Ҫ���ֱ�����ʽ��DispatcherServlet����������Accpetͷ��ȥ�ҵ���Ӧ��Ϣ
	//@RequestBody ��ע������Է��ʷ��������󣬲�ѯ�����е�Content-Type ͷ���������ҵ��ܽ�������ת��ΪSpitter�������͵���Ϣת����
	public String home (@PathVariable("spittleId") String ss, @RequestBody Spitter s) {
		return "home";
	}
	
	
	@RequestMapping("/multipart")
	//ͨ��@RequestPartע����ָ����Ƭ������byte[]�������
	//����ʹ��spring�ṩ��multipartFile�ӿ�������byte���������Ƭ��
	//�����Ը�ȫ��Ļ�ȡ��Ƭ����Ϣ�����
	
	//part�ӿ���multipartFile�ӿڵĹ�����ʵ������ͬ
	//ֻ�����Ҫʹ��multipartFile�ӿ�Ҫ�ڿ������м���multipartResolver ����part�Ͳ���Ҫ
	public String processRegistration (@RequestPart("profilePicture") byte[] profilePicture,
									   @RequestPart("file")MultipartFile file, @RequestPart("part")Part part) 
									   throws IllegalStateException, IOException {
		
		//����multipartFile�ӿڿ��Ժܷ���Ľ�ǰ�˵���Ƭ�����ڱ�����ʱ�ļ���
		file.transferTo(new File("/src/main" + file.getOriginalFilename()));
		return "";
	}
	
	//�쳣����
	@ExceptionHandler(Exception.class)
	public String test (RedirectAttributes model) {
		User u = new User();
		//ͨ��addFlashAttribute�����������ض�����ת������int string֮��Ķ������
		model.addFlashAttribute("user", u);
		return "error/xxx";
	}
}
