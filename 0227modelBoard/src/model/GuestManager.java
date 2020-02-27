package model;

public class GuestManager {
	
	//뷰창에 줄바꿈
	public static String lineBreak(String str){
		StringBuffer buf = new StringBuffer();
		for(int i=0; i<str.length(); i++){
			if(str.charAt(i) == '\n'){
				buf.append("<br>");
			}else{
				buf.append(str.charAt(i));
			}
		}
		
		return new String(buf);
	}
}
