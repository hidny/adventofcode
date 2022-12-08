package probs2022;
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

public class prob5 {

	
	public static void main(String[] args) {
		Scanner in;
		try {
			in = new Scanner(new File("in2022/prob2022in5.txt"));
			//in = new Scanner(new File("in2022/prob2022in6.txt"));
			int numTimes = 0;
			 
			int count = 0;
			boolean part2 = false;
			String line = "";

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

			boolean stillStack = true;
			ArrayList ints = new ArrayList<Integer>();
			
			LinkedList queue[] = new LinkedList[9];
			Stack stack[] = new Stack[9];
			for(int i=0; i<queue.length; i++) {
				queue[i] = new LinkedList();
				stack[i] = new Stack();
			}
			

			
			for(int i=0; i<lines.size(); i++) {
				
				
				line = lines.get(i);
				String token[] = line.split(" ");
				
				if(stillStack && ! token[1].startsWith("1")) {
					System.out.println("Stack");
					
					int index = 0;
					for(int j=0; j<line.length(); j+=4) {
						
						if(line.charAt(j) == '[') {
							queue[index].add(line.charAt(j + 1) + "");
						} else {
							//pass
						}
						index++;
					}
					
					
				} else if(stillStack){
					stillStack = false;
					
					for(int j=0; j<stack.length; j++) {
						while(! queue[j].isEmpty()) {
							stack[j].add(queue[j].getLast());
							sopl(queue[j].getLast());
							queue[j].removeLast();
							
							
						}
						sopl();
						sopl();
					}
					
				}
				
				if(line.startsWith("move")) {
					int num = pint(token[1]);
					
					int loc1 = pint(token[3]) - 1;
					int loc2 = pint(token[5]) - 1;
					
					int cur = num;
					while(!stack[loc1].isEmpty() && cur > 0) {
						stack[loc2].add(stack[loc1].pop());
						cur--;
					}
					
					if(cur > 0) {
						sopl("What");
					}
				}
				
				//PQPQDRLNN
				
			}
			

			sopl("Answer: ");
			for(int j=0; j<stack.length; j++) {
				//For test:
				if(stack[j].isEmpty() == false) {
					sop(stack[j].pop());
				}
			}
			sopl();
			
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
