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

public class prob19part2 {

	
	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2019/prob2019in19.txt"));
			 //in = new Scanner(new File("in2019/prob2019in19.txt.test"));
			int numTimes = 0;
			 
			int count = 0;
			boolean part2 = true;
			String line = "";

			
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
			
			int FACTOR = 10000;
			
			
			long answer = -1;
			long dist=0;
			
			
			long distRangeMax = -1;
			
			int minj=0;
			int maxj=0;
			
			
			boolean hasBeam = false;
			LinkedList<Integer> queue = new LinkedList<Integer>();
			
			int MAX_WAIT_FOR_BEAM = 100;
			
			
			int BLOCK_SIZE = 100;
			
			int oldMax = -1;
			
			for(int i=0; true; i++) {
				sopl(i + ": minj = " + minj + ", maxj = " + maxj + ", oldMax = " + oldMax);
				
				for(int j=minj; j<minj + MAX_WAIT_FOR_BEAM; j++) {
					if(runProg(j, i, line) == 1) {
						minj = j;
						hasBeam = true;
						break;
					}
				}
				if(hasBeam) {
					for(int j=Math.max(minj, maxj); true; j++) {
						if(runProg(j, i, line) == 0) {
							maxj = j;
							break;
						}
					}
				}
				
				queue.add(maxj);
				
				while(queue.size() > BLOCK_SIZE) {
					queue.remove();
				}
				
				if(queue.size() ==BLOCK_SIZE) {
					oldMax = queue.getFirst();
				}
				
				if(minj + BLOCK_SIZE <= oldMax) {
					
					//619948 too low...
					int answerY = i-BLOCK_SIZE + 1;
					int answerX = minj;
					
					answer = FACTOR* answerX + answerY;
					break;
				}
				
			}
			
			sopl("Answer: " + answer);
			
			
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	
	
	public static long runProg(long x, long y, String line) {
		
		LinkedList<Long> inputQueue = new LinkedList<Long>();
		inputQueue.add(x);
		inputQueue.add(y);
		String cmdsCopy[] = line.split(",");
		
		IntCode intCode = new IntCode(cmdsCopy, inputQueue);
		intCode.setPauseOnOutput();
		long ret = intCode.runProg();
		
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
