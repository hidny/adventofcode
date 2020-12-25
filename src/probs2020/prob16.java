package probs2020;
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
			// in = new Scanner(new File("in2020/prob2020in16.txt"));
			 in = new Scanner(new File("in2020/prob2020in16.txt"));
			int numTimes = 0;
			 
			int count = 0;
			boolean part2 = false;

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
				String line = in.nextLine();
				lines.add(line);
				
				
			}
			
			
			ArrayList ints = new ArrayList<Integer>();
			String token[];

			ArrayList<String> contraints = new ArrayList<String>();
			
			ArrayList<String> validTickets = new ArrayList<String>();
			boolean  getContraints = true;
			boolean nearby = false;
			for(int i=0; i<lines.size(); i++) {
				
				//int temp = Integer.parseInt(lines.get(i));
				//count+=temp;
				
				String line = lines.get(i);
				String lineCopy = String.copyValueOf(line.toCharArray());

				//token = line.split(" ");
				
				if(line.trim().equals("")) {
					getContraints = false;
				}
				
				if(getContraints) {
						contraints.add(line.split(":")[1].trim());
						
						sopl(contraints.get(contraints.size() - 1));
				}
				
				if(line.startsWith("nearby tickets")) {
					nearby = true;
					continue;
				}
				
				
				if(nearby) {
					//sopl(line);
					
					String nums[] = line.split(",");
					
					for(int i2=0; i2<nums.length; i2++) {

						boolean notValidAny = true;
						for(int j=0; j<contraints.size(); j++) {
							if(fits(contraints.get(j), pint(nums[i2]))) {
								notValidAny = false;
								break;
							}
						}
						

						if(notValidAny) {
							count += pint(nums[i2]);
						}
					}
					
				}
			}
			
			
			
			sopl("Answer: " + count);
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	
	public static boolean fits(String contraint, int number) {
		
		String token[] = contraint.split(" or ");
		
		for(int i=0; i<token.length; i++) {
			String token2[] = token[i].split("-");
			int min = pint(token2[0]);
			int max = pint(token2[1]);
			
			if(number >= min && number <= max) {
				return true;
			}
			
		}
		
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
