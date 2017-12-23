package probs2017;

import java.io.File;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

import utils.Mapping;

public class prob22b {

	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2017/prob2017in22.txt"));
			
			int count = 0;
			String line = "";

			ArrayList <String>lines = new ArrayList<String>();
			
			
			int LIMIT = 40000;
			byte table[][] = new byte[LIMIT][LIMIT];
			
			
			byte CLEAN = 0;
			byte WEAK = 1;
			byte INF = 2;
			byte FLAG = 3;
			
			//dir: 0 up
			//1 right
			//2 down
			//3 left
			
			while(in.hasNextLine()) {
				line = in.nextLine();
				lines.add(line);
				
				
			}
			
			for(int i=0; i<lines.size(); i++) {
				for(int j=0; j<lines.get(i).length(); j++) {
					if(lines.get(i).charAt(j) == '#') {
						table[i][j] = INF;
					} else {
						table[i][j] = CLEAN;
					}
				}
			}
			
			int posX = (lines.size()/2);
			int posY = (lines.size()/2);
			
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
				table[posY][posX] =(byte) (( table[posY][posX]+1) %4);
				
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
				
			}
			

			sop("ehllo");
			
			
			System.out.println("Answer: " + count);
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	
	public static void sop(String output) {
		System.out.println(output);
	}

}
