package probs2015;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import utilsPE.Permutation;

public class prob13 {

	public static int happy[][] = new int[26][26];
	
	public static int numPeople = 0;
	
	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2015/prob2015in13.txt"));
			
			int bestHappy = 0;
			boolean part2 = false;
			
			while(in.hasNextLine()) {
				parseHappyLine(in.nextLine());
			}
			
			String list[] = new String[numPeople];
			
			for(int i=0; i<list.length; i++) {
				list[i] = "" + listPeople.get(i);
				System.out.println(list[i]);
			}
			
			long numPerm = Permutation.getSmallFactorial(numPeople);
			
			String currentPerm[];
			int currentHappy;
			
			for(int i=0; i<numPerm; i++) {
				currentPerm = Permutation.generatePermutation(list, i);
				currentHappy = 0;
				
				for(int j=0; j<numPeople; j++) {
					
					if(part2 == false) {
						currentHappy += happy[getIndex(currentPerm[j])][getIndex(currentPerm[(j+1)%numPeople])];
						currentHappy += happy[getIndex(currentPerm[(j+1)%numPeople])][getIndex(currentPerm[j])];
					} else {
						if(j<numPeople - 1) {
							currentHappy += happy[getIndex(currentPerm[j])][getIndex(currentPerm[(j+1)])];
							currentHappy += happy[getIndex(currentPerm[(j+1)])][getIndex(currentPerm[j])];
						}
					}
				}
				if(currentHappy > bestHappy) {
					bestHappy = currentHappy;
				}
			}
			
			
			System.out.println("Answer: " + bestHappy);
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	

	public static void parseHappyLine(String line) {
		String tokens[] = line.split(" ");
		int index1 = getIndex(tokens[0]);
		int index2 = getIndex(tokens[10].substring(0, tokens[10].length() -1));
		
		numPeople = Math.max(numPeople, Math.max(index1, index2) + 1);
		
		if(tokens[2].equals("gain")) {
			happy[index1][index2] = Integer.parseInt(tokens[3]);
		} else {
			happy[index1][index2] = -Integer.parseInt(tokens[3]);
		}
	}
	
	public static ArrayList <String> listPeople = new ArrayList<String>();
	
	public static int getIndex(String name) {
		for(int i=0; i<listPeople.size(); i++) {
			if(name.equals(listPeople.get(i))) {
				return i;
			}
		}
		listPeople.add(name);
		return listPeople.size()-1;
	}
}
