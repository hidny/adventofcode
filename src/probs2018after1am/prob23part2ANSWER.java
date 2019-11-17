package probs2018after1am;

import java.io.File;

import number.IsNumber;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

import utils.Mapping;

public class prob23part2ANSWER {

	public static int answerIntesect[] = new int[0];
	
	public static boolean regionIntersectTable[][] = new boolean[0][0];
	
	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2018/prob2018in23.txt"));
			
			 Scanner input = new Scanner(System.in);
			 
			int count = 0;
			boolean part2 = false;
			String line = "";

			LinkedList queue = new LinkedList();
			Stack stack = new Stack();
			HashSet set = new HashSet();
			
			Mapping dict = new Mapping();
			ArrayList <String>lines = new ArrayList<String>();
			
			
			int LIMIT = 20000;
			boolean table[][] = new boolean[LIMIT][LIMIT];
			
			Hashtable<Long, Integer> trail = new Hashtable<Long, Integer>();
			
			//dir: 0 up
			//1 right
			//2 down
			//3 left
			
			while(in.hasNextLine()) {
				line = in.nextLine();
				lines.add(line);
				
				
			}
			
			int maxRadiusIndex = 0;
			int maxRadius = 0;

			int numIgnoredPrev = -1;
			int numIgnored = 0;
			
			int answerRadius[] = new int[lines.size()];
			

			int answerIntesectNotCorner[][];
			
			answerIntesect = new int[lines.size()];
			
			regionIntersectTable = new boolean[lines.size()][lines.size()];
			
			for(int i=0; i<regionIntersectTable.length; i++) {
				for(int j=0; j<regionIntersectTable.length; j++) {
					regionIntersectTable[i][j] = false;
					
				}
			}
			
