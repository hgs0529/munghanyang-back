package mul.com.sns.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j2;
import mul.com.sns.dto.AskDto;
import mul.com.sns.dto.OrdersDto;
import mul.com.sns.dto.SearchParam;
import mul.com.sns.dto.UserDto;
import mul.com.sns.service.AdminService;

@Log4j2
@RestController
@RequestMapping(value="/admin")
public class AdminController {

	@Autowired
	AdminService service;
	
	////////// 대시보드 //////////
	// 신규 데이터 가져오기
	@GetMapping(value="/dashboard/count")
	public int newuser(String dataType) {
		log.info("**** Admin GET /dashboard/count ****");
		log.info("dataType: " + dataType);
		return service.getNewData(dataType);
	}
	
	// 데이터 증감 비교
	@GetMapping(value="/dashboard/count/updown")
	public int userupdown(String dataType) {
		log.info("**** Admin GET /dashboard/count/updown ****");
		return service.getDataUpDown(dataType);
	}
	
	// 차트: 일별 수익
	@GetMapping(value="/dashboard/chart/sales")
	public Map<String, Integer> dailysales() {
		log.info("**** Admin GET /dashboard/chart/sales ****");
		return service.getDailySales();
	}
	
	////////// 회원 관리 //////////
	// 회원 목록 불러오기
	@GetMapping(value="/users")
	public List<UserDto> userlist(SearchParam param) {
		log.info("**** Admin GET /users ****");
		
		int currentPage = param.getCurrentPage();
		int end = currentPage * param.getDataPerPage();
		int start = end - (param.getDataPerPage() - 1);
		
		log.info("currentPage: " + currentPage + ", start: " + start + ", end: " + end);
		
		param.setStart(start);
		param.setEnd(end);
		
		return service.getUserList(param);
	}
	
	// 회원수 불러오기
	@GetMapping(value="/users/count")
	public int usercount(SearchParam param) {
		log.info("**** Admin GET /users/count ****");
		return service.getUserCount(param);
	}
	
	
	////////// 주문관리 //////////
	// 주문 목록 불러오기
	@GetMapping(value="/orders")
	public List<OrdersDto> orderlist(SearchParam param) {
		log.info("**** Admin GET /orders ****");
		
		int currentPage = param.getCurrentPage();
		int end = currentPage * param.getDataPerPage();
		int start = end - (param.getDataPerPage() - 1);
		
		log.info("currentPage: " + currentPage + ", start: " + start + ", end: " + end);
		
		param.setStart(start);
		param.setEnd(end);
		
		return service.getOrderList(param);
	}
	
	// 주문수 불러오기
	@GetMapping(value="/orders/count")
	public int ordercount(SearchParam param) {
		log.info("**** Admin GET /orders/count ****");
		return service.getOrderCount(param);
	}
	
	// 주문상품명 불러오기
	@GetMapping(value="/orders/count/products")
	public OrdersDto orderproduct(int seq) {
		log.info("**** Admin GET /orders/count/products ****");
		return service.getOrderProduct(seq);
	}
	
	// 주문상태 변경하기
	@PostMapping(value="/orders/status")
	public String orderstatus(OrdersDto order) {
		log.info("**** Admin POST /delivery/status ****");
		
		log.info("**** 주문상태 " + order.getOrderstatus() + "로 변경");
		log.info(order.getOrderarray());
		
		service.changeOrderStatus(order);
		
		return "OK";
	}
	
	////////// 상품관리 //////////
	// 상품목록 불러오기
	@GetMapping(value="/product")
	public List<Object> productlist(SearchParam param) {
		log.info("**** Admin GET /product/list ****");
		return service.getProductList(param);
	}
	
	@GetMapping(value="/product/count")
	public int productcount(SearchParam param) {
		log.info("**** Admin GET /product/count ****");
		return service.getProductCount(param);
	}
	
	@GetMapping(value="/product/setting")
	public void productattribute() {
		log.info("**** Admin GET /product/setting ****");
		service.changeProduct();
	}
	
	@GetMapping(value="/product/new")
	public void productnew() {
		log.info("**** Admin GET /product/new ****");
		service.addProduct();
	}
	
	////////// 문의관리 //////////
	// 문의목록 불러오기
	@GetMapping(value="/product/ask")
	public List<AskDto> ask(SearchParam param) {
		log.info("**** Admin GET /ask ****");
		
		int currentPage = param.getCurrentPage();
		int end = currentPage * param.getDataPerPage();
		int start = end - (param.getDataPerPage() - 1);
		
		log.info("currentPage: " + currentPage + ", start: " + start + ", end: " + end);
		
		param.setStart(start);
		param.setEnd(end);
		
		return service.getAskList(param);
	}
	
	// 총 문의수 불러오기
	@GetMapping(value="/product/ask/count")
	public int askcount(SearchParam param) {
		log.info("**** Admin GET /ask/count ****");
		return service.getAskCount(param);
	}
	
	// 문의 상세 불러오기
	@GetMapping(value="/product/ask/detail")
	public AskDto askdetail(AskDto ask) {
		log.info("**** Admin GET /ask/detail ****");
		return service.getAskDetail(ask);
	}
	
	// 답변 상세 불러오기
	@GetMapping(value="/product/answer/detail")
	public AskDto answerdetail(AskDto ask) {
		log.info("**** Admin GET /answer/detail ****");
		return service.getAnswerDetail(ask);
	}
	
	// 문의 답변 등록
	@PostMapping(value="/product/answer/new")
	public String askasnswer(AskDto ask) {
		log.info("**** Admin POST /ask/answer/new ****");
		service.addAskAnswer(ask);
		return "ANSWER OK";
	}
	
	@PostMapping(value="/product/ask/answered")
	public String askanswered(int seq) {
		log.info("**** Admin POST /ask/answered ****");
		log.info("seq: " + seq);
		service.updateAsk(seq);
		return "UPDATE OK";
	}
	
	// 문의 답변 수정
	@PostMapping(value="/product/answer/update")
	public String askupdate(AskDto ask) {
		log.info("*** Admin POST /ask/answer/update ****");
		service.updateAskAnswer(ask);
		return "ANSWER OK";
	}
	

	@RequestMapping(value = "/product/getask", method = RequestMethod.GET)
	public List<AskDto> getAsk(int productid) {
		System.out.println("AdminController getAsk()");
		
		List<AskDto> list = service.getAskProduct(productid);
		
		return list;
	}
	
	@RequestMapping(value = "/product/getanswer", method = RequestMethod.GET)
	public List<AskDto> getAnswer(int productid) {
		System.out.println("AdminController getAnswer()");
		
		List<AskDto> list = service.getAnswerProduct(productid);
		
		return list;
	}
}
