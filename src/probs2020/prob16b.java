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

public class prob16b {

	
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

			ArrayList<String> contraintsNames = new ArrayList<String>();
			ArrayList<String> contraints = new ArrayList<String>();
			
			ArrayList<String> validTickets = new ArrayList<String>();
			boolean  getContraints = true;
			boolean nearby = false;
			boolean yourTicket = false;
			
			String myTicket[] = null;
			
			
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
						contraintsNames.add(line.split(" ")[0].trim());
						sopl(contraints.get(contraints.size() - 1));
				}
				
				
				if(line.startsWith("your ticket")) {
					yourTicket=true;
					continue;
				}
				if(yourTicket) {
					sopl(line);
					myTicket = line.trim().split(",");
					yourTicket = false;
					continue;
				}
				
				if(line.startsWith("nearby tickets")) {
					nearby = true;
					sopl("hello");
					continue;
				}
				
				boolean validTicket = true;
				
				if(nearby) {
					sopl(line);
					
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
							validTicket = false;
							break;
						}
					}
					
					if(validTicket) {
						validTickets.add(line);
						sopl("valid: " + line);
					}
					
				}
			}
			
			
			
			//It's like a sudoku...
			
			
			boolean table2[][] = new boolean[contraintsNames.size()][myTicket.length];
			
			for(int i=0; i<table2.length; i++) {
				for(int j=0; j<table2[0].length; j++) {
					table2[i][j] = false;
				}
			}
			
			long answer = 1;
			for(int i=0; i<contraintsNames.size(); i++) {
					

					for(int c=0; c<myTicket.length; c++) {
						
						boolean stillGood = true;
						
						//myTickets
						for(int r=0; r<validTickets.size(); r++) {
							
							if(!fits(contraints.get(i), pint(validTickets.get(r).split(",")[c]))) {
								stillGood = false;
								break;
							}
							
							
						}

						if(stillGood) {
							table2[i][c] = true;
							answer *= pint(myTicket[c]);
							sopl(contraintsNames.get(i) + "  c:" + c);
						} else {

							table2[i][c] = false;
						}
						
					}
					
			}
			
			for(int i=0; i<table2.length; i++) {
				for(int j=0; j<table2[0].length; j++) {
					if(table2[i][j]) {
						sop("*");
					} else {
						sop("_");
					}
				}
				sopl();
			}
			sopl();

		for(int trial=0; trial<100; trial++) {
			for(int i=0; i<table2.length; i++) {
				
				int numPossible = 0;
				for(int j=0; j<table2[0].length; j++) {
					if(table2[i][j]) {
						numPossible++;
					}
				}
				
				sopl(numPossible);
				if(numPossible == 1) {
					for(int j=0; j<table2[0].length; j++) {
						if(table2[i][j]) {

							for(int k=0; k<table2.length; k++) {
								if(k != i) {
									sopl("False");
									table2[k][j] = false;
								}
							}
						}
					}
				}
			}
		}

		for(int i=0; i<table2.length; i++) {
			for(int j=0; j<table2[0].length; j++) {
				if(table2[i][j]) {
					sop("*");
				} else {
					sop("_");
				}
			}
			sopl();
		}
sopl();


		long answer2 = 1;
		for(int i=0; i<contraintsNames.size(); i++) {
			if(contraintsNames.get(i).startsWith("departure")) {
				
				for(int j=0; j<table2[0].length; j++) {
					if(table2[i][j]) {
						
						answer2 *= pint(myTicket[j]);
						sopl("answer Mult");
					}
				}
			}
		}
		
			//547126557
			
			sopl("Answer2: " + answer2);
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
