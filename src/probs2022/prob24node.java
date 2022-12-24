package probs2022;
import java.io.File;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

import aStar.AstarAlgo;
import aStar.AstarNode;
import number.IsNumber;
import utils.Mapping;
import utils.Sort;

public class prob24node implements AstarNode {

	

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
	
	public static ArrayList<String> lines;
	
	public int i;
	public int j;
	
	public int curMinute;

	public static ArrayList<Boolean[][]> blizzMapArray = new ArrayList<Boolean[][]>();
	
	public static int goali;
	public static int goalj;
	@Override
	public long getAdmissibleHeuristic(AstarNode goal) {
		
		long tmp = Math.abs(this.i - goali) + Math.abs(this.j - goalj);
		
		if(tmp == 0) {
			return AstarAlgo.GOAL_FOUND;
		} else {
			return tmp;
		}
	}

	
	
	public prob24node(int i, int j, int curMinute) {
		super();
		this.i = i;
		this.j = j;
		this.curMinute = curMinute;
	}
	

	//Hope it's less than 500 minutes...
	public static prob24node spots [][][];
	
	public static prob24node getSpot(int i, int j, int min) {
		if(spots[min][i][j] == null) {
			spots[min][i][j] = new prob24node(i, j, min);
		}
		
		return spots[min][i][j];
	}
	
	public static Boolean[][] createBlizzMap(int minute) {
		
		Boolean result[][] = new Boolean[lines.size()][lines.get(0).length()];

		for(int i=0; i<lines.size(); i++) {
			for(int j=0; j<lines.get(0).length(); j++) {
				result[i][j] = false;
			}
		}
		for(int i=0; i<lines.size(); i++) {
			for(int j=0; j<lines.get(0).length(); j++) {
				
				if(lines.get(i).charAt(j) == '<') {
					result[i][1 + mod((j-1) - minute, lines.get(0).length() - 2)] = true;
				}
				
				if(lines.get(i).charAt(j) == '^') {
					result[1 + mod((i-1) - minute, lines.size() - 2)][j] = true;
				}
				
				if(lines.get(i).charAt(j) == '>') {
					result[i][1 + mod((j-1) + minute, lines.get(0).length() - 2)] = true;
				}
				
				if(lines.get(i).charAt(j) == 'v') {
					result[1 + mod((i-1) + minute, lines.size() - 2)][j] = true;
				}
			}
		}
		
		/*for(int i=0; i<lines.size(); i++) {
			for(int j=0; j<lines.get(0).length(); j++) {
				if(lines.get(i).charAt(j) == '#') {
					if(result[i][j]) {
						sopl("DOH!");
						exit(1);
					}
					sop("#");
				} else if(result[i][j]) {
					sop("B");
				} else {
					sop(".");
				}
			}
			sopl();
		}
		sopl();
		*/
		return result;
	}
	
	public static int mod(int n, int mod) {
		int tmp = n % mod;
		if(tmp < 0) {
			return tmp + mod;
		} else {
			return tmp;
		}
	}
	
	@Override
	public ArrayList<AstarNode> getNodeNeighbours() {
		
		//could stay still...
		if(curMinute+1 >= blizzMapArray.size()) {
			
			Boolean map[][] = createBlizzMap(curMinute+1);
			
			if(curMinute >= blizzMapArray.size()) {
				sopl("doh!");
				exit(1);
			}
			blizzMapArray.add(map);
			
			if(blizzMapArray.size() - 1 != curMinute+1) {
				sopl("Doh size");
				exit(1);
			}
			
			//Create blizz map
		}
		
		Boolean blizzMapNextMinute[][] = blizzMapArray.get(this.curMinute+1);
		
		ArrayList<AstarNode> ret = new ArrayList<AstarNode>();
		
		if(i > 0                             && lines.get(i-1).charAt(j) != '#' && blizzMapNextMinute[i-1][j] == false) {
			ret.add(getSpot(i-1, j, curMinute+1));
		}
		
		if(j+1 < blizzMapNextMinute[0].length && lines.get(i).charAt(j+1) != '#' && blizzMapNextMinute[i][j+1] == false) {
			ret.add(getSpot(i, j+1, curMinute+1));
		}
		
		if(i+1 <  blizzMapNextMinute.length && lines.get(i+1).charAt(j) != '#' && blizzMapNextMinute[i+1][j] == false) {
			ret.add(getSpot(i+1, j, curMinute+1));
		}

		if(j > 0                        && lines.get(i).charAt(j-1) != '#' && blizzMapNextMinute[i][j-1] == false) {
			ret.add(getSpot(i, j-1, curMinute+1));
		}
		
		if(                                                              blizzMapNextMinute[i][j] == false) {
			ret.add(getSpot(i, j, curMinute+1));
		}
		
		// TODO Auto-generated method stub
		return ret;
	}

	@Override
	public long getCostOfMove(AstarNode nextPos) {
		
		return 1;
	}

}
