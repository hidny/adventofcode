package probs2021;
import java.io.File;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
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
			in = new Scanner(new File("in2021/prob2021in14.txt"));
			 //in = new Scanner(new File("in2021/prob2021in15.txt"));
			
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
			
			ArrayList<String> formulas = new ArrayList<String>();
			ArrayList<String> lhs = new ArrayList<String>();
			ArrayList<String> rhs = new ArrayList<String>();
			
			String start = lines.get(0);
			
			for(int i=0; i<lines.size(); i++) {
				
				//int temp = Integer.parseInt(lines.get(i));
				//count+=temp;
				
				String line = lines.get(i);
				
				if(lines.get(i).contains("->")) {
					formulas.add(lines.get(i));
					lhs.add(lines.get(i).split("-")[0].trim());
					rhs.add(lines.get(i).split(">")[1].trim());
				}
				
			}
			
			HashMap<String, Long> pairs = new HashMap<String, Long>();

			String cur = start;
			
				for(int j=0; j<cur.length() - 1; j++) {

					String key = cur.charAt(j) + "" + cur.charAt(j+1);
					
					for(int i=0; i<lhs.size(); i++) {
						
						if(lhs.get(i).equals(key)) {
							if(pairs.containsKey(key)) {
								long value = pairs.get(key);
								pairs.remove(key);
								pairs.put(key, value+1L);
							} else {
								pairs.put(key, 1L);
							}
							break;
						}
					}
					
				}
			

			for(int step=0; step<40; step++) {

				
				HashMap<String, Long> pairsNext = new HashMap<String, Long>();
				
				Iterator hmIterator = pairs.keySet().iterator();

				 while (hmIterator.hasNext()) {
			            String pair = (String)hmIterator.next();
			            
			            
			            boolean pairAdded = false;
			            for(int k=0; k<lhs.size(); k++) {
							if(pair.equals(lhs.get(k))) {
								pairAdded = true;
								String key = pair.charAt(0) + "" + rhs.get(k);
								if(pairsNext.containsKey(key)) {
									long value = pairsNext.get(key);
									pairsNext.remove(key);
									pairsNext.put(key, pairs.get(pair) + value);
								} else {
									pairsNext.put(key, pairs.get(pair));
								}
								
								
								String key2 = rhs.get(k) + "" + pair.charAt(1);
								if(pairsNext.containsKey(key2)) {
									long value = pairsNext.get(key2);
									pairsNext.remove(key2);
									pairsNext.put(key2, pairs.get(pair) + value);
								} else {
									pairsNext.put(key2, pairs.get(pair));
								}
								
								break;
							}
						}
			            
			            if(pairAdded == false) {
			            	String key = pair;
							if(pairsNext.containsKey(key)) {
								long value = pairsNext.get(key);
								pairsNext.remove(key);
								pairsNext.put(key, pairs.get(pair) + value);
							} else {
								pairsNext.put(key, pairs.get(pair));
							}
			            }
			            
			            
			            
				}
				 
				pairs = pairsNext;
				
				

			}

			
			Iterator hmIterator2 = pairs.keySet().iterator();

			long array[] = new long[26];
			
			 while (hmIterator2.hasNext()) {
		            String pair = (String)hmIterator2.next();
		            
		            array[pair.charAt(0) - 'A'] += pairs.get(pair);
		            array[pair.charAt(1) - 'A'] += pairs.get(pair);
			 }
			 
			 for(int i=0; i<array.length; i++) {
				 array[i] = (array[i] + 1) /2;
				 if(array[i] > 0) {
					 sopl((char)('A' + i) + ": " + array[i]);
				 }
			 }
			
			long max = Long.MIN_VALUE;
			long min = Long.MAX_VALUE;
			
			for(int i=0; i<array.length; i++) {
				
				if(array[i] > max) {
					max = array[i];
				}
				if(array[i] < min && array[i] > 0) {
					min = array[i];
				}
			}
			
			sopl("max: " + max);
			sopl("min: " + min);
			sopl("diff here: "+ (max-min));
			//sopl(max);
			//sopl(min);
			//3815645915883
			
			sopl("Answer: " + (max - min));
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}

	
	public static void addToHash() {
		
	}
	
	
	public static ArrayList<String> function(ArrayList<String> lines, int depth, String cur) {
		ArrayList<String> ret = new ArrayList<String>();
		
		
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
