package probs2015;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import utilsFromProjectEuler.Permutation;

public class prob9 {

	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2015/prob2015in9.txt"));
			
			int count = 0;
			boolean part2 = false;
			
			ArrayList<String> locations = new ArrayList<String>();
			long table[][] = new long[100][100];
			while(in.hasNextLine()) {
				addDistance(locations, table, in.nextLine());
			}
			
			String origPermute[] = new String[locations.size()];
			
			for(int i=0; i<locations.size(); i++) {
				origPermute[i] = "" + i;
			}
			
			System.out.println(locations.size());
			//TODO: try all permutations
			long numPermutations = Permutation.getSmallFactorial(locations.size());
			String currentPermute[];
			
			long bestDistance = 0;
			if(part2 == false) {
				bestDistance = 100000;
				long currentDistance;
				for(int i=0; i<numPermutations; i++) {
					currentDistance = 0;
					currentPermute = Permutation.generatePermutation(origPermute, i);
					for(int j=0; j<currentPermute.length -1; j++) {
						currentDistance += table[Integer.parseInt(currentPermute[j])][Integer.parseInt(currentPermute[j+1])];
					}
					
					if(currentDistance < bestDistance) {
						bestDistance = currentDistance;
					}
				}
			} else {
				bestDistance = 0;
				long currentDistance;
				for(int i=0; i<numPermutations; i++) {
					currentDistance = 0;
					currentPermute = Permutation.generatePermutation(origPermute, i);
					for(int j=0; j<currentPermute.length -1; j++) {
						currentDistance += table[Integer.parseInt(currentPermute[j])][Integer.parseInt(currentPermute[j+1])];
					}
					
					if(currentDistance > bestDistance) {
						bestDistance = currentDistance;
					}
				}
			}
			//permutation.generatePermutation(origPermute, 0);
						
			
			System.out.println("Answer: " + bestDistance);
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	
	public static void addDistance(ArrayList<String> locations, long table[][], String line) {
		String token[] = line.split(" ");
		
		String label1 = token[0];
		String label2 = token[2];
		long distance = Long.parseLong(token[4]);
		
		int index1 = -1;
		int index2 = -1;
		
		if(getIndexLabel(locations, label1) != -1) {
			index1 = getIndexLabel(locations, label1);
		} else {
			index1 = locations.size();
			locations.add(label1);
		}

		if(getIndexLabel(locations, label2) != -1) {
			index2 = getIndexLabel(locations, label2);
		} else {
			index2 = locations.size();
			locations.add(label2);
		}
		
		table[index1][index2] = distance;
		table[index2][index1] = distance;
		
	}
	
	public static int getIndexLabel(ArrayList<String> locations, String label) {
		for(int i=0; i<locations.size(); i++) {
			if(locations.get(i).equals(label)) {
				return i;
			}
		}
		return -1;
	}
}
