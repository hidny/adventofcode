package aStarPrio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import HeapTree.HeapTreeMT;


public class AstarAlgo {

	
	//From pseudo code of:
	//https://en.wikipedia.org/wiki/A*_search_algorithm
	
	//pre: have a defined .equals
	//AND a defined getHashCode
	//Or else it wont work :(
	public static ArrayList<AstarNode> astar(AstarNode start, AstarNode goal) {
		
		HashSet<AstarNode> closedSet = new HashSet<AstarNode>();
		HashSet<AstarNode> openSet = new HashSet<AstarNode>();
		
		openSet.add(start);
		
		HashMap<AstarNode, AstarNode> cameFrom = new HashMap<AstarNode, AstarNode>();
		

		HashMap<AstarNode, Long> costOfGettingToOpenNode = new HashMap<AstarNode, Long>();
		
		
		costOfGettingToOpenNode.put(start, 0L);
		
		//f(n) = g(n) + h(n)
		
		HashMap<AstarNode, Long> fScoreOpenNodes = new HashMap<AstarNode, Long>();
		HeapTreeMT fScoreQuickMinFinder = new HeapTreeMT();
		
		fScoreOpenNodes.put(start, start.getAdmissibleHeuristic());
		
		fScoreQuickMinFinder.insert(new HeapTree.HeapTreeObject(getPrioHeuristic(start), start));
		
		AstarNode currentNeighbour;
		
		while(openSet.isEmpty() == false) {
			
			AstarNode current = getLowestFFunctionNode(fScoreOpenNodes, fScoreQuickMinFinder);
			
			//Uncomment for fun:
			
			System.out.println("        ");
			System.out.println("------------");
			
			System.out.println("Current smallest fscore: " + fScoreOpenNodes.get(current));
			System.out.println("hashCode: " + current.hashCode());
			
			System.out.println(current);
			
			if(fScoreOpenNodes.get(current) == 72) {
				System.out.println("DEBUG!");
			}
			
			if(current.hashCode() == goal.hashCode()) {
				return reconstruct_path(cameFrom, current);
			}
			
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
				fScoreOpenNodes.put(currentNeighbour, tentative_gScore + currentNeighbour.getAdmissibleHeuristic());
				
				fScoreQuickMinFinder.insert(new  HeapTree.HeapTreeObject(tentative_gScore + getPrioHeuristic(currentNeighbour), currentNeighbour));
			}
		}
		
		//At this point, we could not find the best path:
		return null;
		
	}
	
	//Make the heuristic prioritise being as small as possibe.
	//That way, the A* algo checks the deepest promissing nodes first and gets the answer faster.
	//This hack works if the answer is integer and there are many answers that are the same.
	public static double getPrioHeuristic(AstarNode node) {
		return node.getAdmissibleHeuristic() - 1.0/(1.0 * node.getAdmissibleHeuristic());
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
	/*
	 * AstarNode ret = null;
		AstarNode next;
		Iterator<AstarNode> iter =  set.iterator();
		
		while(iter.hasNext()) {
			next = iter.next();
			if(ret == null) {
				ret = next;
			} else if(costOfGettingToNode.get(ret) > costOfGettingToNode.get(next)) {
				ret = next;
			}
		}
		
		return ret;

*/
	 
	
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
