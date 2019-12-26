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

public class prob21 {

	
	public static void main(String[] args) {
		Scanner in;
		try {
			in = new Scanner(new File("in2019/prob2019in21.txt"));
			boolean part2 = true;
			String line = "";

			line = "";
			while(in.hasNextLine()) {
				line += in.nextLine();
			}
			
			String cmds[] = line.split(",");
			
			
			LinkedList<Long> inputQueue = new LinkedList<Long>();
			
			int MAX_INST = 15;
			String scriptCmds[] = new String[MAX_INST];
			
			/*String script = 
"NOT A J" + "\n" +
"NOT B T" + "\n" +
"AND T J" + "\n" +
"NOT C T" + "\n" +
"AND T J" + "\n" +
"AND D J" + "\n" +
"WALK"  + "\n" ;*/
			
			/*String script = 
					"NOT A J" + "\n" +
					"WALK" + "\n";
			*/
			String script = "";
			
			if(part2 == false) { 
				script= "NOT A J" + "\n" +
						"OR J T" + "\n" +
						"NOT B J" + "\n" +
						"OR J T" + "\n" +
						"NOT C J" + "\n" +
						"OR J T" + "\n" +
						"AND D T" + "\n" +
				//At this point, T is true if D avail and A or B or C is hole.
						"NOT T J" + "\n" +
						"NOT J J" + "\n" +
				//At this point, I transferred the value of T into J...
				//I could've saved the last 2 steps by planning ahead, but whatever.

						"WALK" + "\n";
			} else {
				
				//TODO: This program sucks, but it passed the tests...
				//TODO: Make a perfect jumper program

				script= "NOT A J" + "\n" +
						"OR J T" + "\n" +
						"NOT B J" + "\n" +
						"OR J T" + "\n" +
						"NOT C J" + "\n" +
						"OR J T" + "\n" +
						"AND D T" + "\n" +
					//At this point, T is true if D avail and A or B or C is hole.

						//NEW CODE:
					//Check if D is a good landing spot because it can do a double jump:
						"NOT H J" + "\n" +
						"NOT J J" + "\n" +
					//At this point J is true if H is avail... jump if double jump possible

					//If we could do a double jump AND we could jump onto location D:
						"AND T J" + "\n" +
						
						
				//DESPERATE JUMP:
						"NOT A T" + "\n" +
						"OR T J" + "\n" +
				//RUN:
						"RUN" + "\n";
			}
			for(int i=0; i<script.length(); i++) {
				inputQueue.add((long)script.charAt(i));
			}
			
			IntCode intCode = new IntCode(cmds, inputQueue);

			//pause on output, so I can process it...
			intCode.setPauseOnOutput();
			
			prob21InputGenerator inputReader = new prob21InputGenerator(intCode);
			
			intCode.setInputReader(inputReader);
			
			
			
			String output = "";
			while(intCode.isHalted() == false) {
				
				long tmp = intCode.runProg();
				
				if(tmp >=0 && tmp <256) {
					char x = (char)tmp;
					output += x;
					if(x == '\n') {
						sop(output);
						output = "";
						
					}
				} else {
					sopl("Answer: " + tmp);
				}
			}
			sopl();

			
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
