package probs2018;

import java.io.File;

import number.IsNumber;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

import utils.Mapping;

public class prob12part2 {

	static int LENGTH = 1000;
	
	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2018/prob2018in12.txt"));
			
			int count = 0;
			boolean part2 = false;
			String line = "";

			LinkedList queue = new LinkedList();
			Stack stack = new Stack();
			HashSet set = new HashSet();
			
			Mapping dict = new Mapping();
			ArrayList <String>lines = new ArrayList<String>();
			
			
			int LIMIT = 20000;
			
			Hashtable<Long, Integer> trail = new Hashtable<Long, Integer>();
			
			//dir: 0 up
			//1 right
			//2 down
			//3 left
			
			while(in.hasNextLine()) {
				line = in.nextLine();
				lines.add(line);
				
				
			}
			//16411
			
			int NUM_GEN = 300;
			
			line = lines.get(0).split(" ")[2];
			
			boolean prev[]  = new boolean[LENGTH];
			boolean current[] = new boolean[LENGTH];
			
			prev = convertBig(line);
			
			for(int gen = 1; gen<=NUM_GEN; gen++) {
				
				current = new boolean[prev.length + 4];
				
				for(int i=2; i<lines.size(); i++) {
					line = lines.get(i);
					
					boolean check[] =convert(line.split(" ")[0]);
					boolean result;
					if(line.split(" ")[2].equals("#")) {
						result = true;
					} else {
						result = false;
					}
					
					for(int j=2; j<prev.length - 2; j++) {
						boolean nope = false;
						
						for(int k=0; k<5; k++) {
							if(prev[j - 2 + k] != check[k]) {
								nope = true;
								break;
							}
						}

						if(nope ==false) {
							current[j+2] = result;
						}
					}
					
					
				}
				for(int i=0; i<current.length; i++) {
					if(current[i]) {
						System.out.print("#");
					} else {

						System.out.print(",");
					}
				}
				
				//It's linear after 300 gens!
				//Found formula:
				//(NUM_GEN - GEN300)* (55 new points gen after gen 300)  + (number of points at 300)
				//(50000000000-300)*(16411-16356) + 16411

				int prevCount = count;
				count = 0;
				//sop("");
				//sop(table[0].length);
				for(int i=0; i<current.length; i++) {
					if(current[i]) {
						count += i - current.length/2;
					}
				}
				//sop(count-prevCount);
				sop(count);
				
				prev= current;
				
				
			}
			count = 0;
			
			for(int i=0; i<current.length; i++) {
				if(current[i]) {
					count += i - current.length/2;
				}
			}
			
			sopl("Answer: " + count);
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	
	public static boolean[] convertBig(String arg) {
		//sop(arg);
		boolean ret[] = new boolean[LENGTH];
		for(int i=0; i<arg.length(); i++) {
			if(arg.charAt(i) == '#') {
				ret[LENGTH/2 + i] =true;
			} else {
				ret[LENGTH/2 + i] = false;
			}
		}
		return ret;
	}
	public static boolean[] convert(String arg) {
		//sop(arg);
		boolean ret[] = new boolean[arg.length()];
		for(int i=0; i<arg.length(); i++) {
			if(arg.charAt(i) == '#') {
				ret[i] =true;
			} else {
				ret[i] = false;
			}
		}
		return ret;
	}
	
	public static void sop(Object a) {
		System.out.println(a.toString());
	}
	public static void sopl(Object a) {
		System.out.println(a.toString());
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
