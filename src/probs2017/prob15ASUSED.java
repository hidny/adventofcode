package probs2017;

import java.io.File;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

import utils.Mapping;

public class prob15ASUSED {

	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2017/prob2017in15.txt"));
			
			int count = 0;
			boolean part2 = true;
			String line = "";
			
			LinkedList queue = new LinkedList();
			Stack stack = new Stack();
			HashSet set = new HashSet();
			
			Mapping dict = new Mapping();
			ArrayList <String>lines = new ArrayList<String>();
		
			int genA = 65;
			int genB = 8921;
			
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
			for(int i=0; i<40000000; i++) {
				currentA = (16807*currentA) % 2147483647;
				currentB = (48271*currentB) % 2147483647;
				
				
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
