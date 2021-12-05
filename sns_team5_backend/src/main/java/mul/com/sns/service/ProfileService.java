package mul.com.sns.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mul.com.sns.dao.ProfileDao;
import mul.com.sns.dto.PetProfileDto;

@Service
@Transactional
public class ProfileService {

	@Autowired
	ProfileDao dao;
	
	public List<PetProfileDto> getPetProfile(int userid) {
		
		return dao.getPetProfile(userid);
	}
	
	public boolean addPetProfile(PetProfileDto dto) {
		
		int n = dao.addPetProfile(dto);
		
		return n >0 ? true:false; 
	}
	
	public boolean deletePetProfile(int seq) {
		int n = dao.deletePetProfile(seq);
		
		return n >0 ? true:false; 
	}
}
