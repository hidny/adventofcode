package probs2016;


import java.io.File;
import java.util.Scanner;

import utils.LetterCount;

public class prob6 {

	public static void main(String args[]) {
		
		
		
		Scanner in;
		try {
			 in = new Scanner(new File("prob6in.txt"));
			 
			 LetterCount array[][] = new LetterCount[8][LetterCount.NUM_LETTERS];
			 for(int i=0; i<array.length; i++) {
				 for(int j=0; j<array[i].length; j++) {
					 array[i][j] = new LetterCount((char)('a' + j));
				 }
			 }
			 
			 String line;
			while(in.hasNextLine()) {
				line = in.nextLine();
				for(int i=0; i<line.length(); i++) {
					array[i][(int)(line.charAt(i) - 'a')].increment();
				}
				
			}
			
			String answer = "";
			int highestIndex;
			for(int i=0; i<array.length; i++) {
				highestIndex = 0;
				for(int j=0; j<array[i].length; j++) {
					if(array[i][highestIndex].number > array[i][j].number && array[i][j].number > 0) {
						highestIndex = j;
					}
				}
				answer += (char)(highestIndex + 'a');
			}
			 
			System.out.println("Answer: " + answer);
			 in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}//Answer: fykjtwyn
