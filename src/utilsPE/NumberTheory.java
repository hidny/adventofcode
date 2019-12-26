package utilsPE;

import java.math.BigInteger;

public class NumberTheory {
	public static long[] getAllDivisors(long n) {
		long half[] = getFirstHalfDivisors(n);
		long table[];
		boolean perfectSquare;
		
		if(half[half.length - 1] * half[half.length - 1] == n) {
			table = new long[2*half.length-1];
			perfectSquare = true;
		} else {
			table = new long[2*half.length];
			perfectSquare = false;
		}
		
		for(int i=0; i<half.length; i++) {
			table[i] = half[i];
		}
		
		for(int i=0; i< table.length-half.length; i++) {
			if(perfectSquare == false) {
				table[half.length + i] = (long) (n/table[half.length - 1 - i]);
			} else {
				table[half.length + i] = (long) (n/table[half.length - 2 - i]);
			}
		}
		
		return table;
	}
	
	//todo: get second half of divisors with first half.
	private static long[] getFirstHalfDivisors(long n) {
		int numDivisors = 0;
		long sqrt = (long)Math.sqrt(n);
		for(int i=1; i<=sqrt; i++) {
			if(n % i == 0) {
				numDivisors++;
			}
		}
		
		long divisors[] = new long[numDivisors];
		
		int currentIndex = 0;
		for(long i=1; i<=sqrt; i++) {
			if(n % i == 0) {
				divisors[currentIndex] = i;
				currentIndex++;
			}
		}
		
		return divisors;
	}
	
	
	public static BigInteger getAPowerPmodMOD(BigInteger a, BigInteger pow, BigInteger MOD) {
		//base case for power:
		if(pow.compareTo(BigInteger.ZERO) == 0) {
			if(a.compareTo(BigInteger.ZERO) == 0 ) {
				System.out.println("0^0 is I don't know!!!");
				System.exit(1);
			} else if(pow.compareTo(BigInteger.ZERO) < 0 ) {
				System.out.println("No negative powers!" +  a + " to the power of " + pow + "?" );
				System.exit(1);
			}
			return BigInteger.ONE;
		} else if(a.compareTo(BigInteger.ZERO) == 0) {
			return BigInteger.ZERO;
		}
		
		//System.out.println(a + " to the power of " + pow);
		
		int lengthPowTable = 0;
		BigInteger current = BigInteger.ONE;
		while(current.compareTo(pow) <= 0) {
			lengthPowTable++;
			current = current.multiply(new BigInteger("2"));
		}
		
		//System.out.println("Length: " + lengthPowTable);
		
		//Setup the power of 2 table
		BigInteger pow2Table[] = new BigInteger[lengthPowTable];
		pow2Table[0] = a;
		
		
		for(int i=1; i<lengthPowTable; i++) {
			pow2Table[i] = (pow2Table[i-1].multiply(pow2Table[i-1])).mod(MOD);
		}
		//End setup the power of 2 table.
	
		current = pow;
		BigInteger answer = BigInteger.ONE;
		
		
		for(int i=0; i<lengthPowTable && current.compareTo(BigInteger.ZERO) > 0; i++) {
			if(current.mod(new BigInteger("2")).compareTo(BigInteger.ONE) == 0) {
				answer = (answer.multiply(pow2Table[i])).mod(MOD);
				current = current.subtract(BigInteger.ONE);
			}
			current = current.divide(new BigInteger("2"));
		}
		
		return answer;
	}
	

	public static long getNumMultforFactorial(long n, long multiple) {
		long ret = 0;
		for(long current=multiple; current<=n; current=current*multiple) {
			ret += n/current;
		}
		return ret;
	}
	
	
	public static void main(String args[]) {
		
		if(getAInverseMOD(7, 10) != 3) {
			System.out.println("ERROR: getAInverseMOD(7, 10) != 3: " + getAInverseMOD(7, 10));
			System.exit(1);
		} else {
			System.out.println("PASS: getAInverseMOD(7, 10) ==3");
		}
		
		
		if(getAInverseMOD(-1, 2) != 1) {
			System.out.println("ERROR: getAInverseMOD(1, 2)  != 1: " + getAInverseMOD(-1, 2));
			System.exit(1);
		} else {
			System.out.println("PASS: getAInverseMOD(1, 2)  == 1");
		}
		
		if(getAInverseMOD(7, 100) != 43) {
			System.out.println("ERROR: getAInverseMOD(7, 100) != 43: " + getAInverseMOD(7, 100));
			System.exit(1);
		} else {
			System.out.println("PASS: getAInverseMOD(7, 100) == 43");
		}
		
		if(getAInverseMOD(7, 1000) != 143) {
			System.out.println("ERROR: getAInverseMOD(7, 1000) != 143: " + getAInverseMOD(7, 1000));
			System.exit(1);
		} else {
			System.out.println("PASS: getAInverseMOD(7, 1000) == 143");
		}
		
		if(getAInverseMOD(7, 10000) != 7143) {
			System.out.println("ERROR: getAInverseMOD(7, 10000) != 7143: " + getAInverseMOD(7, 10000));
			System.exit(1);
		} else {
			System.out.println("PASS: getAInverseMOD(7, 10000) == 7143");
		}
	}
	
	//pre: gcd (a, mod) = 1
	public static long getAInverseMOD(long a, long MOD) {
		
		a = a % MOD;
		if(a < 0) {
			a += MOD;
		}
		if(a == 0) {
			System.out.println("ERROR: trying to get inverse of 0");
			System.exit(1);
		}
		if(GCD.getGCD(a, MOD) > 1) {
			long gcd = GCD.getGCD(a, MOD);
			a /= gcd;
			MOD /= gcd;
		}
		
		long inverse = getAInverseMOD(a, MOD, 1, 0, 0, 1);
		
		inverse	= inverse % MOD;
		
		if(inverse < 0) {
			inverse += MOD;
		}
		
		return inverse;
	}
	
	//TODO: more testing of edge cases?

	private static long getAInverseMOD(long a, long b, long multOrigA1, long multOrigB1, long multOrigA2, long multOrigB2) {
	
		
		if(a > b || a<0) {
			System.out.println("ERROR: expected a < b and a >=0");
			System.exit(1);
		}
		
		long newA = b % a;
		
		if(newA == 0) {
			
			return multOrigA1;
		} else {
		
			long quotient = b / a;
			
			long newMultA1 = multOrigA2 - quotient * multOrigA1;
			long newMultB1 = multOrigB2 - quotient * multOrigB1;
			
			
			return getAInverseMOD(newA, a, newMultA1, newMultB1, multOrigA1, multOrigB1);
		}
	}
	
}
