package mul.com.sns.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import mul.com.sns.dto.LikesDto;
import mul.com.sns.service.LikesService;

@RestController
public class LikesController {
	
	@Autowired
	LikesService likesservice;
	
	/*
	 * @RequestMapping(value = "/heart", method = RequestMethod.POST) //produces =
	 * "application/json" public int heart(LikesDto dto) throws Exception {
	 * 
	 * 
	 * // int photolike = likesservice.countLikes(likesdto); 좋아요 수
	 * 
	 * if(heart >= 1) { likesservice.deleteLikes(likesdto); heart = 0; } else {
	 * likesservice.giveLikes(likesdto); heart = 1;
	 * 
	 * } return heart; }
	 */
	
	/*
	  @RequestMapping(value = "/deletelikes", method = RequestMethod.GET) public
	  String deletelikes(LikesDto likesdto) {
	  System.out.println("LikesController deletelikes()");
	  
	  boolean b = likesservice.deleteLikes(likesdto); if(b == true) { return "OK";
	  }else { return "NO"; } }
	 */
	
	
	
	
	
	
	
}
