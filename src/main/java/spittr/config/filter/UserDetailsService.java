package spittr.config.filter;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * ʹ�÷ǹ�ϵ�����ݿ�ʱ�������Զ����û�����ӿ�
 * ֻ��ѽʵ�ָýӿڣ����ش�������û���userDetails���󼴿�
 *
 */
public interface UserDetailsService {

	UserDetails loadUserByUsername (String username);
}
