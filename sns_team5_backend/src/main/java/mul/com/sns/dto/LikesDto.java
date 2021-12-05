package mul.com.sns.dto;

import java.io.Serializable;

public class LikesDto implements Serializable {

	private int seq;
	private int userid;
	private int photoid;
	private int commentid;
	
	public LikesDto() {
		// TODO Auto-generated constructor stub
	}

	public LikesDto(int seq, int userid, int photoid, int commentid) {
		super();
		this.seq = seq;
		this.userid = userid;
		this.photoid = photoid;
		this.commentid = commentid;
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

	public int getCommentid() {
		return commentid;
	}

	public void setCommentid(int commentid) {
		this.commentid = commentid;
	}

	@Override
	public String toString() {
		return "LikesDto [seq=" + seq + ", userid=" + userid + ", photoid=" + photoid + ", commentid=" + commentid
				+ "]";
	}

	
	
}
