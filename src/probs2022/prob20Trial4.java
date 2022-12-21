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

public class prob20Trial4 {

	//I cheated to find out what I was doing wrong!
	// It turns out that there's duplicate entries!
	
	public static void main(String[] args) {
		Scanner in;
		try {
			
			//-850 wrong.
			
			in = new Scanner(new File("in2022/prob2022in20.txt"));
			//in = new Scanner(new File("in2022/prob2022in21.txt"));

			 
			int count = 0;
			String line = "";
			
			ArrayList <String>lines = new ArrayList<String>();
			
			
			while(in.hasNextLine()) {
				line = in.nextLine();
				lines.add(line);
				
			}

			ArrayList<Integer> ints = new ArrayList<Integer>();
			for(int i=0; i<lines.size(); i++) {
				
				
				line = lines.get(i);
				
				ints.add(pint(line));
				
			}
			
			
			int array[] = new int[ints.size()];
			
			int location[] = new int[ints.size()];
			for(int i=0; i<ints.size(); i++) {
				array[i] = ints.get(i);
				location[i] = i;
				
			}
			
			
			for(int i=0; i<ints.size(); i++) {
				
				//sopl();
				//sopl();
				//sopl();
				//sopl("i = " + i);
				int numMove = ints.get(i);
				//sopl("Num move: " + numMove);
				
				int curLocationNumToMove = -1;
				for(int j=0; j<location.length; j++) {
					if(location[j] == i && curLocationNumToMove == -1) {
						curLocationNumToMove = j;
					} else if(location[j] == i) {
						sopl("DOH! 45");
						exit(1);
					}
					
				}
				if(curLocationNumToMove == -1) {
					sopl("DOH!");
					exit(1);
				}
				if(ints.get(i) != array[curLocationNumToMove]) {
					sopl("DOH 66");
					exit(1);
				}
				
				
				//use cards to see what happens when num = array.length and array.length - 1
				//Something weird happens with -1, -2...
				
				//-5252 wrong.
				
				//I'm guessing the pick up data loses it's position temporally?
				//Answer: yes!
				numMove = mod(numMove, array.length - 1);
				
				for(int j=0; j<numMove; j++) {
					
					array[mod(curLocationNumToMove + j, array.length)] = array[mod(curLocationNumToMove + j + 1, array.length)];
					
					location[mod(curLocationNumToMove + j, array.length)] = location[mod(curLocationNumToMove + j + 1, array.length)];
				}

				array[mod(curLocationNumToMove + numMove, array.length)] = ints.get(i);
				
				location[mod(curLocationNumToMove + numMove, array.length)] = i;
				
					//4342?
				//latest: 3258?
					//15233 wrong
				
				
			}
			
			
			boolean taken[] = new boolean[array.length];
			int numTaken = 0;
			for(int k=0; k<ints.size(); k++) {
				for(int j=0; j<ints.size(); j++) {
					
					if(! taken[j] && ints.get(k) == array[j]) {
						taken[j] = true;
						numTaken++;
						break;
					}
				}
			}
			sopl("Array length: " + numTaken);

			for(int k=0; k<ints.size(); k++) {
				if(taken[k] == false) {
					sopl("ERROR: Didn't mix well!");
					exit();
				}
			}
			
			//9652
			
			//wrong: 4501
			int location0 = -1;
			for(int j=0; j<array.length; j++) {
				if(array[j] == 0 && location0 == -1) {
					location0 = j;
				} else if(array[j] == 0) {
					sopl("crap");
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
