package mul.com.sns.dto;

public class HashtagDto {

	private int seq;		// 해쉬 넘버
	private int userid;		// 작성자 넘버
	private int photoid;	// 글 넘버
	private String content;	// 해쉬 내용
	
	public HashtagDto() {
	}

	public HashtagDto(int seq, int userid, int photoid, String content) {
		super();
		this.seq = seq;
		this.userid = userid;
		this.photoid = photoid;
		this.content = content;
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

	public int getPhotoid() {
		return photoid;
	}

	public void setPhotoid(int photoid) {
		this.photoid = photoid;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "HashtagDto [seq=" + seq + ", userid=" + userid + ", photoid=" + photoid + ", content=" + content + "]";
	}
	
	
	
}
