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

import mul.com.sns.dto.PetProfileDto;
import mul.com.sns.service.ProfileService;

@RestController
@RequestMapping(value = "/profile")
public class ProfileController {

	@Autowired
	ProfileService pservice;
	
	@RequestMapping(value = "/getpet", method = RequestMethod.GET)
	public List<PetProfileDto> getPetProfile(int userid) {
		System.out.println("ProfileController getPetProfile()");
		
		List<PetProfileDto> list = pservice.getPetProfile(userid);
		
		return list;
	}
	
	
	@RequestMapping(value = "/addpet", method = RequestMethod.POST)
	public String addPetProfile(@RequestParam("uploadFile") 
									MultipartFile uploadFile, 
									HttpServletRequest req,
									PetProfileDto dto) {
		System.out.println("ProfileController addPetProfile()");
		
		String uploadPath = req.getServletContext().getRealPath("/upload/pet");
		// String uploadPath = "d:\\temp";
		String productdata = "";
		
		String filename = uploadFile.getOriginalFilename();

		String newfilename = ""; // 새로운 파일명
		String filepost = ""; // 확장자명

		if (filename.indexOf(".") >= 0) { // 확장자명이 있는 경우
			filepost = filename.substring(filename.indexOf(".")); // abc.txt
			newfilename = new Date().getTime() + filepost;
		} else { // 확장자명이 없는 경우
			newfilename = new Date().getTime() + ".back";
		}
		String filepath = uploadPath + File.separator + newfilename;
		productdata += "/upload/pet/" + newfilename;

		try {
			BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(new File(filepath)));
			os.write(uploadFile.getBytes());
			os.close();

			// DB input

		} catch (Exception e) {
			e.printStackTrace();
			return "file upload fail";
		}
		
		dto.setPhoto(productdata);
		
		boolean b = pservice.addPetProfile(dto);
		
		if(b) {
			return "YES";
		}else {
			return "NO";
		}
	}
	
	@RequestMapping(value = "/deletepet", method = RequestMethod.POST)
	public String deletePetProfile(int seq) {
		System.out.println("ProfileController getPetProfile()");
		
		boolean b = pservice.deletePetProfile(seq);
		if(!b) {
			System.out.println("삭제실패");
			return "NO";
		}
		
		return "YES";
	}
	
}
