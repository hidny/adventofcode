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

public class prob7 {

	
	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2019/prob2019in7.txt"));
			 
			 //in = new Scanner(new File("in2019/prob2019in7.txt.test"));
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

			while(in.hasNextLine()) {
				line = in.nextLine();
				lines.add(line);
						
			}
			String cmds[] = lines.get(0).split(",");
			
			String perm[] =  {"0", "1", "2", "3", "4"};
			//String[] generatePermutation(String objects[], long permNumber)
			
			
			int maxSignal = -1000000;
			
			
			for(int permNumber=0; permNumber<Permutation.getSmallFactorial(perm.length); permNumber++) {
				String curPerm[] = Permutation.generatePermutation(perm, permNumber);
				
				int curInput = 0;
				for(int i=0; i<curPerm.length; i++) {
					curInput = prog(cmds, pint(curPerm[i]), curInput);
				}
				
				if(curInput > maxSignal) {
					maxSignal = curInput;
				}
				
			}
			
			sopl("Answer: " + maxSignal);
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	
	
	public static int prog(String cmds[], int phase, int input) {
		
		
		int position = 0;
		
		boolean input1Done = false;
		boolean input2Done = false;
		
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
			sopl("Test inst: " + cmds[position]);
			
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
				if(input1Done == false) {
					cmds[var1] = "" + phase;
					input1Done = true;
					
				} else if(input2Done == false) {
					cmds[var1] = "" + input;
					input2Done = true;
					
				} else {
					sopl("input ??");
					exit(1);
				}
				
				
			} else if(opCode == 4) {
				 return var1;
				
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
		
		sopl("ERROR: prog ended!");
		exit(1);
		return -1;
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
