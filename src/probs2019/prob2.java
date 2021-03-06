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

public class prob2 {

	
	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2019/prob2019in2.txt"));
			int numTimes = 0;
			 
			int count = 0;
			boolean part2 = false;
			String line = "";

			LinkedList queue = new LinkedList();
			Stack stack = new Stack();
			HashSet set = new HashSet();
			
			Mapping dict = new Mapping();
			ArrayList <String>lines = new ArrayList<String>();
			
			
			int LIMIT = 20000;
			boolean table[][] = new boolean[LIMIT][LIMIT];
			
			Hashtable<Long, Integer> trail = new Hashtable<Long, Integer>();
			
			//dir: 0 up
			//1 right
			//2 down
			//3 left
			
			while(in.hasNextLine()) {
				line = in.nextLine();
				lines.add(line);
				
				
			}
			
			
			
			
			for(int i=0; i<100; i++) {
				for(int j=0; j<100; j++) {
					String cmds[] = lines.get(0).split(",");
					cmds[1] = i + "";
					cmds[2] = j + "";
					int count2 = 100*i + j;
					
					int position = 0;
					
					while(pint(cmds[position]) != 99) {
						int temp = -1;
						if(pint(cmds[position]) == 1) {
							temp = pint(cmds[pint(cmds[position + 1])]) + pint(cmds[pint(cmds[position + 2])]);
						} else if(pint(cmds[position]) == 2) {
							temp = pint(cmds[pint(cmds[position + 1])]) * pint(cmds[pint(cmds[position + 2])]);
						} else {
							cmds[0] = -1 + "";
							break;
						}
						
						cmds[pint(cmds[position + 3])] = temp + "";
						
						position += 4;
					}
					
					sopl(i + " " + j + " " + cmds[0]);
					if(pint(cmds[0]) == 19690720) {

						sopl("Ans" + count2);
						exit(1);
					}
					
				}
			}
			
			
			
			
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
