package mul.com.sns.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import mul.com.sns.dto.FollowDto;
import mul.com.sns.dto.LikesDto;

@Mapper
@Repository
public interface LikesDao {
	
	int giveLikes(LikesDto likesdto);
	
	int deleteLikes(LikesDto likesdto);
	
	int countLikes(LikesDto likesdto);
	
	List<LikesDto> LikesList(int userid);

}
