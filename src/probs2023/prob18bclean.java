package probs2023;
import java.io.File;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

import number.IsNumber;
import utils.Mapping;
import utils.Sort;

public class prob18bclean {

	//day1 part 1
	//2:38.01
	
	public static void main(String[] args) {
		Scanner in;
		try {
			in = new Scanner(new File("in2023/prob2023in18.txt"));
			//in = new Scanner(new File("in2023/prob2023in0.txt"));

			String line;
			long cur;
			
			ArrayList <String>lines = new ArrayList<String>();
			
			
			//dir: 0 up
			//1 right
			//2 down
			//3 left
			
			while(in.hasNextLine()) {
				line = in.nextLine();
				lines.add(line);
				
			}
			
			int curi = 0;
			int curj = 0;
			
			int maxj= curi;
			int maxi= curj;

			int minj= curi;
			int mini= curj;
			
			int oldi = -1;
			int oldj = -1;
			
			Hashtable<String, Integer> table = new Hashtable<String, Integer>();
			
			int NUM_OVER_SHOOT_FOR_HISTORY = 2;
			char dir = '3';
			char olddir='2';
			char olddir2;
			for(int i=0; i<lines.size() + NUM_OVER_SHOOT_FOR_HISTORY; i++) {
				
				line = lines.get(i % lines.size());
				sopl(line);
				
				
				String token[] = line.split(" ");
				
				olddir2 = olddir;
				olddir = dir;
				dir = token[2].charAt(token[2].length() - 2);
				
				if(olddir == dir) {
					//Check that we don't go the same direction twice in a row.
					exit(1);
				}
				
				String amountHex = token[2].substring(2,7);
				
				int amount = hexToInt(amountHex);

				oldi = curi;
				oldj = curj;
				
				sopl(dir + " " + amount);
				
				if(dir == 'U'  || dir == '3') {
					
					curi-= amount;
					
				} else if(dir == 'R' || dir == '0') {
					curj+= amount;
				} else if(dir == 'D' || dir == '1') {
					curi+= amount;
					
				} else if(dir == 'L' || dir == '2') {
					curj-= amount;
				}
				
				if(curi >maxi) {
					maxi = curi;
				}
				
				if(curi <mini) {
					mini = curi;
				}
				
				if(curj<minj) {
					minj=curj;
				}
				
				if(curj>maxj) {
					maxj =curj;
				}
				
				if(i >= NUM_OVER_SHOOT_FOR_HISTORY) {

					if(olddir2 == dir) {
						table.put(oldi + "," + oldj + "," + curi + "," + curj+"," + 0, 1);
						sopl(oldi + "," + oldj + "," + curi + "," + curj+"," + 1);
					} else {
						table.put(oldi + "," + oldj + "," + curi + "," + curj+"," + 1, 1);
						sopl(oldi + "," + oldj + "," + curi + "," + curj+"," + 0);
					}
				}
			}
			
			
			sopl("(length i, length j) = (" + (maxi - mini + 1) + ", " + (maxj - minj + 1) + ")");
			
			
			cur = 0L;
			for(int i=mini-100; i<=maxi+200; ) {
				
				long borders[] = getJSortedBorders(i, table, false);
				
				if(borders.length % 2 != 0) {
					sopl("ERROR: borders array should be even!");
					
					exit(1);
				}
				
				long horiBorder[][] = getJHorizontalBorders(i, table, false);
				
				boolean usedHoriBorder[] = new boolean[horiBorder[0].length];
				for(int j=0; j<usedHoriBorder.length; j++) {
					usedHoriBorder[j] = false;
				}
				
				//Get nextI where something semi-interesting happens:
				int nextI = getNextI(i, table);
				
				for(int i2=0; i2<borders.length; i2+=2) {
					
					cur+= (nextI - i) * (borders[i2+1] - borders[i2] + 1);
					
					
					for(int j2=0; j2<horiBorder[0].length; j2++) {
						
						if(horiBorder[0][j2] == borders[i2 + 1]) {
							cur += horiBorder[1][j2] - horiBorder[0][j2];
							
							if(usedHoriBorder[j2]) {
								//Hack to compensate for the edge-case where the hori-border gets counted twice where
								// it should only be counted once.
								// Also, subtract one extra because in the edge-case, both ends of the horizontal border are accounted for:
								cur -= (horiBorder[1][j2] - horiBorder[0][j2] + 1);
							}
							
							usedHoriBorder[j2] = true;
						}
						
						if(horiBorder[1][j2] == borders[i2]) {
							cur += horiBorder[1][j2] - horiBorder[0][j2];
							
							if(usedHoriBorder[j2]) {
								//Hack to compensate for the edge-case where the hori-border gets counted twice where
								// it should only be counted once.
								// Also, subtract one extra because in the edge-case, both ends of the horizontal border are accounted for:
								cur -= (horiBorder[1][j2] - horiBorder[0][j2] + 1);
							}
							usedHoriBorder[j2] = true;
						}
					}
					
				}
				

				// Increment i to the next point where something interesting happens:
				i = nextI;
			}
			
			
			
			sopl("Done");
			
			sopl("Answer: " + cur);
			//Plan: 
			
			//952407302304

		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	
	public static int getNextI(int i, Hashtable<String, Integer> table) {
		
		Set<String> set = table.keySet();
		
		Iterator<String> it = set.iterator();
		
		int curNextI = -1;
		
		while(it.hasNext()) {
			String tmp = it.next();
			String tokens[] = tmp.split(",");
			
			if(pint(tokens[0]) != pint(tokens[2])) {
				
				if(pint(tokens[0]) >= i && (curNextI == -1 || pint(tokens[0]) < curNextI) ) {
					curNextI = pint(tokens[0]);
				}

				if(pint(tokens[2]) >= i && (curNextI == -1 || pint(tokens[2]) < curNextI) ) {
					curNextI = pint(tokens[2]);
				}
				
			} else {
				
				if(pint(tokens[0]) >= i && (curNextI == -1 || pint(tokens[0]) < curNextI) ) {
					curNextI = pint(tokens[0]);
				}
				
			}
		}
		
		if(curNextI == i || curNextI == -1) {
			curNextI = i + 1;
		}
		
		
		return curNextI;
	}
	public static long[] getJSortedBorders(int i, Hashtable<String, Integer> table, boolean verbose) {
		
		Set<String> set = table.keySet();
		
		Iterator<String> it = set.iterator();
		
		ArrayList<Integer> borders = new ArrayList<Integer>();
		
	
		while(it.hasNext()) {
			String tmp = it.next();
			String tokens[] = tmp.split(",");
			
			if(pint(tokens[0]) != pint(tokens[2])) {
				
				if((pint(tokens[0]) <= i && pint(tokens[2]) >= i) ||
					(pint(tokens[0]) >= i && pint(tokens[2]) <= i) ) {
				

					if(pint(tokens[0]) != i || pint(tokens[4]) == 1) {
						borders.add(pint(tokens[3]));
					}
					
				}
			}
		}
		
		
		long ret[] = new long[borders.size()];
		
		for(int i2=0; i2<ret.length; i2++) {
			ret[i2] = borders.get(i2);
		}
		
		for(int i2=0; i2<ret.length; i2++) {
			for(int j2=i2+1; j2<ret.length; j2++) {
				
				if(ret[i2] > ret[j2]) {
					long tmp = ret[i2];
					ret[i2] = ret[j2];
					ret[j2] = tmp;
				}
			}
		}
		
		return ret;
	}
	
	public static long[][] getJHorizontalBorders(int i, Hashtable<String, Integer> table, boolean verbose) {
		Set<String> set = table.keySet();
		
		Iterator<String> it = set.iterator();
		
		ArrayList<Integer> borders = new ArrayList<Integer>();
		
		
		ArrayList<Integer> lhs = new ArrayList<Integer>();
		ArrayList<Integer> rhs = new ArrayList<Integer>();
		
		while(it.hasNext()) {
			String tmp = it.next();
			String tokens[] = tmp.split(",");
			
			
			if(pint(tokens[0]) == pint(tokens[2]) && pint(tokens[0]) == i) {
				
				
				lhs.add(Math.min(pint(tokens[1]), pint(tokens[3])));
				rhs.add(Math.max(pint(tokens[1]), pint(tokens[3])));
				
			}

		}
		
		long ret[][] = new long[2][lhs.size()];
		
		for(int j=0; j<ret[0].length; j++) {
			ret[0][j] = lhs.get(j);
			ret[1][j] = rhs.get(j);
		}
		return ret;
	}

	public static int hexToInt(String hex) {
		int ret = 0;
		for(int i=0; i<hex.length(); i++) {
			
			char cur = hex.charAt(i);
			
			if(cur >= '0' && cur <='9') {
				ret = 16*ret + (int)(cur - '0');
			} else if(cur >= 'a' && cur <= 'f') {
				ret = 16*ret + (int)(cur - 'a' + 10);
				
				
			} else {
				sopl(cur);
				exit(1);
			}
		}
		return ret;
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
