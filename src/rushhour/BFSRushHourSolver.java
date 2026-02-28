package rushhour;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

//Cool video:
//I Solved Klotski
//2swap

//Solver that already exists:
// https://rushhour.froogo.co.uk/
//It also solved the reddit one in 42 moves!

public class BFSRushHourSolver {

	public static void main(String[] args) {

		RushHourState test = getProb1();
		
		RushHourState test27 = Stash.reddit();
		
		solveBFS(test27);
	}
	
	
	public static void solveBFS(RushHourState start) {
		
		HashSet <String> foundNodes = new HashSet<String>();
		
		
		LinkedList <RushHourState> queue = new LinkedList<RushHourState>();
		
		queue.add(start);
		foundNodes.add(start.toString());
		
		boolean foundSol = false;
		
		int debugNumSkip = 0;
		
		int limitMoves = -1;
		
		DONE_BFS:
		while(queue.isEmpty() == false) {
			
			RushHourState cur = queue.poll();
			
			ArrayList<RushHourState> neighbours = cur.getOptions();
			
			for(int i=0; i<neighbours.size(); i++) {
				
				RushHourState nei = neighbours.get(i);
				
				String tmpString = nei.toString();
				if(foundNodes.contains(tmpString)) {

					debugNumSkip++;
					if(debugNumSkip % 100000 == 0) {
						System.out.println("Skipped " + debugNumSkip + " nodes because of duplicates.");
					}
					continue;
				}
				if(nei.hitGoal()) {
					System.out.println("Found solution that is " + nei.depth + " moves!");
					foundSol = true;

					RushHourState.printFullSolutionGivenEndNode(nei);
					
					break DONE_BFS;
					/*//TODO: print the whole thing.
					
					if(limitMoves == -1) {
						limitMoves = nei.depth;
					} else if(nei.depth > limitMoves ) {
						break DONE_BFS;
						
					}*/
				}
				
				foundNodes.add(tmpString);
				
				if(foundNodes.size() % 1000 == 0) {
					System.out.println("Found " + foundNodes.size() + " nodes.");
				}
				
				queue.add(nei);
				
			}
			
			
		}
		
		if(foundSol == false) {
			System.out.println("No solution found!");
		}
		
		
	}
	
	
	public static RushHourState getProb1() {

		RushHourState test = new RushHourState(6);
		
		//Features:
		//Cars are auto-labeled
		//Defaults to car length 2.
		//There's a warning there's an overlap
		
		test.insertCar(0, 0, 3, true);
		test.insertCar(2, 2, true, true);
		test.insertCar(4, 3, true);

		test.insertCar(0, 3, false);
		test.insertCar(0, 4, 3, false);
		test.insertCar(1, 1, false);
		test.insertCar(3, 2, false);
		test.insertCar(4, 5, false);
		
		test.goalJ = 4;
		
		
		return test;
	}

}
