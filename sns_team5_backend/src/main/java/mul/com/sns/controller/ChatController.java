package mul.com.sns.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import mul.com.sns.dto.MessageDto;
import mul.com.sns.dto.ChatDto;
import mul.com.sns.service.ChatService;

@RestController
@RequestMapping(value = "/chat")
public class ChatController {
	
	@Autowired
	ChatService service;
	
	@Autowired
	SimpMessagingTemplate template;
	
	@GetMapping(value = "/manager")
	public List<ChatDto> getAllChat(int auth) {
		System.out.println("ChatController ChatAll GET()");
		
		if(auth != 3) {
			return null;
		}
		List<ChatDto> list = service.getAllChatList();
		for (ChatDto chatDto : list) {
			System.out.println(chatDto.toString());
		}
		return list;
	}
	
	@GetMapping(value = "/messages")
	public List<MessageDto> getMessages(MessageDto dto) {
		System.out.println("ChatController Messages GET()");
		List<MessageDto> list = service.getMessageList(dto.getChatid());
		for (MessageDto MessageDto : list) {
			System.out.println(MessageDto.toString());
		}
		boolean b = service.setReadMessage(dto);
		if(!b) {
			System.out.println("메시지 읽음처리 실패");
		}
		return list;
	}
	
	@GetMapping(value = "/all")
	public List<ChatDto> getChat(int userid) {
		System.out.println("ChatController Chat GET()");
		List<ChatDto> list = service.getChatList(userid);
		return list;
	}
	
	@GetMapping(value = "/add")
	public int addChat(ChatDto chat) {
		System.out.println("ChatController Chat GET()");
		boolean b = service.addChat(chat.getUserid());
		int chatid = 0;
		if(b) {
			chatid = service.getChatId(chat.getUserid());
			if(chatid <= 0) {
				chatid = 0;
			}
		}
		MessageDto firstMessage = new MessageDto();
		firstMessage.setUserid(1); // 처음 방을 생성하면 관리자 계정으로 첫 챗팅을 보냄
		firstMessage.setChatid(chatid);
		firstMessage.setContent("안녕하세요 문의사항을 남겨주세요.");
		
		service.addMessage(firstMessage);
		return chatid;
	}
	
	@PostMapping(value = "/upload")
	public String fileupload( 	@RequestParam("uploadFile")
								MultipartFile uploadFile,
								String chatid,
								String userid,
								HttpServletRequest req) {
		System.out.println("PdsController fileupload()");
		
		
		MessageDto dto = new MessageDto();
		dto.setUserid(Integer.parseInt(userid));
		dto.setChatid(Integer.parseInt(chatid));
		
		// 경로
		// server : 3000
		String uploadPath = req.getServletContext().getRealPath("/upload/chat");
		// 폴더
		// String uploadPath = "c:\\temp1";
		
		// 파일명 취득
		String filename = uploadFile.getOriginalFilename();
		String newfilename = "";
		String filepost = "";	// 확장자명
		if(filename.indexOf('.') >= 0) { // 확장자명이 있는 경우
			filepost = filename.substring( filename.indexOf(".") );	// abc.txt
			newfilename = new Date().getTime() + filepost;			
		}
		if (filename.equals("") || filename == null) {							// 확장자명이 없는 경우
			newfilename = "";
		}
		String filepath = uploadPath + File.separator + newfilename;
		
		System.out.println(filepath);
		System.out.println("filename:" + filename);
		System.out.println("newfilename:" + newfilename);
		
		try {
			BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(new File(filepath)));
			os.write(uploadFile.getBytes());
			os.close();
			LocalDateTime now = LocalDateTime.now();
			String formatedNow = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
			dto.setFile("/upload/chat/" + newfilename);	
			dto.setWdate(formatedNow);
			
			boolean b = service.addMessage(dto);
			if(!b) {
				System.out.println("채팅 저장 실패");
				return "ok";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return "ok";
		}
		template.convertAndSend("http://localhost:3000/sub/chat/room/" + dto.getChatid(), dto);
		return "ok";
	}

}
