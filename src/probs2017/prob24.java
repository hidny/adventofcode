package probs2017;

import java.io.File;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

import utils.Mapping;

public class prob24 {

	public static void main(String[] args) {
		Scanner in;
		try {
			in = new Scanner(new File("in2017/prob2017in24.txt"));

			String line = "";

			ArrayList <String>lines = new ArrayList<String>();
			
			ArrayList<Integer> sideA = new ArrayList<Integer>();
			ArrayList<Integer> sideB = new ArrayList<Integer>();
			
			while(in.hasNextLine()) {
				line = in.nextLine();
				lines.add(line);
				String token[] = line.split("/");
				sideA.add(Integer.parseInt(token[0]));
				sideB.add(Integer.parseInt(token[1]));
				
				
			}
			
			
			int answer = getBestWeight(sideA, sideB);
			
			
			System.out.println("Answer: " + answer);
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	
	public static int getBestWeight(ArrayList<Integer> sideA, ArrayList<Integer> sideB) {
		int currentWeight = 0;
		
		int currentPort = 0;
		
		boolean used[] = new boolean[sideA.size()];
		
		
		return getBestWeight(sideA, sideB, 0, used, currentWeight, currentPort);
	}
	
	public static int getBestWeight(ArrayList<Integer> sideA, ArrayList<Integer> sideB, int depth, boolean used[], int startWeight, int currentPort) {

		int currentIndex = 0;
		
		int bestWeight = startWeight;
		
		while(true) {

			int currentWeight = startWeight;
			
			currentIndex = getNextIndexPort(currentIndex, currentPort, used, sideA, sideB);
			
			if(currentIndex == -1) {
				break;
			}
			
			used[currentIndex] = true;
			int otherSidePort = getOtherSidePort(currentPort, currentIndex, sideA, sideB);
			
			currentWeight += getWeight(currentIndex, sideA, sideB);
			
			currentWeight = getBestWeight(sideA, sideB, depth+1, used, currentWeight, otherSidePort);
			
			if(currentWeight > bestWeight) {
				bestWeight = currentWeight;
				//for(int i=0; i<depth; i++) {
				//	sop("\t");
				//}
				//sopl(sideA.get(currentIndex) + "/" + sideB.get(currentIndex));
			}

			used[currentIndex] = false;
			currentIndex++;
		}
		
		return bestWeight;
	}
	
	//start search at currentIndex:
	public static int getNextIndexPort(int currentIndex, int numPorts, boolean used[], ArrayList<Integer> sideA, ArrayList<Integer> sideB) {
		
		for(int i=currentIndex; i<sideA.size(); i++) {
			if(used[i]) {
				continue;
			}
			for(int j=0; j<2; j++) {
				if(j == 0) {
					if(sideA.get(i) == numPorts) {
						return i;
					}
				} else if(j == 1) {
					if(sideB.get(i) == numPorts) {
						return i;
					}
				}
			}
		}
		//-1 no solution
		return -1;
	}
	
	public static int getOtherSidePort(int prevPort, int indexUsed, ArrayList<Integer> sideA, ArrayList<Integer> sideB) {
		if(sideA.get(indexUsed) == prevPort) {
			return sideB.get(indexUsed);
		} else {
			return sideA.get(indexUsed);
		}
	}
	
	public static int getWeight(int indexUsed, ArrayList<Integer> sideA, ArrayList<Integer> sideB) {
		return sideA.get(indexUsed)  + sideB.get(indexUsed);
	}
	
	

	public static void sopl() {
		sopl("");
	}
	
	public static void sopl(String out) {
		System.out.println(out);
	}

	public static void sop(String out) {
		System.out.print(out);
	}
	

}
