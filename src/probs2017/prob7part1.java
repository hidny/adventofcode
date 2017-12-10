package probs2017;

import java.io.File;

import java.util.ArrayList;
import java.util.Scanner;

public class prob7part1 {

	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2017/prob2017in7.txt"));
			
			String line = "";
			ArrayList <String>lines = new ArrayList<String>();
		
			utils.Mapping dict = new utils.Mapping();
			
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
					
					dict.set(current, 0);
				}
				
				if(onTop.size() > 0 && dict.has(token[0]) == false) {
					dict.set(token[0], num);
				}
				
				
				
			}
			
			String answer = "";
			for(int i=0; i<dict.size(); i++) {
				if(dict.number.get(i) > 0) {
					answer = dict.label.get(i);
				}
			}
			
			System.out.println("Answer: " + answer);
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	

}
