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

public class prob24 {

	//day1 part 1
	//2:38.01
	
	public static void main(String[] args) {
		Scanner in;
		try {
			in = new Scanner(new File("in2024/prob2024in24.txt"));
			//in = new Scanner(new File("in2024/prob2024in0.txt"));
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
			
			Hashtable<String, Integer> vars = new Hashtable<String, Integer>();
			

			Hashtable<String, String> equations = new Hashtable<String, String>();
			ArrayList<String> lhs = new ArrayList<String>();
			ArrayList<String> rhs = new ArrayList<String>();
			
			while(in.hasNextLine()) {
				line = in.nextLine();
				lines.add(line);
				
				if(line.contains(":")) {
					String tokens[] = line.split(": ");
					vars.put(tokens[0], pint(tokens[1]));
				} else if(line.contains("->")) {
					String tokens[] = line.split(" -> ");
					//sopl(tokens[1] + "=" + tokens[0]);
					equations.put(tokens[1], tokens[0]);
					
					lhs.add(tokens[1]);
					rhs.add(tokens[0]);
					
				}
				
				
			}
			
			boolean progress = true;
			
			while(progress) {
				progress = false;
				
				for(int i=0; i<equations.size(); i++) {
					
					String curRHS[] = rhs.get(i).split(" ");
					
					String curLHS = lhs.get(i);
					
					if( ! vars.containsKey(curLHS) && vars.containsKey(curRHS[0])  && vars.containsKey(curRHS[2])) {
						
						progress = true;
						
						if(curRHS[1].equals("AND")) {
							
							if(vars.get(curRHS[0]) == 1 && vars.get(curRHS[2]) == 1) {
								vars.put(curLHS, 1);
							} else {
								vars.put(curLHS, 0);
							}
							
						} else if(curRHS[1].equals("OR")) {
							
							if(vars.get(curRHS[0]) == 1 || vars.get(curRHS[2]) == 1) {
								vars.put(curLHS, 1);
							} else {
								vars.put(curLHS, 0);
							}
							
						} else if(curRHS[1].equals("XOR")) {
							
							if((vars.get(curRHS[0]) == 1 && vars.get(curRHS[2]) == 0) || (vars.get(curRHS[0]) == 0 && vars.get(curRHS[2]) == 1)) {
								vars.put(curLHS, 1);
							} else {
								vars.put(curLHS, 0);
							}
							
						} else {
							sopl("DOH!");
							exit(1);
						}
						
						sopl("ah" + curLHS + " = " + vars.get(curLHS));
					}
				}
				
				
				
				
			}

			
			//28415794793469
			long cur = 0L;

			int index = 0;
			String bits = "";
			while(vars.containsKey("z0" + index) || vars.containsKey("z" + index)) {
				
				sopl("z0" + index);
				long num = 0;
				if(vars.containsKey("z0" + index)) {
					num = vars.get("z0" + index);
					sopl(index + ": " + num);
				} else {
					num = vars.get("z" + index);
					sopl(index + ": " + num);
				}
				if(num == 1) {
					bits+="1";
				} else {
					bits+="0";
				}
				index++;
			}
			
			for(int i=0; i<bits.length(); i++) {
				
				if(bits.charAt(bits.length() - 1 - i) == '1') {
					cur = 2*cur + 1;
				} else {
					cur = 2*cur;
				}
			}

			//28415794793469
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
