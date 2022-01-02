package mul.com.sns.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import mul.com.sns.dto.CartDto;
import mul.com.sns.dto.ProductOptionDto;
import mul.com.sns.service.CartService;

@RestController
@RequestMapping(value = "/cart") // 묶어주기
public class CartController {

	@Autowired
	CartService service;
	
		//장바구니 목록 보기
		@GetMapping(value = "/all")
		public List<CartDto> getCart(int userid, int resetchk){
			System.out.println("CartController getCart GET()");
			
			if(resetchk == 0 ) {
				service.allCheckCart(userid);
			}
			
			
			
			List<CartDto> list = service.getCart(userid);
			for(CartDto cd : list) {
				
			}
			// System.out.println(list);
			return list;
		}
		
		@RequestMapping(value = "/getcheck", method = RequestMethod.GET)
		public List<CartDto> getCheck(int userid){
			System.out.println("CartController getCheck()");
			
			return service.getCheck(userid);
		}


		// 내 장바구니 안 체크한 것의 (가격*수량)의 값을 구함
		@GetMapping(value = "/sum")
		public CartDto getSum(int userid){
			System.out.println("CartController getSum GET()");
			
			return service.getSum(userid);
		}
		
		//내 장바구니 갯수
		@GetMapping(value = "/count")
		public int countCart(int userid) {
			System.out.println("CartController countCart GET()");
				
			return service.countCart(userid);
		}
		
		//장바구니에 추가
	@PostMapping(value = "/new")
	public String addCart(CartDto dto) {
		System.out.println("CartController addCart POST()");
		
		String str = "";
		if(!dto.getOptions.equals("")) {
			String[] option = dto.getOptions().split("\\?");
			for(int i=0; i<option.length; i++) {
				ProductOptionDto pdto = service.getProductOption(Integer.parseInt(option[i]));
				str += pdto.getTitle() + "-" + pdto.getSubtitle() + "-" + Integer.toString(pdto.getOptionprice()) + "?";
				dto.setOptions(str.substring(0, str.length() - 1));
			}
		}
		
		
		
		boolean b = service.addCart(dto);
		if(b) {
			return "OK";
		}else {
			return "NG";
		}
		
	}

	/* 바로구매 */
	@RequestMapping(value = "/addimmediately", method = RequestMethod.POST)
	public String immediatelyPurchase(CartDto dto) {
		System.out.println("CartController immediatelyPurchase()");
		
		service.allUncheckCart(dto.getUserid());
		
		if(!dto.getOptions().equals("")) {
			String[] option = dto.getOptions().split("\\?");
			
			String str = "";
			
			for(int i=0; i<option.length; i++) {
				ProductOptionDto pdto = service.getProductOption(Integer.parseInt(option[i]));
				str += pdto.getTitle() + "-" + pdto.getSubtitle() + "-" + Integer.toString(pdto.getOptionprice()) + "?";				
			}
			dto.setOptions(str.substring(0, str.length() - 1));
		}
		
		boolean b = service.addCart(dto);
		
		if(b) {
			return "OK";
		}else {
			return "NG";
		}
	}
		//장바구니 수정
		@PostMapping(value = "/update")
		public String updateCart(CartDto cd) {
			System.out.println("CartController updateCart POST()");
			
			boolean b = service.updateCart(cd);
			if(b == true) {
				return "OK";
			}else {
				return "NG";
			}		
		}	

		//모두체크
		@PostMapping(value = "/allcheck")
		public String allCheckCart(int userid) {
			System.out.println("CartController allCheckCart POST()");
			
			boolean b = service.allCheckCart(userid);
			if(b == true) {
				return "OK";
			}else {
				return "NG";
			}			
		}
		
		//모두언체크
		@PostMapping(value = "/alluncheck")
		public String allUncheckCart(int userid) {
			System.out.println("CartController allUncheckCart POST()");
			
			boolean b = service.allUncheckCart(userid);
			if(b == true) {
				return "OK";
			}else {
				return "NG";
			}			
		}
		
		//체크될때
		@PostMapping(value = "/check")
		public String checkCart(CartDto cd) {
			System.out.println("CartController checkCart POST()");
				
			boolean b = service.checkCart(cd);
			if(b == true) {
				return "OK";
			}else {
				return "NG";
			}		
		}
		
		//언체크될때
		@PostMapping(value = "/uncheck")
		public String uncheckCart(CartDto cd) {
			System.out.println("CartController uncheckCart POST()");
			
			boolean b = service.uncheckCart(cd);
			if(b == true) {
				return "OK";
			}else {
				return "NG";
			}		
		}
		
		@RequestMapping(value = "/afterdel", method = RequestMethod.POST)
	      public String AfterDelCart(CartDto cd) {
	         System.out.println("CartController AfterDelCart()");
	         
	         boolean b = service.AfterDelCart(cd);
	         
	         if(b) {
	            return "OK";
	         }else {
	            return "NG";
	         }
	      }
		
		//버튼삭제
		@PostMapping(value = "/btndel")
		public String btnDelCart(CartDto cd) {
			System.out.println("CartController btnDelCart POST()");
			System.out.println(cd.toString());
			boolean b = service.btnDelCart(cd);
			if(b == true) {
				return "OK";
			}else {
				return "NG";
			}		
		}
		
		//체크만 삭제
		@PostMapping(value = "/checkdel")
		public String checkDelCart(CartDto cd) {
			System.out.println("CartController checkDelCart POST()");
			System.out.println(cd.toString());
			boolean b = service.checkDelCart(cd);
			if(b == true) {
				return "OK";
			}else {
				return "NG";
			}		
		}
		
		//모두 삭제
		@PostMapping(value = "/alldel")
		public String allDelCart(int userid) {
			System.out.println("CartController allDelCart POST()");
			
			boolean b = service.allDelCart(userid);
			if(b == true) {
				return "OK";
			}else {
				return "NG";
			}		
		}
		
		/*수량 변경*/
		@RequestMapping(value = "/setquantity", method = RequestMethod.POST)
		public String UpdateQuantity(CartDto dto) {
			System.out.println("CartController Updatequantity()");
			
			boolean b = service.UpdateQuantity(dto);
			
			if(b) {
				return "OK";
			}else {
				return "NG";
			}
		}

	
}
