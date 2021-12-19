package probs2021;
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

public class prob17b {

	
	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2021/prob2021in17.txt"));
			 in = new Scanner(new File("in2021/prob2021in17.txt"));
			int numTimes = 0;
			 
			int count = 0;
			boolean part2 = false;

			LinkedList queue = new LinkedList();
			Stack stack = new Stack();
			HashSet set = new HashSet();
			
			
			Hashtable<Long, Integer> trail = new Hashtable<Long, Integer>();
			
			ArrayList <String>lines = new ArrayList<String>();
			
			
			//int LIMIT = 20000;
			//boolean table[][] = new boolean[LIMIT][LIMIT];
			
			
			//dir: 0 up
			//1 right
			//2 down
			//3 left
			
			while(in.hasNextLine()) {
				String line = in.nextLine();
				lines.add(line);
				
				
			}
			
			
			
			check_sim(7, 2, 277, 318, -92, -53);
			
			ArrayList ints = new ArrayList<Integer>();
			
			String line ="target area: x=277..318, y=-92..-53";
			
			long answer = 0;
			
			
			for(int y=0; y < 1000000; y++) {
				
				for(int x = 1; x < 400; x++) {
					//long temp = check_sim(x, y, 20, 30, -10, -5);
					long temp = check_sim(x, y, 277, 318, -92, -53);
					if(temp >= 0) {

						sopl(x + ", " + y);
						answer++;
						sopl(answer);
					}
					
					
					if(y!=0) {
						//temp = check_sim(x, -y, 20, 30, -10, -5);
						temp = check_sim(x, -y, 277, 318, -92, -53);
						if(temp >= 0) {
							
							//sopl(x + ", " + (0-y));
							answer++;
							sopl(answer);
							//2702
						}
					}
				}
				
			}
			
			
			sopl("Answer: " + answer);
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	//91 wrong.
	
	public static long check_sim(long velX, long velY, long minX, long maxX, long minY, long maxY) {
		
		long t = 0;
		
		long x=0;
		long y=0;
		//ArrayList<String> pos = new ArrayList<String>();
		
		while(x <= maxX && y>=minY) {
			
			x += velX;
			y += velY;
			
			//pos.add(x + "," + y);
			//sopl(x + "," + y);
			
			if(velX > 0) {
				velX -= 1;
			} else if(velX < 0) {
				velX += 1;
			}
			
			velY -= 1;
			
			
			if(x>=minX && x <= maxX && y>=minY && y<= maxY) {
				return 1L;
			}
		}
		
		
		
		return -1L;
		
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

	public static long plong(String s) {
		if (IsNumber.isLong(s)) {
			return Long.parseLong(s);
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
