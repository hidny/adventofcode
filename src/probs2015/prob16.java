package probs2015;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import utils.Mapping;

public class prob16 {

	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2015/prob2015in16.txt"));
			
			boolean part2 = true;
			
			Mapping ticker = new Mapping();
			ticker.addLabel("children", 3);
			ticker.addLabel("cats", 7);
			ticker.addLabel("samoyeds", 2);
			ticker.addLabel("pomeranians", 3);
			ticker.addLabel("akitas", 0);
			ticker.addLabel("vizslas", 0);
			ticker.addLabel("goldfish", 5);
			ticker.addLabel("trees", 3);
			ticker.addLabel("cars", 2);
			ticker.addLabel("perfumes", 1);
			
			while(in.hasNextLine()) {
				String line = in.nextLine();
				String token[] = line.split(" ");
				
				String label;
				int number;
				boolean match = true;
			
				
				for(int i=2; i<token.length; i=i+2) {
					
					label = token[i].substring(0, token[i].length() -1);
					
					if(i+2 >= token.length) {
						number = Integer.parseInt(token[i+1]);
					} else {
						number = Integer.parseInt(token[i+1].substring(0, token[i+1].length() -1));
					}
					
					
					if(part2 == true && (label.equals("cats") || label.equals("trees"))) {
						if(ticker.getNumberFromLabel(label) >= number) {
							match = false;
							break;
						}
					} else if(part2 == true && (label.equals("pomeranians") || label.equals("goldfish"))) {
						if(ticker.getNumberFromLabel(label) <= number) {
							match = false;
							break;
						}
					} else {
						if(ticker.getNumberFromLabel(label) != number) {
							match = false;
							break;
						}
					}
				}
				
				if(match == true) {
					System.out.println("Answer: " + token[1]);
				}
				
				
			}
			
			in.close();
			//313265
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	

}
