package mul.com.sns;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class ChatHandler extends TextWebSocketHandler {

	private static List<WebSocketSession> list = new ArrayList<>();
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		String payload = message.getPayload();
		System.out.println("payload: " + payload);
		
		for(WebSocketSession sess : list) {
			sess.sendMessage(message);
		}
	}

	/* 클라이언트 접속시 */
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		
		list.add(session);
		System.out.println(session + " 클라이언트 접속");
	}

	
	/* 클라이언트 접속 해제시 */
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		
		list.remove(session);
		System.out.println(session + " 클라이언트 접속해제");
	}
	
	
}
