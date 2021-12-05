package mul.com.sns.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mul.com.sns.dao.AddressDao;
import mul.com.sns.dto.AddressDto;

@Service
@Transactional
public class AddressService {
	
	@Autowired
	AddressDao dao;

	public List<AddressDto> getAddress(int userid){
		return dao.getAddress(userid);
	}
	
	public AddressDto getAddressOne(int seq){
		return dao.getAddressOne(seq);
	}
	
	public AddressDto getAddressName(AddressDto ad){
		return dao.getAddressName(ad);
	}
	
	public boolean addAddress(AddressDto ad){		
		int n = dao.addAddress(ad);
		return n>0?true:false;
	}
	
	public boolean updateAddress(AddressDto ad){
		int n = dao.updateAddress(ad);
		return n>0?true:false;
	}

	public boolean deleteAddress(AddressDto ad){
		int n = dao.deleteAddress(ad);
		return n>0?true:false;
	}
	public void UpdateDefAddress(AddressDto ad) {
	      
      dao.UpdateDefAddress(ad);
   }
	
	public int recentlyAddSeq(AddressDto ad) {
	      
      return dao.recentlyAddSeq(ad);
   }
		
}
