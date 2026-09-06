package json;
import java.util.ArrayList;
import java.util.Iterator;

public class JSONArray extends JSONValue implements Iterable<JSONValue> {
	ArrayList<JSONValue> arr = new ArrayList<>();
	
	protected JSONArray(String text) {
		super(text);
		if (text.length() != 2) { // check not empty
			ArrayList<String> values = JSONParser.split(text.substring(1, text.length() - 1));
			for (String value: values) {
				arr.add(JSONParser.parseJSON(value));
			}
		}
	}
	
	@Override
	public int size() {
		return arr.size();
	}
	
	@Override
	public JSONValue get(int index) {
		return arr.get(index);
	}
	
	@Override
	public JSONValue get(String str) {
		throw new IllegalArgumentException("Tried to get from a JSON array with a string.");
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int i = 0; i < arr.size() - 1; i++) {
			sb.append(arr.get(i).toString());
			sb.append(",");
		}
		if (arr.size() != 0) {
			sb.append(arr.getLast());
		}
		sb.append("]");
		return sb.toString();
	}

	@Override
	public Iterator<JSONValue> iterator() {
		return arr.iterator();
	}
}
