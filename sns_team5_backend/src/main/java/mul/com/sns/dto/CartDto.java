package mul.com.sns.dto;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@Component
@AllArgsConstructor
@ToString
public class CartDto {

	private int seq;				// 장바구니 시퀀스
	private int userid;				// 작성자 시퀀스
	private int productid;			// 상품 시퀀스
	
	private int quantity;			// 상품의 수량
	private int status;				// 구입체크 on off
	
	private String options;		// 상품 내 선택옵션? 

	private int del;				// 상품품절여부 product table
	private int price;				// 상품가격 product table
	private int discount;
	private String name;			// 상품이름 product table
	private String file;			// 상품사진 product table	
	
	private int sumprice;			// 각 항목의 (가격*수량) 값
	
	public CartDto() {
		
	}

	

	
	
}
