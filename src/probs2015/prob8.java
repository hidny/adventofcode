package probs2015;

import java.io.File;
import java.util.Scanner;

public class prob8 {

	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2015/prob2015in8.txt"));
			
			int count = 0;
			boolean part2 = false;
			
			int length;
			int screenLength;
			String line;
			
			while(in.hasNextLine()) {
				line = in.nextLine();
				
				if(part2 == false) {
					for(int i=0; i<line.length(); i++) {
						if(line.charAt(i) == '\"') {
							count++;
						} else if(line.charAt(i) == '\\') {
								count++;
								if(line.charAt(i+1) == 'x') {
									count = count + 2;
									i +=3;
								} else {
									i++;
								}
						}
					}
				} else {
					count += 2;
					for(int i=0; i<line.length(); i++) {
						if(line.charAt(i) == '\"') {
							count++;
						} else if(line.charAt(i) == '\\') {
								count++;
						}
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
