package probs2023;
import java.io.File;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

import number.IsNumber;
import utils.Mapping;
import utils.Sort;

public class prob12 {

	//day1 part 1
	//2:38.01
	
	public static void main(String[] args) {
		Scanner in;
		try {
			//in = new Scanner(new File("in2023/prob2023in12.txt"));
			in = new Scanner(new File("in2023/prob2023in12.txt"));
			int numTimes = 0;
			 
			int count = 0;
			boolean part2 = false;
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
			
			while(in.hasNextLine()) {
				line = in.nextLine();
				lines.add(line);
				
			}

			int most = 0;
			int most2 = 0;
			int most3 = 0;
			long cur = 0;
			ArrayList ints = new ArrayList<Integer>();
			for(int i=0; i<lines.size(); i++) {
				
				
				line = lines.get(i);
				
				String puzzle = line.split(" ")[0];
				String tokens[] = line.split(" ")[1].split(",");
				
				int sequence[] = new int[tokens.length];
				
				for(int j=0; j<sequence.length; j++) {
					sequence[j] = pint(tokens[j]);
				}
				
				cur += f(puzzle, sequence);
				sopl(puzzle + ": " + f(puzzle, sequence));
			}


			sopl("Answer: " + cur);
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	
	public static long f(String line, int sequence[]) {
		
		
		return f(line, sequence, 0);
	}
	
	public static long f(String line, int sequence[], int curIndex) {
		
		
		long ret = 0L;
		if(curIndex < line.length()) {
			
			if(line.charAt(curIndex) == '?') {
				String line2 = line.substring(0, curIndex) + "." + line.substring(curIndex + 1, line.length());
				ret += f(line2, sequence, curIndex + 1);
				
				line2 = line.substring(0, curIndex) + "#" + line.substring(curIndex + 1, line.length());
				ret += f(line2, sequence, curIndex + 1);
				
			} else {
				ret += f(line, sequence, curIndex + 1);
			}
			//43387
		} else {
			
			//sopl(line);
			int curSequence[] = new int[sequence.length];
			
			int curNumRow = 0;
			boolean lastWasOn = false;
			int indexToUse = 0;
			
			boolean stillgood = true;
			
			for(int j=0; j<line.length(); j++) {
				
				if(lastWasOn == false && line.charAt(j) == '#') {
					lastWasOn = true;
					curNumRow = 1;
				} else if(lastWasOn) {
					
					if(line.charAt(j) == '#') {
						curNumRow++;
					} else {
						
						if(indexToUse < curSequence.length) {
							curSequence[indexToUse] = curNumRow;
							lastWasOn = false;
							curNumRow = 0;
							
							if(curSequence[indexToUse] != sequence[indexToUse]) {

								stillgood = false;
							}
							indexToUse++;
							
						} else {
							stillgood = false;
						}
					}
				}
			}
			
			if(lastWasOn == true) {
				if(indexToUse < curSequence.length) {
					curSequence[indexToUse] = curNumRow;
					lastWasOn = false;
					curNumRow = 0;
					if(curSequence[indexToUse] != sequence[indexToUse]) {
	
						stillgood = false;
					}
					indexToUse++;
				} else {
					stillgood = false;
				}
			}
			
			if(indexToUse == sequence.length && stillgood) {
				ret++;
			}
			//216
			
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
			sop("Error: (" + s + ") is not a number");
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
