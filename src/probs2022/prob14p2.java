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

public class prob14p2 {

	
	public static void main(String[] args) {
		Scanner in;
		try {
			in = new Scanner(new File("in2022/prob2022in14.txt"));
			// in = new Scanner(new File("in2022/prob2022in15.txt"));
			int numTimes = 0;
			 
			int count = 0;
			boolean part2 = false;
			String line = "";

			LinkedList queue = new LinkedList();
			Stack stack = new Stack();
			HashSet set = new HashSet();
			
			
			Hashtable<Long, Integer> trail = new Hashtable<Long, Integer>();
			
			ArrayList <String>lines = new ArrayList<String>();
			
			
			int LIMIT = 1000;
			boolean table[][] = new boolean[LIMIT][LIMIT];
			int MID = LIMIT/2;
			
			
			//dir: 0 up
			//1 right
			//2 down
			//3 left
			
			while(in.hasNextLine()) {
				line = in.nextLine();
				lines.add(line);
				
			}


			int highestI = 0;
			
			ArrayList ints = new ArrayList<Integer>();
			for(int i=0; i<lines.size(); i++) {
				
				
				line = lines.get(i);
				String token[] = line.split(" -> ");
				
				
				int iprev = -1;
				int jprev = -1;
				for(int j=0; j<token.length; j++) {
					int j2 = pint(token[j].split(",")[0]);
					int i2 = pint(token[j].split(",")[1]);
					
					if(i2 > highestI) {
						highestI = i2;
					}
					
					if(j> 0) {
						if(iprev != i2) {
							for(int a=Math.min(iprev, i2); a<=Math.max(iprev, i2); a++) {
								table[a][j2] = true;
							}
						} else if(jprev != j2) {
							for(int a=Math.min(jprev, j2); a<=Math.max(jprev, j2); a++) {
								table[i2][a] = true;
							}
						} else if(iprev != i2 && jprev != j2) {
							sopl("doh!");
						}
					}
					
					iprev = i2;
					jprev = j2;
					
				}

				
				
				
			}
			
			int floor = highestI + 2;
			
			for(int a=0; a<table[0].length; a++) {
				table[floor][a] = true;
			}
			
			
			int curi=0;
			int curj=500;
			
			int numSand = 0;
			
			while(table[0][500] == false) {
				//sopl(curi + "," + curj);
				//if(curj== 501) {
				//	sopl("what");
				//}
				if(table[curi+1][curj] == false) {
					curi++;
				} else if(table[curi+1][curj-1] == false) {
					curi++;
					curj--;
				} else if(table[curi+1][curj+1] == false) {
					curi++;
					curj++;
				} else {
					numSand++;
					table[curi][curj]=true;
					//sopl("HERE: " + curi + ", " + curj);
					curi=0;
					curj=500;
					
					/*sopl();
					sopl();
					for(int i=0; i<table.length; i++) {
						for(int j=0; j<table[0].length; j++) {
							if(table[i][j]) {
								sop("#");
							} else {
								sop(".");
							}
						}
						sopl();
					}*/
				}
				
			}
			sopl(curi + "," + curj);
			
			sopl("Answer: " + numSand);
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
