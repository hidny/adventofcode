package probs;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class prob22 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in;
		Scanner in2;
		try {
			 in = new Scanner(new File("prob22in.txt"));
			 in2 = new Scanner(System.in);
			 String line;
			 
			 int x;
			 int y;
			 
			 int size;
			 int used;
			 int avail;
			 
			 String token[];
			 
			prob22Cluster orig = new prob22Cluster();
			 
			boolean isPart1 = false;
			
			System.out.println("Start");
			 while(in.hasNextLine()) {
				 line = in.nextLine();
				 
				 token = line.split(" ");
				 
				 if(line.startsWith("/dev/grid/")) {
					 x = Integer.parseInt(token[0].split("-")[1].substring(1));
					 y = Integer.parseInt(token[0].split("-")[2].substring(1));
					 
					 nextToken:
					 for(int i=1; i<token.length; i++) {
						 for(int j=i; j<token.length; j++) {
							 if(token[j].equals("") == false) {
								 token[i] = token[j];
								 token[j] = "";
								 continue nextToken;
							 }
						 }
					 }
					 
					 //Take away the T:
					 for(int i=1; i<=3; i++) {
						 token[i] = token[i].substring(0, token[i].length() -1 );
					 }
					 size = Integer.parseInt(token[1]);
					 used = Integer.parseInt(token[2]);
					 avail = Integer.parseInt(token[3]);
					 
					 orig.setXYComp(x, y, size, used, avail);
					 System.out.println(line);
					 System.out.println(orig.getArray()[y][x]);
				 }
			 }
			 
			 orig.removeNullComps();
			 
			 if(isPart1) {
				 //part 1:
				 //O(n^2) instead of O(n*log(n)) because I'm lazy
				
				 long answer = 0;
				 ArrayList<prob22comp> node = orig.getArrayList();
				 
				 for(int i=0; i<node.size(); i++) {
					 if(node.get(i).isUsedZero() == false) {
						 for(int j=0; j<node.size(); j++) {
							 if(i==j) {
								 //Nodes A and B are not the same
								 continue;
							 }
							 
							 if(node.get(i).fitsInside(node.get(j))) {
								 answer++;
							 }
						 }
					 }
				 }
				 System.out.println(node.size());
				 System.out.println("Asnwer: " + answer);

			 } else {
				 orig.printCluster();
				 orig.printMoves();
				 orig.printNodesThatCantMove();
				 
				 ArrayList<prob22comp> voids = orig.getVoidsCouldBeFilled();

				 orig.setOrigGoalProb2();
				 
				 ArrayList<prob22comp> goalNeighbours = orig.getGoalNeighbours();
				 
				 for(int i=0; i<voids.size(); i++) {
					 System.out.println("void: " + voids.get(i).toString2());
				 }
				 
				 System.out.println();
				 for(int i=0; i<goalNeighbours.size(); i++) {
					 System.out.println("goal Neighbour: " + goalNeighbours.get(i).toString2());
				 }
			 }
			
			 
			 
			 in2.close();
			 in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
