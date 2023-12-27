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
			in = new Scanner(new File("in2023/prob2023in24.txt"));
			//in = new Scanner(new File("in2023/prob2023in0.txt"));
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
			
			long biggestVel = 0L;
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
					
					if(tmp.vel[j] > biggestVel) { 
						biggestVel = tmp.vel[j];
					}
				}
				
				trajs.add(tmp);
			}
			
			sopl(biggestVel);
			//exit(1);
			
			//TODO: get transitions for X, Y, Z
			
			ArrayList <prob24bTransition> transitionXNextSmaller[] = new ArrayList[trajs.size()];
			ArrayList <prob24bTransition> transitionYNextSmaller[] = new ArrayList[trajs.size()];
			ArrayList <prob24bTransition> transitionZNextSmaller[] = new ArrayList[trajs.size()];

			
			ArrayList <prob24bTransition> transitionXNextBigger[] = new ArrayList[trajs.size()];
			ArrayList <prob24bTransition> transitionYNextBigger[] = new ArrayList[trajs.size()];
			ArrayList <prob24bTransition> transitionZNextBigger[] = new ArrayList[trajs.size()];
			

			for(int i=0; i<trajs.size(); i++) {
					transitionXNextSmaller[i] = new ArrayList<prob24bTransition>();
					transitionYNextSmaller[i] = new ArrayList<prob24bTransition>();
					transitionZNextSmaller[i] = new ArrayList<prob24bTransition>();

					transitionXNextBigger[i] = new ArrayList<prob24bTransition>();
					transitionYNextBigger[i] = new ArrayList<prob24bTransition>();
					transitionZNextBigger[i] = new ArrayList<prob24bTransition>();
			}

			for(int i=0; i<trajs.size(); i++) {
				for(int j=i+1; j<trajs.size(); j++) {
					
					prob24bTransition potentialNewX = prob24obj.getTranstionForTwoTrajectories(trajs, i, j, 0);
					prob24bTransition potentialNewY = prob24obj.getTranstionForTwoTrajectories(trajs, i, j, 1);
					prob24bTransition potentialNewZ = prob24obj.getTranstionForTwoTrajectories(trajs, i, j, 2);
					
					if(potentialNewX != null) {
						transitionXNextSmaller[potentialNewX.origLesserIndex].add(potentialNewX);
						transitionXNextBigger[potentialNewX.newLesserIndex].add(potentialNewX);
					}
					if(potentialNewY != null) {
						transitionYNextSmaller[potentialNewY.origLesserIndex].add(potentialNewY);
						transitionYNextBigger[potentialNewY.newLesserIndex].add(potentialNewY);
					}
					if(potentialNewZ != null) {
						transitionZNextSmaller[potentialNewZ.origLesserIndex].add(potentialNewZ);
						transitionZNextBigger[potentialNewZ.newLesserIndex].add(potentialNewZ);
					}
				}
			}
			
			for(int i=0; i<trajs.size(); i++) {
				transitionXNextSmaller[i] = (ArrayList <prob24bTransition>)sortByTime((transitionXNextSmaller[i]));
				transitionXNextBigger[i] = (ArrayList <prob24bTransition>)sortByTime((transitionXNextBigger[i]));

				transitionYNextSmaller[i] = (ArrayList <prob24bTransition>)sortByTime((transitionYNextSmaller[i]));
				transitionYNextBigger[i] = (ArrayList <prob24bTransition>)sortByTime((transitionYNextBigger[i]));

				transitionZNextSmaller[i] = (ArrayList <prob24bTransition>)sortByTime((transitionZNextSmaller[i]));
				transitionZNextBigger[i] = (ArrayList <prob24bTransition>)sortByTime((transitionZNextBigger[i]));
			}
			//TODO: sort the transitions by time:

			
			//END TODO: put in sanity check function
			
			//Start with getting viable 1st hits and times:
			
			sopl();
			getViableFirstHitLocationAndTimes(trajs,
					transitionXNextSmaller,
					transitionXNextBigger,
					transitionYNextSmaller,
					transitionYNextBigger,
					transitionZNextSmaller,
					transitionZNextBigger
				);
			
			
			sopl("Answer: " + cur);
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	
	public static void sanityTestTestProb(ArrayList <prob24obj> trajs) {

		
		
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
	}
	
	
	public static void getViableFirstHitLocationAndTimes(ArrayList <prob24obj> trajs,
			ArrayList <prob24bTransition> transitionXNextSmaller[],
			ArrayList <prob24bTransition> transitionXNextBigger[],
			ArrayList <prob24bTransition> transitionYNextSmaller[],
			ArrayList <prob24bTransition> transitionYNextBigger[],
			ArrayList <prob24bTransition> transitionZNextSmaller[],
			ArrayList <prob24bTransition> transitionZNextBigger[]) {
		
		
		int MAX_VEL = 1000;
		
		
		
		for(int i=0; i<(int)Math.pow(2, 3); i++) {
			
			sopl();
			sopl("Direction index attempt: " + i);

			boolean usefulXMaxes[] = getVelocitiesThatActuallyExistInSameDir(trajs, 0, MAX_VEL, i);
			boolean usefulYMaxes[] = getVelocitiesThatActuallyExistInSameDir(trajs, 1, MAX_VEL, i);
			boolean usefulZMaxes[] = getVelocitiesThatActuallyExistInSameDir(trajs, 2, MAX_VEL, i);

			for(int xVelMax = MAX_VEL; xVelMax >= 1; xVelMax--) {
				
				if(!usefulXMaxes[xVelMax]) {
					continue;
				}
				sopl(xVelMax);
				
				for(int yVelMax = MAX_VEL; yVelMax >= 1; yVelMax--) {
					

					if(!usefulYMaxes[yVelMax]) {
						continue;
					}
					
					for(int zVelMax = MAX_VEL; zVelMax >= 1; zVelMax--) {
						

						if(!usefulZMaxes[zVelMax]) {
							continue;
						}
						
						getViableFirstHitLocationAndTimes(trajs,
								transitionXNextSmaller,
								transitionXNextBigger,
								transitionYNextSmaller,
								transitionYNextBigger,
								transitionZNextSmaller,
								transitionZNextBigger,
								i,
								new int[] {xVelMax, yVelMax, zVelMax});
					}
				}
			}
			
		}
	}
	
	public static boolean[] getVelocitiesThatActuallyExistInSameDir(ArrayList <prob24obj> trajs, int indexDim, int MAX_VEL, int dirIndex) {
		
		boolean ret[] = new boolean[MAX_VEL + 1];
		boolean isVelocityPositivePerDim[] = { (dirIndex & 4) != 0,  (dirIndex & 2) != 0, (dirIndex & 1) != 0};


		for(int i=0; i<ret.length; i++) {
			
			if(i == MAX_VEL) {
				ret[i]= true;
			} else {
				ret[i] = false;
			}
			
		}
		
		for(int i=0; i<trajs.size(); i++) {
			
			if( (trajs.get(i).vel[indexDim] >=0 && isVelocityPositivePerDim[indexDim])
					|| (trajs.get(i).vel[indexDim] < 0 && ! isVelocityPositivePerDim[indexDim])
					){
				ret[(int)Math.abs(trajs.get(i).vel[indexDim])] = true;
			}
		}
		
		return ret;
	}
	
	public static void getViableFirstHitLocationAndTimes(ArrayList <prob24obj> trajs,
			ArrayList <prob24bTransition> transitionXNextSmaller[],
			ArrayList <prob24bTransition> transitionXNextBigger[],
			ArrayList <prob24bTransition> transitionYNextSmaller[],
			ArrayList <prob24bTransition> transitionYNextBigger[],
			ArrayList <prob24bTransition> transitionZNextSmaller[],
			ArrayList <prob24bTransition> transitionZNextBigger[],
			int dirIndex,
			int maxVelPerDim[]) {
		
		boolean isVelocityPositivePerDim[] = { (dirIndex & 4) != 0,  (dirIndex & 2) != 0, (dirIndex & 1) != 0};

		ArrayList <prob24bTransition> transitionXToUse[];
		ArrayList <prob24bTransition> transitionYToUse[];
		ArrayList <prob24bTransition> transitionZToUse[];
		
		if(isVelocityPositivePerDim[0]) {
			transitionXToUse = transitionXNextSmaller;
		} else {
			transitionXToUse = transitionXNextBigger;
		}

		if(isVelocityPositivePerDim[1]) {
			transitionYToUse = transitionYNextSmaller;
		} else {
			transitionYToUse = transitionYNextBigger;
		}
		
		if(isVelocityPositivePerDim[2]) {
			transitionZToUse = transitionZNextSmaller;
		} else {
			transitionZToUse = transitionZNextBigger;
		}
		
		/*sopl();
		sopl();
		sopl("Attempting following velocity type:");
		sopl(isVelocityPositivePerDim[0] + ", " + isVelocityPositivePerDim[1] + ", " + isVelocityPositivePerDim[2]);
		

		if(! isVelocityPositivePerDim[0] && isVelocityPositivePerDim[1] && isVelocityPositivePerDim[2]) {
			//This one should have an example solution
			sopl("This iteration includes the example solution");
		}
		*/
		double currentTime = 0.0;
		
		int minMaxIndexes[] = new int[3];
		//int minMaxIndexes2[] = new int[3];
		
		for(int i=0; i<minMaxIndexes.length; i++) {
			
			if(isVelocityPositivePerDim[i]) {

				minMaxIndexes[i] = getMinStartIndexForDim(trajs, i, maxVelPerDim, isVelocityPositivePerDim);
			} else {

				minMaxIndexes[i] = getMaxStartIndexForDim(trajs, i, maxVelPerDim, isVelocityPositivePerDim);
			}
			
			if(minMaxIndexes[1] == -1) {
				return;
			}
		}
		
		//TODO: they better not overtake at time 0.0
		
		boolean currentlyViableCandidate = false;
		
		
		//Check at the very start:
		/*if(theresAnObjectThatCouldBeFirstHit(minMaxIndexes1)) {
			
			currentlyViableCandidate = true;
			sopl("At time " + currentTime + ", obj index " + minMaxIndexes1[0] + " became a viable candidate");
		}*/
		
		//Debug:
		
		/*sopl("dirIndex = " + dirIndex);
		for(int i=0; i<3; i++) {
			sopl(currentTime + ": " + minMaxIndexes[i]);
		}
		sopl();
		*/
			
		for(int ix=0, iy = 0, iz = 0; ix < transitionXToUse[minMaxIndexes[0]].size() || iy < transitionYToUse[minMaxIndexes[1]].size() || iz < transitionZToUse[minMaxIndexes[2]].size(); ) {
			
			prob24bTransition proposalNextX = null;
			prob24bTransition proposalNextY = null;
			prob24bTransition proposalNextZ = null;
			
			if(ix < transitionXToUse[minMaxIndexes[0]].size()) {
				proposalNextX = transitionXToUse[minMaxIndexes[0]].get(ix);
			}

			if(iy < transitionYToUse[minMaxIndexes[1]].size()) {
				proposalNextY = transitionYToUse[minMaxIndexes[1]].get(iy);
			}
			
			if(iz < transitionZToUse[minMaxIndexes[2]].size()) {
				proposalNextZ = transitionZToUse[minMaxIndexes[2]].get(iz);
			}
			
			int nextDimTransition = 0;
			double curShortestTime = -1.0;
			if(proposalNextX != null) {
				
				nextDimTransition = 0;
				
				curShortestTime = proposalNextX.trasitionTime;
				
			}
			
			if(proposalNextY != null) {
				
				if(curShortestTime < -0.5) {
					nextDimTransition = 1;
					curShortestTime = proposalNextY.trasitionTime;
					
				} else if(proposalNextY.trasitionTime < curShortestTime) {
					nextDimTransition = 1;
					curShortestTime = proposalNextY.trasitionTime;
				}
				
			}
			
			if(proposalNextZ != null) {
				
				if(curShortestTime < -0.5) {
					nextDimTransition = 2;
					curShortestTime = proposalNextZ.trasitionTime;
					
				} else if(proposalNextZ.trasitionTime < curShortestTime) {
					nextDimTransition = 2;
					curShortestTime = proposalNextZ.trasitionTime;
					
				}
			}
			
			boolean somethingHappened = false;
			
			currentTime = curShortestTime;
			prob24bTransition proposal;
			
			//TODO: Later: reduce copy/paste code
			//TODO: not done:
			if(nextDimTransition == 0) {
				proposal = proposalNextX;
				
				if(proposalTooFast(trajs, proposal, maxVelPerDim, isVelocityPositivePerDim)) {
					
					//pass
		
				} else if(isVelocityPositivePerDim[0]) {
					
					//TODO: copy/paste code this 5 times:
					if(proposal.origLesserIndex == minMaxIndexes[0]) {
						minMaxIndexes[0] = proposal.newLesserIndex;
						somethingHappened = true;
						
					}
					//END TODO
					
				} else {
					if(proposal.newLesserIndex == minMaxIndexes[0]) {
						minMaxIndexes[0] = proposal.origLesserIndex;
						somethingHappened = true;
						
					}
					
				}
				
				ix++;
			} else if(nextDimTransition == 1) {
				proposal = proposalNextY;
				if(proposalTooFast(trajs, proposal, maxVelPerDim, isVelocityPositivePerDim)) {
					//pass

				} else if(isVelocityPositivePerDim[1]) {
					
					if(proposal.origLesserIndex == minMaxIndexes[1]) {
						minMaxIndexes[1] = proposal.newLesserIndex;
						somethingHappened = true;
						
					}
					
				} else {
					
					if(proposal.newLesserIndex == minMaxIndexes[1]) {
						minMaxIndexes[1] = proposal.origLesserIndex;
						somethingHappened = true;
						
					}
					
				}
				
				
				iy++;
			} else if(nextDimTransition == 2) {
				proposal = proposalNextZ;
				if(proposalTooFast(trajs, proposal, maxVelPerDim, isVelocityPositivePerDim)) {
					//pass

				} else if(isVelocityPositivePerDim[2]) {
					
					if(proposal.origLesserIndex == minMaxIndexes[2]) {
						minMaxIndexes[2] = proposal.newLesserIndex;
						somethingHappened = true;
						
					}
					
				} else {
					if(proposal.newLesserIndex == minMaxIndexes[2]) {
						minMaxIndexes[2] = proposal.origLesserIndex;
						somethingHappened = true;
						
					}
					
				}
				
				
				iz++;
			}
			
			if(somethingHappened) {
				ix = 0;
				iy = 0;
				iz = 0;
			}
			//TODO: not done
			//TODO: Later: reduce copy/paste code
			
			if((currentTime == 0 || somethingHappened) 
					&& theresAnObjectThatCouldBeFirstHit(minMaxIndexes)) {
				
				currentlyViableCandidate = true;
				sopl("At time " + currentTime + ", obj index " + minMaxIndexes[0] + " became a viable candidate");
				sopl(isVelocityPositivePerDim[0] + ", " + isVelocityPositivePerDim[1] + ", " + isVelocityPositivePerDim[2]);
				
				
				//sopl("Max vel: " + maxVelPerDim[0] + ", " + maxVelPerDim[1] + ", " + maxVelPerDim[2]);
				//sopl(isVelocityPositivePerDim[0] + ", " + isVelocityPositivePerDim[1] + ", " + isVelocityPositivePerDim[2]);
				
				if(isVelocityPositivePerDim[0]) {
					sop(maxVelPerDim[0] + ", ");
				} else {
					sop((int)(0-maxVelPerDim[0]) + ", ");
				}

				if(isVelocityPositivePerDim[1]) {
					sop(maxVelPerDim[1] + ", ");
				} else {
					sop((int)(0-maxVelPerDim[1]) + ", ");
				}
				
				if(isVelocityPositivePerDim[2]) {
					sop(maxVelPerDim[2] + ", ");
				} else {
					sop((int)(0-maxVelPerDim[2]) + ", ");
				}
				sopl();
				
				
			} else if( ! theresAnObjectThatCouldBeFirstHit(minMaxIndexes) && currentlyViableCandidate){
				currentlyViableCandidate = false;

				sopl("At time " + currentTime + ", obj stopped becoming a viable candidate.");
				sopl(isVelocityPositivePerDim[0] + ", " + isVelocityPositivePerDim[1] + ", " + isVelocityPositivePerDim[2]);
				sopl("----------------");
				sopl();
				
			}
			
		}
		
		
	}
	
	public static boolean theresAnObjectThatCouldBeFirstHit(int minMaxIndexes1[]) {
		
		return minMaxIndexes1[0] == minMaxIndexes1[1] && minMaxIndexes1[0] == minMaxIndexes1[2];
		
	}
	
	//TODO: care about direction to make it better?
	public static boolean proposalTooFast(ArrayList <prob24obj> trajs, prob24bTransition proposal, int maxVelPerDim[], boolean isVelocityPositivePerDim[]) {
		
		prob24obj a = trajs.get(proposal.origLesserIndex);
		prob24obj b = trajs.get(proposal.newLesserIndex);
		
		if(objTooFast(a, maxVelPerDim, isVelocityPositivePerDim) || objTooFast(b, maxVelPerDim, isVelocityPositivePerDim)) {
			return true;
		}
		
		return false;
		//proposalTooFast(proposal, maxVelPerDim);
	}
	
	public static boolean objTooFast(prob24obj obj, int maxVelPerDim[], boolean isVelocityPositivePerDim[]) {
		
		for(int i=0; i<maxVelPerDim.length; i++) {
			
			if(isVelocityPositivePerDim[i] && obj.vel[i] >= maxVelPerDim[i] ) {
				return true;
			} else if( ! isVelocityPositivePerDim[i] && obj.vel[i] <= 0  -maxVelPerDim[i] ) {
				return true;
			}
		}
		
		return false;
		//proposalTooFast(proposal, maxVelPerDim);
	}

	
	public static int getMinStartIndexForDim(ArrayList <prob24obj> trajs, int dimIndex, int maxVelPerDim[], boolean isVelocityPositivePerDim[]) {
		return getMinStartIndexForDim(trajs, dimIndex, -1, maxVelPerDim, isVelocityPositivePerDim);
	}
	public static int getMinStartIndexForDim(ArrayList <prob24obj> trajs, int dimIndex, int indexToIgnore, int maxVelPerDim[], boolean isVelocityPositivePerDim[]) {
		int ret = -1;
		
		double currentSmallest = 0.0;
		
		for(int i=0; i<trajs.size(); i++) {
			if(i == indexToIgnore) {
				continue;
			}
			if(objTooFast(trajs.get(i), maxVelPerDim, isVelocityPositivePerDim)) {
				continue;
			}
			if(ret == -1 || trajs.get(i).start[dimIndex] < currentSmallest) {
				
				currentSmallest = trajs.get(i).start[dimIndex];
				
				ret = i;
				
			} else if(ret != -1 && trajs.get(i).start[dimIndex] == currentSmallest) {
				
				if(trajs.get(i).vel[dimIndex] < trajs.get(ret).vel[dimIndex]) {
					
					currentSmallest = trajs.get(i).start[dimIndex];
					
					ret = i;
				}
			}
		}
		
		return ret;
	}
	
	public static int getMaxStartIndexForDim(ArrayList <prob24obj> trajs, int dimIndex, int maxVelPerDim[], boolean isVelocityPositivePerDim[]) {
		return getMaxStartIndexForDim(trajs, dimIndex, -1, maxVelPerDim, isVelocityPositivePerDim);
	}
	public static int getMaxStartIndexForDim(ArrayList <prob24obj> trajs, int dimIndex, int indexToIgnore, int maxVelPerDim[], boolean isVelocityPositivePerDim[]) {
		int ret = -1;
		
		double currentBiggest = 0.0;
		
		for(int i=0; i<trajs.size(); i++) {
			if(i == indexToIgnore) {
				continue;
			}
			if(objTooFast(trajs.get(i), maxVelPerDim, isVelocityPositivePerDim)) {
				continue;
			}
			if(ret == -1 || trajs.get(i).start[dimIndex] > currentBiggest) {
				
				currentBiggest = trajs.get(i).start[dimIndex];
				
				ret = i;
				
			} else if(ret != -1 && trajs.get(i).start[dimIndex] == currentBiggest) {
				
				if(trajs.get(i).vel[dimIndex] > trajs.get(ret).vel[dimIndex]) {
					currentBiggest = trajs.get(i).start[dimIndex];
					
					ret = i;
				}
			}
		}
		
		return ret;
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
