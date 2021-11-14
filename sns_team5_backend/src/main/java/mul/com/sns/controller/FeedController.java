package mul.com.sns.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import mul.com.sns.dto.FeedDto;
import mul.com.sns.dto.LikesDto;
import mul.com.sns.service.FeedService;

@RestController
@RequestMapping(value = "/feed")
public class FeedController {

	@Autowired
	FeedService fservice;
	
	@RequestMapping(value = "/getall", method = RequestMethod.GET)
	public List<FeedDto> getFeed(){
		System.out.println("FeedController getFeed()");
		
		List<FeedDto> list = fservice.getallFeed();
		
		return list;
	}
	
	@RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
	public String fileUpload(   @RequestParam("uploadFile")
								List<MultipartFile> uploadFile, 
								HttpServletRequest req,
								int userid, String content) {
		System.out.println("FeedController fileUpload");
		System.out.println(userid);
		System.out.println(content);
		
		
		// 경로
		// server : 3000
		String uploadPath = req.getServletContext().getRealPath("/upload/feed");
		// 폴더
		//String uploadPath = "d:\\temp";
		String photodata = "";
		for(MultipartFile file : uploadFile) {
		// 파일명 취득
			String filename = file.getOriginalFilename();
			String filepath = uploadPath + File.separator + filename;		
			
			String newfilename = ""; // 새로운 파일명
			String filepost = "";	// 확장자명
			
			if(filename.indexOf(".") >= 0) { // 확장자명이 있는 경우
				filepost = filename.substring( filename.indexOf(".") );	// abc.txt
				newfilename = new Date().getTime() + filepost;			
			}else {							// 확장자명이 없는 경우
				newfilename = new Date().getTime() + ".back";	
			}
			
			photodata += "/feedupload/" + newfilename + "?";
			
			try {
				BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(new File(filepath)));
				os.write(file.getBytes());
				os.close();
				
				// DB input
				
			} catch (Exception e) {			
				e.printStackTrace();			
				return "file upload fail";
			}
		}
		
		String str = photodata.substring(0, photodata.length()-1);
		System.out.println(str);	
		
		
		
		System.out.println("FeedController addFeed()");
		FeedDto dto = new FeedDto();
		dto.setFile(str);
		dto.setUserid(userid);
		dto.setContent(content);
		boolean b = fservice.addFeed(dto);
		
		if(b) {
			return "YES";
		}else {
			return "NO";
		}
		
	}
	
	
	@RequestMapping(value = "/post/update", method = RequestMethod.POST)
	public String updateFeed(FeedDto dto) {
		System.out.println("FeedController updateFeed()");
		
		boolean b = fservice.updateFeed(dto);
		if(b) {
			return "YES";
		}else {
			return "NO";
		}
	}
	
	
	@RequestMapping(value = "/post/del", method = RequestMethod.POST)
	public String deleteFeed(int seq) {
		System.out.println("FeedController deleteFeed()");
		
		boolean b = fservice.deleteFeed(seq);
		if(b) {
			return "YES";
		}else {
			return "NO";
		}
	}
	
}
