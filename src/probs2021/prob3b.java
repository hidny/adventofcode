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

public class prob3b {

	
	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2021/prob2021in3.txt"));
			 //in = new Scanner(new File("in2021/prob2021in3.txt.test"));
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
				String line = in.nextLine().trim();
				lines.add(line);
				
				
			}
			
			
			ArrayList ints = new ArrayList<Integer>();
			
			int array0s[] = new int[lines.get(0).length()];
			int array1s[] = new int[lines.get(0).length()];
			
			for(int i=0; i<lines.size(); i++) {
				
				//int temp = Integer.parseInt(lines.get(i));
				//count+=temp;
				
				String line = lines.get(i);
				
				for(int j=0; j<line.length(); 
						j++) {
					
					if(line.charAt(j) == '0') {
						array0s[j]++;
					} else if(line.charAt(j) == '1') {
						array1s[j]++;
					} else{
						sopl("doh");
					}
				}
				
				
				
				
			}

			String start = "";
			if(array0s[0] > array1s[0]) {
				start += "0";
			} else {
				start += "1";
			}
			//Oxy:
		
			while(start.length() < lines.get(0).length()) {
				
				
	
				int array0[] = new int[lines.get(0).length()];
				int array1[] = new int[lines.get(0).length()];
				
				for(int i=0; i<lines.size(); i++) {
					
					//int temp = Integer.parseInt(lines.get(i));
					//count+=temp;
					
					String line = lines.get(i);
					
					
					for(int j=start.length(); j<line.length(); 
							j++) {
						
						if(line.startsWith(start)) {
							if(line.charAt(j) == '0') {
								array0[j]++;
							} else if(line.charAt(j) == '1') {
								array1[j]++;
							} else{
								sopl("doh");
							}
						}
					}
					
					
				}
	
	
				if(array0[start.length()] > array1[start.length()]) {
					start += "0";
				} else {
					start += "1";
				}
				sopl(start);
			}
			
			int numOxy = 0;
			for(int i=0; i<start.length(); i++) {
				if(start.charAt(i) == '0') {
					numOxy = 2*numOxy + 0;
				} else {
					numOxy = 2*numOxy + 1;
				}
			}
			
			/////
			start = "";
			if(array0s[0] <= array1s[0]) {
				start += "0";
			} else {
				start += "1";
			}
			
			while(start.length() < lines.get(0).length()) {
				
	
				int array0[] = new int[lines.get(0).length()];
				int array1[] = new int[lines.get(0).length()];
				
				for(int i=0; i<lines.size(); i++) {
					
					//int temp = Integer.parseInt(lines.get(i));
					//count+=temp;
					
					String line = lines.get(i);
					
					
					for(int j=start.length(); j<line.length(); 
							j++) {
						
						if(line.startsWith(start)) {
							if(line.charAt(j) == '0') {
								array0[j]++;
							} else if(line.charAt(j) == '1') {
								array1[j]++;
							} else{
								sopl("doh");
							}
						}
					}
					
					
				}
	
				sopl(array0[start.length()] + "vs " + array1[start.length()]);
	
				if(array0[start.length()] == 0) {
					start += "1";
				} else if(array1[start.length()] == 0) {
					start += "0";
				} else 	if(
						array0[start.length()] <= array1[start.length()]) {
					start += "0";
				} else {
					start += "1";
				}
				sopl(start);
			}
			
			int numCO2 = 0;
			for(int i=0; i<start.length(); i++) {
				if(start.charAt(i) == '0') {
					numCO2 = 2*numCO2 + 0;
				} else {
					numCO2 = 2*numCO2 + 1;
				}
			}
			
			sopl("Answer: " + (numOxy*numCO2));
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
