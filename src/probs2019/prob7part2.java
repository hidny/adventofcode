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
import utilsPE.Permutation;

public class prob7part2 {

	
	public static void main(String[] args) {
		Scanner in;
		try {
			 //in = new Scanner(new File("in2019/prob2019in7.txt"));
			 
			 in = new Scanner(new File("in2019/prob2019in7.txt"));
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
			
			line = "";
			while(in.hasNextLine()) {
				line += in.nextLine();
			}
			
			String cmds[] = line.split(",");
			
			String perm[] =  {"5", "6", "7", "8", "9"};
			
			
			int maxSignal = -1000000;
			
			
			for(int permNumber=0; permNumber<Permutation.getSmallFactorial(perm.length); permNumber++) {
				String curPerm[] = Permutation.generatePermutation(perm, permNumber);
				

				//TODO: save state
				String state[][] = new String[curPerm.length][cmds.length + 2];
				
				for(int i=0; i<curPerm.length; i++) {
					//position:
					state[i][0] = "0";
					//1st input received:
					state[i][1] = "0";
					//cmds:
					for(int j=0; j<cmds.length; j++) {
						state[i][j+2] = cmds[j];
					}
				}
				//END TODO
				
				possibleOutput = -1000;
				
				progRecursive(state, curPerm, 0);
				
				if(possibleOutput > maxSignal) {
					maxSignal = possibleOutput;
				}
				
			}
			
			//Wrong Answer: 1439349076
			sopl("Answer: " + maxSignal);
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	
	
	//state:
	//position
	//cmds
	
	//1st example wrong, but 2nd right...
	
	public static int possibleOutput = -1000;
	
	
	public static void progRecursive(String state[][], String curPerm[], int input) {
		progRecursive(state, curPerm, 0, input);
	}
	
	public static void progRecursive(String state[][], String curPerm[], int index, int input) {
		
		if(state[index] == null) {
			sopl("WARN: PROG DONE");
			return;
		}
		
		int position = pint(state[index][0]);
		
		boolean inputPhaseDone = false;
		if(state[index][1].equals("1")) {
			inputPhaseDone = true;
		}
		
		
		String cmds[] = new String[state[index].length - 2];
		for(int i=0; i<cmds.length; i++) {
			cmds[i] = "" + state[index][i + 2];
		}
		
		while(pint(cmds[position]) % 100 != 99) {
			
			int lngth = 4;
			int opCode = pint(cmds[position]) % 10;
			
			if(opCode >= 3 && opCode <=4) {
				lngth = 2;

			} else if(opCode >= 5 && opCode <=6) {
				lngth = 3;

			} else {
				lngth = 4;
			}
			
			//pad with 0s...
			while(cmds[position].length() < 5) {
				cmds[position] = "0" + cmds[position];
			}
			//sopl("Test inst: " + cmds[position]);
			
			int var1 = pint(cmds[position + 1]);
			int var2 = -1;
			int var3 = -1;

			if(opCode != 3 && cmds[position].charAt(2) != '1') {
				var1 = pint(cmds[pint(cmds[position + 1])]);
			}
			
			if(lngth >=3 ) {
				var2 = pint(cmds[position + 2]);
				
				if(cmds[position].charAt(1)!= '1') {
					var2 = pint(cmds[pint(cmds[position + 2])]);
									
				}
			}

			if(lngth >=4) {
			    var3 = pint(cmds[position + 3]);
			    
			   //He said this should never happen... reading is hard :(
			    /*if(cmds[position].charAt(0) != '1') {
					var3 = pint(cmds[pint(cmds[position + 3])]);
					sopl("boo");
					
			    }*/
			}
			
			if(opCode == 1) {
				int temp = var1 + var2;
				cmds[var3] = temp + "";
				
			} else if(opCode == 2) {
				int temp = var1 * var2;
				cmds[var3] = temp + "";
				
			} else if(opCode == 3) {
				//INPUT HERE
				if(inputPhaseDone == false) {
					cmds[var1] = "" + curPerm[index];
					inputPhaseDone = true;
					
				} else{
					
					cmds[var1] = "" + input;
				}
				
			} else if(opCode == 4) {
				
				//SAVE STATE (next command)
					//position
					state[index][0] = (position+lngth) +"";
					
					//1st input received:
					if(inputPhaseDone) {
						state[index][1] = "1";
					} else {
						state[index][1] = "0";
					}
					
					//cmds:
					for(int j=0; j<cmds.length; j++) {
						state[index][j+2] = cmds[j];
					}
				//END SAVE STATE
				
				//GET OUTPUT if last amplifier... (only works if last output of last amplifier)
				if(index == curPerm.length - 1) {
					for(int i=0; i<curPerm.length; i++) {
						sop(curPerm[i] + ",");
					}
					sopl("boom: " + var1);
					possibleOutput = var1;
				}
				//END GET OUTPUT
				
				progRecursive(state, curPerm, (index + 1) % state.length, var1);
				return;

			} else if(opCode == 5) {
				if(var1 != 0) {
					position = var2;
					continue;
				}
				
			} else if(opCode == 6) {
				if(var1 == 0) {
					position = var2;
					continue;
				}

			} else if(opCode == 7) {
				if(var1 < var2) {
					cmds[var3] = "1";
					
				} else {
					cmds[var3] = "0";
				}

			} else if(opCode == 8) {
				if(var1 == var2) {
					cmds[var3] = "1";
					
				} else {
					cmds[var3] = "0";	
				}

			} else {
				sopl();
				sopl("ERROR UNKNOWN OPCODE:" + opCode);
				exit(1);

				break;
			}
			
			position += lngth;
		}
		
		state[index] = null;
		
		return;
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
	//69814864
	//vs
	//139629729

}
