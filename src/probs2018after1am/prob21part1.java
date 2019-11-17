package probs2018after1am;

import java.io.File;

import number.IsNumber;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

import utils.Mapping;

public class prob21part1 {

	
	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2018/prob2018in21.txt"));
			
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
			
			long min = 9107763;
			
			int origCount = 0;
			for(int i=0; i<lines.size(); i++) {
				
			}
			

			
			long registers[] = new long[6];
			registers[0] = 0;
			
			
			int ipReg = 2;
			
			long mark = -1;
			long possibleAnswer = -1;
			
			Scanner input = new Scanner(System.in);
			//registers[ipReg]
			
			for(; registers[ipReg]>=0 && registers[ipReg]<lines.size() - 1; registers[ipReg]++) {
				
				sopl("line " + (2*registers[ipReg] + 3) + ":");
				if((2*registers[ipReg] + 3) == 59) {
					
					break;
				}
				
				
				String regCommandString[] = lines.get((int)registers[ipReg] + 1).split(" ");
				
				int regCommand[] = new int[regCommandString.length];
				for(int i=1; i<regCommand.length; i++) {
					regCommand[i] = pint(regCommandString[i]);
				}
				
				registers = doCommand(regCommandString[0], registers, regCommand );
				
				
				//Print state of registers:
				for(int i=0; i<6; i++) {
					if(i == ipReg) {
						sopl(registers[i] + "*");
					} else {
						sopl(registers[i]);
					}
				}
				
				sopl();
				sopl();
				//input.next();
				
			}
			
			
			sopl("Answer for part 1: " + registers[3]);
			
			
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}

	public static long[] resetTemp(long regS[]) {
		long temp[] = new long[regS.length];
		
		for(int i=0; i<temp.length; i++) {
			temp[i] = regS[i];
		}
		return temp;
	}
	public static long[] doCommand(String cmd, long regS[], int regCommand[] ) {

		long temp[];
		temp = resetTemp(regS);
		
		//sopl(cmd);
		
		if(cmd.equals("addr") ) {
			//add r:
			temp[regCommand[3]] = temp[regCommand[1]] + temp[regCommand[2]];
			
		} else if(cmd.equals("addi")) {
			
			temp[regCommand[3]] = temp[regCommand[1]] + regCommand[2];
			
		} else if(cmd.equals("mulr")) {
			temp[regCommand[3]] = temp[regCommand[1]] * temp[regCommand[2]];
			
		} else if(cmd.equals("muli")) {
			temp[regCommand[3]] = temp[regCommand[1]] * regCommand[2];
			
		} else if(cmd.equals("banr")) {
			temp[regCommand[3]] = temp[regCommand[1]] & temp[regCommand[2]];
			
		} else if(cmd.equals("bani")) {
			temp[regCommand[3]] = temp[regCommand[1]] & regCommand[2];
			
		} else if(cmd.equals("borr")) {
			temp[regCommand[3]] = temp[regCommand[1]] | temp[regCommand[2]];
			
		} else if(cmd.equals("bori")) {
			temp[regCommand[3]] = temp[regCommand[1]] | regCommand[2];
			
		} else if(cmd.equals("setr")) {
			temp[regCommand[3]] = temp[regCommand[1]];
			
		} else if(cmd.equals("seti")) {
			temp[regCommand[3]] = regCommand[1];
			
		} else if(cmd.equals("gtir")) {
			if(regCommand[1] > temp[regCommand[2]]) {
				temp[regCommand[3]] = 1;
			} else {
				temp[regCommand[3]] = 0;
			}
			
		} else if(cmd.equals("gtri")) {
			if(temp[regCommand[1]] > regCommand[2]) {
				temp[regCommand[3]] = 1;
			} else {
				temp[regCommand[3]] = 0;
			}
			
		} else if(cmd.equals("gtrr")) {
			if(temp[regCommand[1]] > temp[regCommand[2]]) {
				temp[regCommand[3]] = 1;
			} else {
				temp[regCommand[3]] = 0;
			}
			
		} else if(cmd.equals("eqir")) {
			if(regCommand[1] == temp[regCommand[2]]) {
				temp[regCommand[3]] = 1;
			} else {
				temp[regCommand[3]] = 0;
			}
			
		} else if(cmd.equals("eqri")) {
			if(temp[regCommand[1]] == regCommand[2]) {
				temp[regCommand[3]] = 1;
			} else {
				temp[regCommand[3]] = 0;
			}
			
		} else if(cmd.equals("eqrr")) {
			if(temp[regCommand[1]] == temp[regCommand[2]]) {
				temp[regCommand[3]] = 1;
			} else {
				temp[regCommand[3]] = 0;
			}
		} else if(cmd.equals("bani")) {
			exit(1);
			sopl("???");
		} else {
			exit(1);
			sopl("???");
		}
		return temp;
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
