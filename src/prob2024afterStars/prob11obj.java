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

public class prob11obj {

	//day1 part 1
	//2:38.01
	public BigInteger num;
	public prob11obj next;

	public prob11obj(BigInteger tmp2, prob11obj next2) {
		// TODO Auto-generated constructor stub
		this.num = tmp2;
		this.next = next2;
	}

	public void do_calc() {
		if(num.compareTo(BigInteger.ZERO) == 0) {
			num = BigInteger.ONE;
		} else if(("" + num).length() % 2 == 0) {
			BigInteger tmp = new BigInteger(("" + num).substring(0, ("" + num).length()/2));
			BigInteger tmp2 = new BigInteger(("" + num).substring(("" + num).length()/2));
			
			this.num = tmp;
			
			prob11obj next2 = new prob11obj(tmp2, next);
			this.next = next2;
		} else {
			this.num = this.num.multiply(new BigInteger("2024"));
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
