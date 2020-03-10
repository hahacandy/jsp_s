//classes->KISA(key.dat, SHA256.class) is required

import java.util.Random;

import KISA.SHA256;
import sun.misc.BASE64Encoder;

public class ThubanPasswd {
	
	//kisa sha256을 이용하여 일반 문자를 sha256 해쉬코드로 변경후 베이스64 문자열로 변환
	public static String changePasswdToSHA256(String passwd) {
		String result = null;
		SHA256 s = new SHA256(result.getBytes());
		BASE64Encoder Base64Encoder = new BASE64Encoder();
		result = Base64Encoder.encode(s.GetHashCode());
		return result;
	}
	
	//랜덤 임시 비밀번호 생성 (passLen자 만큼 생성)
	public static String createTempPasswd(int passLen) {
		char[] passwdChars = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
				'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z',
				'!','@','#','$','%','^','&','*'};

		Random random = new Random();

		String result = "";
		for(int i=0; i<passLen; i++) {
		result += passwdChars[random.nextInt(passwdChars.length)];
		}
		
		return result;
	}
	
	
}
