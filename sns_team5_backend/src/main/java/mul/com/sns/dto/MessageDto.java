package mul.com.sns.dto;

import java.io.Serializable;

public class MessageDto implements Serializable {

	private static final long serialVersionUID = 1L;
	private int seq;
	private int userid;
	private int chatid;
	private String content;
	private String file;
	private String wdate;
	
	public MessageDto() {
	}

	public MessageDto(int seq, int userid, int chatid, String content, String file, String wdate) {
		super();
		this.seq = seq;
		this.userid = userid;
		this.chatid = chatid;
		this.content = content;
		this.file = file;
		this.wdate = wdate;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public int getChatid() {
		return chatid;
	}

	public void setChatid(int chatid) {
		this.chatid = chatid;
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

	public String getWdate() {
		return wdate;
	}

	public void setWdate(String wdate) {
		this.wdate = wdate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "ChatDto [seq=" + seq + ", userid=" + userid + ", chatid=" + chatid + ", content=" + content + ", file="
				+ file + ", wdate=" + wdate + "]";
	}

	
	
	
}
