package probs2019;
import java.io.File;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

import number.IsNumber;
import utils.Mapping;
import utils.Sort;

public class prob24part2 {

	
	public static void main(String[] args) {
		Scanner in;
		try {
			String filename = "in2019/prob2019in24.txt";
			in = new Scanner(new File(filename));
			
			int NUM_MINUTES = -1;
			
			if(filename.contains("test")) {
				NUM_MINUTES = 10;
			} else {
				NUM_MINUTES = 200;
			}
			
			ArrayList <String>lines = new ArrayList<String>();
			

			while(in.hasNextLine()) {
				lines.add(in.nextLine());
				
				
			}
			

			//TODO: I might have to skip using strings and use ints instead...
			HashSet<String> bugsNext = new HashSet<String>();
			HashSet<String> bugsPrev = new HashSet<String>();
			

			
			//Initialize bugs:
			for(int i=0; i<lines.size(); i++) {
				for(int j=0; j<lines.get(0).length(); j++) {
					
					if(i == 2 && j == 2) {
						//Do nothing because it's the middle one
					} else if(lines.get(i).charAt(j) == '#') {
						bugsNext.add(0 +"," + i + "," + j);

						if(i==LEVEL_LENGTH || j ==LEVEL_LENGTH) {
							sopl("ERROR: what?");
							exit(1);
						}
					}
				}
			}
			
			bugsPrev = bugsNext;

			for(int minutes=0; minutes<NUM_MINUTES; minutes++) {
				
				bugsNext = new HashSet<String>();
				
			
				
				Hashtable<String, Integer> bugNeighbourTable = GetListNeighboursToConsider(bugsPrev);
				
				
				Enumeration<String> keys = bugNeighbourTable.keys();
				
				sopl("PRINT STUFF:");
		        while(keys.hasMoreElements()){
		            String key = keys.nextElement();
		            //System.out.println("Value of "+key+" is: "+bugNeighbourTable.get(key));
		            
		            boolean wasAlive =  bugsPrev.contains(key);
		            
		            if(wasAlive && bugNeighbourTable.get(key) != 1) {
		            	//sopl(key + " now dead: " + bugNeighbourTable.get(key));
		            	//Dead do nothing
		            } else if(wasAlive == false 
		            		&& (bugNeighbourTable.get(key) == 1 || bugNeighbourTable.get(key) == 2) ){
		            	//Alive:
		            	bugsNext.add(key);
		            	
		            } else if(wasAlive) {
		            	//If it was alive, it stays alive
		            	bugsNext.add(key);
		            } else {
		            	//sopl(key + " didn't live: " + bugNeighbourTable.get(key));
		            }
		           
		            
		        }
		        
		        for(int level=debugMinDepth; level<=debugMaxDepth; level++) {
		        	sopl("Depth " + level);
		        	for(int i=0; i<LEVEL_LENGTH; i++) {
		        		for(int j=0; j<LEVEL_LENGTH; j++) {
		        			if(i == 2 && j == 2) {
		        				sop("?");
		        			} else if(bugsNext.contains(level + "," + i + "," + j)) {
		        				sop("#");
		        			} else {
		        				sop(" ");
		        			}
		        		}
		        		sopl();
		        	}
		        	sopl();
		        	sopl();
		        }

				sopl("========");
			    
				bugsPrev = bugsNext;
			}
			sopl();
			sopl("========");
			
			sopl("Answer: " + bugsNext.size());
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}

	public static int LEVEL_LENGTH = 5;
	public static int MIDDLE = LEVEL_LENGTH / 2;
	
	public static Hashtable<String, Integer> GetListNeighboursToConsider( HashSet<String> bugsFromPrevIt) {
		
		Object bugs[] = bugsFromPrevIt.toArray();
		
		Hashtable<String, Integer> bugNeighbourTable = new Hashtable<String, Integer>();
		
		for(int it=0; it<bugs.length; it++) {
			int levelBug = pint(((String)bugs[it]).split(",")[0]);
			int iBug = pint(((String)bugs[it]).split(",")[1]);
			int jBug = pint(((String)bugs[it]).split(",")[2]);
			
			// HANDLE ROW BORDERS
			if(iBug == 0) {

				bugNeighbourTable = addBugNeighbour(bugNeighbourTable, levelBug-1, MIDDLE-1, MIDDLE);
				
			} else if(iBug == LEVEL_LENGTH - 1) {
				bugNeighbourTable = addBugNeighbour(bugNeighbourTable, levelBug-1, MIDDLE+1, MIDDLE);
				
				
			} else if(jBug == MIDDLE) {
				if(iBug == MIDDLE) {
					sopl("ERROR: no bugs should be in middle!");
					exit(1);
				} else if(iBug == MIDDLE - 1 || iBug == MIDDLE + 1 ) {
					
					int row = -1;
					if(iBug == MIDDLE - 1) {
						row = 0;
					} else {
						row = LEVEL_LENGTH - 1;
					}
					for(int j=0; j<LEVEL_LENGTH; j++) {
						bugNeighbourTable = addBugNeighbour(bugNeighbourTable, levelBug+1, row, j);
					}
				
				} else {
					sopl("ERROR (1): unexpected value of i: " + iBug);
					exit(1);
				}
			}
			
			// HANDLE COL BORDERS
			if(jBug == 0) {

				bugNeighbourTable = addBugNeighbour(bugNeighbourTable, levelBug-1, MIDDLE, MIDDLE-1);
				
			} else if(jBug == LEVEL_LENGTH - 1) {
				bugNeighbourTable = addBugNeighbour(bugNeighbourTable, levelBug-1, MIDDLE, MIDDLE+1);
				
				
			} else if(iBug == MIDDLE) {
				if(jBug == MIDDLE) {
					sopl("ERROR: no bugs should be in middle! 2");
					exit(1);
				} else if(jBug == MIDDLE - 1 || jBug == MIDDLE + 1 ) {
					
					int col = -1;
					if(jBug == MIDDLE - 1) {
						col = 0;
					} else {
						col = LEVEL_LENGTH - 1;
					}
					for(int i=0; i<LEVEL_LENGTH; i++) {
						bugNeighbourTable = addBugNeighbour(bugNeighbourTable, levelBug+1, i, col);
					}
				
				} else {
					sopl("ERROR: unexpected value of j: " + jBug);
					exit(1);
				}
			}
			
			//HANDLE Bug neighboursWithSame level:
			for(int i=Math.max(iBug-1, 0); i<=Math.min(LEVEL_LENGTH - 1, iBug+1); i++) {
				for(int j=Math.max(jBug-1, 0); j<=Math.min(LEVEL_LENGTH - 1, jBug+1); j++) {
					if(i == 2 && j == 2) {
						//No bugs in middle
						continue;
					} if((iBug == i && jBug != j) || (iBug != i && jBug == j)) {
						//At this point i,j should be touching iBug and jBug and on the same level
						bugNeighbourTable = addBugNeighbour(bugNeighbourTable, levelBug, i, j);
					}
				}
			}
			
			
			
			
		}
		
		return bugNeighbourTable;
	}
	
	public static int debugMinDepth = 0;
	public static int debugMaxDepth = 0;
	
	public static Hashtable<String, Integer> addBugNeighbour( Hashtable<String, Integer> cur, int level, int i, int j) {
		if(level <debugMinDepth) {
			debugMinDepth = level;
		} else if(level > debugMaxDepth) {
			debugMaxDepth = level;
		}
		String key = level +"," + i + "," + j;
		if(cur.containsKey(key)) {
			int curNumBugs = cur.get(key);
			cur.remove(key);
			cur.put(key, curNumBugs + 1);
			//sopl("Put multiple neighbours");
		} else {
			cur.put(key, 1);
		}
		return cur;
	}
	
		
	
	public static int getNumNeighbours(boolean map[][], int i, int j) {
		
		int numNeighbours = 0;
		for(int i2=Math.max(0, i-1); i2<=Math.min(map.length - 1, i+1); i2++) {
			for(int j2=Math.max(0, j-1); j2<=Math.min(map[0].length - 1, j+1); j2++) {
				
				if((i2 != i && j2 == j) || (i2 == i && j2 != j)) {
					//At this point i2, j2 is one of 4 adjacent tiles:
					if(map[i2][j2]) {
						numNeighbours++;
					}
					
				}
			}
		}
		
		return numNeighbours;
	}
	
	public static long getDiversity(boolean map[][]) {
		long ret = 0L;
		for(int i=map.length -1; i>=0; i--) {
			for(int j=map[0].length-1; j>=0; j-- ) {
				
				if(map[i][j]) {
					ret = 2*ret + 1;
				} else {
					ret = 2*ret + 0;
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
	
	public static void exit() {
		exit(0);
	}
	public static void exit(int code) {
		sop("Exit with code " + code);
		
		System.exit(code);
	}

}
