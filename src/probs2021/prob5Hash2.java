package probs2021;
import java.io.File;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

import number.IsNumber;
import utils.HashTableTally;
import utils.Mapping;
import utils.Sort;

public class prob5Hash2 {

	
	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2021/prob2021in5.txt"));
			 //in = new Scanner(new File("in2021/prob2021in6.txt"));
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
			
			utils.HashTableTally hashTally = new HashTableTally();
			
			for(int i=0; i<lines.size(); i++) {
				
				//int temp = Integer.parseInt(lines.get(i));
				//count+=temp;
				
				String line = lines.get(i);
				String token[] = line.split(" ");
				
				String point1[] = token[0].split(",");
				String point2[] = token[2].split(",");
				
				
				int i1 = pint(point1[0]);
				int j1 = pint(point1[1]);
				
				int i2 = pint(point2[0]);
				int j2 = pint(point2[1]);
				
				
				if(i1 == i2) {
					
					for(int j=Math.min(j1, j2); j<=Math.max(j1, j2); j++) {
						//table[i1][j]++;
						hashTally.add1ToHash(i1 + "," + j);
					}
					
				} else if(j1 == j2) {

					for(int i3=Math.min(i1, i2); i3<=Math.max(i1, i2); i3++) {
						//table[i3][j1]++;
						//addToHash(i1 + "," + j);
						hashTally.add1ToHash(i3 + "," + j1);
					}
				} else {
					
					int c = 0;
					int start = -1;
					int mult = 1;
					
					sopl(line);
					
					if((i1 < i2 && j1 < j2)
							|| (i1>i2 && j1 > j2) ){
						mult = 1;
						

						if(i1 <i2) {
							start = j1;
						} else {
							start =j2;
						}
					} else {
						mult = -1;

						if(i1 <i2) {
							start = j1;
						} else {
							start =j2;
						}
						
					}
					
					for(int i3=Math.min(i1, i2); i3<=Math.max(i1, i2); i3++) {
						
						//sopl(i3 + ", " + (start + mult*c));
						//table[i3][start + mult*c]++;
						//addToHash(hashTable, i3 + "," + (start + mult*c));
						hashTally.add1ToHash(i3 + "," + (start + mult*c));
						c++;
					}
				}
				
			}
			
			
			Enumeration<String> e = hashTally.getKeysInList();
			
			while(e.hasMoreElements() ) {
				String curKey = e.nextElement();
				if(hashTally.get(curKey) > 1) {
					count++;
				}
			}
			
			
			
			
			sopl("Answer: " + count);
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}

	public static void addToHash(Hashtable t, String key) {
		if(t.containsKey(key)) {
			int val = (Integer)t.get(key);
			t.remove(key);
			t.put(key, val + 1);
		} else {
			t.put(key, 1);
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
