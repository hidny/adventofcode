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

public class prob24bkp {

	
	public static void main(String[] args) {
		Scanner in;
		try {
			// in = new Scanner(new File("in2021/prob2021in24.txt"));
			// in = new Scanner(new File("in2021/prob2021in25.txt"));
			 in = new Scanner(new File("in2021/prob2021in26.txt"));
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
			
			String input = "67";
			
			String result[] = new String[4];
			long sample[] = new long[4];
			
			for(int i=0; i<result.length; i++) {
				result[i] = "0";
			}
			
			//int vers[4] = new int[4];
			
			int numInputUsed = 0;
			
			for(int pc=0; pc<lines.size(); pc++) {
				
				//int temp = Integer.parseInt(lines.get(i));
				//count+=temp;
				
				String line = lines.get(pc);
				sopl(line);
				String token[] = line.split(" ");
				
				String inst = token[0];

				String arg1 =  token[1];
				int arg1Index = convertToIndex(arg1);
				
				String arg2 = "0";
				if(token.length >= 3) {
					arg2 = token[2];
					
				}
				
				long secondNum = 0;
				if(arg2.length() > 0 && arg2.charAt(0) >= 'w' && arg2.charAt(0) <= 'z') {
					secondNum = sample[convertToIndex(arg2)];
				} else {
					secondNum = plong(arg2);
				}
				
				if(arg2.startsWith("-")) {
					arg2 = "(" + arg2 + ")";
				}
				
				if(inst.equals("inp")) {
					
					result[arg1Index] = arg1 + "" + numInputUsed;
					sample[arg1Index] = pint(input.charAt(numInputUsed) + "");
					
					numInputUsed++;
					
				} else if(inst.equals("add")) {
					
					result[arg1Index] = "(" + result[arg1Index] + " + " + arg2 + ")";
					sample[arg1Index] += secondNum;
					
				} else if(inst.equals("mul")) {
					
					result[arg1Index] = "(" + result[arg1Index] + " * " + arg2 + ")";
					sample[arg1Index] *= secondNum;
					
				} else if(inst.equals("div")) {

					result[arg1Index] = "(" + result[arg1Index] + " / " + arg2 + ")";
					if(secondNum == 0) {
						sopl("DIVIDED BY ZERO");
						exit(1);
					}
					sample[arg1Index] /= secondNum;
					
				} else if(inst.equals("mod")) {
					
					result[arg1Index] = "(" + result[arg1Index] + " % " + arg2 + ")";
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
			
			sopl("Answer: " + count);
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	
	public static int convertToIndex(String var) {
		return (int)(var.charAt(0) - 'w');
	}


	public static char convertIndexToVar(int index) {
		return (char)(index + 'w');
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
