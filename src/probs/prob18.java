package probs;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class prob18 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in;
		Scanner in2;
		int GRID_SIZE = 4;
		try {
			 in = new Scanner(new File("prob18in.txt"));
			 boolean isPart2 = true;
			 
			 in2 = new Scanner(System.in);
			 String line = "";
			 long part2Answer = -1;
			 
			System.out.println("Start");
			 while(in.hasNextLine()) {
				 line = in.nextLine();
				 
				 System.out.println(line);
			 }
			
			 long answer =0;
			 
			 boolean input[] = convertToBoolArray(line);

			 boolean array[][] = new boolean[input.length][input.length];
			 if(input.length < 40) {
				 array = new boolean[input.length][input.length];
			 } else if(isPart2 == false){
				 array = new boolean[40][input.length];
			 } else {
				 array = new boolean[400000][input.length];
				 
			 }
			 array[0] = input;
			 for(int i=1; i<array.length; i++) {
				 array[i] = getNextLine(array[i-1]);
				 /*
				 for(int j=0; j<array[i].length; j++) {
					 if(array[i][j]) {
						 System.out.print("^");
					 } else {
						 System.out.print(" ");
					 }
				 }
				 System.out.println();
				 */
			 }
			 
			 for(int i=0; i<array.length; i++) {
				 for(int j=0; j<array[i].length; j++) {
					 if(array[i][j] == false) {
						 answer++;
					 }
				 }
			 }
			 
			 System.out.println("Length: " + array.length);
			 System.out.println("Area: " + array.length*array[0].length);
			System.out.println("Asnwer: " + answer);
			
			 in2.close();
			 in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static boolean[] convertToBoolArray(String input) {
		boolean ret[] = new boolean[input.length()];
		for(int i=0; i<input.length(); i++) {
			if(input.charAt(i) == '^') {
				ret[i] = true;
			} else {
				ret[i] = false;
			}
		}
		return ret;
	}
	
	public static boolean[] getNextLine(boolean input[]) {
		boolean ret[] = new boolean[input.length];
		boolean left;
		boolean center;
		boolean right;
		for(int i=0; i<ret.length; i++) {
			if(i == 0) {
				left = false;
			} else {
				left = input[i - 1];
			}
			if(i == ret.length - 1) {
				right = false;
			} else {
				right = input[i + 1];
			}
			center = input[i];
			
			/*
			 * its left and center tiles are traps, but its right tile is not.
Its center and right tiles are traps, but its left tile is not.
Only its left tile is a trap.
Only its right tile is a trap.
			 */
			
			if(left && center && right == false) {
				ret[i] = true;
			} else if(center && right && left == false) {
				ret[i] = true;
			} else if(left && center == false && right == false) {
				ret[i] = true;
			} else if(right && left == false && center == false) {
				ret[i] = true;
			} else {
				ret[i] = false;
			}
			
		}
		return ret;
	}
}

//DUDRLRRDDR
