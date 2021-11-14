package mul.com.sns.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mul.com.sns.dao.CommentDao;
import mul.com.sns.dto.CommentDto;


@Service
@Transactional
public class CommentService {
	
	@Autowired
	CommentDao dao;
	
	public List<CommentDto> getComment(int photoid){
		return dao.getComment(photoid);
	}
	
	public int countComment(int photoid){
		return dao.countComment(photoid);
	}
	
	public int countAnswerComment(int ref){
		return dao.countAnswerComment(ref);
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
		return dao.deleteComment(com);
	}
	

}
