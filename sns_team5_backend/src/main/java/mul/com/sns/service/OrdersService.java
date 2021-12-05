package mul.com.sns.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mul.com.sns.dao.OrdersDao;
import mul.com.sns.dto.OrdersDto;
import mul.com.sns.dto.UserDto;

@Service
@Transactional
public class OrdersService {

	@Autowired
	OrdersDao dao;
	
	public List<OrdersDto> getOrders(int seq){
		return dao.getOrders(seq);
	}

	public OrdersDto getOrdersOne(int seq){
		return dao.getOrdersOne(seq);
	}
	
	public boolean addOrders(OrdersDto od){		
	int n = dao.addOrders(od);
		
		return n>0?true:false;
	}
	
	public boolean updateOrders(OrdersDto od){
	int n = dao.updateOrders(od);
		
		return n>0?true:false;
	}

	public boolean deleteOrders(OrdersDto od){
	int n = dao.deleteOrders(od);
		
		return n>0?true:false;
	}
	
	public void UpdateMungpoint(UserDto dto) {
	      
      dao.UpdateMungpoint(dto);
   }
	
	public int recentlyAddSeq(OrdersDto od) {
	      
      return dao.recentlyAddSeq(od);
   }
}
