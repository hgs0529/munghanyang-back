package mul.com.sns.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.springframework.web.socket.WebSocketSession;

public class RoomDto implements Serializable {

	private int seq;
	private String ownerid;
	private String userid;
	private String created_at;
	
	
	public RoomDto() {
	}


	public RoomDto(int seq, String ownerid, String userid, String created_at) {
		super();
		this.seq = seq;
		this.ownerid = ownerid;
		this.userid = userid;
		this.created_at = created_at;
	}


	public int getSeq() {
		return seq;
	}


	public void setSeq(int seq) {
		this.seq = seq;
	}


	public String getOwnerid() {
		return ownerid;
	}


	public void setOwnerid(String ownerid) {
		this.ownerid = ownerid;
	}


	public String getUserid() {
		return userid;
	}


	public void setUserid(String userid) {
		this.userid = userid;
	}


	public String getCreated_at() {
		return created_at;
	}


	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	
	
	

	
}
