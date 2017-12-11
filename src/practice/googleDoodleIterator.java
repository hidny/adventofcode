package practice;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class googleDoodleIterator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner in;
		try {
			 in = new Scanner(new File("practiceDoodle/doodle6.txt"));
			
			int count = 0;
			ArrayList<String> lines = new ArrayList<String>();
			
			while(in.hasNextLine()) {
				lines.add(in.nextLine());
			}
			
			int bunnydir = getBunnyDir(lines.get(0));
			int bunnyi = -1;
			int bunnyj = -1;
			
			int grid[][] = new int[lines.size() - 1][lines.get(1).length()];
			
			for(int i=0; i<grid.length; i++) {
				for(int j=0; j<grid[0].length; j++) {
					char cur = lines.get(i+1).charAt(j);
					if(cur == 'W') {
						grid[i][j] = googleDoodleEnv.WALL;
						
					} else if(cur == 'C') {
						grid[i][j] = googleDoodleEnv.CARROT;
						
					} else {
						grid[i][j] = 0;
					}
					if(cur == 'B') {
						bunnyi = i;
						bunnyj = j;
					}
				}
			}
			if(bunnyi  < 0) {
				System.out.println("ERROR: Where\'s the bunny?");
				System.exit(1);
			}
			googleDoodleEnv start = new googleDoodleEnv(grid, bunnyi, bunnyj, bunnydir);
			
			//doodle1
			//googleDoodleEnv end = googleDoodleEnv.playInstructions(new int [] {0, 0, 1, 0, 0}, start);
			//end.print();
			
			//doodle2
			//googleDoodleEnv end = googleDoodleEnv.playInstructions(new int [] {4, 0, 3}, start);
			//end.print();
			
			//doodle3
			//googleDoodleEnv end = googleDoodleEnv.playInstructions(new int [] {4, 0, 0, 1, 3}, start);
			//end.print();
			
			boolean foundSolution = false;
			
			int lengthIns = 0;
			for(; true; lengthIns++) {
				int nextIteration[] = new int[lengthIns * 2];
				
				for(int i=0; i<nextIteration.length; i++) {
					nextIteration[i] = -1;
				}
				
				
				while(nextIteration != null) {
					int instructions[] = clearMinus1(nextIteration);
					nextIteration = iterate(nextIteration);
					
					if(isValidInstructions(instructions) == false) {
						continue;
					}
					
					if(getLengthIntructions(instructions) != lengthIns) {
						continue;
					}
					
					googleDoodleEnv endIter = googleDoodleEnv.playInstructions(instructions, start);
					
					if(endIter.hasCarrots() == false) {
						foundSolution = true;
						
						System.out.println("Solution: ");
						endIter.print();
						System.out.println("Length instructions: " + getLengthIntructions(instructions));
						System.out.println("Sanity check length: " + lengthIns);
						System.out.println("instructions:");
						for(int i=0; i<instructions.length; i++) {
							System.out.println(instructions[i]);
						}
						System.out.println();
					}
				}
				
				if(foundSolution) {
					break;
				}
				
			}
			
			//TODO: 4 doesn't count as an instruction. (in google doodle 1 loop is 1 instruction)
			
			System.out.println("Answer: " + lengthIns);
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}

	public static int getBunnyDir(String dir) {
		if(dir.equals("R")) {
			return 0;
		} else if(dir.equals("U")) {
			return 1;
		} else if(dir.equals("L")) {
			return 2;
		} else if(dir.equals("D")) {
			return 3;
		} else {
			System.out.println("WHAT DIR?");
			System.exit(1);
			return 0;
		}
	}
	
	
	public static int getLengthIntructions(int instructions[]) {
		int ret = 0;
		for(int i=0; i<instructions.length; i++) {
			if(instructions[i] != googleDoodleEnv.START_LOOP4) {
				ret++;
			}
		}
		
		return ret;
	}
	
	public static boolean isValidInstructions(int instructions[]) {
		
		//Loops must be balanced, and end loop doesn't make sense if there's no initiating start loop
		int depth = 0;
		for(int i=0; i<instructions.length; i++) {
			if(instructions[i] == googleDoodleEnv.START_LOOP4) {
				depth++;
			} else if(instructions[i] == googleDoodleEnv.END_LOOP4) {
				depth--;
				if(depth < 0) {
					return false;
				}
			}
		}
		
		//
		if(depth != 0) {
			return false;
		}
		
		//No out-of-range commands
		
		for(int i=0; i<instructions.length; i++) {
				if(instructions[i] < 0 || instructions[i] >= googleDoodleEnv.NUM_INSTRUCTIONS) {
					System.out.println("Error: unknown instuction!");
					System.exit(1);
					return false;
				}
		}
		
		//No empty loops
		for(int i=0; i<instructions.length; i++) {
			if(i+  1 < instructions.length && instructions[i] == googleDoodleEnv.START_LOOP4 && instructions[i+1] == googleDoodleEnv.END_LOOP4) {
				return false;
			}
		}
		
		return true;
	}
	
	
	public static int[] iterate(int a[]) {
		if(a.length == 0) {
			return null;
		}
		int ret[] = new int[a.length];
		for(int i=0; i<a.length; i++) {
			ret[i] = a[i];
		}
		
		for(int i=0; i<ret.length; i++) {
			ret[i]++;
			if(ret[i] >= googleDoodleEnv.NUM_INSTRUCTIONS) {
				if(i == ret.length - 1) {
					return null;
				} else {
					ret[i] = 0;
				}
			} else {
				break;
			}
		}
		return ret;
	}
	
	public static int[] clearMinus1(int currentIns[]) {
		int length =0;
		for(int i=0; i<currentIns.length; i++) {
			if(currentIns[i] != -1) {
				length++;
			} else {
				break;
			}
			
		}
		
		int ret[] = new int[length];
		
		for(int i=0; i<ret.length; i++) {
			ret[i]= currentIns[i];
		}
		return ret;
	}
}
