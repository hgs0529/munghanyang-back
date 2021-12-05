package mul.com.sns.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import mul.com.sns.dto.FeedDto;
import mul.com.sns.dto.FollowDto;
import mul.com.sns.dto.HashtagDto;
import mul.com.sns.dto.LikesDto;
import mul.com.sns.dto.PageParam;

@Mapper
@Repository
public interface FeedDao {

	List<FeedDto> getallFeed(PageParam param);
	List<FeedDto> getUserFeed(FeedDto dto);
	int getFeedCount(PageParam param);
	
	FeedDto getFeed(int seq);
	void updateReadcount(FeedDto dto);
	
	/* List<FeedDto> getCategoryFeed(FeedDto dto); */
	
	boolean addFeed(FeedDto dto);
	
	int updateFeed(FeedDto dto);
	
	FeedDto getLikeFeed(int photoid);
	
	List<Integer> getLikeCount(int userid);
	
	int deleteFeed(int seq);
	
	int recentlyAddSeq();
	
	List<LikesDto> getallLikes(int userid);
	
	int getFeedLikes(FeedDto dto);
	
	List<FeedDto> getOther(FeedDto dto);
	
	boolean isLikes(FeedDto dto);
	
	int serachLikes(LikesDto dto);
	
	int addLikes(LikesDto dto);
	
	int deleteLikes(LikesDto dto);
	
	int deleteAllLikes(int seq);
	
	FeedDto getFeedDetail(int seq);
	
	List<FeedDto> getMyFeed(int userid);
	
	List<FeedDto> getcategoryFeed(FeedDto dto);

	
	
	/* Follow 부분 */
	
	// 팔로우
	int follow(FollowDto followdto);
	
	// 팔로우 되어있는지 확인
	int isfollow(FollowDto followdto);
	
	// 언팔로우 (팔로우 취소)
	int unfollow(FollowDto followdto);
	
	// 팔로우 리스트 조회
	List<FollowDto> FollowgiveList(FollowDto followdto);
	
	// 팔로워 리스트 조회
	List<FollowDto> FollowrecvList(FollowDto followdto);
	
	//내가 팔로우 한 사람의 피드 가져오기
	List<FeedDto> getfollowFeed(int userid);
	
	
	/* Hashtag 부분 */
	
	/* 해시태그 삭제 */
	void deletehashtag(int seq);
	
	/* 해시태그 추가 */
	void addHashtag(HashtagDto dto);
	
	/* 피드에 해당하는 해시태그 불러오기 */
	List<String> getHashtag(int seq);
	
}
