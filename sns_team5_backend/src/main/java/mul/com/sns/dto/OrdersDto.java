package mul.com.sns.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrdersDto {

	private int seq;			// 결제 시퀀스 = 주문번호
	private int userid;			// 작성자 시퀀스
	private int addressid;		// 주소 시퀀스

	private int productamount;	// 상품의 총 금액
	private int deliveryamount;
	
	private int usemungpoint;	// 사용한 포인트
	private int savemungpoint;	// 저장되는 포인트
	private int totalamount;			// 실 결제 금액
	private int orderstatus;	// 주문진행상태
	private String ordermessage;		// 배송 메세지
	private String orderdate;			// 결제된 날짜

	private String orderername;
	private String ordereremail;
	private String ordererphone;
	
	private String nickname;
	private String receivephone;
	
	
	private String selectoption;		// 선택옵션
	private String addressname;			// 배송지 - address 테이블
	private String address;				// 상세주소 - address 테이블
	private String receiveuser;			// 수령인 - address 테이블

	
	private String ordername;
	private List<Integer> orderarray;
}
