package probs2023;
import java.io.File;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

import number.IsNumber;
import utils.Mapping;
import utils.Sort;

public class prob9 {

	//day1 part 1
	//2:38.01
	
	public static void main(String[] args) {
		Scanner in;
		try {
			//in = new Scanner(new File("in2023/prob2023in0.txt"));
			in = new Scanner(new File("in2023/prob2023in9.txt"));
			int numTimes = 0;
			 
			int count = 0;
			boolean part2 = false;
			String line = "";

			LinkedList queue = new LinkedList();
			Stack stack = new Stack();
			HashSet set = new HashSet();
			
			
			Hashtable<Long, Integer> trail = new Hashtable<Long, Integer>();
			
			ArrayList <String>lines = new ArrayList<String>();
			
			
			int LIMIT = 20000;
			boolean table[][] = new boolean[LIMIT][LIMIT];
			
			
			//dir: 0 up
			//1 right
			//2 down
			//3 left
			
			while(in.hasNextLine()) {
				line = in.nextLine();
				lines.add(line);
				
			}

			long cur = 0;
			
			ArrayList ints = new ArrayList<Integer>();
			for(int i=0; i<lines.size(); i++) {
				
				
				line = lines.get(i);
				
				
			}
			
			long array[][] = new long[lines.size()][lines.get(0).split(" ").length];
			
			for(int i=0; i<array.length; i++) {
				
				String tokens[] = lines.get(i).split(" ");
				for(int j=0; j<tokens.length; j++) {
					
					array[i][j] = pint(tokens[j]);
				}
				
				sopl(tokens.length);
			
			}
			
			
			cur=0;
			
			for(int i=0; i<array.length; i++) {
				
				long tmp[] = array[i];
				
				
				
				int depth = 0;

				long diffToUse = -1;
				boolean doneSoFar = false;
				
				while(!doneSoFar) {
					
					long diffs[] = new long[tmp.length - 1];
					
					doneSoFar = true;
					long firstDiff = tmp[1] - tmp[0];
					for(int j=0; j<diffs.length; j++) {
						diffs[j] = tmp[j+1] - tmp[j];
						
						if(diffs[j] != firstDiff) {
							doneSoFar = false;
						}
					}
					
					if(doneSoFar) {
						diffToUse = firstDiff;
						break;
					} else {
						diffToUse = firstDiff;
						
						tmp = diffs;
						depth++;
					}
				}
				
				
				long origArray[] = array[i];
				
				long firstElement = origArray[0];
				
				long firstDiff = origArray[1];
				
				int lastElement = -1;
				/*
				if(depth == 0) {
					lastElement = firstElement;
					
				} else if(depth == 1) {
					lastElement = firstElement + firstDiff * array[i].length;
					
				} else if(depth == 2) {
					//What's the formula...
				}
				*/
				long belowArray[][] = new long[depth + 2][origArray.length];
				boolean numSet[][] = new boolean[depth + 2][origArray.length];
				
				for(int i2=0; i2<belowArray.length; i2++) {
					for(int j2=0; j2<belowArray[0].length - i2; j2++) {
						if(i2 == 0) {
							belowArray[0][j2] = origArray[j2];
						} else {
							
							belowArray[i2][j2] = belowArray[i2 - 1][j2 + 1] - belowArray[i2 - 1][j2];
							

							if(i2 == belowArray.length - 1) {
								if(belowArray[i2][j2] != diffToUse) {
									sopl("oops!");
									exit(1);
								}
							}
						}
						
						numSet[i2][j2] = true;
						
						sop(belowArray[i2][j2] + " ");
					}
					
					sopl();
				}
				
				sopl("done");

				for(int j2=0; j2<belowArray[0].length; j2++) {
					belowArray[belowArray.length - 1][j2] = diffToUse;
					numSet[belowArray.length - 1][j2] = true;
				}
				for(int i2=belowArray.length - 1; i2>=0; i2--) {
					for(int j2=0; j2<belowArray[0].length; j2++) {
						
						if(numSet[i2][j2] == false) {
							belowArray[i2][j2] = belowArray[i2][j2 - 1] + belowArray[i2 + 1][j2 - 1];
						}
						
						sop(belowArray[i2][j2] + " ");
					}
					sopl();
				}
				
				
				cur+= belowArray[0][origArray.length - 1] + belowArray[1][origArray.length - 1];
				
				sopl("Next: " + (belowArray[0][origArray.length - 1] + belowArray[1][origArray.length - 1]));
				for(int i2=1; i2<belowArray.length; i2++) {
					
					//cur+= belowArray[i2][origArray.length - i2];
				}

				sopl("cur: " + cur);
				
			}
			
			//25687360507
			
			//158831346683
			sopl("Answer: " + cur);
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
			sop("Error: (" + s + ") is not a number");
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
