package probs2017;

import java.io.File;

import utils.Mapping;

import java.util.*;

public class prob13 {

	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2017/prob2017in13.txt"));
			
			int count = 0;
			String line = "";
			
			
			Mapping dict = new Mapping();
			ArrayList <String>lines = new ArrayList<String>();
			
			
			
			while(in.hasNextLine()) {
				line = in.nextLine();
				lines.add(line);
				String token[] = line.split(" ");
				
				dict.set(token[0].substring(0, token[0].length() - 1), Integer.parseInt(token[1]));
				
			}
			
			boolean hitScanner;
			
			int answerb=0;
			for(; true; answerb++) {
				count =0;
				
				hitScanner = false;
				
				for(int i=0; i<dict.size(); i++) {
					
					
					int location = Integer.parseInt(dict.label.get(i));
					
					long scannerLocation = (location  + answerb ) % (2*dict.number.get(i)-2);
					
					
					if(scannerLocation == 0) {
						//System.out.println(location + "x" + dict.number.get(i));
						
						count += location * dict.number.get(i);

						hitScanner = true;
						
						if(answerb > 0) {
							break;
						}
					}
				}
				if(answerb==0) {
					System.out.println("Answer part 1: " + count);
				}
				if(hitScanner == false) {
					break;
				}
			}
			
			System.out.println("Answer part 2: " + answerb);
			
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	//3034
	

}
