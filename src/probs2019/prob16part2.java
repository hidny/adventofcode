package probs2019;
import java.io.File;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

import number.IsNumber;
import utils.Mapping;
import utils.Sort;

public class prob16part2 {

	
	public static void main(String[] args) {
		Scanner in;
		try {
			// in = new Scanner(new File("in2019/prob2019in16.txt"));
			 in = new Scanner(new File("in2019/prob2019in16.txt"));

			boolean part2 = false;
			String line = "";

			
			ArrayList <String>lines = new ArrayList<String>();
			
			
			int LIMIT = 20000;
			
			
			//dir: 0 up
			//1 right
			//2 down
			//3 left
			
			while(in.hasNextLine()) {
				line = in.nextLine();
				lines.add(line);
				
				
			}
			
			int TWO = 2;
			
			
			StringBuilder sig = new StringBuilder(lines.get(0));
			
			int messageOffset = pint(lines.get(0).substring(0, 7));
			
			
			//Topaz made an off-by-one error... I guess he's human...
			int NUM_REPEATS_PART2 = 10000 - 1;
			
			if(part2) {
				for(int i=0; i<NUM_REPEATS_PART2; i++) {
					sig.append(lines.get(0));
				}
			}
			
			
			StringBuilder nextSig;
			
			long num;
			for(int phase = 1; phase<=100; phase++) {
				
				//sopl("phas1");
				
				//Set up signal in long table and then group them by twos, then fours, then eights, etc...
				long sumsCum[] = new long[sig.length() + 1];
				
				if(sig.length() == 0) {
					sopl("ERROR: no signal!");
					exit(1);
				}
				sumsCum[0] = 0;
				for(int i=1; i<sumsCum.length; i++) {
					sumsCum[i] = sumsCum[i - 1] + (long)(sig.charAt(i - 1) - '0');
					//sopl(sumsCum[i]);
				}
				
				nextSig = new StringBuilder();
				for(int m=1; m<=sig.length(); m++) {
					
					if(part2 && m < 100) {
						sopl("Phase " + phase + " and m = " + m);
					} else if(part2 && m%10000 == 0) {
						sopl("Phase " + phase + " and m = " + m);
						
					}
					
					num = 0;
					for(int i=1; i<=sig.length(); ) {
						
						int amountFactorLeft = m - (i % m);
						
						int amountMessageLeft = sig.length() - i + 1;
						
						amountFactorLeft = Math.min(amountFactorLeft, amountMessageLeft);

						int factor = getFactor(i, m);
						
						if(factor != 0) {
							num += factor * (sumsCum[i-1 + amountFactorLeft] - sumsCum[i-1]);
						}
						
						i += amountFactorLeft;
						//sopl(factor + " " + (sig.charAt(i-1) - '0') + "  " + sig.charAt(i-1));
					}
					//sopl();
					num = Math.abs(num);
					
					nextSig.append("" + (num%10));
				}
				sig = nextSig;
				if(part2 == false) {
					sopl("Phase " + phase + ": " + nextSig);
				}
			}
			if(part2) {
				sopl("Answer: " + sig.substring(messageOffset, messageOffset + 8));
			} else {
				sopl("Answer: " + sig.substring(0, 8));
			}
			//64462780 too low...
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	
	
	public static int getFactor(int i, int m) {
		int num = (i/m) % 4;
		
		if(num == 0) {
			return 0;
		} else if(num ==1) {
			return 1;
		} else if(num == 2) {
			return 0;
		} else if(num == 3) {
			return -1;
		} else {
			sopl("aa");
			exit(1);
			return -1;
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
