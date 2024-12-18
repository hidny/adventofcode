package probs2024;
import java.io.File;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

import number.IsNumber;
import utils.Mapping;
import utils.Sort;

public class prob17b {

	//day1 part 1
	//2:38.01
	
	public static void main(String[] args) {
		Scanner in;
		try {
			//in = new Scanner(new File("in2024/prob2024in17.txt"));
			in = new Scanner(new File("in2024/prob2024in17.txt"));
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
			boolean table342[][] = new boolean[LIMIT][LIMIT];
			
			
			//dir: 0 up
			//1 right
			//2 down
			//3 left
			
			while(in.hasNextLine()) {
				line = in.nextLine();
				lines.add(line);
				
			}

			int most = 0;
			int most2 = 0;
			int most3 = 0;
			long cur = 0L;
			ArrayList ints = new ArrayList<Integer>();
			
			long prog[] = null;
			String fullProgString = "";
			for(int i=0; i<lines.size(); i++) {
				
				
				line = lines.get(i);
				
				if(line.startsWith("Program:")) {
					String tmp = line.split(": ")[1];
					fullProgString = tmp;
					String progString[] = tmp.split(",");
					
					prog = new long[progString.length];
					for(int j=0; j<prog.length; j++) {
						prog[j] = plong(progString[j]);
					}
				}
				
			}
			
			sopl("fullProgString: " + fullProgString);
			
			for(int i=0; i<prog.length; i++) {
				sopl(prog[i]);
			}
			
			int LIMIT_2 = (int)Math.pow(2, 13);
			String outputs[] = new String[LIMIT_2];
			
			
			int a = 0;
			for(; a<LIMIT_2; a++) {
				long reg[] = new long[3];
				reg[0] = a;
				reg[1] = 0;
				reg[2] = 0;
				/*
				reg[0] = 729;
				reg[1] = 0;
				reg[2] = 0;
				*/
				/*for(int i=0; i<3; i++) {
					sop(reg[i] + ",");
				}
				sopl();
				*/
				int ip=0;

				
				String output = "";
				//sopl();
				for(ip=0; ip<prog.length; ip+=2) {
					
					//System.out.println("ip: " + ip);
					
					long opcode = prog[ip];
					long operand = prog[ip + 1];
					
					//literal opernd
					// 7 -> 7
					
					//combo operand
					boolean isComboOperand = false;
	
					long numToUse = -1;
					if(opcode == 0 || opcode == 2 || opcode == 5 || opcode == 6 || opcode == 7 ) {
						isComboOperand = true;
					}
					
					
					if(isComboOperand) {
						if(operand <= 3) {
							numToUse = operand;
							
						} else if(operand <=6) {
							
							int index = (int)(operand - 4);
							numToUse = reg[index];
						
						} else if(operand == 7) {
							
							sopl("oops!");
							exit(1);
						}
					} else {
						numToUse = operand;
					}
					
					if(numToUse > 1000) {
						//System.out.println("Num to use: " + numToUse);
					}
			
					
					if(opcode == 0) {
						//Division pow 2
						
						long num = reg[0];
						long den = (long)Math.pow(2, numToUse);
						
						reg[0] = num / den;
						
					} else if(opcode == 1) {
						
						reg[1] = reg[1] ^ numToUse;
						
					} else if(opcode == 2) {
						
						long mod8 = numToUse % 8;
						reg[1] = mod8;
						
					} else if(opcode == 3) {
	
						if(reg[0] == 0) {
							//pass
						} else {
							
							//minus 2 to counter loop incr.
							ip = (int)(numToUse - 2);
							
						}
						
					} else if(opcode == 4) {
						
						reg[1] = reg[1] ^ reg[2];
						
					} else if(opcode == 5) {
						
						long mod8 = numToUse % 8;
						//System.out.println("output: " + mod8 + ",");
						//sop(mod8 + ",");
						//7,0,7,6,7,5,4,6,7
						
						output += mod8 + ",";
						
					} else if(opcode == 6) {
						long num = reg[0];
						long den = (long)Math.pow(2, numToUse);
						
						reg[1] = num / den;
						
					} else if(opcode == 7) {
						long num = reg[0];
						long den = (long)Math.pow(2, numToUse);
						
						reg[2] = num / den;
						
					} else {
						sopl("oops!0");
						exit(1);
					}
					
					
					/*sopl(opcode + ", " + operand);
					for(int i=0; i<3; i++) {
						sop(reg[i] + ",");
					}
					sopl();*/
				}
				
				
				/*String ans = "";
				for(int i=0; i<3; i++) {
					ans += reg[i] + ",";
				}*/

				//ans = ans.substring(0, ans.length() - 1);
				
				output = output.substring(0, output.length() - 1);
				
				outputs[a] = output;
				//if(a % 10000 == 0) {
					//sopl("Answer for a = " + a + ": (" + output + ") vs (" + fullProgString +")");
				//}
				
				if(a > 1024) {
					sopl("outputs[" + a + " ] = " + output);
					sopl("outputs[" + (a-1024) + " ] = " + outputs[a-1024]);
					sopl();
					
					if(! output.split(",")[0].equals(outputs[a-1024].split(",")[0])) {
						sopl("Oops!");
						exit(1);
					}
					
					
				}
					
				if(fullProgString.equals(output)) {
					break;
				}
			}
			
			sopl("Answer: " + a);
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
			sop("Error: (" + s + ") is not a number");
			return -1;
		}
	}
	

	public static long plong(String s) {
		if (IsNumber.isLong(s)) {
			return Long.parseLong(s);
		} else {
			sop("Error: (" + s + ") is not a number");
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
	
	public static int[][] getIntTable(ArrayList<String> lines) {
		int grid[][] = new int[lines.size()][lines.get(0).length()];
		
		for(int i=0; i<lines.size(); i++) {
			
			for(int j=0; j<grid[0].length; j++) {
				if(lines.get(i).charAt(j) == '.') {
					grid[i][j] = -1;
				} else {
					grid[i][j] = (int)(lines.get(i).charAt(j) - '0');
				}
			}
		}
		
		return grid;
	}
	

	public static char[][] getCharTable(ArrayList<String> lines) {
		char grid[][] = new char[lines.size()][lines.get(0).length()];
		
		for(int i=0; i<lines.size(); i++) {
			
			for(int j=0; j<grid[0].length; j++) {
				grid[i][j] = lines.get(i).charAt(j);

			}
		}
		
		return grid;
	}

}
