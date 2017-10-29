package probs2015;

import java.io.File;
import java.util.Scanner;

public class prob20 {

	public static void main(String[] args) {
		Scanner in;
		try {
			
			int count = 0;
			boolean part2 = true;
			
			//int NUMBER =34000000;
			int NUMBER =34000000;
			
			int LIMIT_SUM = 0;
			
			if(part2) {
				LIMIT_SUM = NUMBER/11;
			} else {
				LIMIT_SUM = NUMBER/10;
			}
			
			int NUM_HOUSES = 50;


			long divisors[];
			
			for(long i=1; i<LIMIT_SUM; i++) {
				divisors = utilsFromProjectEuler.NumberTheory.getAllDivisors(i);
				
				int sum = 0;
				for(int j=0; j<divisors.length; j++) {
					if(part2 == false || divisors[j] * NUM_HOUSES >= i) {
						sum += divisors[j];
					}
				}
				if(sum >= LIMIT_SUM) {
					
					//Just in case... even though I know it's not the case.
					if(part2 && sum* 11 < NUMBER) {
						continue;
					}
					System.out.println("Answer: " + i);
					break;
				}
			}
			
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
		//887040
	}
	

}
