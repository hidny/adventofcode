package probs2021;
import java.io.File;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

import number.IsNumber;
import probs2019after1am.IntCode;
import utils.Mapping;
import utils.Sort;

public class probEaster {

	
	public static void main(String[] args) {
		Scanner in;
		try {
			//Reddit says day 7 input could be partially read by intcode:
			 in = new Scanner(new File("in2021/prob2021in7.txt"));
			 
			 //Ceci n'est pas une intcode program
			 //lol!
			 
			 //in = new Scanner(new File("in2019/prob2019in3.txt.test"));
			int numTimes = 0;
			 
			int count = 0;
			boolean part2 = false;
			String line = "";

			LinkedList queue = new LinkedList();
			Stack stack = new Stack();
			HashSet set = new HashSet();
			
			Scanner in2 = new Scanner(System.in);
			
			
			Hashtable<Long, Integer> trail = new Hashtable<Long, Integer>();
			
			ArrayList <String>lines = new ArrayList<String>();
			
			
			int LIMIT = 20000;
			boolean table[][] = new boolean[LIMIT][LIMIT];
			
			
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
			
			
			String output = "";
			
			while(intCode.isHalted() == false) {
				long temp = intCode.runProg();
				
				if(temp < 256) {
					char nextChar = (char)temp;
				
					output += nextChar;
					//sopl("Answer:");
					sop(nextChar);
				}
				
				if(output.toLowerCase().endsWith("command?")) {
					String input = in2.nextLine();
					System.out.println("Got: " + input);
					
					for(int i=0; i<input.length(); i++) {
						intCode.inputQueue.add((long)input.charAt(i));
						sop((long)input.charAt(i) + ",");
					}

					intCode.inputQueue.add((long)'\n');
				}
			}
			
			/*
			 * 
A loud, robotic voice says "Analysis complete! You may proceed." and you enter the cockpit.
Santa notices your small droid, looks puzzled for a moment, realizes what has happened, and radios your ship directly.
"Oh, hello! You should be able to get in by typing 25166400 on the keypad at the main airlock."
*/
			
			in.close();
			in2.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
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
