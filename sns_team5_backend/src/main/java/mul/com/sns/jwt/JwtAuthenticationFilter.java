package mul.com.sns.jwt;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import mul.com.sns.CookieUtil;
import mul.com.sns.dao.UserDao;
import mul.com.sns.dto.UserDto;

@Log4j2
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	
	private final JwtUtil jwtUtil;
	private final UserDao userDao;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		log.info("**** Authentication Filter running ****");

		// 현재 URI 확인
		String reqPath = request.getRequestURI();
		log.info("URI: " + reqPath);
		
		// 제외할 URI들
		if(reqPath.contains("/user/login") || reqPath.contains("/user/join")) {
			log.info("**** Filter Canceled ****");
			filterChain.doFilter(request, response);
		       return;
		}
		
		// 헤더에서 토큰 취득
        String accessToken = jwtUtil.resolveAccessToken(request);
        String refreshToken = jwtUtil.resolveRefreshToken(request);

        // 액세스토큰 존재 확인
        if (accessToken != null) {
        	
            // 1. 액세스토큰이 유효 & 리프레시토큰이 유효한 경우
            if (jwtUtil.validateToken(accessToken) && jwtUtil.validateToken(refreshToken)) {
            	log.info("쿠키 다 있음: 액세스 유효 & 리프레시 유효");
            	UserDto user = userDao.findUserBySeq(jwtUtil.getUserSeq(accessToken));
            	this.setAuthentication(user);
            }
            // 2. 액세스토큰이 만료 & 리프레시토큰이 유효한 경우: 액세스토큰 재발급 & 컨텍스트에 넣어주기
            else if (jwtUtil.validateToken(refreshToken)) {
            	log.info("쿠키 다 있음: 액세스 만료 & 리프레시 유효");
                int seq = jwtUtil.getUserSeq(refreshToken);
                UserDto user = userDao.findUserBySeq(seq);
                
                String newAccessToken = jwtUtil.generateAccessToken(user);
                
                jwtUtil.setCookieAccessToken(response, newAccessToken);
                this.setAuthentication(user);
            }
            // 3. 액세스토큰이 유효 & 리프레시토큰이 만료된 경우: 리프레시토큰 업데이트
            else if (jwtUtil.validateToken(accessToken)) {
            	log.info("쿠키 다 있음: 액세스 유효 & 리프레시 만료");
            	UserDto user = userDao.findUserBySeq(jwtUtil.getUserSeq(accessToken));
            	String newRefreshToken = jwtUtil.generateRefreshToken(user);
            	
            	jwtUtil.setCookieRefreshToken(response, newRefreshToken);
            	userDao.updateRefreshToken(user);
            }
            else {
            	log.info("쿠키 다 있음: 액세스 만료 & 리프레시 만료");
            	CookieUtil cookieUtil = new CookieUtil();
            	cookieUtil.deleteCookie(response, "Authorization");
            	cookieUtil.deleteCookie(response, "RefreshToken");
            	this.setAnonymousAuthentication();
            }
        }
        // 4. 쿠키의 액세스토큰은 null이고 리프레시토큰은 있는 경우
        else if(refreshToken != null) {
        	log.info("액세스쿠키 없음 & 리프레시쿠키 있음");
        	if (jwtUtil.validateToken(refreshToken)) {
        		int seq = jwtUtil.getUserSeq(refreshToken);
                UserDto user = userDao.findUserBySeq(seq);
                
                String newAccessToken = jwtUtil.generateAccessToken(user);
                
                jwtUtil.setCookieAccessToken(response, newAccessToken);
                this.setAuthentication(user);
        	}else {
        		log.info("액세스쿠키 없음 & 리프레시쿠키 있는데 만료");
        		this.setAnonymousAuthentication();
        	}
        }
        else {
        	log.info("액세스쿠키 없음 & 리프레시쿠키 없음");
    		this.setAnonymousAuthentication();
    	}
        filterChain.doFilter(request, response);
    }
		
	// 시큐리티컨텍스트에 Authentication 객체 저장
    public void setAuthentication(UserDto user) {
        Authentication authentication = new JwtAuthentication(user);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        
        log.info("Authorities: " + SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        
    }
    
    public void setAnonymousAuthentication() {
    	Authentication authentication = new AnonymousAuthenticationToken("ANON", "anonymousUser", Collections.singletonList(new SimpleGrantedAuthority("ROLE_ANONYMOUS")));
    	SecurityContextHolder.getContext().setAuthentication(authentication);
        
        log.info("Authorities: " + SecurityContextHolder.getContext().getAuthentication().getAuthorities());
    }

}
