package util;

import java.awt.List;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.simple.JSONObject;


public class ThubanKanjiToMean {

	
	//일본 문장을 기입하면, 그중 한자만 선택하여 그 뜻을 나타내줌
	public static String convert(String inputText) {
		
		java.util.List<String> kanjiMeans = new ArrayList<String>();
		
		java.util.List<String> kanjis = convertMorseToText(inputText);
		for(String kanji : kanjis) {

	        org.json.simple.parser.JSONParser parser = new org.json.simple.parser.JSONParser();
	        JSONObject jsonObj = null;
	        String temp = null;
	        try {
				jsonObj = (JSONObject) parser.parse(getKanjiMean(kanji));
				temp = jsonObj.get("searchResult").toString();
				
				//대괄호로 감싸져있기때문에 대괄호를 풀어야 제이슨 오프젝트로 변환 가능함
				temp = temp.substring(1, temp.length()-1);

				
				jsonObj = (JSONObject) parser.parse(temp);
				temp = jsonObj.get("expKoreanPron").toString();
				
				
				kanjiMeans.add(kanji + " : " + temp);
				kanjiMeans.add("<br>");
				
				//jsonObj = (JSONObject) parser.parse(temp);
				//temp = (String) jsonObj.get("expKoreanPron").toString();
				//result.append(temp);
				//result.append("<br>");
			} catch (Exception exception) {}
	        
	       
			
		}
		
		StringBuilder result = new StringBuilder();
		for(int i=0; i<kanjiMeans.size()-1 ; i++) {
			result.append(kanjiMeans.get(i));
		}
		
		return result.toString();
	}
	
	//해당 한자의 뜻을 웹에서 가져옴
	private static String getKanjiMean(String kanji) {
		String resultString ="";
    	
        try {

            String text = URLEncoder.encode(kanji, "UTF-8");
            
            String apiURL = "https://ja.dict.naver.com/api3/jako/search/hanja";
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");

            String getParams = "query=" + text;
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(getParams);
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
        
        return resultString;
	}
	
	//해당 문자열에서 한자만 추출하여 리스트에 저장함
	private static java.util.List<String> convertMorseToText(String text) {
        Pattern pattern = Pattern.compile("[一-龠]"); // 漢字の判定
        Matcher matcher = pattern.matcher(text);
        
        java.util.List<String> result = new ArrayList<String>();
        while (matcher.find()) {
        	result.add(matcher.group());
        }
        return result;
    }
}
