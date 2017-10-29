package probs2015;

import java.io.File;
import java.util.Scanner;

public class prob2 {

	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2015/prob2015in2.txt"));
			
			long count = 0;
			boolean part2 = false;
			
			int temp;
			while(in.hasNextLine()) {
				String split[] = in.nextLine().split("x");
				int a = Integer.parseInt(split[0]);
				int b = Integer.parseInt(split[1]);
				int c = Integer.parseInt(split[2]);
				
				if(a > b) {
					temp = a;
					a = b;
					b = temp;
				}
				
				if(a > c) {
					temp = a;
					a = c;
					c = temp;
				}
				
				if( b > c) {
					temp = b;
					b = c;
					c = temp;
				}
				
				if(part2 == false) {
					//At this point, a < b < c:
					//Surface area:
					count += 2*a*b + 2*a*c + 2*b*c;
					
					//Slack
					count += a*b;
				} else {
					//Around:
					count += 2*a + 2*b;
					
					//volume
					count += a*b*c;
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
