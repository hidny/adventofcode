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

public class prob24 {

	
	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2021/prob2021in24.txt"));
			// in = new Scanner(new File("in2021/prob2021in25.txt"));
			// in = new Scanner(new File("in2021/prob2021in26.txt"));
			// in = new Scanner(new File("in2021/prob2021in27.txt"));
			int numTimes = 0;
			 
			int count = 0;
			boolean part2 = false;

			LinkedList queue = new LinkedList();
			Stack stack = new Stack();
			HashSet set = new HashSet();
			
			
			Hashtable<Long, Integer> trail = new Hashtable<Long, Integer>();
			
			ArrayList <String>lines = new ArrayList<String>();
			
			
			//int LIMIT = 20000;
			//boolean table[][] = new boolean[LIMIT][LIMIT];
			
			
			//dir: 0 up
			//1 right
			//2 down
			//3 left
			
			while(in.hasNextLine()) {
				String line = in.nextLine();
				lines.add(line);
				
				
			}
			
			
			ArrayList ints = new ArrayList<Integer>();
			
			String input = "79197919993985";
			              //01234567890123
			
			
			
			
			String result[] = new String[4];
			long sample[] = new long[4];
			
			for(int i=0; i<result.length; i++) {
				result[i] = "0";
			}
			//131911113571213
			//12345678901234
			
			int version[] = new int[4];
			
			int numInputUsed = 0;
			
			Hashtable<String, String> pastVars = new Hashtable<String, String>();
			
