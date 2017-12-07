package probs2017;

import java.io.File;

import java.util.ArrayList;
import java.util.Scanner;

public class prob7ASUSED {

	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2017/prob2017in7.txt"));
			
			int count = 0;
			boolean part2 = true;
			String line = "";
			ArrayList <String>lines = new ArrayList<String>();
			
			prob7objASUSED temp;
			
			while(in.hasNextLine()) {
				line = in.nextLine();
				String token[] = line.split(" ");
				lines.add(line);
				
				
				int num = Integer.parseInt(token[1].substring(1, token[1].length() -1));
				ArrayList <String> onTop = new ArrayList<String>();
				String current;
				for(int i=3; i<token.length; i++) {
					current = token[i];
					if(token[i].endsWith(",")) {
						current = current.substring(0, current.length() -1);
					}
					
					
					onTop.add(current);
					
				}
				
				temp = new prob7objASUSED(token[0], num, onTop);
				
				
				
			}

			prob7objASUSED.checkWeights();
			
			System.out.println("Answer: " + count);
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	

}
