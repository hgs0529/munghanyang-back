package mul.com.sns.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import mul.com.sns.dto.FeedDto;

@Mapper
@Repository
public interface FeedDao {

	List<FeedDto> getallFeed();
	
	boolean addFeed(FeedDto dto);
	
	int updateFeed(FeedDto dto);
	
	int deleteFeed(int seq);
}
