package probs2017;

import java.io.File;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

import utils.Mapping;

public class prob22bASUSED {

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
			
			
			//It barely fit lol!
			int LIMIT = 20000;
			int table[][] = new int[LIMIT][LIMIT];
			
			
			int CLEAN = 0;
			int WEAK = 1;
			int INF = 2;
			int FLAG = 3;
			
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
						table[i][j] = INF;
						origCount++;
					} else {
						table[i][j] = CLEAN;
					}
				}
			}
			
			int posX = lines.size()/2;
			int posY = lines.size()/2;
			
			int dir =0;
			
			for(int burst=0; burst<10000000; burst++) {
				
				if(table[posY][posX] == CLEAN) {
					dir = (4 + dir - 1) % 4;
				} else if(table[posY][posX] == WEAK) {
					
				} else if(table[posY][posX] == INF) {
					dir = (4 + dir + 1) % 4;
				} else if(table[posY][posX] == FLAG) {
					dir = (4 + dir + 2) % 4;
				} else {
					System.out.println("AAHH!");
					System.exit(1);
				}
				
				if(table[posY][posX] == WEAK) {
					count++;
				}
				table[posY][posX] =( table[posY][posX]+1) %4;
				
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
