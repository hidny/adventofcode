package probs2018;

import java.io.File;

import number.IsNumber;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

import utils.Mapping;

public class prob21part2 {

	
	public static void main(String[] args) {
		long r3 = 0;
		
		Scanner in = new Scanner(System.in);

		boolean hit[] = new boolean[(int)Math.pow(2,  24)];

		System.out.println("Start");

		long potentialAnswer = -1;
		
		do {

			potentialAnswer = r3;
			hit[(int)r3] = true;
			
			long r4 = r3;
			if((r3/65536) % 2 == 0) {
				r4+=65536;
			}
			
			//System.out.println(r4);
			//System.out.println(r4);
			r3 = 7041048;
			do {
				
				long r5 = r4 % ((int)Math.pow(2,  8));
			
				r3 += r5;
				
				r3 = r3 % ((int)Math.pow(2,  24));
				r3 = r3 *65899;
				r3 = r3 % ((int)Math.pow(2,  24));
				
				if(r4 < (int)Math.pow(2,  8) ) {
						break;
				}
				
				r4 /= (int)Math.pow(2,  8);
				
			}  while(true);
		
			
			//7402355
			
			System.out.println(r3);
			
			
		} while(hit[(int)r3] == false);
		
		System.out.println("Answer for part 2: " + potentialAnswer);

		
	}

}
