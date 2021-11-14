package mul.com.sns.jwt;

import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import mul.com.sns.dao.UserDao;
import mul.com.sns.dto.UserDto;

@Log4j2
@Getter
@Component
public class JwtAuthentication implements Authentication {

	private static final long serialVersionUID = -532271639300705155L;

	@Autowired
	UserDao userDao;
	
	private String token;
	private int seq;
	private int auth;
	private boolean isAuthenticated;
	
	public JwtAuthentication(UserDto userDto) {
			this.token = userDto.getToken();
			this.seq = userDto.getSeq();
			this.auth = userDto.getAuth();
			this.isAuthenticated = true;
	}

	@Override
	public String getName() {
		return Integer.toString(this.seq);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		log.debug("Getting Authorities...");
		if (this.auth == 3) {
			this.isAuthenticated = true;
			return Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN"));
		} else {
			this.isAuthenticated = true;
			return Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
		}
	}

	@Override
	public Object getCredentials() {
		if(this.seq != 0) return this.seq;
		return null;
	}

	@Override
	public Object getDetails() {
		if(this.seq != 0) return this.token;
		return null;
	}

	@Override
	public Object getPrincipal() {
		if(this.seq != 0) {
			UserDetails user = new User(Integer.toString(this.seq), "", this.getAuthorities());
			return user;
		}
		return null;
	}

	@Override
	public boolean isAuthenticated() {
		return this.isAuthenticated;
	}

	@Override
	public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
		this.isAuthenticated = isAuthenticated;

	}

}
