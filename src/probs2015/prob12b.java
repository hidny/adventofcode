package probs2015;

import java.io.File;
import java.util.Scanner;

public class prob12b {

	public static boolean PART2 = true;
	
	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2015/prob2015in12.txt"));
			
			int count = 0;
			String line = "";
			
			
			while(in.hasNextLine()) {
				line = in.nextLine();
				prob12bParseTree root = new prob12bParseTree(line);
				
				count += root.sumNonRed();
				System.out.println(count);
			}
			
			
			
			System.out.println("Answer: " + count);
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	

}
