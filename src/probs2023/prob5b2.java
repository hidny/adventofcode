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

public class prob5b2 {

	//day1 part 1
	//2:38.01
	
	public static void main(String[] args) {
		Scanner in;
		try {
			in = new Scanner(new File("in2023/prob2023in5.txt"));
			//in = new Scanner(new File("in2023/prob2023in5.txt"));
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
			boolean table[][] = new boolean[LIMIT][LIMIT];
			
			
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
			long cur = 0;

			ArrayList<prob5Ranges> curseeds = new ArrayList<prob5Ranges>();
			ArrayList<prob5Ranges> movedSeeds = new ArrayList<prob5Ranges>();
			ArrayList<prob5Ranges> prevseeds = new ArrayList<prob5Ranges>();

			
		//	ArrayList stosMap = new ArrayList<Integer>();
			
			
			boolean firstMap = true;
			
			ArrayList ints = new ArrayList<Long>();
			for(int i=0; i<lines.size(); i++) {
				
				line = lines.get(i);
				sopl(line);
				if(i==0) {
					String tokens1[] = line.split(":")[1].trim().split(" ");
					
					for(int j=0; j + 1<tokens1.length; j = j + 2) {
						curseeds.add(new prob5Ranges(plong(tokens1[j]), plong(tokens1[j]) + plong(tokens1[j+1])));
						
						System.out.println(curseeds.get(curseeds.size() - 1));
					}

					
					sopl("Done seeds");
					
				} else {
					//New Map
					if(line.contains("map")) {
						
						sopl();
						sopl("hello map");
						
						
						sopl("missed seeds:");
						
						ArrayList<prob5Ranges> tmp = getNotMovedSeeds(prevseeds, movedSeeds);
						for(int j=0; j<tmp.size(); j++) {
							sopl(tmp.get(j).start + ", " + tmp.get(j).end);
						}
						

						prevseeds = curseeds;
						sopl("new curSeeds:");
						for(int j=0; j<prevseeds.size(); j++) {
							sopl(prevseeds.get(j).start + ", " + prevseeds.get(j).end);
						}
						
						if(firstMap == false) {
							prevseeds.addAll(tmp);
						}
						firstMap = false;
						
						movedSeeds = new ArrayList<prob5Ranges>();
						curseeds = new ArrayList<prob5Ranges>();
						
						
						sopl("----");
						sopl();
						
					} else if(line.length() > 0) {
						
						String mappingsS[] = line.split(" ");
						
						long mappings[] = new long[3];
						
						for(int j=0; j<mappingsS.length; j++) {
							mappings[j] = plong(mappingsS[j]);
						}
						
						for(int j=0; j<prevseeds.size(); j++) {
							
							long mappingRight = mappings[1] + mappings[2];
							long displacement = mappings[0] - mappings[1];
							
							prob5Ranges tmp = prevseeds.get(j);
							if( tmp.start >= mappingRight || tmp.end <= mappings[1]) {
								continue;
							}
							
							
							
							long leftExtreme = Math.max(tmp.start, mappings[1]);
							long rightExtreme = Math.min(tmp.end, mappingRight);
							
							curseeds.add(new prob5Ranges(leftExtreme + displacement, rightExtreme + displacement));
						
							movedSeeds.add(new prob5Ranges(leftExtreme, rightExtreme));
							
						}
						
						
					}
				}
			}
			
			//TODO: copy/paste copde:

			ArrayList<prob5Ranges> tmp = getNotMovedSeeds(prevseeds, movedSeeds);
			
			prevseeds = curseeds;
			prevseeds.addAll(tmp);
			
			cur = -1L;
			
			for(int i=0; i<prevseeds.size(); i++) {
				
				if(i==0 || cur > (long)prevseeds.get(i).start) {
					cur = (long)prevseeds.get(i).start;
				}
			}
			
			//108956227
			//279420935 too high
			sopl("Answer: " + cur);
			
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}

	public static ArrayList<prob5Ranges> getNotMovedSeeds(ArrayList<prob5Ranges> prevSeeds, ArrayList<prob5Ranges> movedSeeds) {
		
		ArrayList<prob5Ranges> ret = new ArrayList<prob5Ranges>();
		
		for(int i=0; i<prevSeeds.size(); i++) {
			ret.add(prevSeeds.get(i).hardCopy());
		}
		
		for(int i=0; i<ret.size(); i++) {

			prob5Ranges tmpRet = ret.get(i);
			
			//Handle in-between cases first:
			for(int j=0; j<movedSeeds.size(); j++) {
				prob5Ranges tmpMoved = movedSeeds.get(j);
				if(tmpRet.start < tmpMoved.start && tmpRet.end > tmpMoved.end) {
					
					ret.set(i, new prob5Ranges(tmpRet.start, tmpMoved.start));
					
					ret.add(new prob5Ranges(tmpMoved.end, tmpRet.end));
					
				}
			}
			
			//Handle cuts from right or left or whole segment:
			boolean removed = false;
			long case1start = -1;
			long case2end = -1;
			CHECK_CURRENT_RANGE:
			for(int j=0; j<movedSeeds.size(); j++) {
				
				prob5Ranges tmpMoved = movedSeeds.get(j);
				
				if(tmpRet.end > tmpMoved.end && tmpRet.start < tmpMoved.end) {

					//sopl("1");
					//ret.add(new prob5Ranges(tmpMoved.end, tmpRet.end));
					if(case1start == -1 || case1start < tmpMoved.end) {
						case1start = tmpMoved.end;
					}
				}
				
				if(tmpRet.start < tmpMoved.start && tmpRet.end > tmpMoved.start) {
					//sopl("2");
					//ret.set(i, new prob5Ranges(tmpRet.start, tmpMoved.start));
					if(case2end == -1 || case2end > tmpMoved.start) {
						case2end = tmpMoved.start;
					}
				}
				
				if(tmpRet.start >= tmpMoved.start && tmpRet.end <= tmpMoved.end) {
					ret.remove(i);
					i--;
					//sopl("rm");
					removed = true;
					break CHECK_CURRENT_RANGE;
				}
				
				
			}
			
			if( ! removed ) {
				
				if(case1start != -1 && case2end != -1) {
					
					if(case1start < case2end) {
						ret.set(i, new prob5Ranges(case1start, case2end));
					} else {
						ret.remove(i);
						i--;
					}
					
				} else {
				
					if(case1start != -1) {
						ret.set(i, new prob5Ranges(case1start, tmpRet.end));
						
					}
					if(case2end != -1) {
						ret.set(i, new prob5Ranges(tmpRet.start, case2end));
					}
				}
				
			}
			
			
		}
		
		return ret;
	}
	//266636474
	
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
		//if (IsNumber.isNumber(s)) {
			return Long.parseLong(s);
		//} else {
		//	sop("Error: (" + s + ") is not a number2");
		//	return -1;
		//}
	}
	
	
	public static void exit() {
		exit(0);
	}
	public static void exit(int code) {
		sop("Exit with code " + code);
		
		System.exit(code);
	}

}
