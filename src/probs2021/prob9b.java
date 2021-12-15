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

public class prob9b {

	
	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2021/prob2021in9.txt"));
			 //in = new Scanner(new File("in2021/prob2021in11.txt"));
			int numTimes = 0;
			 
			int answer = 1;
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
			
			boolean basinLocations[][] = new boolean[lines.size()][lines.get(0).length()];
			
			boolean progress = true;
			
			while(progress == true) {
				progress = false;
				
				for(int i=0; i<lines.size(); i++) {
					
					for(int j=0; j<lines.get(i).length(); j++) {
						
						if(basinLocations[i][j] == true) {
							continue;
						}
						
						int cur = pint(lines.get(i).charAt(j) + "");
						
						boolean flowOut = false;
						if(i > 0 && pint(lines.get(i-1).charAt(j) + "") < cur && basinLocations[i-1][j] == false) {
							flowOut= true;
						} else if(i<lines.size() - 1 && pint(lines.get(i+1).charAt(j) + "") < cur && basinLocations[i+1][j] == false) {
							flowOut = true;
						} else if(j > 0 && pint(lines.get(i).charAt(j-1) + "") < cur && basinLocations[i][j-1] == false) {
							flowOut = true;
						} else if(j+1<lines.get(i).length() && pint(lines.get(i).charAt(j+1) + "") < cur && basinLocations[i][j+1] == false) {
							flowOut = true;
						}
						
						if(cur == 9) {
							flowOut = true;
						}
						
						if(flowOut == false) {
							basinLocations[i][j] = true;
							progress = true;
						}
					}
					
				}
				

				
				for(int i=0; i<lines.size(); i++) {
					
					for(int j=0; j<lines.get(i).length(); j++) {
						if(basinLocations[i][j]) {
							sop("#");
						} else {
							sop(" ");
						}
					}
					sopl();
				}
				sopl();
			}
			
			
			int threeLargest[] = new int[3];
			
			boolean taken[][] = new boolean[basinLocations.length][basinLocations[0].length];
			
			for(int i=0; i<lines.size(); i++) {
				
				for(int j=0; j<lines.get(i).length(); j++) {
					
					if(taken[i][j] == false && basinLocations[i][j]) {
						taken[i][j] = true;
						
						
						boolean progress2 = true;
						int size = 1;
						
						while(progress2) {
							progress2 = false;
							for(int i2=0; i2<lines.size(); i2++) {
								
								for(int j2=0; j2<lines.get(i2).length(); j2++) {
									
									if(taken[i2][j2] == true) {
										continue;
									}
									if(basinLocations[i2][j2] == false) {
										continue;
									}
									
									boolean touching = false;
									if(i2 > 0 && taken[i2-1][j2]) {
										touching= true;
									} else if(i2<lines.size() - 1 && taken[i2+1][j2]) {
										touching = true;
									} else if(j2 > 0 && taken[i2][j2-1]) {
										touching = true;
									} else if(j2+1<lines.get(i2).length() && taken[i2][j2+1]) {
										touching = true;
									}
									
									if(touching) {
										progress2 = true;
										size++;
										taken[i2][j2] = true;
									}
								}
							}
							
						}
						
						
						
						sopl(size);
						
						if(size >= threeLargest[0]) {
							threeLargest[2] = threeLargest[1];
							threeLargest[1] = threeLargest[0];
							threeLargest[0] = size;
						} else if(size >= threeLargest[1]) {
							threeLargest[2] = threeLargest[1];
							threeLargest[1] = size;
						} else if(size >= threeLargest[0]) {
							threeLargest[2] = size;
							
						}
						
						//answer *= size;
					}
				}
			}
			answer = threeLargest[2] * threeLargest[1] * threeLargest[0];
			
			sopl("Answer: " + answer);
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
