package mul.com.sns.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import mul.com.sns.dto.CommentDto;
import mul.com.sns.dto.FeedDto;
import mul.com.sns.dto.FollowDto;
import mul.com.sns.dto.HashtagDto;
import mul.com.sns.dto.LikesDto;
import mul.com.sns.dto.PageParam;
import mul.com.sns.service.CommentService;
import mul.com.sns.service.FeedService;

@RestController
@RequestMapping(value = "/feed")
public class FeedController {

	@Autowired
	FeedService fservice;
	
	@Autowired
	CommentService cservice;

	
	  /* 전체피드 가져오기 */
	   @RequestMapping(value = "/getall", method = RequestMethod.GET)
	   public List<FeedDto> getAllFeed(PageParam param) {
	      System.out.println("FeedController getAllFeed()");

	      int page = param.getPage();
	      int start = (page * 20) + 1;
	      int end = (page + 1) * 20;

	      param.setStart(start);
	      param.setEnd(end);      
	      
	      List<FeedDto> list = fservice.getallFeed(param);
	      for (FeedDto dto : list) {
			dto.setIsfollow(fservice.isfollow(new FollowDto(0, param.getUserid(), dto.getUserid())));
		}

	      return list;
	   }
	   
	   /* 유저의 전체피드 가져오기 */
	   @RequestMapping(value = "/getUserFeed", method = RequestMethod.GET)
	   public List<FeedDto> getUserFeed(FeedDto feed) {
	      System.out.println("FeedController getAllFeed()");

	      List<FeedDto> list = fservice.getUserFeed(feed);

	      return list;
	   }
	
	   /* 선택피드 가져오기 */
	   @RequestMapping(value = "/getfeed", method = RequestMethod.GET)
	   public FeedDto getFeed(FeedDto dto) {
	      System.out.println("FeedController getFeed()");
	      
	      //조회수증가
	      fservice.updateReadcount(dto);
	      
	      System.out.println(dto.toString());
	      //해시태그 불러오기
	      List<String> list = fservice.getHashtag(dto.getSeq());
	      String hashtag = String.join("-", list);
	      
	      int likecount = fservice.getFeedLikes(dto);
	      boolean b = false;
	      if (dto.getUserid() != 0) {

	         b = fservice.isLikes(dto);
	      }
	      

	      FeedDto feed = fservice.getFeed(dto.getSeq());
	      feed.setHashtag(hashtag);
	      feed.setLikecount(likecount);
	      feed.setIsliked(b);
	      feed.setIsfollow(fservice.isfollow(new FollowDto(0, dto.getUserid(), feed.getUserid())));

	      return feed;
	   }

	@RequestMapping(value = "/pagecount", method = RequestMethod.GET)
	public int pageCount(PageParam param) {
		System.out.println("FeedController pageCount()");

		return fservice.getFeedCount(param);
	}
	
	
	/* 사진게시판 그사람이 쓴 다른글 가져오기 */
	@RequestMapping(value = "/getother", method = RequestMethod.GET)
	public List<FeedDto> getOther(FeedDto dto) {
		System.out.println("FeedController getphoto()");
		
		List<FeedDto> list = fservice.getOther(dto);
		
		return list; 
		
	}


