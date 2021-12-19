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

public class prob16b2 {

	
	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2021/prob2021in16.txt"));
			// in = new Scanner(new File("in2021/prob2021in17.txt"));
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
			
			String map[][] = {{"0", "0000"},
					{"1", "0001"},
					{"2", "0010"},
					{"3", "0011"},
					{"4", "0100"},
					{"5", "0101"},
					{"6", "0110"},
					{"7", "0111"},
					{"8", "1000"},
					{"9", "1001"},
					{"A", "1010"},
					{"B", "1011"},
					{"C", "1100"},
					{"D", "1101"},
					{"E", "1110"},
					{"F", "1111"}};
			String binary = "";
			
			String line = "";
			line = lines.get(0);
			
			//478849624406
			
			//line = "9C0141080250320F1802104A08";
			for(int j=0; j<line.length(); j++) {
				
				String hex = "" + line.charAt(j);
				
			boolean found = false;
			
				for(int k=0; k<map.length; k++) {
					if(map[k][0].equals(hex)) {
						binary += map[k][1];
						found = true;
						break;
					}
				}
				
				if(!found) {
					sopl("oops");
				}
			}
			
			sopl(binary);
			
			//binary = "11101110000000001101010000001100100000100011000001100000";
			
			long answer[] = processPackets(0, binary, -1, binary.length(), 0, "");
			
			sopl("Answer: " + answer[1]);
			in.close();
			//478849624406
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	
	
	
	public static long[] processPackets(int startIndex, String binary, int limitPackets, int endIndex, int depth, String operation) {

		int count = 0;
		sopl();
		sopl(prependDepth(depth) + "Depth: "+ depth);

		sopl(prependDepth(depth) + "OP: " + operation);
		int numPacketsProcessed = 0;
		int index=startIndex;
		
		long answer = 0;
		
		if(operation.equals("*")) {
			answer = 1L;
			
		} else if(operation.equals("max")) {
			answer = Long.MIN_VALUE;
			
		} else if(operation.equals("min")) {
			answer = Long.MAX_VALUE;
		}
		
		long arrayTemp[] = new long[2];
		int arrayTempIndex = 0;
		
		
		while((limitPackets == -1 || numPacketsProcessed<limitPackets)
			 && (endIndex == -1 || index<endIndex)) {
			
			//sopl("Current substring: " + binary.substring(index));
			
			if(operation.equals("") && index > 0) {
				//Check if we're done here;
				sopl(prependDepth(depth) + "break because only a few leading 0s left");
				break;
			} else if(operation.equals("==")
					|| operation.equals(">")
					|| operation.equals("<")) {
				
				if(arrayTempIndex == 2) {
					sopl(prependDepth(depth) + "break");
					break;
				}
			} else if(binary.substring(index).length() < 6) {
				sopl(prependDepth(depth) + "break2");
				break;
			} else if(binary.substring(index, endIndex).length() < 6) {
				sopl(prependDepth(depth) + "break3");
				break;
			}
			
			int version =  getVersion(binary, index);
			count +=version;
			index+=3;
			
			sopl();
			sopl(prependDepth(depth) + "New package:");
			sopl(prependDepth(depth) + "Version: " + version);
			
			int type = getType(binary, index);
			sopl(prependDepth(depth) + "Type: " + type);
			index+=3;
			
			if(type == 4) {
				
				
				long array[] = getLiteral(index, binary);
				
				index = (int)array[1];
				
				//TODO: START FUNCTION
				long num = array[0];
				
				long table[] = updateAnswer(answer, num, operation, depth, arrayTemp, arrayTempIndex);
				
				answer=table[0];
				arrayTempIndex= (int)table[1];
				
				
			} else {
				

				String nextOp = "";
				
				if(type==0) {
					nextOp = "+";
				} else if(type == 1){
					nextOp = "*";
				} else if(type == 2) {
					nextOp = "min";
				} else if(type == 3) {
					nextOp = "max";
				} else if(type==5) {
					nextOp = ">";
				} else if(type == 6){
					nextOp = "<";
				} else if(type == 7) {
					nextOp = "==";
				} else {
					sopl(type);
					sopl("Ah1");
					exit(1);
				}
				sopl(prependDepth(depth) + "Next Op: " + nextOp);
				
				int indicator = getNDigit(1, binary, index);
				index++;
				
				long ret[] = null;
				
				//Operator:
				if(indicator == 1) {
					sopl(prependDepth(depth) + "Indicator is 1");
					//11 array
					int numPackets = getNDigit(11, binary, index);
					index+=11;
					
					sopl(prependDepth(depth) + "Num packets: " + numPackets);
					
					
					
					ret = processPackets(index, binary, numPackets, binary.length(), depth + 1, nextOp);


				} else {
					sopl(prependDepth(depth) + "Indicator is 0");

					int lenghtInBits = getNDigit(15, binary, index);
					index+=15;
					
					sopl(prependDepth(depth) + "Length ih bits: " + lenghtInBits);
					
					
					ret = processPackets(index, binary, -1, index + lenghtInBits, depth + 1, nextOp);
				}
				
				index = (int)ret[0];
				count += ret[1];
				
				//TODO
				long num = ret[2];
				
				long table[] = updateAnswer(answer, num, operation, depth, arrayTemp, arrayTempIndex);
				
				answer=table[0];
				arrayTempIndex= (int)table[1];
				
			} //END OP
			
			
			numPacketsProcessed++;
		}
		
		
		sopl(prependDepth(depth) + "depth: " + depth + " answer: " + answer);
		sopl();
		return new long[]{index, count, answer};
	}
	
	
	public static long[] updateAnswer(long answer, long num, String operation, int depth, long arrayTemp[], int arrayTempIndex) {

		if(operation.equals("")) {
			sopl(prependDepth(depth) + "Blank op");
			answer = num;
			
		} else if(operation.equals("==")
				|| operation.equals(">")
				|| operation.equals("<")) {
			
			if(arrayTempIndex < 2) {
				arrayTemp[arrayTempIndex] = num;
				arrayTempIndex++;
			}
			
			if(arrayTempIndex == 2) {
				if(operation.equals("==")) {
					sopl(prependDepth(depth) + "debug");
					if(arrayTemp[0] == arrayTemp[1]) {
						answer = 1L;
					} else {
						answer = 0L;
					}
				} else if(operation.equals(">")) {
					if(arrayTemp[0] > arrayTemp[1]) {
						answer = 1L;
					} else {
						answer = 0L;
					}
				} else if(operation.equals("<")) {
					if(arrayTemp[0] < arrayTemp[1]) {
						answer = 1L;
					} else {
						answer = 0L;
					}
				} else {
					sopl("oops 12");
					exit(1);
				}
			}
			
			//TODO END FUNCTION
		} else {
			
			if(operation.equals("+")) {
				answer += num;
			} else if(operation.equals("*")) {
				answer *= num;
				
			} else if(operation.equals("max")) {
				answer = Math.max(answer, num);
				
			} else if(operation.equals("min")) {
				answer = Math.min(answer, num);
			} else {
				sopl("oops 13");
				exit(1);
			}
		}
		
		return new long[] {answer, arrayTempIndex};
	}
	
