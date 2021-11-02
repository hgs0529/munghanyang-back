package mul.com.sns.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "user")
public class UserController {

	@GetMapping(value = "/{userid}") 
	public void userGET(@PathVariable("userid") String userid) {
		System.out.println("UserController user GET()");
		// 유저 한명의 정보
	}
	
	
}
