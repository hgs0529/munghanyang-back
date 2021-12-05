package mul.com.sns.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mul.com.sns.dao.FeedDao;
import mul.com.sns.dto.FeedDto;
import mul.com.sns.dto.FollowDto;
import mul.com.sns.dto.HashtagDto;
import mul.com.sns.dto.LikesDto;
import mul.com.sns.dto.PageParam;

@Service
@Transactional
public class FeedService {

	@Autowired
	FeedDao dao;
	
	public List<FeedDto> getallFeed(PageParam param) {
		
		return dao.getallFeed(param);
	}
	
	public int getFeedCount(PageParam param) {
		
		return dao.getFeedCount(param);
	}
	
	public List<FeedDto> getUserFeed(FeedDto dto) {
		return dao.getUserFeed(dto);
	}
	
	/*
	 * public List<FeedDto> getCategoryFeed(FeedDto dto){
	 * 
	 * return dao.getCategoryFeed(dto); }
	 */
	
	public FeedDto getFeed(int seq) {
		
		return dao.getFeed(seq);
	}
	
	public void updateReadcount(FeedDto dto) {
		
		dao.updateReadcount(dto);
	}
	
	public boolean addFeed(FeedDto dto) {
			
		return dao.addFeed(dto);
	}
	
	public boolean updateFeed(FeedDto dto) {
		
		int n = dao.updateFeed(dto);
		
		return n>0?true:false;
	}
	
	public FeedDto getLikeFeed(int photoid){
		
		return dao.getLikeFeed(photoid);
		
	}
	
	public List<Integer> getLikeCount(int userid){
		
		return dao.getLikeCount(userid);
	}
	

	
	public boolean deleteFeed(int seq) {
		
		int n = dao.deleteFeed(seq);
		return n>0?true:false;
	}
	
	public int recentlyAddSeq() {
		
		return dao.recentlyAddSeq();
	}
	
	
	public List<LikesDto> getallLikes(int userid){
		
		return dao.getallLikes(userid);
	}
	
	public int getFeedLikes(FeedDto dto) {
		
		return dao.getFeedLikes(dto);
	}
	
	public boolean isLikes(FeedDto dto) {
		
		return dao.isLikes(dto);
	}
	
	public List<FeedDto> getOther(FeedDto dto){
		
		return dao.getOther(dto);	
	}

	
	
	public int serachLikes(LikesDto dto) {
		
		return dao.serachLikes(dto);
	}
	
	public int addLikes(LikesDto dto) {
		
		return dao.addLikes(dto);
	}
	
	public int deleteLikes(LikesDto dto) {
		
		return dao.deleteLikes(dto);
	}
	
	public boolean deleteAllLikes(int seq) {
		int n = dao.deleteAllLikes(seq);
		
		return n>0?true:false;
	}
	
	public FeedDto getFeedDetail(int seq) {
		
		return dao.getFeedDetail(seq);
	}
	
	
	public List<FeedDto> getMyFeed(int userid){
		
		return dao.getMyFeed(userid);
	}
	
	public List<FeedDto> getcategoryFeed(FeedDto dto){
		
		return dao.getcategoryFeed(dto);
	}

	
	
	/* Follow 부분 */
	
	// 팔로우
	public boolean follow(FollowDto followdto) {
		int n = dao.follow(followdto);
		
		return n>0?true:false;
	}
	
	// 팔로우 확인
	public boolean isfollow(FollowDto followdto) {
		int n = dao.isfollow(followdto);
		return n>0?true:false;
	}
		
	// 언팔로우 (팔로우 취소)
	public boolean unfollow(FollowDto followdto) {
		int n = dao.unfollow(followdto);
		
		return n>0?true:false;
	}
		
	// 팔로우 리스트 조회
	public List<FollowDto> FollowgiveList(FollowDto followdto) {
		
		return dao.FollowgiveList(followdto);
		
	}
		
	// 팔로워 리스트 조회
	public List<FollowDto> FollowrecvList(FollowDto followdto) {
		
		return dao.FollowrecvList(followdto);
	}
	
	public List<FeedDto> getfollowFeed(int userid){
		
		return  dao.getfollowFeed(userid);
	}
	
	
	/* 해시태그 부분 */
	
	//해시태그 삭제
	public void deletehashtag(int seq) {
		
		dao.deletehashtag(seq);
	}
	
	//해시태그 추가
	public void addHashtag(HashtagDto dto) {
		
		dao.addHashtag(dto);
	}
	
	
	/* 피드에 해당하는 해시태그 불러오기 */
	public List<String> getHashtag(int seq){
		
		return dao.getHashtag(seq);
	}
}
