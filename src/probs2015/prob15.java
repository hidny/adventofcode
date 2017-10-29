package probs2015;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class prob15 {

	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2015/prob2015in15.txt"));
			
			int NUM_TEASPOONS = 100;
			long bestScore = 0;
			boolean part2 = true;
			
			boolean combo[];
			int numIngredients = 0;
			
			String line = "";
			
			ArrayList <prob15Ingredient> ing = new ArrayList<prob15Ingredient>();
			
			while(in.hasNextLine()) {
				numIngredients++;
				line = in.nextLine();
				
				ing.add(new prob15Ingredient(line));
				
			}
			
			combo = new boolean[NUM_TEASPOONS + numIngredients - 1];
			for(int i=0; i<combo.length; i++) {
				combo[i] = false;
			}
			for(int i=0; i<numIngredients - 1; i++) {
				combo[i] = true;
			}
			
			int temp[] = new int[0];
			while(combo != null) {
				
				long currentScore = prob15Ingredient.calcScore(ing, getIntArrayFromCombo(combo, numIngredients), part2);
				
				if(currentScore > bestScore) {
					bestScore = currentScore;
					temp = getIntArrayFromCombo(combo, numIngredients);
				}
				
				combo = utilsFromProjectEuler.Combination.getNextCombination(combo);
			}
			
			for(int i=0; i<temp.length; i++) {
				System.out.println(temp[i]);
			}
			
			System.out.println("Answer: " + bestScore);
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	
	public static int[] getIntArrayFromCombo(boolean combo[], int numIngredients) {
		int ret[] = new int[numIngredients];
		
		int current = 0;
		int retInd = 0;
		for(int i=0; i<combo.length; i++) {
			if(combo[i] == true) {
				ret[retInd] = current;
				current = 0;
				retInd++;
			} else {
				current++;
			}
		}
		
		ret[retInd] = current;
		return ret;

	}
	

}
