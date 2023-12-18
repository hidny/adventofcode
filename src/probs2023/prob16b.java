package probs2023;
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

public class prob16b {

	//day1 part 1
	//2:38.01
	
	public static void main(String[] args) {
		Scanner in;
		try {
			in = new Scanner(new File("in2023/prob2023in16.txt"));
			//in = new Scanner(new File("in2023/prob2023in0.txt"));
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
			
			
			//dir: 0 up
			//1 right
			//2 down
			//3 left
			
			while(in.hasNextLine()) {
				line = in.nextLine();
				lines.add(line);
				
			}


			boolean table[][] = new boolean[lines.size()][lines.get(0).length()];
			
			

			long max = 0L;
			
			for(int i=0; i<2 * table.length + 2* table[0].length; i++) { 
				ArrayList<Integer> is = new ArrayList<Integer>();
				ArrayList<Integer> js = new ArrayList<Integer>();
				ArrayList<Integer> dirs = new ArrayList<Integer>();
				
				table = new boolean[lines.size()][lines.get(0).length()];
			
				//11265
				
				if(i<table.length) {
			
					is.add(i);
					js.add(0);
					dirs.add(1);
				} else if(i <table.length + table[0].length) {
					
					is.add(table.length - 1);
					js.add(i - table.length);
					dirs.add(0);
				} else if(i < 2 * table.length + table[0].length) {

					is.add(i - table.length - table[0].length);
					js.add(table[0].length - 1);
					dirs.add(3);
				} else if(i < 2 * table.length + 2*table[0].length) {

					is.add(0);
					js.add(i - 2 * table.length - table[0].length);
					dirs.add(2);
				} else {
					exit(1);
				}
				
				
	
				long cur = 0L;
				
				HashSet<String>  taken= new HashSet<String> ();
				
				boolean stillGood = true;
				while( stillGood ) {
					
					stillGood = false;
					
					//sopl("loop");
					
					for(int b=0; b<is.size(); b++) {
						
						int curi=is.get(b);
						int curj=js.get(b);
						int curDir = dirs.get(b);
						
						//sopl(curi + "," +curj);
						
						if(! taken.contains(curi+"," + curj +"," + curDir) && curi>=0 && curi<table.length && curj>=0 && curj<table[0].length) {
							taken.add(curi+"," + curj +"," + curDir);
							
							
							stillGood = true;
							
							char tmp = lines.get(curi).charAt(curj);
	
							table[curi][curj] = true;
							
							if(tmp == '.') {
								
							} else if(tmp == '|' && (curDir == 1 || curDir == 3)) {
								
								curDir = 0;
								is.add(curi + 1);
								js.add(curj);
								dirs.add(2);
								
							} else if(tmp == '-' && (curDir == 0 || curDir == 2)) {
	
								curDir = 1;
								is.add(curi);
								js.add(curj - 1);
								dirs.add(3);
								
							} else if(tmp == '/') {
								
								if(curDir == 0) {
									curDir = 1;
								} else if(curDir == 1) {
									curDir = 0;
								} else if(curDir == 2) {
									curDir = 3;
								} else if(curDir == 3) {
									curDir = 2;
								} else {
									exit(1);
								}
								
								
							} else if(tmp == '\\') {
	
								if(curDir == 0) {
									curDir = 3;
								} else if(curDir == 1) {
									curDir = 2;
								} else if(curDir == 2) {
									curDir = 1;
								} else if(curDir == 3) {
									curDir = 0;
								} else {
									exit(1);
								}
								
							} else {
								//pass
							}
							
							
							if(curDir == 0) {
								curi--;
							} else if(curDir == 1) {
								curj++;
							} else if(curDir == 2) {
								curi++;
							} else if(curDir == 3) {
								curj--;
							} else {
								exit(1);
							}
	
							is.set(b, curi);
							js.set(b, curj);
							dirs.set(b, curDir);
						}
					}
				}
	
				cur = 0;
				for(int i2=0; i2<table.length; i2++) {
					for(int j=0; j<table[0].length; j++) {
						if(table[i2][j]) {
							cur++;
						}
					}
				}
				
				if(cur > max) {
					max = cur;
				}
			}

			sopl("Answer: " + max);
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
