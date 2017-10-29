package utilsFromProjectEuler;
import java.math.BigInteger;
import java.util.Comparator;

public class Fraction implements Comparable {

	/**
	 * Extremely slow Data type that is incredibly accurate.
	 * Note that it automatically does the gcd without asking.
	 *
	 * This should help with not messing up the accuracy.
	 * The idea is to only ever divide the final answer.
	 * 
	 * 
	 */
	
	public static final Fraction ONE = new Fraction(1, 1);
	public static final Fraction ZERO = new Fraction(0, 1);
	
	private BigInteger numerator;
	private BigInteger denominator;
	
	public static void main(String[] args) {
		
		long a = getGCD(9, 3);
		long b =  getGCD(13, 17);
		
		long c =  getGCD(24, 15);
		System.out.println("a: " + a);
		System.out.println("b: " + b);
		System.out.println("c: " + c);
		
		Fraction comb = new Fraction(1, 1);
		for(int i=0; i<30; i++) {
			comb = mult(comb, (i+1));
		}
		System.out.println(comb);
		for(int i=0; i<15; i++) {
			comb = divide(comb, (i+1));
		}
		for(int i=0; i<15; i++) {
			comb = divide(comb, (i+1));
		}
		
		System.out.println(comb + "== 155117520 ? ");
		
		Fraction num = new Fraction(1, 1);
		Fraction factor = new Fraction(4, 2);
		
		Fraction sum = new Fraction(0, 1);
		
		for(int i=0; i<10; i++) {
			num = divide(num, factor);
			sum = plus(sum, num);
		}
		
		System.out.println(sum + "== 1023/1024?");
		
		for(int i=0; i<10; i++) {
			sum = minus(sum, num);
			num = mult(num, factor);
			
		}
		System.out.println(sum + "== 0?");
		
	}
	
	public Fraction(long numerator, long denominator) {
		this.numerator = new BigInteger("" + numerator);
		this.denominator = new BigInteger("" + denominator);
	}
	
	public Fraction(BigInteger numerator, BigInteger denominator) {
		this.numerator = numerator;
		this.denominator = denominator;
	}
	
	public String toString() {
		return "" + getDecimalFormat();
	}
	
	//gets decimal format by using only the first (digits) digits.
	//hopefully this will stop any potential explosions...
	public double getDecimalFormat(int digits) {
		String sNumerator = this.numerator.toString();
		String sDenominator = this.denominator.toString();
		
		double doubleNumerator = Double.parseDouble(sNumerator.substring(0, Math.min(digits, sNumerator.length())));
		double doubleDenominator = Double.parseDouble(sDenominator.substring(0, Math.min(digits, sDenominator.length())));
		
		double mult = 1.0;
		
		//TODO: test with digits == num digits euler problem wants!
		//approximate to 0:
		if(sDenominator.length() - (sNumerator.length()+1) > digits) {
			return 0.0;
		}
		
		if(sNumerator.length() - (sDenominator.length()+1) > digits) {
			return Double.POSITIVE_INFINITY;
		}
		
		
		//If denominator is way bigger than numerator adjust by powers of 10:
		for(int i=Math.max(sNumerator.length(), digits); i<Math.max(sDenominator.length(), digits); i++) {
			mult *= 10;
		}
		
		//If numerator is way bigger than denominator adjust by powers of 10 the other way:
		for(int i=Math.max(sDenominator.length(), digits); i<Math.max(sNumerator.length(), digits); i++) {
			mult /= 10;
		}
		
		
		return (doubleNumerator/mult) / (doubleDenominator);
	}
	
	//gets decimal format by using only the first (digits) digits.
	//hopefully this will stop any potential explosions...
	public Fraction Approx(int digits) {
		String sNumerator = this.numerator.toString();
		String sDenominator = this.denominator.toString();
		
		BigInteger numerator = new BigInteger(sNumerator.substring(0, Math.min(digits, sNumerator.length())));
		BigInteger denominator = new BigInteger(sDenominator.substring(0, Math.min(digits, sDenominator.length())));
		
		Fraction mult = Fraction.ONE;
		
		//TODO: test with digits == num digits euler problem wants!
		//approximate to 0:
		if(sDenominator.length() - (sNumerator.length()+1) > digits) {
			return Fraction.ZERO;
		}
		
		if(sNumerator.length() - (sDenominator.length()+1) > digits) {
			return new Fraction(1,0);
		}
		
		
		//If denominator is way bigger than numerator adjust by powers of 10:
		for(int i=Math.max(sNumerator.length(), digits); i<Math.max(sDenominator.length(), digits); i++) {
			mult = Fraction.mult(mult, 10);
		}
		
		//If numerator is way bigger than denominator adjust by powers of 10 the other way:
		for(int i=Math.max(sDenominator.length(), digits); i<Math.max(sNumerator.length(), digits); i++) {
			mult = Fraction.divide(mult, 10);
		}
		
		
		return Fraction.divide(new Fraction(numerator, denominator), mult);
	}
		
