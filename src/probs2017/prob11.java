package probs2017;

import java.io.File;

import java.util.ArrayList;
import java.util.Scanner;

import utils.Mapping;

public class prob11 {
	
	//HEX GRID

	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2017/prob2017in11.txt"));
			
			int count = 0;
			boolean part2 = false;
			
			String line = "";
			Mapping dict = new Mapping();
			ArrayList <String>lines = new ArrayList<String>();
			
			while(in.hasNextLine()) {
				line = in.nextLine();
				lines.add(line);
				String token[] = line.split(",");
				
				int x =0;
				int y=0;
				int z=0;
				for(int i=0; i<token.length; i++) {
					if(token[i].equals("nw")) {
						x--;
					} else if(token[i].equals("n")) {
						y--;
					} else if(token[i].equals("ne")) {
						x++;
						y--;
					} else if(token[i].equals("se")) {
						x++;
					} else if(token[i].equals("s")) {
						y++;
					} else if(token[i].equals("sw")) {
						x--;
						y++;
					} else {
						System.out.println("WTF");
						System.out.println(token[i]);
						System.exit(1);
					}
					if(part2) {
						int temp = 0;
						if(x > 0 && y < 0 || x<0 && y>0 ){
							temp = Math.min( Math.abs(x), Math.abs(y));
						}
						
						int a1 = Math.abs(x) +Math.abs(y) - temp;
						
						if(a1 > count) {
							count = a1;
						}
					}
				}
				
				System.out.println(x);
				System.out.println(y);
				
				if(part2 == false) {
					int temp = 0;
					if(x > 0 && y < 0 || x<0 && y>0 ){
						temp = Math.min( Math.abs(x), Math.abs(y));
					}
					
					int a1 = Math.abs(x) +Math.abs(y) - temp;
					
					if(a1 > count) {
						count = a1;
					}
				}
				
				
				System.out.println("Answer: " + count);
				
			}
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	

}
