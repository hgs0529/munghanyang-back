package mul.com.sns.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mul.com.sns.dao.CartDao;
import mul.com.sns.dto.CartDto;
import mul.com.sns.dto.ProductOptionDto;

@Service
@Transactional
public class CartService {
	
	@Autowired
	CartDao dao;

	public List<CartDto> getCart(int userid){
		return dao.getCart(userid);
	}
	public CartDto getSum(int userid){
		return dao.getSum(userid);
	}
	
	public int countCart(int userid){
		return dao.countCart(userid);
	}
	public boolean addCart(CartDto cd){		
		int n = dao.addCart(cd);
		return n>0?true:false;
	}
	public boolean updateCart(CartDto cd){		
		int n = dao.updateCart(cd);
		return n>0?true:false;
	}
	public boolean allCheckCart(int userid){
		int n = dao.allCheckCart(userid);
		return n>0?true:false;
	}
	public boolean allUncheckCart(int userid){
		int n = dao.allUncheckCart(userid);
		return n>0?true:false;
	}
	public boolean checkCart(CartDto cd){
		int n = dao.checkCart(cd);
		return n>0?true:false;
	}
	public boolean uncheckCart(CartDto cd){
		int n = dao.uncheckCart(cd);
		return n>0?true:false;
	}
	public boolean btnDelCart(CartDto cd){
		int n = dao.btnDelCart(cd);
		return n>0?true:false;
	}
	public boolean checkDelCart(CartDto cd){
		int n = dao.checkDelCart(cd);
		return n>0?true:false;
	}
	public boolean allDelCart(int userid){
		int n = dao.allDelCart(userid);
		return n>0?true:false;
	}
	
	public CartDto getimmediatelyPurchase(CartDto dto) {
		
		return dao.getimmediatelyPurchase(dto);
	}
	
	public ProductOptionDto getProductOption(int seq) {
		
		return dao.getProductOption(seq);
	}
	
	public boolean UpdateQuantity(CartDto dto) {
		
		int n = dao.UpdateQuantity(dto);
		
		return n > 0 ? true:false;
	}

	public List<CartDto> getCheck(int userid){
		
		return dao.getCheck(userid);
	}

	public boolean AfterDelCart(CartDto dto) {
	      
	      int n = dao.AfterDelCart(dto);
	      
	      return n > 0 ? true:false;
	   }

}
