package mul.com.sns.dao;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import mul.com.sns.dto.CartDto;
import mul.com.sns.dto.ProductOptionDto;

@Mapper
@Repository
public interface CartDao extends Serializable {

		//한 유저의 장바구니 모두 불러오기
		List<CartDto> getCart(int userid);
		
		//한 유저 장바구니의 체크된 물품의 (가격*수량)값 
		CartDto getSum(int userid);
		
		//장바구니에 상품 갯수 카운트
		int countCart(int userid);
		
		//카트에 추가
		int addCart(CartDto cd);
		
		//카트 물품 수량 수정
		int updateCart(CartDto cd);
		
		//모두 체크 언체크
		int allCheckCart(int userid);
		int allUncheckCart(int userid);
		
		//특정 체크 언체크
		int checkCart(CartDto cd);
		int uncheckCart(CartDto cd);
		
		// 버튼 삭제, 체크된것 삭제, 모두 삭제
		int btnDelCart(CartDto cd);
		int checkDelCart(CartDto cd);
		int allDelCart(int userid);
			

		CartDto getimmediatelyPurchase(CartDto dto);
		
		ProductOptionDto getProductOption(int seq);
		int UpdateQuantity(CartDto dto);

		List<CartDto> getCheck(int userid);
		
		int AfterDelCart(CartDto dto);

}
