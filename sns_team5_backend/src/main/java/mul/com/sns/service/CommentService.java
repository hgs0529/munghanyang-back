package mul.com.sns.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mul.com.sns.dao.CommentDao;
import mul.com.sns.dto.CommentDto;
import mul.com.sns.dto.LikesDto;


@Service
@Transactional
public class CommentService {
	
	@Autowired
	CommentDao dao;
	
	public List<CommentDto> getComment(CommentDto com){
		return dao.getComment(com);
	}
	
	public List<Integer> getCommentsSeq(int seq){
		return dao.getCommentsSeq(seq);
	}
	
	public List<Integer> getAllCommentsSeq(int seq){
		return dao.getAllCommentsSeq(seq);
	}
	
	
	public int countComment(int photoid){
		return dao.countComment(photoid);
	}
	
	
	public boolean addComment(CommentDto com){
		 int n = dao.addComment(com);
		
		 return n>0?true:false;
	}
	
	public boolean addAnswerComment(CommentDto com){
		int n = dao.addAnswerComment(com);
		
		return n>0?true:false;
	}
	
	public boolean deleteComment(CommentDto com){
		int n = dao.deleteComment(com);
		return n>0?true:false;
	}
	
	public boolean deleteReply(CommentDto com){
		int n = dao.deleteReply(com);
		return n>0?true:false;
	}
	
	public int getcommentLikes(int seq) {
		return dao.getcommentLikes(seq);
	}
	
	public boolean addLikes(LikesDto dto) {
		int n = dao.addLikes(dto);
		return n>0?true:false;
	}
	
	public boolean deleteLikes(LikesDto dto) {
		int n = dao.deleteLikes(dto);
		return n>0?true:false;
	}
	
	public boolean serachLikes(LikesDto dto) {
		int n = dao.serachLikes(dto);
		return n>0?true:false;
	}
	
	public boolean deleteAllLikes(int commentid) {
		int n = dao.deleteAllLikes(commentid);
		return n>0?true:false;
	}

	public boolean deleteReplyLikes(int commentid) {
		int n = dao.deleteReplyLikes(commentid);
		return n>0?true:false;
	}
	
	public boolean deleteAllComment(int seq) {
		int n = dao.deleteAllComment(seq);
		return n>0?true:false;
	}
	
	//새로 추가한 부분 상품댓글
	public boolean addProductComment(CommentDto com) {
		
		int n = dao.addProductComment(com);
		
		return n >0 ? true:false;
	}
		
	public boolean deleteProductComment(CommentDto com) {
			
		int n = dao.deleteProductComment(com);
			
		return n >0 ? true:false; 
		
	}

	
}
