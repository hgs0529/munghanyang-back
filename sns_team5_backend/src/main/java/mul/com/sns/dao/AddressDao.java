package mul.com.sns.dao;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import mul.com.sns.dto.AddressDto;

@Mapper
@Repository
public interface AddressDao extends Serializable  {
	
		//불러오기
		List<AddressDto> getAddress(int userid);

		//하나만 불러오기
		AddressDto getAddressOne(int seq);
		
		//주소명에 따라 해당하는 주소 불러오기
		AddressDto getAddressName(AddressDto ad);
		
		//주소 추가
		int addAddress(AddressDto ad);
		
		//주소 수정
		int updateAddress(AddressDto ad);
		
		//주소 삭제
		int deleteAddress(AddressDto ad);

		//기본배송지에 따른 테이블 수정
	    void UpdateDefAddress(AddressDto ad);
	    
	    int recentlyAddSeq(AddressDto ad);
}
