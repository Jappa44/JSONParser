package json;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class JSONObject extends JSONValue {
	Map<String, JSONValue> map = new HashMap<>();
	
	protected JSONObject(String text) {
		super(text);
		if (text.length() != 2) { // check object not empty
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
	}
	
	@Override
	public int size() {
		return map.size();
	}
	
	@Override
	public ArrayList<JSONValue> values() {
		return new ArrayList<JSONValue>(map.values());
	}
	
	@Override
	public ArrayList<String> keys() {
		return new ArrayList<String>(map.keySet());
	}
	
	@Override
	public JSONValue get(String key) {
		JSONValue value = map.get(key);
		if (value != null) {
			return value;
		} else {
			throw new IllegalArgumentException(String.format("Could not find key \"%s\"", key));
		}
	}
	
	@Override
	public JSONValue get(int index) {
		throw new IllegalArgumentException("Tried to get from a JSON object with a number.");
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		ArrayList<String> keys = keys();
		sb.append("{");
		if (size() > 0) {
			for (int i = 0; i < keys.size() - 1; i++) {
				sb.append('"');
				sb.append(keys.get(i));
				sb.append("\":");
				sb.append(get(keys.get(i)));
				sb.append(",");
			}
			sb.append('"');
			sb.append(keys.get(keys.size() - 1));
			sb.append("\":");
			sb.append(get(keys.get(keys.size() - 1)));
		}
		sb.append("}");
		return sb.toString();
	}
}
