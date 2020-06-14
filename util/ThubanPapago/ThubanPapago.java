package util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

public class ThubanPapago {
	//국가에는 ja, ko, en등을 적을수 잇음
	public static String translation(String originalCountry, String changeCountry, String original_str) {
		List<String> clientIds = new ArrayList<String>();
		List<String> clientSecrets = new ArrayList<String>();
		
		clientIds.add("");//애플리케이션 클라이언트 아이디값";
		clientSecrets.add("");//애플리케이션 클라이언트 시크릿값";
		
		// clientIds.add("");//애플리케이션 클라이언트 아이디값";
		// clientSecrets.add("");//애플리케이션 클라이언트 시크릿값";
		
		// clientIds.add("");//애플리케이션 클라이언트 아이디값";
		// clientSecrets.add("");//애플리케이션 클라이언트 시크릿값";
		
		// clientIds.add("");//애플리케이션 클라이언트 아이디값";
		// clientSecrets.add("");//애플리케이션 클라이언트 시크릿값";
		
		// clientIds.add("");//애플리케이션 클라이언트 아이디값";
		// clientSecrets.add("");//애플리케이션 클라이언트 시크릿값";
		
		
		
        String resultString ="";
        
        while(true) {
        	
        	resultString ="";
        	
	        try {
	
	            String text = URLEncoder.encode(original_str, "UTF-8");
	            
	            String apiURL = "https://openapi.naver.com/v1/papago/n2mt";
	            URL url = new URL(apiURL);
	            HttpURLConnection con = (HttpURLConnection)url.openConnection();
	            con.setRequestMethod("POST");
	            con.setRequestProperty("X-Naver-Client-Id", clientIds.get(0));
	            con.setRequestProperty("X-Naver-Client-Secret", clientSecrets.get(0));
	            // post request
	            String postParams = "source=ja&target=ko&text=" + text;
	            con.setDoOutput(true);
	            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
	            wr.writeBytes(postParams);
	            wr.flush();
	            wr.close();
	            int responseCode = con.getResponseCode();
	            BufferedReader br;
	            if(responseCode==200) { // 정상 호출
	                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
	            } else { // 에러 발생
	                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
	            }
	            String inputLine;
	            StringBuffer response = new StringBuffer();
	            while ((inputLine = br.readLine()) != null) {
	                response.append(inputLine);
	            }
	            br.close();
	
	            resultString = response.toString();
	        } catch (Exception e) {
	            System.out.println(e);
	        }
	        
	
	        org.json.simple.parser.JSONParser parser = new org.json.simple.parser.JSONParser();
	        JSONObject jsonObj = null;
	        String temp = null;
	        try {
				jsonObj = (JSONObject) parser.parse(resultString);
				temp = jsonObj.get("message").toString();
				jsonObj = (JSONObject) parser.parse(temp);
				temp = (String) jsonObj.get("result").toString();
				jsonObj = (JSONObject) parser.parse(temp);
				resultString = (String) jsonObj.get("translatedText").toString();
			} catch (Exception exception) {
				
				clientIds.remove(0);
				clientSecrets.remove(0);
				
				if(clientIds.size() == 0 || clientSecrets.size() == 0) {
					return "api키 다 써버림";
				}else {
					continue;
				}
			}
	        break;
        }
        return resultString;
	}
}
