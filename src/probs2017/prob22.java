package probs2017;

import java.io.File;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

import utils.Mapping;

public class prob22 {

	//https://en.wikipedia.org/wiki/Langton%27s_ant
	
	//The right to do this was with Hashes...
	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2017/prob2017in22.txt"));
			
			int count = 0;
			boolean part2 = false;
			String line = "";

			LinkedList queue = new LinkedList();
			Stack stack = new Stack();
			HashSet set = new HashSet();
			
			Mapping dict = new Mapping();
			ArrayList <String>lines = new ArrayList<String>();
			
			
			int LIMIT = 20000;
			boolean table[][] = new boolean[LIMIT][LIMIT];
			
			Hashtable<Long, Integer> trail = new Hashtable<Long, Integer>();
			
			//dir: 0 up
			//1 right
			//2 down
			//3 left
			
			while(in.hasNextLine()) {
				line = in.nextLine();
				lines.add(line);
				
				
			}
			
			int origCount = 0;
			for(int i=0; i<lines.size(); i++) {
				for(int j=0; j<lines.get(i).length(); j++) {
					if(lines.get(i).charAt(j) == '#') {
						table[i][j] = true;
						origCount++;
					} else {
						table[i][j] = false;
					}
				}
			}
			
			int posX = lines.size()/2;
			int posY = lines.size()/2;
			
			int dir =0;
			
			for(int burst=0; burst<10000; burst++) {
				
				if(table[posY][posX] == true ) {
					dir = (4 + dir + 1) % 4;
				} else {
					dir = (4 + dir - 1) % 4;
					
				}
				if(table[posY][posX] == false) {
					count++;
				}
				table[posY][posX] = !table[posY][posX];
				
				if(dir == 0) {
					posY--;
					if(posY < 0) {
						posY += LIMIT;
					}
				} else if(dir == 1) {
					posX++;
					if(posX >= LIMIT) {
						posX -= LIMIT;
					}
				} else if(dir == 2) {
					posY++;
					if(posY >= LIMIT) {
						posY -= LIMIT;
					}
				} else if(dir == 3) {
					posX--;
					if(posX < 0) {
						posX += LIMIT;
					}
				} else {
					System.out.println("AAAH!!");
					System.exit(1);
				}
				
				
				
				/*for(int i=0; i<LIMIT; i++) {
					for(int j=0; j<LIMIT; j++) {
						if(table[i][j]) {
							System.out.print("#");
						} else {
							System.out.print("");
						}
						System.out.println();
					}
				}
				System.out.println();
				System.out.println();*/
			}
			
			
			
			
			System.out.println("Answer: " + count);
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	

}
