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

public class prob19 {

	
	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2019/prob2019in19.txt"));
			 //in = new Scanner(new File("in2019/prob2019in19.txt.test"));
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
			
			
			
			//pause on output, so I can process it...
			
			
			
			for(long i=0; i<50; i++) {
				for(long j=0; j<50; j++) {
					LinkedList<Long> inputQueue = new LinkedList<Long>();
					inputQueue.add(i);
					inputQueue.add(j);
					String cmdsCopy[] = line.split(",");
					
					IntCode intCode = new IntCode(cmdsCopy, inputQueue);
					intCode.setPauseOnOutput();
					long ret = intCode.runProg();
					
					if(ret == 1) {
						count++;
					}
				}
			}
			
			sopl("Answer: " + count);
			
			
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
