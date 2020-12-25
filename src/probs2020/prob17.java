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

public class prob17 {

	
	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2020/prob2020in17.txt"));
			 //in = new Scanner(new File("in2020/prob2020in17test.txt"));
			int numTimes = 0;
			 
			int count = 0;
			boolean part2 = false;

			LinkedList queue = new LinkedList();
			Stack stack = new Stack();
			HashSet set = new HashSet();
			
			
			Hashtable<String, Integer> table = new Hashtable<String, Integer>();
			
			ArrayList <String>lines = new ArrayList<String>();
			
			
			
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
				
				
				String line = lines.get(i);
				String lineCopy = String.copyValueOf(line.toCharArray());

				sopl(line);
				for(int j=0; j<line.length(); j++) {
					if(line.charAt(j) =='#') {
						
						table.put(i +","+j+","+0, 1);
						
						//sopl(i+","+j);
					}
				}
			
			}
			
			
			
			for(int it=0; it<6; it++) {
				Hashtable<String, Integer> used = new Hashtable<String, Integer>();
				Hashtable<String, Integer> table2 = new Hashtable<String, Integer>();
				
				//sopl("it: " + it);
				
				sopl("Table size: " + table.size());
				Set<String> keys = table.keySet();
				 for(String key: keys){
					 
					 //sopl(key);
			        	
					 
					 String token[] = key.split(",");
					 int i = pint(token[0]);
					 int j = pint(token[1]);
					 int k = pint(token[2]);
					 
					 //sopl(i);
					 //sopl(j);
					 //sopl(k);
					 //sopl();
					 
					 for(int i2=i-1; i2<=i+1; i2++) {
						 for(int j2=j-1; j2<=j+1; j2++) {
							 for(int k2=k-1; k2<=k+1; k2++) {
								 
								 
								 if(used.containsKey(i2 +"," + j2 + "," + k2) == false) {

									 int numNei = getNeighbour(table, i2, j2, k2);
									 
									 if(table.containsKey(i2 +"," + j2 + "," + k2) && (numNei == 2 || numNei == 3)) {
										// sopl("PUT");
										 table2.put(i2 +"," + j2 + "," + k2, 1);
									 } else if(numNei == 3) {
										 //sopl("PUT");
										 table2.put(i2 +"," + j2 + "," + k2, 1);
										 
									 }
									 
									 used.put(i2 +"," + j2 + "," + k2, 1);
								 }
							 }
						 }
					 }
					 
					 
			        	
			            //System.out.println(key + "-> " + value + "  or " + StringWithXToNum(value));
			           
			     }
				 table = table2;
			}
			
			
			
			
			sopl("Answer: " + table.size());
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	
	public static int getNeighbour(Hashtable<String, Integer> table, int i, int j, int k) {
		
		int ret = 0;
		for(int i2=i-1; i2<=i+1; i2++) {
			 for(int j2=j-1; j2<=j+1; j2++) {
				 for(int k2=k-1; k2<=k+1; k2++) {
					
					 if(i ==i2 && j == j2 && k == k2) {
						 continue;
					 }
					 
					 if(table.containsKey(i2 +"," + j2 + "," + k2)) {
						 ret++;
					 }
				 }
			 }
		 }
		
		//sopl("ret" + ret);
		
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
