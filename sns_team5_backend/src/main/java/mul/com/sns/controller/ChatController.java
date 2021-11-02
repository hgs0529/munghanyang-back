package mul.com.sns.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/chat")
public class ChatController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public void getChat() {
		System.out.println("ChatController Chat GET()");
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET) 
		public String id(@PathVariable("id") String id) {
			return id;
		}
}
