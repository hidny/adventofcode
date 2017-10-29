package probs2015;

import java.io.File;
import java.util.Scanner;

public class prob11 {

	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2015/prob2015in11.txt"));
			
			int count = 0;
			boolean part2 = false;
			String line = "";
			
			while(in.hasNextLine()) {
				line = in.next();
			}
			
			while(true) {
				line = increment(line);
				System.out.println(line);
				
				if(hasIncreasing3(line) == false) {
					continue;
				}
				if(noBadLetters(line) == false) {
					continue;
				}
				
				if(twoDiffOverLetter(line) == false) {
					continue;
				}
				if(part2) {
					count++;
					if(count >= 2) {
						break;
					}
				} else {
					break;
				}
			}
			
			System.out.println("Answer: " + line);
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	
	public static String increment(String line) {
		
		boolean carryTheOne = true;
		String newLine = "";
		
		String prevEndLine = line;
		
		for(int i=prevEndLine.length() - 1; i>=0 && carryTheOne == true; i--) {
			if(prevEndLine.charAt(i) == 'z') {
				carryTheOne = true;
			} else {
				carryTheOne = false;
			}
			if(i > 0) {
				newLine =  prevEndLine.substring(0, i);
			}
			if(carryTheOne) {
				newLine += 'a';
			} else {
				newLine += (char)((int)(prevEndLine.charAt(i)) + 1);
			}
			
			if(i<line.length()-1) {
				newLine += prevEndLine.substring(i+1);
			}
			
			prevEndLine = newLine;
		}
		return newLine;
		
	}

	public static boolean hasIncreasing3(String line) {
	
		for(int i=0; i<line.length() - 2; i++) {
			if((int)(line.charAt(i)) + 1 == (int)(line.charAt(i+1)) && (int)(line.charAt(i)) + 2 == (int)(line.charAt(i+2))) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean noBadLetters(String line) {
		for(int i=0; i<line.length(); i++) {
			if(line.charAt(i) == 'i' || line.charAt(i) == 'o' || line.charAt(i) == 'l') {
				return false;
			}
		}
		return true;
	}
	
	public static boolean twoDiffOverLetter(String line) {
		for(int i=0; i<line.length() - 1; i++) {
			if(line.charAt(i) == line.charAt(i+1)) {

				for(int j=i+2; j<line.length() - 1; j++) {
					if(line.charAt(j) == line.charAt(j+1)) {
						if(line.charAt(i) != line.charAt(j)) {
							return true;
						}
					}
				}
			}
			
		}
		
		return false;
	}
}
