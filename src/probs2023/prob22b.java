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

public class prob22b {

	//day1 part 1
	//2:38.01
	
	public static void main(String[] args) {
		Scanner in;
		try {
			in = new Scanner(new File("in2023/prob2023in22.txt"));
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
			boolean table342[][] = new boolean[LIMIT][LIMIT];
			
			
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

			ArrayList<prob22objb> bricks = new ArrayList<prob22objb>();
			
			for(int i=0; i<lines.size(); i++) {
				
				
				line = lines.get(i);
				
				String tokens[] = line.split("~");
				
				String co1[] = tokens[0].split(",");
				String co2[] = tokens[1].split(",");
				
				prob22objb tmp= new prob22objb();
				
				tmp.x1 = pint(co1[0]);
				tmp.y1 = pint(co1[1]);
				tmp.z1 = pint(co1[2]);
				

				tmp.x2 = pint(co2[0]);
				tmp.y2 = pint(co2[1]);
				tmp.z2 = pint(co2[2]);
				
				tmp.canDisintegrate = true;
				
				tmp.minZ = Math.min(tmp.z1, tmp.z2);
				
				bricks.add(tmp);
			}
			
			bricks = prob22objb.sort(bricks);
			

			for(int i=0; i<bricks.size(); i++) {
				sopl(bricks.get(i));
			
			}
			//exit(1);
			
			
			
			for(int i=0; i<bricks.size(); i++) {
				cur += getNumDistingrate(bricks, i);
			}
			

			sopl("Answer: " + cur);
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}

	public static long getNumDistingrate(ArrayList<prob22objb> bricks, int ignoreIndex) {
		int curZlanding;
		
		int count2 = 0;
		
		boolean disintegrated[] = new boolean[bricks.size()];
		for(int i=0; i<disintegrated.length; i++) {
			disintegrated[i] = false;
		}
		
		if(ignoreIndex >= 0) {
			disintegrated[ignoreIndex] = true;
		}
		
		int curCantDisintegrate;
		for(int i=0; i<bricks.size(); i++) {
			
			curZlanding = 0;
			curCantDisintegrate = -1;
			
			//sopl("Minz: " + bricks.get(i).minZ);
			//466
			
			for(int j=0; j<i; j++) {
				
				if(prob22objb.intersects(bricks.get(i), bricks.get(j))) {
					
					
					//if(bricks.get(j).z1 == 6 &&bricks.get(i).z1 == 8 ) {
					//	sopl("Debug");
					//}
					
					int newZLanding = bricks.get(j).zLanding + bricks.get(j).getHeight();
					
					if(newZLanding > curZlanding) {
						//sopl(bricks.get(j) + " and " + bricks.get(i) + ": intersects");
						
						curCantDisintegrate = j;
					} else if(newZLanding == curZlanding && disintegrated[j] == false){
						//sopl(bricks.get(j) + " and " + bricks.get(i) + ": intersects");
						
						curCantDisintegrate = -2;
					}
					
					curZlanding = Math.max(newZLanding, curZlanding);
					
				}
				
			}
			//sopl("Zlanding: " + curZlanding);
			bricks.get(i).zLanding = curZlanding;
			
			if(curCantDisintegrate >= 0 ) {

				if(disintegrated[curCantDisintegrate]) {
					disintegrated[i] = true;
				}
						
				
			} else {
				//sopl("hello");
				//exit(1);
			}
			//sopl();
		}
		
		//1325
		
		//466 too high
		//sopl("Final:");
		long cur = 0L;
		for(int i=0; i<bricks.size(); i++) {
			
			if(disintegrated[i]) {
				cur++;
			}
			
		}
		
		//Don't count the original block that got disintegrated (want other blocks)
		return cur - 1;
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
