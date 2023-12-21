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

public class prob19b {

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
					//pass.
				}
				
			}

			cur = getAnswer(hashmap, "in", new ArrayList<String>());

			sopl("Answer: " + cur);
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	
	
	public static long getAnswer(HashMap<String, prob19obj> hashmap, String curPos, ArrayList<String> currentRules) {
		
		long cur = 0L;
	
		while(curPos.equals("A") == false && curPos.equals("R") == false) {
			
			//sopl(curPos);
			//sopl();
			String rules[] = hashmap.get(curPos).rules;
			
			
			
			for(int j=0; j<rules.length; j++) {
				
				//sopl(rules[j]);
				
				String rule = rules[j];
				
				if(rule.contains(">") || rule.contains("<")) {
					
					ArrayList<String> newCurrentRules = new ArrayList<String>();
					
					for(int k=0; k<currentRules.size(); k++) {
						newCurrentRules.add(currentRules.get(k));
					}
					newCurrentRules.add(rule.split(":")[0]);
					
					//sopl(rule.split(":")[0]);
					//sopl(getOppositeRule(rule));
					
					//exit(1);
					
					cur += getAnswer(hashmap, rule.split(":")[1], newCurrentRules);
					
					
					String oppositeRule = getOppositeRule(rule);
					
					currentRules.add(oppositeRule);
					
					
				} else {
					
					curPos = rule;
					
				}
			}
			
		}
		
		if(curPos.equals("A")) {
			//TODO count
			
			long prod =  getRange("x", currentRules)
					   * getRange("m", currentRules)
					   * getRange("a", currentRules)
					   * getRange("s", currentRules);
			
			
			/*sopl("Rules:");
			for(int j=0; j<currentRules.size(); j++) {
				sopl(currentRules.get(j));
			}
			sopl(prod);
			sopl();
			*/
			//exit(1);
			
			cur += prod;
		}
		
		
		return cur;
	}
	
	public static long getRange(String var, ArrayList<String> currentRules) {
		
		long ret = 0L;
		
		for(int pos=1; pos<=4000; pos++) {
			
			boolean stillGood = true;
			
			for(int j=0; j<currentRules.size(); j++) {
				
				String rule = currentRules.get(j).split(":")[0];
				
				if(rule.startsWith(var)) {
					
					if(rule.contains(">")) {
						
						String tokens[] = rule.split(">");
						
						if(pos > pint(tokens[1])) {
							//pass
						} else {
							stillGood = false;
						}
						
					
					} else if(rule.contains("<")) {
						
						String tokens[] = rule.split("<");
						
						if(pos < pint(tokens[1])) {
							//pass
						} else {
							stillGood = false;
						}
						
					}
					
				}
				
			}
			
			if(stillGood) {
				ret++;
			}
		}
		
		return ret;
	}
	
	public static String getOppositeRule(String rule) {
		
		String newRule = "";
		
		if(rule.contains(">")) {
		
			String tokens[] = rule.split(":")[0].split(">");
			
			long value;
			if(number.IsNumber.isNumber(tokens[0])) {
				
				value = pint(tokens[0]);
				
				newRule = tokens[1] + ">" + (value - 1);
				
			} else if(number.IsNumber.isNumber(tokens[1])) {
				value = pint(tokens[1]);
				
				newRule = tokens[0] + "<" + (value + 1);
			}
		} else if(rule.contains("<")) {
			
			String tokens[] = rule.split(":")[0].split("<");
			
			long value;
			if(number.IsNumber.isNumber(tokens[0])) {
				
				value = pint(tokens[0]);
				
				newRule = tokens[1] + "<" + (value + 1);
				
			} else if(number.IsNumber.isNumber(tokens[1])) {
				value = pint(tokens[1]);
				
				newRule = tokens[0] + ">" + (value - 1);
			}
			
		} else {
			exit(1);
		}
		
		return newRule;
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
