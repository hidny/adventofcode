package probs2022;
import java.io.File;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

import number.IsNumber;

public class prob11 {

	
	public static void main(String[] args) {
		Scanner in;
		try {
			in = new Scanner(new File("in2022/prob2022in11.txt"));
			// in = new Scanner(new File("in2022/prob2022in12.txt"));
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
			
			
			//dir: 0 up
			//1 right
			//2 down
			//3 left
			
			while(in.hasNextLine()) {
				line = in.nextLine();
				lines.add(line);
				
			}
			
			ArrayList<prob11Monkey> monkeys = new ArrayList<prob11Monkey>();
			

		//divide by 3
			//takes turns 0 then 1 then 2...
			//thrown goes to end of list

			prob11Monkey current = new prob11Monkey();
			int numMonkey = -1;
			
			ArrayList ints = new ArrayList<Integer>();
			for(int i=0; i<lines.size(); i++) {
				
				
				line = lines.get(i).trim();
				String token[] = line.split(" ");
				
				
				if(token[0].startsWith("Monkey")) {
					
					if(numMonkey >=0) {
						monkeys.add(current);
						current = new prob11Monkey();
					} else {
						
					}
					numMonkey++;
					
				} else if(line.startsWith("Starting items")) {
					
					for(int j=2; j<token.length; j++) {
						current.items.add(new BigInteger("" + ((long)pint(token[j].split(",")[0]))));
					}
					
				} else if(token[0].startsWith("Operation")) {
					
					current.op = token[token.length - 2];
					if(IsNumber.isNumber(token[token.length - 1])) {
						current.opAmount = new BigInteger("" + pint(token[token.length - 1]));
					} else {
						current.isOld = true;
					}
					
				} else if(token[0].startsWith("Test")) {
					
					current.divisor = new BigInteger("" + pint(token[token.length - 1]));
					
					
				} else if(line.startsWith("If true")) {
					
					
					current.trueMonkey = pint(token[token.length - 1]);
					
					
				} else if(line.startsWith("If false")) {
					
					current.falseMonkey = pint(token[token.length - 1]);
					
				}
				
				
			}
			
			if(numMonkey >=0) {
				monkeys.add(current);
			}
			
			BigInteger mod = BigInteger.ONE;
			
			for(int m=0; m<monkeys.size(); m++) {
				mod = mod.multiply(monkeys.get(m).divisor);
			}
			
			long activity[] = new long[monkeys.size()];
			
			for(int round =0; round <10000; round++) {
				
				for(int m=0; m<monkeys.size(); m++) {
					
					//sopl("monkey " + m);
					current = monkeys.get(m);
					while(current.items.size() > 0) {

						
						BigInteger tmp = current.items.removeFirst();
						activity[m]++;
						
						if(current.isOld) {
							current.opAmount = tmp;
						}
						if(current.op.equals("+")) {
							tmp = tmp.add(current.opAmount);
							
						} else if(current.op.equals("*")) {
							tmp = tmp.multiply(current.opAmount);
							
						} else if(current.op.equals("-")) {
							tmp = tmp.subtract(current.opAmount);
							
						} else if(current.op.equals("/")) {
							tmp = tmp.divide(current.opAmount);
							
						} else {
							sopl("oops334");
							exit(1);
						}
						
						
						//tmp /= 3;
						
						if(tmp.divideAndRemainder(current.divisor)[1] == BigInteger.ZERO) {

							//sopl("throw " + tmp + " to " + current.trueMonkey);
							//Throw to true
							//monkeys.get(current.trueMonkey).items.add(tmp);
							monkeys.get(current.trueMonkey).items.add(tmp.divideAndRemainder(mod)[1]);
							
						} else {
							
							//sopl("throw " + tmp + " to " + current.falseMonkey);

							//monkeys.get(current.falseMonkey).items.add(tmp);
							monkeys.get(current.falseMonkey).items.add(tmp.divideAndRemainder(mod)[1]);
							
						}
						
					}
					
					
				}
				
				sopl(round);
				if(round ==9999) {
					for(int m=0; m<monkeys.size(); m++) {
						sop("Monkey "+ m + ": ");
						while(monkeys.get(m).items.size() > 0) {
							sop(monkeys.get(m).items.removeFirst() + ", ");
						}
						sopl();
						
					}
					for(int i=0; i<activity.length; i++) {
						sop("Monkey "+ i + ": ");
						sopl(activity[i]);
					}
				}
			}
			
			long mostActive = 0;
			long secondMostActive = 0;
			for(int i=0; i<activity.length; i++) {
				
				if(activity[i]> mostActive) {
					secondMostActive = mostActive;
					mostActive = activity[i];
				} else if(activity[i] > secondMostActive) {
					secondMostActive = activity[i];
				}
			}
			
			
			sopl("Answer: " + (mostActive * secondMostActive));
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
