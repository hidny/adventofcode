package probs2020;
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

public class prob1b {

	//day1 part 2
	//6:56.90
	
	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2020/prob2020in1.txt"));
			 //in = new Scanner(new File("in2020/prob2020in1.txt.test"));
			int numTimes = 0;
			 
			long count = 0;
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
			
			ArrayList ints = new ArrayList<Integer>();
			
			OUT:
			for(int i=0; i<lines.size(); i++) {
				long temp1 = Integer.parseInt(lines.get(i));
				for(int j=i+1; j<lines.size(); j++) {
					long temp2 = Integer.parseInt(lines.get(j));
					for(int k=j+1; k<lines.size(); k++) {
						long temp3 = Integer.parseInt(lines.get(k));

						if(temp1 + temp2 +temp3  == 2020) {
							count = temp1 * temp2 * temp3;
							sopl(temp1);
							sopl(temp2);
							sopl(temp3);
							sopl("---");
						}
						
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
