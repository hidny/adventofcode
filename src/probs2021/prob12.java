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

public class prob12 {

	
	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2021/prob2021in12.txt"));
			// in = new Scanner(new File("in2021/prob2021in13.txt"));
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
			
			
			ArrayList ints = new ArrayList<Integer>();
			
			
			for(int i=0; i<lines.size(); i++) {
				
				//int temp = Integer.parseInt(lines.get(i));
				//count+=temp;
				
				String line = lines.get(i);
				String token[] = line.split(" ");
				
				//sopl(line);
				/*int amount = pint(token[1]);
				
				if(token[0].equals("forward")) {
					h+= amount;
					d+= aim*amount;
				} else if(token[0].equals("down")) {
					//d+=amount;
					aim += amount;
					
				} else if(token[0].equals("up")) {
					//d-=amount;
					aim -= amount;
				} else {
					sopl("doh");
				}*/

				
			}
			
			
			ArrayList<String> paths = new ArrayList<String>();
			
			
			
			String curLocation = "start";
			
			String curPath = curLocation;
			
			
			paths = getPaths(lines, curLocation, curPath, 100);
			
			sopl("Answer 2: " + paths.size());
			in.close();
			
			for(int i=0; i< paths.size(); i++) {
				//sopl(paths.get(i));
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	
	
	public static HashSet <String> locationsTaken = new HashSet<String>();
	
	public static ArrayList<String> getPaths(ArrayList<String> lines, String curLocation, String curPath, int depth) {
		
		ArrayList<String> ret = new ArrayList<String>();
		locationsTaken.add(curLocation);
		for(int i=0; i<lines.size(); i++) {
			
			String tokens[] = lines.get(i).split("-");
			//sopl(tokens[0] + "  " + tokens[1]);
			
			String dest = "";
			if(tokens[0].equals(curLocation)) {
				dest = tokens[1];
			} else if(tokens[1].equals(curLocation)) {
				dest = tokens[0];
			} else {
				continue;
			}
			
			
			if(dest.equals(dest.toLowerCase())) {
				
				if(locationsTaken.contains(dest)) {
					continue;
				} else {
					locationsTaken.add(dest);
				}
			}
			
			
			
			if(dest.equals("end")) {
				ret.add(curPath + "end");
				sopl("Answer: " + ret.get(ret.size() - 1));
			} else {
				
				//curPath += curLocation + ",";
				
				ret.addAll(getPaths( lines, dest, curPath + "," + dest, depth - 1));

			}
			
			if(locationsTaken.contains(dest)) {
				locationsTaken.remove(dest);
			}
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
