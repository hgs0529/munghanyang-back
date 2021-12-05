package mul.com.sns.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import mul.com.sns.dto.CommentDto;
import mul.com.sns.dto.LikesDto;
import mul.com.sns.service.CommentService;

@RestController
@RequestMapping(value = "/comments") // 묶어주기
public class CommentController {
	
	@Autowired
	CommentService service;
	
	//피드에 따라 댓글 대댓글 다 불러오기
	@GetMapping(value = "/all")
	public List<CommentDto> getComment(CommentDto com){
		System.out.println("CommentController getComment GET()");
		
		List<CommentDto> list = service.getComment(com);
		for(CommentDto dto : list) {
			System.out.println(dto.toString());
		}
		
		return list;

	}
	
	
	//피드 내 댓글 갯수
	@GetMapping(value = "/count")
	public int countComment(int photoid) {
		System.out.println("CommentController countComment GET()");
			
		return service.countComment(photoid);
	}
	
	
	//댓글 추가
	@PostMapping(value = "/new")
	public String addComment(CommentDto com) {
		System.out.println("CommentController addComment POST()");
		
		boolean b = service.addComment(com);
		if(b == true) {
			return "OK";
		}else {
			return "NG";
		}		
	}	
	
	
	//대댓글 추가
	@PostMapping(value = "/new/answer")
	public String addAnswerComment(CommentDto com) {
		System.out.println("CommentController addAnswerComment POST()");
		
		System.out.println(com.getSeq());
		boolean b = service.addAnswerComment(com);
		if(b == true) {
			return "OK";
		}else {
			return "NG";
		}		
	}	
	
	//댓글 삭제
	@PostMapping(value = "/del")
	public String deleteComment(CommentDto com) {
		System.out.println("CommentController deleteComment POST()");
		
		List<Integer> list = service.getCommentsSeq(com.getCommentid());
		for (Integer seq : list) {
			service.deleteReplyLikes(seq);
		}
		
		
		service.deleteAllLikes(com.getCommentid());
		
		service.deleteReply(com);
		boolean b = service.deleteComment(com);
		if(b == true) {
			return "OK";
		}else {
			return "NG";
		}		
	}	
	
	@RequestMapping(value = "/likes", method = RequestMethod.POST)
	public String addLike(LikesDto dto) {
		System.out.println("CommentController addLike()");

		boolean b = service.serachLikes(dto);

		if (b) {
			boolean b2 = service.deleteLikes(dto);
			if (b2) {
				return "unlikes";
			}
		} else {
			boolean b2 = service.addLikes(dto);
			if (b2) {
				return "likes";
			}
		}

		return "NO";
	}
	
	
	@RequestMapping(value = "/delproduct", method = RequestMethod.POST)
	public String deleteProductComment(CommentDto com) {
		System.out.println("CommentController deleteProductComment()");
		
		boolean b = service.deleteProductComment(com);
		
		if(b) {
			return "OK";
		}else {
			return "NG";
		}
	}
	
	/************* 새로 추가한 부분 상품댓글 */
	@RequestMapping(value = "/addproduct", method = RequestMethod.POST)
	public String addProductComment(@RequestParam("uploadFile")
									MultipartFile uploadFile, 
									HttpServletRequest req,
									CommentDto com) {
		System.out.println("CommentController addProductComment()");
		
		String uploadPath = req.getServletContext().getRealPath("/upload/product");
		String commentdata = "";
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
		commentdata += "/upload/product/" + newfilename;
		
		try {
			BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(new File(filepath)));
			os.write(uploadFile.getBytes());
			os.close();

		} catch (Exception e) {			
			e.printStackTrace();			
			return "file upload fail";
		}
		
		com.setFile(commentdata);
		
		boolean b = service.addProductComment(com);
		
		if(b) {
			return "OK";
		}else {
			return "NG";
		}
	}

	
}
