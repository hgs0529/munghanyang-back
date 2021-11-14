package mul.com.sns.dto;

import java.io.Serializable;

public class FollowDto implements Serializable{

	private int seq;
	private int follow_give;
	private int follow_recv;
	
	public FollowDto() {
		// TODO Auto-generated constructor stub
	}

	public FollowDto(int seq, int follow_give, int follow_recv) {
		super();
		this.seq = seq;
		this.follow_give = follow_give;
		this.follow_recv = follow_recv;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public int getFollow_give() {
		return follow_give;
	}

	public void setFollow_give(int follow_give) {
		this.follow_give = follow_give;
	}

	public int getFollow_recv() {
		return follow_recv;
	}

	public void setFollow_recv(int follow_recv) {
		this.follow_recv = follow_recv;
	}

	@Override
	public String toString() {
		return "FollowDto [seq=" + seq + ", follow_give=" + follow_give + ", follow_recv=" + follow_recv + "]";
	}
	
	
}
