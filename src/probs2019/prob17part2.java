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

public class prob17part2 {

	
	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2019/prob2019in17.txt"));
			 //in = new Scanner(new File("in2019/prob2019in17.txt.test"));
			int numTimes = 0;
			 
			int count = 0;
			boolean part2 = true;
			String prog = "";

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

			prog = "";
			while(in.hasNextLine()) {
				prog += in.nextLine();
			}
			
			String cmds[] = prog.split(",");
			
			
			ArrayList ints = new ArrayList<Integer>();
			LinkedList<Long> inputQueue = new LinkedList<Long>();
			
			IntCode intCode = new IntCode(cmds, inputQueue);
			
			
			//pause on output, so I can process it...
			intCode.setPauseOnOutput();
			
			
			ArrayList <String>lines2 = new ArrayList<String>();
			
			
			String scaff = "";
			while(intCode.isHalted() == false) {
				char next = (char)intCode.runProg();
				if(next =='\n') {
					if(scaff.length() >0) {
						lines2.add(scaff);
					}
					scaff = "";
				} else {
					scaff += next;
				}
			}

			sopl("Scaff: ");
			for(int i=0; i<lines2.size(); i++) {
				for(int j=0; j<lines2.get(i).length(); j++) {
					sop(lines2.get(i).charAt(j));
					
					if(lines2.get(i).charAt(j) == '#') {
						if(i+1 < lines2.size() && lines2.get(i+1).charAt(j) == '#'
								&& j+1 < lines2.get(i).length() && lines2.get(i).charAt(j+1) == '#'
								&& i-1 >= 0 && lines2.get(i-1).charAt(j) == '#'
								&& j-1 >= 0 && lines2.get(i).charAt(j-1) == '#') {
							count += i*j;
						}
					}
				}
				sopl();
			}
			
			sopl("Answer part 1: " + count);
			
			getAnswerPart2(prog, lines2);
			
			
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	
	public static void getAnswerPart2(String prog, ArrayList <String>lines2) {
		String longAnswer = getLongAnswer(lines2);

		sopl("Long answer:");
		sopl(longAnswer);
		
		solveProg(longAnswer);
		
		System.out.println("Solved prog to convert to intcode:");
		sopl(curProgram);
		
		sopl("Num answers found: " + numWays);
		
		
		
		
		
		curProgram = curProgram + "n" + "\n";
		
		System.out.println("RevisedProg:" + curProgram);
		System.out.println("curProgram");
		//Force the vacuum robot to wake up by changing the value in your ASCII program at address 0 from 1 to 2.
		//The ASCII program will use input instructions to receive them, but they need to be provided as ASCII code; end each line of logic with a single newline, ASCII code 10.
		
		
		//Finally, you will be asked whether you want to see a continuous video feed; provide either y or n and a newline
		
		//Try to run prog:
		String cmds[] = prog.split(",");
		sopl("Debug: " + cmds[0]);
		//show that we want to do part 2:
		cmds[0] = "2";
		sopl("Debug: " + cmds[0]);
		
		
		LinkedList<Long> inputQueue = new LinkedList<Long>();
		
		sopl("Program:");
		for(int i=0; i<curProgram.length(); i++) {
			inputQueue.add((long)curProgram.charAt(i));
			sop((long)curProgram.charAt(i) + ",");
		}
		
		sopl("Hello");
		IntCode intCode = new IntCode(cmds, inputQueue);
		
		//pause on output, so I can process it...
		intCode.setPauseOnOutput();
		
		sopl("prog run:");
		
		boolean gotAnswer = false;
		
		while(intCode.isHalted() == false) {
			long temp = intCode.runProg();
			
			if(temp < 256) {
				char next = (char)temp;
			
				//sopl("Answer:");
				sop(next);
				
				if(gotAnswer) {
					System.out.println("Output after answer: " + temp);
				}
			} else {
				
				System.out.println("Answer: " + temp);
				gotAnswer = true;
			}
		}
		
	}
	
