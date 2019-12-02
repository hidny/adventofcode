package aStar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import HeapTree.HeapTreeMT;


public class AstarAlgo {

	public static final int GOAL_FOUND = -1;
	
	public static int astarSize(AstarNode start, AstarNode goal) {
		 ArrayList<AstarNode>  temp = astar(start, goal);
		 
		 if(temp == null) {
			 return -1;
		 } else {
			 return temp.size() - 1;
		 }
	}
	
	//From pseudo code of:
	//https://en.wikipedia.org/wiki/A*_search_algorithm
	
	public static ArrayList<AstarNode> astar(AstarNode start, AstarNode goal) {
		
		HashSet<AstarNode> closedSet = new HashSet<AstarNode>();
		HashSet<AstarNode> openSet = new HashSet<AstarNode>();
		
		openSet.add(start);
		
		HashMap<AstarNode, AstarNode> cameFrom = new HashMap<AstarNode, AstarNode>();
		

		HashMap<AstarNode, Long> costOfGettingToOpenNode = new HashMap<AstarNode, Long>();
		
		
		costOfGettingToOpenNode.put(start, 0L);
		
		//f(n) = g(n) + h(n)
		// h(n) <= actual cost of getting there.
		
		//fScoreOpenNodes holds the set of open nodes left to explore.
		//Note that it also holds the fScore, put that's only used for debugging purposes.
		
		HashMap<AstarNode, Long> fScoreOpenNodes = new HashMap<AstarNode, Long>();
		HeapTreeMT fScoreQuickMinFinder = new HeapTreeMT();
		
		fScoreOpenNodes.put(start, start.getAdmissibleHeuristic(goal));
		
		fScoreQuickMinFinder.insert(new HeapTree.HeapTreeObject(getPriorityHeuristic(start, goal), start));
		
		AstarNode currentNeighbour;
		
		while(openSet.isEmpty() == false) {
			
			AstarNode current = getLowestFFunctionNode(fScoreOpenNodes, fScoreQuickMinFinder);
			
			
			//Uncomment for fun:
			
			//System.out.println("        ");
			//System.out.println("------------");
			
			//System.out.println("getPositionID: " + current.getPositionID());
			
			//System.out.println(current);
			//System.out.println("Current smallest fscore: " + fScoreOpenNodes.get(current));
			//System.out.println("Current heuristic fscore: " + current.getAdmissibleHeuristic(goal));
			
			//END OF UNCOMMENT
			//if(fScoreOpenNodes.get(current) == 72) {
			//	System.out.println("DEBUG!");
			//}
			
			if((goal != null && current.hashCode() == goal.hashCode())) {
				return reconstruct_path(cameFrom, current);
			
			//Alternative way to show solution. //TODO TEST
			} else if(goal == null && current.getAdmissibleHeuristic(null) == GOAL_FOUND) { // GOAL_FOUND = -1
				return reconstruct_path(cameFrom, current);
			}
			//TESTING
			//System.out.println(current);
			//END TESTING
			openSet.remove(current);
			fScoreOpenNodes.remove(current);
			
			closedSet.add(current);
			
			ArrayList<AstarNode> neighbours = current.getNodeNeighbours();
			
			for(int i=0; i<neighbours.size(); i++) {
				currentNeighbour = neighbours.get(i);
				// Ignore the neighbor which is already evaluated
				if(closedSet.contains(currentNeighbour)) {
					continue;
				}
				
				long tentative_gScore = costOfGettingToOpenNode.get(current) + current.getCostOfMove(currentNeighbour);
				
				if(openSet.contains(currentNeighbour) == false && closedSet.contains(currentNeighbour) == false) {
					openSet.add(currentNeighbour);
					
				} else if(tentative_gScore >= costOfGettingToOpenNode.get(currentNeighbour)){
					//Ignore paths that to a node that aren't the best:
					continue;
				}
				
				// This path is the best until now. Record it!
				if(cameFrom.containsKey(currentNeighbour)) {
					cameFrom.remove(currentNeighbour);
				}
				cameFrom.put(currentNeighbour, current);
				
				if(costOfGettingToOpenNode.containsKey(currentNeighbour)) {
					costOfGettingToOpenNode.remove(currentNeighbour);
				}
				costOfGettingToOpenNode.put(currentNeighbour, tentative_gScore);
				
				if(fScoreOpenNodes.containsKey(currentNeighbour)) {
					fScoreOpenNodes.remove(currentNeighbour);
				}
				fScoreOpenNodes.put(currentNeighbour, tentative_gScore + currentNeighbour.getAdmissibleHeuristic(goal));
				
				fScoreQuickMinFinder.insert(new  HeapTree.HeapTreeObject(tentative_gScore + getPriorityHeuristic(currentNeighbour, goal), currentNeighbour));
			}
		}
		
		//At this point, we could not find the best path:
		return null;
		
	}
	
	//Make the quick min prioritize the admissible heuristic being as small as possible by lowering the heuristic function depending on how deep we are..
	//That way, the A* algo checks the deepest promising nodes first and gets the answer faster.
	
	//The "correct" way to do this is to make the min finder have a defined tie breaker that goes with the deepest node if all else is equal, but that's complicated.
	
	public static double getPriorityHeuristic(AstarNode node, AstarNode goal) {
		double admissibleHeuristic = node.getAdmissibleHeuristic(goal);
		
		return admissibleHeuristic  - 1.0/(100.0 + admissibleHeuristic);
	}

	//TreeMap<Long, AstarNode> minCostOfGettingNodeGetter = new TreeMap<Long, AstarNode>();
	//HashMap<AstarNode, Long> costOfGettingToOpenNode = new HashMap<AstarNode, Long>();
	public static AstarNode getLowestFFunctionNode(HashMap<AstarNode, Long> fScoreOpenNodes, HeapTreeMT fScoreQuickMinFinder) {
		HeapTree.HeapTreeObject temp;
		do {
			temp = ( HeapTree.HeapTreeObject)fScoreQuickMinFinder.extractMin();
			
		} while(fScoreOpenNodes.containsKey(temp.getHeapObject()) == false);
		
		return (AstarNode)temp.getHeapObject();
		
	}
	
	public static ArrayList<AstarNode> reconstruct_path(HashMap<AstarNode, AstarNode> cameFrom, AstarNode goal) {
		ArrayList<AstarNode> revPath = new ArrayList<AstarNode>();
		AstarNode current = goal;
		while(current != null) {
			revPath.add(current);
			
			current = cameFrom.get(current);
			
		}
		
		ArrayList<AstarNode> path = new ArrayList<AstarNode>();
		//reverse the list:
		for(int i=0; i<revPath.size(); i++) {
			path.add(revPath.get(revPath.size() - 1 - i));
		}
		
		
		
		return path;
	}

}
