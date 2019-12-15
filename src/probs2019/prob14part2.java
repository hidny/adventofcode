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

public class prob14part2 {

	//tried 444311
	public static void main(String[] args) {
		Scanner in;
		try {
			//495786
			 //in = new Scanner(new File("in2019/prob2019in14.txt"));
			 in = new Scanner(new File("in2019/prob2019in14.txt"));
			int numTimes = 0;
			 
			long count = 0;
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
			
			long prodAmount=1;
			String resultSearch = "FUEL";

			//count = getOreNeeded(lines, resultSearch, 1);
			 extra = new Hashtable<String, Long>();
			//count = getOreNeeded(lines, resultSearch, 46424);
			//sopl(count);
			//exit(1);
			
			
			 //Binary search:
			long min =1;
			long max = (long)Math.pow(10, 12);
			
			long TRIL = (long)Math.pow(10, 12);
			
			long answer = -1;
			while(min < max) {
				long mid = (max + min)/2;
				
				extra = new Hashtable<String, Long>();
				count = getOreNeeded(lines, resultSearch, mid);
				
				if(count < (long)TRIL) {
					if(max == min + 1){
						sopl("Answer: " + mid + " gives " + count);
						break;
					}
					min = mid;
				} else if(count > (long)TRIL) {
					max = mid;
				} else {
					answer = mid;
					break;
				}
				sopl(mid + " gives " + count);
			}
			
			//46423 wrong???
			
			sopl("Answer: " + min);
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}

	
	
	
	public static Hashtable<String, Long> extra;
	
	public static long getOreNeeded(ArrayList <String>lines, String resultSearch, long initAmount) {
		
		long amount = initAmount;
		if(extra.get(resultSearch) == null) {
			extra.put(resultSearch, 0L);
		} else {
			amount -= extra.get(resultSearch);
		}
		
		if(amount <= 0) {
			long temp = extra.get(resultSearch) - initAmount;
			extra.remove(resultSearch);
			extra.put(resultSearch, temp);
			//exit(1);
			return 0L;
		}
		
		for(int i=0; i<lines.size(); i++) {
			String ing[] = lines.get(i).split("=>")[0].split(", ");
			
			String result = lines.get(i).split("=>")[1].trim();
			//sopl("test" + result.split(" ")[1]);
			
			if(result.split(" ")[1].equals(resultSearch)) {
				
				long numReactions = 1 + (amount / pint(result.split(" ")[0]));
				
				if(amount % pint(result.split(" ")[0]) == 0) {
					numReactions--;
				}
				extra.remove(resultSearch);
				extra.put(resultSearch, numReactions*pint(result.split(" ")[0]) - amount);
				
				if(numReactions*pint(result.split(" ")[0]) - amount < 0) {
					sopl("shit");
					exit(1);
				} else if(numReactions*pint(result.split(" ")[0]) - amount >= pint(result.split(" ")[0])) {
					sopl("shit");
					exit(1);
				}
				
				//sopl("Numreactions: " + result + ": " + numReactions);

				long ret = 0;
				
				for(int j=0; j<ing.length; j++) {
					String currentIng = ing[j].trim();
					
					long amountNeeded = numReactions * pint(currentIng.split(" ")[0]);
					
					String newSearch = currentIng.split(" ")[1];
					
					if(newSearch.equals("ORE")) {
						return amountNeeded;
					} else {
						
						long temp = getOreNeeded(lines, newSearch, amountNeeded);
						//sopl("Search: " +amountNeeded + " "+ newSearch + ": " + temp);
						ret += temp;
						
						
					}
				}
				
				return ret;
			}
			
		}
		sopl("couldnt find " + resultSearch);
		exit(1);
		return -1;
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