			for(int i=0; i<4; i++) {
				pastVars.put(convertIndexToVarAndVersion(i, version), "0");
				sopl(convertIndexToVarAndVersion(i, version) + " = " + pastVars.get(convertIndexToVarAndVersion(i, version)));
			}
			//exit(1);
			
			
			for(int pc=0; pc<lines.size(); pc++) {

				//int temp = Integer.parseInt(lines.get(i));
				//count+=temp;
				
				String line = lines.get(pc);
				String token[] = line.split(" ");
				

				sopl(line);
				
				String inst = token[0];

				String arg1 =  token[1];
				int arg1Index = convertToIndex(arg1);
				
				String arg2 = "0";
				if(token.length >= 3) {
					arg2 = token[2];
					
				}
				
				long secondNum = 0;
				if(arg2.length() > 0 && arg2.charAt(0) >= 'w' && arg2.charAt(0) <= 'z') {
					secondNum = sample[convertToIndex(arg2)] ;
				} else {
					secondNum = plong(arg2);
				}
				
				int arg2Index = -1;
				
				if(arg2.startsWith("-")) {
					arg2 = "(" + arg2 + ")";
				} else if(arg2.length() > 0 && arg2.charAt(0) >= 'w' && arg2.charAt(0) <= 'z') {
					
					arg2 = convertIndexToVarAndVersion(convertToIndex(arg2), version);
					 arg2Index = convertToIndex(arg2);
				}
				

				version[arg1Index]++;
				
				if(inst.equals("inp")) {
					
					
					if(numInputUsed > 0) {
						sopl("Check point: " + numInputUsed);

						printResults(result, pastVars);
						
						
						if(numInputUsed == 1) {
							result[1]="1";
							result[2]="(i0 + 7)";
							result[3]="(i0 + 7)";
							
							updatePastVarsAtCheckPoint(arg1Index, result, pastVars, version);
							
						} else if(numInputUsed == 2) {
							
							result[1]="1";
							result[2]="(26 * (i0 + 7))";
							result[3]="((i1 + 8) + 26 * (i0 + 7))";
							
							updatePastVarsAtCheckPoint(arg1Index, result, pastVars, version);
							
						} else if(numInputUsed == 3) {
							
							result[1]="1";
							result[2]="(i2 + 10)";
							result[3]="(26^2 * (i0 + 7) + 26 * (i1 + 8) + (i2 + 10) )";
							
							updatePastVarsAtCheckPoint(arg1Index, result, pastVars, version);
							
						} else if(numInputUsed == 4) {
							
							result[1]="1";
							result[2]="(i3 + 4)";
							result[3]="(26^2 * (i0 + 7) + 26 * (i1 + 8) + (i3 + 4) )";
							
							updatePastVarsAtCheckPoint(arg1Index, result, pastVars, version);
						} else if(numInputUsed == 5) {
							
							result[1]="1";
							result[2]="(i4 + 4)";
							result[3]="(26^2 * (i0 + 7) + 26 * (i1 + 8) + (i4 + 4) )";
							
							updatePastVarsAtCheckPoint(arg1Index, result, pastVars, version);
						
						} else if(numInputUsed == 6) {
							
							result[1]="1";
							result[2]="(i5 + 6)";
							result[3]="(26^3 * (i0 + 7) + 26^2 * (i1 + 8) + 26*(i4 + 4) + (i5 + 6) )";
							
							updatePastVarsAtCheckPoint(arg1Index, result, pastVars, version);
						} else if(numInputUsed == 7) {
							
							sopl("Guess:");
							result[1]="1";
							result[2]="(i6 + 11)";
							result[3]="(26^3 * (i0 + 7) + 26^2 * (i1 + 8) + 26*(i4 + 4) + (i6 + 11) )";
							
							//divide result by 26 if i5=9 and i6=1
							
							updatePastVarsAtCheckPoint(arg1Index, result, pastVars, version);
						
						} else if(numInputUsed == 8) {
							
							sopl("Guess:");
							result[1]="1";
							result[2]="(i7 + 13)";
							result[3]="(26^3 * (i0 + 7) + 26^2 * (i1 + 8) + 26*(i4 + 4) + (i7 + 13) )";
							
							//divide result by 26 if i5=9 and i6=1
							
							updatePastVarsAtCheckPoint(arg1Index, result, pastVars, version);
							
						} else if(numInputUsed == 9) {
							
							result[1]="1";
							result[2]="(i8 + 1)";
							result[3]="(26^4 * (i0 + 7) + 26^3 * (i1 + 8) + 26^2*(i4 + 4) + 26^(i7 + 13) + (i8+1) )";
							
							updatePastVarsAtCheckPoint(arg1Index, result, pastVars, version);
						
						} else if(numInputUsed == 10) {
							
							result[1]="1";
							result[2]="(i9 + 8)";
							result[3]="(26^5 * (i0 + 7) + 26^4 * (i1 + 8) + 26^3*(i4 + 4) + 26^2*(i7 + 13) + 26*(i8+1)+ (i9+8) )";
							
							updatePastVarsAtCheckPoint(arg1Index, result, pastVars, version);
						
						} else if(numInputUsed == 11) {
							
							result[1]="1";
							result[2]="(i10 + 4)";
							result[3]="(26^5 * (i0 + 7) + 26^4 * (i1 + 8) + 26^3*(i4 + 4) + 26^2*(i7 + 13) + 26*(i8+1)+ (i10+4) )";
							//result[3] = "zprev";
							updatePastVarsAtCheckPoint(arg1Index, result, pastVars, version);
						
						} else if(numInputUsed == 12) {
							
							result[1]="1";
							result[2]="(i11 + 13)";
							result[3]="(26^6 * (i0 + 7) + 26^5 * (i1 + 8) + 26^4*(i4 + 4) + 26^3*(i7 + 13) + 26^2*(i8+1)+ 26*(i10+4)+ (i11+13) )";
							//result[3] = "zprev";
							updatePastVarsAtCheckPoint(arg1Index, result, pastVars, version);
						
						} else if(numInputUsed == 13) {
							
							result[1]="1";
							result[2]="(i12 + 4)";
							result[3]="(26^6 * (i0 + 7) + 26^5 * (i1 + 8) + 26^4*(i4 + 4) + 26^3*(i7 + 13) + 26^2*(i8+1)+ 26*(i10+4)+ (i12+4) )";
							
							//result[3] = "zprev";
							updatePastVarsAtCheckPoint(arg1Index, result, pastVars, version);
						
						}
						sopl("After update:");
						printResults(result, pastVars);
						
						sopl();
						System.out.println("Sample:");
						for(int i=0; i<sample.length; i++) {
							sopl(convertIndexToVar(i) + ": " + sample[i]);
						}
						
						
						
						if(numInputUsed > 13) {
							sopl("exit0");
							exit(0);
						}
					}
					sopl();
					
					result[arg1Index] =  "i" + numInputUsed;
					sample[arg1Index] = pint(input.charAt(numInputUsed) + "");
					
					numInputUsed++;
					
				} else if(inst.equals("add")) {
					
					if(arg2.equals("0") || (arg2Index != -1 && result[arg2Index].equals("0"))) {
						result[arg1Index] = result[arg1Index];
						
					} else if(result[arg1Index].equals("0")) {
						result[arg1Index] = arg2;
						
					} else {
						result[arg1Index] = "(" + result[arg1Index] + " + " + arg2 + ")";
					}
					
					sample[arg1Index] += secondNum;
					
					
				} else if(inst.equals("mul")) {
					
					
					
					if(result[arg1Index].equals("0") || arg2.equals("0") || (arg2Index != -1 && result[arg2Index].equals("0")) ) {
						result[arg1Index] = "0";
						sopl("Multiply by 0");
					} else {
						
						result[arg1Index] = "(" + result[arg1Index] + " * " + arg2 + ")";
						
						/*
						sopl("No mult by 0");
						sopl("result 1: " + result[arg1Index]);
						sopl("result 2: " + result[arg2Index]);
						sopl("arg2 index: " + arg2Index);
						sopl("arg2: " + arg2);
						*/
						
					}
					
					sample[arg1Index] *= secondNum;
					

					
				} else if(inst.equals("div")) {

					if(arg1.equals("0") || result[arg1Index].equals("0")) {
						result[arg1Index] = "0";
						sopl("Dividing 0");
						
					} else if(arg2.equals("1") || (arg2Index != -1 && result[arg2Index].equals("1"))) {
						sopl("Dividing by 1");
						
					} else {
						result[arg1Index] = "(" + result[arg1Index] + " / " + arg2 + ")";
					}
					
					if(secondNum == 0) {
						sopl("DIVIDED BY ZERO");
						exit(1);
					}
					
					
					sample[arg1Index] /= secondNum;
					

					
				} else if(inst.equals("mod")) {
					
					if(arg1.equals("0") || result[arg1Index].equals("0")) {
						result[arg1Index] = "0";
						sopl("Finding remainder of 0");
						
					} else if(arg2.equals("1") || (arg2Index != -1 && result[arg2Index].equals("1"))) {
						sopl("remainder of 1");
						result[arg1Index] = "0";
						
					} else {
						result[arg1Index] = "(" + result[arg1Index] + " % " + arg2 + ")";
						
						sopl("Why not 0: " + pastVars.get(arg1));
					}
					
					if(secondNum == 0) {
						sopl("DIVIDED BY ZERO in % calc");
						exit(1);
					}
					sample[arg1Index] = sample[arg1Index] % secondNum;
					

					
				} else if(inst.equals("eql")) {
					result[arg1Index] = "(" + result[arg1Index] + " == " + arg2 + ")";
					
					if(sample[arg1Index] == secondNum) {
						sample[arg1Index] = 1;
					} else {

						sample[arg1Index] = 0;
					}
					

					
				}
				
				if(pastVars.get(convertIndexToVarAndVersion(arg1Index, version)) != null) {
					sopl("Already there!");
					exit(1);
				}
				pastVars.put(convertIndexToVarAndVersion(arg1Index, version), result[arg1Index]);
				
				sopl(convertIndexToVarAndVersion(arg1Index, version) + " = " + pastVars.get(convertIndexToVarAndVersion(arg1Index, version)));

				if(result[arg1Index].contains(convertIndexToVarAndVersion(arg1Index, version))) {
					sopl("DOH!");
					exit(1);
				}
				
			}
			
