package probs2021;
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

public class prob11 {

	
	public static void main(String[] args) {
		Scanner in;
		try {
			in = new Scanner(new File("in2021/prob2021in11.txt"));
			//in = new Scanner(new File("in2021/prob2021in12.txt"));
			
			int numTimes = 0;
			 
			int count = 0;
			boolean part2 = false;

			LinkedList queue = new LinkedList();
			Stack stack = new Stack();
			HashSet set = new HashSet();
			
			
			Hashtable<Long, Integer> trail = new Hashtable<Long, Integer>();
			
			ArrayList <String>lines = new ArrayList<String>();
			
			
			//int LIMIT = 20000;
			//boolean table[][] = new boolean[LIMIT][LIMIT];
			
			
			//dir: 0 up
			//1 right
			//2 down
			//3 left
			
			while(in.hasNextLine()) {
				String line = in.nextLine();
				lines.add(line);
				
				
			}
			
			
			ArrayList ints = new ArrayList<Integer>();
			
			int energy[][] = new int[lines.size()][lines.get(0).length()];
			
			for(int i=0; i<lines.size(); i++) {
				
				for(int j=0; j<lines.get(i).length(); j++) {
					energy[i][j] = pint(lines.get(i).charAt(j) + "");
				}
				
			}
			
			sopl();
			for(int i=0; i<energy.length; i++) {
				for(int j=0; j<energy[0].length; j++) {
						sop(energy[i][j]);
				}
				sopl();
			}
			sopl();
			
			int newEnergy[][] = new int[lines.size()][lines.get(0).length()];
			for(int step=0; step<10000; step++) {
				
				newEnergy = new int[lines.size()][lines.get(0).length()];
				
				
				for(int i=0; i<energy.length; i++) {
					for(int j=0; j<energy[0].length; j++) {
						newEnergy[i][j] = energy[i][j]+1;
					}
				}
				
				
				boolean progress = true;
				
				boolean activated[][] = new boolean[energy.length][energy[0].length];
				
				while(progress) {
					progress = false;
					
					for(int i=0; i<energy.length; i++) {
						for(int j=0; j<energy[0].length; j++) {
							
							if(newEnergy[i][j] > 9 && activated[i][j] == false) {
								count++;
								progress = true;
								activated[i][j] = true;
								
								for(int i2 = Math.max(0, i-1); i2 <= Math.min(i+1, energy.length - 1); i2++) {
									for(int j2 = Math.max(0, j-1); j2 <= Math.min(j+1, energy[0].length - 1); j2++) {
										if(i2 == i && j2 == j) {
											continue;
											
										} else {
											newEnergy[i2][j2]++;
										}
									}
								}
							}
						}
					}
				}
				
				boolean allflash = true;
				
				for(int i=0; i<energy.length; i++) {
					for(int j=0; j<energy[0].length; j++) {
						if(activated[i][j]) {
							newEnergy[i][j] = 0;
						} else {
							allflash=false;
						}
					}
				}
				
				
				//436
				sopl("Next step:");
				for(int i=0; i<energy.length; i++) {
					for(int j=0; j<energy[0].length; j++) {
						sop(newEnergy[i][j]);
					}
					sopl();
				}
				sopl();
				
				if(allflash) {
					sopl("Answer: " + (step+1));
					exit(1);
				}
				
				energy = newEnergy;
			}
			
			
			
			sopl("Answer: " + count);
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
			sop("Error: (" + s + " is not a number");
			return -1;
		}
	}

	public static long plong(String s) {
		if (IsNumber.isLong(s)) {
			return Long.parseLong(s);
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
