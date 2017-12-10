package probs2017;

import java.io.File;

import java.util.ArrayList;
import java.util.Scanner;

public class prob7obj {

	
	private ArrayList<String> onTop;
	private int weight;
	private String label;
	
	public static ArrayList<prob7obj> list = new ArrayList<prob7obj>();
	
	
	private static int answerStackWeight = -1;
	private static int answer = -1;
	
	public prob7obj(String label, int num, ArrayList <String> onTop) {
		this.weight = num;
		this.onTop = onTop;
		this.label = label;
		
		list.add(this);
	}
	

	public static  void checkWeightsForPart2() {
		prob7obj current;
		int weightTop = 0;
		int currentWeightTop = 0;
		for(int i=0; i<list.size(); i++) {
			current = list.get(i);
			weightTop = 0;
			//System.out.println(list.get(i).label + ":");
			
			for(int j=0; j<current.onTop.size(); j++) {
				currentWeightTop = getObj(current.onTop.get(j)).getWeight();
				if(weightTop == 0) {
					weightTop = currentWeightTop;
				} else if(currentWeightTop == weightTop) {
					//good
					//System.out.println("good");
				} else if(currentWeightTop != weightTop){
					//System.out.println(getObj(current.onTop.get(j)).label);
					if(current.onTop.size() > 2 ) {
						int thirdWeight = 0;
						if( j==1) {
							thirdWeight = getObj(current.onTop.get(j+1)).getWeight();
						} else if(j>1) {
							thirdWeight = getObj(current.onTop.get(j+1)).getWeight();
						}
						
						int potentialAnswer = 0;
						
						if(thirdWeight == currentWeightTop) {
							System.out.println("Stack weight of " + getObj(current.onTop.get(0)).label + " is " + weightTop + " but should be " + thirdWeight);
							
							int diff = weightTop - thirdWeight;
							potentialAnswer =  (getObj(current.onTop.get(0)).weight - diff);
							System.out.println("Weight of " + getObj(current.onTop.get(0)).label + " is " + getObj(current.onTop.get(0)).weight + " but should be " + potentialAnswer);
						} else {
							System.out.println("Stack weight of " + getObj(current.onTop.get(j)).label + " is " + currentWeightTop + " but should be " + thirdWeight);
							
							int diff = currentWeightTop - thirdWeight;
							potentialAnswer = ( getObj(current.onTop.get(j)).weight - diff);
							System.out.println("Weight of " + getObj(current.onTop.get(j)).label + " is " + getObj(current.onTop.get(j)).weight + " but should be " + potentialAnswer);
							
							
						}
						System.out.println();
						
						if(answer == -1 || answerStackWeight > thirdWeight) {
							answerStackWeight = thirdWeight;
							answer = potentialAnswer;
						}
					}
					
				}
			}
			
		}
		
		System.out.println("Final answer: " + answer);
		
		//604 - 8 = 596
		//I manually switched mfzpvpj from 604 to 596 and saw there was no problems!
		//I did a lot of things manually... :(
		//I'll need to clean this up later. (I think I'll keep this copy for prosperity)
		
		
		//2170 or 2162 is smallest (is the smallest contradictory combo)
		//and 2170 is uniquely bad out of all 2162 weights (I check that there was more than 1 for zuahdoy)
		//zuahdoy (14) -> gsqcet, mfzpvpj, ygmtia, nvold
		//And based on standard output I see that the one that's not like the others is mfzpvpj.
		//Also based on standard output I see that mfzpvpj should weight 8 less.
		//So answer = weight(mfzpvpj) - 8 = 604 - 8 = 596
		
		//Goodnight!
		
		//For part 1, I just manually went up the chain.
		//I was like 'programming this is hard', and my assumption that there's only 1 root node seems to be wrong.
		
	}
	//64507
	//64507 or 64499
	//2170 or 2162
	//8670 or 8662
	
	//8662
	//8670
	//2170
	
	//---
	//2162
	
	
	
	public int getWeight() {
		int sum = this.weight;
		for(int i=0; i<this.onTop.size(); i++) {
			sum+= getObj(this.onTop.get(i)).getWeight();
		}
		return sum;
	}
	
	public static prob7obj getObj(String label) {
		//System.out.println("Hello");
		for(int i=0; i<list.size(); i++) {
			//System.out.println(list.get(i).label);
			if(list.get(i).label.equals(label)) {
				return list.get(i);
			}
		}
		
		System.out.println("AHH" + label);
		return null;
		
	}
}
