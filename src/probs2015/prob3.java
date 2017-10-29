package probs2015;

import java.io.File;
import java.util.Scanner;

public class prob3 {

	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2015/prob2015in3.txt"));
			
			int count = 0;
			boolean part2 = false;
			
			int LIMIT = 1000;
			
			boolean table[][] = new boolean[LIMIT][LIMIT];
			boolean robotable[][] = new boolean[LIMIT][LIMIT];
			
			for(int i=0; i<table.length; i++) { 
				for(int j=0; j<table[0].length; j++) {
					table[i][j] = false;
					robotable[i][j] = false;
				}
			}
			
			int i=0;
			int j=0;
			
			int ir = 0;
			int jr = 0;

			table[i][j] = true;
			
			boolean robotTurn = false;
			
			while(in.hasNextLine()) {
				String dir = in.nextLine();
				
				for(int k=0; k<dir.length(); k++) {
					if(!part2 || robotTurn == false) {
						if(dir.charAt(k) == '^') {
							i--;
						} else if(dir.charAt(k) == 'v') {
							i++;
						} else if(dir.charAt(k) == '>') {
							j++;
						} else if(dir.charAt(k) == '<') {
							j--;
						}
					} else {
						if(dir.charAt(k) == '^') {
							ir--;
						} else if(dir.charAt(k) == 'v') {
							ir++;
						} else if(dir.charAt(k) == '>') {
							jr++;
						} else if(dir.charAt(k) == '<') {
							jr--;
						}
					}
					
					if(Math.abs(i) >= LIMIT/2 || Math.abs(j) >= LIMIT/2 ) {
						System.out.println("WARNING!");
					}
					if(Math.abs(ir) >= LIMIT/2 || Math.abs(jr) >= LIMIT/2 ) {
						System.out.println("WARNING ROBOT!");
					}
					table[(LIMIT + i) % LIMIT][(LIMIT + j) % LIMIT] = true;
					table[(LIMIT + ir) % LIMIT][(LIMIT + jr) % LIMIT] = true;
					
					robotTurn = !robotTurn;
				}
			}
			
			for(int i2=0; i2<table.length; i2++) { 
				for(int j2=0; j2<table[0].length; j2++) {
					if(table[i2][j2]) {
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
	

}
