package mul.com.sns.dao;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import mul.com.sns.dto.CommentDto;
import mul.com.sns.dto.HashtagDto;

@Mapper
@Repository
public interface HashtagDao extends Serializable {

	//불러오기 
	List<HashtagDto> getHashtag(int photoid);
	
	//추가하기
	int addHashtag(HashtagDto has);
}
