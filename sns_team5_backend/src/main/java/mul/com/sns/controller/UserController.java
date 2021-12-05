package mul.com.sns.controller;


import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

import javax.annotation.Nullable;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.log4j.Log4j2;
import mul.com.sns.CookieUtil;
import mul.com.sns.dto.AskDto;
import mul.com.sns.dto.FollowDto;
import mul.com.sns.dto.UserDto;
import mul.com.sns.jwt.JwtUtil;
import mul.com.sns.service.FeedService;
import mul.com.sns.service.UserService;

@Log4j2
@RestController
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private UserService service;
	
	@Autowired
	FeedService fservice;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private CookieUtil cookieUtil;
	
	@Autowired
	private JavaMailSender mailSender;
	@Value("${spring.mail.username}")
	String sendFrom;
	
	@GetMapping(value = "/") 
	public UserDto findUserByEmail(int userid, int login) {
		log.info("***** GET user (findUserByEmail) *****");
		
		// 유저 한명의 정보
		UserDto user = service.findUserBySeq(userid);
		user.setPwd("");
		System.out.println(login);
		System.out.println(userid);
		boolean b = fservice.isfollow(new FollowDto(0, login, userid));
		System.out.println(b);
		user.setIsfollow(b);
		return user;
	}
	
	@PostMapping(value="/update")
	public String profileUpdate(@RequestParam("uploadFile") @Nullable MultipartFile uploadFile, UserDto user, HttpServletRequest request) {
        log.info("***** user POST /profile/update *****");
        log.info("***** user Seq: " + user.getSeq());
        log.info("***** user Seq: " + user.getNickname());

        String uploadPath = request.getServletContext().getRealPath("/upload/avatar");
        System.out.println(uploadFile);
        if(uploadFile != null) {
        	String filename = uploadFile.getOriginalFilename();
            String filepost = "";
            String newfilename = "";
            if(filename.indexOf('.') >= 0) {
                filepost = filename.substring(filename.indexOf('.'));
                newfilename = new Date().getTime() + filepost;
            }else {
                newfilename = new Date().getTime() + ".back";
            }

            String filepath = uploadPath + File.separator + newfilename;

            try {
                BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(new File(filepath)));
                os.write(uploadFile.getBytes());
                os.close();

                // DB input
                user.setAvatar("/upload/avatar/" + newfilename);
                log.info("***** newAvatar: " + user.getAvatar());

                

            } catch (Exception e) {
                e.printStackTrace();
                return "NO";
            }
        }
        
        service.updateUserProfile(user);
        return "YES";
    }
	
	@PostMapping(value="/resign")
	public String resign(int seq) {
	    log.info("***** user POST /resign *****");

	    service.deleteUser(seq);

	    return "YES";
	}
	
	@GetMapping(value="/refresh")
	public UserDto refresh(HttpServletRequest req) {
		log.info("**** reload page ****");
		UserDto dto = null;
		String accessToken = jwtUtil.resolveAccessToken(req);
		if(accessToken != null) {
			int seq = jwtUtil.getUserSeq(accessToken);
			dto = service.findUserBySeq(seq);
			dto.setPwd("");
			System.out.println(dto.toString());
		}
		return dto;
	}
	
	@PostMapping(value = "/login")
	public String login(UserDto user, HttpServletRequest request, HttpServletResponse response) {
		log.info("***** POST user (login) *****");
		
		
		System.out.println(user.toString());
		// 유저 존재 확인
        UserDto dto = service.findUserByEmail(user.getEmail());
        System.out.println(dto.toString());
        if(dto == null) {
        	return "email";
        }else {
            boolean pwdCheck = service.pwdCheck(user, dto);
            if(!pwdCheck) {
            	return "pwd";
            }
        }
        
        // 액세스토큰 생성
        String accessToken = jwtUtil.generateAccessToken(dto);
        
        // 리프레시토큰 생성 + DB에 저장
        String refreshToken = jwtUtil.generateRefreshToken(dto);
        if(jwtUtil.findRefreshToken(dto.getSeq()) != null) {
        	dto.setToken(refreshToken);
        	service.updateRefreshToken(dto);
        }else {
        	System.out.println(dto.toString());
        	service.insertRefreshToken(dto);
        }
        
        //쿠키설정
        jwtUtil.setCookieAccessToken(response, accessToken);
        jwtUtil.setCookieRefreshToken(response, refreshToken);
        
        return "ok";
	}
	
	@PostMapping(value = "/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		log.info("***** POST user (logout) *****");
		
		String accessToken = jwtUtil.resolveAccessToken(request);
		int userSeq = jwtUtil.getUserSeq(accessToken);
		UserDto user = service.findUserBySeq(userSeq);
		service.deleteRefreshToken(user.getSeq());
		
		// 쿠키 정보 삭제
		cookieUtil.deleteCookie(response, "Authorization");
		cookieUtil.deleteCookie(response, "RefreshToken");
		
		return "ok";
	}
	
	@PostMapping(value = "/join")
	public String join(UserDto user) {
		log.info("***** POST user (join) *****");
		System.out.println(user.toString());
		service.join(user);
		UserDto result = service.findUserByEmail(user.getEmail());
		
		return result != null ?
				"ok" :
				"no";
	}
	
	@GetMapping(value="/join/emailcheck")
	public String emailCheck(String email) {
		log.info("***** GET user (join/emailcheck) *****");
		
		// 이메일 중복여부 확인
		UserDto dto = service.emailCheck(email);
		System.out.println(email);
		if(dto != null ) {
			if(dto.isSns_only()) {
				return "sns";
			}else {
				return "email";
			}
		}
		else {
			return "ok";
		}
	}
	
	@GetMapping(value="/join/nicknamecheck")
	public String nicknameCheck(String nickname) {
		log.info("***** POST user (join/nicknamecheck) *****");
		System.out.println(nickname);
		// 닉네임 중복여부 확인
		UserDto dto = service.nicknameCheck(nickname);
		if(dto != null ) {
			return "no";
		}
		else {
			return "ok";
		}
	}
	
	@GetMapping(value="/join/emailauth")
	public String emailauth(String email) {
	    log.info("**** User GET /join/emailauth ****");

	    String authkey = Integer.toString(ThreadLocalRandom.current().nextInt(100000, 1000000));
	    String mailcontent = "<img src='cid:munghanyang'>"
	        + "<h3>인증 코드를 입력해주세요.</h3>"
	        + "<h1>" + authkey + "</h1>";

	    mailSender.send(new MimeMessagePreparator() {
	        public void prepare(MimeMessage mimeMessage) throws MessagingException {
	            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
	            message.setFrom(sendFrom);
	            message.setTo(email);
	            message.setSubject("[멍하냥] 인증 코드 발송");
	            message.setText(mailcontent, true);
	            message.addInline("munghanyang", new ClassPathResource("img/logomail.png"));
	        }
	    });

	    return authkey;
	}
	@PostMapping(value="product/ask")
	public String productask(AskDto ask) {
	    log.info("***** user POST /product/ask *****");
	    service.addProductAsk(ask);
	    return "OK";
	}
}
