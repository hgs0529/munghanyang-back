package mul.com.sns.dao;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import mul.com.sns.dto.CommentDto;

@Mapper
@Repository
public interface CommentDao extends Serializable {

	//불러오기
	List<CommentDto> getComment(int photoid);
	
	//댓글 갯수세기
	int countComment(int photoid);

	//대댓글 갯수세기
	int countAnswerComment(int ref);
	
	//댓글 추가
	int addComment(CommentDto com);
	
	//대댓글 추가
	int addAnswerComment(CommentDto com);
	
	//댓글 삭제
	boolean deleteComment(CommentDto com);
	
}
