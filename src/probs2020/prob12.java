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

public class prob12 {

	
	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2020/prob2020in12.txt"));
			 //in = new Scanner(new File("in2020/prob2020in12.txt.test"));
			int numTimes = 0;
			 
			int count = 0;
			boolean part2 = false;

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
				String line = in.nextLine();
				lines.add(line);
				
				
			}
			
			
			ArrayList ints = new ArrayList<Integer>();

			
			int wx = 10;
			int wy = -1;
			
			int x = 0;
			int y = 0;
			for(int i=0; i<lines.size(); i++) {
				
				//int temp = Integer.parseInt(lines.get(i));
				//count+=temp;
				
				String line = lines.get(i);
				
				String cmd = line.substring(0, 1);
				
				int num = pint(line.substring(1));
				
				
				if(cmd.startsWith("F")) {
					x += num * wx;
					y += num * wy;
					
				} else if(cmd.startsWith("N")) {
					wy-= num;
				} else if(cmd.startsWith("S")) {
					wy+= num;
				} else if(cmd.startsWith("E")) {
					wx+= num;
				} else if(cmd.startsWith("W")) {
					wx-= num;
					
				} else if(cmd.startsWith("R")) {
					if(num == 90) {

						int temp = wx;
						wx = -wy;
						wy = temp;

					} else if(num == 270) {
						int temp = wx;
						wx = wy;
						wy = -temp;
						
					} else if(num == 180) {
						
						wx = -wx;
						wy = -wy;
					} else {
						sopl("ahh");
					}
					
				} else if(cmd.startsWith("L")) {
					if(num == 90) {

						int temp = wx;
						wx = wy;
						wy = -temp;
						
					} else if(num == 270) {
						int temp = wx;
						wx = -wy;
						wy = temp;
						

					} else if(num == 180) {
						
						wx = -wx;
						wy = -wy;
					} else {
						sopl("ahh");
					}
				}
				
				
				sopl(x + ", "+ y);

				sopl("w: " + wx + ", "+ wy);
				
			}
			
			//74261
			
			//1637
			
			sopl("Answer: " + (Math.abs(x) + Math.abs(y)));
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
