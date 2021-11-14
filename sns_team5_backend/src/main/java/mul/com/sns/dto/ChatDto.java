package mul.com.sns.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.springframework.web.socket.WebSocketSession;

public class ChatDto implements Serializable {

	private int seq;
	private String userid;
	private String cdate;
	private int newmessagecount;
	private String recentmessage;
	
	public ChatDto() {
	}

	public ChatDto(int seq, String userid, String cdate, int newmessagecount, String recentmessage) {
		super();
		this.seq = seq;
		this.userid = userid;
		this.cdate = cdate;
		this.newmessagecount = newmessagecount;
		this.recentmessage = recentmessage;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getCdate() {
		return cdate;
	}

	public void setCdate(String cdate) {
		this.cdate = cdate;
	}

	public int getNewmessagecount() {
		return newmessagecount;
	}

	public void setNewmessagecount(int newmessagecount) {
		this.newmessagecount = newmessagecount;
	}

	public String getRecentmessage() {
		return recentmessage;
	}

	public void setRecentmessage(String recentmessage) {
		this.recentmessage = recentmessage;
	}

	@Override
	public String toString() {
		return "ChatDto [seq=" + seq + ", userid=" + userid + ", cdate=" + cdate + ", newmessagecount="
				+ newmessagecount + ", recentmessage=" + recentmessage + "]";
	}

	
	
}
