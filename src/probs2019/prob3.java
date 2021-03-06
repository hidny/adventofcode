package probs2019;
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

public class prob3 {

	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2019/prob2019in3.txt"));
			 //in = new Scanner(new File("in2019/prob2019in3.txt.test"));
			int numTimes = 0;
			 
			int count = 0;
			boolean part2 = false;
			String line = "";

			LinkedList queue = new LinkedList();
			Stack stack = new Stack();
			HashSet set = new HashSet();
			
			Mapping dict = new Mapping();
			ArrayList <String>lines = new ArrayList<String>();
			
			
			
			
			Hashtable<Long, Integer> trail = new Hashtable<Long, Integer>();
			
			//dir: 0 up
			//1 right
			//2 down
			//3 left
			
			while(in.hasNextLine()) {
				line = in.nextLine();
				lines.add(line);
				
				
			}
			//START
			
			String cmds1[] = lines.get(0).split(",");
			String cmds2[] = lines.get(1).split(",");
			
			
			
			int currentj=0;
			int currenti = 0;
			
			for(int i=0; i<cmds1.length; i++) {
				int num = pint(cmds1[i].substring(1));
				
				if(cmds1[i].startsWith("R")) {
					for(int j=1; j<=num; j++) {
						currentj++;
						set.add(new Integer(100000* currenti + currentj));
					}
					
				} else if(cmds1[i].startsWith("D")) {
					for(int j=1; j<=num; j++) {
						currenti++;
						set.add(new Integer(100000* currenti + currentj));
					}
					
				} else if(cmds1[i].startsWith("U")) {
					for(int j=1; j<=num; j++) {
						currenti--;
						set.add(new Integer(100000* currenti + currentj));
					}
					
				} else if(cmds1[i].startsWith("L")) {
					for(int j=1; j<=num; j++) {
						currentj--;
						set.add(new Integer(100000* currenti + currentj));
					}
					
				} else {
					sopl("h");
					exit(1);
				}
			}

			currentj=0;
		    currenti = 0;
			int answer =10000000;
			
			for(int i=0; i<cmds2.length; i++) {
				int num = pint(cmds2[i].substring(1));
				
				if(cmds2[i].startsWith("R")) {
					for(int j=1; j<=num; j++) {
						currentj++;
						if(set.contains(new Integer(100000* currenti + currentj))) {
							int temp = Math.abs(currenti) + Math.abs(currentj);
							
							if(temp < answer) {
								answer = temp;
							}
						}
					}
					
				} else if(cmds2[i].startsWith("D")) {
					for(int j=1; j<=num; j++) {
						currenti++;
						if(set.contains(new Integer(100000* currenti + currentj))) {
							int temp = Math.abs(currenti) + Math.abs(currentj);
							
							if(temp < answer) {
								answer = temp;
							}
						}
					}
					
				} else if(cmds2[i].startsWith("U")) {
					for(int j=1; j<=num; j++) {
						currenti--;
						if(set.contains(new Integer(100000* currenti + currentj))) {
							int temp = Math.abs(currenti) + Math.abs(currentj);
							
							if(temp < answer) {
								answer = temp;
							}
						}
					}
					
				} else if(cmds2[i].startsWith("L")) {
					for(int j=1; j<=num; j++) {
						currentj--;
						if(set.contains(new Integer(100000* currenti + currentj))) {
							int temp = Math.abs(currenti) + Math.abs(currentj);
							
							if(temp < answer) {
								answer = temp;
							}
						}
					}
					
				} else {
					sopl("h2");
					exit(1);
				}
			}
			
			
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
	
	public static void exit() {
		exit(0);
	}
	public static void exit(int code) {
		sop("Exit with code " + code);
		
		System.exit(code);
	}

}
