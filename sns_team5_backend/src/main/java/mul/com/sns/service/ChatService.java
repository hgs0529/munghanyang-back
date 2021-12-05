package mul.com.sns.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mul.com.sns.dao.ChatDao;
import mul.com.sns.dto.MessageDto;
import mul.com.sns.dto.ChatDto;

@Service
@Transactional
public class ChatService {
	
	@Autowired
	ChatDao dao;
	
	public List<ChatDto> getChatList(int userid) {
		return dao.getChatList(userid);
	}
	
	public int getChatId(int userid) {
		return dao.getChatId(userid);
	}

	public List<ChatDto> getAllChatList() {
		return dao.getAllChatList();
	}
	
	public boolean addChat(int seq) {
		int n = dao.addChat(seq);
		return n > 0 ? true : false;
	}
	
	public List<MessageDto> getMessageList(int seq) {
		return dao.getMessageList(seq);
	}
	
	public boolean addMessage(MessageDto dto) {
		int n = dao.addMessage(dto);
		return n > 0 ? true : false;
	}
	
	public MessageDto getMessage(int seq) {
		return dao.getMessage(seq);
	}
	
	public boolean deleteChat(int seq) {
		int n = dao.deleteMessage(seq);
		if(n > 0) {
			n = dao.deleteChat(seq);
		}
		return n > 0 ? true : false;
	}
	
	public boolean setReadMessage(MessageDto dto) {
		int n = dao.setReadMessage(dto);
		return n > 0 ? true : false;
	}
}
