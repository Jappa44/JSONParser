import java.util.ArrayList;

public class JSONParser {
	private String text;

	public JSONParser() {

	}

	public void setText(String text) {
		this.text = text;
	}

	public static JSONValue parseJSON(String text) {
		if (text.charAt(0) == '{') { // is an object
			return new JSONObject(text);
		} else if (text.charAt(0) == '[') { // is an array
			return new JSONArray(text);
		} else { // must be one of the other JSON values
			return new JSONValue(text);
		}
	}

	// finds the indices of the delimiting commas for a given object or array str, assumes
	// surrounding brackets or braces have already been removed
	protected static ArrayList<Integer> findDelimiters(String str) {
		ArrayList<Integer> arr = new ArrayList<>();
		int depth = 0;
		char[] chars = str.toCharArray();
		char c;
		for (int i = 0; i < chars.length; i++) {
			c = chars[i];
			if (c == '{' || c == '[') {
				depth++;
			} else if (c == '}' || c == ']') {
				depth--;
			} else if (c == ',' && depth == 0) {
				arr.add(i);
			}
		}
		
		return arr;
	}
	
	protected static ArrayList<String> split(String str) {
		ArrayList<Integer> indices = JSONParser.findDelimiters(str);
		ArrayList<String> arr = new ArrayList<>();
		if (indices.size() == 0) {
			arr.add(str);
			return arr;
		}
		arr.add(str.substring(0, indices.get(0)));
		for (int i = 1; i < indices.size(); i++) {
			arr.add(str.substring(indices.get(i - 1) + 1, indices.get(i)));
		}
		arr.add(str.substring(indices.get(indices.size() - 1) + 1));
		return arr;
	}
}
