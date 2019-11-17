package probs2016;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

import aStar.AstarNode;

public class prob13part2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in;
		Scanner in2;
		try {
			 in = new Scanner(new File("prob13in.txt"));
			 in2 = new Scanner(System.in);
			 String line;
			 
			 long register[] = new long[4];
			 for(int i=0; i<register.length; i++) {
				 register[i] = 0;
			 }
			 
			 long input = 0;
			 
			System.out.println("Start");
			 while(in.hasNextLine()) {
				 input =  Integer.parseInt(in.nextLine());
			 }
			 
			 System.out.println("input: " + input);
			 prob13pos.setPuzzleInput(input);
			 //31, 39, 
			 prob13pos.main(null);
			 
			 prob13pos originalPos = new prob13pos(1, 1);
			 
			 HashSet<prob13pos> oldLayer = new HashSet<prob13pos>();
			 HashSet<prob13pos> currentLayer    = new HashSet<prob13pos>();
			 HashSet<prob13pos> futureLayer  = new HashSet<prob13pos>();
			 
			 
			 currentLayer.add(originalPos);
			 
			 Iterator<prob13pos> borderPositions;
			 prob13pos current;
			 
			 ArrayList<AstarNode> neighbours = new ArrayList<AstarNode>();
			 prob13pos tempNeighbour;
			 
			 for(int i=0; i<50; i++) {
				 
				 borderPositions = currentLayer.iterator();
				 futureLayer  = new HashSet<prob13pos>();
				 
				 while(borderPositions.hasNext()) {
					 current = borderPositions.next();
					 
					 neighbours = current.getNodeNeighbours();
					 
					 for(int j=0; j<neighbours.size(); j++) {
						 tempNeighbour = (prob13pos)neighbours.get(j);
						 if(oldLayer.contains(tempNeighbour) == false && currentLayer.contains(tempNeighbour) == false) {
							 futureLayer.add(tempNeighbour);
						 }
					 }
				 }
				 
				 oldLayer.addAll(currentLayer);
				 currentLayer = futureLayer;
			 }
			 
			 System.out.println("Answer: " + (oldLayer.size() + futureLayer.size()));
			 //138
			 
			 in2.close();
			 in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
