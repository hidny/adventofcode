package probs2022;
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

public class prob20Trial2 {

	
	public static void main(String[] args) {
		Scanner in;
		try {
			//in = new Scanner(new File("in2022/prob2022in20.txt"));
			in = new Scanner(new File("in2022/prob2022in21.txt"));
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

			ArrayList<Integer> ints = new ArrayList<Integer>();
			for(int i=0; i<lines.size(); i++) {
				
				
				line = lines.get(i);
				String token[] = line.split(" ");
				
				ints.add(pint(line));
				
			}
			
			
			int array[] = new int[ints.size()];
			
			for(int i=0; i<ints.size(); i++) {
				array[i] = ints.get(i);
				
			}
			
			
			for(int i=0; i<ints.size(); i++) {
				
				//sopl();
				//sopl();
				//sopl();
				//sopl("i = " + i);
				int numMove = ints.get(i);
				//sopl("Num move: " + numMove);
				
				int origLocation = -1;
				for(int j=0; j<array.length; j++) {
					if(array[j] == numMove) {
						origLocation = j;
					}
				}
				
				//use cards to see what happens when num = array.length and array.length - 1
				//Something weird happens with -1, -2...
				
				if(numMove < 0) {
					numMove -=1;
				}
				//-5252 wrong.
				
				numMove = mod(numMove, array.length);
				
				
				boolean skip = false;
				if(numMove == 0 || numMove == array.length - 1) {
					sopl("Skip: " + ints.get(i));
					sopl("or skip: " + (ints.get(i) % array.length));
					
						
					
				}
				
				
				
				/*if(numToMove >= 0) {
				
					for(int j=0; j<numToMove && j< array.length-1; j++) {
						
						array[mod(origLocation + j, array.length)] = array[mod(origLocation + j + 1, array.length)];
						
					}
				} else {
					for(int j=0; j>numToMove && j> 0 -array.length +1; j--) {
						
						array[mod(origLocation + j, array.length)] = array[mod(origLocation + j - 1, array.length)];
						
					}
				}*/
				
				if(skip == false) {
					for(int j=0; j<numMove; j++) {
						
						array[mod(origLocation + j, array.length)] = array[mod(origLocation + j + 1, array.length)];
						
					}

					array[mod(origLocation + numMove, array.length)] = ints.get(i);
				}
					//4342?
				//latest: 3258?
					//15233 wrong
				

			}
			
			boolean taken[] = new boolean[array.length];
			int numTaken = 0;
			for(int i=0; i<ints.size(); i++) {
				for(int j=0; j<ints.size(); j++) {
					
					if(! taken[j] && ints.get(i) == array[j]) {
						taken[j] = true;
						numTaken++;
						break;
					}
				}
			}
			sopl(numTaken);

			for(int i=0; i<ints.size(); i++) {
				if(taken[i] == false) {
					sopl("ERROR: Didn't mix well!");
					exit();
				}
			}
			
			
			
			//wrong: 4501
			int location0 = -1;
			for(int j=0; j<array.length; j++) {
				if(array[j] == 0) {
					location0 = j;
				}
			}
			if(location0 == -1) {
				sopl("What");
			}
			
			sopl();
			sopl("final values move:");
			for(int j=0; j<array.length; j++) {
					sop(array[j] + " ");
			}
			sopl();
			
			int num1 = array[mod(location0 + 1000, array.length)];
			int num2 = array[mod(location0 + 2000, array.length)];
			int num3 = array[mod(location0 + 3000, array.length)];

			sopl(num1);
			sopl(num2);
			sopl(num3);
			
			count = num1 + num2 + num3;
			
			sopl("Answer: " + count);
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	
	public static int mod(int num, int mod) {
		
		int tmp = num % mod;
		if(tmp < 0) {
			
			int tmp2 = tmp + mod;
			if(tmp2 < 0) {
				sopl("DOH!");
				exit(1);
			}
			return tmp2;
		} else {
			return tmp;
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
