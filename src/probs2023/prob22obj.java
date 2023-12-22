package probs2023;
import java.io.File;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

import number.IsNumber;
import utils.Mapping;
import utils.Sort;

public class prob22obj {

	public int minZ;
	public int x1;
	public int y1;
	public int z1;
	

	public int x2;
	public int y2;
	public int z2;
	
	public boolean canDisintegrate = true;
	
	public int zLanding = 0;
	
	public static ArrayList<prob22obj> sort(ArrayList<prob22obj> bricks) {
		
		for(int i=0; i<bricks.size(); i++) {
			
			for(int j=i; j<bricks.size(); j++) {
				
				if(bricks.get(i).minZ > bricks.get(j).minZ) {
					prob22obj tmp = bricks.get(i);
					
					bricks.set(i, bricks.get(j));
					bricks.set(j, tmp);
					
					
					
				}
			}
		}
		
		return bricks;
	}
	
	public static boolean intersects(prob22obj a, prob22obj b) {
		
		//System.out.println(a + "vs " + b);
		int minax = Math.min(a.x1, a.x2);
		int maxax = Math.max(a.x1, a.x2);

		int minbx = Math.min(b.x1, b.x2);
		int maxbx = Math.max(b.x1, b.x2);
		
		if(maxax < minbx || maxbx < minax) {

			//System.out.println("False 1: " + maxax + "," +minbx+ "," + maxbx + ","+ minax);
			return false;
		}
		
		int minay = Math.min(a.y1, a.y2);
		int maxay = Math.max(a.y1, a.y2);

		int minby = Math.min(b.y1, b.y2);
		int maxby = Math.max(b.y1, b.y2);
		
		if(maxay < minby || maxby < minay) {
			//System.out.println("False 2");
			return false;
		}
		
		return true;
	}
	
	public String toString() {
		return x1 + "," + y1 + "," + z1 + "~" + x2 + "," + y2 + "," + z2;
	}
	
	public int getHeight() {
		return Math.abs(this.z1 - this.z2) + 1;
	}
}
