package probs2019;
import java.io.File;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

import number.IsNumber;
import utils.Mapping;
import utils.Sort;
import utilsPE.NumberTheory;

public class prob22part2 {

	public static int CARD_IN_POS_TEST = 9;
	public static long NUM_REPEATS_TEST = 1034;
	public static int NUM_CARDS_TEST = 13;
	
	
	public static void main(String[] args) {
		Scanner in;
		try {
			
			//String filename = "in2019/prob2019in22.txt.test";
			String filename = "in2019/prob2019in22.txt";
			
			 in = new Scanner(new File(filename));
			 //in = new Scanner(new File("in2019/prob2019in22.txt.test"));
		 
			String line = "";

			ArrayList <String>lines = new ArrayList<String>();
	
			while(in.hasNextLine()) {
				line = in.nextLine();
				lines.add(line);
				
				
			}

			boolean isTestInput = false;
			if(filename.contains("test")) {
				isTestInput = true;
			}

			long cardIndexToLookAt = -1;
			long numReshuffles = -1;
			long numCards = -1;

			//Set input vars:
			if(isTestInput) {

				numCards = NUM_CARDS_TEST;
				cardIndexToLookAt = CARD_IN_POS_TEST;
				numReshuffles = NUM_REPEATS_TEST;
				sopl("CARD NUMBER TO FIND IN POS: " + cardIndexToLookAt);
				
			} else {

				sopl("This is not a test!");
				numCards = 119315717514047L;
				cardIndexToLookAt = 2020;
				numReshuffles = 101741582076661L;
				sopl("CARD NUMBER TO FIND IN POS: " + cardIndexToLookAt);
			}
			
			
			//The answer will take the form of:
			// pos(x) = a * x + b mod NUM_CARDS
			
			//We work backwards to find the function pos(x) after a single round of shuffling
			
			long a = -1;
			long b = -1;

			BigInteger aBig = BigInteger.ONE;
			BigInteger bBig = BigInteger.ZERO;
			//long a = 1;
			//long b = 0;
			
			for(int i=lines.size() - 1; i >= 0 ; i--) {
				String cmd = lines.get(i);
				
				if(cmd.startsWith("#")) {
					continue;
				}
				
				if(cmd.equals("deal into new stack")) {
					
					aBig = BigInteger.ZERO.subtract(aBig);
					bBig = BigInteger.ZERO.subtract(bBig.add(BigInteger.ONE));
					//a = 0 - a;
					//b = -1 - b;
					
				} else if(cmd.startsWith("deal with increment")) {
					String tmp[] = cmd.split(" ");
					
					long stepNum = pint(tmp[tmp.length - 1]);
					
					long mult = NumberTheory.getAInverseMOD(stepNum, numCards);
					
					aBig = aBig.multiply(new BigInteger("" + mult));
					bBig = bBig.multiply(new BigInteger("" + mult));
					
					//a *= mult;
					//b *= mult;
					
				} else if(cmd.startsWith("cut")) {
					String tmp[] = cmd.split(" ");
					
					long cutNum = pint(tmp[tmp.length - 1]);
					
					bBig = bBig.add(new BigInteger("" + cutNum));
					//b += cutNum;
					
				} else {
					sopl("ERROR: unknown command");
					exit(1);
				}
				
				a = aBig.remainder(new BigInteger("" + numCards)).longValue();
				b = bBig.remainder(new BigInteger("" + numCards)).longValue();
				
				if(a < numCards) {
					a += numCards;
				}

				if(b < numCards) {
					b += numCards;
				}
				
				//TODO: compare comment vs uncomment (should not make any difference)
				//aBig = new BigInteger("" + a);
				//bBig = new BigInteger("" + b);
				
				sopl(cmd);
				if(isTestInput) {
					long result = ((a *cardIndexToLookAt + b) % numCards);
					
					if(result < 0) {
						result += numCards;
					}
					sopl(result);
					
					sop("Result: ");
					sopl(a + " * x + " + b);
					sopl("Answer so far: " + result);
				}
			}
			
			
			sop("Result after 1 shuffle process: ");
			long result = ((a * cardIndexToLookAt + b) % numCards);
			
			if(result < 0) {
				result += numCards;
			}
			sopl(result);
			
			//Formula for n shuffle processes:
			
			//after 1: a(x) = ax + b
			//after 2: b(x) = a(a*x + b) + b = (a^2)*x + a*b + b
			//after 3: c(x) = a(a^2*x + a*b + b) + b = (a^3)*x + a^2*b + a*b + b
			
			
			//after n:   (a)^(n)*x + a^(n-1)*b + a^(n-2)*b + ... + b
			//             (Second part is just a geometric series. There's an equation to find the answer)
			
			// So it could be simplified to:
			//         = (a)^(n)*x + ((a^(n))*b - b) / ( a -1)
			//
			//

			
			BigInteger origA = new BigInteger("" + a);
			BigInteger origB = new BigInteger("" + b);

			BigInteger numCardsBig = new BigInteger("" + numCards);
			BigInteger numRepeats = new BigInteger("" + numReshuffles);
			
			BigInteger aToTheN = utilsPE.NumberTheory.getAPowerPmodMOD(origA, numRepeats, numCardsBig);
			
			BigInteger firstTerm = aToTheN.multiply(new BigInteger("" + cardIndexToLookAt));
			
			BigInteger secondTermNominator = aToTheN.multiply(origB).subtract(origB);
			
			BigInteger secondTerm = BigInteger.ONE;
			if(utilsPE.GCD.getGCD(a-1, numCards) == 1) {
				
				//Thankfully, part 2 has a prime # of cards because division mod prime is easy (If it wasn't prime, division is hard/impossible)
				long secondTermDenomInverse = NumberTheory.getAInverseMOD(a - 1, numCards);
				secondTerm = secondTermNominator.multiply(new BigInteger("" + secondTermDenomInverse));
				
			} else {
				
				//At this point the # of cards is not prime, so just hope the division mod n works out. :(
				//The only guaranteed way of getting it right at this point is by not getting any remainder out of a^n, which could make that number absurdly big....
				
				secondTerm = secondTermNominator.divide(new BigInteger("" + (a - 1)));
			}
			
			BigInteger finalResult = firstTerm.add(secondTerm).mod(new BigInteger("" + numCards));
			
			long answer =  finalResult.longValue();
			if(answer < 0) {
				answer += numCards;
			}
			if(isTestInput) {
				sopl("TEST");
			}
			sopl("Answer found: " + finalResult.longValue());

			// 42152620178084
			//(TOO HIGH)
			
			sopl();
			
			
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}

	public static void sop(Object a) {
		System.out.print(a.toString());
	}
	public static void sopl(Object a) {
		System.out.println(a.toString());
	}

	public static void sopl() {
		System.out.println();
	}
	
	public static int pint(String s) {
		if (IsNumber.isNumber(s)) {
			return Integer.parseInt(s);
		} else {
			sop("Error: (" + s + " is not a number");
			return -1;
		}
	}
	
	public static void exit() {
		exit(0);
	}
	public static void exit(int code) {
		sop("Exit with code " + code);
		
		System.exit(code);
	}

}
