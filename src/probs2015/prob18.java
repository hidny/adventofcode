package probs2015;

import java.io.File;
import java.util.Scanner;

public class prob18 {

	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2015/prob2015in18.txt"));
			
			int count = 0;
			boolean part2 = false;
			
			int SIZE = 100;
			int STEPS =100;
			
			int grid[][] = new int[SIZE][SIZE];
			for(int i=0; i<SIZE; i++) {
				for(int j=0; j<SIZE; j++) {
					grid[i][j] = 0;
				}
			}
			
			int lineNumber = 0;
			
			while(in.hasNextLine()) {
				String line = in.nextLine();
				for(int i=0; i<line.length(); i++) {
					if(line.charAt(i) == '#') {
						grid[lineNumber][i] = 1;
					}
				}
				lineNumber++;
			}
			
			for(int i=0; i<STEPS; i++) {
				//printGrid(grid);
				//System.out.println();
				grid = update(grid, part2);
			}

			//printGrid(grid);
			
			for(int i=0; i<grid.length; i++) {
				for(int j=0; j<grid[0].length; j++) {
					if(grid[i][j] > 0) {
						count++;
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
	
	public static void printGrid(int grid[][]) {
		for(int i=0; i<grid.length; i++) {
			for(int j=0; j<grid[0].length; j++) {
				if(grid[i][j] != 0) {
					System.out.print("#");
				} else {
					System.out.print(".");
				}
			}
			System.out.println();
		}
	}
	
	public static int[][] update(int grid[][], boolean part2) {
		int newGrid[][] = new int[grid.length][grid[0].length];
		
		if(part2) {
			grid[0][0] = 1;
			grid[0][newGrid[0].length -1] = 1;
			grid[newGrid.length -1][0] = 1;
			grid[newGrid.length -1][newGrid[0].length -1] = 1;
		}
		
		for(int i=0; i<newGrid.length; i++) {
			for(int j=0; j<newGrid[0].length; j++) {
				int numNeighbours = 0;
				
				if(j>0         &&         i>0                && grid[i-1][j-1] >0) {
					numNeighbours++;
					
				}
				if(                       i>0   &&           grid[i-1][j] >0) {
					numNeighbours++;
					
				}
				
				
				if(j<grid[0].length - 1    && i>0           && grid[i-1][j+1] >0) {
					numNeighbours++;
					
				}
				
				
				if(j>0                                    && grid[i][j-1] >0) {
					numNeighbours++;
					
				}
				if(j<grid[0].length - 1                    && grid[i][j+1] >0) {
					numNeighbours++;
					
				}
				
				if(j>0 &&                  i<grid.length -1   && grid[i+1][j-1] >0) {
					numNeighbours++;
					
				}
				if(                         i<grid.length -1 && grid[i+1][j] >0) {
					numNeighbours++;
					
				}
				
				if(j<grid[0].length - 1 && i<grid.length -1 && grid[i+1][j+1] >0) {
					numNeighbours++;
					
				}
				
				if(grid[i][j] >0 ) {
					if(numNeighbours == 2 || numNeighbours == 3) {
						newGrid[i][j] = 1;
					} else {
						newGrid[i][j] = 0;
					}
				} else {
					if(numNeighbours == 3) {
						newGrid[i][j] = 1;
					} else {
						newGrid[i][j] = 0;
					}
				}
			}
		}
		
		if(part2) {
			newGrid[0][0] = 1;
			newGrid[0][newGrid[0].length -1] = 1;
			newGrid[newGrid.length -1][0] = 1;
			newGrid[newGrid.length -1][newGrid[0].length -1] = 1;
		}
		
		return newGrid;
		
	}

}
