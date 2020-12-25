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

public class prob23b {

	
	public static int NUM_CUPS = 1000000;
	public static int NUM_ROUNDS = 10000000;
	
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
			

			//ArrayList<Integer> nextCupIndex = new ArrayList<Integer>();
			
			int nextCupIndex[] = new int[NUM_CUPS];
			
			int curIndex = -1;
			
			for(int i=0; i<lines.size(); i++) {
				
				//int temp = Integer.parseInt(lines.get(i));
				//count+=temp;
				
				String line = lines.get(i);
				String lineCopy = String.copyValueOf(line.toCharArray());

				
				int last = -1;
				
				for(int j=0; j<line.length(); j++) {
					int current = (int)(line.charAt(j) - '1');
					
					if(j  > 0) {
						nextCupIndex[last] =  current;
						sopl((last+1) + " ->" + (current+1));
					}
					last = current;
					
				}
				
				for(int j=9; j<NUM_CUPS; j++) {
					nextCupIndex[last] = j;
					last = j;
					//sopl((last+1) + " ->" + (j+1));
				}
				curIndex = line.charAt(0) - '1';
				nextCupIndex[last] =  (int)(curIndex);
				//sopl(last + " ->" + (int)(curIndex));
				
				
			}
			
			//int testIndex = curIndex;
			//for(int j=0; j<nextCupIndex.size(); j++) {
				//sop(testIndex + 1 + " ");
			//	testIndex = nextCupIndex.get(testIndex);
			//}
			//sopl();

			//3 cups rm
			//dest cup:
			//label -1... and wraps around...
			
			int minCup = 0;
			int maxCup = 0;
			
			
			//todo increase it
			for(int it=0; it<10000000; it++) {
				
				if(it % 10000 == 0) {
				//	sopl("Iteration: " + it);
				}
				int nextThreeCups[] = new int[3];
				int nextThreeIndex[] = new int[3];
				
				//sop("Pick up ");
				int nextCupInd = curIndex;
				for(int j=0; j<nextThreeCups.length; j++) {
					nextCupInd = nextCupIndex[nextCupInd];
					
					nextThreeIndex[j] = nextCupInd;
					
					//sop((nextCupInd+1) + " ");
				}
				//sopl();
				
				int temp = nextCupIndex[curIndex] + 0;
				
				int nextIndex= nextCupIndex[nextThreeIndex[2]];
				nextCupIndex[curIndex] =  nextIndex;
				
				//this is label minus 1
				int targetLabel = curIndex;

				boolean isRemoved = false;
				
				do {
					targetLabel = targetLabel - 1;
					if(targetLabel < 0) {
						targetLabel = NUM_CUPS - 1;
					}
					
					isRemoved = false;
					
					for(int j=0; j<nextThreeCups.length; j++) {
						if(nextThreeIndex[j] == targetLabel) {
							isRemoved = true;
						}
					}
				
				} while(isRemoved == true);
				
				//sopl("Next target label: " + (targetLabel + 1));
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
				
				temp = nextCupIndex[curIndex];
				nextCupIndex[curIndex] =  nextThreeIndex[0];
				nextCupIndex[nextThreeIndex[2]] =  temp;
				
				curIndex = nextIndex;
				
				
				
				//int testIndex2 = curIndex;
				//for(int j=0; j<nextCupIndex.length; j++) {
					//sop(testIndex2 + 1 + " ");
				//	testIndex2 = nextCupIndex[testIndex2];
				//}
				//sopl();
				
			}
			
			sopl("Current Index: " + curIndex);
			sopl("Cup label: " + (curIndex + 1));
			
			
			sopl("Answer:");
			int testIndex2 = curIndex;
			
			
			int startIndex = 0;
			curIndex = startIndex;
			
			long answer = 1;
			for(int j=0; j<2; j++) {
				curIndex = nextCupIndex[curIndex];
				
				answer *= (curIndex + 1);
			}
			sopl();
			
			
			sopl("Answer: " + answer);
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
