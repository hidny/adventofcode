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

public class prob14part2 {

	
	public static void main(String[] args) {
		try {
			
			
			ArrayList<Integer> recipes = new ArrayList<Integer>();
			
			recipes.add(3);
			recipes.add(7);
			
			int elf1 = 0;
			int elf2 = 1;
			
			//solution doesn't work for leading zeros... oh well
			int target = 513401;
			//int target = 51589;
			//int target = 92510;
			//int target = 59414;
			
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
				
				if(ended(recipes, target)) {
					break;
				}
				
				
				if(nextRecipe2 != -1) {
					recipes.add(nextRecipe2);
					
					if(ended(recipes, target)) {
						break;
					}
				}
				
				elf1 = (elf1 + recipes.get(elf1) + 1) % recipes.size();
				elf2 = (elf2 + recipes.get(elf2) + 1) % recipes.size();
				
				
				//END
			}
			sop("answer");
			
			sopl(recipes.size() - getSizeTarget(target));
			
			
			sopl("");
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	
	public static int getSizeTarget(int target) {
		int size = 0;
		int pow10 = 1;
		while(target / pow10 > 0) {
			size++;
			pow10 *= 10;
		}
		
		return size;
	}
	
	public static boolean ended(ArrayList<Integer> recipes , int target) {
		int temp = target;
		
		for(int i=recipes.size() - 1; temp > 0; i--) {
			if(i < 0) {
				return false;
			}
			if(temp % 10 != recipes.get(i)) {
				return false;
			}
			temp /= 10;
			
		
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
