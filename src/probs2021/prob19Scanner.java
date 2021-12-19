package probs2021;
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

public class prob19Scanner {

	
	public ArrayList<String> points = new  ArrayList<String>();
	
	public ArrayList<String> distRelAbs = new  ArrayList<String>();
	
	public prob19Point pointDistArray[] = null; 
	

	
	public ArrayList<String> part2Scanners = new  ArrayList<String>();
	
	public prob19Scanner() {
		//Add the fact that there's a scanner at the origin for part 2:
		part2Scanners.add("0,0,0");
	}
	
	public void addPoint(String point) {
		points.add(point);
	}
	
	
	public void getRelDist() {
		
		pointDistArray = new prob19Point[points.size()];
		for(int i=0; i<pointDistArray.length; i++) {
			pointDistArray[i] = new prob19Point(points.get(i));
		}
		
		
		for(int i=0; i<points.size(); i++) {
			for(int j=i+1; j<points.size(); j++) {
				
				String coord1[] = points.get(i).split(",");
				String coord2[] = points.get(j).split(",");
				
				int distSqare[] = new int[coord2.length];
				
				for(int k=0; k<distSqare.length; k++) {
					distSqare[k] = Math.abs(pint(coord1[k]) - pint(coord2[k]));
					//distSqare[k] *= distSqare[k];
				}
				
				for(int k=0; k<distSqare.length; k++) {
					for(int m=k+1; m<distSqare.length; m++) {
						if(distSqare[m] > distSqare[k]) {
							int swap =  distSqare[k];
							distSqare[k] = distSqare[m];
							distSqare[m] = swap;
						}
					}
				}
				
				String ret = "";
				for(int k=0; k<distSqare.length; k++) {
					ret += distSqare[k] + ",";
				}
				ret = ret.substring(0, ret.length() - 1);
				distRelAbs.add(ret);
				
				pointDistArray[i].addDist(ret);
				pointDistArray[j].addDist(ret);
			}
		}
	}
	
