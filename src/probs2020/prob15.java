package probs2020;
import java.io.File;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

import number.IsNumber;
import utils.Mapping;
import utils.Sort;

public class prob15 {

	
	public static void main(String[] args) {
		Scanner in;
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
			
			
			ArrayList<Integer> ints = new ArrayList<Integer>();
			
			for(int i=0; i<lines.size(); i++) {
				
				//numbers.add(Integer.parseInt(lines.get(i)));
				//count+=temp;
				
			}
			
			
			String line = "16,11,15,0,1,7";
			String lineCopy = String.copyValueOf(line.toCharArray());

			String token[] = line.split(",");
			for(int j=0; j<token.length; j++) {
				ints.add(pint(token[j]));
			}
			
			
			for(int i=ints.size(); i<2020; i++) {
				
				if(i!= ints.size()) {
					sopl("ah");
					exit(1);
				}
				
				int lastOne = ints.get(i-1);
				
				boolean foundLastOne = false;
				for(int j=i-2; j>=0; j--) {
					if(ints.get(j) == lastOne) {
						foundLastOne = true;
						
						sopl("Turn " + (i+1) +":" + (i-j-1));
						ints.add(i-j-1);
						break;
					}
				}
				
				if(foundLastOne == false) {
					sopl("Turn " + (i+1) +":" + 0);
					ints.add(0);
				}
				
			}
			
			sopl("Answer: " + ints.get(2019));
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
