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

public class prob16 {

	
	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2019/prob2019in16.txt"));
			 //in = new Scanner(new File("in2019/prob2019in16.txt.test"));
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
			
			String sig = lines.get(0);
			
			String nextSig;
			
			long num;
			for(int phase = 1; phase<=100; phase++) {
				
				nextSig = "";
				for(int m=1; m<=sig.length(); m++) {
					num = 0;
					for(int i=1; i<=sig.length(); i++) {
						int factor = getFactor(i, m);
						//sop(factor);
						num += factor * (sig.charAt(i-1) - '0');
						//sopl(factor + " " + (sig.charAt(i-1) - '0') + "  " + sig.charAt(i-1));
					}
					//sopl();
					num = Math.abs(num);
					
					nextSig += "" + (num%10);
				}
				sig = nextSig;
				sopl(nextSig);
			}
			
			sopl("Answer: " + sig.substring(0, 8));
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	
	public static int getFactor(int i, int m) {
		int num = (i/m) % 4;
		
		if(num == 0) {
			return 0;
		} else if(num ==1) {
			return 1;
		} else if(num == 2) {
			return 0;
		} else if(num == 3) {
			return -1;
		} else {
			sopl("aa");
			exit(1);
			return -1;
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
	
	public static void exit() {
		exit(0);
	}
	public static void exit(int code) {
		sop("Exit with code " + code);
		
		System.exit(code);
	}

}
