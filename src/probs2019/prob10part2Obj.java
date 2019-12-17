package probs2019;
import java.io.File;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

import number.IsNumber;
import utils.Mapping;
import utils.Sort;
import utilsPE.GCD;

public class prob10part2Obj implements Comparable {

	public int y;
	public int x;
	
	
	//0 up
	//1 right
	//2 down
	//3 left
	public int phase = -1;
	
	//public double degree;
	
	public prob10part2Obj(int i0, int j0, int i, int j) {
		
		//Convert it to the x,y coord that I learned in high-school
		this.y = i0 - i;
		this.x = j - j0;
		
		if(x == 0) {
			if(y > 0) {
				phase = 0;
			} else if(y < 0) {
				phase =2;
			} else {
				System.out.println("ERROR! 3.1");
				System.exit(1);
				
			}
		} else if(x > 0) {
			phase = 1;
		} else if(x < 0) {
			phase = 3;
		} else{
			System.out.println("ERROR!4");
			System.exit(1);
			
		}
	}

	@Override
	public int compareTo(Object o) {
		
		if(phase < ((prob10part2Obj)o).phase) {
			return -1;
		} else if(phase > ((prob10part2Obj)o).phase) {
			return 1;
		}
		
		if(phase == 0 || phase == 2) {
			return 0;
		}
		
		int rotFactor = 1;
		if(phase == 1) {
			rotFactor = 1;
		} else if(phase == 3) {
			rotFactor = -1;
			
		}
		
			if(Math.atan2(rotFactor * y, rotFactor * x) > (Math.atan2(rotFactor * ((prob10part2Obj)o).y, rotFactor * ((prob10part2Obj)o).x))) {
				return -1;
			} else if(Math.atan2(rotFactor * y, rotFactor * x) < (Math.atan2(rotFactor * ((prob10part2Obj)o).y, rotFactor * ((prob10part2Obj)o).x))) {
				return 1;
			} else {
				System.out.println("ERROR! 3");
				System.out.println(this + " vs  " + o);
				System.exit(1);
			}
		
		
		
		return 0;
	}

	public String toString() {
		return x + ", " + y + ": " + phase;
	}
}
