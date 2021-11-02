package mul.com.sns.dto;

import java.io.Serializable;

public class ChatDto implements Serializable {

	private static final long serialVersionUID = 1L;
	private int seq;
	private String userid;
	private int roomid;
	private String content;
	private String file;
	private String created_at;
	
	public ChatDto() {
	}

	public ChatDto(int seq, String userid, int roomid, String content, String file, String created_at) {
		super();
		this.seq = seq;
		this.userid = userid;
		this.roomid = roomid;
		this.content = content;
		this.file = file;
		this.created_at = created_at;
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

	public int getRoomid() {
		return roomid;
	}

	public void setRoomid(int roomid) {
		this.roomid = roomid;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "ChatDto [seq=" + seq + ", userid=" + userid + ", roomid=" + roomid + ", content=" + content + ", file="
				+ file + ", created_at=" + created_at + "]";
	}

	
	
	
}
