package util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ThubanKanjiToFurigana {
	
	public static String translation(String kanji) {
		
		String target_url = "https://jlp.yahooapis.jp/FuriganaService/V1/furigana";
		String api_key= ""; //야후 재팬에서 받은 api키
		StringBuffer result = new StringBuffer();
		
		try {
			kanji = URLEncoder.encode(kanji, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		try{
			StringBuffer url = new StringBuffer(target_url);
			url.append("?appid="+api_key);
			url.append("&sentence="+kanji);
			
			System.out.println(url);
			
			DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
			Document doc = dBuilder.parse(url.toString());
			
			// root tag 
			doc.getDocumentElement().normalize();
			
			NodeList nList = doc.getElementsByTagName("Word");

			for(int temp = 0; temp < nList.getLength(); temp++){		
				Node nNode = nList.item(temp);
				if(nNode.getNodeType() == Node.ELEMENT_NODE){
									
					Element eElement = (Element) nNode;
					try {
						result.append(getTagValue("Furigana", eElement));
					} catch (Exception e) {}
					
					//System.out.println("######################");
					//System.out.println("Surface  : " + getTagValue("Surface", eElement));
					//System.out.println("Furigana  : " + getTagValue("Furigana", eElement));
					//System.out.println("Roman : " + getTagValue("Roman", eElement));
				}	// for end
			}	// if end
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return result.toString();
	}

	private static String getTagValue(String tag, Element eElement) {
	    NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
	    Node nValue = (Node) nlList.item(0);
	    if(nValue == null) 
	        return null;
	    return nValue.getNodeValue();
	}
	
}
