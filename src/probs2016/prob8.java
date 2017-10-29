package probs2016;
import java.io.File;
import java.util.Scanner;

public class prob8 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in;
		Scanner in2;
		boolean hype = true;
		try {
			if(hype) {
				in = new Scanner(new File("prob0Hype2017-2.txt"));
			} else {
				in = new Scanner(new File("prob8in.txt"));
			}
			 in2 = new Scanner(System.in);
			 String line;
			 
			 int answer = 0;
			 
			 String tokens[];
			 String numbers[];
			 
			 int amt;
				
			 while(in.hasNextLine()) {
				 line = in.nextLine();
				 tokens = line.split(" ");
				 
				 System.out.println(line);
				 
				 if(line.contains("rect")) {
					 numbers =  tokens[1].split("x");
					 rect(Integer.parseInt(numbers[0]), Integer.parseInt(numbers[1]));
				 } else if(line.contains("rotate row")) {
					 int row = Integer.parseInt(tokens[2].split("=")[1]);
					 amt = Integer.parseInt(tokens[4]);
					 rotateRow(row, amt);
				 } else if(line.contains("rotate column")) {
					 int col = Integer.parseInt(tokens[2].split("=")[1]);
					 amt = Integer.parseInt(tokens[4]);
					 rotateCol(col, amt);
				 }
				 
				 /*for(int i=0; i<grid.length; i++) {
					 for(int j=0; j<grid[0].length; j++) {
						 if(grid[i][j]) {
							 System.out.print("#");
							 answer++;
						 } else {
							 System.out.print(".");
							 
						 }
						 
					 }
					 System.out.println();
				 }
				 
				 in2.next();
				 */
			 }
			 
			 for(int i=0; i<grid.length; i++) {
				 for(int j=0; j<grid[0].length; j++) {
					 if(grid[i][j]) {
						 System.out.print("#");
						 answer++;
					 } else {
						 System.out.print(".");
						 
					 }
					 
				 }
				 System.out.println();
			 }
			 
			 System.out.println("Answer: " + answer);
			 in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
			 
	}

	public static boolean grid[][] = new boolean[6][50];
	
	public static void rect(int a, int b) {
		for(int i=0; i<b; i++) {
			for(int j=0; j<a; j++) {
				grid[i][j] = true;
				
			}
		}
	}
	
	public static void rotateCol(int col, int amt) {
		boolean arrayTemp[] = new boolean[grid.length];
		for(int i=0; i<grid.length; i++) {
			arrayTemp[(i + amt)% grid.length] = grid[i][col];
		}
		
		for(int i=0; i<grid.length; i++) {
			grid[i][col] = arrayTemp[i];
		}
	}
	
	public static void rotateRow(int row, int amt) {
		boolean arrayTemp[] = new boolean[grid[0].length];
		for(int j=0; j<grid[0].length; j++) {
			arrayTemp[(j + amt)% grid[0].length] = grid[row][j];
		}
		
		for(int j=0; j<grid[0].length; j++) {
			grid[row][j] = arrayTemp[j];
		}
	}
}
