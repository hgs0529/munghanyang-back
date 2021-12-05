package mul.com.sns.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.springframework.web.socket.WebSocketSession;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ChatDto implements Serializable {

	private int seq;
	private int userid;
	private String cdate;
	private int newmessagecount;
	private String recentmessage;
	private String avatar;
	private String nickname;
	
	
}
