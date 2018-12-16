package probs2018;

import java.io.File;

import number.IsNumber;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

import utils.Mapping;

public class prob14part2 {

	
	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2018/prob2018in14.txt"));
			
			int count = 0;
			boolean part2 = false;
			String line = "";

			LinkedList queue = new LinkedList();
			Stack stack = new Stack();
			HashSet set = new HashSet();
			
			Mapping dict = new Mapping();
			ArrayList <String>lines = new ArrayList<String>();
			
			
			int LIMIT = 20000;
			boolean table[][] = new boolean[LIMIT][LIMIT];
			
			Hashtable<Long, Integer> trail = new Hashtable<Long, Integer>();
			
			//dir: 0 up
			//1 right
			//2 down
			//3 left
			
			while(in.hasNextLine()) {
				line = in.nextLine();
				lines.add(line);
				
				
			}
			//score
			//1 + score current recipe
			
			
			int origCount = 0;
			for(int i=0; i<lines.size(); i++) {
				
			}
			
			//513401
			
			ArrayList<Integer> recipes = new ArrayList<Integer>();
			
			recipes.add(3);
			recipes.add(7);
			
			int elf1 = 0;
			int elf2 = 1;
			
			int NUM_RECIPES = 513401;
			int roundNumber = 1;
			int numMade = 2;
			
			int target = 513401;
			
			//while(numMade < NUM_RECIPES + 15) {
			
			while(true) {
				//START get next recipes.
				int nextRecipeCombined;
				
				nextRecipeCombined = recipes.get(elf1) + recipes.get(elf2);
				
				
				int nextRecipe1 = -1;
				int nextRecipe2 = -1;
				if( nextRecipeCombined >= 10) {
					nextRecipe1 = 1;
					nextRecipe2 = nextRecipeCombined % 10;
				} else {
					nextRecipe1 = nextRecipeCombined;
				}
				
				recipes.add(nextRecipe1);
				numMade++;
				if(ended(recipes, target)) {
					break;
				}
				
				
				if(nextRecipe2 != -1) {
					recipes.add(nextRecipe2);
					numMade++;
					if(ended(recipes, target)) {
						break;
					}
				}
				
				elf1 = (elf1 + recipes.get(elf1) + 1) % recipes.size();
				elf2 = (elf2 + recipes.get(elf2) + 1) % recipes.size();
				
				/*for(int i=0; i<recipes.size(); i++) {
					if(elf1 == i) {
						System.out.print("(" + recipes.get(i) + ")");
					} else if(elf2 == i) {
						System.out.print("[" + recipes.get(i) + "]");
						
					} else {
						System.out.print(" " + recipes.get(i) + " ");
					}
				}
				sopl("");
				*/
			//END
			}
			sop("answer");
			sopl(recipes.size() - 6);
			for(int i=NUM_RECIPES; i<NUM_RECIPES + 10; i++) {
				System.out.print(recipes.get(i));
			}
			
			sopl("");
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	
	public static boolean ended(ArrayList<Integer> recipes , int target) {
		int temp = target;
		
		for(int i=recipes.size() - 1; temp > 0; i--) {
			if(temp % 10 != recipes.get(i)) {
				return false;
			}
			temp /= 10;
			
			//hack
			if(i ==0) {
				return false;
			}
		}
		return true;
	}
	
	public static void sop(Object a) {
		System.out.println(a.toString());
	}
	public static void sopl(Object a) {
		System.out.println(a.toString());
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
