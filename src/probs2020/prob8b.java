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

public class prob8b {

	
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
			
			int answer = -1;
			
			boolean used[][] = new boolean[lines.size()][lines.size()];
			int acc[] = new int[lines.size()];
			
			int pc[] = new int[lines.size()];
			
			OUT:
			while(true) {
				sopl("test");
				for(int brokenInd = 0; brokenInd<lines.size(); brokenInd++) {
					
					if(pc[brokenInd] < 0 || pc[brokenInd] >= lines.size()) {
						answer = acc[brokenInd];
						break OUT;
					}
					
					sopl(brokenInd +": " + pc[brokenInd]);
					/*if(used[brokenInd][pc[brokenInd]]) {
						
						answer = acc[brokenInd];
						
						break OUT;
					} else {
						used[brokenInd][pc[brokenInd]] = true;
					}*/
					
					String cmd =  "";
					if(brokenInd == pc[brokenInd]) {
							
							cmd = lines.get(pc[brokenInd]).toLowerCase();
							if(cmd.contains("nop")) {
								cmd = cmd.replace("nop", "jmp");
							} else if(cmd.contains("jmp")) {
								cmd = cmd.replace("jmp", "nop");
									
							} else {
								//Do nothing whatever...
							}
					} else {
							cmd = lines.get(pc[brokenInd]).toLowerCase();
					}
					
					String token[] = cmd.split(" ");
					
					if(pc[brokenInd] != 0) {
						sopl(pc[brokenInd]);
					}
					
					int num = pint(token[1]);
					if(token[1].contains("-") && num >=0) {
						sopl("error: " + num );
						exit(1);
					}
					
					if(cmd.startsWith("nop")) {
						
					} else if(cmd.startsWith("acc")) {
						
						sopl("acc");
						acc[brokenInd] += num;
						
					} else if(cmd.startsWith("jmp")) {
						
						pc[brokenInd] += num;
						pc[brokenInd]--;
						
					} else {
						sopl("Error1");
						exit(1);
					}
					
					
					pc[brokenInd]++;
				}
				sopl("test");
			}
			//245
			
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
