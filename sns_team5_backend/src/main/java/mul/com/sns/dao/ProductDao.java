package mul.com.sns.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import mul.com.sns.dto.CommentDto;
import mul.com.sns.dto.PageParam;
import mul.com.sns.dto.ProductCategoryDto;
import mul.com.sns.dto.ProductDto;
import mul.com.sns.dto.ProductOptionDto;

@Mapper
@Repository
public interface ProductDao {

	List<ProductDto>getallProduct();
	
	List<ProductDto>orderProduct(PageParam param);
	
	List<ProductDto>categoryProduct(PageParam param);
	
	ProductDto getProduct(PageParam param);
	
	List<ProductDto>searchProduct(String search);
	
	int addProduct(ProductDto dto);
	
	int recentlyProductSeq();
	
	void addProductOption(ProductOptionDto optdto);
	
	int getProductSeq(ProductDto dto);
	
	List<ProductCategoryDto> dogorcatCategory(int dogorcat);

	List<ProductCategoryDto> detailCategory(int catecode);
	
	ProductDto getProductDetail(PageParam param);
	
	List<ProductOptionDto> getProductOption(int productid);
	
	List<CommentDto> getProductComment(int productid);
	
	String getCategoryByCatecode(int catecode);
	
}
