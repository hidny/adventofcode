package probs2019;
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

public class prob4 {

	
	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2019/prob2019in4.txt"));
			 //in = new Scanner(new File("in2019/prob2019in4.txt.test"));
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
			//147981-691423
			int start = 147981;
			int end = 691423;
			
			
			
			for(int i=start; i<=end; i++) {
				int digits[] = getDigits(i);
				
				boolean good = true;
				boolean foundMatch = false;
				
				for(int j=0; j<digits.length; j++) {
					if(j > 0 && digits[j-1] > digits[j]) {
						good =false;
						break;
					} else if(j > 0 && digits[j-1] == digits[j]) {
						
						if(j > 1 && digits[j-1] == digits[j-2]) {
							//nope
						} else if(j<digits.length-1 && digits[j] == digits[j+1]) {
							//nope
						} else {
							foundMatch = true;
						}
					}
					
				}
				
				if(foundMatch && good) {
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
	
	//Why didn't I just convert to string? A: Because this is the first solution iIthought of and then I stopped thinking. :(
	public static int[] getDigits(int num) {
		int digits[] = new int[6];
		
		int cur = num;
		for(int i=5; i>=0; i--) {
			digits[i] = cur % 10;
			cur /=10;
		}
		return digits;
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
	
	public static void exit() {
		exit(0);
	}
	public static void exit(int code) {
		sop("Exit with code " + code);
		
		System.exit(code);
	}

}
