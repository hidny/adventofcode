package utilsPE;

public class GCD {
	public static long getGCD(long a, long b) {
		if(b>a) {
			return getGCD(b, a);
		}
		
		long ret = a % b;
		
		if(ret == 0) {
			return b;
		} else{
			return getGCD(b, ret);
		}
	}
	
}
