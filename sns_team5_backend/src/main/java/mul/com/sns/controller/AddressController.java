package mul.com.sns.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mul.com.sns.dto.AddressDto;
import mul.com.sns.service.AddressService;

@RestController
@RequestMapping(value = "/address") // 묶어주기
public class AddressController {

	@Autowired
	AddressService service;
	
		//유저에 따라 주소 불러오기
		@GetMapping(value = "/all")
		public List<AddressDto> getAddress(int userid){
			System.out.println("AddressController getAddress GET()");
			
			List<AddressDto> list = service.getAddress(userid);
			return list;

		}
		
		//유저에 따라 주소 불러오기
		@GetMapping(value = "/one")
		public AddressDto getAddressOne(int seq){
			System.out.println("AddressController getAddressOne GET()");
			
			return service.getAddressOne(seq);
		}
		
		//유저의 배송지명에 따라 주소 불러오기
		@GetMapping(value = "/name")
		public AddressDto getAddressName(AddressDto ad){
			System.out.println("AddressController getAddressName GET()");
			
			return service.getAddressName(ad);
		}

		 //주소 추가
	      @PostMapping(value = "/new")
	      public int addAddress(AddressDto ad) {
	         System.out.println("AddressController addAddress POST()");
	         
	         if(ad.isDef()) {
	            service.UpdateDefAddress(ad);
	         }
	         
	         service.addAddress(ad);
	         
	         int n = service.recentlyAddSeq(ad);
	         
	         return n;
	      }   
	
		
		//주소 수정
		@PostMapping(value = "/update")
		public String updateAddress(AddressDto ad) {
			System.out.println("AddressController updateAddress POST()");
			
			boolean b = service.updateAddress(ad);
			if(b == true) {
				return "OK";
			}else {
				return "NG";
			}		
		}	

		//주소 삭제
		@PostMapping(value = "/del")
		public String deleteAddress(AddressDto ad) {
			System.out.println("AddressController deleteAddress POST()");
				
			boolean b = service.deleteAddress(ad);
			if(b == true) {
				return "OK";
			}else {
				return "NG";
			}		
		}	
}
