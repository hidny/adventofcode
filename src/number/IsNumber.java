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
	
	public static void main( String args[]) {
		System.out.println(isLong("0033933999992442222"));
		System.out.println(Long.parseLong("0033933999992442222"));
	}
}
