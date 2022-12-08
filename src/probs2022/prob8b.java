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

public class prob8b {

	
	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2022/prob2022in8.txt"));
			// in = new Scanner(new File("in2022/prob2022in9.txt"));
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

			boolean taken[][] = new boolean[lines.size()][lines.get(0).length()];
			ArrayList ints = new ArrayList<Integer>();
			for(int i=1; i<lines.size()-1; i++) {
				
				for(int j=1; j<lines.get(0).length()-1; j++) {
					
					int curHeight = pint(lines.get(i).charAt(j) + "");
					
					int right = 1;
					for(int k=j+1; k < lines.get(0).length(); k++) {
						
						int h = pint(lines.get(i).charAt(k) + "");
						
						if(h < curHeight) {
							right++;
						} else{

							break;
						}
						

						if(k == lines.get(0).length() - 1) {
							right--;
						}
					}
					
					int left = 1;
					for(int k=j-1; k >= 0; k--) {
						
						int h = pint(lines.get(i).charAt(k) + "");
						
						if(h < curHeight) {
							left++;
						} else{

							break;
						}
						
						if(k == 0) {
							left--;
						}
					}
					

					int up = 1;
					for(int k=i-1; k >= 0; k--) {
						
						int h = pint(lines.get(k).charAt(j) + "");
						
						if(h < curHeight) {
							up++;
						} else{

							break;
						}
						if(k == 0) {
							up--;
						}
					}

					int down = 1;
					for(int k=i+1; k < lines.size(); k++) {
						
						int h = pint(lines.get(k).charAt(j) + "");
						
						if(h < curHeight) {
							down++;
						} else{

							break;
						}
						
						if(k == lines.size() -1) {
							down--;
						}
					}
					
					sopl(i + " " + j+  ":" + up + " " + left + " " + down+ " " + right);
					int score = right * left * up * down;
					
					if(score > count) {
						count = score;
					}
				}//4583440
 				
			}
			//4698026
			
			
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
