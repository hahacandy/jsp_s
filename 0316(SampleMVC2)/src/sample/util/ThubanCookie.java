package sample.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ThubanCookie {
	
	//cookieName의 쿠기값에 cookieSetValue이 없다면, 추가후 false를 리턴, 있다면 true를 리턴
	public static boolean isCookieSet(HttpServletRequest request, HttpServletResponse response, String cookieName, String cookieSetValue) {
		
		Cookie[] cookies = request.getCookies();
		String cookieValues = null;
		boolean isReadCntIdx = false;
		for(Cookie cookie : cookies) {
			if(cookie.getName().equals(cookieName)) {
				cookieValues = cookie.getValue();
				if(cookie.getValue().contains(cookieSetValue)) {
					isReadCntIdx = true;
				}
				break;
			}
		}
		
		Cookie temp = null;
		if(!isReadCntIdx) {
			if(cookieValues == null) {
				temp = new Cookie(cookieName, cookieSetValue);
			}else {
				temp = new Cookie(cookieName, cookieValues+cookieSetValue);
			}
			temp.setMaxAge(60*60*24);
			response.addCookie(temp);
			
			return false;
		}else {
			return true;
		}
	}
}
