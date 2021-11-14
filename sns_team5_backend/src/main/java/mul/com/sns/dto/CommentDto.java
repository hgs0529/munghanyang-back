package mul.com.sns.dto;

public class CommentDto {

	private int seq;		// 댓글 넘버
	private int userid;		// 작성자 넘버
	private int photoid;	// 글 넘버
	
	private int likes;		// 좋아요
	
	private String content;	// 댓글 내용

	private int ref;        // 대댓글이 달리는 댓글의 번호
	private int step;		// 대댓글의 시퀀스
	
	private String cdate;	// 댓글 작성날짜
	
	public CommentDto() {
	}

	public CommentDto(int seq, int userid, int photoid, int likes, String content, int ref, int step, String cdate) {
		super();
		this.seq = seq;
		this.userid = userid;
		this.photoid = photoid;
		this.likes = likes;
		this.content = content;
		this.ref = ref;
		this.step = step;
		this.cdate = cdate;
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

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getRef() {
		return ref;
	}

	public void setRef(int ref) {
		this.ref = ref;
	}

	public int getStep() {
		return step;
	}

	public void setStep(int step) {
		this.step = step;
	}

	public String getCdate() {
		return cdate;
	}

	public void setCdate(String cdate) {
		this.cdate = cdate;
	}

	@Override
	public String toString() {
		return "CommentDto [seq=" + seq + ", userid=" + userid + ", photoid=" + photoid + ", likes=" + likes
				+ ", content=" + content + ", ref=" + ref + ", step=" + step + ", cdate=" + cdate + "]";
	}

	
}
