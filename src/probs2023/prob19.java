package probs2023;
import java.io.File;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

import number.IsNumber;
import utils.Mapping;
import utils.Sort;

public class prob19 {

	//day1 part 1
	//2:38.01
	
	public static void main(String[] args) {
		Scanner in;
		try {
			in = new Scanner(new File("in2023/prob2023in19.txt"));
			//in = new Scanner(new File("in2023/prob2023in0.txt"));
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
			boolean table3432[][] = new boolean[LIMIT][LIMIT];
			
			
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
			
			HashMap<String, prob19obj> hashmap = new HashMap<String, prob19obj>();
			
			
			
			boolean rulesPart = true;
			for(int i=0; i<lines.size(); i++) {
				
				sopl(line);
				line = lines.get(i);
				
				if(line.trim().equals("")) {
					rulesPart = false;
				
				} else if(rulesPart) {
					prob19obj obj = new prob19obj();
					
					sopl(line);
					obj.label = line.split("\\{")[0];
					
					obj.rules = line.split("\\{")[1].split("\\}")[0].split(",");
				
					hashmap.put(obj.label, obj);
				} else {
				

					sopl(line);
					
					String vars[] = line.split("\\{")[1].split("\\}")[0].split(",");
					
					HashMap<String, Long> hashmapVars = new HashMap<String, Long>();
					
					long curScore = 0L;
					
					for(int j=0; j<vars.length; j++) {
						String varName = vars[j].split("=")[0];
						long initValue = pint(vars[j].split("=")[1]);
						
						curScore += initValue;
						hashmapVars.put(varName, initValue);
					}
					
					
					sopl();
					sopl();
					
					String curPos = "in";
					
					NEXT_POS:
					while(curPos.equals("A") == false && curPos.equals("R") == false) {
						
						sopl(curPos);
						sopl();
						String rules[] = hashmap.get(curPos).rules;
						
						
						
						for(int j=0; j<rules.length; j++) {
							
							//sopl(rules[j]);
							
							String rule = rules[j];
							
							if(rule.contains(">")) {
								
								String tokens[] = rule.split(":")[0].split(">");
								
								long lhs;
								long rhs;
								if(number.IsNumber.isNumber(tokens[0])) {
									lhs = pint(tokens[0]);
								} else {
									lhs = hashmapVars.get(tokens[0]);
									//sopl(tokens[0] + ": " + lhs);
								}
								
								if(number.IsNumber.isNumber(tokens[1])) {
									rhs = pint(tokens[1]);
								} else {
									rhs = hashmapVars.get(tokens[1]);
									//sopl(tokens[1] + ": " + rhs);
								}

								if(lhs > rhs) {
									curPos = rule.split(":")[1];
									sopl("Move to " + curPos);
									continue NEXT_POS;
								} else {
									continue;
								}
								
							} else if(rule.contains("<")) {

								String tokens[] = rule.split(":")[0].split("<");
								
								long lhs;
								long rhs;
								if(number.IsNumber.isNumber(tokens[0])) {
									lhs = pint(tokens[0]);
								} else {
									lhs = hashmapVars.get(tokens[0]);
									//sopl(tokens[0] + ": " + lhs);
								}
								
								if(number.IsNumber.isNumber(tokens[1])) {
									rhs = pint(tokens[1]);
								} else {
									rhs = hashmapVars.get(tokens[1]);
									//sopl(tokens[1] + ": " + rhs);
								}

								if(lhs < rhs) {
									curPos = rule.split(":")[1];
									sopl("Move to " + curPos);
									continue NEXT_POS;
								} else {
									continue;
								}
							} else {
								
								curPos = rule;
								sopl("Move to " + rule);
								continue NEXT_POS;
							}
						}
						
					}
					
					
					if(curPos.equals("A")) {
						cur+=curScore;
					}
					
				}
			}


			sopl("Answer: " + cur);
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
	
	public static void exit() {
		exit(0);
	}
	public static void exit(int code) {
		sop("Exit with code " + code);
		
		System.exit(code);
	}

}
