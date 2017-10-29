package probs2015;

import java.io.File;
import java.util.Scanner;

public class prob1 {

	public static void main(String[] args) {
		Scanner in;
		boolean part2 = false;
		try {
			 in = new Scanner(new File("in2015/prob2015in1.txt"));
			
			int count = 0;
			
			String input = in.nextLine();
			
			for(int i=0; i<input.length(); i++) {
				if(input.charAt(i) == '(' ) {
					count++;
				} else if(input.charAt(i) == ')') {
					
					count--;
					if(part2 && count == -1) {
						System.out.println("Answer part 2: " + (i+1));
						break;
					}
				}
			}
			
			while(in.hasNextInt()) {
				
			}
			
			System.out.println("Answer: " + count);
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	

}
