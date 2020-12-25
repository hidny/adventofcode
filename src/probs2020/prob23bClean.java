package probs2020;
import java.io.File;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

import number.IsNumber;
import utils.Mapping;
import utils.Sort;

public class prob23bClean {

	
	public static int NUM_CUPS = 1000000;
	public static int NUM_ROUNDS = 10000000;
	//public static int NUM_CUPS = 9;
	//public static int NUM_ROUNDS = 100;
	
	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2020/prob2020in23.txt"));
			 //in = new Scanner(new File("in2020/prob2020in23.txt.test"));
			
			ArrayList <String>lines = new ArrayList<String>();
			
			while(in.hasNextLine()) {
				String line = in.nextLine();
				lines.add(line);
				
				
			}
			int nextCupIndex[] = new int[NUM_CUPS];
			
			int curIndex = -1;
			
			for(int i=0; i<lines.size(); i++) {
				
				String line = lines.get(i);
				String lineCopy = String.copyValueOf(line.toCharArray());

				
				int last = -1;
				
				for(int j=0; j<line.length(); j++) {
					int current = (int)(line.charAt(j) - '1');
					
					if(j  > 0) {
						nextCupIndex[last] =  current;
						sopl((last+1) + " ->" + (current+1));
					}
					last = current;
					
				}
				
				for(int j=9; j<NUM_CUPS; j++) {
					nextCupIndex[last] = j;
					last = j;
				}
				curIndex = line.charAt(0) - '1';
				nextCupIndex[last] =  (int)(curIndex);
				//sopl(last + " ->" + (int)(curIndex));
				
			}
			
			
			for(int it=0; it<NUM_ROUNDS; it++) {
				
				if(it % 100000 == 0) {
					sopl("Iteration: " + it);
				}
				int nextThreeCups[] = new int[3];
				int nextThreeIndex[] = new int[3];
				
				///Pick up 3:
				int nextCupInd = curIndex;
				for(int j=0; j<nextThreeCups.length; j++) {
					nextCupInd = nextCupIndex[nextCupInd];
					
					nextThreeIndex[j] = nextCupInd;
					
				}
				
				int temp = nextCupIndex[curIndex] + 0;
				
				int nextIndex= nextCupIndex[nextThreeIndex[2]];
				nextCupIndex[curIndex] =  nextIndex;
				
				//End pickup 3:
				
				//Get destination label:
				int targetLabel = curIndex;

				boolean isRemoved = false;
				
				do {
					targetLabel = targetLabel - 1;
					if(targetLabel < 0) {
						targetLabel = NUM_CUPS - 1;
					}
					
					isRemoved = false;
					
					for(int j=0; j<nextThreeCups.length; j++) {
						if(nextThreeIndex[j] == targetLabel) {
							isRemoved = true;
						}
					}
				
				} while(isRemoved == true);
				
				//END get next target label:
				curIndex = targetLabel;
				
				//Place the 3 cups beside the destination cup:
				temp = nextCupIndex[curIndex];
				nextCupIndex[curIndex] =  nextThreeIndex[0];
				nextCupIndex[nextThreeIndex[2]] =  temp;
				
				//set curIndex of next round to the cup clockwise beside original cup of the round:
				curIndex = nextIndex;
				
			}
			
			sopl("Answer for part 1: (if applicable)");
			int testIndex = 0;
			for(int j=0; j<9 - 1; j++) {
				testIndex = nextCupIndex[testIndex];
				sop((testIndex + 1) + " ");
			}
			sopl();
			
			
			long answer = 1;
			for(int j=0; j<2; j++) {
				curIndex = nextCupIndex[curIndex];
				
				answer *= (curIndex + 1);
			}
			sopl();
			
			
			sopl("Answer: " + answer);
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

	public static long plong(String s) {
		if (IsNumber.isLong(s)) {
			return Long.parseLong(s);
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
