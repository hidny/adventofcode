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

public class prob22part2 {

	
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
			
			sopl("Num rows: " + numRows);
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

			sopl("Num rows: " + numRows);
			int cubeLength = -1;
			if(numRows > 100) {
				cubeLength = 50;
			} else {
				cubeLength = 4;
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
								int newLoc[] = setupTransferWithCheck(curi, curj + 1, rot, cubeLength, lines);
								
								sopl(curi);
								sopl(curj);
								sopl("..");
								sopl(newLoc[1]);
								if(lines.get(newLoc[0]).charAt(newLoc[1]) != '.' &&
										lines.get(newLoc[0]).charAt(newLoc[1]) != '#') {
									sopl("DOH oops12");
									exit(1);
									
								} else if(lines.get(newLoc[0]).charAt(newLoc[1]) == '.') {
									curi = newLoc[0];
									curj = newLoc[1];
									rot = newLoc[2];
								} else if(lines.get(newLoc[0]).charAt(newLoc[1]) == '#') {
									break MOVE;
								} else {
									sopl("???");
									exit(1);
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
								
								int newLoc[] = setupTransferWithCheck(curi + 1, curj, rot, cubeLength, lines);
								
								if(lines.get(newLoc[0]).charAt(newLoc[1]) != '.' &&
										lines.get(newLoc[0]).charAt(newLoc[1]) != '#') {
									sopl("DOH oops13");
									exit(1);
									
								} else if(lines.get(newLoc[0]).charAt(newLoc[1]) == '.') {
									curi = newLoc[0];
									curj = newLoc[1];
									rot = newLoc[2];
								} else if(lines.get(newLoc[0]).charAt(newLoc[1]) == '#') {
									break MOVE;
								} else {
									sopl("???");
									exit(1);
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

								int newLoc[] = setupTransferWithCheck(curi, curj - 1, rot, cubeLength, lines);
								
								if(lines.get(newLoc[0]).charAt(newLoc[1]) != '.' &&
										lines.get(newLoc[0]).charAt(newLoc[1]) != '#') {
									sopl("DOH oops14");
									exit(1);
									
								} else if(lines.get(newLoc[0]).charAt(newLoc[1]) == '.') {
									curi = newLoc[0];
									curj = newLoc[1];
									rot = newLoc[2];
								} else if(lines.get(newLoc[0]).charAt(newLoc[1]) == '#') {
									break MOVE;
								} else {
									sopl("???");
									exit(1);
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
								
								int newLoc[] = setupTransferWithCheck(curi-1, curj, rot, cubeLength, lines);
								
								if(lines.get(newLoc[0]).charAt(newLoc[1]) != '.' &&
										lines.get(newLoc[0]).charAt(newLoc[1]) != '#') {
									sopl("DOH oops15");
									exit(1);
									
								} else if(lines.get(newLoc[0]).charAt(newLoc[1]) == '.') {
									curi = newLoc[0];
									curj = newLoc[1];
									rot = newLoc[2];
								} else if(lines.get(newLoc[0]).charAt(newLoc[1]) == '#') {
									break MOVE;
								} else {
									sopl("???");
									exit(1);
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

	public static int[] setupTransferWithCheck(int i, int j, int rot, int lengthCube, ArrayList<String> lines) {
		
		int ret[] = setupTransfer2(i, j, rot, lengthCube);
		
		if(lines.get(ret[0]).length() < ret[1]) {
			sopl("Oops!");
		}
		if(lines.get(ret[0]).charAt(ret[1]) != '.' && lines.get(ret[0]).charAt(ret[1]) != '#') {
			sopl("Doh");
			exit(1);
		}

		int origChecki = ret[0];
		int origCheckj = ret[1];
		int origCheckRot = (ret[2] + 2) % 4;
		
		if(origCheckRot == 0) {
			origCheckj++;
		} else if(origCheckRot == 1) {
			origChecki++;
		} else if(origCheckRot == 2) {
			origCheckj--;
		} else if(origCheckRot == 3) {
			origChecki--;
		} else {
			sopl("doh");
			exit(1);
		}
		
		int origCheck[] = setupTransfer2(origChecki, origCheckj, origCheckRot, lengthCube);
		
		origChecki = origCheck[0];
		origCheckj = origCheck[1];
		origCheckRot = (origCheck[2] + 2) % 4;
		
		if(origCheckRot == 0) {
			origCheckj++;
		} else if(origCheckRot == 1) {
			origChecki++;
		} else if(origCheckRot == 2) {
			origCheckj--;
		} else if(origCheckRot == 3) {
			origChecki--;
		} else {
			sopl("doh");
			exit(1);
		}
		
		if(origChecki != i) {
			sopl("Doh! i ");
			exit(1);
		}
		if(origCheckj != j) {
			sopl("Doh! j");
			exit(1);
			
		}
		if(origCheckRot != rot) {
			sopl("Doh rot");
			exit(1);
		}
		
		return ret;
	}
	//only applies to shape in input
	public static int[] setupTransfer2(int i, int j, int rot, int lengthCube) {
		
		int inew = -1;
		int jnew = -1;
		int newRot = -1;
		if(rot == 0) {
			
			if( i< lengthCube) {
				inew = 3 * lengthCube - 1 - i % lengthCube;
				jnew= 2 * lengthCube -1;
				
				newRot = 2;
				
			} else if( i >= lengthCube && i< 2*lengthCube) {
				inew=lengthCube - 1;
				jnew=2 * lengthCube + i % lengthCube;
				
				newRot = 3;
				
			} else if( i >= 2 *lengthCube && i< 3*lengthCube) {
				inew=lengthCube - 1 - i % lengthCube;
				jnew= 3 * lengthCube -1;
				
				newRot = 2;
				
			} else if( i >= 3 * lengthCube && i< 4*lengthCube) {
				inew = 3 * lengthCube - 1;
				jnew = lengthCube + i % lengthCube;
				
				newRot = 3;

			} else{
				sopl("do0");
				exit(1);
			}
		} else if(rot == 1) {

			if(j< lengthCube) {
				inew = 0;
				jnew = 2 * lengthCube + j % lengthCube;
				
				newRot=1;
				
			} else if(j >= lengthCube && j< 2*lengthCube){
				inew = 3 * lengthCube + j % lengthCube;
				jnew = lengthCube - 1;
				
				newRot = 2;

			} else if(j >= 2*lengthCube && j< 3*lengthCube){

				inew = lengthCube + j % lengthCube;
				jnew = 2 * lengthCube - 1;
				
				newRot = 2;

			} else{
				sopl("do1");
				exit(1);
			}
		} else if(rot == 2) {

			if( i< lengthCube) {
				inew = 3 * lengthCube - 1 - i % lengthCube;
				jnew= 0;

				newRot = 0;
				
			} else if( i >=lengthCube && i< 2*lengthCube) {
				inew= 2 * lengthCube;
				jnew= i % lengthCube;
				
				newRot = 1;

			} else if( i >= 2 * lengthCube && i< 3*lengthCube) {
				inew= lengthCube - 1 - i % lengthCube;
				jnew=  lengthCube;
				
				newRot = 0;

			} else if( i >= 3 * lengthCube && i < 4*lengthCube) {
				inew = 0;
				jnew = lengthCube + i % lengthCube;
				
				newRot = 1;

			} else{
				sopl("do0");
				exit(1);
			}
			
		} else if(rot == 3) {

			if(j< lengthCube) {
				inew = lengthCube + j % lengthCube;
				jnew = lengthCube;
				
				newRot=0;
				
			} else if(j >= lengthCube && j< 2*lengthCube){
				inew = 3 * lengthCube + j % lengthCube;
				jnew = 0;
				
				newRot = 0;

			} else if(j >= 2*lengthCube && j< 3*lengthCube){

				inew = 4 * lengthCube - 1;
				jnew = j % lengthCube;

				newRot = 3;

			} else{
				sopl("do2");
				exit(1);
			}
		}
		return new int[] {inew, jnew, newRot};
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
