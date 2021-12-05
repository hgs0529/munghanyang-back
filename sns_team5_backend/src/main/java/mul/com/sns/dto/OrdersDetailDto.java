package mul.com.sns.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class OrdersDetailDto {

	private int seq;			// 결제한 것들 시퀀스 = 주문번호
	private int userid;			// 작성자 시퀀스
	private int productid;		// 상품 시퀀스
	private int orderid;		// 구매후확인 시퀀스
	
	private String selection;		// 결제방법
	private int quantity;			// 상품수량 		orders 테이블
	private int totalamount;				// 실제 결제가격 	orders 테이블
	private String orderdate;		// 결제된 날짜 	orders 테이블
	
	private String file;		// 상품 사진 product 테이블
	private String name;		// 상품 이름 product 테이블
	private String content;		// 상품 내용 product 테이블

	private int addressid;
	private String addressname;			// 배송지 - address- orders
	private String address;				// 상세주소 - address - orders
	private String receiveuser;			// 수령인 - address - orders
	private String receivephone;		// 수령인연락처 - address - orders
	
	private int reviewstatus;		// 후기 등록 상태 0, 1 썼는가 안썼는가
	private String thumbnail;
	
	
}
