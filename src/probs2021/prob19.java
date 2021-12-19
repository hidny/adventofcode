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

public class prob19 {

	//TODO: clean the code (there's lots of copy paste code!)
	
	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2021/prob2021in19.txt"));
			// in = new Scanner(new File("in2021/prob2021in20.txt"));
			int numTimes = 0;
			 
			int count = 0;
			boolean part2 = false;

			LinkedList queue = new LinkedList();
			Stack stack = new Stack();
			HashSet set = new HashSet();
			
			
			Hashtable<Long, Integer> trail = new Hashtable<Long, Integer>();
			
			ArrayList <String>lines = new ArrayList<String>();
			
			
			//int LIMIT = 20000;
			//boolean table[][] = new boolean[LIMIT][LIMIT];
			
			
			//dir: 0 up
			//1 right
			//2 down
			//3 left
			
			while(in.hasNextLine()) {
				String line = in.nextLine();
				lines.add(line);
				
				
			}
			
			
			ArrayList ints = new ArrayList<Integer>();
			
			
			int numScanners = 0;
			for(int i=0; i<lines.size(); i++) {
				
				//int temp = Integer.parseInt(lines.get(i));
				//count+=temp;
				
				String line = lines.get(i);
				
				if(line.contains("scanner")) {
					numScanners++;
				}
				
			}
			
			prob19Scanner scanners[] = new prob19Scanner[numScanners];
			for(int i=0; i<numScanners; i++) {
				scanners[i] = new prob19Scanner();
			}
			
			int curScanner = -1;
			for(int i=0; i<lines.size(); i++) {
				
				//int temp = Integer.parseInt(lines.get(i));
				//count+=temp;

				String line = lines.get(i);
				
				if(line.contains("scanner")) {
					curScanner++;
					
					
				} else if(line.contains(",")) {
					
					scanners[curScanner].addPoint(line);
					
				}
				
			}
			
			for(int i=0; i<numScanners; i++) {
				//sopl("Scanner " + i + " distances:");
				scanners[i].getRelDist();
				//scanners[i].printDistSquare();
				//sopl();
				//sopl();
				
			}
			

			//Try to combine scanners into a mega scanner
			//If the scanners don't natually combine, try again later:
			prob19Scanner ret = scanners[0];
			
			boolean keepTrying = true;
			
			boolean done[] = new boolean[scanners.length];
			
			
			while(keepTrying) {
				keepTrying = false;
				
				for(int i=1; i<scanners.length; i++) {
					
					if(done[i] == false) {
						
						sopl("Combining with scanner " + i);
						prob19Scanner tmp  = prob19Scanner.tryToCombineScanners(ret, scanners[i]);
						if(tmp == null) {
							sopl("Cancelled!");
							keepTrying = true;
							
						} else {
							ret = tmp;
							done[i] = true;
						}
					}
				}
			}
			
			//SANITY CHECK:
			for(int i=1; i<done.length; i++) {
				if(done[i] == false) {
					sopl("WARN: Not joined to scanner zero: " + i);
				}
			}
			//END SANITY CHECK
			
			sopl("Answer part 1: " + ret.points.size());
			in.close();
			
			//Part 2:
			
			int maxManhattanDistanceBetweenScanners = 0;
			
			for(int i=0; i<ret.part2Scanners.size(); i++) {
				for(int j=i+1; j<ret.part2Scanners.size(); j++) {
					
					int curDist = 0;
					String coord1[] = ret.part2Scanners.get(i).split(",");
					String coord2[] = ret.part2Scanners.get(j).split(",");
					
					int dist[] = new int[coord2.length];
					
					for(int k=0; k<dist.length; k++) {
						curDist += Math.abs(pint(coord1[k]) - pint(coord2[k]));
						//distSqare[k] *= distSqare[k];
					}
					
					if(curDist > maxManhattanDistanceBetweenScanners) {
						maxManhattanDistanceBetweenScanners = curDist;
					}
				}
			}
			
			sopl("Part 2 answer: " + maxManhattanDistanceBetweenScanners);
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
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
	
	public static int pint(String s) {
		if (IsNumber.isNumber(s)) {
			return Integer.parseInt(s);
		} else {
			sop("Error: (" + s + " is not a number");
			return -1;
		}
	}

	public static long plong(String s) {
		if (IsNumber.isLong(s)) {
			return Long.parseLong(s);
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
