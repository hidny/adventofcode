package prob2024afterStars;
import java.io.File;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

import number.IsNumber;
import utils.Mapping;
import utils.Sort;

public class prob7b2 {

	//day1 part 1
	//2:38.01
	
	public static void main(String[] args) {
		Scanner in;
		try {
			in = new Scanner(new File("in2024/prob2024in7.txt"));
			//in = new Scanner(new File("in2024/prob2024in0.txt"));
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
			for(int i=0; i<lines.size(); i++) {
				
				
				line = lines.get(i);
				
				
				
				BigInteger lhs = new BigInteger(line.split(":")[0]);
				
				String tokens[] = line.split(": ")[1].split(" ");
				
				ArrayList<BigInteger> pos = null;
				ArrayList<BigInteger> prevPos = null;
				
				
				for(int j=0; j<tokens.length; j++) {
					
					prevPos = pos;
					
					if(j==0) {
						pos = new ArrayList();
						BigInteger next = new BigInteger(tokens[j]);
						pos.add(next);
					} else {
						
						pos = new ArrayList();
						
						BigInteger next = new BigInteger(tokens[j]);
						
						for(int k=0; k<prevPos.size(); k++) {
							
							addIfNotBigger(pos, lhs, prevPos.get(k).add(next));
							addIfNotBigger(pos, lhs, prevPos.get(k).multiply(next));
							
							
							BigInteger tmp = BigInteger.ONE;
							BigInteger TEN = new BigInteger("10");
							int numDigits = 1;
							BigInteger finalConcat = prevPos.get(k);
							
							while(next.compareTo(tmp) >=0) {
								tmp = tmp.multiply(TEN);
								finalConcat = finalConcat.multiply(TEN);
								numDigits++;
								
							}
							
							addIfNotBigger(pos, lhs, finalConcat.add(next));
						}
					}
					
					
					prevPos = pos;
					
				}
				
				for(int k=0; k<prevPos.size(); k++) {
					if(lhs.compareTo(prevPos.get(k)) == 0) {
						cur += lhs.longValue();
						//One small bug: I forgot to put break and it failed the test data
						break;
					}
				}
				
			}


			sopl("Answer: " + cur);
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	
	public static void addIfNotBigger(ArrayList list, BigInteger target, BigInteger cur) {
		
		if(cur.compareTo(target) <=0) {
			list.add(cur);
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
			sop("Error: (" + s + ") is not a number");
			return -1;
		}
	}

	public static long plong(String s) {
		if (IsNumber.isNumber(s)) {
			return Long.parseLong(s);
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

}
