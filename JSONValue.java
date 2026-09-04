
public class JSONValue {
	String value;
	
	protected JSONValue(String str) {
		value = str;
	}
	
	public JSONValue get(String str) {
		throw new IllegalArgumentException("tried to get from a base level JSON value, such as a String, boolean, or number");
	}
	
	@Override
	public String toString() {
		return value;
	}
}
