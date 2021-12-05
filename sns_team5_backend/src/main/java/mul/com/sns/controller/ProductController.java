package mul.com.sns.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import mul.com.sns.dto.CommentDto;
import mul.com.sns.dto.PageParam;
import mul.com.sns.dto.ProductCategoryDto;
import mul.com.sns.dto.ProductDto;
import mul.com.sns.dto.ProductOptionDto;
import mul.com.sns.service.ProductService;

@RestController
@RequestMapping(value = "/product")
public class ProductController {

	@Autowired
	ProductService pservice;
	
	/* 모든상품 가져오기 */
	@RequestMapping(value = "/getall", method = RequestMethod.GET)
	public List<ProductDto> getAllProduct(){
		System.out.println("ProductController getProduct()");
		
		List<ProductDto> list = pservice.getallProduct();
		
		return list;
	}
	
	/* 메인에 종류별 상품 지정 갯수만큼 가져오기 */
	@RequestMapping(value = "/orderproduct", method = RequestMethod.GET)
	public List<ProductDto> orderProduct(PageParam param){
		
		System.out.println("ProductController orderProduct()");
		
		Date nowDate;
        Calendar cal = java.util.Calendar.getInstance();
        cal.add(cal.DATE, -7);// 일주일 빼기
        nowDate = cal.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = format.format(nowDate);
        
        param.setCreated(str);
		
		List<ProductDto> list = pservice.orderProduct(param);
		
		return list;
	}

	
	/* 카테고리별 상품 가져오기 */
	@RequestMapping(value = "/categoryproduct", method = RequestMethod.GET)
	public List<ProductDto> categoryProduct(PageParam param){
		System.out.println("ProductController categoryProduct()");
		
		int page = param.getPage();
		int start = (page * 30) + 1;
		int end = (page + 1) * 30;
		
		param.setStart(start);
		param.setEnd(end);		
		System.out.println(param.toString());
		List<ProductDto> list = pservice.categoryProduct(param);
		
		return list;
	}
	
	/* 상품 상세정보 가져오기 */
	@RequestMapping(value = "/getproduct", method = RequestMethod.GET)
	public ProductDto getProduct(PageParam param) {
		System.out.println("ProductController getProduct()");
		
		ProductDto dto = pservice.getProduct(param);
		
		return dto;
	}
	
	/* 키워드로 검색한 모든 관련 상품 가져오기 */
	@RequestMapping(value = "/getsearch", method = RequestMethod.GET)
	public List<ProductDto> searchProduct(String search){
		
		System.out.println("ProductController searchProduct()");
		
		List<ProductDto> list = pservice.searchProduct(search);
				
		return list;
	}
	
