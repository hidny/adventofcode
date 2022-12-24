package probs2022;
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

public class prob22 {

	
	public static void main(String[] args) {
		Scanner in;
		try {
			//in = new Scanner(new File("in2022/prob2022in23.txt"));
			in = new Scanner(new File("in2022/prob2022in22.txt"));
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

			int numRows = 0;
			ArrayList ints = new ArrayList<Integer>();
			for(int i=0; i<lines.size(); i++) {
				
				
				line = lines.get(i);
				
				if(line.trim().equals("")) {
					numRows = i;
				}
			}
			
			
			int curi =0;
			int curj=0;
			START:
			for(int i=0; i<numRows; i++) {
				String map = lines.get(i);
				for(int j=0; j<map.length(); j++) {
					if(map.charAt(j) == '.') {
						curi = i;
						curj = j;
						break START;
					}
				}
			}
			
			String cmd = lines.get(lines.size() - 1);
			
			int rot = 0;
			
			boolean isNum = false;
			for(int index=0; index<cmd.length(); index++) {
				
				if(isNum == false) {
					sopl("curi " + curi);
					sopl("curj " + curj);
					sopl("rot" + rot);
					sopl("index; " + index);
					if(index == 5) {
						sopl("Debug");
					}
				}
				sopl();
				isNum = !isNum;
				
				if(isNum == false) {
					char rotChar = cmd.charAt(index);
					if(rotChar == 'R') {
						rot = (rot + 1) % 4;
					} else if(rotChar == 'L') {
						rot = (rot + 3) % 4;
						
					} else {
						sopl("oops rot");
						exit(1);
					}
				} else if(isNum) {
					int curNum = 0;
					
					while(index <cmd.length() && cmd.charAt(index) >= '0' && cmd.charAt(index)<='9') {
						curNum = curNum *10 + (int)(cmd.charAt(index) - '0');
						index++;
					}
					index--;
					
					
					MOVE:
					for(int k=0; k<curNum; k++) {
						
						if(rot == 0) {
							
							if(curj+1 >= lines.get(curi).length() 
									|| ( lines.get(curi).charAt(curj + 1) != '.'
									&& lines.get(curi).charAt(curj + 1) != '#')) {
								
								//go to the left:
								
								for(int m=0; m<lines.get(curi).length(); m++) {
									if(lines.get(curi).charAt(m) == '.') {
										curj = m;
										break;
									} else if(lines.get(curi).charAt(m) == '#') {
										break MOVE;
									}
								}
							} else if(lines.get(curi).charAt(curj + 1) == '.') {
								curj++;
							} else if(lines.get(curi).charAt(curj + 1) == '#') {
								break MOVE;
							} else {
								sopl("Doh! move");
								exit(1);
							}
							
						} else if(rot == 1) {
							
							if(curi+1 >= numRows 
									|| lines.get(curi+1).length() <= curj
									|| (lines.get(curi+1).charAt(curj) != '.'
									&& lines.get(curi+1).charAt(curj) != '#')) {
								
								//go to the down:
								
								for(int m=0; m<numRows; m++) {
									if(lines.get(m).length() <= curj) {
										//NOPE
										continue;
										
									} else if(lines.get(m).charAt(curj) == '.') {
										curi = m;
										break;
									} else if(lines.get(m).charAt(curj) == '#') {
										break MOVE;
									}
								}
							} else if(lines.get(curi + 1).charAt(curj) == '.') {
								curi++;
							} else if(lines.get(curi + 1).charAt(curj) == '#') {
								break MOVE;
							} else {
								sopl("Doh! move");
								exit(1);
							}
							
						} else if(rot == 2) {
							
							if(curj-1 < 0
									|| (lines.get(curi).charAt(curj - 1) != '.'
									&& lines.get(curi).charAt(curj - 1) != '#')) {
								
								//go to the left:
								
								for(int m=lines.get(curi).length() - 1; m>=0; m--) {
									if(lines.get(curi).charAt(m) == '.') {
										curj = m;
										break;
									} else if(lines.get(curi).charAt(m) == '#') {
										break MOVE;
									}
								}
							} else if(lines.get(curi).charAt(curj - 1) == '.') {
								curj--;
							} else if(lines.get(curi).charAt(curj - 1) == '#') {
								break MOVE;
							} else {
								sopl("Doh! move");
								exit(1);
							}
							
						} else if(rot == 3) {
							
							if(curi-1 < 0 
									|| lines.get(curi-1).length() <= curj
									|| ( lines.get(curi-1).charAt(curj) != '.'
									&& lines.get(curi-1).charAt(curj) != '#')) {
								
								//go to the down:
								
								for(int m=numRows - 1; m>=0; m--) {
									if(lines.get(m).length() <= curj) {
										continue;
									} else if(lines.get(m).charAt(curj) == '.') {
										curi = m;
										break;
									} else if(lines.get(m).charAt(curj) == '#') {
										break MOVE;
									}
								}
							} else if(lines.get(curi - 1).charAt(curj) == '.') {
								curi--;
							} else if(lines.get(curi - 1).charAt(curj) == '#') {
								break MOVE;
							} else {
								sopl("Doh! move");
								exit(1);
							}
							
						} else {
							sopl("doh rotation");
							exit(1);
						}
					}
				}
			}
			
			sopl(curi);
			sopl(curj);
			sopl(rot);
			
			//41276 WRONG!
			count = 1000 * (curi+1) + 4 * (curj+1) + rot;
			
			sopl("Answer: " + count);
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
