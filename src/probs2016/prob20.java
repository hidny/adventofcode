package probs2016;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class prob20 {

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
			 
			 
			 
			 long answer = 0;
			 
			 boolean changed;
			 while(true) {
				 changed = false;
				 for(int i=0; i<maxs.size(); i++) {
					 if(answer > maxs.get(i)) {
						 maxs.remove(i);
						 mins.remove(i);
						 i--;
					 } else if(answer >= mins.get(i) && answer <= maxs.get(i)) {
						 answer = maxs.get(i) + 1;
						 maxs.remove(i);
						 mins.remove(i);
						 changed = true;
						 i--;
					 }
					 
				 }
				 if(changed == false) {
					 break;
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
