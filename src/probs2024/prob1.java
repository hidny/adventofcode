package probs2024;
import java.io.File;

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

public class prob1 {

	//day1 part 1
	
	public static void main(String[] args) {
		Scanner in;
		try {
			in = new Scanner(new File("in2024/prob2024in1.txt"));
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
			ArrayList ints1 = new ArrayList<Integer>();
			ArrayList ints2 = new ArrayList<Integer>();
			for(int i=0; i<lines.size(); i++) {
				
				
				line = lines.get(i);
				
				String tokens[] = line.split("   ");
				
				int num[] = new int[2];
				num[0] = pint(tokens[0]);
				num[1] = pint(tokens[1]);
				
				System.out.println(num[0] + ", | + " + num[1]);
				
				ints1.add(num[0]);
				ints2.add(num[1]);
				//cur += pint(line);
				
			}
			
			
			int array1[] = new int[ints1.size()];
			int array2[] = new int[ints1.size()];
			
			for(int i=0; i<ints1.size(); i++) {
				
				array1[i] = (int)ints1.get(i);
				array2[i] = (int)ints2.get(i);
				
				for(int j=i+1; j<ints1.size(); j++) {
					
					//System.out.println((int)ints1.get(j));
					if((int)ints1.get(j) < array1[i]) {
						System.out.println("s: "+ array1[i]);
						
						int tmp = (int)ints1.get(j);
						ints1.set(j, array1[i]);
						
						ints1.set(i, tmp);
						
						array1[i] = (int)ints1.get(i);
						
					}
					if((int)ints2.get(j) < array2[i]) {
						
						int tmp = (int)ints2.get(j);
						ints2.set(j, array2[i]);
						
						ints2.set(i, tmp);
						array2[i] = (int)ints2.get(i);
					}
				}
				
				//System.out.println(array1[i]);
				//System.out.println(array2[i]);
				//System.out.println();
				cur += Math.abs(array2[i] - array1[i]);
				//System.exit(1);
				
				
				
			}


			sopl("Answer: " + cur);
			in.close();
			cur= 0;
			for(int i=0; i<ints1.size(); i++) {
				int mult = (int)ints1.get(i);
				
				int mult2= 0;
				for(int j=0; j<ints2.size(); j++) {
					if((int)ints1.get(i) == (int)ints2.get(j)) {
						mult2++;
					}
				}
				
				cur+= mult * mult2;
			}
			
			System.out.print("A2: " + cur);
			
			
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
