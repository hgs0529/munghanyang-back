package mul.com.sns.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mul.com.sns.dto.OrdersDetailDto;
import mul.com.sns.service.OrdersDetailService;

@RestController
@RequestMapping(value = "/ordersdetail") // 묶어주기
public class OrdersDetailController {

	@Autowired
	OrdersDetailService service;
	
	//유저에 따라 주문결과 불러오기
	@GetMapping(value = "/all")
	public List<OrdersDetailDto> getOrdersDetail(int orderid){
		System.out.println("OrdersDetailController getOrdersDetail GET()");
		
		List<OrdersDetailDto> list = service.getOrdersDetail(orderid);
		for(OrdersDetailDto oddto : list) {
			System.out.println(oddto.toString());
		}
		
		return list;

	}

	//주문 결과 하나만 보기
	@GetMapping(value = "/one")
	public OrdersDetailDto getOrdersDetailOne(int seq){
		System.out.println("OrdersDetailController getOrdersDetailOne GET()");
		
		return service.getOrdersDetailOne(seq);

	}
	
	//주문결과 추가
	@PostMapping(value = "/new")
	public String addOrdersDetail(OrdersDetailDto oddto) {
		System.out.println("OrdersDetailController addOrdersDetail POST()");
		
		boolean b = service.addOrdersDetail(oddto);
		if(b == true) {
			return "OK";
		}else {
			return "NG";
		}		
	}	
	
	//주문결과 수정
	@PostMapping(value = "/update")
	public String updateOrdersDetail(OrdersDetailDto oddto) {
		System.out.println("OrdersDetailController updateOrdersDetail POST()");
		
		boolean b = service.updateOrdersDetail(oddto);
		if(b == true) {
			return "OK";
		}else {
			return "NG";
		}		
	}	

	//주문결과 삭제
	@PostMapping(value = "/del")
	public String deleteOrdersDetail(OrdersDetailDto oddto) {
		System.out.println("OrdersDetailController deleteOrdersDetail POST()");
			
		boolean b = service.deleteOrdersDetail(oddto);
		if(b == true) {
			return "OK";
		}else {
			return "NG";
		}		
	}	
	
}
