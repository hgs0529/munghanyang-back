package mul.com.sns.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import lombok.RequiredArgsConstructor;
import mul.com.sns.dto.MessageDto;
import mul.com.sns.service.ChatService;

@Controller
@RequiredArgsConstructor
public class MessageContoller {
	
	@Autowired
	ChatService service;

	private final SimpMessagingTemplate template; // 특정 Broker로 메세지를 전달
	
	
	@MessageMapping(value = "/chat/message")
	public void mesasge(MessageDto message) {
		System.out.println("ChatContoller message()");
		
		LocalDateTime now = LocalDateTime.now();
		boolean b = service.addMessage(message);
		if(!b) {
			System.out.println("채팅 저장 실패");
		}
		String formatedNow = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		message.setWdate(formatedNow);
		template.convertAndSend("http://localhost:3000/sub/chat/room/" + message.getChatid(), message);
	}
}
