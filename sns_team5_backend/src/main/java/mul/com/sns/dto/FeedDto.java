package mul.com.sns.dto;

import java.io.Serializable;

public class FeedDto implements Serializable {

	private int seq;
	private int userid;
	private String file;
	private String content;
	private String created_at;
	private String p_link;
	private String update_at;
	private int productid;
	
	public FeedDto() {
	}

	public FeedDto(int seq, int userid, String file, String content, String created_at, String p_link, String update_at,
			int productid) {
		super();
		this.seq = seq;
		this.userid = userid;
		this.file = file;
		this.content = content;
		this.created_at = created_at;
		this.p_link = p_link;
		this.update_at = update_at;
		this.productid = productid;
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

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public String getP_link() {
		return p_link;
	}

	public void setP_link(String p_link) {
		this.p_link = p_link;
	}

	public String getUpdate_at() {
		return update_at;
	}

	public void setUpdate_at(String update_at) {
		this.update_at = update_at;
	}

	public int getProductid() {
		return productid;
	}

	public void setProductid(int productid) {
		this.productid = productid;
	}

	@Override
	public String toString() {
		return "FeedDto [seq=" + seq + ", userid=" + userid + ", file=" + file + ", content=" + content
				+ ", created_at=" + created_at + ", p_link=" + p_link + ", update_at=" + update_at + ", productid="
				+ productid + "]";
	}
	
	
		

	
}
