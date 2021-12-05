package mul.com.sns.dao;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import mul.com.sns.dto.OrdersDetailDto;

@Mapper
@Repository
public interface OrdersDetailDao extends Serializable {

	//불러오기
	List<OrdersDetailDto> getOrdersDetail(int orderid);
	
	//하나만 불러오기
	OrdersDetailDto getOrdersDetailOne(int seq);
	
	//구매목록 추가
	int addOrdersDetail(OrdersDetailDto oddto);
	
	//구매목록 수정
	int updateOrdersDetail(OrdersDetailDto oddto);
	
	//구매목록 삭제
	int deleteOrdersDetail(OrdersDetailDto oddto);
}