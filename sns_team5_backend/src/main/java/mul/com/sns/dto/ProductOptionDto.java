package mul.com.sns.dto;

import java.io.Serializable;

public class ProductOptionDto implements Serializable{

	private int seq;
	private int productid;
	private String title;
	private String subtitle;
	private int optionprice;
	
	public ProductOptionDto() {
	}

	public ProductOptionDto(int seq, int productid, String title, String subtitle, int optionprice) {
		super();
		this.seq = seq;
		this.productid = productid;
		this.title = title;
		this.subtitle = subtitle;
		this.optionprice = optionprice;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public int getProductid() {
		return productid;
	}

	public void setProductid(int productid) {
		this.productid = productid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public int getOptionprice() {
		return optionprice;
	}

	public void setOptionprice(int optionprice) {
		this.optionprice = optionprice;
	}

	@Override
	public String toString() {
		return "ProductOptionDto [seq=" + seq + ", productid=" + productid + ", title=" + title + ", subtitle="
				+ subtitle + ", optionprice=" + optionprice + "]";
	}
	
}
