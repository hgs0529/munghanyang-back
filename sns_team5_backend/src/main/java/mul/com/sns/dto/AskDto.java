package mul.com.sns.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AskDto {

	private int seq;
	
	private int userid;
	private int productid;
	
	private String content;
	private String wdate;
	private int askstatus;	// 질문답변 구분 (질문:1, 답변:2)
	private int ref;			// 답변을 질문 seq에 연결
	
	private String pname;
	private String askname;
	private boolean answered;	// 답변여부 확인
	
	private String nickname;
	private String avatar;
	private String asktype;

}
