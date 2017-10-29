package probs2015;

import java.io.File;
import java.util.Scanner;

public class prob10a {

	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2015/prob2015in10.txt"));
			
			int count = 0;
			boolean part2 = true;
			String line = "";
			while(in.hasNextLine()) {
				line = in.nextLine();
			}
			
			String current;
			String next = line;
			
			int NUM_TIMES = 40;
			for(int i=0; i<NUM_TIMES; i++) {
				current = next;
				next = "";
				int numTimes = 0;
				for(int j=0; j<current.length(); j+=numTimes) {
					char num = current.charAt(j);
					
					numTimes = 1;
					
					for(int k = j+1; k<current.length(); k++) {
						if(num != current.charAt(k)) {
							break;
						} else {
							numTimes++;
						}
					}
					
					next += ""  + numTimes;
					next += "" + num;
					
				}
				//System.out.println(next);
			}
			
			System.out.println("Answer: " + next.length());
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	

}
