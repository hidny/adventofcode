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

public class prob18b {

	//day1 part 1
	//2:38.01
	
	public static void main(String[] args) {
		Scanner in;
		try {
			//in = new Scanner(new File("in2023/prob2023in18.txt"));
			in = new Scanner(new File("in2023/prob2023in0.txt"));
			int numTimes = 0;
			 
			int count = 0;
			boolean part2 = false;
			String line = "";

			Stack stack = new Stack();
			HashSet set = new HashSet();
			
			
			
			
			ArrayList <String>lines = new ArrayList<String>();
			
			
			int LIMIT = 1000;
			
			
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
			

			int curi = LIMIT/2;
			int curj = LIMIT/2;
			
			int maxj= curi;
			int maxi= curj;

			int minj= curi;
			int mini= curj;
			
			int oldi = -1;
			int oldj = -1;
			
			Hashtable<String, Integer> table = new Hashtable<String, Integer>();
			
			char dir = '3';
			char olddir='2';
			char olddir2;
			for(int i=0; i<lines.size() + 2; i++) {
				
				line = lines.get(i % lines.size());
				sopl(line);
				
				
				String token[] = line.split(" ");
				
				olddir2 = olddir;
				olddir = dir;
				dir = token[2].charAt(token[2].length() - 2);
				
				if(olddir == dir) {
					exit(1);
				}
				
				String amountHex = token[2].substring(2,7);
				
				
				int amount =hexToInt(amountHex);

				oldi = curi;
				oldj = curj;
				
				//sopl(curi + "," + curj);
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
				
				if(i >= 2) {

					if(olddir2 == dir) {
						table.put(oldi + "," + oldj + "," + curi + "," + curj+"," + 0, 1);
						sopl(oldi + "," + oldj + "," + curi + "," + curj+"," + 1);
					} else {
						table.put(oldi + "," + oldj + "," + curi + "," + curj+"," + 1, 1);
						sopl(oldi + "," + oldj + "," + curi + "," + curj+"," + 0);
					}
				}
			}
			
			
			sopl("hello");
			sopl((maxi - mini + 1));
			sopl((maxj - minj + 1));
			
			sopl("min i: " + mini);
			
			HashSet<Long> distinctNums = new HashSet<Long>();
			HashMap<Long, Integer> instances = new HashMap<Long, Integer>();
			
			cur = 0L;
			for(int i=mini-100; i<=maxi+200; i++) {
				
				long borders[] = getJSortedBorders(i, table, false);
				
				if(borders.length % 2 != 0) {
					sopl("Doh!");
					 getJSortedBorders(i, table, true);
					sopl("i: " + i);
					sopl("hello:");
					for(int i2=0; i2<borders.length; i2++) {
						sopl(borders[i2]);
					}
					sopl("---");
					
					exit(1);
				}
				
				
				
				for(int i2=0; i2<borders.length; i2+=2) {
					
					cur+= borders[i2+1] - borders[i2] + 1;
					
					long key = borders[i2+1] - borders[i2] + 1;
					distinctNums.add(key);
					
					if(instances.containsKey(key)) {
						
						int num = instances.get(key) + 1;
						instances.remove(key);
						instances.put(key, num);
						
					} else {
						instances.put(key, 1);
					}
				}
			}
			
			
			
			Iterator it = distinctNums.iterator();
			
			sopl("Distinct numbers:");
			while(it.hasNext()) {
				long tmp = (long)it.next();
				sopl(tmp + ": " + instances.get(tmp));
				
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
	
	public static long[] getJSortedBorders(int i, Hashtable<String, Integer> table, boolean verbose) {
		
		Set<String> set = table.keySet();
		
		Iterator<String> it = set.iterator();
		
		ArrayList<Integer> borders = new ArrayList<Integer>();
		
		if(verbose) {
			sopl("getJSorted");
		}
		while(it.hasNext()) {
			String tmp = it.next();
			String tokens[] = tmp.split(",");
			
			if(pint(tokens[0]) != pint(tokens[2])) {
				
				if((pint(tokens[0]) <= i && pint(tokens[2]) >= i) ||
					(pint(tokens[0]) >= i && pint(tokens[2]) <= i) ) {
				

					if(pint(tokens[0]) != i || pint(tokens[4]) == 1) {
						borders.add(pint(tokens[3]));
					}
					
					//if(pint(tokens[0]) != i) {
					//	borders.add(pint(tokens[3]));
					//}

					//borders.add(pint(tokens[3]));
					if(verbose) {
						sopl(tmp);
						if(pint(tokens[0]) != i || pint(tokens[4]) == 1) {
							sopl("adding: " + tokens[1]);
						}
						sopl();
					}
				}
			} else {
				
				if(pint(tokens[0]) == i) {
				
					//borders.add(pint(tokens[1]));
					//borders.add(pint(tokens[3]));
					
					//952407302304
					if(verbose) {
						sopl(tmp);
						sopl("adding 2: " + tokens[1]);
						sopl("adding 2: " + tokens[3]);
						sopl();
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
		
		//Remove dups and hope
		
		/*int numDups = 0;
		for(int i2=1; i2<ret.length; i2++) {
			if(ret[i2] == ret[i2 - 1]) {
				numDups++;
				if(verbose) {
					sopl("dup: " + ret[i2]);
				}
			}
		}
		
		int ret2[] = new int[borders.size() - numDups];
		
		int curIndex = 0;
		for(int i2=0; i2<ret.length; i2++) {
			if(i2>0 && ret[i2] == ret[i2 - 1]) {
				//pass
			} else {
				ret2[curIndex] = ret[i2];
				curIndex++;
			}
		}
		
		if(curIndex != ret2.length) {
			exit(1);
		}
		
		//sopl("hello:");
		//for(int i2=0; i2<ret2.length; i2++) {
		//	sopl(ret2[i2]);
		//}
		//sopl("---");
		*/
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