	public double getDecimalFormat() {
		return this.numerator.doubleValue() / this.denominator.doubleValue();
	}
	

	public static Fraction mult(Fraction fraction1, long num) {
		return mult(fraction1, new Fraction(num, 1));
	}
	
	public static Fraction mult(Fraction fraction1, Fraction fraction2) {
		BigInteger numerator = fraction1.numerator.multiply(fraction2.numerator);
		BigInteger denominator = fraction1.denominator.multiply(fraction2.denominator);
		
		BigInteger gcd = numerator.gcd(denominator);
		if(gcd.compareTo(BigInteger.ZERO) != 0) {
			numerator = numerator.divide(gcd);
			denominator = denominator.divide(gcd);
		}
		return new Fraction(numerator, denominator);
	}
	
	public static Fraction divide(Fraction fraction1, long num) {
		return divide(fraction1, new Fraction(num, 1));
	}
	
	public static Fraction divide(Fraction fraction1, Fraction fraction2) {
		BigInteger numerator = fraction1.numerator.multiply(fraction2.denominator);
		BigInteger denominator = fraction1.denominator.multiply(fraction2.numerator);
		
		BigInteger gcd = numerator.gcd(denominator);
		if(gcd.compareTo(BigInteger.ZERO) != 0) {
			numerator = numerator.divide(gcd);
			denominator = denominator.divide(gcd);
		}
		
		return new Fraction(numerator, denominator);
	}
	
	public static Fraction plus(Fraction fraction1, long num) {
		return plus(fraction1, new Fraction(num, 1));
	}
	
	public static Fraction plus(Fraction fraction1, Fraction fraction2) {
		BigInteger numerator = (fraction1.numerator.multiply(fraction2.denominator)).add(fraction2.numerator.multiply(fraction1.denominator));
		BigInteger denominator = fraction1.denominator.multiply(fraction2.denominator);
		
		BigInteger gcd = numerator.gcd(denominator);
		
		if(gcd.compareTo(BigInteger.ZERO) != 0) {
			numerator = numerator.divide(gcd);
			denominator = denominator.divide(gcd);
		}
		
		return new Fraction(numerator, denominator);
	}
	
	public static Fraction minus(Fraction fraction1, long num) {
		return minus(fraction1, new Fraction(num, 1));
	}
	
	public static Fraction minus(Fraction fraction1, Fraction fraction2) {
		BigInteger numerator = (fraction1.numerator.multiply(fraction2.denominator)).subtract(fraction2.numerator.multiply(fraction1.denominator));
		BigInteger denominator = fraction1.denominator.multiply(fraction2.denominator);
		
		BigInteger gcd = numerator.gcd(denominator);
		if(gcd.compareTo(BigInteger.ZERO) != 0) {
			numerator = numerator.divide(gcd);
			denominator = denominator.divide(gcd);
		}
		return new Fraction(numerator, denominator);
	}

	public BigInteger getNumerator() {
		return numerator;
	}

	public BigInteger getDenominator() {
		return denominator;
	}
	
	public boolean greaterThan0() {
		if(numerator.signum() > 0 && denominator.signum() > 0) {
			return true;
		} else if(numerator.signum() < 0 && denominator.signum() < 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean lessThan0() {
		if(numerator.signum() > 0 && denominator.signum() < 0) {
			return true;
		} else if(numerator.signum() < 0 && denominator.signum() > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	//TODO: Test
	public int compareTo(Fraction other) {
		Fraction diff = Fraction.minus(this, other);
		if(diff.greaterThan0()) {
			return 1;
		} else if(diff.lessThan0()) {
			return -1;
		} else {
			return 0;
		}
	}
	
	@Override
	public int compareTo(Object arg0) {
		Fraction a = (Fraction)arg0;
		return this.compareTo(a);
	}
	
	
	//My get gcd method that I don't currently use.
		// it works though.
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
