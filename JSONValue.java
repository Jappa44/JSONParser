package json;
import java.util.ArrayList;

public class JSONValue {
	private String value;
	private boolean isString = false, isBoolean = false, isNumber = false, isArray = false, isObject = false;
	
	protected JSONValue(String str) {
		value = str;
		
		if (str.length() != 0) {
			switch (str.charAt(0)) {
				case '{':
					isObject = true;
					break;
				case '[':
					isArray = true;
					break;
				case '"':
					isString = true;
					break;
				case 't','f': // is true or false
					isBoolean = true;
					break;
				case 'n': // is null
					break;
				default:
					isNumber = true;
			}
		}
	}
	
	public String getValue() {
		return value;
	}

	public boolean isString() {
		return isString;
	}

	public boolean isBoolean() {
		return isBoolean;
	}

	public boolean isNumber() {
		return isNumber;
	}

	public boolean isArray() {
		return isArray;
	}

	public boolean isObject() {
		return isObject;
	}

	public ArrayList<JSONValue> values() {
		throw new IllegalArgumentException("Tried to get a values array from a base level JSON value, such as a String, boolean, or number");
	}
	
	public ArrayList<String> keys() {
		throw new IllegalArgumentException("Tried to get a keys array from a base level JSON value, such as a String, boolean, or number");
	}
	
	public JSONValue get(String str) {
		throw new IllegalArgumentException("Tried to get from a base level JSON value, such as a String, boolean, or number");
	}
	
	public JSONValue get(int index) {
		throw new IllegalArgumentException("Tried to get from a base level JSON value, such as a String, boolean, or number");
	}
	
	public int size() {
		throw new IllegalArgumentException("Tried to get the size of a base level JSON value, such as a String, boolean, or number");
	}
	
	public int toInt() {
		return Integer.valueOf(this.toString());
	}
	
	public double toDouble() {
		return Double.valueOf(this.toString());
	}
	
	public boolean toBoolean() {
		return Boolean.valueOf(this.toString());
	}
	
	public boolean isNull() {
		if (this.toString() == "null") {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public String toString() {
		return value;
	}
}
