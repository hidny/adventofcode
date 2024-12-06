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

public class prob6 {

	//day1 part 1
	//2:38.01
	
	public static void main(String[] args) {
		Scanner in;
		try {
			in = new Scanner(new File("in2024/prob2024in6.txt"));
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
			
			int curi=0;
			int curj=0;
			int dir=0;
			for(int i=0; i<lines.size(); i++) {
				
				for(int j=0; j<lines.get(i).length(); j++) {
					if(lines.get(i).charAt(j) == '^') {
						curi=i;
						curj=j;
						dir=0;
					}
				}
				line = lines.get(i);
				
			}
			
			cur=0;
			
			for(int i=0; i<lines.size(); i++) {
				
				for(int j=0; j<lines.get(i).length(); j++) {
					if(checkLocationForLoop(i, j, curi, curj, lines)) {
						cur++;
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
		
		public static boolean checkLocationForLoop(int iTry, int jTry, int curi, int curj, ArrayList <String>lines) {
			int LIMIT = Math.max(lines.size(), lines.get(0).length());
			
			if(curi == iTry && curj == jTry) {
				return false;
			}
			
			int table342[][] = new int[LIMIT][LIMIT];
			
			for(int i=0; i<table342.length; i++) {
				for(int j=0; j<table342[0].length; j++) {
					table342[i][j] = 0;
				}
			}
			

			int dir=0;
			
			table342[curi][curj] = 1;
			int nexti;
			int nextj;
			OUT:
			while(curi>=0 && curj>=0 && curi < lines.size() && curj < lines.get(0).length()) {
				
				boolean moved = false;

				nexti = curi;
				nextj = curj;
				while(moved == false) {
					nexti = curi;
					nextj = curj;
					
					moved = true;
					if(dir == 0) {
						if(curi==0) {
							break OUT;
						}
						nexti--;
						if(lines.get(nexti).charAt(nextj) == '#' || ( nexti ==iTry && nextj == jTry)) {
							dir = (dir + 1) % 4;
							moved = false;
						}
						
					} else if(dir == 1) {
						if(curj == lines.get(0).length() - 1) {
							break OUT;
						}
						nextj++;
						if(lines.get(nexti).charAt(nextj) == '#' || ( nexti ==iTry && nextj == jTry)) {
							dir = (dir + 1) % 4;
							moved = false;
						}
					} else if(dir == 2) {
						if(curi==lines.size()-1) {
							break OUT;
						}
						nexti++;
						if(lines.get(nexti).charAt(nextj) == '#' || ( nexti ==iTry && nextj == jTry)) {
							dir = (dir + 1) % 4;
							moved = false;
						}
						
					} else if(dir == 3) {
						if(curj == 0) {
							break OUT;
						}
						nextj--;
						if(lines.get(nexti).charAt(nextj) == '#' || ( nexti ==iTry && nextj == jTry)) {
							dir = (dir + 1) % 4;
							moved = false;
						}
						
					}
				}
				
				curi = nexti;
				curj = nextj;
				//sopl(curi + ", " + curj);
				if((table342[curi][curj] & ((int)Math.pow(2, dir))) != 0) {
					return true;
				}
				table342[curi][curj] ^= (int)Math.pow(2, dir);
				//sopl(dir);
			}
			//185, 186
			

			return false;
			
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
