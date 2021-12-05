package mul.com.sns.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {

	private int seq;		// 댓글 넘버
	private int userid;		// 작성자 넘버
	private int photoid;	// 글 넘버
	private int commentid;	// 댓글 넘버
	private int productid;
	private String content;	// 댓글 내용
	
	private int parent; 
	private String refuser; 
	private boolean isliked;
	private boolean isfollow;
	private int likecount;
	private int login;
	
	private String cdate;	// 댓글 작성날짜

	private String avatar; // 작성자 프로필사진 user 테이블
	private String nickname; // 작성자 닉네임 user 테이블
	private String file;
	private double star;

	
}
