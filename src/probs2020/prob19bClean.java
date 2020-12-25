package probs2020;
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

public class prob19bClean {

	
	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2020/prob2020in19.txt"));
			 //in = new Scanner(new File("in2020/prob2020in19.txt.test"));
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
			
			ArrayList<String> rules = new ArrayList<String>();
			
			
			
			
			
			for(int i=0; i<lines.size(); i++) {
				
				String line = lines.get(i);
				String lineCopy = String.copyValueOf(line.toCharArray());

				

				if(line.contains(":")) {
					rules.add(line);
				}
			}
			
			betterRules( rules);
			int root = findRoot();
			sopl(root);
			
			
			
			for(int i=0; i<lines.size(); i++) {
				String line = lines.get(i);
					if(line.contains(":") == false && line.trim().equals("") == false) {
							if(matchesRule(line)) {
								count++;
							}
							sopl();
							sopl();
					}
					
			}
		
			sopl("Answer: " + count);
			in.close();
			
			
			/*
			long count = 0;
			Set<String> keys = numbers.keySet();
	        for(String key: keys){
	        	
	        	String value = numbers.get(key);
	        	count += StringToNum(value);
	        	
	            System.out.println(key + "-> " + value + "  or " + StringToNum(value));
	           
	        }
			 */
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}

	public static void betterRules(ArrayList<String> rules) {
		
		ArrayList<String> betterRules = new ArrayList<String>();
		
		
		for(int i=0; i<rules.size(); i++) {

			sopl(rules.get(i));
			String rootTmp = rules.get(i).substring(0, rules.get(i).indexOf(":")).trim();
			if(rootTmp.equals("8")) {
				rules.set(i, "8: 42 | 42 8");
			} else if(rootTmp.equals("11")) {
				rules.set(i, "11: 42 31 | 42 11 31");
			}
			
			String a = rules.get(i).substring(rules.get(i).indexOf(':') + 1);
			
			sopl("a   " + a);
			String b[] = a.split("\\|");
			
			for(int j=0; j<b.length; j++) {
				
				String temp = b[j].trim();
				sopl("b   " + temp);
				String tokens[] = temp.split(" ");
				if(tokens.length == 1) {
					
					
					lhs.add(tokens[0]);
					rhs.add("");
					rhs2.add("");
				} else {
					
					
					lhs.add(tokens[0]);
					rhs.add(tokens[1]);
					if(tokens.length == 3) {
						rhs2.add(tokens[2]);
					} else {
						rhs2.add("");
					}
					
					
					if(tokens.length > 3) {
						sopl("aah");
					}
				}
				
				root.add(rootTmp);
			}
			
			
			
			
		}
		
		
		for(int i=0; i<root.size(); i++) {
			sopl(root.get(i) + "-> " + lhs.get(i) + " and " + rhs.get(i) + " and " + rhs2.get(i));
		}
		
	}
	
	public static int findRoot() {
		
		for(int i=0; i<root.size(); i++) {
			if(root.get(i).equals("0")) {
				return i;
			}
		}
		exit(1);
		return -1;
		
	}
	public static boolean matchesRule(String line) {
		
		sopl("Start: " + line);
		origLine = line;
		if(matchesRule(line, "0").size() > 0) {
			return true;
		} else {
			return false;
		}
		
	}

	public static String origLine = "";
	public static ArrayList<String> root = new ArrayList<String>();
	public static ArrayList<String> lhs = new ArrayList<String>();
	public static ArrayList<String> rhs = new ArrayList<String>();
	public static ArrayList<String> rhs2 = new ArrayList<String>();
	
	public static ArrayList<String> matchesRule(String line, String curNode) {
		
		//for(int i=0; i<origLine.length() - line.length(); i++) {
			//sop(" ");
		//}
		//sopl(curNode);
		
		ArrayList <String> ret = new ArrayList<String>();
		for(int i=0; i<root.size(); i++) {
			if(root.get(i).equals(curNode)) {
				

				
				if(rhs.get(i).equals("")) {
					if(lhs.get(i).contains("a") || lhs.get(i).contains("b")) {
						String charac = lhs.get(i).substring(1, 2);
						
						if(line.startsWith(charac)) {
							ret.add(charac);
						}
					} else {
						ret.addAll(matchesRule(line, lhs.get(i)));
					}
				} else {
					
					ArrayList <String> tmp = matchesRule(line, lhs.get(i));
					
					for(int j=0; j<tmp.size(); j++) {
						
						if(line.startsWith(tmp.get(j)) == false) {
							sopl("ahh!");
							exit(1);
						}
						
						//sopl("sub string: " + line.substring(tmp.get(j).length()));
						ArrayList <String> next = matchesRule(line.substring(tmp.get(j).length()), rhs.get(i));
						
						ArrayList <String> temp2 = new ArrayList <String>();
						for(int k= 0; k<next.size(); k++) {
							temp2.add(tmp.get(j) + next.get(k));
							
							if(line.startsWith(tmp.get(j) + next.get(k)) == false) {
								sopl("ahh2!");
								exit(1);
							}

							if(rhs2.get(i).equals("") == false) {
								
								//sopl("sub string rhs2: " + line.substring(tmp.get(j).length()));
								ArrayList <String> next2 = matchesRule(line.substring(tmp.get(j).length() + next.get(k).length()), rhs2.get(i));
								
								for(int m= 0; m<next2.size(); m++) {
									ret.add(tmp.get(j) + next.get(k) + next2.get(m));
								}
								
							} else {
								ret.addAll(temp2);
							}
						}
						
						
					}
					
					
				}
			}
		}
		
		//RM dups:
		for(int i=0; i<ret.size(); i++) {
			for(int j=i+1; j<ret.size(); j++) {
				
				if(ret.get(i).equals(ret.get(j))) {
					ret.remove(j);
					j--;
					sopl("REMOVE DUP");
				}
			}
		}
		
		if(curNode.equals("0")) {
			for(int i=0; i<ret.size(); i++) {
				if(ret.get(i).equals(line) == false) {
					sopl("REMOVE " + ret.get(i));
					ret.remove(i);
					i--;
				}
			}
		}
		
		return ret;
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
