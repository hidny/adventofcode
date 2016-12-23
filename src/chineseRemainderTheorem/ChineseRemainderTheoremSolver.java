package chineseRemainderTheorem;

import java.util.ArrayList;

public class ChineseRemainderTheoremSolver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public static CRTTuple solve(ArrayList<CRTTuple> equations) {
		
		boolean isGCDGOOD = true;
		long current = 1;
		for(int i=0; i<equations.size(); i++) {
			if(getGCD(current, equations.get(i).getMod()) > 1) {
				System.out.println("Warning: The equations don't have gcd 1.");
				System.exit(1);
			}
			current *= equations.get(i).getMod();
		}
		
		long bigMod = 1;
		for(int i=0; i<equations.size(); i++) {
			bigMod *= equations.get(i).getMod();
		}
		
		long answer = 0;
		
		long ithTerm;
		
		for(int i=0; i<equations.size(); i++) {
			ithTerm = 1;
			for(int j=0; j<equations.size(); j++) {
				if(i != j) {
					ithTerm *= equations.get(j).getMod();
				}
			}
			
			//Really slow mod inverse function:
			for(int j=0; j<equations.get(i).getMod(); j++) {
				if( (j* ithTerm) % equations.get(i).getMod() == equations.get(i).getNum()) {
					ithTerm = j* ithTerm;
					break;
				}
			}
			
			answer += ithTerm;
		}
		
		return new CRTTuple(answer, bigMod);
	}
	
	//From project euler code:
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
