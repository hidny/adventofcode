package probs2024;
import java.io.File;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

import number.IsNumber;
import utils.Mapping;
import utils.Sort;

public class prob5b {

	//day1 part 1
	//2:38.01
	
	public static void main(String[] args) {
		Scanner in;
		try {
			in = new Scanner(new File("in2024/prob2024in5.txt"));
			//in = new Scanner(new File("in2024/prob2024in0.txt"));
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
			boolean table342[][] = new boolean[LIMIT][LIMIT];
			
			
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
			long cur = 0L;
			ArrayList ints = new ArrayList<Integer>();
			
			HashSet<String> order = new HashSet<String>();
			
			for(int i=0; i<lines.size(); i++) {
				
				
				line = lines.get(i);
				
				if(line.contains("|")) {
					String tokens[] = line.split("\\|");
					order.add(tokens[0] + "," + tokens[1]);
					sopl(tokens[0] + "," + tokens[1]);
					
				} else if(line.contains(",")){
					//sopl("go");
					String tokens[] = line.split(",");
					if(order.isEmpty()) {
						sopl("empty...");
					}
					Iterator it = order.iterator();
					//while(it.hasNext()) {
					//	System.out.println(it.next());
					//}
					//exit(1);
					//if(order.contains("45,76")) {
					//	sopl("test");
					//}
					
					boolean good = true;
					for(int i2=tokens.length-1; i2>=0; i2--) {
						for(int j=i2-1; j>=0; j--) {
							//sopl(tokens[i2] + "," + tokens[j]);
							if(order.contains(tokens[i2] + "," + tokens[j])) {
								
								String tmp = tokens[i2];
								tokens[i2] = tokens[j];
								tokens[j] = tmp;
								good = false;
							}
						}
					}
					
					if(good == false) {
						cur += pint(tokens[tokens.length / 2]);
					}
					
				} else {
					sopl("??" + line);
				}
				
			}


			sopl("Answer: " + cur);
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
