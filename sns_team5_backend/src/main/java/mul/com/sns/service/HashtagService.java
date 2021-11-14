package mul.com.sns.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mul.com.sns.dao.HashtagDao;
import mul.com.sns.dto.CommentDto;
import mul.com.sns.dto.HashtagDto;

@Service
@Transactional
public class HashtagService {

	@Autowired
	HashtagDao dao;
	
	public List<HashtagDto> getHashtag(int photoid){
		return dao.getHashtag(photoid);
	}
	
	public boolean addHashtag(HashtagDto has) {
		int n = dao.addHashtag(has);
		
		return n>0?true:false;	
	}
	
}
