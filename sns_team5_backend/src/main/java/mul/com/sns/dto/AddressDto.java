package mul.com.sns.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AddressDto {

	private int seq;				// 주소 시퀀스
	private int userid;				// 작성자 시퀀스
	private int orderid;			// 결제완료 시퀀스

	private String addressname;		// 주소 제목 
	private String address;			// 주소 
	private String receiveuser;		// 수령인
	private String receivephone;	// 수령인 연락처
	private String ordermessage;	// 배송 메세지
	
	private String realname;	// 보내는 사람 user table
	private String phonenum;	// 보내는 사람의 연락처 user table
	private boolean def;
	



}
