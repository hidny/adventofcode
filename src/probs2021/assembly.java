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

public class assembly {

	
	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2020/prob2020in8.txt"));
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
			
			while(in.hasNextLine()) {
				String line = in.nextLine();
				lines.add(line);
				
				
			}
			
			
			ArrayList ints = new ArrayList<Integer>();
			
			for(int i=0; i<lines.size(); i++) {
				
				
				String line = lines.get(i);
				
				if(line.trim().equals("")) {
					//Blank line means something this year...
				}
				
				
			}
			
			
			long registers[] = new long[64];
			
			
			
			boolean used[] = new boolean[lines.size()];
			int acc = 0;
			
			for(int pc=0; pc<lines.size(); pc++) {
				
				used[pc] = true;
				
				
				String cmd = lines.get(pc).toLowerCase();
				
				String token[] = cmd.split(" ");
				
				sopl(pc);
				int num1 = pint(token[1]);
				
				int num2 = -1;
				if(token.length > 2) {
					num2 = pint(token[2]);
				}
				
				if(token[1].contains("-") && num1 >=0) {
					sopl("error: " + num1 );
					exit(1);
				}
				
				if(cmd.startsWith("nop")) {
					
				} else if(cmd.startsWith("acc")) {
					
					acc += num1;
					
				} else if(cmd.startsWith("jmp")) {
					
					pc += num1;
					pc--;
					
				} else if(cmd.startsWith("beq")) {
					//TODO
					
				} else {
					sopl("Error1");
					exit(1);
				}
			}
			
			sopl("Answer: " + acc);
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
