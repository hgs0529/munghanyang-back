package mul.com.sns.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import mul.com.sns.dto.FollowDto;

@Mapper
@Repository
public interface FollowDao {
	
	// 팔로우
	void follow(FollowDto followdto);
	
	// 언팔로우 (팔로우 취소)
	void unfollow(FollowDto followdto);
	
	// 팔로우 리스트 조회
	List<FollowDto> FollowgiveList(FollowDto followdto);
	
	// 팔로워 리스트 조회
	List<FollowDto> FollowrecvList(FollowDto followdto);
	
	// 팔로우 유무
//	int isFollow(FollowDto followdto);
	
	// 탈퇴 시 팔로우 삭제
	
	
	
}
