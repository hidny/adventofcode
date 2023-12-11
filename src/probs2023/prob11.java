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

public class prob11 {

	//day1 part 1
	//2:38.01
	
	public static void main(String[] args) {
		Scanner in;
		try {
			in = new Scanner(new File("in2023/prob2023in11.txt"));
			//in = new Scanner(new File("in2023/prob2023in12.txt"));
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

			int most = 0;
			int most2 = 0;
			int most3 = 0;
			int cur = 0;
			ArrayList ints = new ArrayList<Integer>();


			ArrayList <String>lines2 = new ArrayList<String>();
			
			for(int i=0; i<lines.size(); i++) {
				
				
				line = lines.get(i);
				
				boolean expandRow = true;
				for(int j=0; j<line.length(); j++) {
					if(line.charAt(j) != '.') {
						expandRow = false;
						break;
					}
				}
				
				lines2.add(line);
				
				if(expandRow) {
					lines2.add(line);
				}
				
			}
			
			
			boolean expandCol[] = new boolean[lines.get(0).length()];
			
			for(int j=0; j<lines2.get(0).length(); j++) {
				
				boolean curExpandCol = true;
				
				for(int i=0; i<lines2.size(); i++) {
					if(lines2.get(i).charAt(j) != '.') {
						curExpandCol = false;
						break;
					}
				}
				
				expandCol[j] = curExpandCol;
			}
			
			ArrayList <String>lines3 = new ArrayList<String>();
			
			
			for(int i=0; i<lines2.size(); i++) {

				String prevLine = lines2.get(i);
				
				String curLine = "";
				int prevIndex = 0;
				for(int j=0; j<expandCol.length; j++) {
					
					if(expandCol[j]) {
						
						curLine += prevLine.substring(prevIndex, j) + ".";
						
						prevIndex = j;
					}
					
				}
				curLine += prevLine.substring(prevIndex, prevLine.length());
				
				lines3.add(curLine);
				
			}
			
			sopl();
			for(int i=0; i<lines3.size(); i++) {
				sopl(lines3.get(i));
			}
			
			cur = 0;
			int count2 = 0;
			for(int i=0; i<lines3.size(); i++) {
				
				line = lines3.get(i);
				for(int j=0; j<line.length(); j++) {
					
					if(line.charAt(j) != '.') {
						for(int i2=i; i2<lines3.size(); i2++) {
							
							String line2 = lines3.get(i2);
							
							for(int j2=0; j2<line2.length(); j2++) {
								
								
								if(i == i2 && j >= j2) {
									continue;
								} else if(line2.charAt(j2) != '.') {
									
									cur += Math.abs(i - i2) + Math.abs(j - j2);
									count2++;
									sopl(count2 + ": " +(Math.abs(i - i2) + Math.abs(j - j2)));
								}
							}
						}
						
					}
				}
			}

			sopl("Answer: " + cur);
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
