package spittr.config.filter;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * 使用非关系型数据库时，可以自定义用户服务接口
 * 只需呀实现该接口，返回代表给定用户的userDetails对象即可
 *
 */
public interface UserDetailsService {

	UserDetails loadUserByUsername (String username);
}
