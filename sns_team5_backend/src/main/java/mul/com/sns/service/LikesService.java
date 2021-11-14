package mul.com.sns.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mul.com.sns.dao.LikesDao;
import mul.com.sns.dto.FollowDto;
import mul.com.sns.dto.LikesDto;

@Service
@Transactional
public class LikesService {
	
	@Autowired
	LikesDao likesdao;
	
	public void giveLikes(LikesDto likesdto) {
		likesdao.giveLikes(likesdto);
	}

	public void deleteLikes(LikesDto likesdto) {
		likesdao.deleteLikes(likesdto);
	}
	
	public int countLikes(LikesDto likesdto) {
		return likesdao.countLikes(likesdto);
	}
	
	public List<LikesDto> LikesList(int userid) {
		return likesdao.LikesList(userid);
	}
	
	
}
