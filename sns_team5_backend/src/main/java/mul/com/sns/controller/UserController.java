package mul.com.sns.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j2;
import mul.com.sns.CookieUtil;
import mul.com.sns.dto.UserDto;
import mul.com.sns.jwt.JwtUtil;
import mul.com.sns.service.UserService;

@Log4j2
@RestController
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private UserService service;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private CookieUtil cookieUtil;
	
	@GetMapping(value = "/") 
	public UserDto findUserByEmail(int userid) {
		log.info("***** GET user (findUserByEmail) *****");
		
		// 유저 한명의 정보
		UserDto user = service.findUserBySeq(userid);
		return user;
	}
	
	@GetMapping(value="/refresh")
	public UserDto refresh(HttpServletRequest req) {
		log.info("**** reload page ****");
		UserDto dto = null;
		String accessToken = jwtUtil.resolveAccessToken(req);
		if(accessToken != null) {
			int seq = jwtUtil.getUserSeq(accessToken);
			dto = service.findUserBySeq(seq);
			dto.setPwd("");
			System.out.println(dto.toString());
		}
		return dto;
	}
	
	@PostMapping(value = "/login")
	public String login(UserDto user, HttpServletRequest request, HttpServletResponse response) {
		log.info("***** POST user (login) *****");
		
		// 유저 존재 확인
        UserDto dto = service.findUserByEmail(user.getEmail());
        
        if(dto == null) {
        	return "존재하지 않는 계정입니다.";
        }else {
            boolean pwdCheck = service.pwdCheck(user, dto);
            if(!pwdCheck) {
            	return "아이디 또는 비밀번호가 일치하지 않습니다.";
            }
        }
        
        // 액세스토큰 생성
        String accessToken = jwtUtil.generateAccessToken(dto);
        
        // 리프레시토큰 생성 + DB에 저장
        String refreshToken = jwtUtil.generateRefreshToken(dto);
        if(jwtUtil.findRefreshToken(dto.getSeq()) != null) {
        	dto.setToken(refreshToken);
        	service.updateRefreshToken(dto);
        }else {
        	service.insertRefreshToken(dto);
        }
        
        //쿠키설정
        jwtUtil.setCookieAccessToken(response, accessToken);
        jwtUtil.setCookieRefreshToken(response, refreshToken);
        
        return "로그인 되었습니다.";
	}
	
	@PostMapping(value = "/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		log.info("***** POST user (logout) *****");
		
		String accessToken = jwtUtil.resolveAccessToken(request);
		int userSeq = jwtUtil.getUserSeq(accessToken);
		UserDto user = service.findUserBySeq(userSeq);
		service.updateRefreshToken(user);
		
		// 쿠키 정보 삭제
		cookieUtil.deleteCookie(response, "Authorization");
		cookieUtil.deleteCookie(response, "RefreshToken");
		
		return "로그아웃 되었습니다.";
	}
	
	@PostMapping(value = "/join")
	public String join(UserDto user) {
		log.info("***** POST user (join) *****");
		
		service.join(user);
		UserDto result = service.findUserByEmail(user.getEmail());
		
		return result != null ?
				"환영합니다! 성공적으로 가입되었습니다." :
				"유효하지 않은 접근입니다. 다시 시도해주세요.";
	}
	
	@GetMapping(value="/join/emailcheck")
	public String emailCheck(String email) {
		log.info("***** GET user (join/emailcheck) *****");
		
		// 이메일 중복여부 확인
		UserDto dto = service.emailCheck(email);
		
		if(dto != null ) {
			if(dto.isSns_only()) {
				return "SNS 계정으로 가입한 이메일입니다.";
			}else {
				return "이미 가입한 이메일입니다.";
			}
		}
		else {
			return "사용 가능한 이메일입니다.";
		}
	}
	
	@GetMapping(value="/join/nicknamecheck")
	public String nicknameCheck(String nickname) {
		log.info("***** POST user (join/nicknamecheck) *****");
		
		// 닉네임 중복여부 확인
		UserDto dto = service.nicknameCheck(nickname);
		
		if(dto != null ) {
			return "이미 사용중인 이름입니다.";
		}
		else {
			return "사용 가능한 이름입니다.";
		}
	}
}
