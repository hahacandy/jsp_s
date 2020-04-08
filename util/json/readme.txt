import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

String jsonStr = "{"
			+ "		code:'1000',"
			+ "		name:'포도'"
			+ "}"
			;

JSONParser parser = new JSONParser();
Object obj = parser.parse( jsonStr );
JSONObject jsonObj = (JSONObject) obj;

String code = (String) jsonObj.get("code");
String name = (String) jsonObj.get("name");