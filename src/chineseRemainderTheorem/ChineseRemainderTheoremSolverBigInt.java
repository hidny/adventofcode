package chineseRemainderTheorem;

import java.math.BigInteger;
import java.util.ArrayList;

public class ChineseRemainderTheoremSolverBigInt {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
	}

	public static BigInteger[] solve(ArrayList<CRTTuple> equations) {
		
		BigInteger bigMod = BigInteger.ONE;
		for(int i=0; i<equations.size(); i++) {
			bigMod = bigMod.multiply(new BigInteger(equations.get(i).getMod() + ""));
		}
		
		System.out.println("Big Mod: " + bigMod);
		
		BigInteger answer = BigInteger.ZERO;
		
		BigInteger ithTerm;
		
		for(int i=0; i<equations.size(); i++) {
			ithTerm = BigInteger.ONE;
			for(int j=0; j<equations.size(); j++) {
				if(i != j) {
					ithTerm = ithTerm.multiply(new BigInteger(equations.get(j).getMod() + ""));
				}
			}
			

			System.out.println("ithTerm1: " + ithTerm);
			
			BigInteger inverse = ithTerm.modInverse(new BigInteger(""+ equations.get(i).getMod()));
			BigInteger ithTermToUse = ithTerm.multiply(inverse.multiply(new BigInteger(equations.get(i).getNum() + "")));
			
			
			
			/*
			System.out.println("Num to inverse: " + new BigInteger(""+ equations.get(i).getMod()));
			BigInteger inverse = ithTerm.modInverse(new BigInteger(""+ equations.get(i).getMod()));

			System.out.println("inverse: " + inverse);
			BigInteger ithTermToUse = inverse.multiply(new BigInteger(equations.get(i).getNum() + ""));
			
			System.out.println(ithTermToUse.divideAndRemainder(new BigInteger("" + equations.get(i).getMod()))[1] + " vs2 " + new BigInteger(""+ equations.get(i).getNum()));
			
			System.out.println("term to use: " + ithTermToUse + " vs " + new BigInteger(""+ equations.get(i).getNum()));
			
			*/
			//ithTerm = new BigInteger("" + equations.get(i).getNum());
			//System.out.println("term to use: " + ithTerm);
			
			answer = answer.add(ithTermToUse);
		}
		
		return new BigInteger[] {answer.divideAndRemainder(bigMod)[1], bigMod};
	}
	
	//From project euler code:
	public static BigInteger getGCD(BigInteger a, BigInteger b) {
		if(a.compareTo(b) > 0) {
			return getGCD(b, a);
		}
		
		BigInteger ret = a.divideAndRemainder(b)[1];
		
		if(ret.compareTo(BigInteger.ZERO) == 0) {
			return b;
		} else{
			return getGCD(b, ret);
		}
	}
}
