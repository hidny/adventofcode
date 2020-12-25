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

public class prob23 {

	
	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2020/prob2020in23.txt"));
			 //in = new Scanner(new File("in2020/prob2020in23.txt.test"));
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
			
			

			
			//insert 3 new location
			
			//new current cup 
			

			ArrayList<Integer> nextCupIndex = new ArrayList<Integer>();
			
			
			int curIndex = -1;
			
			for(int i=0; i<lines.size(); i++) {
				
				//int temp = Integer.parseInt(lines.get(i));
				//count+=temp;
				
				String line = lines.get(i);
				String lineCopy = String.copyValueOf(line.toCharArray());

				
				int last = -1;
				for(int j=0; j<line.length(); j++) {
					
					nextCupIndex.add(-1);
				}
				
				for(int j=0; j<line.length(); j++) {
					int current = (int)(line.charAt(j) - '1');
					
					if(j  > 0) {
						nextCupIndex.set(last, current);
						sopl((last+1) + " ->" + (current+1));
					}
					last = current;
					
				}
				curIndex = line.charAt(0) - '1';
				nextCupIndex.set(last, (int)(curIndex));
				sopl(last + " ->" + (int)(curIndex));
				
				
			}
			
			int testIndex = curIndex;
			for(int j=0; j<nextCupIndex.size(); j++) {
				sop(testIndex + 1 + " ");
				testIndex = nextCupIndex.get(testIndex);
			}
			sopl();

			//3 cups rm
			//dest cup:
			//label -1... and wraps around...
			
			int minCup = 0;
			int maxCup = 0;
			
			
			//todo increase it
			for(int it=0; it<100; it++) {
				
				sopl("Iteration: " + it);

				int nextThreeCups[] = new int[3];
				int nextThreeIndex[] = new int[3];
				
				sop("Pick up ");
				int nextCupInd = curIndex;
				for(int j=0; j<nextThreeCups.length; j++) {
					nextCupInd = nextCupIndex.get(nextCupInd);
					
					nextThreeIndex[j] = nextCupInd;
					
					sop((nextCupInd+1) + " ");
				}
				sopl();
				
				int temp = nextCupIndex.get(curIndex) + 0;
				
				int nextIndex= nextCupIndex.get(nextThreeIndex[2]);
				nextCupIndex.set(curIndex, nextIndex);
				
				//this is label minus 1
				int targetLabel = curIndex;

				boolean isRemoved = false;
				
				do {
					targetLabel = targetLabel - 1;
					if(targetLabel < 0) {
						targetLabel = 8;
					}
					
					isRemoved = false;
					
					for(int j=0; j<nextThreeCups.length; j++) {
						if(nextThreeIndex[j] == targetLabel) {
							isRemoved = true;
						}
					}
				
				} while(isRemoved == true);
				
				sopl("Next target label: " + (targetLabel + 1));
				/*
				int target = curIndex;
				for(int j=0; true; j++) {
					if(nextCupIndex.get(target) == targetLabel) {
						
						target = 
						break;
					} else {
						target = nextCupIndex.get(target);
					}
				}*/
				
				
				curIndex = targetLabel;
				
				temp = nextCupIndex.get(curIndex);
				nextCupIndex.set(curIndex, nextThreeIndex[0]);
				nextCupIndex.set(nextThreeIndex[2], temp);
				
				curIndex = nextIndex;
				
				
				
				int testIndex2 = curIndex;
				for(int j=0; j<nextCupIndex.size(); j++) {
					sop(testIndex2 + 1 + " ");
					testIndex2 = nextCupIndex.get(testIndex2);
				}
				sopl();
				
			}
			
			sopl("Current Index: " + curIndex);
			sopl("Cup label: " + (curIndex + 1));
			
			
			sopl("Answer:");
			int testIndex2 = curIndex;
			
			
			int startIndex = 0;
			curIndex = startIndex;
			for(int j=0; j<nextCupIndex.size() - 1; j++) {
				curIndex = nextCupIndex.get(curIndex);
				sop(curIndex + 1);
			}
			sopl();
			
			/*
			long count = 0;
			Set<String> keys = numbers.keySet();
	        for(String key: keys){
	        	
	        	long value = numbers.get(key);
	        	count += value;
	        	
	            //System.out.println(key + "-> " + value + "  or " + StringWithXToNum(value));
	           
	        }
	        */
			
			sopl("Answer: " + count);
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
