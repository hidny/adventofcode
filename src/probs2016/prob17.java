package probs2016;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class prob17 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in;
		Scanner in2;
		int GRID_SIZE = 4;
		try {
			 in = new Scanner(new File("prob17in.txt"));
			 boolean isPart2 = true;
			 
			 in2 = new Scanner(System.in);
			 String passcode = "";
			 long part2Answer = -1;
			 
			System.out.println("Start");
			 while(in.hasNextLine()) {
				 passcode = in.nextLine();
				 
				 System.out.println(passcode);
			 }
			 
			 prob17obj nextLengthPathList[][] = new prob17obj[GRID_SIZE][GRID_SIZE];

			 prob17obj currentLengthPathList[][] = new prob17obj[GRID_SIZE][GRID_SIZE];
			 
			 for(int i=0; i<nextLengthPathList.length; i++) {
				 for(int j=0; j<nextLengthPathList[i].length; j++) {
					 nextLengthPathList[i][j] = new prob17obj();
					 currentLengthPathList[i][j] = new prob17obj();
				 }
			 }
			 
			 nextLengthPathList[0][0].add(passcode, "");

			 //(U for up, D for down, L for left, and R for right).
			 
			 while((isPart2 == false && currentLengthPathList[GRID_SIZE - 1][GRID_SIZE - 1].getSize() == 0) || isPart2 == true) {
				 
				 if(isPart2 == true && currentLengthPathList[GRID_SIZE - 1][GRID_SIZE - 1].getSize() > 0){
					 part2Answer = currentLengthPathList[GRID_SIZE - 1][GRID_SIZE - 1].getDirection(0).length();
					 System.out.println(part2Answer);
				 }

				 currentLengthPathList = nextLengthPathList;
				 
				 if(stillNotTrapped(currentLengthPathList) == false) {
					 break;
				 }
				 
				
				 nextLengthPathList = new prob17obj[GRID_SIZE][GRID_SIZE];
				 for(int i=0; i<nextLengthPathList.length; i++) {
					 for(int j=0; j<nextLengthPathList[i].length; j++) {
						 nextLengthPathList[i][j] = new prob17obj();
					 }
				 }
				 
				 for(int i=0; i<GRID_SIZE; i++) {
					 for(int j=0; j<GRID_SIZE; j++) {
						 
						 //For part 2:
						 //Once you're out, you don't need to open vaults:
						 if(i == GRID_SIZE - 1 && j == GRID_SIZE - 1) {
							 continue;
						 }
						 //System.out.println("i = " + i+ "  j =" + j);
						 //right and down:
						 if(j<GRID_SIZE-1) {
							 for(int k=0; k<currentLengthPathList[i][j].getSize(); k++) {
								 if(currentLengthPathList[i][j].isRightOpen(k)) {
									 currentLengthPathList[i][j+1].add(passcode, currentLengthPathList[i][j].getDirection(k) + "R");
								 }
								 
							 }
						 }
						 if(i<GRID_SIZE-1) {
							 for(int k=0; k<currentLengthPathList[i][j].getSize(); k++) {
								 //(U for up, D for down, L for left, and R for right).
								 if(currentLengthPathList[i][j].isDownOpen(k)) {
									 currentLengthPathList[i+1][j].add(passcode, currentLengthPathList[i][j].getDirection(k) + "D");
								 }
								 
							 }
						 }
						 //left and up
						 if(j > 0) {
							 for(int k=0; k<currentLengthPathList[i][j].getSize(); k++) {
								 if(currentLengthPathList[i][j].isLeftOpen(k)) {
									 nextLengthPathList[i][j-1].add(passcode, currentLengthPathList[i][j].getDirection(k) + "L");
								 }
								 
							 }
						 }
						 
						 if(i > 0) {
							 for(int k=0; k<currentLengthPathList[i][j].getSize(); k++) {
								 if(currentLengthPathList[i][j].isUpOpen(k)) {
									 nextLengthPathList[i-1][j].add(passcode, currentLengthPathList[i][j].getDirection(k) + "U");
								 }
								 
							 }
						 }
					 }
				 }
			 }
			 
			
			 if(isPart2 == false) {
				 String answer = currentLengthPathList[GRID_SIZE - 1][GRID_SIZE - 1].getDirection(0);
				 
				 System.out.println("Asnwer: " + answer);
			 } else {
				 System.out.println("Answer for part2: " + part2Answer);
			 }
			 in2.close();
			 in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static boolean stillNotTrapped(prob17obj currentLength[][]) {
		boolean ret = false;
		for(int i=0; i<currentLength.length; i++) {
			for(int j=0; j<currentLength[i].length; j++) {
				if(currentLength[i][j].getSize() > 0) {
					//System.out.println("Found (" + currentLength[i][j].getDirection(0) + ") at i="+ i+ " j=" + j);
					ret = true;
				}
			}
			//System.out.println("---------");
		}
		
		return ret;
	}
}

//DUDRLRRDDR
