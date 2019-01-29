package spittr.config.filter;

import org.springframework.security.core.userdetails.UserDetails;

public class SpitterUserService implements UserDetailsService{
	
	private final SpitterReposiotry spitterReposiotry;
	
	public SpitterUserService(SpitterReposiotry spitterReposiotry) {
		this.spitterReposiotry = spitterReposiotry;
	}
	
	
	@Override
	public UserDetails loadUserByUsername(String username) {
		return spitterReposiotry.findByUsername();
	}

}
