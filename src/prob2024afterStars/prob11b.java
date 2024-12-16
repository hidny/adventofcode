package prob2024afterStars;
import java.io.File;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

import number.IsNumber;
import utils.Mapping;
import utils.Sort;

public class prob11b {

	//day1 part 1
	//2:38.01
	
	public static void main(String[] args) {
		Scanner in;
		try {
			in = new Scanner(new File("in2024/prob2024in11.txt"));
			//in = new Scanner(new File("in2024/prob2024in0.txt"));
			int numTimes = 0;
			 
			int count = 0;
			boolean part2 = false;
			String line = "";

			LinkedList queue = new LinkedList();
			Stack stack = new Stack();
			HashSet set = new HashSet();
			
			
			
			ArrayList <String>lines = new ArrayList<String>();
			
			
			int LIMIT = 20000;
			boolean table342[][] = new boolean[LIMIT][LIMIT];
			
			
			//dir: 0 up
			//1 right
			//2 down
			//3 left
			
			while(in.hasNextLine()) {
				line = in.nextLine();
				lines.add(line);
				
			}

			int most = 0;
			int most2 = 0;
			int most3 = 0;
			long cur = 0L;
			ArrayList ints = new ArrayList<Integer>();
			
			line = "1";
			

			Hashtable<BigInteger, Long> trail = new Hashtable<BigInteger, Long>();
			
			
			//line = "125 17";
			line = lines.get(0);
			
			String tokens[] = line.split(" ");
			
			long nums[] = new long[tokens.length];
			for(int i=0; i<nums.length; i++) {
				nums[i] = plong(tokens[i]);
			}
			
			prob11obj head = new prob11obj(new BigInteger(nums[0] + ""), null);

			prob11obj current = head;
			trail.put(head.num, 1L);
			
			for(int i=1; i<nums.length; i++) {
				current.next = new prob11obj(new BigInteger(nums[i] + ""), null);
				trail.put(current.next.num, 1L);
				current = current.next;
			}
			
			Hashtable<BigInteger, Long> prevtrail = new Hashtable<BigInteger, Long>();
			
			for(int i=0; i<25; i++) {
				
				prevtrail = trail;
				trail = new Hashtable<BigInteger, Long>();
				
				Enumeration<BigInteger> list = prevtrail.keys();
				
				
				
				while(list.hasMoreElements()) {
					
					BigInteger nextElement = list.nextElement();
					long num = prevtrail.get(nextElement);
					
					prob11obj tmpObjectWithNum = new prob11obj(nextElement, null);
					tmpObjectWithNum.do_calc();
					
					if(trail.containsKey(tmpObjectWithNum.num)) {
						long prevNum = trail.get(tmpObjectWithNum.num);
						
						trail.put(tmpObjectWithNum.num, prevNum + num);
					} else {
						trail.put(tmpObjectWithNum.num, num);
					}
					
					prob11obj nextTmpObjectWithNum = tmpObjectWithNum.next;
					
					if(nextTmpObjectWithNum != null) {
						if(trail.containsKey(nextTmpObjectWithNum.num)) {
							long prevNum = trail.get(nextTmpObjectWithNum.num);
							
							trail.put(nextTmpObjectWithNum.num, prevNum + num);
						} else {
							trail.put(nextTmpObjectWithNum.num, num);
						}
					}
				}
				
			}
			
			Enumeration<BigInteger> list = trail.keys();
			
			

			cur=0;
			while(list.hasMoreElements()) {
				cur += trail.get(list.nextElement());
			}
			

			sopl("Answer: " + cur);
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

	public static long plong(String s) {
		if (IsNumber.isNumber(s)) {
			return Long.parseLong(s);
		} else {
			sop("Error: (" + s + ") is not a number");
			return -1;
		}
	}
	public static int pint(String s) {
		if (IsNumber.isNumber(s)) {
			return Integer.parseInt(s);
		} else {
			sop("Error: (" + s + ") is not a number");
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
	
	public static int[][] getIntTable(ArrayList<String> lines) {
		int grid[][] = new int[lines.size()][lines.get(0).length()];
		
		String line;
		for(int i=0; i<lines.size(); i++) {
			
			
			line = lines.get(i);
			
			for(int j=0; j<grid[0].length; j++) {
				if(lines.get(i).charAt(j) == '.') {
					grid[i][j] = -1;
				} else {
					grid[i][j] = (int)(lines.get(i).charAt(j) - '0');
				}
			}
		}
		
		return grid;
	}

}
