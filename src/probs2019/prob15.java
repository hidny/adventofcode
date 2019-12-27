package probs2019;
import java.io.File;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

import aStar.AstarAlgo;
import aStar.AstarNode;
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
			
			char charMap[][] = convertHashMapToCharTable();
			
			int goalIJ[] = getGoal(charMap);
			
			sopl("Char map: ");
			for(int i=0; i<charMap.length; i++) {
				for(int j=0; j<charMap[0].length; j++) {
					sop(charMap[i][j]);
				}
				sopl();
			}
			
			
			//TODO: A*... I'm sure I did this before...
			
			//Copied from 2018 day 15:
			
			 prob15pos.setPuzzleInput(charMap);
			 prob15pos start = new prob15pos((int)(0 - minJ), (int)(0 - minI));
			 prob15pos goal = new prob15pos(goalIJ[1], goalIJ[0]);
			 

			ArrayList<AstarNode> path = AstarAlgo.astar(start, goal);
			
			count = -1;
			if(path != null) {
				count = path.size() - 1;
				
				for(int i=1; i + 1 <path.size(); i++) {
					charMap[((prob15pos)path.get(i)).coordY][((prob15pos)path.get(i)).coordX] =  (char)('0' + i%10);
				}
			
				sopl("Solved char map: ");
				for(int i=0; i<charMap.length; i++) {
					for(int j=0; j<charMap[0].length; j++) {
						sop(charMap[i][j]);
					}
					sopl();
				}
			}
			 
			sopl("Answer part 1: " + count);
			
			sopl("Part 2 dishonourable:");
			
			charMap = convertHashMapToCharTable();
			
			int part2Answer = -1;
			for(int i=0; i<charMap.length; i++) {
				for(int j=0; j<charMap[0].length; j++) {
					if(map.containsKey((minI + i) +"," + (minJ + j))) {
						if(map.get((minI + i) +"," + (minJ + j)) == '.' || (map.get((minI + i) +"," + (minJ + j)) >= '0' && map.get((minI + i) +"," + (minJ + j)) <= '9')) {

							 prob15pos curStart = new prob15pos((int)j, (int)i);
							 ArrayList<AstarNode> path2 = AstarAlgo.astar(curStart, goal);
							 
							 if(path2 != null && path2.size() - 1 > part2Answer) {
								 part2Answer = path2.size() - 1;
							 }
							 if(path2 == null) {
								 sopl("What? How could the path be null?");
								 sopl(i +"," + j);
								 exit(1);
							 }
						}
					}
				}
			}
			//: 278
			sopl("Answer part 2 dishonourable: " + part2Answer);

			
			//Breadth first search:
			sopl("Part 2 honourable:");
			charMap = convertHashMapToCharTable();
			
			
			//record coord as i,j
			LinkedList<String> queueCoord = new LinkedList<String>();
			
			queueCoord.add(goalIJ[0] + "," + goalIJ[1]);
			
			int dist[][] = new int[charMap.length][charMap[0].length];
			for(int i=0; i<dist.length; i++) {
				for(int j=0; j<dist[0].length; j++) {
					dist[i][j] = -1;
				}
			}
			dist[goalIJ[0]][goalIJ[1]] = 0;
			
			int maxDist = 0;
			
			while(queueCoord.isEmpty() == false) {
				String coord = queueCoord.getFirst();
				queueCoord.remove();

				int i = pint(coord.split(",")[0]);
				int j = pint(coord.split(",")[1]);
				int curDist = dist[i][j];
				if(curDist == -1) {
					sopl("ERROR: Impossible dist");
					exit(1);
				}
				
				for(int dir=1; dir<=4; dir++) {
					long ret[] = getCoordProbe(i, j, dir);
					
					if(dist[(int)ret[0]][(int)ret[1]] == -1 && charMap[(int)ret[0]][(int)ret[1]] != '#') {
						queueCoord.add(ret[0] +"," + ret[1]);
						dist[(int)ret[0]][(int)ret[1]] = curDist + 1;
						
						if(maxDist < curDist + 1) {
							maxDist = curDist + 1;
						}
					}
				}
				
			}
			sopl("Part 2 answer: " + maxDist);
			
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
		
		for(long dir = 1; dir<=4; dir++) {
			
			long newCoord[] = getCoordProbe(curI, curJ, dir);
			
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
			
			intCode.inputQueue.add(dir);
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
				if(dir == 1) {
					oppositeDir = 2;
				} else if(dir == 2) {
					oppositeDir = 1;
					
				} else if(dir == 3) {
					oppositeDir = 4;
					
				} else if(dir == 4) {
					oppositeDir = 3;
					
				} else {
					sopl("No opposite Direction! ERROR");
					exit(1);
				}
				
				intCode.inputQueue.add(oppositeDir);
				
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
	
	public static char[][] convertHashMapToCharTable() {
		char charMap[][] = new char[(int)(maxI-minI + 1)][(int)(maxJ - minJ +1)];
		
		int goalI = -1;
		int goalJ = -1;
		for(int i=0; i<charMap.length; i++) {
			for(int j=0; j<charMap[0].length; j++) {
				if(minI + i ==0 && minJ + j ==0) {
					//Find start location:
					charMap[i][j] = 'S';
				} else if(map.containsKey((minI + i) +"," + (minJ + j))) {
					if(map.get((minI + i) +"," + (minJ + j)) == 'O') {
						goalI = i;
						goalJ = j;
					}
					charMap[i][j] = map.get((minI + i) +"," + (minJ + j));
				} else {
					charMap[i][j] = ' ';
				}
			}
		}
		
		return charMap;
	}
	
	//pre: there is a goal
	//post: returns i, j
	public static int[] getGoal(char charMap[][]) {

		for(int i=0; i<charMap.length; i++) {
			for(int j=0; j<charMap[0].length; j++) {
				if(charMap[i][j] == 'O') {
					return new int[]{i, j};
				}
			}
		}
		return null;
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
