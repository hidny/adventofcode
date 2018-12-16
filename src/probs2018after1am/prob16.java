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

public class prob16 {

	
	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2018/prob2018in16.txt"));
			
			int count = 0;
			String line = "";

			ArrayList <String>lines = new ArrayList<String>();
			
			
			//dir: 0 up
			//1 right
			//2 down
			//3 left
			
			while(in.hasNextLine()) {
				line = in.nextLine();
				lines.add(line);
				
				if(line.contains("Before: ") ) {
					
					
					String state1 = line.split("\\[")[1].split("\\]")[0];
					
					line = in.nextLine();
					lines.add(line);
					String command = line;

					line = in.nextLine();
					String state2 = (line.split("\\[")[1]).split("\\]")[0];
				
					sopl(state1);
					sopl(command);
					sop(state2);
					sopl("---");
					
					int numMatches = getNumMatches(state1, command, state2);
					if(numMatches >= 3) {
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
	
	public static int getNumMatches(String state1, String command, String state2) {
		String regString[] = state1.split(",");
		int regS[] = new int[regString.length];
		for(int i=0; i<regS.length ; i++) {
			regS[i] = pint(regString[i].trim());
		}
		String regFinalString[] = state2.split(",");
		int regF[] = new int[regFinalString.length];
		for(int i=0; i<regF.length ; i++) {
			regF[i] = pint(regFinalString[i].trim());
		}
		
		String regCommandString[] = command.split(" ");
		
		int regCommand[] = new int[regCommandString.length];
		for(int i=0; i<regCommand.length ; i++) {
			regCommand[i] = pint(regCommandString[i].trim());
		}
		
		int ret =0;
		
		for(int index =0; index<16; index++) {
			int temp[] = doCommand(index, regS, regCommand );
			if(checkEq(temp, regF)) {
				ret++;
			}
		}
		
		return ret;			
	}
	
	public static int[] doCommand(int index, int regS[], int regCommand[] ) {

		int temp[];
		temp = resetTemp(regS);
		
		if(index ==0 ) {
			//add r:
			temp[regCommand[3]] = temp[regCommand[1]] + temp[regCommand[2]];
			
		} else if(index == 1) {
			
			temp[regCommand[3]] = temp[regCommand[1]] + regCommand[2];
			
		} else if(index == 2) {
			temp[regCommand[3]] = temp[regCommand[1]] * temp[regCommand[2]];
			
		} else if(index == 3) {
			temp[regCommand[3]] = temp[regCommand[1]] * regCommand[2];
			
		} else if(index == 4) {
			temp[regCommand[3]] = temp[regCommand[1]] & temp[regCommand[2]];
			
		} else if(index == 5) {
			temp[regCommand[3]] = temp[regCommand[1]] & regCommand[2];
			
		} else if(index == 6) {
			temp[regCommand[3]] = temp[regCommand[1]] | temp[regCommand[2]];
			
		} else if(index == 7) {
			temp[regCommand[3]] = temp[regCommand[1]] | regCommand[2];
			
		} else if(index == 8) {
			temp[regCommand[3]] = temp[regCommand[1]];
			
		} else if(index == 9) {
			temp[regCommand[3]] = regCommand[1];
			
		} else if(index == 10) {
			if(regCommand[1] > temp[regCommand[2]]) {
				temp[regCommand[3]] = 1;
			} else {
				temp[regCommand[3]] = 0;
			}
			
		} else if(index == 11) {
			if(temp[regCommand[1]] > regCommand[2]) {
				temp[regCommand[3]] = 1;
			} else {
				temp[regCommand[3]] = 0;
			}
			
		} else if(index == 12) {
			if(temp[regCommand[1]] > temp[regCommand[2]]) {
				temp[regCommand[3]] = 1;
			} else {
				temp[regCommand[3]] = 0;
			}
			
		} else if(index == 13) {
			if(regCommand[1] == temp[regCommand[2]]) {
				temp[regCommand[3]] = 1;
			} else {
				temp[regCommand[3]] = 0;
			}
			
		} else if(index == 14) {
			if(temp[regCommand[1]] == regCommand[2]) {
				temp[regCommand[3]] = 1;
			} else {
				temp[regCommand[3]] = 0;
			}
			
		} else if(index == 15) {
			if(temp[regCommand[1]] == temp[regCommand[2]]) {
				temp[regCommand[3]] = 1;
			} else {
				temp[regCommand[3]] = 0;
			}
		} else {
			sopl("???");
		}
		return temp;
	}
	
	
	
	public static int[] resetTemp(int regS[]) {
		int temp[] = new int[regS.length];
		
		for(int i=0; i<temp.length; i++) {
			temp[i] = regS[i];
		}
		return temp;
	}
	
	
	public static boolean checkEq(int temp[], int regF[]) {
		
		for(int i=0; i<temp.length; i++) {
			if(temp[i] != regF[i]) {
				return false;
			}
		}
		return true;
	}
	
	public static void sop(Object a) {
		System.out.println(a.toString());
	}
	public static void sopl(Object a) {
		System.out.println(a.toString());
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
