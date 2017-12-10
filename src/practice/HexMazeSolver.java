package practice;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import aStar.AstarNode;

public class HexMazeSolver {

	//TODO: use this as an excuse to refine your A* algo
	//TODO: just to practice using A*
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		Scanner in;
		try {
			 in = new Scanner(new File("practice/hex7.txt"));
			
			int count = 0;
			boolean part2 = true;
			String line = "";
			
			ArrayList<String> lines = new ArrayList<String>();
			while(in.hasNextLine()) {
				lines.add( in.nextLine());
				
				
				
			}
			
			
			boolean wall[][] = new boolean[lines.size()][lines.get(0).length()];
			
			int starti = -1;
			int startj = -1;
			
			int endi = -1;
			int endj = -1;
			
			
			//Idea just space it out!
			
			//TODO
			
			boolean firstRowShifted = false;
			
			if(lines.get(0).charAt(0) == '>') {
				firstRowShifted = true;
			}
			
			for(int i=0; i<lines.size(); i++) {
				int j=0;
				if(lines.get(i).charAt(0) == '>') {
					j=1;
				}
				int temp = (j + i) % 2;
				if(temp == 1 && firstRowShifted == false) {
					System.out.println("WARNING1");
				} else if(temp == 0 && firstRowShifted == true){
					System.out.println("WARNING2");
				}
				
				
				for(; j<lines.get(i).length(); j+=2) {

					if(lines.get(i).charAt(j) == '#') {
						wall[i][j/2] = true;
						
					} else if(lines.get(i).charAt(j) == 's' || lines.get(i).charAt(j) == 'S') {
						starti = i;
						startj = j/2;
					}else if (lines.get(i).charAt(j) == 'e' || lines.get(i).charAt(j) == 'E' ) {
						endi = i;
						endj = j/2;
					}
				}
			}
			
			
			HexMaze start = new HexMaze(wall, starti, startj, firstRowShifted);
			HexMaze goal = new HexMaze(wall, endi, endj, firstRowShifted);
			
			start.print(endi, endj);
			
			//hex7 expects 2
			//hex6 expects 26
			//hex5 expects 11
			//hex4 expect 144
			//hex3 expect 26
			ArrayList<aStar.AstarNode> answer = aStar.AstarAlgo.astar(start, goal);
			
			
			
			//int numSteps = answer.size() - 1;
			
			System.out.println("Answer: " + aStar.AstarAlgo.astarSize(start, goal));
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}

}
