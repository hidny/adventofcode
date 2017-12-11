package practice;

import java.io.File;
import java.util.Scanner;

public class probPractice {

	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2017/prob2017in16.txt"));
			
			int count = 0;
			boolean part2 = true;
			String line = "";
			
			while(in.hasNextLine()) {
				line = in.nextLine();
				String token[] = line.split(" ");
				
				for(int i =0; i<token.length; i++) {
					for(int j=i + 1; j<token.length; j++) {
						if(token[i].equals(token[j])) {
							System.out.println("Match");
							
							
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
