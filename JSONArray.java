import java.util.ArrayList;

public class JSONArray extends JSONValue {
	ArrayList<JSONValue> arr = new ArrayList<>();
	
	protected JSONArray(String text) {
		super(text);
		ArrayList<String> values = JSONParser.split(text.substring(1, text.length() - 1));
		for (String value: values) {
			arr.add(JSONParser.parseJSON(value));
		}
	}
	
	@Override
	public JSONValue get(String index) {
		return arr.get(Integer.valueOf(index));
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int i = 0; i < arr.size() - 1; i++) {
			sb.append(arr.get(i).toString());
			sb.append(",");
		}
		sb.append(arr.getLast());
		sb.append("]");
		return sb.toString();
	}
}
