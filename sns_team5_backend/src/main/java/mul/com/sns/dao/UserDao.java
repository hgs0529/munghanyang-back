package mul.com.sns.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import mul.com.sns.dto.AskDto;
import mul.com.sns.dto.UserDto;

@Mapper
@Repository
public interface UserDao {
	
	UserDto findUserBySeq(int seq);
	UserDto findUserByEmail(String email);
	
	UserDto login(UserDto user);
	void resetPwd(UserDto user);
	
	String findRefreshToken(int id);
	void insertRefreshToken(UserDto userDto);
	void updateRefreshToken(UserDto userDto);
	void deleteRefreshToken(int seq);

	UserDto emailCheck(String email);
	UserDto nicknameCheck(String nickname);
	void addUser(UserDto user);
	void addOauthUser(UserDto user);
	
	void updateUserProfile(UserDto user);
	void deleteUser(int seq);
	void addProductAsk(AskDto ask);
}