	/* 상품 추가하기 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addProduct(@RequestParam("uploadFile")
							MultipartFile uploadFile, 
							HttpServletRequest req,
							ProductDto dto,String optionstring) {
		
		System.out.println("ProductController addProduct()");
		System.out.println(dto.getContent());
		System.out.println(optionstring);
		String uploadPath = req.getServletContext().getRealPath("/upload/product");
		// 폴더
		//String uploadPath = "d:\\temp";
		String productdata = "";
		String filename = uploadFile.getOriginalFilename();		
		
		String newfilename = ""; // 새로운 파일명
		String filepost = "";	// 확장자명
		
		if(filename.indexOf(".") >= 0) { // 확장자명이 있는 경우
			filepost = filename.substring( filename.indexOf(".") );	// abc.txt
			newfilename = new Date().getTime() + filepost;			
		}else {							// 확장자명이 없는 경우
			newfilename = new Date().getTime() + ".back";	
		}
		String filepath = uploadPath + File.separator + newfilename;
		//productdata += "/productupload/" + newfilename;
		productdata += "/upload/product/" + newfilename;
		
		try {
			BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(new File(filepath)));
			os.write(uploadFile.getBytes());
			os.close();
			
			// DB input
			
		} catch (Exception e) {			
			e.printStackTrace();			
			return "file upload fail";
		}
		
		//product
		dto.setThumbnail(productdata);
		int n1 = pservice.addProduct(dto);	
		
		int seq = pservice.recentlyProductSeq();
		
		
		if(optionstring != null && !optionstring.equals("")) {
			String[] option = optionstring.split("\\?");
			for(int i=0; i<option.length; i++) {
				String[] opt = option[i].split("-");
				
				ProductOptionDto optdto = new ProductOptionDto();
				optdto.setTitle(opt[0]);
				optdto.setSubtitle(opt[1]);
				optdto.setOptionprice(Integer.parseInt(opt[2]));
				optdto.setProductid(seq);
				pservice.addProductOption(optdto);
			}
		}
		
		
		
		if( n1 > 0 ) {
			return "YES";
		}else {
			return "NO";
		}
		
	}
	
	/* summernote 이미지 업로드 후 경로이름 반환해주기 */
	@RequestMapping(value = "/uploadSummernoteImage", method = RequestMethod.POST)
	public String summerNoteFileUpload(@RequestParam("file")
										MultipartFile uploadFile, 
										HttpServletRequest req) {
		System.out.println("ProductController summerNoteFileUpload()");

		// 경로
		// server : 3000
		String uploadPath = req.getServletContext().getRealPath("/upload/product");
		// 폴더
		//String uploadPath = "d:\\temp";

		// 파일명 취득
		String filename = uploadFile.getOriginalFilename();
							
		String newfilename = ""; // 새로운 파일명
		String filepost = "";	// 확장자명
		String summerdata = "";
		
		if(filename.indexOf(".") >= 0) { // 확장자명이 있는 경우
			filepost = filename.substring( filename.indexOf(".") );	// abc.txt
			newfilename = new Date().getTime() + filepost;			
		}else {							// 확장자명이 없는 경우
			newfilename = new Date().getTime() + ".back";	
		}
		String filepath = uploadPath + File.separator + newfilename;
		
		//summerdata += "/productupload/" + newfilename;
		summerdata += "/upload/product/" + newfilename;
		
		try {
			BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(new File(filepath)));
			os.write(uploadFile.getBytes());
			os.close();
			
			// DB input
			
		} catch (Exception e) {			
			e.printStackTrace();			
			return "file upload fail";
		}

		return summerdata;
	}

	
	/* 강아지 또는 고양이 하위 카데고리 가져오기 */
	@RequestMapping(value = "/dogorcatcategory", method = RequestMethod.GET)
	public List<ProductCategoryDto> dogorcatCategory(int dogorcat){
		System.out.println("ProductController dogorcatCategory()");
		
		List<ProductCategoryDto> list = pservice.dogorcatCategory(dogorcat);
		
		return list;
	}
	
	/* 메인카테고리 별 하위 카데고리 가져오기 */
	@RequestMapping(value = "/detailcategory", method = RequestMethod.GET)
	public List<ProductCategoryDto> detailCategory(int catecode){
		System.out.println("ProductController detailCategory()");
		
		List<ProductCategoryDto> list = pservice.detailCategory(catecode);
		
		return list;
	}
	@RequestMapping(value = "/getproductdetail", method = RequestMethod.GET)
	public ProductDto getProductDetail(PageParam param) {
		System.out.println("ProductController getProductDetail()");
		
		return pservice.getProductDetail(param);
	}
	
	@RequestMapping(value = "/getoption", method = RequestMethod.GET)
	public List<ProductOptionDto> getProductOption(int productid) {
		System.out.println("ProductController getProductOption()");
		
		List<ProductOptionDto> list = pservice.getProductOption(productid); 
		
		return list; 
	}
	
	
	@RequestMapping(value = "/getcomment", method = RequestMethod.GET)
	public List<CommentDto> getProductComment(int productid){
		System.out.println("ProductController getProductComment()");
		
		List<CommentDto> list = pservice.getProductComment(productid);
				
		return list;
	}
	
	@RequestMapping(value = "/getcatename", method = RequestMethod.GET)
	public String getCateName(int catecode) {
		System.out.println("ProductController getCateName()");
		
		return pservice.getCategoryByCatecode(catecode);
	}
	
}
