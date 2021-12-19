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

public class prob15Corrected {

	
	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2021/prob2021in15.txt"));
			//in = new Scanner(new File("in2021/prob2021in16.txt"));
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
			
			
			ArrayList ints = new ArrayList<Integer>();
			
			
			for(int i=0; i<lines.size(); i++) {
				
				//int temp = Integer.parseInt(lines.get(i));
				//count+=temp;
				
				String line = lines.get(i);
				String token[] = line.split(" ");
				
				sopl(token[0]);
				
			}
			
			
			long riskLevel[][] = new long[lines.size()][lines.get(0).length()];

			boolean keepLooping = true;
			
			while(keepLooping) {
				keepLooping = false;
				
				for(int d=0; d<  riskLevel.length +  riskLevel[0].length; d++) {
					
					for(int i=0; i<=d; i++) {
						int j = d - i;
						
						if(i >= riskLevel.length || j>= riskLevel[0].length) {
							continue;
						}
						
						if(i==0 && j== 0) {
							riskLevel[i][j] = 0;
							continue;
						}
						
						long above = 9999999;
						if(i>0 ) {
							above = riskLevel[i-1][j];
						}
						
						long left = 999999;
						
						if(j>0 ) {
							left = riskLevel[i][j-1];
						}
						
						
						
						int cell =  pint(lines.get(i).charAt(j) + "");
						
						if(riskLevel[i][j] == 0) {
							if(above > left) {
								riskLevel[i][j] = left + cell;
							} else {
		
								riskLevel[i][j] = above + cell;
							}
						} else {
							if(above > left) {
								riskLevel[i][j] = Math.min(riskLevel[i][j], left + cell);
							} else {
		
								riskLevel[i][j] =  Math.min(riskLevel[i][j], above + cell);
							}

						}
					}
				}
				
				for(int i=1; i<riskLevel.length - 1; i++) {
					for(int j=1; j<riskLevel[0].length - 1; j++) {
						
						int irem = i % lines.size();
						int jrem = j % lines.get(0).length();
								
						int iq = i / lines.size();
						int jq = j /  lines.get(0).length();
						
						int cell =  pint(lines.get(i).charAt(j) + "");
						
						for(int i2=i; i2<=i+1; i2++) {
							for(int j2=j; j2<=j+1; j2++) {
								if(i2 != i && j2 != j) {
									continue;
								}
								if(i2== i && j2 == j) {
									continue;
								}
								if(riskLevel[i][j]  > cell + riskLevel[i2][j2]) {
									riskLevel[i][j] = cell + riskLevel[i2][j2];
									keepLooping = true;
									sopl("go back! " + i + ", " + j);
								}
								
								
							}
						}
					}
				}
				
				
					
			}
			
			
			sopl("Answer: " + riskLevel[riskLevel.length - 1][riskLevel[0].length-1]);
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
