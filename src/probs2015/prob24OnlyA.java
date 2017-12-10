package probs2015;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class prob24OnlyA {

	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2015/prob2015in24.txt"));
			
			
			ArrayList<String> pack = new ArrayList<String>();
			while(in.hasNextLine()) {
				pack.add(in.nextLine());
			}
			
			int totalWeight = 0;
			int array[] = new int[pack.size()];
			for(int i=0; i<array.length; i++) {
				array[i] = Integer.parseInt(pack.get(i));
				totalWeight += array[i];
			}
			
			
			
			long answer = -1;
			
			for(int numFirstPackage = 1; answer == -1 && numFirstPackage*3 <= array.length; numFirstPackage++) {
				System.out.println(numFirstPackage);
				boolean comboFirst[] = new boolean[array.length];
				for(int i=0; i<comboFirst.length; i++) {
					comboFirst[i] = false;
				}
				for(int i=0; i<numFirstPackage; i++) {
					comboFirst[i] = true;
				}
				
				while(comboFirst != null) {
					//Get sum combo first and entanglement.
					int tempSum = 0;

					long quantumEntanglement = 1;
					
					for(int i=0; i<array.length; i++) {
						if(comboFirst[i]) {
							tempSum += array[i];
							quantumEntanglement *= array[i];
						}
					}
					
					if(tempSum == totalWeight / 3) {
						
						for(int numSecondPackage = numFirstPackage; 2*numSecondPackage <= array.length - numFirstPackage; numSecondPackage++) {
							
						
						
							boolean comboSecond[] = new boolean[array.length - numFirstPackage];
							for(int i=0; i<comboSecond.length; i++) {
								comboSecond[i] = false;
							}
							for(int i=0; i<numSecondPackage; i++) {
								comboSecond[i] = true;
							}
							
							while(comboSecond != null) {
								int tempSum2 = 0;
								for(int i=0, j=0; i<array.length; i++) {
									if(comboFirst[i]) {
										continue;
									}
									
									if(comboSecond[j]) {
										tempSum2 += array[i];
									}
									j++;
									
								}
								
								if(tempSum2 == totalWeight / 3) {
									if(answer == -1 || quantumEntanglement < answer) {
										answer = quantumEntanglement;
									}
									
								}
								comboSecond = utilsPE.Combination.getNextCombination(comboSecond);
							}
							
							
						}
						
						
					}
					comboFirst = utilsPE.Combination.getNextCombination(comboFirst);
				}
			}
			
			System.out.println("Answer: " + answer);
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	

}
