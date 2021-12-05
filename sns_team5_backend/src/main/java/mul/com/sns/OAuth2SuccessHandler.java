package mul.com.sns;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.RedirectView;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.var;
import lombok.extern.log4j.Log4j2;
import mul.com.sns.dto.UserDto;
import mul.com.sns.jwt.JwtUtil;
import mul.com.sns.service.UserService;

@Log4j2
@NoArgsConstructor
@AllArgsConstructor
@Component
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private UserService service;
	
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
		var attributes = oAuth2User.getAttributes();
		UserDto user = UserDto.builder()
						  .email((String) attributes.get("email"))
						  .nickname((String) attributes.get("name"))
						  .avatar((String) attributes.get("picture"))
						  .build();
		
		if(service.findUserByEmail(user.getEmail()) == null) {
			service.oauthjoin(user);
		}
		
		UserDto dto = service.findUserByEmail(user.getEmail());
		String accessToken = jwtUtil.generateAccessToken(dto);
		String refreshToken = jwtUtil.generateRefreshToken(dto);
		log.info("**** generate OAuth2 JWT ****");
		
		jwtUtil.setCookieAccessToken(response, accessToken);
		jwtUtil.setCookieRefreshToken(response, refreshToken);
		
		response.sendRedirect("http://localhost:8090/sns_team5_frontend/views/sns/main.html");
	}
}
