package mul.com.sns.dto;


import org.springframework.stereotype.Component;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Component // 이렇게 추가해줘야 객체로 스프링에 등록됨
public class UserDto{

	int seq;
	
	String email;
	String pwd;
	
	String nickname;
	
	String created_at;
	String avatar;
	
	int auth;
	
	boolean sns_only;
	
	String token;
}
