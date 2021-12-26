package probs2021;
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

public class prob25 {

	
	public static void main(String[] args) {
		Scanner in;
		try {
			// in = new Scanner(new File("in2021/prob2021in25.txt"));
			// in = new Scanner(new File("in2021/prob2021in26.txt"));
			 in = new Scanner(new File("in2021/prob2021in25.txt"));
			int numTimes = 0;
			 
			int count = 0;
			boolean part2 = false;

			LinkedList queue = new LinkedList();
			Stack stack = new Stack();
			HashSet set = new HashSet();
			
			
			Hashtable<Long, Integer> trail = new Hashtable<Long, Integer>();
			
			ArrayList <String>lines = new ArrayList<String>();
			
			
			//int LIMIT = 20000;
			//boolean table[][] = new boolean[LIMIT][LIMIT];
			
			
			//dir: 0 up
			//1 right
			//2 down
			//3 left
			
			while(in.hasNextLine()) {
				String line = in.nextLine();
				lines.add(line);
				
				
			}
		
			
			int array[][] = new int[lines.size()][lines.get(0).length()];
			for(int i=0; i<array.length; i++) {
				for(int j=0; j<array[0].length; j++) {
					if(lines.get(i).charAt(j) == '>') {
						array[i][j] = 1;
					} else if(lines.get(i).charAt(j) == 'v') {
						array[i][j] = 2;
						
					} else {
						array[i][j] = 0;
					}
					
					
				}
			}
			
			
			int num1 = 0;
			for(int i=0; i<array.length; i++) {
				for(int j=0; j<array[0].length; j++) {
					
					sop(array[i][j]);
				}
				sopl();
			}
			
			sopl();
			sopl(num1);
			
			
			boolean notDone = true;
			
			for(int n=0; notDone; n++) {
			
				int prev[][] = array; 
			
				array = new int[lines.size()][lines.get(0).length()];
				
				
				//Left:
				for(int i=0; i<array.length; i++) {
					for(int j=0; j<array[0].length; j++) {
						
						if(prev[i][j] == 1 && prev[i][(j+1) % prev[0].length] == 0) {
							array[i][(j+1) % prev[0].length] = 1;
						} else if(prev[i][j] > 0) {
							array[i][j] = prev[i][j];
						}
						
						
					}
				}
				
				//sopl("after right:");
				for(int i=0; i<array.length; i++) {
					for(int j=0; j<array[0].length; j++) {
						
						//sop(array[i][j]);
					}
					//sopl();
				}
				//sopl("done:");
				
				int prev2[][] = array;
				array = new int[lines.size()][lines.get(0).length()];
				
				
				//Down:
				for(int i=0; i<array.length; i++) {
					for(int j=0; j<array[0].length; j++) {
						
						if(prev2[i][j] == 2 && prev2[(i+1)%array.length][j] == 0) {
							array[(i+1)%array.length][j] = 2;
						} else if(prev2[i][j] > 0) {
							array[i][j] = prev2[i][j];
						}
						
						
					}
				}
				
				sopl("after down:");
				for(int i=0; i<array.length; i++) {
					for(int j=0; j<array[0].length; j++) {
						
						//sop(array[i][j]);
					}
					//sopl();
				}
				sopl("done:");
				
				num1 = 0;
				notDone = false;
				for(int i=0; i<array.length; i++) {
					for(int j=0; j<array[0].length; j++) {
						if(array[i][j] != prev[i][j]) {
							notDone = true;
						}
						if(array[i][j] > 0 && prev[i][j] == 0) {
							num1++;
						} else if(prev[i][j] > 0 && array[i][j] ==0) {
							num1--;
						}
						//sop(array[i][j]);
					}
					//sopl();
				}
				
				sopl();
				sopl(num1);
				if(notDone == false) {
					sopl("Answer: " + (n+1));
				}
				
			}
			
			sopl("Answer: " + count);
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
