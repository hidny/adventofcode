package probs2021;
import java.io.File;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

import number.IsNumber;
import utils.Mapping;
import utils.Sort;

public class prob6 {

	
	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2021/prob2021in6.txt"));
			 //in = new Scanner(new File("in2021/prob2021in7.txt"));
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
			
			Hashtable table = new Hashtable<String, Integer>();
			Enumeration e =table.elements();
			
			
			ArrayList ints = new ArrayList<Integer>();

			String token[] = lines.get(0).split(",");
			
			BigInteger array[] = new BigInteger[9];
			for(int j=0; j<array.length; j++) {
				array[j] = BigInteger.ZERO;
			}
			
			for(int i=0; i<token.length; i++) {
				array[pint(token[i])] = array[pint(token[i])].add(BigInteger.ONE);
				sopl(array[pint(token[i])]);
			}
			
			for(int gen=0; gen<256; gen++) {
				BigInteger arrayNext[] = new BigInteger[9];
				for(int j=0; j<arrayNext.length; j++) {
					arrayNext[j] = BigInteger.ZERO;
				}
				
				for(int j=0; j<array.length; j++) {
					if(j==0) {
						arrayNext[8] = array[0];
						arrayNext[6] = arrayNext[6].add(array[0]);
					} else {
						arrayNext[j-1] = arrayNext[j-1].add(array[j]);
					}
					
				}
				array = arrayNext;
				
			}
			
			BigInteger countI = BigInteger.ZERO;
			for(int i=0; i<array.length; i++) {
				countI = countI.add(array[i]);
			}
			
			
			sopl("Answer: " + countI);
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
