package probs2017;

import java.io.File;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

import utils.Mapping;

public class prob15 {

	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2017/prob2017in15.txt"));
			
			int count = 0;
			String line = "";
			
			
			ArrayList <String>lines = new ArrayList<String>();
		
			int genA = 65;
			int genB = 8921;
			
			int MOD_GEN = 2147483647;
			int NUM_ITER = 40000000;
			
			while(in.hasNextLine()) {
				
				line = in.nextLine();
				lines.add(line);
				String token[] = line.split(" ");
				genA = Integer.parseInt(token[4]);
				

				line = in.nextLine();
				lines.add(line);
				token = line.split(" ");
				genB= Integer.parseInt(token[4]);
				
			}
			long currentA = genA;
			long currentB = genB;
			
			int MOD = (int)Math.pow(2,  16);
			
			//2467
			for(int i=0; i<NUM_ITER; i++) {
				currentA = (16807*currentA) % MOD_GEN;
				currentB = (48271*currentB) % MOD_GEN;
				
				
				if(currentA % MOD == currentB % MOD) {
					count++;
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
