import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class JSONObject extends JSONValue {
	Map<String, JSONValue> map = new HashMap<>();
	
	protected JSONObject(String text) {
		super(text);
		ArrayList<String> pairs = JSONParser.split(text.substring(1, text.length() - 1));
		for (String pairText: pairs) {
			String[] pair = new String[2];
			pair[0] = pairText.substring(0, pairText.indexOf(":"));
			pair[1] = pairText.substring(pairText.indexOf(":") + 1);
			String key = pair[0].substring(1, pair[0].length() - 1); // trims off quotation marks, key is always a string
			JSONValue value = JSONParser.parseJSON(pair[1]);
			map.put(key, value);
		}
	}
	
	@Override
	public JSONValue get(String key) {
		return map.get(key);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		map.forEach((k, v) -> {
			sb.append('"');
			sb.append(k);
			sb.append("\":");
			sb.append(v.toString());
		});
		sb.append("}");
		return sb.toString();
	}
}