	public static String getLongAnswer(ArrayList <String>lines2) {
		
		//Model map:
		int startI = -1;
		int startJ = -1;
		
		int curDir = -1;
		
		boolean map[][] = new boolean[lines2.size()][lines2.get(0).length()];
		sopl("Scaff: ");
		for(int i=0; i<lines2.size(); i++) {
			for(int j=0; j<lines2.get(i).length(); j++) {
				//sop(lines2.get(i).charAt(j));
				if(lines2.get(i).charAt(j) == '.') {
					map[i][j] = false;
					
				} else {
					map[i][j] = true;
				}
				//^, v, <, or >
				if(lines2.get(i).charAt(j) == '^' || lines2.get(i).charAt(j) == 'v' || lines2.get(i).charAt(j) == '<' || lines2.get(i).charAt(j) == '>') {
					startI = i;
					startJ = j;
					if(lines2.get(i).charAt(j) == '^') {
						curDir = 0;
					} else if(lines2.get(i).charAt(j) == '>') {
						curDir = 1;
					} else if(lines2.get(i).charAt(j) == 'v') {
						curDir = 2;
					} else if(lines2.get(i).charAt(j) == '<') {
						curDir = 3;
					}
				}
			}
		}
		
		System.out.println("Recreating map:");
		for(int i=0; i<lines2.size(); i++) {
			for(int j=0; j<lines2.get(i).length(); j++) {
				if(map[i][j] == false) {
					sop(".");
				} else if(startI == i && startJ == j) {
					sop("S");
				} else {
					sop("#");
				}
			}
			sopl();
		}
		sopl();
		
		sopl("Start: (" + startI + ", " + startJ + "): " + curDir);
		
		int numToAdd = 0;
		String longAnswer = "";
		
		int curI = startI;
		int curJ = startJ;
		
		int turnNumber = 0;
		do {
			while(canKeepGoingSameDir(map, curI, curJ, curDir)) {
				numToAdd++;
				
				if(curDir == 0) {
					curI--;
				} else if(curDir == 1) {
					curJ++;
				} else if(curDir == 2) {
					curI++;
				} else if(curDir == 3) {
					curJ--;
				}
			}
			
			if(numToAdd > 0) {
				longAnswer += numToAdd + ",";
				numToAdd = 0;
				
			}
			
			//Get dir.
			turnNumber = getNewDir(map, curI, curJ, curDir);
			
			if(turnNumber == 1) {
				longAnswer +=  "R,";
			} else if(turnNumber == -1) {
				longAnswer +=  "L,";
			}
			
			//update curDirection
			curDir = (curDir + 4 + turnNumber) % 4;
			
		} while(turnNumber != 0);
		
		longAnswer = longAnswer.substring(0, longAnswer.lastIndexOf(","));
		
		
		return longAnswer;
		
	}
	
	
	public static boolean canKeepGoingSameDir(boolean map[][], int curI, int curJ, int curDir) {
		
		if(curDir == 0) {
			if(curI - 1 >= 0) {
				if(map[curI - 1][curJ] == true) {
					return true;
				} else {
					return false;
				}
			}
		} else if(curDir == 1) {
			if(curJ + 1 < map[0].length) {
				if(map[curI][curJ + 1] == true) {
					return true;
				} else {
					return false;
				}
			}
		} else if(curDir == 2) {
			if(curI + 1 < map.length) {
				if(map[curI + 1][curJ] == true) {
					return true;
				} else {
					return false;
				}
			}
		} else if(curDir == 3) {
			if(curJ - 1 >= 0) {
				if(map[curI][curJ - 1] == true) {
					return true;
				} else {
					return false;
				}
			}
		} else {
			System.out.println("ERROR: unexpected direction!");
			System.exit(1);
			return false;
		}
		return false;
		
	}
	
	public static int getNewDir(boolean map[][], int curI, int curJ, int curDir) {
		
		sopl(curI + "," + curJ);
		if(curDir % 2 == 0) {
			//Check west
			if(curJ - 1 >= 0 && map[curI][curJ - 1] == true) {
				if(curDir == 0) {
					return -1;
				} else {
					return 1;
				}
			}
			
			//Check east
			else if(curJ + 1 < map[0].length && map[curI][curJ + 1] == true) {
				if(curDir == 0) {
					return 1;
				} else {
					return -1;
				}
				
			}
			
		} else if(curDir % 2 == 1) {
			//Check north or south:
			if(curI - 1 >= 0 && map[curI - 1][curJ] == true) {
				if(curDir == 1) {
					return -1;
				} else {
					return 1;
				}

			} else if(curI + 1 < map.length && map[curI + 1][curJ] == true) {
				if(curDir == 1) {
					return 1;
				} else {
					return -1;
				}
			}
			
		} else {
			System.out.println("ERROR: unexpected direction");
			System.exit(1);
		}
		
		return 0;
		
	}
	
	public static void solveProg(String longAnswer) {
		solveProg(longAnswer, 0, "", new String[] {"", "", ""});
	}
	
	public static int numWays = 0;
	
	public static String curProgram ="";
	
	public static void solveProg(String longAnswer, int startIndex, String main, String prog[]) {
		
		//Try to insert progA to C
		//if we can't return blank
		
		if(startIndex >= longAnswer.length()) {
		
			main = main.substring(0, main.lastIndexOf(","));
			
			System.out.println("Found answer: (TODO: format)");
			curProgram ="";
			curProgram += main + "\n";
			for(int i=0; i<prog.length; i++) {
				curProgram += prog[i] + "\n";
			}
			System.out.println(curProgram);
			
			
			numWays++;
			return;
		}
		
		String tempUpTo = longAnswer.substring(startIndex); 
		
		String tempNewMain;
		
		
		for(int i=0; i<prog.length; i++) {
			if(prog[i].length() > 0) {
				if(tempUpTo.startsWith(prog[i])) {
					tempNewMain = main + (char)('A' + i) + ",";
					solveProg(longAnswer, startIndex + prog[i].length() + 1, tempNewMain, prog);
					
				}
			} else {
				
				//Create prog:
				for(int j=1; j<=20 && j < tempUpTo.length(); j++) {
					if(j == tempUpTo.length() || tempUpTo.charAt(j) == ',') {
						prog[i] = tempUpTo.substring(0, j);
					} else {
						continue;
					}

					tempNewMain = main + (char)('A' + i) + ",";
					solveProg(longAnswer, startIndex + j + 1, tempNewMain, prog);
					
					
					prog[i] = "";
				}
				
				//Once you try to create a prog and fail, it won't work.
				break;
			}
			
		}
		
		
	}
	
	
	public static long BLACK = 0L;
	public static long WHITE = 1L;
	
	public static long getColour(Hashtable<String, Long> paint, int x, int y) {
		if(paint.get(x + "," + y) == null) {
			return BLACK;
		} else if(paint.get(x + "," + y) == BLACK) {
			return BLACK;
		} else {
			return WHITE;
		}
	}
	

	public static void setColour(Hashtable<String, Long> paint, int x, int y, long colour) {
		if(paint.get(x + "," + y) != null) {
			paint.remove(x + "," + y);
		}
		paint.put(x + "," + y, colour);
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
