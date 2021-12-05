package mul.com.sns.dao;


import java.sql.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import mul.com.sns.dto.AskDto;
import mul.com.sns.dto.OrdersDto;
import mul.com.sns.dto.SearchParam;
import mul.com.sns.dto.UserDto;

@Mapper
@Repository
public interface AdminDao {
	
	int getNewUser();
	int getNewUserUpDown();
	int getNewOrder();
	int getNewOrderUpDown();
	int getNewSales();
	int getNewSalesUpDown();
	
	int getDailySales(Date date);
	
	List<UserDto> getUserList(SearchParam param);
	int getUserCount(SearchParam param);
	
	List<OrdersDto> getOrderList(SearchParam param);
	int getOrderCount(SearchParam param);
	OrdersDto getOrderProduct(int seq);
	void changeOrderStatus(OrdersDto order);
	
	List<Object> getProductList(SearchParam param);
	int getProductCount(SearchParam param);
	void changeProduct();
	void addProduct();
	
	List<AskDto> getAskList(SearchParam param);
	int getAskCount(SearchParam param);
	AskDto getAskDetail(AskDto ask);
	AskDto getAnswerDetail(AskDto ask);
	void addAskAnswer(AskDto ask);
	void updateAskAnswer(AskDto ask);
	void updateAsk(int seq);
	
	List<AskDto> getAskProduct(int productid);
	List<AskDto> getAnswerProduct(int productid);
}
