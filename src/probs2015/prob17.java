package probs2015;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class prob17 {

	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2015/prob2015in17.txt"));
			
			boolean part2 = false;
			
			ArrayList <Integer>bucket = new ArrayList<Integer>();
			
			while(in.hasNextLine()) {
				bucket.add(Integer.parseInt(in.nextLine()));
			}
			int GOAL_AMOUNT = 150;
			
			if(part2 == false) {
				long oldNumWays[] = new long[GOAL_AMOUNT + 1];
				
				for(int i=0; i<oldNumWays.length; i++) {
					oldNumWays[i] = 0;
				}
				
				oldNumWays[0] = 1;
				
				long currentNumWays[];
				
				
				for(int i=0; i<bucket.size(); i++) {
					
					currentNumWays = new long[GOAL_AMOUNT + 1];
					for(int j=0; j<currentNumWays.length; j++) {
						currentNumWays[j] = oldNumWays[j];
					}
					
					for(int j=0; j<=GOAL_AMOUNT; j++) {
						if(j - bucket.get(i) >= 0) {
							currentNumWays[j] += oldNumWays[j - bucket.get(i)];
							if(j==GOAL_AMOUNT & part2) {
								break;
							}
						}
					}
					
					oldNumWays = currentNumWays;
				}
				
				
				System.out.println("Answer: " + oldNumWays[GOAL_AMOUNT]);
			} else {
			
				int part2MinNumBucket = 0;
	
				int answer = 0;
				
				while(answer == 0) {
					//At this point, it's part 2:
					boolean bucketCombo[] = new boolean[bucket.size()];
					for(int i=0; i<bucketCombo.length; i++) {
						bucketCombo[i] = false;
					}
					for(int i=0; i<part2MinNumBucket; i++) {
						bucketCombo[i] = true;
					}
					
					
					while(bucketCombo != null) {
						
						int tempSum = 0;
						for(int i=0; i<bucketCombo.length; i++) {
							if(bucketCombo[i]) {
								tempSum += bucket.get(i);
							}
						}
						
						if(tempSum == GOAL_AMOUNT) {
							answer++;
						}
						
						
						bucketCombo = utilsPE.Combination.getNextCombination(bucketCombo);
					}
					
					part2MinNumBucket++;
				}

				System.out.println("Part 2 answer: " + answer);
			}
			
			in.close();
			//313265
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	

}
