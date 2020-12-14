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

public class prob9 {

	
	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2020/prob2020in9.txt"));
			 //in = new Scanner(new File("in2020/prob2020in1.txt.test"));
			int numTimes = 0;
			 
			int answer = 0;
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
			
			
			ArrayList<Integer> ints = new ArrayList<Integer>();
			
			
			for(int i=0; i<lines.size(); i++) {
				
				int temp = Integer.parseInt(lines.get(i));
				ints.add(temp);
				
				boolean isTheOne = true;
				if(ints.size() > 25) {
					for(int j=ints.size() - 1 - 25; j<ints.size() - 1; j++) {
						for(int k=j+1; k<ints.size() - 1; k++) {
							if(ints.get(j) + ints.get(k) == temp) {
								isTheOne =false;
								break;
							}
						}
					}
				} else {
					isTheOne = false;
				}
				
				sopl(ints.size() + " " + temp);
				if(isTheOne) {
					answer = temp;
					sopl(temp);
					break;
				}
				
				
			}
			
			
			
			ArrayList<Long>longs = new ArrayList<Long>();
			
			for(int i=0; i<lines.size(); i++) {
	
				long temp = Long.parseLong(lines.get(i));
				longs.add(temp);
				
				
			}
			
			for(int i=0; i<lines.size(); i++) {
				int curSum = 0;
				for(int j=i; curSum < answer && j<lines.size(); j++) {
					curSum +=longs.get(j);
					if(curSum == answer && j> i) {
						sopl("answer part 2:");
						
						long min=longs.get(i);
						long max=longs.get(i);
						
						for(int k=i; k<=j; k++) {
							if(longs.get(k) > max) {
								max = longs.get(k);
							} else if(longs.get(k) < min) {
								min = longs.get(k);
							}
						}
						sopl(min + max);
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
