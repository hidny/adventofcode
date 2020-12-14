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

public class prob14b {

	
	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2020/prob2020in14.txt"));
			 //in = new Scanner(new File("in2020/prob2020in14.txt.test"));
			int numTimes = 0;
			 
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
			
			
			
			ArrayList ints = new ArrayList<Integer>();
			
			String mask ="";
			
			Hashtable<String, Long> numbers = new Hashtable<String, Long>();
			
			
			
			for(int i=0; i<lines.size(); i++) {
				
				//int temp = Integer.parseInt(lines.get(i));
				//count+=temp;
				
				String line = lines.get(i);
				String lineCopy = String.copyValueOf(line.toCharArray());

				if(line.startsWith("mask")) {
					
					mask = line.split(" ")[2];
				} else {
					String address = (line.split("\\[")[1]).split("\\]")[0];
					
					String addressCode = numToString(plong(address));
					
					long code = plong(line.split(" ")[2]);
					
					String codes = numToString(code);
				
					char addressA[] = addressCode.toCharArray();
					
					for(int j=0; j<36; j++) {
						if(mask.charAt(j) == 'X') {
							addressA[j] = 'X';
						} else if(mask.charAt(j) == '1'){
							addressA[j] = '1';
						} else if(mask.charAt(j) == '0') {
							addressA[j] = addressCode.charAt(j);
							
						}
					}
					
					addressCode = String.copyValueOf(addressA);
					
					//sopl("Address: " + addressCode);
					
					//numbers.put(address, current);

					setupHash(numbers, addressCode, code);
				}
				
			}
			
			long count = 0;
			Set<String> keys = numbers.keySet();
	        for(String key: keys){
	        	
	        	long value = numbers.get(key);
	        	count += value;
	        	
	            //System.out.println(key + "-> " + value + "  or " + StringWithXToNum(value));
	           
	        }
			
			
			sopl("Answer: " + count);
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	
	
	public static void setupHash(Hashtable<String, Long> numbers, String addressCode, long code) {
		
		ArrayList<String> totAddresses = new ArrayList<String>();
		
		String XsZeros = "";
		for(int i=0; i<36; i++) {
			if(addressCode.charAt(i) == '1') {
				XsZeros += 1;
			} else {
				XsZeros += 0;
			}
		}
		
		
		totAddresses.add(XsZeros);
		
		for(int i=0; i<36; i++) {
			
			if(addressCode.charAt(i) == 'X') {
				
				int initSize = totAddresses.size();
				for(int j=0; j<initSize; j++) {
					char line[] = totAddresses.get(j).toCharArray();
					
					line[i] = '1';
					totAddresses.add(String.copyValueOf(line));
					
				}
				
			} else {
				//Do nothing...
			}
		}
		
		int initSize = totAddresses.size();
		for(int j=0; j<initSize; j++) {
			if(numbers.containsKey(totAddresses.get(j))){
				
				numbers.remove(totAddresses.get(j));
			}
				
			numbers.put(totAddresses.get(j), code);
			
		}
		
		
	}
	
	//14919778021
	
	public static String numToString(long num) {
		String ret = "";
		
		long cur = num;
		
		for(int i=0; i<36; i++) {
			if(num % 2 == 1) {
				ret = "1" + ret;
			} else {
				ret = "0" + ret;
			}
			num /= 2;
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
