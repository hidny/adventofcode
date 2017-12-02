package probs2017;

import java.io.File;
import java.util.Scanner;

public class prob1 {

	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2017/prob2017in1.txt"));
			
			int count = 0;
			boolean part2 = false;
			String line = "";
			
			while(in.hasNextLine()) {
				line = in.nextLine();
			}
			
			for(int i=0; i<line.length(); i++) {
				
				if(part2 && line.charAt(i) == line.charAt((i +  line.length()/2) % line.length())) {
					count += (int)((int)line.charAt(i) - '0');
					System.out.println(line.charAt(i));
				} else if(part2 == false && line.charAt(i) == line.charAt((i +  1) % line.length())) {
					count += (int)((int)line.charAt(i) - '0');
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
