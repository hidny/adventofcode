package probs2017;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class prob2 {

	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2017/prob2017in2.txt"));
			
			int count = 0;
			boolean part2 = true;
			String line = "";
			
			while(in.hasNextLine()) {
				line = in.nextLine();
				
				String token[] = line.split("\t");
				
				int largest =Integer.parseInt(token[0]);
				int smallest = Integer.parseInt(token[0]);
				
				ArrayList <Integer> list = new ArrayList<Integer>();
				
				for(int i=0; i<token.length; i++) {
					list.add(Integer.parseInt(token[i]));
					if(part2) {
						for(int j=0; j<i; j++) {
							if(list.get(j) % list.get(i) == 0) {
								count += list.get(j) / list.get(i);
								break;
							} else if(list.get(i) % list.get(j) == 0) {
								count += list.get(i) / list.get(j);
								break;
							}
						}
					} else {
						if(list.get(i) > largest) {
							largest = list.get(i);
						} else if(list.get(i) < smallest) {
							smallest = list.get(i);
						}
					}
					
					
				}
				
				if(part2 == false) {
					count += largest - smallest;
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
