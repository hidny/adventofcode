package number;

public class IsNumber {

	public static boolean isNumber(String val) {
		try {
			int a = Integer.parseInt(val);
			return true;
		} catch(Exception e) {
			return false;
		}
	}
	public static boolean isLong(String val) {
		try {
			long a = Long.parseLong(val);
			return true;
		} catch(Exception e) {
			return false;
		}
	}
}
