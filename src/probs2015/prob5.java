package probs2015;

import java.io.File;
import java.util.Scanner;

public class prob5 {

	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2015/prob2015in5.txt"));
			
			int count = 0;
			boolean part2 = false;
			
			while(in.hasNextLine()) {
				String name = in.nextLine();
				
				if(part2 == false) {
					if(hasAtLeast3Vowels(name) && hasLetterAppearTwice(name) && doesntHaveRejectString(name)) {
						System.out.println("Nice: " + name);
						count++;
					} else {
						System.out.println("Naughty " + name);
					}
				} else {
					if(hasLetterAppearTwiceInTwoPlaces(name) && hasLetterRepeatWithOneBetween(name)) {
						System.out.println("Nice: " + name);
						count++;
					} else {
						System.out.println("Naughty " + name);
					}
				}
			}
			
			System.out.println("Answer: " + count);
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	
	
	public static boolean hasAtLeast3Vowels(String line) {
		int numVowels = 0;
		for(int i=0; i<line.length(); i++) {
			if(letterHasVowel(line.charAt(i))) {
				numVowels++;
				if(numVowels >= 3) {
					return true;
				}
			}
		}
		return false;
	}
	
	public static boolean letterHasVowel(char c) {
		if( c == 'a' || c == 'e' || c =='i' || c =='o' || c=='u') {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean hasLetterAppearTwice(String line) {
		for(int i=0; i<line.length() - 1; i++) {
			if(line.charAt(i) == line.charAt(i+1)) {
					return true;
			}			
			
		}
		return false;
	}
	
	public static boolean doesntHaveRejectString(String line) {
		if(line.contains("ab") || line.contains("cd") || line.contains("pq")  || line.contains("xy") ) {
			return false;
		} else {
			return true;
		}
		
	}
	
	
	public static boolean hasLetterAppearTwiceInTwoPlaces(String line) {
		for(int i=0; i<line.length() - 1; i++) {
			for(int j=i+2; j<line.length() - 1; j++) {
				if(line.charAt(i) == line.charAt(j) && line.charAt(i+1) == line.charAt(j+1)) {
					return true;
				}
			}			
			
		}
		return false;
	}
	
	public static boolean hasLetterRepeatWithOneBetween(String line) {
		for(int i=0; i<line.length() - 2; i++) {
			if(line.charAt(i) == line.charAt(i+2)) {
					return true;
			}	
			
		}
		return false;
	}
}