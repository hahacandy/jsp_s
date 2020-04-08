package util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ThubanSimtoList {

	public static  List<String[]> get(String path) {
		
		StringBuffer sub = new StringBuffer();
        try{
            //입력 버퍼 생성
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path), "utf-8"));

            String line = "";

            while((line = br.readLine()) != null){
            	sub.append(line);
            }
            //.readLine()은 끝에 개행문자를 읽지 않는다.            
            br.close();
        }catch (FileNotFoundException e) {
            // TODO: handle exception
        	e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
        
        sub = new StringBuffer(sub.toString().trim());
        sub = new StringBuffer(sub.toString().replaceAll("'", "\\\\'"));
        sub = new StringBuffer(sub.toString().replaceAll("<br>", ""));
      
        Pattern pat = Pattern.compile("(<SYNC START=[0-9]+>|</BODY>)");
        Matcher match = pat.matcher(sub.toString().toUpperCase());
        
        int cnt = 0;
        int sub_start = 0;
        int sub_end = 0;
        
        StringBuffer sub_time = null;
        StringBuffer sub_text = null;
        
        List<String[]> list = new ArrayList<String[]>();
        
        while(match.find()) {
        	
        	sub_end = match.start();
        	if(cnt > 0) {
        		sub_text = new StringBuffer(sub.substring(sub_start, sub_end).trim());
        		
        		String[] sub_info = {sub_time.toString(),sub_text.toString()};
        		list.add(sub_info);
        	}
        	sub_start = match.end();
        	sub_time = new StringBuffer(match.group().replaceAll("\\D", ""));
        	cnt ++;
        }
        
        return list;

	}
	
}