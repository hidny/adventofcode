package probs2024;
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

public class prob8 {

	//day1 part 1
	//2:38.01
	
	public static void main(String[] args) {
		Scanner in;
		try {
			in = new Scanner(new File("in2024/prob2024in8.txt"));
			//in = new Scanner(new File("in2024/prob2024in0.txt"));
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
			boolean table342[][] = new boolean[LIMIT][LIMIT];
			
			
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
			for(int i=0; i<lines.size(); i++) {
				
				
				line = lines.get(i);
				
			}
			
			

			for(int i=0; i<lines.size(); i++) {
				
				for(int j=0; j<lines.get(0).length(); j++) {
					
					
					for(int i2=0; i2<lines.size(); i2++) {
						
						
						for(int j2=0; j2<lines.get(0).length(); j2++) {
							
							if(i == i2 && j == j2) {
								continue;
							}
							//inBound(int i, int j, ArrayList<String> lines)
							int i3 = i + 2*(i2 - i);
							int j3 = j + 2*(j2 - j);
							
							if(inBound(i3, j3, lines)) {
								if(lines.get(i2).charAt(j2) == lines.get(i).charAt(j) && lines.get(i2).charAt(j2) != '.') {
									
									if(table342[i3][j3] == false) {
										table342[i3][j3] = true;
										cur++;
										//sop("#");
										sopl(i + "," + j + ": " + i2 + ", " + j2 + ":" + i3 + ", " + j3);
										
										continue;
										
									}
								}
							}
						}
						
					}

					//sop(".");
				}
				//sopl();
				
				
			}
			

			sopl("Answer: " + cur);
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	public static boolean inBound(int i, int j, ArrayList<String> lines) {
		return i >=0 && j >=0 && i<lines.size() && j<lines.get(0).length();
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
