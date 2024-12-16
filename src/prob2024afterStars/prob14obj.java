package prob2024afterStars;
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

public class prob14obj {

	//day1 part 1
	//2:38.01
	
	public long p[] = new long[2];
	public long v[] = new long[2];
	
	
	
	
	public prob14obj(long[] p, long[] v) {
		super();
		this.p = p;
		this.v = v;
	}

	public void tick(int numSeconds) {
		
		p[0] += numSeconds * v[0];
		p[1] += numSeconds * v[1];
		
		/*sopl("Move to:");
		sopl(p[0]);
		sopl(p[1]);
		
		sopl("ie");
		sopl(mod(p[0], 11));
		sopl(mod(p[1], 7));
		*/
		
	}
	
	//0: x
	//1: y
	public int quad(int width, int height) {
		
		int MidUp = height/2;
		int MidLeftRight = width/2;
		
		sopl("xtest: " + mod(this.p[0], width));
		sopl("ytest: " + mod(this.p[1], height));
		
		if(mod(this.p[0], width) < MidLeftRight) {
			
			if(mod(this.p[1], height) < MidUp) {
				return 0;
			} else if(mod(this.p[1], height) == MidUp) {
				sopl("???3");
				return -1;
			
			} else {
				return 1;
				
			}
		} else if(mod(this.p[0], width) == MidLeftRight) {
			sopl("???2");
			return -1;
		
		} else {
			if(mod(this.p[1], height) < MidUp) {
				return 2;
			} else if(mod(this.p[1], height) == MidUp) {
				sopl("???");
				return -1;
			
			} else {
				return 3;
				
			}
		}
		
	}
	
	public static long mod(long num, long mod) {
		long tmp = num % mod;
		if(tmp < 0) {
			return tmp + mod;
			
		} else {
			return tmp;
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
		if (IsNumber.isLong(s)) {
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
		
		for(int i=0; i<lines.size(); i++) {
			
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
	

	public static char[][] getCharTable(ArrayList<String> lines) {
		char grid[][] = new char[lines.size()][lines.get(0).length()];
		
		for(int i=0; i<lines.size(); i++) {
			
			for(int j=0; j<grid[0].length; j++) {
				grid[i][j] = lines.get(i).charAt(j);

			}
		}
		
		return grid;
	}

}
