package mul.com.sns.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import mul.com.sns.dto.MessageDto;
import mul.com.sns.dto.ChatDto;

@Mapper
@Repository
public interface ChatDao {
	
	List<ChatDto> getChatList(int userid);
	int getChatId(int userid);
	List<ChatDto> getAllChatList();
	int addChat(int seq);
	int deleteChat(int seq);
	
	List<MessageDto> getMessageList(int seq);
	int addMessage(MessageDto dto);
	MessageDto getMessage(int seq);
	int deleteMessage(int seq);
	int setReadMessage(MessageDto dto);
}
