package mul.com.sns.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mul.com.sns.dao.OrdersDetailDao;
import mul.com.sns.dto.OrdersDetailDto;

@Service
@Transactional
public class OrdersDetailService {

	@Autowired
	OrdersDetailDao dao;
	
	public List<OrdersDetailDto> getOrdersDetail(int orderid){
		return dao.getOrdersDetail(orderid);
	}
	
	public OrdersDetailDto getOrdersDetailOne(int seq) {
		return dao.getOrdersDetailOne(seq);
	}
	
	public boolean addOrdersDetail(OrdersDetailDto oddto){		
		int n = dao.addOrdersDetail(oddto);
		
		return n>0?true:false;
	}
	
	public boolean updateOrdersDetail(OrdersDetailDto oddto){
		int n = dao.updateOrdersDetail(oddto);
		
		return n>0?true:false;
	}
	public boolean deleteOrdersDetail(OrdersDetailDto oddto){
		int n = dao.deleteOrdersDetail(oddto);
		
		return n>0?true:false;
	}
}
