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

public class prob20part2 {

	//I cheated to find out what I was doing wrong!
	// It turns out that there's duplicate entries!
	
	public static void main(String[] args) {
		Scanner in;
		try {
			
			//-850 wrong.
			
			in = new Scanner(new File("in2022/prob2022in20.txt"));
			//in = new Scanner(new File("in2022/prob2022in21.txt"));

			 
			long count = 0;
			String line = "";
			
			ArrayList <String>lines = new ArrayList<String>();
			
			
			while(in.hasNextLine()) {
				line = in.nextLine();
				lines.add(line);
				
			}
			//180931973 (with ints)
			//2865721299243 (with longs)
			
			ArrayList<Long> longs = new ArrayList<Long>();
			for(int i=0; i<lines.size(); i++) {
				
				
				line = lines.get(i);
				
				longs.add(plong(line) * 811589153L);
				
			}
			
			
			long array[] = new long[longs.size()];
			
			int location[] = new int[longs.size()];
			for(int i=0; i<longs.size(); i++) {
				array[i] = longs.get(i);
				location[i] = i;
				
			}
			
			
			for(int numTimes=0; numTimes<10; numTimes++) {
				for(int i=0; i<longs.size(); i++) {
					
					long numMove = longs.get(i);
					
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
					if(longs.get(i) != array[curLocationNumToMove]) {
						sopl("DOH 66");
						exit(1);
					}
					
					numMove = mod(numMove, array.length - 1);
					
					for(int j=0; j<numMove; j++) {
						
						array[mod(curLocationNumToMove + j, array.length)] = array[mod(curLocationNumToMove + j + 1, array.length)];
						
						location[mod(curLocationNumToMove + j, array.length)] = location[mod(curLocationNumToMove + j + 1, array.length)];
					}
	
					array[mod(curLocationNumToMove + numMove, array.length)] = longs.get(i);
					
					location[mod(curLocationNumToMove + numMove, array.length)] = i;
					
					
				}
			}
			
			
			boolean taken[] = new boolean[array.length];
			int numTaken = 0;
			for(int k=0; k<longs.size(); k++) {
				for(int j=0; j<longs.size(); j++) {
					
					if(! taken[j] && longs.get(k) == array[j]) {
						taken[j] = true;
						numTaken++;
						break;
					}
				}
			}
			sopl("Array length: " + numTaken);

			for(int k=0; k<longs.size(); k++) {
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
			
			long num1 = array[mod(location0 + 1000, array.length)];
			long num2 = array[mod(location0 + 2000, array.length)];
			long num3 = array[mod(location0 + 3000, array.length)];

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
	
	public static int mod(long num, long mod) {
		
		int tmp = (int)(num % mod);
		if(tmp < 0) {
			
			int tmp2 = (int)(tmp + mod);
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
