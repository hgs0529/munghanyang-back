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
import mul.com.sns.dto.HashtagDto;
import mul.com.sns.service.HashtagService;

@RestController
@RequestMapping(value = "/hashtag")
public class HashtagController {

	@Autowired
	HashtagService service;
	
	//해쉬태그 불러오기
	@GetMapping(value = "/all")
	public List<HashtagDto> getHashtag(int photoid){
		System.out.println("CommentController getComment GET()");
		
		List<HashtagDto> list = service.getHashtag(photoid);
		for(HashtagDto has : list) {
			System.out.println(has.toString());
		}
		
		return list;

	}
	
	//해쉬태그 추가
	@PostMapping(value = "/add")
	public String addHashtag(HashtagDto has){
		System.out.println("HashtagController addHashtag()");
		
		boolean b = service.addHashtag(has);
		if(b == true) {
			return "OK";
		}else {
			return "NG";
		}		
	}
	

	
	
}
