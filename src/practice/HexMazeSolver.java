package practice;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class HexMazeSolver {

	//TODO: use this as an excuse to refine your A* algo
	//TODO: just to practice using A*
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		Scanner in;
		try {
			 in = new Scanner(new File("practice/hex1.txt"));
			
			int count = 0;
			boolean part2 = true;
			String line = "";
			
			ArrayList<String> lines = new ArrayList<String>();
			while(in.hasNextLine()) {
				lines.add( in.nextLine());

				
			}
			
			
			boolean maze[][] = new boolean[lines.size()][lines.get(0).length()];
			
			int starti;
			int startj;
			
			int endi;
			int endj;
			
			for(int i=0; i<lines.size(); i++) {
				for(int j=0; j<lines.get(i).length(); j++) {

					if(lines.get(i).charAt(j) == '#') {
						maze[i][j] = false;
						
					} else if(lines.get(i).charAt(j) == 's' || lines.get(i).charAt(j) == 'S') {
						starti = i;
						startj = j;
					}else if (lines.get(i).charAt(j) == 'e' || lines.get(i).charAt(j) == 'E' ) {
						endi = i;
						endj = j;
					}
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