			sopl();
			System.out.println("Sample:");
			for(int i=0; i<sample.length; i++) {
				sopl(convertIndexToVar(i) + ": " + sample[i]);
			}

			sopl();
			System.out.println("Formula:");
			for(int i=0; i<sample.length; i++) {
				sopl(convertIndexToVar(i) + ": " + result[i]);
			}
			

			printResults(result, pastVars);
			
			sopl("Answer: " + count);

			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	
	public static void printResults(String result[], Hashtable<String, String> pastVars) {
		sopl();
		System.out.println("Formula based on input:");
		for(int i=0; i<result.length; i++) {
			while(result[i].contains("V")) {
				
				int indexOfV = result[i].indexOf("V");
				
				String findString = result[i].substring(indexOfV - 1, indexOfV + VERSION_LENGTH + 1);
				//sopl(findString);
				
				String replaceString = pastVars.get(findString);
				//sopl(findString);
				
				
				//sopl(findString);
				if(findString.endsWith("0000")) {
					//sopl("Zero: " + replaceString);
				} else {

					//sopl(replaceString);
				}
				result[i] = result[i].replaceAll(findString, replaceString);
			}
			
			sopl(convertIndexToVar(i) + ": " + result[i]);
		}
		sopl();
	}
	
	
	public static int VERSION_LENGTH = 4;
	
	public static int convertToIndex(String var) {
		return (int)(var.charAt(0) - 'w');
	}


	public static char convertIndexToVar(int index) {
		return (char)(index + 'w');
	}
	

	public static void updatePastVarsAtCheckPoint(int arg1Index, String result[], Hashtable<String, String> pastVars, int version[]) {
		
		for(int i=0; i<result.length; i++) {
			if(arg1Index == i) {
				continue;
			}
			
			if(pastVars.get(convertIndexToVarAndVersion(i, version)) == null) {
				sopl("not there!");
				exit(1);
			}
			
			pastVars.remove(convertIndexToVarAndVersion(i, version));
			pastVars.put(convertIndexToVarAndVersion(i, version), result[i]);
			
		}
	}
	
	public static String convertIndexToVarAndVersion(int index, int version[]) {
		
		String versionString = "";
		for(int i=0; i<VERSION_LENGTH; i++) {
			versionString += "0";
		}
		
		versionString = versionString.substring(0, VERSION_LENGTH - (version[index] +"").length())  + version[index];
		
		return ((char)(index + 'w')) + "V" + versionString;
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

	public static long plong(String s) {
		if (IsNumber.isLong(s)) {
			return Long.parseLong(s);
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
