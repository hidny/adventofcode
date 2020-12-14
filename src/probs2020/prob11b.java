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

public class prob11b {

	
	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2020/prob2020in11.txt"));
			 //in = new Scanner(new File("in2020/prob2020in11.txt.test"));
			int numTimes = 0;
			 
			int count = 0;
			boolean part2 = false;

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
			ArrayList <String>lines2 = new ArrayList<String>();
			
			
			while(in.hasNextLine()) {
				String line = in.nextLine();
				lines.add(line);
				lines2.add(line);
				
				sopl(line);
				
			}
			sopl();

			while(true) {
				for(int i=0; i<lines2.size(); i++) {
					for(int j=0; j<lines2.get(i).length(); j++) {
						
						if(lines.get(i).charAt(j) == '.') {
							continue;
						} else if(lines.get(i).charAt(j) == 'L') {
							
							int numAdj = numAdjSee(i, j, lines);
							
							if(numAdj == 0) {
								char array[] = lines2.get(i).toCharArray();
								array[j] = '#';
								lines2.set(i, String.valueOf(array));
							}
						} else if(lines.get(i).charAt(j) == '#') {
							
							int numAdj = numAdjSee(i, j, lines);
							
							if(numAdj >= 5) {
								char array[] = lines2.get(i).toCharArray();
								array[j] = 'L';
								lines2.set(i, String.valueOf(array));
							}
						}
					}
				}
				
				
				for(int i=0; i<lines2.size(); i++) {
					for(int j=0; j<lines2.get(i).length(); j++) {
						sop(lines2.get(i).charAt(j));
					}
					sopl();
				}

				sopl();

				boolean matches = true;
				for(int i=0; i<lines2.size(); i++) {
					for(int j=0; j<lines2.get(i).length(); j++) {
						if(lines.get(i).charAt(j) != lines2.get(i).charAt(j)) {
							matches = false;
							break;
						}
					}
				}
				
				if(matches) {
					break;
				}
				
				
				lines = lines2;
				
				lines2 = new ArrayList<String>();
				for(int i=0; i<lines.size(); i++) {
					
					char array[] = lines.get(i).toCharArray();
					
					lines2.add(String.valueOf(array));
				}
				
				
			}
			
			/*
			 * 			for(int i=0; i<lines.size(); i++) {
				
				//int temp = Integer.parseInt(lines.get(i));
				//count+=temp;
				
				String line = lines.get(i);
				
				ints.add(pint(line));
			}
			
			Object sorted2[] = utils.Sort.sortList(ints);
			
			 */
			
			//number.IsNumber.isLong(TODO);
			
			
			
			
			for(int i=0; i<lines2.size(); i++) {
				for(int j=0; j<lines2.get(i).length(); j++) {
					if(lines2.get(i).charAt(j) == '#') {
						count++;
					}
				}
			}
			
			
			sopl("Answer: " + count);
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	
	public static int numAdjSee(int i, int j, ArrayList<String> lines) {
		int numAdj = 0;
		for(int i2=i-1; i2<=i+1; i2++) {
			if(i2 < 0 || i2 >= lines.size()) {
				continue;
			}
			for(int j2=j-1; j2<=j+1; j2++) {
				
				if(i==i2 && j==j2) {
					continue;
				}
				
				if(j2 < 0 || j2 >= lines.get(i).length()) {
					continue;
				}
				
				int j3 = j2;
				int i3 = i2;
				while(i3 >= 0 && j3 >= 0 && i3<lines.size() && j3 < lines.get(i3).length()) {
					if(lines.get(i3).charAt(j3) == '#') {
						numAdj++;
						break;
					} else if(lines.get(i3).charAt(j3) == 'L') {
						break;
					}
					i3 += (i2-i);
					j3 += (j2-j);
				}
				
				
			}
		}
		return numAdj;
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
	
	public static void exit() {
		exit(0);
	}
	public static void exit(int code) {
		sop("Exit with code " + code);
		
		System.exit(code);
	}

}
