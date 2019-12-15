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

public class prob14 {

	//tried 444311
	public static void main(String[] args) {
		Scanner in;
		try {
			//495786
			 //in = new Scanner(new File("in2019/prob2019in14.txt"));
			 in = new Scanner(new File("in2019/prob2019in14.txt"));
			 
			int count = 0;
			String line = "";

			
			Hashtable<Long, Integer> trail = new Hashtable<Long, Integer>();
			
			ArrayList <String>lines = new ArrayList<String>();
			
			
			int LIMIT = 20000;
			boolean table[][] = new boolean[LIMIT][LIMIT];
			
			
			while(in.hasNextLine()) {
				line = in.nextLine();
				lines.add(line);
				
				
			}
			
			String resultSearch = "FUEL";

			count = getOreNeeded(lines, resultSearch, 1);
			
			
			sopl("Answer: " + count);
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}

	
	
	
	public static Hashtable<String, Integer> extra = new Hashtable<String, Integer>();
	
	public static int getOreNeeded(ArrayList <String>lines, String resultSearch, int initAmount) {
		
		int amount = initAmount;
		if(extra.get(resultSearch) == null) {
			extra.put(resultSearch, 0);
		} else {
			amount -= extra.get(resultSearch);
		}
		
		if(amount <= 0) {
			int temp = extra.get(resultSearch) - initAmount;
			extra.remove(resultSearch);
			extra.put(resultSearch, temp);
			//exit(1);
			return 0;
		}
		
		for(int i=0; i<lines.size(); i++) {
			String ing[] = lines.get(i).split("=>")[0].split(", ");
			
			String result = lines.get(i).split("=>")[1].trim();
			//sopl("test" + result.split(" ")[1]);
			
			if(result.split(" ")[1].equals(resultSearch)) {
				
				int numReactions = 1 + (amount / pint(result.split(" ")[0]));
				
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

				int ret = 0;
				
				for(int j=0; j<ing.length; j++) {
					String currentIng = ing[j].trim();
					
					int amountNeeded = numReactions * pint(currentIng.split(" ")[0]);
					
					String newSearch = currentIng.split(" ")[1];
					
					if(newSearch.equals("ORE")) {
						return amountNeeded;
					} else {
						
						int temp = getOreNeeded(lines, newSearch, amountNeeded);
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
