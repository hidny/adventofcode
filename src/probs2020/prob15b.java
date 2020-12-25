package probs2020;
import java.io.File;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;
import java.util.concurrent.TimeUnit;

import number.IsNumber;
import utils.Mapping;
import utils.Sort;

public class prob15b {

	
	public static void main(String[] args) {
		Scanner in;
		
		long start = System.currentTimeMillis();
		
		
		try {
			
			 in = new Scanner(new File("in2020/prob2020in1.txt"));
			 //in = new Scanner(new File("in2020/prob2020in1.txt.test"));
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
			
			
			//ArrayList<Integer> ints = new ArrayList<Integer>();
			
			Hashtable<Long, Integer> ints = new Hashtable<Long, Integer>();
			
			for(int i=0; i<lines.size(); i++) {
				
				//numbers.add(Integer.parseInt(lines.get(i)));
				//count+=temp;
				
			}
			
			
			String line = "16,11,15,0,1,7";
			//String line = "0,3,6";
			String lineCopy = String.copyValueOf(line.toCharArray());

			
			long lastOne = -1;
			String token[] = line.split(",");
			for(int j=0; j<token.length; j++) {
				ints.put(lastOne, j);
				lastOne = plong(token[j]);
			}
			
			
			//size weird...
			
			//30000000
			for(int i=ints.size(); i<30000000; i++) {
				
				long added = -1;
				
				
				boolean foundLastOne = false;
				int lastIndex = -1;
				if(ints.containsKey(lastOne)) {
					lastIndex = ints.get(lastOne);
					
					ints.remove(lastOne);
					
				}


				ints.put(lastOne, i);
				
				if(lastIndex >= 0) {
					added = i - lastIndex;
				} else {

					//sopl("not found");
					added = 0;
				}
				
				//sopl("Turn " + (i+1) +":" + added);
				
				
				lastOne = added;
				
			}
			
			sopl("Answer: " + lastOne);
			in.close();
			
			/*
			long count = 0;
			Set<String> keys = numbers.keySet();
	        for(String key: keys){
	        	
	        	String value = numbers.get(key);
	        	count += StringToNum(value);
	        	
	            System.out.println(key + "-> " + value + "  or " + StringToNum(value));
	           
	        }
			 */
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
		
		long end = System.currentTimeMillis();

		NumberFormat formatter = new DecimalFormat("#0.00000");
		System.out.print("Execution time is " + formatter.format((end - start) / 1000d) + " seconds");
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
	

	public static long plong(String s) {
		if (IsNumber.isLong(s)) {
			return Long.parseLong(s);
		} else {
			sop("Error: (" + s + " is not a number");
			return -1;
		}
	}
	

}
