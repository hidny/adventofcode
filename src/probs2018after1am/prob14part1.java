package probs2018after1am;

import java.io.File;

import number.IsNumber;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

import utils.Mapping;

public class prob14part1 {

	
	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2018/prob2018in14.txt"));
			
			String line = "";

			
			ArrayList <String>lines = new ArrayList<String>();
			
			while(in.hasNextLine()) {
				line = in.nextLine();
				lines.add(line);
				
				
			}
			
			int NUM_RECIPES = 513401;
			//int NUM_RECIPES = 5;
			//int NUM_RECIPES = 9;
			//int NUM_RECIPES = 18;
			//int NUM_RECIPES = 2018;
			
			ArrayList<Integer> recipes = new ArrayList<Integer>();
			
			recipes.add(3);
			recipes.add(7);
			
			int elf1 = 0;
			int elf2 = 1;
			
			int numMade = 2;
			
			while(numMade < NUM_RECIPES + 15) {
			
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
				
				
				if(nextRecipe2 != -1) {
					recipes.add(nextRecipe2);
					numMade++;
				
				}
				
				elf1 = (elf1 + recipes.get(elf1) + 1) % recipes.size();
				elf2 = (elf2 + recipes.get(elf2) + 1) % recipes.size();
				
				
			//END
			}
			sop("answer");
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
