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

public class prob5 {

	//day1 part 1
	//2:38.01
	
	public static void main(String[] args) {
		Scanner in;
		try {
			in = new Scanner(new File("in2023/prob2023in5.txt"));
			//in = new Scanner(new File("in2023/prob2023in6.txt"));
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

			ArrayList tmpseeds = new ArrayList<Long>();
			ArrayList prevseeds = new ArrayList<Long>();
			boolean prevSeedsUsed[] = null;
			
			ArrayList curseeds = new ArrayList<Long>();
			
		//	ArrayList stosMap = new ArrayList<Integer>();
			
			
			
			ArrayList ints = new ArrayList<Long>();
			for(int i=0; i<lines.size(); i++) {
				
				line = lines.get(i);
				//sopl(line);
				if(i==0) {
					String tokens1[] = line.split(":")[1].trim().split(" ");
					
					for(int j=0; j<tokens1.length; j++) {
						curseeds.add(plong(tokens1[j]));
						
						System.out.println(plong(tokens1[j]));
					}

					prevSeedsUsed = new boolean[0];
					for(int j=0; j<prevSeedsUsed.length; j++) {
						prevSeedsUsed[j] = false;
						
						sopl(curseeds.get(j));
						sopl();
					}
					
					sopl();
					
					sopl("Done seeds");
					
				} else {
				
					//New Map
					if(line.contains("map")) {
						
						tmpseeds = new ArrayList<Long>();
						if(prevSeedsUsed != null) {
							
							for(int j=0; j<prevSeedsUsed.length; j++) {
								
								if(prevSeedsUsed[j] == false) {
									tmpseeds.add(prevseeds.get(j));
									
								}
							}
						}
						prevseeds = curseeds;
						
						prevseeds.addAll(tmpseeds);
						
						curseeds = new ArrayList<Long>();
						
						
						prevSeedsUsed = new boolean[prevseeds.size()];
						for(int j=0; j<prevseeds.size(); j++) {
							prevSeedsUsed[j] = false;
							
							sopl(prevseeds.get(j));
						}
						
						sopl(prevseeds.size());
						sopl();
						sopl("Done list");
						
					} else if(line.length() > 0){
						sopl("size 2: " + prevseeds.size());
						
						String mappingsS[] = line.split(" ");
						
						long mappings[] = new long[3];
						
						for(int j=0; j<mappingsS.length; j++) {
							//sopl(mappingsS[j]);
							mappings[j] = plong(mappingsS[j]);
						}
						//sopl();
						
						
						for(int j=0; j<prevseeds.size(); j++) {
							
							if((long)(prevseeds.get(j)) >= mappings[1] 
									&& (long)(prevseeds.get(j)) < mappings[1] + mappings[2]) {
								
								prevSeedsUsed[j] = true;
								
								curseeds.add((long)prevseeds.get(j) - (long)mappings[1] + (long)mappings[0]);
							}
						}
						
					}
					
					
				}
				
				
				
			}

			
			//copy paste:
			tmpseeds = new ArrayList<Long>();
			if(prevSeedsUsed != null) {
				
				for(int j=0; j<prevSeedsUsed.length; j++) {
					
					if(prevSeedsUsed[j] == false) {
						tmpseeds.add(prevseeds.get(j));
					}
				}
			}
			prevseeds = curseeds;
			
			prevseeds.addAll(tmpseeds);
			
			
			//End copy paste

			cur = -1L;
			
			for(int i=0; i<prevseeds.size(); i++) {
				
				if(i==0 || cur > (long)prevseeds.get(i)) {
					cur = (long)prevseeds.get(i);
				}
			}
			
			sopl("Answer: " + cur);
			in.close();
			
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
