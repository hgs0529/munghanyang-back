package mul.com.sns.dto;

import java.io.Serializable;


public class FeedDto implements Serializable {

	private int seq;
	private int userid;
	private int groupno;
	private String title;
	private String cate;
	private String file;
	private String content;
	private String created;
	private String p_link;
	private String updated;
	private int productid;
	private int readcount;
	private boolean isliked;
	private int likecount;
	private String avatar;
	private String nickname;
	private String hashtag;
	private int order;
	private int commentcount;
	private String recentcomment;
	private String commentwriter;
	private boolean isfollow;
	
	public FeedDto() {
	}

	public FeedDto(int seq, int userid, int groupno, String title, String cate, String file, String content,
			String created, String p_link, String updated, int productid, int readcount, boolean isliked, int likecount,
			String avatar, String nickname, String hashtag, int order, int commentcount, String recentcomment,
			String commentwriter, boolean isfollow) {
		super();
		this.seq = seq;
		this.userid = userid;
		this.groupno = groupno;
		this.title = title;
		this.cate = cate;
		this.file = file;
		this.content = content;
		this.created = created;
		this.p_link = p_link;
		this.updated = updated;
		this.productid = productid;
		this.readcount = readcount;
		this.isliked = isliked;
		this.likecount = likecount;
		this.avatar = avatar;
		this.nickname = nickname;
		this.hashtag = hashtag;
		this.order = order;
		this.commentcount = commentcount;
		this.recentcomment = recentcomment;
		this.commentwriter = commentwriter;
		this.isfollow = isfollow;
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

	public int getGroupno() {
		return groupno;
	}

	public void setGroupno(int groupno) {
		this.groupno = groupno;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCate() {
		return cate;
	}

	public void setCate(String cate) {
		this.cate = cate;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public String getP_link() {
		return p_link;
	}

	public void setP_link(String p_link) {
		this.p_link = p_link;
	}

	public String getUpdated() {
		return updated;
	}

	public void setUpdated(String updated) {
		this.updated = updated;
	}

	public int getProductid() {
		return productid;
	}

	public void setProductid(int productid) {
		this.productid = productid;
	}

	public int getReadcount() {
		return readcount;
	}

	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}

	public boolean isIsliked() {
		return isliked;
	}

	public void setIsliked(boolean isliked) {
		this.isliked = isliked;
	}

	public int getLikecount() {
		return likecount;
	}

	public void setLikecount(int likecount) {
		this.likecount = likecount;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getHashtag() {
		return hashtag;
	}

	public void setHashtag(String hashtag) {
		this.hashtag = hashtag;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public int getCommentcount() {
		return commentcount;
	}

	public void setCommentcount(int commentcount) {
		this.commentcount = commentcount;
	}

	public String getRecentcomment() {
		return recentcomment;
	}

	public void setRecentcomment(String recentcomment) {
		this.recentcomment = recentcomment;
	}

	public String getCommentwriter() {
		return commentwriter;
	}

	public void setCommentwriter(String commentwriter) {
		this.commentwriter = commentwriter;
	}

	public boolean isIsfollow() {
		return isfollow;
	}

	public void setIsfollow(boolean isfollow) {
		this.isfollow = isfollow;
	}

	@Override
	public String toString() {
		return "FeedDto [seq=" + seq + ", userid=" + userid + ", groupno=" + groupno + ", title=" + title + ", cate="
				+ cate + ", file=" + file + ", content=" + content + ", created=" + created + ", p_link=" + p_link
				+ ", updated=" + updated + ", productid=" + productid + ", readcount=" + readcount + ", isliked="
				+ isliked + ", likecount=" + likecount + ", avatar=" + avatar + ", nickname=" + nickname + ", hashtag="
				+ hashtag + ", order=" + order + ", commentcount=" + commentcount + ", recentcomment=" + recentcomment
				+ ", commentwriter=" + commentwriter + ", isfollow=" + isfollow + "]";
	}

	

	

	

	
}
