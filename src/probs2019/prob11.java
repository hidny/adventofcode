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

public class prob11 {

	
	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2019/prob2019in11.txt"));
			 //in = new Scanner(new File("in2019/prob2019in11.txt.test"));
			int numTimes = 0;
			 
			int count = 0;
			boolean part2 = true;
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

			line = "";
			while(in.hasNextLine()) {
				line += in.nextLine();
			}
			
			String cmds[] = line.split(",");
			
			
			ArrayList ints = new ArrayList<Integer>();
			LinkedList<Long> inputQueue = new LinkedList<Long>();
			
			IntCode intCode = new IntCode(cmds, inputQueue);
			
			//Soft copy int code:
			intCode.inputQueue = inputQueue;
			
			//pause on output, so I can process it...
			intCode.setPauseOnOutput();
			
			Hashtable<String, Long> paint = new Hashtable<String, Long>();
			//1 white, 0 black
			
			int x =0;
			int y =0;
			
			int dir = 0;
			
			sopl("start");
			
			int minx=0;
			int miny=0;
			int maxx = 0;
			int maxy = 0;
			
			if(part2) {
				setColour(paint, 0, 0, WHITE);
			}
			
			do {
				count++;
				if(inputQueue.size() != 0) {
					sopl("input queue");
					exit(1);
				}
				inputQueue.add(getColour(paint, x, y));
				long colour = intCode.runProg();
				if(intCode.isHalted() == false) {
					//colour:
					sopl("colour: " + colour);
					setColour(paint, x, y, colour);
					
					long dirChange = intCode.runProg();
					if(intCode.isHalted() == true) {
						sopl("AHH");
						exit(1);
					}
					
					if(dirChange == 0L) {
						sopl("left");
						dir = (dir + 3) % 4;
					} else if(dirChange == 1L){
						sopl("right");
						dir = (dir + 1) % 4;
					} else {
						sopl("aah 2");
						exit(1);
					}
					
					
					if(dir == 0) {
						y++;
					} else if(dir == 1) {
						x++;
					} else if(dir == 2) {
						y--;
					} else if(dir == 3) {
						x--;
					} else {
						sopl("aah 3");
						exit(1);
					}
					
					if(x > maxx) {
						maxx = x;
					} else if(x < minx) {
						minx = x;
					} else if(y>maxy) {
						maxy=y;
					} else if(y<miny) {
						miny = y;
					}
					sopl("on ( " + x + ", " + y + ")");
					
					for(int i=maxy; i>= miny; i--) {
						for(int j=minx; j<=maxx; j++) {
							if(i==y && j==x) {
								if(dir == 0) {
									sop("^");
								} else if(dir == 1) {
									sop(">");
								} else if(dir == 2) {
									sop("v");
								} else if(dir == 3) {
									sop("<");
								} else {
									sopl("aah 4");
									exit(1);
								}
							} else {
								if(getColour(paint, j, i) == WHITE) {
									sop("#");
								} else {
									sop(".");
								}
							}
						}
						sopl();
					}
				}
				
			} while(intCode.isHalted() == false);
			
			
			//TODO: it seems to go into a loop for a while...
			
			//TODO: the test works, but not the actual thing for some reason...
			sopl("Count: " + count);
			sopl("Answer: " + paint.size());
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
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
