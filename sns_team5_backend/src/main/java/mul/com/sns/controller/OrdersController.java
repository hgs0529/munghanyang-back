package mul.com.sns.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mul.com.sns.dto.OrdersDto;
import mul.com.sns.dto.UserDto;
import mul.com.sns.service.OrdersService;

@RestController
@RequestMapping(value = "/orders") // 묶어주기
public class OrdersController {

	@Autowired
	OrdersService service;
	
	//유저에 따라 주문결과 불러오기
	@GetMapping(value = "/all")
	public List<OrdersDto> getOrders(int seq){
		System.out.println("OrdersController getOrders GET()");
		
		List<OrdersDto> list = service.getOrders(seq);
		for(OrdersDto od : list) {
			System.out.println(od.toString());
		}
		
		return list;

	}
	//주문 결과 하나만 보기
	@GetMapping(value = "/one")
	public OrdersDto getOrdersOne(int seq){
		System.out.println("OrdersController getOrdersOne GET()");
			
		return service.getOrdersOne(seq);
		}
	
	//주문결과 추가
	   @PostMapping(value = "/new")
	   public int addOrders(OrdersDto od) {
	      System.out.println("OrdersController addOrders POST()");
	      
	      
	      System.out.println(od.toString());
	      int mungpoint = od.getSavemungpoint() - od.getUsemungpoint();
	      UserDto dto = new UserDto();
	      dto.setSeq(od.getUserid());
	      dto.setMungpoint(mungpoint);
	      service.UpdateMungpoint(dto);
	      
	      
	      service.addOrders(od);
	      
	      int n = service.recentlyAddSeq(od);
	      
	      return n;

	   }   
	
	//주문결과 수정
	@PostMapping(value = "/update")
	public String updateOrders(OrdersDto od) {
		System.out.println("OrdersController updateOrders POST()");
		
		boolean b = service.updateOrders(od);
		if(b == true) {
			return "OK";
		}else {
			return "NG";
		}		
	}	

	//주문결과 삭제
	@PostMapping(value = "/del")
	public String deleteOrders(OrdersDto od) {
		System.out.println("OrdersController deleteOrders POST()");
			
		boolean b = service.deleteOrders(od);
		if(b == true) {
			return "OK";
		}else {
			return "NG";
		}		
	}	
}
