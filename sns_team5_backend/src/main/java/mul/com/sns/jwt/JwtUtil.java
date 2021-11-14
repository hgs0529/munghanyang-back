package mul.com.sns.jwt;

import java.io.Serializable;
import java.time.Duration;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import mul.com.sns.CookieUtil;
import mul.com.sns.dao.UserDao;
import mul.com.sns.dto.UserDto;

@Component
public class JwtUtil implements Serializable {
	
	private static final long serialVersionUID = -1526722295979246046L;
	
	private String secretkey = "munghanyang";
	
	private long accessTokenValidTime = Duration.ofMinutes(30).toMillis();	// 액세스토큰의 유효기간: 30분
	private long refreshTokenValidTime = Duration.ofDays(7).toMillis();		// 리프레시토큰의 유효기간: 7일
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private CookieUtil cookieUtil;

	// 객체 초기화: 시크릿키를 Base64로 암호화
    @PostConstruct
    protected void init() {
        secretkey = Base64.getEncoder().encodeToString(secretkey.getBytes());
    }
    
    // 액세스토큰 발급
    public String generateAccessToken(UserDto userDto){
        return this.generateToken(userDto, accessTokenValidTime);
    }
    
    // 리프레시토큰 발급
    public String generateRefreshToken(UserDto userDto) {
        String refreshToken = this.generateToken(userDto, refreshTokenValidTime);
        userDto.setToken(refreshToken);
        
        if(this.findRefreshToken(userDto.getSeq()) != null) {
        	userDao.updateRefreshToken(userDto);
        }else {
        	userDao.insertRefreshToken(userDto);
        }
        
        return Jwts.builder()
        		   .setSubject(String.valueOf(userDto.getSeq()))
        		   .setIssuedAt(new Date(System.currentTimeMillis()))
        		   .setExpiration(new Date(System.currentTimeMillis() + refreshTokenValidTime))
        		   .signWith(SignatureAlgorithm.HS256, secretkey)
        		   .compact();
    }
    
    // 리프레시토큰으로 새로운 액세스토큰 발급
    public String generateNewAccessToken(String refreshToken) {
    	int seq = this.getUserSeq(refreshToken);
        UserDto user = userDao.findUserBySeq(seq);
        
        return this.generateAccessToken(user);
    }
    
    // 유저 정보를 포함해 토큰 생성
    public String generateToken(UserDto userDto, long validTime){
    	Claims claims = Jwts.claims().setSubject(String.valueOf(userDto.getSeq()));
    	claims.put("auth", userDto.getAuth());
    	
    	Map<String, Object> header = new HashMap<>();
    	header.put("typ", "JWT");
    	header.put("alg", "HS256");
    	
    	return Jwts.builder()
    			.setHeader(header)
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + validTime))
                .signWith(SignatureAlgorithm.HS256, secretkey)
                .compact();
    }
	
    // 토큰에서 회원번호 추출
    public int getUserSeq(String token) {
        return Integer.parseInt(Jwts.parser().setSigningKey(secretkey).parseClaimsJws(token).getBody().getSubject());
    }
    
    // 쿠키에서 액세스토큰 추출
    public String resolveAccessToken(HttpServletRequest request) {
    	if(cookieUtil.getCookieValue(request, "Authorization") != null)
            return cookieUtil.getCookieValue(request, "Authorization");
        return null;
    }
    
    // 쿠키에서 리프레시토큰 추출
    public String resolveRefreshToken(HttpServletRequest request) {
        if(cookieUtil.getCookieValue(request, "RefreshToken") != null)
            return cookieUtil.getCookieValue(request, "RefreshToken");
        return null;
    }

    // 토큰의 유효성 + 만료일자 확인
    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretkey).parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }
    
    // 액세스 토큰 쿠키 설정
    public void setCookieAccessToken(HttpServletResponse response, String accessToken) {
    	cookieUtil.addCookie(response, "Authorization", accessToken, Long.valueOf(accessTokenValidTime).intValue(), false);
    }

    // 리프레시 토큰 쿠키 설정 (안에 실제 정보는 없음. 요청받을 때 비교용이고 진짜는 DB에 저장)
    public void setCookieRefreshToken(HttpServletResponse response, String refreshToken) {
    	cookieUtil.addCookie(response, "RefreshToken", refreshToken, Long.valueOf(refreshTokenValidTime).intValue(), true);
    }

    // 리프레시토큰 존재 확인
    public String findRefreshToken(int id) {
        return userDao.findRefreshToken(id);
    }

}