	public void printDistSquare() {
		for(int i=0; i<distRelAbs.size(); i++) {
			sopl(distRelAbs.get(i));
		}
	}
	
	
	public static prob19Scanner tryToCombineScanners(prob19Scanner a, prob19Scanner b) {
		
		//Thought about using deduction, but then I got lazy...
		//boolean array[][] = new boolean[a.points.size()][b.points.size()];
		
		
		
		int origDiffsX[] = null;
		int NotXOrientation[] = new int[6];
		//int numPossibleXOrientation = 6;
		

		int origDiffsY[] = null;
		int NotYOrientation[] = new int[6];
		//int numPossibleYOrientation = 6;
		
		int origDiffsZ[] = null;
		int NotZOrientation[] = new int[6];
		//int numPossibleZOrientation = 6;
		
		boolean nothingCloseToMatching = true;
		
		for(int i=0; i<a.points.size(); i++) {
			for(int j=0; j<b.points.size(); j++) {

				
				if(prob19Point.couldBeSamePoint(a.pointDistArray[i], b.pointDistArray[j])) {
					
					nothingCloseToMatching = false;
					
					//array[i][j] = true;
					String coord1[]  =a.points.get(i).split(",");
					String coord2[] = b.points.get(j).split(",");
					
					int x1 = pint(coord1[0]);
					int y1 = pint(coord1[1]);
					int z1 = pint(coord1[2]);
					

					
					int x2 = pint(coord2[0]);
					int y2 = pint(coord2[1]);
					int z2 = pint(coord2[2]);
					int x2Neg = -pint(coord2[0]);
					int y2Neg = -pint(coord2[1]);
					int z2Neg = -pint(coord2[2]);
					
					int curDiffX[] = new int[]{x2 - x1, y2 - x1, z2 - x1, x2Neg - x1, y2Neg - x1, z2Neg - x1};
					
					if(origDiffsX == null) {
						origDiffsX = curDiffX;
					} else {
						
						for(int k=0; k<curDiffX.length; k++) {
							if(curDiffX[k] != origDiffsX[k]) {
								NotXOrientation[k]++;
								
							}
						}
					}
					
					//TODO copy/paste code
					int curDiffY[] = new int[]{x2 - y1, y2 - y1, z2 - y1, x2Neg - y1, y2Neg - y1, z2Neg - y1};
					
					if(origDiffsY == null) {
						origDiffsY = curDiffY;
					} else {
						
						for(int k=0; k<curDiffY.length; k++) {
							if(curDiffY[k] != origDiffsY[k]) {
								NotYOrientation[k]++;
								
							}
						}
					}
					//TODO copy/paste code
					

					//TODO copy/paste code
					int curDiffZ[] = new int[]{x2 - z1, y2 - z1, z2 - z1, x2Neg - z1, y2Neg - z1, z2Neg - z1};
					
					if(origDiffsZ == null) {
						origDiffsZ = curDiffZ;
					} else {
						
						for(int k=0; k<curDiffZ.length; k++) {
							if(curDiffZ[k] != origDiffsZ[k]) {
								NotZOrientation[k]++;
								
							}
						}
					}
					//TODO copy/paste code
				}
				
			}
		}
		
		if(nothingCloseToMatching) {
			//If no points seems like a match, stop trying to combine scanners:
			return null;
		}
		
		
		int min = Integer.MAX_VALUE;
		int bestIndexX = 0;
		for(int i=0; i<NotXOrientation.length; i++) {
			if(NotXOrientation[i] < min) {
				min = NotXOrientation[i];
				bestIndexX =i;
			}
			//sopl(NotXOrientation[i]);
		}
		
		//sopl("Best index X: " + bestIndexX);

		min = Integer.MAX_VALUE;
		int bestIndexY= 0;
		for(int i=0; i<NotYOrientation.length; i++) {
			if(NotYOrientation[i] < min) {
				min = NotYOrientation[i];
				bestIndexY =i;
			}
			//sopl(NotXOrientation[i]);
		}
		
		//sopl("Best index Y: " + bestIndexY);
		
		min = Integer.MAX_VALUE;
		int bestIndexZ= 0;
		for(int i=0; i<NotZOrientation.length; i++) {
			if(NotZOrientation[i] < min) {
				min = NotZOrientation[i];
				bestIndexZ =i;
			}
			//sopl(NotZOrientation[i]);
		}
		
		//sopl("Best index Z: " + bestIndexZ);
		
//**************************************************************************		
		//Plan:
		// Make a super scanner... 
		prob19Scanner ret = new prob19Scanner();
		
		for(int i=0; i<a.points.size(); i++) {
			ret.addPoint(a.points.get(i));
		}

		
		
		//I made it a vote!
		int ADJUST = 10000;
		int xDistScanner[] = new int[2 * ADJUST];
		int yDistScanner[] = new int[2 * ADJUST];
		int zDistScanner[] = new int[2 * ADJUST];
		

		for(int i=0; i<a.points.size(); i++) {
			for(int j=0; j<b.points.size(); j++) {
				if(prob19Point.couldBeSamePoint(a.pointDistArray[i], b.pointDistArray[j])) {
					
					String coord1[]  =a.points.get(i).split(",");
					String coord2[] = b.points.get(j).split(",");
					
					int x1 = pint(coord1[0]);
					int y1 = pint(coord1[1]);
					int z1 = pint(coord1[2]);
					

					
					int x2 = pint(coord2[0]);
					int y2 = pint(coord2[1]);
					int z2 = pint(coord2[2]);
					
					int alignedCoord[] = new int[3];
					
					if(bestIndexX == 0) {
						alignedCoord[0] = x2;
					} else if(bestIndexX == 1) {
						alignedCoord[0] = y2;
						
					} else if(bestIndexX == 2) {
						alignedCoord[0] = z2;
						
					} else if(bestIndexX == 3) {
						alignedCoord[0] = -x2;
						
					} else if(bestIndexX == 4) {
						alignedCoord[0] = -y2;
						
					} else if(bestIndexX == 5) {
						alignedCoord[0] = -z2;
						
					}
					xDistScanner[x1- alignedCoord[0] + ADJUST]++;

					if(bestIndexY == 0) {
						alignedCoord[1] = x2;
					} else if(bestIndexY == 1) {
						alignedCoord[1] = y2;
						
					} else if(bestIndexY == 2) {
						alignedCoord[1] = z2;
						
					} else if(bestIndexY == 3) {
						alignedCoord[1] = -x2;
						
					} else if(bestIndexY == 4) {
						alignedCoord[1] = -y2;
						
					} else if(bestIndexY == 5) {
						alignedCoord[1] = -z2;
						
					}

					yDistScanner[y1- alignedCoord[1] + ADJUST]++;

					if(bestIndexZ == 0) {
						alignedCoord[2] = x2;
					} else if(bestIndexZ == 1) {
						alignedCoord[2] = y2;
						
					} else if(bestIndexZ == 2) {
						alignedCoord[2] = z2;
						
					} else if(bestIndexZ == 3) {
						alignedCoord[2] = -x2;
						
					} else if(bestIndexZ == 4) {
						alignedCoord[2] = -y2;
						
					} else if(bestIndexZ == 5) {
						alignedCoord[2] = -z2;
						
					}
					zDistScanner[z1- alignedCoord[2] + ADJUST]++;
					
					
					//sopl("Dist estimate: ( " + xDistScannerGuess + ", " + yDistScannerGuess + " ," + zDistScannerGuess + ")");
				}
			}
		}
		
		int xDistScannerGuess = -10000;
		int yDistScannerGuess = -10000;
		int zDistScannerGuess = -10000;
		
		int maxIndex = -1;
		int max = Integer.MIN_VALUE;
		
		for(int i=0; i<xDistScanner.length; i++) {
			

			if(max > 0 && xDistScanner[i] > 0) {
				sopl("WARN: It's a contest!");
				sopl(max + " vs " + xDistScanner[i]);
			}
			
			if(xDistScanner[i] > max) {
				max = xDistScanner[i];
				maxIndex=i;
				
				if(i > 0 && max < 2) {
					sopl("WARN: could be a fluke!");
				}
			}
			
		}
		
		xDistScannerGuess = maxIndex - ADJUST;
		
		maxIndex = -1;
		max = Integer.MIN_VALUE;
		
		for(int i=0; i<yDistScanner.length; i++) {

			if(max > 0 && yDistScanner[i] > 0) {
				sopl("WARN: It's a contest!");
				sopl(max + " vs " + yDistScanner[i]);
			}
			
			if(yDistScanner[i] > max) {
				max = yDistScanner[i];
				maxIndex=i;
				
				if(i > 0 && max < 2) {
					sopl("WARN: could be a fluke!");
				}
			}
		}
		
		yDistScannerGuess = maxIndex - ADJUST;
		
		maxIndex = -1;
		max = Integer.MIN_VALUE;
		
		for(int i=0; i<zDistScanner.length; i++) {

			if(max > 0 && zDistScanner[i] > 0) {
				sopl("WARN: It's a contest!");
				sopl(max + " vs " + zDistScanner[i]);
			}
			
			if(zDistScanner[i] > max) {
				max = zDistScanner[i];
				maxIndex=i;
				
				if(i > 0 && max < 2) {
					sopl("WARN: could be a fluke!");
				}
			}
		}
		
		zDistScannerGuess = maxIndex - ADJUST;
		
		sopl("Dist estimate: ( " + xDistScannerGuess + ", " + yDistScannerGuess + " ," + zDistScannerGuess + ")");
		
		if(xDistScannerGuess == 0-ADJUST ) {
			return null;
		}
		
		
		for(int j=0; j<b.points.size(); j++) {

			String coord2[] = b.points.get(j).split(",");

			int x2 = pint(coord2[0]);
			int y2 = pint(coord2[1]);
			int z2 = pint(coord2[2]);
			
			int alignedCoord[] = new int[3];
			
			if(bestIndexX == 0) {
				alignedCoord[0] = x2;
			} else if(bestIndexX == 1) {
				alignedCoord[0] = y2;
				
			} else if(bestIndexX == 2) {
				alignedCoord[0] = z2;
				
			} else if(bestIndexX == 3) {
				alignedCoord[0] = -x2;
				
			} else if(bestIndexX == 4) {
				alignedCoord[0] = -y2;
				
			} else if(bestIndexX == 5) {
				alignedCoord[0] = -z2;
				
			}

			if(bestIndexY == 0) {
				alignedCoord[1] = x2;
			} else if(bestIndexY == 1) {
				alignedCoord[1] = y2;
				
			} else if(bestIndexY == 2) {
				alignedCoord[1] = z2;
				
			} else if(bestIndexY == 3) {
				alignedCoord[1] = -x2;
				
			} else if(bestIndexY == 4) {
				alignedCoord[1] = -y2;
				
			} else if(bestIndexY == 5) {
				alignedCoord[1] = -z2;
				
			}


			if(bestIndexZ == 0) {
				alignedCoord[2] = x2;
			} else if(bestIndexZ == 1) {
				alignedCoord[2] = y2;
				
			} else if(bestIndexZ == 2) {
				alignedCoord[2] = z2;
				
			} else if(bestIndexZ == 3) {
				alignedCoord[2] = -x2;
				
			} else if(bestIndexZ == 4) {
				alignedCoord[2] = -y2;
				
			} else if(bestIndexZ == 5) {
				alignedCoord[2] = -z2;
				
			}
			String tmpPoint = (alignedCoord[0] + xDistScannerGuess) + "," + (alignedCoord[1] + yDistScannerGuess) + ","+ ( alignedCoord[2] + zDistScannerGuess);
			
			if(ret.containsPoints(tmpPoint) == false) {
				ret.addPoint(tmpPoint);
				//sopl("Adding point: " + tmpPoint);
			} else {
				//TODO: maybe uncomment to debug?
				//sopl("Already have this point!");
			}
			
		}
		
		//Has to be done one you get all distances:
		ret.getRelDist();
		
		//par2:
		//Add scanner locations:
		ret.part2Scanners = a.part2Scanners;
		ret.part2Scanners.add(xDistScannerGuess +"," + yDistScannerGuess + "," + zDistScannerGuess);
		
		return ret;
	}
	
	public boolean containsPoints(String point) {
		for(int i=0; i<points.size(); i++) {
			if(points.get(i).equals(point)) {
				return true;
			}
		}
		
		return false;
	}
	
	

	public static int pint(String s) {
		if (IsNumber.isNumber(s)) {
			return Integer.parseInt(s);
		} else {
			sop("Error: (" + s + " is not a number");
			return -1;
		}
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
}
