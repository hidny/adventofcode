package probs2016;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import aStar.AstarAlgo;
import aStar.AstarNode;
import utilsPE.Permutation;

public class prob24 {

	public static void main(String[] args) {
		Scanner in;
		Scanner in2;
		
		int NUM_LOCATIONS = 10;
		
		
		try {
			 in = new Scanner(new File("prob24in.txt"));
			 boolean isPart2 = true;
			 
			 //part1: 518
			 //part2: 716
			 
			 in2 = new Scanner(System.in);
			 String line;
			 ArrayList<String> lines = new ArrayList<String>();
			
			System.out.println("Start");
			 while(in.hasNextLine()) {
				 line = in.nextLine();
				 lines.add(line);
			 }
			 
			 char mapOfDucks[][] = new char[lines.size()][lines.get(0).length()];
			 
			 for(int i=0; i<mapOfDucks.length; i++) {
				 for(int j=0; j<mapOfDucks[0].length; j++) {
					 mapOfDucks[i][j] = lines.get(i).charAt(j);
				 }
			 }
			 
			 //Get DATA:
			 int location[][] = new int[NUM_LOCATIONS][2];
			 
			 boolean set[] = new boolean[NUM_LOCATIONS];
			 for(int i=0; i<NUM_LOCATIONS; i++) {
				 set[i] = false;
			 }
			 
			 for(int i=0; i<mapOfDucks.length; i++) {
				 for(int j=0; j<mapOfDucks[0].length; j++) {
					 if(mapOfDucks[i][j] >= '0' && mapOfDucks[i][j] <= '9') {
						 location[mapOfDucks[i][j] - '0'][0] = j;
						 location[mapOfDucks[i][j] - '0'][1] = i;
						 set[mapOfDucks[i][j] - '0'] = true;
						 
					 }
				 }
			 }
			 
			 //Get actual number of locations (test data has only 5 locations)
			 int realNumLocations = 0;
			 for(int i=0; i<NUM_LOCATIONS; i++) {
				 if(set[i]) {
					 realNumLocations++;
					 System.out.println("Location " + i + " is set.");
				 } else {
					 
				 }
			 }
			 NUM_LOCATIONS = realNumLocations;
			 
			 prob24pos start;
			 prob24pos goal;
			 
			 //Get distances from each node to each other node:
			 
			 int dist[][] = new int[NUM_LOCATIONS][NUM_LOCATIONS];
			 for(int i=0; i<dist.length; i++) {
				 dist[i][i] = 0;
			 }
			 
			 ArrayList<AstarNode> path;
			 
			 for(int i=0; i<realNumLocations; i++) {
				 for(int j=i+1; j<realNumLocations; j++) {
					 if(set[i] &&set[j]) {
						 
						 start = new prob24pos(location[i][0], location[i][1]);
						 prob24pos.setPuzzleInput(mapOfDucks);
						 goal = new prob24pos(location[j][0], location[j][1]);
						 path = AstarAlgo.astar(start, goal);
						 dist[j][i] = path.size() - 1;
						 dist[i][j] = dist[j][i];

					 } else {
						 dist[j][i] = 0;
						 dist[i][j] = 0;
					 }
				 }
			 }
			 
			 
			 //Brute force traveling saleman algo:
			 //Don't count 0, because we already know 0 is at the start:
			 String nodes[] = new String[NUM_LOCATIONS - 1];
			 for(int i=0; i<nodes.length; i++) {
				 nodes[i] = "" + (i+1);
			 }
			 
			 int optimalDist = -1;
			 int currentDist;
			 int indexPrevNode;
			 int indexCurrentNode;
			 
			 String perm[];
			 
			 for(int i=0; i<Permutation.getSmallFactorial(nodes.length) ; i++) {
				 perm = Permutation.generatePermutation(nodes, i);
				 
				 currentDist = 0;
				 //The robot always starts at position 0
				 indexPrevNode = 0;
				 
				 for(int j=0; j<perm.length; j++) {
					 indexCurrentNode = Integer.parseInt(perm[j]);
					 
					 currentDist += dist[indexPrevNode][indexCurrentNode];
					 
					 indexPrevNode = indexCurrentNode;
				 }
				 
				 //Bring it back to where it was:
				 if(isPart2) {
					 currentDist += dist[indexPrevNode][0];
				 }
				 
				 
				 if(optimalDist == -1 || currentDist < optimalDist) {
					 optimalDist = currentDist;
					 System.out.print("0 -> ");
					 for(int j=0; j<perm.length; j++) {
						 System.out.print(" " + perm[j] + "-> ");
					 }
					 System.out.println("( " + optimalDist + ")");
				 }
			 }
			 
			 System.out.println("Answer: " + optimalDist);
			 
			 in2.close();
			 in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
