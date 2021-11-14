package mul.com.sns.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mul.com.sns.dao.FeedDao;
import mul.com.sns.dto.FeedDto;

@Service
@Transactional
public class FeedService {

	@Autowired
	FeedDao dao;
	
	public List<FeedDto> getallFeed() {
		
		return dao.getallFeed();
	}
	
	public boolean addFeed(FeedDto dto) {
			
		return dao.addFeed(dto);
	}
	
	public boolean updateFeed(FeedDto dto) {
		
		int n = dao.updateFeed(dto);
		
		return n>0?true:false;
	}
	
	public boolean deleteFeed(int seq) {
		
		int n = dao.deleteFeed(seq);
		return n>0?true:false;
	}
	
}
