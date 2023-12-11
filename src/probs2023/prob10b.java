package probs2023;
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

public class prob10b {

	//day1 part 1
	//2:38.01
	
	public static void main(String[] args) {
		Scanner in;
		try {
			in = new Scanner(new File("in2023/prob2023in10.txt"));
			//in = new Scanner(new File("in2023/prob2023in0.txt"));
			int numTimes = 0;
			 
			int count = 0;
			boolean part2 = false;
			String line = "";

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

			int most = 0;
			int most2 = 0;
			int most3 = 0;
			int cur = 0;
			ArrayList ints = new ArrayList<Integer>();
			for(int i=0; i<lines.size(); i++) {
				
				
				line = lines.get(i);
				
			}

			int start[] = getS(lines);
			
			int numStepsNeeded = 0;
			int dirToUse = -1;
			
			HashSet<String> transCoordsUsed = new HashSet<String>();
			
			for(int dirStart=0; dirStart<4; dirStart++) {
				
				numStepsNeeded = 0;
				int curDir = dirStart;
				int curi = start[0];
				int curj = start[1];
				
				transCoordsUsed = new HashSet<String>();
				
				
				if(dirStart == 0) {
					transCoordsUsed.add(curi + "," + curj + "," + (curi+1) + "," + curj);
					curi++;
				} else if(dirStart == 1) {
					transCoordsUsed.add(curi + "," + (curj-1) + "," + curi + "," + curj);
					curj--;
				} else if(dirStart == 2) {
					transCoordsUsed.add((curi-1) + "," + curj + "," + curi + "," + curj);
					curi--;
				} else if(dirStart == 3) {
					transCoordsUsed.add(curi + "," + (curj+1) + "," + curi + "," + curj);
					curj++;
				}
				
				do {
					
					transCoordsUsed.add(curi + "," + curj + "," + f(lines, curi, curj, curDir)[0] + "," + f(lines, curi, curj, curDir)[1]);
					
					int ret[] = f(lines, curi, curj, curDir);
					curi = ret[0];
					curj = ret[1];
					curDir = ret[2];
					
					numStepsNeeded++;
					
				} while(curi >= 0 && curj>=0 && !(curi == start[0] && curj == start[1]));
				
				if(curi == start[0] && curj == start[1]) {
					
					
					dirToUse = dirStart;
					break;
				}
			}
			
			
			
			/*if(numStepsNeeded % 2 == 1) {
				sopl("doh!");
				exit(1);
			}*/
			
			sopl(numStepsNeeded);


			ArrayList<String> lines15= new ArrayList<String>();
			
			sopl();
			sopl();

			for(int i=0; i<lines.size(); i++) {

				line = lines.get(i);
				String tmp = lines.get(i);
				
				for(int j=0; j<lines.get(i).length(); j++) {
					
					if(tmp.charAt(j) != '.') {
						
						if(!(transCoordsUsed.contains((i+1) + "," + j + "," + i + "," + j) ||
								transCoordsUsed.contains((i-1) + "," + j + "," + i + "," + j) ||
								transCoordsUsed.contains(i + "," + (j+1) + "," + i + "," + j) ||
								transCoordsUsed.contains(i + "," + (j-1) + "," + i + "," + j)) ) {
							
							
							tmp = tmp.substring(0, j) + "." + tmp.substring(j+1, tmp.length());
						}
					}
					
				}
				

				sopl();
				sopl();
				sopl(line);
				sopl(tmp);
				lines15.add(tmp);
			}
			
			lines = lines15;
			
			ArrayList<String> lines2 = new ArrayList<String>();
			
			for(int i=0; i<lines.size(); i++) {
				
				String tmp = lines.get(i);
				
				String output = "_";
				
				for(int j=0; j<lines.get(i).length(); j++) {
					
					if(	transCoordsUsed.contains(i + "," + j + "," + i + "," + (j + 1))
							|| transCoordsUsed.contains(i + "," + (j+1) + "," + i + "," + j)
					) {
						output+= "" + lines.get(i).charAt(j) + "0";
					} else {
						output+= "" + lines.get(i).charAt(j) + "_";
					}
					
				}
				
				lines2.add(output);
				
				String output2 = "";
				for(int j=0; j<output.length(); j++) {
					
					if(j%2 == 1) {
						
						int origJ = (j - 1) / 2;
						
						if(	transCoordsUsed.contains((i+1) + "," + origJ + "," + i + "," + origJ)
								|| transCoordsUsed.contains(i + "," + origJ + "," + (i+1) + "," + origJ)
						) {
							output2 += "" + "0";
						} else {
							
							output2 += "_";
						}
						
					} else {
						output2 += "_";
					}
				}
				

				lines2.add(output2);
				
				
			}
			
			sopl();
			sopl();
			sopl();
			
			for(int i=0; i<lines2.size(); i++) {
				for(int j=0; j<lines2.get(i).length(); j++) {
					sop(lines2.get(i).charAt(j));
				}
				sopl();
				
			}
			
			int numPoints = getNumPoints(lines2);
			
			if(numPoints != getNumPoints(lines)) {
				sopl("doh");
				exit(1);
			}
			
			int potentialRet = 0;
			start = getFirstPoint(lines);
			
			Queue<String> queue = new LinkedList();
			boolean explored[][] = new boolean[lines2.size()][lines2.get(0).length()];
			
			queue.add(start[0] + "," + start[1]);
			explored[start[0]][start[1]]  =true;
			
			while(queue.isEmpty() == false) {
				
				String coordS = queue.poll();
				

				int coordI = pint(coordS.split(",")[0]);
				int coordJ = pint(coordS.split(",")[1]);
				
				for(int dir=0; dir<4; dir++) {
					
					int newCoordI = coordI;
					int newCoordJ = coordJ;
					
					if(dir == 0) {
						newCoordI--;
					} else if(dir == 1) {
						newCoordJ++;
					} else if(dir == 2) {
						newCoordI++;
					} else if(dir == 3) {
						newCoordJ--;
					}
					
					
					if(newCoordI >= 0 && newCoordI < explored.length && newCoordJ >=0 && newCoordJ < explored[0].length) {
						
						if( ! explored[newCoordI][newCoordJ]) {
							
							explored[newCoordI][newCoordJ] = true;
							
							char charFound = lines2.get(newCoordI).charAt(newCoordJ);
							if(charFound == '.') {
								potentialRet++;
								queue.add(newCoordI + "," + newCoordJ);
							
							} else if(charFound == '_') {
								
								queue.add(newCoordI + "," + newCoordJ);
							}
						}
						
					} else {
						sopl("Use 1st answer");
					}
					
				}
				
			}

			
			for(int i=0; i<explored.length; i++) {
				for(int j=0; j<explored[0].length; j++) {
					if(explored[i][j]) {
						sop("#");
					} else {
						sop(" ");
					}
				}
				sopl();
			}
			
			
			//At this point flood fill?
			

			sopl("Answer: " + (numPoints -potentialRet ));
			
			sopl("Or: " + potentialRet + " useless told to use 1st answer");
			
			
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	

	public static int[] getFirstPoint(ArrayList <String>lines) {
		
		for(int i=0; i<lines.size(); i++) {
			for(int j=0; j<lines.get(i).length(); j++) {
				if(lines.get(i).charAt(j) == '.') {
					return new int[] {i, j};
					
				}
			}
		}
		
		return null;
	}
	
	public static int getNumPoints(ArrayList <String>lines) {
		int ret = 0;
		
		for(int i=0; i<lines.size(); i++) {
			for(int j=0; j<lines.get(i).length(); j++) {
				if(lines.get(i).charAt(j) == '.') {
					ret++;
					
				}
			}
		}
		
		return ret;
	}
	
	public static int[] getS(ArrayList <String>lines) {

		for(int i=0; i<lines.size(); i++) {
			sopl(lines.get(i));
			for(int j=0; j<lines.get(i).length(); j++) {
				if(lines.get(i).charAt(j) == 'S') {
					
					sopl("ahsdflef");
					
					return new int[] {i, j};
					
				}
			}
		}
		
		return new int[] {-5, -5};
	}
	
	public static int[] f(ArrayList <String>lines, int i, int j, int comingFrom) {
		
		char scan = lines.get(i).charAt(j);
		
		if(scan == '|' && comingFrom ==0) {
			return new int[] {i+1, j, 0};
			
		} else if(scan == '|' && comingFrom ==2) {
			return new int[] {i-1, j, 2};
			
		} else if(scan == '-' && comingFrom ==1) {
			return new int[] {i, j-1, 1};
			
		} else if(scan == '-' && comingFrom ==3) {
			return new int[] {i, j+1, 3};

		} else if(scan == 'L' && comingFrom ==0) {
			return new int[] {i, j+1, 3};
			
		} else if(scan == 'L' && comingFrom ==1) {
			return new int[] {i-1, j, 2};
			
		} else if(scan == 'J' && comingFrom ==0) {
			return new int[] {i, j-1, 1};
			
		} else if(scan == 'J' && comingFrom ==3) {
			return new int[] {i-1, j, 2};
			
		} else if(scan == '7' && comingFrom ==2) {
			return new int[] {i, j-1, 1};
			
		} else if(scan == '7' && comingFrom ==3) {
			return new int[] {i+1, j, 0};
		
		} else if(scan == 'F' && comingFrom ==1) {
			return new int[] {i+1, j, 0};
			
		} else if(scan == 'F' && comingFrom ==2) {
			return new int[] {i, j+1, 3};
			
		} else if(scan == 'S') {
			return new int[] {-5, -5, -5};
		
		} else if(scan == '.') {
			return new int[] {-10, -10, -10};
		}
		
		
		return new int[] {-1, -1, -1};
		
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
