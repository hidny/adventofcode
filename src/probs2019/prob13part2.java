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

public class prob13part2 {


	public static int EMPTY = 0;
	public static int WALL = 1;
	public static int BLOCK = 2;
	public static int HORI_PADDLE = 3;
	public static int BALL = 4;
	
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
			
			//part2: start game:
			cmds[0] = "2";
			
			ArrayList ints = new ArrayList<Integer>();
			
			IntCode intCode = new IntCode(cmds, new LinkedList<Long>());

			Hashtable<String, Long> screen = new Hashtable<String, Long>();
			
			prob13part2UserInputGetter screenInput = new prob13part2UserInputGetter(screen, 0, 0);

			//Set input reader to std in, so I can play the game...
			intCode.setInputReader(screenInput);
			
			
			//pause on output, so I can process it...
			intCode.setPauseOnOutput();
		
			
			
			long maxX = 0;
			long maxY = 0;

			boolean seenPaddle = false;
			
			
			while(intCode.isHalted() == false) {
				
				long x = intCode.runProg();
				long y = intCode.runProg();

				long tileId = intCode.runProg();
				
				if(x == -1) {
					sopl("SCORE set: " + tileId);
				}
				if(tileId == WALL && x > maxX) {
					maxX = x;
					screenInput.refreshDims(maxX, maxY);
				}
				if(tileId == WALL && y > maxY) {
					maxY = y;
					screenInput.refreshDims(maxX, maxY);
				}
				if(tileId == BLOCK) {
					count++;
				}
				
				if(tileId == HORI_PADDLE) {
					seenPaddle = true;
				}
				
				if(screen.get(x + "," + y) != null && screen.get(x + "," + y) == BLOCK) {
					count++;
				}
				setTile(screen, x, y, tileId);
				
			}

			
			//1093 too high...
			sopl("GAME OVER!");
			//475 too high...
			
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	
	public static long BLACK = 0L;
	public static long WHITE = 1L;
	
	public static long getTile(Hashtable<String, Long> screen, long x, long y) {
		if(screen.get(x + "," + y) == null) {
			return BLACK;
		} else if(screen.get(x + "," + y) == BLACK) {
			return BLACK;
		} else {
			return WHITE;
		}
	}
	

	public static void setTile(Hashtable<String, Long> screen, long x, long y, long tileId) {
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
