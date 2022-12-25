package probs2022;
import java.io.File;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

import number.IsNumber;
import utils.Mapping;
import utils.Sort;

public class prob13 {

	//488
	//1202 too low
	//577
	//866
	//3628
	
	//3794 wrong
	
	public static void main(String[] args) {
		Scanner in;
		try {
			in = new Scanner(new File("in2022/prob2022in13.txt"));
			//in = new Scanner(new File("in2022/prob2022in14.txt"));
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
			boolean table[][] = new boolean[LIMIT][LIMIT];
			
			
			//dir: 0 up
			//1 right
			//2 down
			//3 left
			
			while(in.hasNextLine()) {
				line = in.nextLine();
				lines.add(line);
				
			}

			ArrayList ints = new ArrayList<Integer>();
			
			ArrayList<String> pair1 = new ArrayList<String>();

			ArrayList<String> pair2 = new ArrayList<String>();
			
			boolean usePair1 = true;
			
			for(int i=0; i<lines.size(); i++) {
				
				
				line = lines.get(i).trim();
				if(line.startsWith("[")) {
					if(usePair1) {
						pair1.add(line);
					} else {
						pair2.add(line);
					}
					usePair1 = ! usePair1;
				}
				
				
			}
			
			
			for(int i=0; i<pair1.size() && i<pair2.size(); i++) {
				
				
				String p1 = pair1.get(i);
				String p2 = pair2.get(i);
				
				ArrayList<prob13Pair> p1p = prob13Pair.parseParquet(p1);
				ArrayList<prob13Pair> p2p = prob13Pair.parseParquet(p2);
				
				sopl(p1);
				sopl(p2);
				int goodSoFar = 0;
				
				
				for(int j=0; j<p1p.size() && goodSoFar == 0; j++) {
					

					if(j>=p2p.size()) {
						goodSoFar = -1;
						break;
					}
					
					prob13Pair tmp1 = p1p.get(j);
					prob13Pair tmp2 = p2p.get(j);
					
					if(tmp1.isList == false && tmp2.isList == false) {
						sopl("Compare " + tmp1.num + " vs " + tmp2.num);
						if(tmp2.num < tmp1.num) {
							goodSoFar = -1;
							break;
						} else if(tmp1.num < tmp2.num) {
							goodSoFar = 1;
							break;
						}
					} else if(tmp1.isList && tmp2.isList) {
						
						goodSoFar = recurse(tmp1.list, tmp2.list, 1);
						if(goodSoFar != 0) {
							break;
						}
					} else {
						if(tmp1.isList == false) {
							tmp1.isList = true;
							tmp1.list.add(new prob13Pair(false, tmp1.num, new ArrayList<prob13Pair>()));
						}
						if(tmp2.isList == false) {
							tmp2.isList = true;
							tmp2.list.add(new prob13Pair(false, tmp2.num, new ArrayList<prob13Pair>()));
						}
						
						goodSoFar = recurse(tmp1.list, tmp2.list, 1);
						if(goodSoFar != 0) {
							break;
						}
					}
					
				}
				
				sopl(goodSoFar);
				sopl();
				if(goodSoFar >= 0) {
					count += (i+1);
				}
			}
			
			
			sopl("Answer: " + count);
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	
	public static int recurse(ArrayList<prob13Pair> p1p, ArrayList<prob13Pair> p2p, int depth) {
		int goodSoFar = 0;
		
		
		for(int j=0; goodSoFar == 0 && j<p1p.size(); j++) {
			
			if(j>=p2p.size()) {
				goodSoFar = -1;
				break;
			}
			
			prob13Pair tmp1 = p1p.get(j);
			prob13Pair tmp2 = p2p.get(j);
			
			if(tmp1.isList == false && tmp2.isList == false) {
				for(int i=0; i<depth; i++) {
					sop(" ");
				}
				sopl("CompareRec " + tmp1.num + " vs " + tmp2.num);
				
				if(tmp2.num < tmp1.num) {
					goodSoFar = -1;
					break;
				} else if(tmp1.num < tmp2.num) {
					goodSoFar = +1;
					break;
				}
			} else if(tmp1.isList && tmp2.isList) {
				
				goodSoFar = recurse(tmp1.list, tmp2.list, depth+1);
				if(goodSoFar != 0) {
					break;
				}
					
			} else {
				if(tmp1.isList == false) {
					tmp1.isList = true;
					tmp1.list.add(new prob13Pair(false, tmp1.num, new ArrayList<prob13Pair>()));
				}
				if(tmp2.isList == false) {
					tmp2.isList = true;
					tmp2.list.add(new prob13Pair(false, tmp2.num, new ArrayList<prob13Pair>()));
				}
				goodSoFar = recurse(tmp1.list, tmp2.list, depth+1);
				if(goodSoFar != 0) {
					break;
				}
			}
			
		}
		
		return goodSoFar;
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