package mul.com.sns.dao;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import mul.com.sns.dto.CommentDto;
import mul.com.sns.dto.LikesDto;

@Mapper
@Repository
public interface CommentDao extends Serializable {

	//불러오기
	List<CommentDto> getComment(CommentDto com);
	List<Integer> getCommentsSeq(int seq);
	List<Integer> getAllCommentsSeq(int seq);
	
	//댓글 갯수세기
	int countComment(int photoid);

	//댓글 추가
	int addComment(CommentDto com);
	
	//대댓글 추가
	int addAnswerComment(CommentDto com);
	
	//댓글 삭제
	int deleteComment(CommentDto com);
	int deleteReply(CommentDto com);
	int deleteAllComment(int seq);
	
	//좋아요 총수
	int getcommentLikes(int seq);
	
	//좋아요 추가
	int addLikes(LikesDto dto);
	
	//좋아요 삭제
	int deleteLikes(LikesDto dto);
	
	
	//좋아요 검색
	int serachLikes(LikesDto dto);
	
	//댓글삭제하면
	int deleteAllLikes(int commentid);
	int deleteReplyLikes(int commentid);
	
	//새로 추가한 부분 상품댓글
	int addProductComment(CommentDto com);
	
	int deleteProductComment(CommentDto com);

}
