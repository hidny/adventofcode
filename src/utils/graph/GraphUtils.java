package utils.graph;

import java.util.ArrayList;

public class GraphUtils {

	
	//TODO: THIS IS TOTALLY WRONG (BUG DISCOVERED MAY 30th 2020)
	
	//See PositonFilterTests in weirdMathProblems for the correction, but don't copy paste because
	// that function doesn't actually get minWeightSpanningTree
	
	//TODO: TEST (it worked for Advent of code 2019 day 18...)
	public static int getMinWeightSpanningTree(int numVertices, ArrayList<Comparable> edges) {
		int minSpanningTreeWeight = 0;
		Object sortedEdges[] = utils.Sort.sortList(edges);
		
		boolean taken[] = new boolean[numVertices];
		
		int numEdgesNeeded = taken.length - 1;
		
		int numEdgesUsed = 0;
		
		
		boolean connections[][] = new boolean[taken.length][taken.length];
		
		for(int i=0; numEdgesUsed < numEdgesNeeded; i++) {
			GraphEdge tmp = (GraphEdge)sortedEdges[i];
			if(connections[tmp.getI()][tmp.getJ()] == false) {

				//Add edge to tree:
				minSpanningTreeWeight += tmp.getWeigth();
				
				connections[tmp.getI()][tmp.getJ()] = true;
				connections[tmp.getJ()][tmp.getI()] = true;
				
				for(int x=0; x<connections.length; x++) {
					if(connections[tmp.getI()][x] == true) {
						connections[tmp.getJ()][x] = true;
						connections[x][tmp.getJ()] = true;
						
					} else if(connections[tmp.getJ()][x] == true) {
						connections[tmp.getI()][x] = true;
						connections[x][tmp.getI()] = true;
					}
				}
				
				if(taken[tmp.getI()] == false) {
					taken[tmp.getI()] = true;
					
				}
				
				if(taken[tmp.getJ()] == false) {
					taken[tmp.getJ()] = true;
				}
	
				
				numEdgesUsed++;
			}
			
		}
		
		return minSpanningTreeWeight;
	}
	//END TODO TEST
}
