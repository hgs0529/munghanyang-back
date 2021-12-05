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
public class ProductDto implements Serializable {

	private int seq;
	private String name;
	private String thumbnail;
	private String content;
	private int price;
	private String created;
	private int del;
	private int discount;
	private int categorycode;
	private String catename;
	private int readcount;
	private boolean isliked;
	private int likecount;
	private double star;
	private double starcount;
	private int reviewcount;
	
}
