package mul.com.sns.dao;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import mul.com.sns.dto.OrdersDto;
import mul.com.sns.dto.UserDto;

@Mapper
@Repository
public interface OrdersDao extends Serializable {

	//불러오기
	List<OrdersDto> getOrders(int seq);
	
	//하나만 불러오기
	OrdersDto getOrdersOne(int seq);
	
	//구매확인 추가
	int addOrders(OrdersDto od);
	
	//구매확인 수정
	int updateOrders(OrdersDto od);
	
	//구매확인 삭제
	int deleteOrders(OrdersDto od);
   /* 포인트적립 */
   void UpdateMungpoint(UserDto dto);
   
   int recentlyAddSeq(OrdersDto od);
}
