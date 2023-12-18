package probs2023;
import java.io.File;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

import aStar.AstarNode;
import number.IsNumber;
import utils.Mapping;
import utils.Sort;

public class prob17Node2 implements AstarNode {

	int goali;
	int goalj;
	
	int curi;
	int curj;
	int curDir;
	int curInRow;
	
	public static prob17Node2 map[][][][] = null;
	
	public static int heat[][] = null;
	
	
	@Override
	public long getAdmissibleHeuristic(AstarNode goal) {
		// TODO Auto-generated method stub
		
		if(curi == goali && curj == goalj && curInRow >= 4 - 1) {
			return -1;
		}
		return (int)Math.abs(curi - goali) + (int)Math.abs(curj - goalj);
	}

	@Override
	public ArrayList<AstarNode> getNodeNeighbours() {
		// TODO Auto-generated method stub
		
		ArrayList<AstarNode> ret = new ArrayList<AstarNode>();
		
		
		int MAX_IN_A_ROW = 9;
		int MIN_BEFORE_TURN = 3;
		
		if(curi > 0) {
			if(curDir == 0) {
				if(curInRow < MAX_IN_A_ROW) {
					ret.add(map[curi-1][curj][0][curInRow + 1]);
				}
			} else if(curDir == 2) {
				//pass
			} else if(curInRow >= MIN_BEFORE_TURN) {
				
				
				ret.add(map[curi-1][curj][0][0]);
			}
		}
		if(curi <map.length - 1) {
			if(curDir == 2) {
				if(curInRow < MAX_IN_A_ROW) {
					ret.add(map[curi+1][curj][2][curInRow + 1]);
				}
			} else if(curDir == 0) {
				//pass
			} else if(curInRow >= MIN_BEFORE_TURN) {
				ret.add(map[curi+1][curj][2][0]);
			}
		}
		if(curj > 0) {
			if(curDir == 3) {
				if(curInRow < MAX_IN_A_ROW) {
					ret.add(map[curi][curj-1][3][curInRow + 1]);
				}
			} else if(curDir == 1) {
				//pass
			} else if(curInRow >= MIN_BEFORE_TURN) {
				ret.add(map[curi][curj-1][3][0]);
			}
		}
		if(curj < map[0].length - 1) {
			if(curDir == 1) {
				if(curInRow < MAX_IN_A_ROW) {
					ret.add(map[curi][curj+1][1][curInRow + 1]);
				}
			} else if(curDir == 3) {
				//pass
			} else if(curInRow >= MIN_BEFORE_TURN) {
				ret.add(map[curi][curj+1][1][0]);
			}
		}

		//System.out.println(curi + ","+ curj+ "," + curDir + ","+ curInRow + ":" + ret.size());
		
		return ret;
	}

	@Override
	public long getCostOfMove(AstarNode nextPos) {
		// TODO Auto-generated method stub
		return heat[((prob17Node2)nextPos).curi][((prob17Node2)nextPos).curj];
	}

	

}
