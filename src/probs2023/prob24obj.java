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

public class prob24obj {

	//day1 part 1
	//2:38.01
	
	public long start[] = new long[3];
	public long vel[] = new long[3];
	
	
	public String toString() {
		return start[0] + "," + start[1] + "," + start[2] + " @ " + + vel[0] + "," + vel[1] + "," + vel[2] + ",";
	}
	
	public static boolean isIntersectionInFuture(prob24obj a, double inter[]) {
		
		//double ma = (1.0 * a.vel[1]) / (1.0 * a.vel[0]);
		
		//double ba = 1.0 * a.start[1] - ma * a.start[0];
		
		if(inter[0] > a.start[0] && a.vel[0] > 0) {
			return true;
		} else if(inter[0] < a.start[0] && a.vel[0] < 0) {
			return true;
		}
		
		return false;
	}

	//0: X
	//1: Y
	//2; Z
	
	public static prob24bTransition getTranstionForTwoTrajectories(ArrayList <prob24obj> trajs, int indexi, int indexj, int indexDim) {
		
		prob24obj a = trajs.get(indexi);
		prob24obj b = trajs.get(indexj);
		
		boolean origLesserIndexIsI = true;
		
		if(a.start[indexDim] <= b.start[indexDim]) {
			origLesserIndexIsI = true;
		} else {
			origLesserIndexIsI = false;
		}
	
		if(origLesserIndexIsI) {
			
			if(a.vel[indexDim] <= b.vel[indexDim]) {
				return null;
			} else {
				
				prob24bTransition ret = new prob24bTransition();
				
				ret.origLesserIndex = indexi;
				ret.newLesserIndex = indexj;

				ret.trasitionTime = (1.0 * (b.start[indexDim] - a.start[indexDim])) / (a.vel[indexDim] - b.vel[indexDim]);
				
				return ret;
				
			}
			
		} else {
			
			if(a.vel[indexDim] >= b.vel[indexDim]) {
				return null;
			} else {
				
				prob24bTransition ret = new prob24bTransition();
				
				ret.origLesserIndex = indexj;
				ret.newLesserIndex = indexi;

				ret.trasitionTime = (1.0 * (b.start[indexDim] - a.start[indexDim])) / (a.vel[indexDim] - b.vel[indexDim]);

				return ret;
				
			}
			
		}
		
	}
	
	
	public static double[] getIntersection(prob24obj a, prob24obj b) {
		
		//sopl("Get intersection:");
		//sopl(a);
		//sopl(b);
		
		if(a.vel[0] == 0 && b.vel[0] == 0) {
			
			if(a.start[1] == b.start[1]) {
				exit(1);
				
			} else {
				
				return null;
			}
		}
		if(a.vel[0] == 0) {
			sopl("vert line");
			exit(1);
			//Horizontal line
		}
		
		if(b.vel[0] == 0) {
			//Horizonal line
			sopl("vert line");
			exit(1);
		}
		
		double ma = (1.0 * a.vel[1]) / (1.0 * a.vel[0]);
		
		double ba = 1.0 * a.start[1] - ma * a.start[0];
		

		
		double mb = (1.0 * b.vel[1]) / (1.0 * b.vel[0]);
		
		double bb = 1.0 * b.start[1] - mb * b.start[0];
		
		if(ma == mb) {
			return null;
		}
		
		//sopl(bb + " vs " + ba);
		
		double xInt = (bb - ba) / (ma - mb);
		
		double yInt = ma * xInt + ba;
		
		double yInt2 = mb * xInt + bb;

		/*sopl("Intersect");
		sopl(xInt);
		sopl(yInt);
		sopl(yInt2);
		sopl();*/
		/*if(yInt * 1.0001 < yInt2 || yInt * 0.9999 > yInt2) {
			
			sopl("oops 2!");

			sopl("Intersect");
			sopl(yInt);
			sopl(yInt2);
			sopl(yInt - yInt2);
			exit(1);
		}
		*/
		return new double[] {xInt, yInt};
	}
	
	public static double[] getIntersectionXZ(prob24obj a, prob24obj b) {
		
		//sopl("Get intersection XZ:");
		//sopl(a);
		//sopl(b);
		
		if(a.vel[0] == 0 && b.vel[0] == 0) {
			
			if(a.start[2] == b.start[2]) {
				exit(1);
				
			} else {
				
				return null;
			}
		}
		if(a.vel[0] == 0) {
			sopl("vert line");
			exit(1);
		}
		
		if(b.vel[0] == 0) {
			sopl("vert line");
			exit(1);
		}
		
		double ma = (1.0 * a.vel[2]) / (1.0 * a.vel[0]);
		
		double ba = 1.0 * a.start[2] - ma * a.start[0];
		

		
		double mb = (1.0 * b.vel[2]) / (1.0 * b.vel[0]);
		
		double bb = 1.0 * b.start[2] - mb * b.start[0];
		
		if(ma == mb) {
			return null;
		}
		
		//sopl(bb + " vs " + ba);
		
		double xInt = (bb - ba) / (ma - mb);
		
		double zInt = ma * xInt + ba;
		
		double zInt2 = mb * xInt + bb;

		/*sopl("Intersect");
		sopl(xInt);
		sopl(yInt);
		sopl(yInt2);
		sopl();*/
		/*if(yInt * 1.0001 < yInt2 || yInt * 0.9999 > yInt2) {
			
			sopl("oops 2!");

			sopl("Intersect");
			sopl(yInt);
			sopl(yInt2);
			sopl(yInt - yInt2);
			exit(1);
		}
		*/
		return new double[] {xInt, zInt};
	}

	
	
	public static void sop(Object a) {
		System.out.print(a.toString());
	}
	public static void sopl(Object a) {
		System.out.println(a.toString());
	}

	public static void sopl() {
		System.out.println();
	}
	
	public static int pint(String s) {
		if (IsNumber.isNumber(s)) {
			return Integer.parseInt(s);
		} else {
			sop("Error: (" + s + ") is not a number");
			return -1;
		}
	}
	
	public static void exit() {
		exit(0);
	}
	public static void exit(int code) {
		sop("Exit with code " + code);
		
		System.exit(code);
	}

}
