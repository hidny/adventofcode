package probs2019;
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

import probs2019after1am.*;

public class prob15 {

	
	public static void main(String[] args) {
		Scanner in;
		try {
			String filename = "in2019/prob2019in15.txt";
			 in = new Scanner(new File(filename));
			int numTimes = 0;
			 
			int count = 0;
			String line = "";

			ArrayList <String>lines = new ArrayList<String>();
			
			
			line = "";
			while(in.hasNextLine()) {
				line += in.nextLine();
			}
			
			
			LinkedList<Long> inputQueue = new LinkedList<Long>();
			
			String cmdsCopy[] = line.split(",");
			
			IntCode intCode = new IntCode(cmdsCopy, inputQueue);
			
			intCode.setPauseOnOutput();
			
			
			exploreArea(intCode);
			
			
			sopl("Answer: " + count);
			
			
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	
	private static long minI=0;
	private static long maxI=0;
	private static long minJ=0;
	private static long maxJ=0;
	
	public static Hashtable<String, Character> map = new Hashtable<String, Character>();
	
	public static void exploreArea(IntCode intCode) {

		//Assume Droid starts on empty spot...
		map.put("0,0", new Character('.'));
		explore(0,0,intCode);
	}
	
	public static void explore(long curI, long curJ, IntCode intCode) {
		
		long dirList[] = new long[] {1, 2, 3, 4};
		
		for(int dir = 0; dir<4; dir++) {
			
			long newCoord[] = getCoordProbe(curI, curJ, dirList[dir]);
			
			if(map.containsKey(newCoord[0] +"," + newCoord[1])) {
				continue;
			}
			
			if(newCoord[0] < minI) {
				minI = newCoord[0];
			} else if(newCoord[0] > maxI) {
				maxI = newCoord[0];
			}
			
			if(newCoord[1] < minJ) {
				minJ = newCoord[1];
			} else if(newCoord[1] > maxJ) {
				maxJ = newCoord[1];
			}
			
			intCode.inputQueue.add(dirList[dir]);
			if(intCode.inputQueue.size() != 1) {
				sopl("ERROR: input queue too big or small");
				exit(1);
			}
			
			if(intCode.inputQueue.get(0) < 1 || intCode.inputQueue.get(0) > 4) {
				sopl("ERROR: invalid input!");
			}
			
			long ret = intCode.runProg();
			
			
			if(intCode.isHalted()) {
				sopl("ERROR: intcode halted!");
				exit(1);
			}
			
			
			if(ret == 0) {
				//Hit a wall.
				sopl("Hit wall");
				map.put(newCoord[0] +"," + newCoord[1], new Character('#'));
				
				drawMap(curI, curJ);
				
				
			} else if(ret == 1) {

				sopl("found empty space");
				
				//Empty space
				map.put(newCoord[0] +"," + newCoord[1], new Character('.'));
				drawMap(newCoord[0], newCoord[1]);
				
				explore(newCoord[0], newCoord[1], intCode);
				
				//Return to orig location:
				
			} else if(ret == 2) {

				sopl("found goal");
				map.put(newCoord[0] +"," + newCoord[1], new Character('O'));
				
				drawMap(curI, curJ);
				//Found goal
				explore(newCoord[0], newCoord[1], intCode);

			} else {
				sopl("AAHHH! unknown ret!");
				exit(1);
			}
			
			if(ret !=0 ) {
				//Moved! time to return to orig location:
				
				long oppositeDir = -1;
				if(dirList[dir] == 1) {
					oppositeDir = 2;
				} else if(dirList[dir] == 2) {
					oppositeDir = 1;
					
				} else if(dirList[dir] == 3) {
					oppositeDir = 4;
					
				} else if(dirList[dir] == 4) {
					oppositeDir = 3;
					
				} else {
					sopl("No opposite Direction! ERROR");
					exit(1);
				}
				
				intCode.inputQueue.add(oppositeDir);
				if(intCode.inputQueue.size() != 1) {
					sopl("ERROR: input queue too big or small");
					exit(1);
				}
				
				if(intCode.inputQueue.get(0) < 1 || intCode.inputQueue.get(0) > 4) {
					sopl("ERROR: invalid input!");
				}
				
				long ret2 = intCode.runProg();
				
				if(ret2 == 0) {
					sopl("Hit a wall on the way back... something is wrong");
					exit(1);
				}
			}
		}
		
	}
	
	public static void drawMap(long curI, long curJ) {
		sopl("Drawing map:");
		for(long i=minI; i<=maxI; i++) {
			for(long j=minJ; j<=maxJ; j++) {
				if(i==curI && j == curJ) {
					sop("C");
				} else if(i==0 && j==0) {
					sop("D");
				} else if(map.containsKey(i +"," + j)) {
					sop(map.get(i +"," + j));
				} else {
					sop(" ");
				}
			}
			sopl();
		}
		sopl("Cur coord: " + curI + ", " + curJ);
		sopl("End drawing map");
	}
	
	//returns coord i, j and i starts from top:
	public static long[] getCoordProbe(long curI, long curJ, long dir) {
		long ret[] = new long[2];
		ret[0] = curI;
		ret[1] = curJ;
		
		if(dir == 1) {
			ret[0]--;
		} else if(dir == 2) {
			ret[0]++;
		} else if(dir == 3) {
			ret[1]--;
		} else if(dir == 4) {
			ret[1]++;
		} else {
			sopl("ERROR: unknown dir");
			exit(1);
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
