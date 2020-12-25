package probs2020;
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

public class prob18bClean {

	
	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2020/prob2020in18.txt"));
			 //in = new Scanner(new File("in2020/prob2020in15.txt.test"));
			int numTimes = 0;
			 
			long count = 0;
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
			
			
			
			
			ArrayList ints = new ArrayList<Integer>();
			
			for(int i=0; i<lines.size(); i++) {
				
				//int temp = Integer.parseInt(lines.get(i));
				//count+=temp;
				
				String line = lines.get(i);
				String lineCopy = String.copyValueOf(line.toCharArray());

				count += plong(solve(line));
				
			}
			
			sopl("Answer: " + count);
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	
	
	public static String solve(String a) {
		
		a = a.trim();
		a = a.replace(" ", "");
		
		a = removeParen(a);
		
		a = removeAddition(a);
		
		return multiplyNumbers(a);
		
		
	}
	
	public static String removeParen(String a) {

		//RM paren:
		boolean stillParen = true;
		
		while(stillParen) {
			
			stillParen = false;
			
			PARENFINDER:
			//Get rid of paren:
			for(int i=0; i<a.length(); i++) {
				if(a.charAt(i) == '(') {

					stillParen = true;
					
					int numDeep = 1;
					for(int j=i+1; j<a.length(); j++) {
						if(a.charAt(j) == ')') {
							numDeep--;
							
							if(numDeep == 0) {
								
								String before = a.substring(0,i);
								
								String after;
								
								if(j+1<a.length()) {
									after = a.substring(j+1, a.length());
								} else {
									after = "";
								}
								
								sopl("Solve " + a.substring((i)+1, (j+1)-1));
								String middle = solve(a.substring((i)+1, (j+1)-1));
								
								a = before + middle + after;
								
								break PARENFINDER;
							}
							
						} else if(a.charAt(j) == '(') {
							numDeep++;
						}
					}
					
					sopl("AAHH!!");
					exit(1);
					
				}
			}
		}
		
		return a;
	}
	
	public static String removeAddition(String a) {

		a = a.trim();
		a = a.replace(" ", "");
		
		//RM add:
		boolean stillADD = true;
		
		while(stillADD) {
			
			stillADD = false;
			
			PARENFINDER:
			//Get rid of paren:
			for(int i=0; i<a.length(); i++) {
				if(a.charAt(i) == '+') {

					stillADD = true;
					

					int indexRight = a.length();
					long curNum = 0;
					for(int j=i+1; j<a.length(); j++) {
						if(a.charAt(j) >= '0' && a.charAt(j) <= '9') {
							curNum = 10 * curNum + (int)(a.charAt(j) - '0');
							
						} else {
							indexRight = j;
							break;
						}
					}

					int indexLeft = 0;
					long curNum2 = 0;
					long CUR_MULT = 1;
					long MULT = 10;
					for(int j=i-1; j>=0; j--) {
						if(a.charAt(j) >= '0' && a.charAt(j) <= '9') {
							curNum2 = curNum2 +  CUR_MULT  * (int)(a.charAt(j) - '0');
							CUR_MULT *=MULT;
						} else {
							indexLeft = j+1;
							break;
						}
					}
					
					long sum = curNum + curNum2;
					
					
					if(indexLeft >= 0) {
						a = a.substring(0, indexLeft) + (sum + "") + a.substring(indexRight);
					} else {
						a = (sum + "") + a.substring(indexRight);
					}
					sopl("Remove addition: " + a);
					
					
					
				}
			}
		}
		
		return a;
	}
	
	public static String multiplyNumbers(String a) {

		int nextIndex = -1;
		long curNum = 0;
		long CUR_MULT = 1;
		long MULT = 10;
		for(int i=a.length()-1; i>=0; i--) {
			if(a.charAt(i) >= '0' && a.charAt(i) <= '9') {
				curNum = curNum + CUR_MULT * (int)(a.charAt(i) - '0');
				
				CUR_MULT *= MULT;
			} else {
				nextIndex = i;
				break;
			}
		}
		
		if(nextIndex <= 0) {
			return curNum + "";
		} else {
			if(a.charAt(nextIndex) == '+') {
				return ((plong(multiplyNumbers(a.substring(0, nextIndex)))) + curNum) + "";
			
			} else if(a.charAt(nextIndex) == '-') {
				return (plong(multiplyNumbers(a.substring(0, nextIndex))) - curNum) + "";
				
			}else if(a.charAt(nextIndex) == '*') {
				return (plong(multiplyNumbers(a.substring(0, nextIndex))) * curNum) + "";
				
			}else if(a.charAt(nextIndex) == '/') {
				return (plong(multiplyNumbers(a.substring(0, nextIndex))) / curNum) + "";
			} else {
				sopl("AHH!");
				sopl(a);
				return "";
			}
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

	//26796147
}