	public static String prependDepth(int depth) {
		String ret="";
		for(int i=0; i<depth; i++) {
			ret +=" ";
		}
		return ret;
	}
	
	public static long[] getLiteral(int index, String binary) {
		long finalNum = 0;
		
		while(binary.charAt(index) == '1') {
			index++;
			long first4Digit = get4Digit(binary, index);
			index +=4;
			finalNum = 16* finalNum + first4Digit;
			
			//sopl("first4Digit: " + first4Digit);
		}

		index++;
		
		long last4Digit = get4Digit(binary, index);
		index +=4;
		finalNum = 16* finalNum + last4Digit;
		
		sopl("literal: " + finalNum);
		
		//if(index %4 > 0) {
			//Handle 0 padding:
		//	index += 4 - (index %4);
		//}
	
		
		return new long[]{finalNum, index};
	}
	//01010010001001000000000
	//01010010001001000000000
	
	public static int getVersion(String binary, int index) {
		int ret = 0;
		for(int i=index; i<index+3 && i<binary.length(); i++) {
			if(binary.charAt(i) == '1') {
				ret = ret * 2 + 1;
			} else {
				ret = ret * 2;
			}
		}
		return ret;
	}
	
	public static int get4Digit(String binary, int index) {
		int ret = 0;
		for(int i=index; i<index+4 && i<binary.length(); i++) {
			if(binary.charAt(i) == '1') {
				ret = ret * 2 + 1;
			} else {
				ret = ret * 2;
			}
		}
		return ret;
	}
	
	public static int getNDigit(int n, String binary, int index) {
		int ret = 0;
		for(int i=index; i<index+n && i<binary.length(); i++) {
			if(binary.charAt(i) == '1') {
				ret = ret * 2 + 1;
			} else {
				ret = ret * 2;
			}
		}
		return ret;
	}


	public static int getType(String binary, int index) {
		return getVersion(binary, index);
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
