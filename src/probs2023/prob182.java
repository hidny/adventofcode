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

public class prob182 {

	//day1 part 1
	//2:38.01
	
	public static void main(String[] args) {
		Scanner in;
		try {
			in = new Scanner(new File("in2023/prob2023in0.txt"));
			//in = new Scanner(new File("in2023/prob2023in0.txt"));
			int numTimes = 0;
			 
			int count = 0;
			boolean part2 = false;
			String line = "";

			Stack stack = new Stack();
			HashSet set = new HashSet();
			
			
			Hashtable<Long, Integer> trail = new Hashtable<Long, Integer>();
			
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
			
			Hashtable<String, Integer> table = new Hashtable<String, Integer>();
			
			
			table.put(curi + "," + curj, 1);
			
			int maxj= curi;
			int maxi= curj;

			int minj= curi;
			int mini= curj;
			
			
			for(int i=0; i<lines.size(); i++) {
				
				line = lines.get(i);
				sopl(line);
				
				
				String token[] = line.split(" ");
				
				char dir = token[2].charAt(token[2].length() - 2);
				
				String amountHex = token[2].substring(2,7);
				
				
				int amount =hexToInt(amountHex);

				sopl(dir + " " + amount);

				for(int j=0; j<amount; j++) {
					if(dir == 'U'  || dir == '3') {
						
						curi--;
						
					} else if(dir == 'R'  || dir == '0') {
						curj++;
					} else if(dir == 'D'  || dir == '1') {
						curi++;
						
					} else if(dir == 'L'  || dir == '2') {
						curj--;
					}
					table.put(curi + "," + curj, 1);
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
			}
			

			sopl("hello");
			sopl((maxi - mini + 1));
			sopl((maxj - minj + 1));
			
			cur=0L;
			boolean inside = false;
			for(int i=mini-1; i<=maxi+1; i++) {
				sopl(i);
				
				for(int j=minj-1; j<=maxj+1; j++) {
					
					if(table.contains(i + "," + j)) {
						
						inside = !inside;
						
						cur++;
						
					} else if(inside) {
							cur++;
					}
				}
			}
			
			sopl("Answer: " + cur);
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
	public static void exit() {
		exit(0);
	}
	public static void exit(int code) {
		sop("Exit with code " + code);
		
		System.exit(code);
	}

}
