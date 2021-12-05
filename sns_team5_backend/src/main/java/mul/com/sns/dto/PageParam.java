package mul.com.sns.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PageParam implements Serializable{

	private int groupno;   // feed
	private String cate;   // feed
	private int page;	   // feed,product
	private int userid;    // feed,product
	private int start;     // feed,product
	private int end;	   // feed,product
	private int order;     // feed
	private int category;  // product
	private int seq;	   // product
	private String created;

	
}
