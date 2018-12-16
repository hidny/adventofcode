package probs2018;

import java.io.File;

import number.IsNumber;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

import utils.Mapping;

public class prob16part2 {

	
	public static void main(String[] args) {
		Scanner input;
		try {
			 input = new Scanner(new File("in2018/prob2018in16.txt"));
			
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
			
			while(input.hasNextLine()) {
				line = input.nextLine();
				lines.add(line);
				
			}
			
			
			boolean pos[][] = new boolean[16][16];
			for(int i=0; i<pos.length; i++) {
				for(int j=0; j<pos[0].length; j++) {
					pos[i][j] = true;
				}
			}
			
			
			for(int i=0; i<lines.size(); i++) {
				line = lines.get(i);
				
				if(line.contains("Before: ") ) {
					//sopl(line);
					
					
					String state1 = line.split("\\[")[1].split("\\]")[0];
					
					i++;
					line = lines.get(i);
					String command = line;

					i++;
					line = lines.get(i);
					String state2 = (line.split("\\[")[1]).split("\\]")[0];
				
					//sopl(state1);
					//sopl(command);
					//sop(state2);
					
					int indexCommand = pint(command.split(" ")[0]);
					//sop(indexCommand);
					//sopl("---");
					
					for(int j=0; j<16; j++) {
						if(matches(j, state1, command, state2) == false) {
							pos[indexCommand][j] = false;
						}
					}
					
					
				}
			}
			
			for(int i=0; i<pos.length; i++) {
				for(int j=0; j<pos[0].length; j++) {
					if(pos[i][j]) {
						System.out.print("1 ");
					} else {
						System.out.print("0 ");
					}
				}
				sopl("");
			}
			sopl("");
			
			
			
			sopl("Start");
			String perm[] = new String[] {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15"};
			
			
			
			
			long numPerm = utilsPE.Permutation.getSmallFactorial(16);
			
			sopl(numPerm);
			
			long jump = 1;
			String order[] = new String[1];
			
			int debug = 0;
			
			long permIndex=0;
			
			for(; permIndex<numPerm; permIndex+=jump) {
				
				//sopl(permIndex);
				
				order = utilsPE.Permutation.generatePermutation(perm, permIndex);
				
				boolean isBroken = false;
				
				for(int i=0; i<order.length; i++) {
					if(pos[i][pint(order[i])] == false) {
						isBroken = true;
						//sopl("JUMP " + i);
						jump = utilsPE.Permutation.getSmallFactorial(15 - i);
						break;
					}
				}
				

					
				if(isBroken == false) {
					sopl("Found possible combo:");
					for(int j=0; j<order.length; j++) {
						sopl(order[j] + " ");
					}
					break;
				}
				
			}
			sopl(permIndex + " vs " + numPerm);
			
			int reg[] = new int[4];
			reg[0] = 0;
			reg[1] = 0;
			reg[2] = 0;
			reg[3] = 0;
			
			for(int lineIndex=3137; lineIndex<lines.size(); lineIndex++) {
				line = lines.get(lineIndex);
				if(line.trim().equals("")) {
					continue;
				}
				
				String regCommandString[] = line.split(" ");
				int regCommand[] = new int[regCommandString.length];
				for(int i=0; i<regCommand.length ; i++) {
					regCommand[i] = pint(regCommandString[i].trim());
				}
				
				
				reg = doCommand(pint(order[regCommand[0]]), reg, regCommand );
				
			}
			
			sopl("Answer: " + reg[0]);
			input.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	
	
	public static boolean matches(int index, String state1, String command, String state2) {
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
		
		int temp[] = doCommand(index, regS, regCommand );
		
		return checkEq(temp, regF);
		
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