	/* 사진게시판 피드 작성하여(파일업로드 추가) 추가하기 */
	@RequestMapping(value = "/addpictureboard", method = RequestMethod.POST)
	public String addPictureBoard(@RequestParam("uploadFile") List<MultipartFile> uploadFile, HttpServletRequest req,
			FeedDto dto, String hashtag) {
		System.out.println("FeedController addPictureBoard()");
		System.out.println(dto.getUserid());
		System.out.println(dto.getContent());
		System.out.println(dto.getGroupno());

		// server : 3000
		String uploadPath = req.getServletContext().getRealPath("/upload/feed");
		// String uploadPath = "d:\\temp";
		String photodata = "";
		for (MultipartFile file : uploadFile) {
			// 파일명 취득
			String filename = file.getOriginalFilename();

			String newfilename = ""; // 새로운 파일명
			String filepost = ""; // 확장자명

			if (filename.indexOf(".") >= 0) { // 확장자명이 있는 경우
				filepost = filename.substring(filename.indexOf(".")); // abc.txt
				newfilename = new Date().getTime() + filepost;
			} else { // 확장자명이 없는 경우
				newfilename = new Date().getTime() + ".back";
			}
			String filepath = uploadPath + File.separator + newfilename;
			photodata += "/upload/feed/" + newfilename + "?";

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

		String str = photodata.substring(0, photodata.length() - 1);
		System.out.println(str);

		System.out.println("FeedController addFeed()");
		dto.setFile(str);

		boolean b = fservice.addFeed(dto);

		int photoid = fservice.recentlyAddSeq();

		String[] strarr = hashtag.split("-");

		for (int x = 0; x < strarr.length; x++) {
			HashtagDto hash = new HashtagDto();
			hash.setPhotoid(photoid);
			hash.setContent(strarr[x]);

			fservice.addHashtag(hash);
		}

		if (b) {
			return "YES";
		} else {
			return "NO";
		}

	}

	/* summernote 이미지 업로드 후 경로이름 반환해주기 */
	@RequestMapping(value = "/uploadImage", method = RequestMethod.POST)
	public String uploadImage(@RequestParam("file") MultipartFile uploadFile, HttpServletRequest req) {
		System.out.println("FeedController uploadImage()");

		// server : 3000
		String uploadPath = req.getServletContext().getRealPath("/upload/feed");
		// String uploadPath = "d:\\temp";
		// 파일명 취득
		String filename = uploadFile.getOriginalFilename();

		String newfilename = ""; // 새로운 파일명
		String filepost = ""; // 확장자명
		String summerdata = "";

		if (filename.indexOf(".") >= 0) { // 확장자명이 있는 경우
			filepost = filename.substring(filename.indexOf(".")); // abc.txt
			newfilename = new Date().getTime() + filepost;
		} else { // 확장자명이 없는 경우
			newfilename = new Date().getTime() + ".back";
		}
		String filepath = uploadPath + File.separator + newfilename;

		summerdata += "/upload/feed/" + newfilename;

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

	/*
	 * 노하우,상품리뷰 작성하여(파일업로드 추가) 추가하기
	 * 
	 * @RequestMapping(value = "/addboard", method = RequestMethod.POST) public
	 * String addBoard(FeedDto dto, String hashtag) {
	 * System.out.println("FeedController addBoard()");
	 * 
	 * boolean b = fservice.addFeed(dto); int photoid = fservice.recentlyAddSeq();
	 * 
	 * String[] str = hashtag.split("-");
	 * 
	 * for(int x=0; x<str.length; x++) { HashtagDto hash = new HashtagDto();
	 * hash.setPhotoid(photoid); hash.setContent(hashtag);
	 * 
	 * fservice.addHashtag(hash); }
	 * 
	 * if(b) { return "YES"; }else { return "NO"; } }
	 */

	/* 피드 수정하기 */
	@RequestMapping(value = "/updatefeed", method = RequestMethod.POST)
	public String updateFeed(FeedDto dto) {
		System.out.println("FeedController updateFeed()");

		boolean b = fservice.updateFeed(dto);
		if (b) {
			return "YES";
		} else {
			return "NO";
		}
	}

	/* 피드 삭제하기 */
	@RequestMapping(value = "/del", method = RequestMethod.POST)
	public String deleteFeed(int seq) {
		System.out.println("FeedController deleteFeed()");
		
		
		List<Integer> list = cservice.getAllCommentsSeq(seq);
		System.out.println(list.toString());
		for (Integer commSeq : list) {
			cservice.deleteAllLikes(commSeq);
			CommentDto dto = new CommentDto();
			dto.setCommentid(commSeq);
			cservice.deleteReply(dto);
			cservice.deleteComment(dto);
		}
		
//		for (Integer commSeq : list) {
//			CommentDto dto = new CommentDto();
//			dto.setCommentid(commSeq);
//			cservice.deleteComment(dto);
//		}
		
		
		fservice.deleteAllLikes(seq);
		fservice.deletehashtag(seq);
		boolean b = fservice.deleteFeed(seq);
		
		if (b) {
			return "YES";
		} else {
			return "NO";
		}
		
	}
	
	/* 게시판 별 일부 게시물 가져오기 */
	@RequestMapping(value = "/getcategoryfeed", method = RequestMethod.GET)
	public List<FeedDto> getCategoryFeed(PageParam param) {
		System.out.println("FeedController getCategoryFeed()");
		
		Date nowDate;
        Calendar cal = java.util.Calendar.getInstance();
        cal.add(cal.DATE, -7);// 일주일 빼기
        nowDate = cal.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = format.format(nowDate);
        
        FeedDto dto = new FeedDto();
        dto.setUserid(param.getUserid());
        dto.setGroupno(param.getGroupno());
        dto.setCreated(str);
        
        List<FeedDto> list = fservice.getcategoryFeed(dto);
        
        return list;
	}
	
	@RequestMapping(value = "/getlikefeed", method = RequestMethod.GET)
	public List<FeedDto> getLikeFeed(int userid){
		System.out.println("FeedController getLikeFeed()");
		
		List<Integer> arr = fservice.getLikeCount(userid);
		for (Integer seq : arr) {
			System.out.println(seq);
		}
		
		List<FeedDto> list = new ArrayList<FeedDto>();
		
		for(int x=0; x<arr.size(); x++) {
		
			FeedDto dto = fservice.getLikeFeed(arr.get(x));
			list.add(dto);
		}
		
		return list;
	}



	// 좋아요 부분

	/* 좋아요 판단하여 아이콘에 표시하기 */
	@RequestMapping(value = "/likes", method = RequestMethod.POST)
	public String addLike(LikesDto dto) {
		System.out.println("FeedController addLike()");

		int n = fservice.serachLikes(dto);

		if (n == 1) {
			int n2 = fservice.deleteLikes(dto);
			if (n2 > 0) {
				return "unlikes";
			}
		} else {
			int n2 = fservice.addLikes(dto);
			if (n2 > 0) {
				return "likes";
			}
		}

		return "NO";
	}
	

	/* 마이페이지에서 피드 눌럿을 때 상세화면 */
	@RequestMapping(value = "/feeddetail", method = RequestMethod.GET)
	public FeedDto feedDetail(int seq) {
		System.out.println("FeedController feedDetail()");

		return fservice.getFeedDetail(seq);
	}

	@RequestMapping(value = "/myfeed", method = RequestMethod.GET)
	public List<FeedDto> mypageFeed(int userid) {
		System.out.println("FeedController mypageFeed()");

		List<FeedDto> list = fservice.getMyFeed(userid);

		return list;
	}

	// 팔로우 부분

	// follow
	@RequestMapping(value = "/follow", method = RequestMethod.POST)
	public String follow(FollowDto followdto) {

		System.out.println("FeedController follow()");
		
		boolean b = fservice.isfollow(followdto);
		boolean b1;
		if(b) {
			b1 = fservice.unfollow(followdto);
		} else {
			b1 = fservice.follow(followdto);
		}


		if (b) {
			return "unfollow";
		} else {
			return "follow";
		}

	}


	// 팔로워, 팔로잉 리스트 조회
	@RequestMapping(value = "/followlist", method = RequestMethod.GET)
	public Map<String, List<FollowDto>> personalList(FollowDto followdto) throws Exception {
		System.out.println("FeedController personalList()");

		Map<String, List<FollowDto>> map = new HashMap<>();

		List<FollowDto> followerList = fservice.FollowgiveList(followdto);
		List<FollowDto> followingList = fservice.FollowrecvList(followdto);

		// List<FollowDto> followerList = new ArrayList<FollowDto>();
		// followerList.add(followdto);

		map.put("followerList", followerList);
		map.put("followingList", followingList);

		return map;
	}

	@RequestMapping(value = "/followfeed", method = RequestMethod.GET)
	public List<FeedDto> followFeed(int userid) {
		System.out.println("FeedController followFeed()");

		List<FeedDto> list = fservice.getfollowFeed(userid);

		return list;

	}

}
