package probs;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class prob20part2 {

	//TODO: O(n) solution
	//Do it by hand first
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in;
		Scanner in2;
		try {
			 in = new Scanner(new File("prob20in.txt"));
			 in2 = new Scanner(System.in);
			 String line;
			 ArrayList<String> lines = new ArrayList<String>();
			 
			 
			System.out.println("Start");
			 while(in.hasNextLine()) {
				 line = in.nextLine();
				 lines.add(line);
				 
			 }
			 
			 //I should make it a linked list but meh.
			 ArrayList <Long> mins = new ArrayList<Long>();
			 ArrayList <Long> maxs = new ArrayList<Long>();
			 
			 for(int i=0; i<lines.size(); i++) {
				 mins.add(Long.parseLong(lines.get(i).split("-")[0]));
				 maxs.add(Long.parseLong(lines.get(i).split("-")[1]));
			 }
			 
			 
			 
			 long currentMinRange = 0;
			 long currentMaxRange = (long)Math.pow(2, 32) - 1;
			 long answer = 0;
			 
			 boolean changed;
			 while(currentMinRange < (long)Math.pow(2, 32)) {
				 changed = false;
				 for(int i=0; i<maxs.size(); i++) {
					 if(currentMinRange > maxs.get(i)) {
						 maxs.remove(i);
						 mins.remove(i);
						 i--;
					 } else if(currentMinRange >= mins.get(i) && currentMinRange <= maxs.get(i)) {
						 currentMinRange = maxs.get(i) + 1;
						 currentMaxRange = (long)Math.pow(2, 32) - 1;
						 maxs.remove(i);
						 mins.remove(i);
						 
						 changed = true;
						 i--;
					 } else if(currentMaxRange >= mins.get(i)) {
						 currentMaxRange = mins.get(i) - 1;
						 if(currentMinRange > currentMaxRange) {
							 System.out.println("ERROR: min range bigger than max.");
						 }
					 }
					 
				 }
				 if(changed == false) {
					 System.out.println(currentMinRange + " to " + currentMaxRange);
					 answer += currentMaxRange - currentMinRange + 1;
					 currentMinRange = currentMaxRange + 1;
					 currentMaxRange = (long)Math.pow(2, 32) - 1;
					 
				 }
			 }
			 
			 
			 System.out.println("Asnwer: " + answer);
			 
			 in2.close();
			 in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
