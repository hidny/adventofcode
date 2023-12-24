package probs2023;
import java.io.File;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

import chineseRemainderTheorem.CRTTuple;
import chineseRemainderTheorem.ChineseRemainderTheoremSolver;
import chineseRemainderTheorem.ChineseRemainderTheoremSolverBigInt;
import number.IsNumber;
import utils.Mapping;
import utils.Sort;

public class prob24b2 {

	//day1 part 1
	//2:38.01
	
	public static void main(String[] args) {
		Scanner in;
		try {
			//in = new Scanner(new File("in2023/prob2023in24.txt"));
			in = new Scanner(new File("in2023/prob2023in0.txt"));
			int numTimes = 0;
			 
			int count = 0;
			boolean part2 = false;
			String line = "";

			LinkedList queue = new LinkedList();
			Stack stack = new Stack();
			HashSet set = new HashSet();
			
			
			Hashtable<Long, Integer> trail = new Hashtable<Long, Integer>();
			
			ArrayList <String>lines = new ArrayList<String>();
			
			
			int LIMIT = 20000;
			int table[][] = new int[LIMIT][LIMIT];
			
			
			//dir: 0 up
			//1 right
			//2 down
			//3 left
			
			while(in.hasNextLine()) {
				line = in.nextLine();
				lines.add(line);
				
			}

			int most = 0;
			int most2 = 0;
			int most3 = 0;
			long cur = 0L;
			
			ArrayList <prob24obj> trajs = new ArrayList <prob24obj>();
			ArrayList ints = new ArrayList<Integer>();
			for(int i=0; i<lines.size(); i++) {
				
				
				line = lines.get(i);
				
				String tokens[] = line.trim().split("@");
				
				String token1[] = tokens[0].trim().split(",");

				String token2[] = tokens[1].trim().split(",");
				
				prob24obj tmp = new prob24obj();
				for(int j=0; j<token1.length; j++) {
					tmp.start[j] = plong(token1[j].trim());
				}
				for(int j=0; j<token2.length; j++) {
					tmp.vel[j] = plong(token2[j].trim());
				}
				
				trajs.add(tmp);
			}
			
			prob24obj potentialSolution = new prob24obj();
			potentialSolution.start[0] = 0;
			potentialSolution.start[1] = 0;
			potentialSolution.start[2] = 0;
			

			potentialSolution.vel[0] = 1;
			potentialSolution.vel[1] = 1;
			potentialSolution.vel[2] = 1;
			
			if(! isSolution(potentialSolution, trajs)) {
				sopl("First proposal no good. (GOOD!)");
			} else {
				sopl("Error in first proposal.");
			}
			potentialSolution.start[0] = 24;
			potentialSolution.start[1] = 13;
			potentialSolution.start[2] = 10;
			

			potentialSolution.vel[0] = -3;
			potentialSolution.vel[1] = 1;
			potentialSolution.vel[2] = 2;
			
			if(isSolution(potentialSolution, trajs)) {
				sopl("Second proposal good. (GOOD!)");
			} else {
				sopl("Error in second proposal.");
			}

			
			sopl("Test get proposed throw:");
			
			prob24obj proposedSolutionTest = getProposedThrow(trajs.get(0), 5, trajs.get(3), 6);
			
			if(isSolution(proposedSolutionTest, trajs)) {
				sopl("third proposal good. (GOOD!)");
			} else {
				sopl("Error in second proposal.");
			}
			
			
			//TODO: get transitions for X, Y, Z
			
			ArrayList <prob24bTransition> transitionX = new ArrayList <prob24bTransition>();
			ArrayList <prob24bTransition> transitionY = new ArrayList <prob24bTransition>();
			ArrayList <prob24bTransition> transitionZ = new ArrayList <prob24bTransition>();
			

			for(int i=0; i<trajs.size(); i++) {
				for(int j=i+1; j<trajs.size(); j++) {
					
					prob24bTransition potentialNewX = prob24obj.getTranstionForTwoTrajectories(trajs, i, j, 0);
					prob24bTransition potentialNewY = prob24obj.getTranstionForTwoTrajectories(trajs, i, j, 1);
					prob24bTransition potentialNewZ = prob24obj.getTranstionForTwoTrajectories(trajs, i, j, 2);
					
					if(potentialNewX != null) {
						transitionX.add(potentialNewX);
					}
					if(potentialNewY != null) {
						transitionY.add(potentialNewY);
					}
					if(potentialNewZ != null) {
						transitionZ.add(potentialNewZ);
					}
				}
			}
			
			//TODO: sort the transitions by time:
			transitionX = sortByTime(transitionX);
			transitionY = sortByTime(transitionY);
			transitionZ = sortByTime(transitionZ);
			
			//TODO: put in sanity check function:
			sopl();
			sopl("X Transitions:");
			for(int i=0; i<transitionX.size(); i++) {
				prob24bTransition tmp =  transitionX.get(i);
				sopl("Index " + tmp.origLesserIndex + " overtakes " + tmp.newLesserIndex + " at time " + tmp.trasitionTime);
			}
			

			sopl();
			sopl("Y Transitions:");
			for(int i=0; i<transitionY.size(); i++) {
				prob24bTransition tmp =  transitionY.get(i);
				sopl("Index " + tmp.origLesserIndex + " overtakes " + tmp.newLesserIndex + " at time " + tmp.trasitionTime);
			}
			

			sopl();
			sopl("Z Transitions:");
			for(int i=0; i<transitionZ.size(); i++) {
				prob24bTransition tmp =  transitionZ.get(i);
				sopl("Index " + tmp.origLesserIndex + " overtakes " + tmp.newLesserIndex + " at time " + tmp.trasitionTime);
			}
			
			//END TODO: put in sanity check function
			
			sopl("Answer: " + cur);
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	
	public static ArrayList <prob24bTransition> sortByTime(ArrayList<prob24bTransition> transitions) {
		
		for(int i=0; i<transitions.size(); i++) {
			for(int j=i+1; j<transitions.size(); j++) {
				
				if(transitions.get(i).trasitionTime > transitions.get(j).trasitionTime) {
					
					prob24bTransition tmp = transitions.get(i);
					
					transitions.set(i, transitions.get(j));
					
					transitions.set(j, tmp);
				}
				
			}
		}
		
		return transitions;
	}
	
	
	public static boolean isSolution(prob24obj potentialSolution, ArrayList <prob24obj> trajs) {
	
		for(int i=0; i<trajs.size(); i++) {
			
			prob24obj current = trajs.get(i);
			
			
			double intersect1[] = prob24obj.getIntersection(current, potentialSolution);
			

			double intersect2[] = prob24obj.getIntersectionXZ(current, potentialSolution);
			
			if(intersect1 == null || intersect2 == null) {
				return false;
			}
			
			if(1001 * intersect1[0]  < 1000* intersect2[0] || 1001 * intersect2[0]  < 1000* intersect1[0]) {
				return false;
			}
			
			double time1 =getTimeOfIntersect(potentialSolution, intersect1[0]);
			double time2 =getTimeOfIntersect(current, intersect1[0]);
			
			//sopl("Times: " + time1 + "vs " +time2);

			if(1001 * time1  < 1000* time2 || 1001 * time2  < 1000* time1) {
				return false;
			}
		}
		
		
		
		return true;
	}
	

	public static double getTimeOfIntersect(prob24obj traj, double intersectX) {
		
		return (intersectX - traj.start[0]) / (1.0 * traj.vel[0]);
	}
	
	
	public static prob24obj getProposedThrow(prob24obj firstObjHit, long time1, prob24obj secondObjHit, long time2) {
		
		if(time1 > time2) {
			
			sopl("ERROR: object 2 gets hit before object 1");
			exit(1);
			return null;
		} else if(time1 == time2) {

			sopl("WARNING: object 2 gets hit at same time as object 1. Maybe delete object 2 and recalc?");
			exit(1);
			return null;
			
		}
		
		
		long timeDelta = time2 - time1;
		
		double firstLocation[] = getLocationObjAtTimeT(firstObjHit, time1);
		double secondLocation[] = getLocationObjAtTimeT(secondObjHit, time2);
		
		double ThrowVel[] = new double[firstLocation.length];
		
		for(int i=0; i<ThrowVel.length; i++) {
			
			double diff = secondLocation[i] - firstLocation[i];
			
			ThrowVel[i] = (diff/(1.0 * timeDelta));
			
		
		}
		
		

		double ThrowPos[] = new double[firstLocation.length];
		
		for(int i=0; i<ThrowVel.length; i++) {
			
			ThrowPos[i] = (firstLocation[i] - ThrowVel[i] * time1);
			

			double ThrowPosSanity = secondLocation[i] - ThrowVel[i] * time2;
			
			if(1001 * ThrowPos[i]  < 1000* ThrowPosSanity || 1001 * ThrowPosSanity  < 1000* ThrowPos[i]) {
				sopl("ERROR: sanity check in getProposedThrow got flagged!");
				exit(1);
			}
		}

		//double newVel[] = 
		
		prob24obj proposedSolution = new prob24obj();

		long ThrowPosLong[] = new long[firstLocation.length];
		long ThrowVelLong[] = new long[firstLocation.length];

		for(int i=0; i<ThrowVel.length; i++) {
			ThrowPosLong[i] = (long)ThrowPos[i];
			ThrowVelLong[i] = (long)ThrowVel[i];
		}
		
		
		proposedSolution.start = ThrowPosLong;
		proposedSolution.vel = ThrowVelLong;
		
		
		return proposedSolution;
	}
	
	public static double[] getLocationObjAtTimeT(prob24obj obj, long time) {
		
		double ret[] = new double[3];
		
		//{obj.start[0] + time * obj.vel[0]};
		
		for(int i=0; i<obj.start.length; i++) {
			ret[i] = obj.start[i] + time * obj.vel[i];
		}
		
		return ret;
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
	
	public static long plong(String s) {
		if (IsNumber.isLong(s)) {
			return Long.parseLong(s);
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
