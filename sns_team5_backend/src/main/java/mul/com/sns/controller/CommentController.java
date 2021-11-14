package mul.com.sns.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import mul.com.sns.dto.CommentDto;
import mul.com.sns.service.CommentService;

@RestController
@RequestMapping(value = "/comments")
public class CommentController {
	
	@Autowired
	CommentService service;
	
	//피드에 따라 댓글 대댓글 다 불러오기
	@GetMapping(value = "/all")
	public List<CommentDto> getComment(int photoid){
		System.out.println("CommentController getComment GET()");
		
		List<CommentDto> list = service.getComment(photoid);
		for(CommentDto com : list) {
			System.out.println(com.toString());
		}
		
		return list;

	}
	
	//피드 내 댓글 갯수
	@GetMapping(value = "/count")
	public int countComment(int photoid) {
		System.out.println("CommentController countComment GET()");
			
		return service.countComment(photoid);
	}
	
	//댓글 내 대댓글 갯수
	@GetMapping(value = "/answer/count")
	public int countAnswerComment(int ref) {
		System.out.println("CommentController countAnswerComment GET()");
		
		return service.countAnswerComment(ref);
	}	
	
	//댓글 추가
	@PostMapping(value = "/{id}")
	public String addComment(@PathVariable("id") CommentDto com) {
		System.out.println("CommentController addComment POST()");
		
		return addComment(com);
			
	}
	
	//대댓글 추가
	@PostMapping(value = "/{parent}")
	public String addAnswerComment(@PathVariable("parent") CommentDto com) {
		System.out.println("CommentController addAnswerComment POST()");
		
		return addAnswerComment(com);
	}
	
	//댓글 삭제
	@DeleteMapping(value = "/{id}")
	public String deleteComment(@PathVariable("id") String id) {
		System.out.println("CommentController deleteComment POST()");
			
			return deleteComment(id);	
		}
	
	
}
