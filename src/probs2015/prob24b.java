package probs2015;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class prob24b {

	//The clean way to do it is with recursion,
	//But I'm trying to race, so I just made it one BIG function :)
	
	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2015/prob2015in24.txt"));
			
			boolean part2 = true;
			
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
			
			
			int NUMBER_OF_GROUPS = 4;
			
			if(part2 == false) {
				NUMBER_OF_GROUPS = 3;
			}
			
			long answer = -1;
			
			for(int numFirstPackage = 1; answer == -1 && numFirstPackage*NUMBER_OF_GROUPS <= array.length; numFirstPackage++) {
				System.out.println(numFirstPackage);
				boolean comboFirst[] = new boolean[array.length];
				for(int i=0; i<comboFirst.length; i++) {
					comboFirst[i] = false;
				}
				for(int i=0; i<numFirstPackage; i++) {
					comboFirst[i] = true;
				}
				
				NEXT_ENTANGLE:
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
					

					if(quantumEntanglement > answer && answer != -1) {
						comboFirst = utilsFromProjectEuler.Combination.getNextCombination(comboFirst);
						continue;
					}
					
					
					if(tempSum == totalWeight / NUMBER_OF_GROUPS) {
						
						for(int numSecondPackage = numFirstPackage; (NUMBER_OF_GROUPS-1)*numSecondPackage <= array.length - numFirstPackage; numSecondPackage++) {
							
						
						
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
								
								if(tempSum2 == totalWeight / NUMBER_OF_GROUPS) {
									if(NUMBER_OF_GROUPS > 3) {
										
										for(int numThirdPackage = numSecondPackage; (NUMBER_OF_GROUPS-2)*numThirdPackage <= array.length - numFirstPackage - numSecondPackage; numThirdPackage++) {
											
											
											
											boolean comboThird[] = new boolean[array.length - numFirstPackage - numSecondPackage];
											for(int i=0; i<comboThird.length; i++) {
												comboThird[i] = false;
											}
											for(int i=0; i<numThirdPackage; i++) {
												comboThird[i] = true;
											}
											
											while(comboThird != null) {
												int tempSum3 = 0;
												for(int i=0, j=0, k=0; i<array.length; i++) {
													if(comboFirst[i]) {
														continue;
													}
													
													if(comboSecond[j]) {
														j++;
														continue;
													} else {
														j++;
													}
													if(comboThird[k]) {
														tempSum3 += array[k];
													}
													k++;
													
												}
										
												if(tempSum3 == totalWeight / NUMBER_OF_GROUPS) {
													 if(answer == -1 || quantumEntanglement < answer) {
														 answer = quantumEntanglement;
														 
														 comboFirst = utilsFromProjectEuler.Combination.getNextCombination(comboFirst);
														 continue NEXT_ENTANGLE;
													 }
												}
												
												comboThird = utilsFromProjectEuler.Combination.getNextCombination(comboThird);
											}
										}
										
									} else if(answer == -1 || quantumEntanglement < answer) {
										answer = quantumEntanglement;
										
										comboFirst = utilsFromProjectEuler.Combination.getNextCombination(comboFirst);
										continue NEXT_ENTANGLE;
									}
									
								}
								comboSecond = utilsFromProjectEuler.Combination.getNextCombination(comboSecond);
							}
							
							
						}
						
						
					}
					comboFirst = utilsFromProjectEuler.Combination.getNextCombination(comboFirst);
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
