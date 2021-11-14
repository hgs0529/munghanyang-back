package mul.com.sns.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import mul.com.sns.dto.UserDto;

@Mapper
@Repository
public interface UserDao {
	
	UserDto findUserBySeq(int seq);
	UserDto findUserByEmail(String email);
	
	UserDto login(UserDto user);
	
	String findRefreshToken(int id);
	void insertRefreshToken(UserDto userDto);
	void updateRefreshToken(UserDto userDto);

	UserDto emailCheck(String email);
	UserDto nicknameCheck(String nickname);
	void addUser(UserDto user);
	
	
}
