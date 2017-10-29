package probs2015;

import java.io.File;
import java.util.Scanner;

public class prob12 {

	
	
	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2015/prob2015in12.txt"));
			
			int count = 0;
			String line = "";
			
			int isNeg = 0;
			
			while(in.hasNextLine()) {
				line = in.nextLine();
				int tempNum = 0;
				for(int i=0; i<line.length(); i++) {
					
					if(line.charAt(i) >= '0' && line.charAt(i) <= '9') {
						tempNum = 10 * tempNum + (int)(line.charAt(i) - '0');
					} else if(line.charAt(i) == '-' && tempNum == 0) {
						isNeg = 1;
					} else {
						if(isNeg == 1) {
							count -= tempNum;
							
						} else {

							count += tempNum;
						}
						isNeg = 0;
						tempNum = 0;
					}
					
					//Repeat logic on last loop:
					if(i == line.length() - 1) {
						if(isNeg == 1) {
							count -= tempNum;
							
						} else {

							count += tempNum;
						}
						isNeg = 0;
						tempNum = 0;
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
	

}
