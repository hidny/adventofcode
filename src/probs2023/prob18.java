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

public class prob18 {

	//day1 part 1
	//2:38.01
	
	public static void main(String[] args) {
		Scanner in;
		try {
			in = new Scanner(new File("in2023/prob2023in18.txt"));
			//in = new Scanner(new File("in2023/prob2023in0.txt"));
			int numTimes = 0;
			 
			int count = 0;
			boolean part2 = false;
			String line = "";

			Stack stack = new Stack();
			HashSet set = new HashSet();
			
			
			Hashtable<Long, Integer> trail = new Hashtable<Long, Integer>();
			
			ArrayList <String>lines = new ArrayList<String>();
			
			
			int LIMIT = 1000;
			
			
			//dir: 0 up
			//1 right
			//2 down
			//3 left
			
			while(in.hasNextLine()) {
				line = in.nextLine();
				lines.add(line);
				
			}

			int most = 0;
			int most2 = 0;
			int most3 = 0;
			long cur = 0L;
			ArrayList ints = new ArrayList<Integer>();
			

			int table[][] = new int[LIMIT][LIMIT];
			
			int curi = LIMIT/2;
			int curj = LIMIT/2;
			
			
			for(int i=0; i<lines.size(); i++) {
				
				line = lines.get(i);
				sopl(line);
				
				
				String token[] = line.split(" ");
				
				char dir = token[0].charAt(0);
				
				int amount = pint(token[1]);

				table[curi][curj] = 1;
				for(int j=0; j<amount; j++) {
					if(dir == 'U') {
						
						curi--;
						
					} else if(dir == 'R') {
						curj++;
					} else if(dir == 'D') {
						curi++;
						
					} else if(dir == 'L') {
						curj--;
					}
					table[curi][curj] = 1;
				}
			}
			
			
			START:
			for(int i=0; i<100; i++) {
				for(int j=0; j<100; j++) {
					
					if(table[i][j] == 0) {
						curi = i;
						curj=j;
						
						break START;
					}
				}
			}
			for(int i=0; i<100; i++) {
				for(int j=0; j<100; j++) {
					sop(table[i][j]);
				}
				sopl();
			}
			sopl();
			LinkedList queue = new LinkedList();
			
			queue.add(curi + "," + curj);
			
			boolean explored[][] = new boolean[table.length][table[0].length];
			
			while(queue.isEmpty() == false) {
				
				String next = (String)queue.remove();
				
				int nextI = pint(next.split(",")[0]);
				int nextJ = pint(next.split(",")[1]);
				
				if(table[nextI][nextJ] == 0) {
					table[nextI][nextJ] = 2;
				
					if(nextI > 0 && explored[nextI-1][nextJ] == false) {
						queue.add((nextI - 1) + "," + nextJ);
						explored[nextI-1][nextJ]  = true;
					}
					
					if(nextJ > 0 && explored[nextI][nextJ-1] == false) {
						queue.add(nextI + "," + (nextJ - 1));
						explored[nextI][nextJ-1] = true;
					}
					
					if(nextI < table.length - 1 && explored[nextI+1][nextJ] == false) {
						queue.add((nextI + 1) + "," + nextJ);
						explored[nextI+1][nextJ]  = true;
					}
					

					if(nextJ < table[0].length - 1 && explored[nextI][nextJ+1] == false) {
						queue.add(nextI + "," + (nextJ + 1));
						explored[nextI][nextJ+1]  = true;
					}
				}
				
				//sopl(queue.size());
			}
			
			cur = 0;
			long cur2 = 0;
			for(int i=0; i<table.length; i++) {
				for(int j=0; j<table[0].length; j++) {
					
					if(table[i][j] == 1) {
						cur++;
						cur2++;
					} else if(table[i][j] == 2) {
						cur2++;
					} else {
						cur++;
					}
				}
			}

			sopl("Answer: " + cur + " or " + cur2);
			in.close();
			for(int i=0; i<100; i++) {
				for(int j=0; j<100; j++) {
					sop(table[i][j]);
				}
				sopl();
			}
			sopl();
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
