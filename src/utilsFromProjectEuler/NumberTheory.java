package utilsFromProjectEuler;

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
	
}
