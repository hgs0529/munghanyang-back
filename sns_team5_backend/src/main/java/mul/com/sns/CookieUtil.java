package mul.com.sns;

import java.io.Serializable;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

@Component
public class CookieUtil implements Serializable {

	private static final long serialVersionUID = -5249231248790093523L;
	
	public Cookie[] readAllCookie(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		return cookies;
	}
	
	public String getCookieValue(HttpServletRequest request, String cookieName) {
		
		Cookie[] cookies = readAllCookie(request);
			
		if(cookies != null) {
			for(Cookie c : cookies) {
				if(c.getName().equals(cookieName) ) {
					return c.getValue();
				}
			}
		}
		return null; 
	}
	
	public void addCookie(HttpServletResponse response, String cookieName, String cookieValue, int MaxAge, boolean httponly) {
		Cookie cookie = new Cookie(cookieName, cookieValue);
		cookie.setPath("/");
		cookie.setMaxAge(MaxAge);
		cookie.setHttpOnly(httponly);
		response.addCookie(cookie);
	}
	
	public void deleteCookie(HttpServletResponse response, String cookieName) {
		Cookie cookie = new Cookie(cookieName, null);
		cookie.setMaxAge(0);
		cookie.setPath("/");
		response.addCookie(cookie);
	}
}
