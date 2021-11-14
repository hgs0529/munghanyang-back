package mul.com.sns.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mul.com.sns.dao.FollowDao;
import mul.com.sns.dto.FollowDto;

@Service
@Transactional
public class FollowService {
	
	@Autowired
	FollowDao followdao;
	
	// 팔로우
	public void follow(FollowDto followdto) {
		followdao.follow(followdto);
	}
		
	// 언팔로우 (팔로우 취소)
	public void unfollow(FollowDto followdto) {
		followdao.unfollow(followdto);
	}
		
	// 팔로우 리스트 조회
	public List<FollowDto> FollowgiveList(FollowDto followdto) {
		return followdao.FollowgiveList(followdto);
		
	}
		
	// 팔로워 리스트 조회
	public List<FollowDto> FollowrecvList(FollowDto followdto) {
		return followdao.FollowrecvList(followdto);
	}
		

}
