package utilsPE;

public class GCD {
	public static long getGCD(long a, long b) {
		a = Math.abs(a);
		b = Math.abs(b);
		if(a==0 || b==0 ) {
			return 0;
		}
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
