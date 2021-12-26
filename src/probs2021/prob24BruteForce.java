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

public class prob24BruteForce {

	
	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2021/prob2021in24.txt"));
			// in = new Scanner(new File("in2021/prob2021in25.txt"));
			// in = new Scanner(new File("in2021/prob2021in26.txt"));
			// in = new Scanner(new File("in2021/prob2021in27.txt"));
			
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
			
			
			int INPUT_SIZE = 14;
			
			String input = "";
			for(int i=0; i<INPUT_SIZE; i++) {
				input += "9";
			}
			
			
			int countDebug = 0;
			
			for(long inputNum = plong(input); inputNum>=0; inputNum--) {
				
				countDebug++;
				while((("" + inputNum).contains("0"))) {
					//Could be faster but whatever
					inputNum--;
				}
				if(countDebug % 1000 == 0) {
					sopl(inputNum);
				}
				
				int numInputUsed = 0;
				long sample[] = new long[4];
				
				for(int pc=0; pc<lines.size(); pc++) {
	
					//int temp = Integer.parseInt(lines.get(i));
					//count+=temp;
					
					String line = lines.get(pc);
					String token[] = line.split(" ");
					
	
					//sopl(line);
					
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
					
					
					
					if(inst.equals("inp")) {
						
						//sopl();
						
						sample[arg1Index] = pint(input.charAt(numInputUsed) + "");
						
						numInputUsed++;
						
					} else if(inst.equals("add")) {
						
						sample[arg1Index] += secondNum;
						
						
					} else if(inst.equals("mul")) {
						
						
						sample[arg1Index] *= secondNum;
						
	
						
					} else if(inst.equals("div")) {
	
						
						if(secondNum == 0) {
							sopl("DIVIDED BY ZERO");
							continue;
						}
						sample[arg1Index] /= secondNum;
						
	
						
					} else if(inst.equals("mod")) {
						
						if(secondNum == 0) {
							sopl("DIVIDED BY ZERO in % calc");
							continue;
						}
						sample[arg1Index] = sample[arg1Index] % secondNum;
						
	
						
					} else if(inst.equals("eql")) {
						
						if(sample[arg1Index] == secondNum) {
							sample[arg1Index] = 1;
						} else {
	
							sample[arg1Index] = 0;
						}
						
	
						
					}
					
	
					
					
				}
				
				//sopl();
				//System.out.println("Sample:");
				for(int i=0; i<sample.length; i++) {
					//sopl(convertIndexToVar(i) + ": " + sample[i]);
				}
				
				if(sample[3] == 0) {
					sopl("Answer: " + inputNum);
					exit(0);
				}
			}

			
			
			

			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	
	public static int VERSION_LENGTH = 4;
	
	public static int convertToIndex(String var) {
		return (int)(var.charAt(0) - 'w');
	}


	public static char convertIndexToVar(int index) {
		return (char)(index + 'w');
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