			while(numIgnoredPrev != numIgnored) {
				numIgnoredPrev = numIgnored;
				
				answerIntesectNotCorner = new int[lines.size()][8];
				
				int origCount = 0;
				for(int i=0; i<lines.size(); i++) {
					if(answerIntesect[i] == -1) {
						continue;
					}
					
					int posix = pint(lines.get(i).split("<")[1].split(">")[0].split(",")[0]);
					int posiy = pint(lines.get(i).split("<")[1].split(">")[0].split(",")[1]);
					int posiz = pint(lines.get(i).split("<")[1].split(">")[0].split(",")[2]);
					
					int ri = pint(lines.get(i).split("=")[2]);
					
					if(ri > maxRadius) {
						maxRadius = ri;
						maxRadiusIndex=i;
					}
					
					//boolean hasZero = (Math.abs(posix) + Math.abs(posiy) + Math.abs(posiz) <= ri);
					//if(hasZero) {
					//	sopl("Does not have Zero");
					//}
					
					for(int j=0; j<lines.size(); j++) {
						if(answerIntesect[j] == -1) {
							continue;
						}
						
						int posjx = pint(lines.get(j).split("<")[1].split(">")[0].split(",")[0]);
						int posjy = pint(lines.get(j).split("<")[1].split(">")[0].split(",")[1]);
						int posjz = pint(lines.get(j).split("<")[1].split(">")[0].split(",")[2]);
	
						int rj = pint(lines.get(j).split("=")[2]);
						
						//sopl(Math.abs(posix - posjx) + Math.abs(posiy - posjy) + Math.abs(posiz - posjz));
						
						boolean Regionsintersect = (Math.abs(posix - posjx) + Math.abs(posiy - posjy) + Math.abs(posiz - posjz) <= ri + rj);
						if(Regionsintersect) {
							answerIntesect[i]++;
							regionIntersectTable[i][j] = true;
							//regionIntersectTable[j][i] = true;
						}
						
						boolean OutsideRegionsintersectCenter = (Math.abs(posix - posjx) + Math.abs(posiy - posjy) + Math.abs(posiz - posjz) <= rj);
						if(OutsideRegionsintersectCenter) {
							answerRadius[i]++;
						}
						
						
						
						//This strat doesn't really help... too inpresice
						if(Regionsintersect && OutsideRegionsintersectCenter == false) {
							
							boolean xNotRightish = false;
							boolean xNotLeftish = false;
							
							if(posix - posjx > 0) {
								xNotRightish = true;
							} else if(posix - posjx < 0) {
								xNotLeftish = true;
							} else {
								xNotRightish = true;
								xNotLeftish = true;
								
							}
							
							boolean yNotRightish = false;
							boolean yNotLeftish = false;
							
							if(posiy - posjy > 0) {
								yNotRightish = true;
							} else if(posiy - posjy < 0) {
								yNotLeftish = true;
							} else {
								yNotRightish = true;
								yNotLeftish = true;
							}
							
							boolean zNotUpish = false;
							boolean zNotDownish = false;
							
							if(posiz - posjz > 0) {
								zNotUpish = true;
							} else if(posiz - posjz < 0) {
								zNotDownish = true;
							} else {
								zNotUpish = true;
								zNotDownish = true;
							}
							
							if(xNotRightish && yNotRightish && zNotUpish) {
								answerIntesectNotCorner[i][0]++;
							}
							
							if(xNotRightish && yNotRightish && zNotDownish) {
								answerIntesectNotCorner[i][1]++;
							}
							
							if(xNotRightish && yNotLeftish && zNotUpish) {
								answerIntesectNotCorner[i][2]++;
							}
							
							if(xNotRightish && yNotLeftish && zNotDownish) {
								answerIntesectNotCorner[i][3]++;
							}
							
							if(xNotLeftish && yNotRightish && zNotUpish) {
								answerIntesectNotCorner[i][4]++;
							}
							
							if(xNotLeftish && yNotRightish && zNotDownish) {
								answerIntesectNotCorner[i][5]++;
							}
							
							if(xNotLeftish && yNotLeftish && zNotUpish) {
								answerIntesectNotCorner[i][6]++;
							}
							
							if(xNotLeftish && yNotLeftish && zNotDownish) {
								answerIntesectNotCorner[i][7]++;
							}
							
						}
					}
					
					//sopl("answerIntesect[" + i + "] = " + answerIntesect[i] + "    ri  " + ri);
				}
	
	
				int indexMaxAnswer = 0;
				int maxAnswerRadius = 0;
				for(int i=0; i<answerRadius.length; i++) {
					if(answerIntesect[i] == -1) {
						continue;
					}
					
					if(answerRadius[i] > maxAnswerRadius) {
						maxAnswerRadius = answerRadius[i];
						indexMaxAnswer = i;
					}
				}
				
				sopl("Max answer radius: " + maxAnswerRadius);
				sopl("Index answer radius: " + indexMaxAnswer + "(" + lines.get(indexMaxAnswer)+ ")");
				
				sopl("-----------------");
				sopl("-----------------");
				sopl("-----------------");
				
				
				for(int i=0; i<answerIntesect.length; i++) {
					if(answerIntesect[i] == -1) {
						continue;
					}
					
					/*
					int temp=0;
					for(int j=0; j<regionIntersectTable.length; j++) {
						if(answerIntesect[i] == -1 || answerIntesect[j] == -1) {
							continue;
						}
						
						
						if(regionIntersectTable[i][j]) {
							temp++;
						}
						
						if(regionIntersectTable[i][j] != regionIntersectTable[j][i]) {
							sopl("What??");
							exit(1);
						}
					}*/
					
					int minusFactor = answerIntesectNotCorner[i][0];
					for(int j=0; j<8; j++) {
						
						if(answerIntesectNotCorner[i][j] < minusFactor) {
							minusFactor = answerIntesectNotCorner[i][j];
						}
					}
					//sopl(minusFactor);
					
					if(minusFactor > 0) {
						sopl("Minus: " + minusFactor);
					}
					
					if(answerIntesect[i] - minusFactor < maxAnswerRadius) {
						answerIntesect[i] = -1;
						numIgnored++;
					} else {
						answerIntesect[i] = 0;
						answerRadius[i] = 0;
					}
					
				}
				sopl("Ignore " + numIgnored);
				sopl("Inside region of one of " + (1000 - numIgnored));
				

			}
			
			for(int i=0; i<regionIntersectTable.length; i++) {
				for(int j=0; j<regionIntersectTable[0].length; j++) {
					if(answerIntesect[i] != -1 && answerIntesect[j] != -1) {
						if(regionIntersectTable[i][j] == false) {
							sopl("FALSE");
							exit(1);
						}
					}
				}
			}
			
			//OMG
			//Looks like the obvious 979 regions all intersect each other...
			//Just make an algo that gets the intersected region, then get the point i,j,k that's smallest when for i+j+k 
			
			//BronKerbosch1();
			
			
			int answer = 0;
			
			for(int i=0; i<lines.size(); i++) {
				if(answerIntesect[i] == -1) {
					continue;
				}
				int posix = pint(lines.get(i).split("<")[1].split(">")[0].split(",")[0]);
				int posiy = pint(lines.get(i).split("<")[1].split(">")[0].split(",")[1]);
				int posiz = pint(lines.get(i).split("<")[1].split(">")[0].split(",")[2]);
				
				int ri = pint(lines.get(i).split("=")[2]);
				
				if(posix + posiy + posiz - ri > answer) {
					answer = posix + posiy + posiz - ri ;
				}
			}
			
			sopl("answer: " + answer);
			//At this point we narrowed it done to within 416 regions and outside 584 regions
			
			//in.close();
			
			/*
			for(int d=0; d<100000; d++) {
				for(int x=0;x<d; d++) {
					for(int y=0; y<d;d++) {
						for(int z=0; z<d;d++) {
							
						}
					}
				}
			}*/
			
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	
	
