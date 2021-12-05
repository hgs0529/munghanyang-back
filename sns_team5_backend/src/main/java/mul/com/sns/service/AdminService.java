package mul.com.sns.service;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.log4j.Log4j2;
import mul.com.sns.dao.AdminDao;
import mul.com.sns.dto.AskDto;
import mul.com.sns.dto.OrdersDto;
import mul.com.sns.dto.SearchParam;
import mul.com.sns.dto.UserDto;

@Service
@Transactional
@Log4j2
public class AdminService {

	@Autowired
	AdminDao dao;
	
	public int getNewData(String dataType) {
		if(dataType.equals("user")) {
			return dao.getNewUser();
		}
		else if(dataType.equals("order")) {
			return dao.getNewOrder();
		}
		else if(dataType.equals("sales")) {
			return dao.getNewSales();
		}
		else {
			return 0;
		}
	}
	
	public int getDataUpDown(String dataType) {
		if(dataType.equals("user")) {
			int now = dao.getNewUser();
			int before = dao.getNewUserUpDown();
			return now - before;
		}
		else if(dataType.equals("order")) {
			int now = dao.getNewOrder();
			int before = dao.getNewOrderUpDown();
			return now - before;
		}
		else if(dataType.equals("sales")) {
			int now = dao.getNewSales();
			int before = dao.getNewSalesUpDown();
			return now - before;
		}
		else {
			return 0;
		}
	}
	
	public Map<String, Integer> getDailySales() {
		Date now = new Date(System.currentTimeMillis());
		Date yesterday = new Date(now.getTime() - 1000*60*60*24);
		Date twodays = new Date(now.getTime() - 1000*60*60*24*2);
		System.out.println(now);
		
		Map<String, Integer> data = new HashMap<String, Integer>();
		data.put("twodays", dao.getDailySales(twodays));
		data.put("yesterday", dao.getDailySales(yesterday));
		data.put("now", dao.getDailySales(now));
		
		return data;
	}
	
	public List<UserDto> getUserList(SearchParam param) {
		return dao.getUserList(param);
	}
	
	public int getUserCount(SearchParam param) {
		return dao.getUserCount(param);
	}
	
	public List<OrdersDto> getOrderList(SearchParam param) {
		return dao.getOrderList(param);
	}
	
	public int getOrderCount(SearchParam param) {
		return dao.getOrderCount(param);
	}
	
	public void changeOrderStatus(OrdersDto order) {
		dao.changeOrderStatus(order);
	}
	
	public List<Object> getProductList(SearchParam param) {
		return dao.getProductList(param);
	}
	
	public int getProductCount(SearchParam param) {
		return dao.getProductCount(param);
	}
	
	public void changeProduct() {
		dao.changeProduct();
	}
	
	public void addProduct() {
		dao.addProduct();
	}
	
	public OrdersDto getOrderProduct(int seq) {
		return dao.getOrderProduct(seq);
	}
	
	public List<AskDto> getAskList(SearchParam param) {
		return dao.getAskList(param);
	}
	
	public int getAskCount(SearchParam param) {
		return dao.getAskCount(param);
	}
	
	public AskDto getAskDetail(AskDto ask) {
		return dao.getAskDetail(ask);
	}
	
	public AskDto getAnswerDetail(AskDto ask) {
		return dao.getAnswerDetail(ask);
	}
	
	public void addAskAnswer(AskDto ask) {
		dao.addAskAnswer(ask);
	}
	
	public void updateAskAnswer(AskDto ask) {
		dao.updateAskAnswer(ask);
	}
	
	public void updateAsk(int seq) {
		dao.updateAsk(seq);
	}
	

	public List<AskDto> getAskProduct(int productid) {
		
		return dao.getAskProduct(productid);
	}

	public List<AskDto> getAnswerProduct(int productid){
		
		return dao.getAnswerProduct(productid);
	}
}
