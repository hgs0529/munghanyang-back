package mul.com.sns.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import mul.com.sns.dao.UserDao;
import mul.com.sns.dto.AskDto;
import mul.com.sns.dto.UserDto;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

	private final UserDao dao;
	private final PasswordEncoder pwdEncoder;
	
	public void addProductAsk(AskDto ask) {
	    dao.addProductAsk(ask);
	}
	
	public UserDto findUserBySeq(int seq) {
		UserDto dto =  dao.findUserBySeq(seq);
		return dto;
	}
	
	public void updateUserProfile(UserDto dto) {
	    dao.updateUserProfile(dto);
	}

	public void deleteUser(int seq) {
	    dao.deleteUser(seq);
	}
	
	public UserDto findUserByEmail(String email) {
		UserDto dto =  dao.findUserByEmail(email);
		return dto;
	}
	
	public UserDto emailCheck(String email) {
		return dao.emailCheck(email);
	}
	
	public UserDto nicknameCheck(String nickname) {
		return dao.nicknameCheck(nickname);
	}
	
	public boolean pwdCheck(UserDto user, UserDto dto) {
		return pwdEncoder.matches(user.getPwd(), dto.getPwd());
	}
	
	public void join(UserDto user) {
		
		// 들어온 비밀번호를 암호화
	    String inputPwd = user.getPwd();
	    String pwd = pwdEncoder.encode(inputPwd);
	    user.setPwd(pwd);
		
		dao.addUser(user);
	}
	
	public void oauthjoin(UserDto user) {
		dao.addOauthUser(user);
	}
	
	public void resetPwd(UserDto user) {
		String resetpwd = user.getPwd();
		String pwd = pwdEncoder.encode(resetpwd);
		user.setPwd(pwd);
		
		dao.resetPwd(user);
	}
	
	public void insertRefreshToken(UserDto userDto) {
		dao.insertRefreshToken(userDto);
	}
	
	public void updateRefreshToken(UserDto userDto) {
		dao.updateRefreshToken(userDto);
	}
	
	public void deleteRefreshToken(int seq) {
		dao.deleteRefreshToken(seq);
	}
}
