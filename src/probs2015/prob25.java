package probs2015;

import java.io.File;
import java.util.Scanner;

public class prob25 {

	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2015/prob2015in25.txt"));
			
			int count = 0;
			boolean part2 = true;
			String line = "";
			
			int row =0;
			int column =0;
			
			while(in.hasNextLine()) {
				line = in.nextLine();
				String token[] = line.split(" ");
				
				row = Integer.parseInt(token[16].substring(0,  token[16].length() -1));
				column = Integer.parseInt(token[18].substring(0,  token[18].length() -1));
				
			}
			
			
			int diagNumber = row + column;

			int numTotDiag = (diagNumber * (diagNumber-1))/2;
			
			int totalComputation = numTotDiag - row;
			
			
			long current = 20151125;
			
			for(int k=0; k<totalComputation; k++) {
				current = (current * 252533) % 33554393;
			}
			
			System.out.print("Answer: " + current);
			
			//System.out.println("Answer: " + current);
			in.close();
			
			//18168396
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	

}