	/*
	 *   BronKerbosch1(R, P, X):
       if P and X are both empty:
           report R as a maximal clique
       for each vertex v in P:
           BronKerbosch1(R ⋃ {v}, P ⋂ N(v), X ⋂ N(v))
           P := P \ {v}
           X := X ⋃ {v}
	 */
	
	public static void BronKerbosch1() {
		HashSet<Integer> setP = new HashSet<Integer>();
		
		for(int i=0; i<answerIntesect.length; i++) {
			if(answerIntesect[i] >=0) {
				setP.add(i);
			}
		}
		
		BronKerbosch1(new HashSet<Integer>(), setP, new HashSet<Integer>());
	}
	
	public static HashSet<Integer> biggestIntersect = new HashSet<Integer>();
	public static boolean warningMultAnswers = false;
	
	public static void BronKerbosch1(HashSet<Integer> R, HashSet<Integer> P, HashSet<Integer> X) {
		
		
		//sopl(R.size());
		/*
		 *   BronKerbosch1(R, P, X):
	       if P and X are both empty:
	           report R as a maximal clique
	       for each vertex v in P:
	           BronKerbosch1(R ⋃ {v}, P ⋂ N(v), X ⋂ N(v))
	           P := P \ {v}
	           X := X ⋃ {v}
		 */
		if(P.size() == 0 && X.size() == 0) {
			if(R.size() > biggestIntersect.size()) {
				biggestIntersect = R;
				warningMultAnswers = false;
				
				sopl("Good max: " + R.size());
				
			} else if(R.size() == biggestIntersect.size()) {
				biggestIntersect = R;
				warningMultAnswers = true;
				
				sopl("Bad max: " + R.size());
			}
		}
		
		Iterator<Integer> pIter = P.iterator();
		
		while(pIter.hasNext()) {
			int v = (Integer)pIter.next();
			
			HashSet<Integer> N = getNeighbors(v);
			

			HashSet<Integer> itersectNP = new HashSet<Integer>();
			HashSet<Integer> itersectNX = new HashSet<Integer>();
			
			Iterator<Integer> nIter = N.iterator();
			while(nIter.hasNext()) {
				Integer temp = nIter.next();
				if(P.contains(temp)) {
					itersectNP.add(temp);
				}
				if(X.contains(temp)) {
					itersectNX.add(temp);
				}
			}
			
			// BronKerbosch1(R ⋃ {v}, P ⋂ N(v), X ⋂ N(v))
	        //   P := P \ {v}
	        //   X := X ⋃ {v}
	           
			///*HashSet<Integer> Ponly = */P.retainAll(n);

			HashSet<Integer> Rcopy = (HashSet<Integer>)R.clone();
			HashSet<Integer> Pcopy = (HashSet<Integer>)P.clone();
			HashSet<Integer> Xcopy = (HashSet<Integer>)X.clone();
			
			R.add(v);
			BronKerbosch1(R, itersectNP, itersectNX);
			
			R = Rcopy;
			P = Pcopy;
			X = Xcopy;
			
			
			P.remove(new Integer(v));
			X.add(new Integer(v));
		}
	}
	
			
	public static HashSet<Integer> getNeighbors(int indexNode) {
		
			HashSet<Integer> ret = new HashSet<Integer>();
			
			if(answerIntesect[indexNode] == -1) {
				return ret;
			}
			
			
			for(int j=0; j<regionIntersectTable.length; j++) {
				if(answerIntesect[j] == -1 || j == indexNode) {
					continue;
				}
				
				
				if(regionIntersectTable[indexNode][j]) {
					ret.add(new Integer(j));
				} else {
					sopl("not inter");
				}
				
				if(regionIntersectTable[indexNode][j] != regionIntersectTable[j][indexNode]) {
					sopl("What??");
					exit(1);
				}
			}
			
			return ret;
		
	}
	/*
	public static int[] complement(ArrayList <String>lines, int i, int j) {
		int posix = pint(lines.get(i).split("<")[1].split(">")[0].split(",")[0]);
		int posiy = pint(lines.get(i).split("<")[1].split(">")[0].split(",")[1]);
		int posiz = pint(lines.get(i).split("<")[1].split(">")[0].split(",")[2]);
		
		int ri = pint(lines.get(i).split("=")[2]);
		
		int posjx = pint(lines.get(j).split("<")[1].split(">")[0].split(",")[0]);
		int posjy = pint(lines.get(j).split("<")[1].split(">")[0].split(",")[1]);
		int posjz = pint(lines.get(j).split("<")[1].split(">")[0].split(",")[2]);

		int rj = pint(lines.get(i).split("=")[2]);
		
		
		
		
	}
*/
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
			sop("Error: (" + s + " is not a number");
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
