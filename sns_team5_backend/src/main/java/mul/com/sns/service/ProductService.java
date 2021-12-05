package mul.com.sns.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mul.com.sns.dao.ProductDao;
import mul.com.sns.dto.CommentDto;
import mul.com.sns.dto.PageParam;
import mul.com.sns.dto.ProductCategoryDto;
import mul.com.sns.dto.ProductDto;
import mul.com.sns.dto.ProductOptionDto;

@Service
@Transactional
public class ProductService {

	@Autowired
	ProductDao dao;
	
	public List<ProductDto> getallProduct() {
		
		return dao.getallProduct();
	}
	
	public List<ProductDto> orderProduct(PageParam param){
		
		return dao.orderProduct(param); 
	}
	
	public List<ProductDto>categoryProduct(PageParam param){
		
		return dao.categoryProduct(param);
	}
	
	public ProductDto getProduct(PageParam param) {
		
		return dao.getProduct(param);
	}
	
	public List<ProductDto> searchProduct(String search) {
		
		return dao.searchProduct(search);
	}
	
	public int addProduct(ProductDto dto) {
		
		return dao.addProduct(dto);
	}
	
	public int recentlyProductSeq() {
		
		return dao.recentlyProductSeq();
	}
	
	public void addProductOption(ProductOptionDto optdto) {
		
		dao.addProductOption(optdto);
	}
	
	public int getProductSeq(ProductDto dto) {
		
		return dao.getProductSeq(dto);
	}
	
	
	public List<ProductCategoryDto> dogorcatCategory(int dogorcat){
		
		return dao.dogorcatCategory(dogorcat);
	}
	
	public List<ProductCategoryDto> detailCategory(int catecode){
		
		return dao.detailCategory(catecode);
	}
	
	public ProductDto getProductDetail(PageParam param) {
		
		return dao.getProductDetail(param);
		
	}
	
	public List<ProductOptionDto> getProductOption(int productid){
		
		return dao.getProductOption(productid);
	}
	
	public List<CommentDto> getProductComment(int productid){
		
		return dao.getProductComment(productid);
	}
	
	public String getCategoryByCatecode(int catecode) {
		return dao.getCategoryByCatecode(catecode);
	}
	
}
