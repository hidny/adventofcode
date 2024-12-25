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

public class prob24bcount {

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
			
			//Good:
			swapLHS(lhs, "z12", "qdg");
			//swapLHS(lhs, "kvf", "z23");
			//swapLHS(lhs, "z19", "z20");
			//swapLHS(lhs, "smb", "gng");
			//swapLHS(lhs, "ssr", "nhb");

			int numOops;
			boolean oops = false;
			
			int minOops = 100;
			
			String suggestion = "";
			
			for(int i2=0; i2<lhs.size(); i2++) {
				sopl("i2 = " + i2);
				for(int j2=i2+1; j2<lhs.size(); j2++) {

					sopl("j2 = " + j2);

					swapLHS(lhs, i2, j2);
					
					numOops = 0;
					for(int indexBit=0; indexBit<45; indexBit++) {
						for(int typeOfAdd=0; typeOfAdd<8; typeOfAdd++) {
							
							if(typeOfAdd == 0) {
								continue;
							}
							/*sopl("index bit: " + indexBit);
							sopl("typeOfAdd: " + typeOfAdd);
							*/
							vars = setUpAdd(vars, indexBit, typeOfAdd);
							/*
							if(indexBit > 0) {
								sopl(varWithIndex("x", indexBit-1) + ": " + vars.get(varWithIndex("x", indexBit-1)));
								sopl(varWithIndex("y", indexBit-1) + ": " + vars.get(varWithIndex("y", indexBit-1)));
							}
							sopl(varWithIndex("x", indexBit) + ": " + vars.get(varWithIndex("x", indexBit)));
							sopl(varWithIndex("y", indexBit) + ": " + vars.get(varWithIndex("y", indexBit)));
							*/
							int expected = getExpectedResultForBit(indexBit, typeOfAdd);
							//sopl("Expected: " + expected);
							
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
										
										if(vars.get(curLHS) != 0) {
											//sopl("Note: " + curLHS + " = " + vars.get(curLHS));
										}
									}
								}
								
							}
							
							//sopl("Got for " + varWithIndex("z", indexBit) + ": " + vars.get(varWithIndex("z", indexBit)));
						
							
							if(vars.contains(varWithIndex("z", indexBit)) && vars.get(varWithIndex("z", indexBit)) == expected) {
								//sopl("Nice!");
							} else {
								//sopl("Oops!");
								numOops++;
								oops = true;
								
								if(vars.contains(varWithIndex("z", indexBit)) == false ) {
									numOops++;
								}
							}
							//sopl();
							//sopl();
							
						}
						
						if(oops) {
							//exit(1);
						}
					}

					if(numOops < minOops) {
						sopl("Try " + lhs.get(i2) + " and " + lhs.get(j2));
						suggestion = "Try " + lhs.get(i2) + " and " + lhs.get(j2);
					}

					swapLHS(lhs, j2, i2);
				}
			}

			
			//28415794793469
			long cur = 0L;

			int index = 0;
			String bits = "";
			while(vars.containsKey(varWithIndex("z", index))) {
				
				sopl("z0" + index);
				long num = vars.get(varWithIndex("z", index));
				sopl(index + ": " + num);
				
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
			
			sopl(suggestion);
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	
	
	public static ArrayList<String> swapLHS(ArrayList<String> lhs, String label1, String label2) {
		
		int i = -1;
		int j = -1;
		
		for(int k=0; k<lhs.size(); k++) {
			if(lhs.get(k).equals(label1)) {
				i = k;
			}
			if(lhs.get(k).equals(label2)) {
				j = k;
			}
		}
		
		
		return swapLHS(lhs, i, j);
	}
	
	public static ArrayList<String> swapLHS(ArrayList<String> lhs, int i, int j) {
		
		String tmp1 = lhs.get(i);
		String tmp2 = lhs.get(j);
		
		lhs.set(i, tmp2);
		lhs.set(j, tmp1);
		
		
		return lhs;
	}
	
	public static int getExpectedResultForBit(int indexBit, int typeOfAdd) {
		//TODO
		int sum = 0;
		
		if((typeOfAdd & 4) == 4) {
			sum++;
		} 
		
		if((typeOfAdd & 2) == 2) {

			sum++;
		} 
		
		if(indexBit > 0 && (typeOfAdd & 1) == 1) {

			sum++;
		}
		
		return sum % 2;
	}
	
	public static Hashtable<String, Integer> setUpAdd(Hashtable<String, Integer> vars, int indexBit, int typeOfAdd) {
		
		Hashtable<String, Integer> vars2 = new Hashtable<String, Integer>();
		for(int i=0; vars.containsKey(varWithIndex("x", i)); i++) {
			vars2.put(varWithIndex("x", i), 0);
		}
		
		for(int i=0; vars.containsKey(varWithIndex("y", i)); i++) {
			vars2.put(varWithIndex("y", i), 0);
		}
		
		//if(typeOfAdd == 3) {
		//	sopl("debug");
		//}
		
		if((typeOfAdd & 4) == 4) {
			vars2.remove(varWithIndex("x", indexBit));
			vars2.put(varWithIndex("x", indexBit), 1);
		}

		if((typeOfAdd & 2) == 2) {
			vars2.remove(varWithIndex("y", indexBit));
			vars2.put(varWithIndex("y", indexBit), 1);
		}
		
		if((typeOfAdd & 1) == 1 && indexBit > 0) {
			vars2.remove(varWithIndex("x", indexBit-1));
			vars2.put(varWithIndex("x", indexBit-1), 1);
			
			vars2.remove(varWithIndex("y", indexBit-1));
			vars2.put(varWithIndex("y", indexBit-1), 1);
		}
		
		return vars2;
	}
	
	public static String varWithIndex(String var, int indexBit) {
		
		if(indexBit >= 10) {
			return var + indexBit;
		} else {
			return var +"0" + indexBit;
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
