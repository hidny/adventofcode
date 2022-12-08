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

public class prob7 {

	
	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2022/prob2022in7.txt"));
			//in = new Scanner(new File("in2022/prob2022in8.txt"));
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

			
			prob7Stuff top = new prob7Stuff("/", 0, true);
			
			prob7Stuff cur = top;
			
			ArrayList<prob7Stuff> path = new ArrayList<prob7Stuff>();
			
			path.add(top);
			
			ArrayList ints = new ArrayList<Integer>();
			for(int i=0; i<lines.size(); i++) {
				
				
				line = lines.get(i);
				String token[] = line.split(" ");
				
				if(token[0].equals("$")) {
					sopl(line);
					
					if(token[1].equals("cd")) {
						if(token[2].equals("..")) {
							path.remove(path.size() -1);
							cur = path.get(path.size() -1);
						} else {
							boolean foundIt = false;
							for(int j=0; j<cur.stuff.size(); j++) {
								sopl(token[2] +" vs " + cur.stuff.get(j).name.substring(cur.stuff.get(j).name.lastIndexOf('/') + 1));
								if(cur.stuff.get(j).name.substring(cur.stuff.get(j).name.lastIndexOf('/') + 1).equals(token[2])) {
									path.add(cur.stuff.get(j));
									cur = cur.stuff.get(j);
									foundIt = true;
								}
							}
							if(foundIt == false) {
								sopl("oops");
								cur.stuff.add(new prob7Stuff(cur.name + "/" + token[1], 0, true));
								
								for(int j=0; j<cur.stuff.size(); j++) {
									if(cur.stuff.get(j).name.equals(token[2])) {
										path.add(cur.stuff.get(j));
										cur = cur.stuff.get(j);
										foundIt = true;
									}
								}
							}
						}
					} else if(token[1].equals("ls")) {
						//nothing
					}
				} else {
					if(token[0].startsWith("dir")) {
						sopl("create dir:" + token[1]);
						cur.stuff.add(new prob7Stuff(cur.name + "/" + token[1], 0, true));
					} else {
						cur.stuff.add(new prob7Stuff(cur.name + "/" + token[1], pint(token[0]), false));

					}
				}
				
			}
			
			prob7Stuff.printTree(top);
			long sum = prob7Stuff.sumTree(top, 0);
			
			
			//6431588
			
			sopl("Answer: " + prob7Stuff.answer);
			
			long target = 30000000;
			
			
			
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
