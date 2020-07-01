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
        		
        		String[] sub_info = {sub_time.toString(),sub_text.toString(), null};
        		list.add(sub_info);
        	}
        	sub_start = match.end();
        	sub_time = new StringBuffer(match.group().replaceAll("\\D", ""));
        	cnt ++;
        }
        
        
        
        //자막 시작 시간에 따라서 오름차순 정렬
        String temp[] = null;
        for(int i=0; i<list.size(); i++) {
        	
        	for(int j=i+1; j<list.size(); j++) {
        		
        		if(Integer.valueOf(list.get(i)[0]) > Integer.valueOf(list.get(j)[0])){
        			temp = list.get(j);
        			list.set(j, list.get(i));
        			list.set(i, temp);
        		}
        		
        	}
        	
        }
        
        
        //자막 시작시간을 이용해 끝시간을 계산
        for(int i=0; i<list.size()-1; i++) {
        	list.get(i)[2] = list.get(i+1)[0];
        }
        
        //마지막  자막 끝 시간  99999999로 변경
        list.get(list.size()-1)[2] = "999999999999";

        
        
        return list;

	}
	
}
