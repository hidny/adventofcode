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

public class prob13 {

	
	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2019/prob2019in13.txt"));
			 //in = new Scanner(new File("in2019/prob2019in13.txt.test"));
			int numTimes = 0;
			 
			int count = 0;
			boolean part2 = true;
			String line = "";

			int LIMIT = 20000;

			line = "";
			while(in.hasNextLine()) {
				line += in.nextLine();
			}
			
			String cmds[] = line.split(",");
			
			
			ArrayList ints = new ArrayList<Integer>();
			LinkedList<Long> inputQueue = new LinkedList<Long>();
			
			IntCode intCode = new IntCode(cmds, inputQueue);
			
			
			//pause on output, so I can process it...
			intCode.setPauseOnOutput();
			
			
			ArrayList <String>lines2 = new ArrayList<String>();
			
			Hashtable<String, Long> screen = new Hashtable<String, Long>();
			
			int maxX = 0;
			int maxY = 0;

			int EMPTY = 0;
			int WALL = 1;
			int BLOCK = 2;
			int HORI_PADDLE = 3;
			int BALL = 4;
			
			
			String scaff = "";
			while(intCode.isHalted() == false) {
				int x = (char)intCode.runProg();
				int y = (char)intCode.runProg();
				int tileId = (char)intCode.runProg();
				
				if(tileId == WALL && x > maxX) {
					maxX = x + 1;
				}
				if(tileId == WALL && y > maxY) {
					maxY = y + 1;
				}
				if(tileId == BLOCK) {
					count++;
				}
				if(screen.get(x + "," + y) != null && screen.get(x + "," + y) == BLOCK) {
					count++;
				}
				setTile(screen, x, y, tileId);
			}

			for(int y=0; y<maxY; y++) {
				for(int x=0; x<maxX; x++) {
					if(screen.get(x + "," + y) == null) {
						sop(" ");
					} else if(screen.get(x + "," + y) == EMPTY) {
						sop(" ");
					} else if(screen.get(x + "," + y) == WALL) {
						sop("W");
					} else if(screen.get(x + "," + y) == BLOCK) {
						sop("#");
					} else if(screen.get(x + "," + y) == HORI_PADDLE) {
						sop("=");
					} else if(screen.get(x + "," + y) == BALL) {
						sop("O");
					}
				}
				sopl();
			}
			
			//1093 too high...
			sopl("Answer part 1: " + count);
			//475 too high...
			
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	
	public static long BLACK = 0L;
	public static long WHITE = 1L;
	
	public static long getTile(Hashtable<String, Long> screen, int x, int y) {
		if(screen.get(x + "," + y) == null) {
			return BLACK;
		} else if(screen.get(x + "," + y) == BLACK) {
			return BLACK;
		} else {
			return WHITE;
		}
	}
	

	public static void setTile(Hashtable<String, Long> screen, int x, int y, long tileId) {
		if(screen.get(x + "," + y) != null) {
			screen.remove(x + "," + y);
		}
		screen.put(x + "," + y, tileId);
		
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
