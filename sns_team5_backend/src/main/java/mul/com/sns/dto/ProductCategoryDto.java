package mul.com.sns.dto;

import java.io.Serializable;

public class ProductCategoryDto implements Serializable {

	private int dogorcat;
	private String catename;
	private int catecode;
	private int catecoderef;
	
	public ProductCategoryDto() {
	}

	public ProductCategoryDto(int dogorcat, String catename, int catecode, int catecoderef) {
		super();
		this.dogorcat = dogorcat;
		this.catename = catename;
		this.catecode = catecode;
		this.catecoderef = catecoderef;
	}

	public int getDogorcat() {
		return dogorcat;
	}

	public void setDogorcat(int dogorcat) {
		this.dogorcat = dogorcat;
	}

	public String getCatename() {
		return catename;
	}

	public void setCatename(String catename) {
		this.catename = catename;
	}

	public int getCatecode() {
		return catecode;
	}

	public void setCatecode(int catecode) {
		this.catecode = catecode;
	}

	public int getCatecoderef() {
		return catecoderef;
	}

	public void setCatecoderef(int catecoderef) {
		this.catecoderef = catecoderef;
	}

	@Override
	public String toString() {
		return "ProductCategoryDto [dogorcat=" + dogorcat + ", catename=" + catename + ", catecode=" + catecode
				+ ", catecoderef=" + catecoderef + "]";
	}

	
}
