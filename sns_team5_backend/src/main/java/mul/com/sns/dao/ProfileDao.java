package mul.com.sns.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import mul.com.sns.dto.PetProfileDto;

@Mapper
@Repository
public interface ProfileDao {

	List<PetProfileDto> getPetProfile(int userid);
	
	int addPetProfile(PetProfileDto dto);
	
	int deletePetProfile(int seq);
}
