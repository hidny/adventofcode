package practice.snaky_problem.AI.utils;

import practice.snaky_problem.GUI.Constants;
import practice.snaky_problem.logic.BoardStateStatic;

public class positionCheckUtils {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	
	public static boolean is1Away(int playerIndex) {
		
		
		return get1Away(playerIndex) != null;
	}
	
	public static int[] get1Away(int playerIndex) {
			
		if(BoardStateStatic.allSnakes == null) {
			BoardStateStatic.setupAllSnakes();
		}
		
		
		for(int i2=0; i2<Constants.NUM_CELLS_VERT; i2++) {
			for(int j2=0; j2<Constants.NUM_CELLS_HORI; j2++) {
				
				if(getMinNumAwayAtTopLeftLocation(i2, j2, playerIndex) == 1) {
					
					return getLocationToFillMinNumAwayAtTopLeftLocation(i2, j2, playerIndex);
				}
			}
		}
		return null;
	}
	
	
	
	public static int getMinNumAwayAtTopLeftLocation(int i, int j, int playerIndex) {

		int minAway = 6;
		for(int k=0; k<BoardStateStatic.allSnakes.length; k++) {
			
			int numMismatch = 0;
			
			for(int i3=0; i3<BoardStateStatic.allSnakes[k].length; i3++) {
				for(int j3=0; j3<BoardStateStatic.allSnakes[k][0].length; j3++) {

					int effectiveI = i + i3;
					int effectiveJ = j + j3;
					
					if(effectiveI >= Constants.NUM_CELLS_VERT || effectiveJ>=Constants.NUM_CELLS_HORI) {
						numMismatch=7;
						continue;
						
					} else if(BoardStateStatic.allSnakes[k][i3][j3] == true && BoardStateStatic.get(effectiveI, effectiveJ) == getOtherPlayer(playerIndex)) {
						numMismatch=7;
						continue;
					}
					
					if(BoardStateStatic.allSnakes[k][i3][j3] == true && BoardStateStatic.get(effectiveI, effectiveJ) == Constants.EMPTY) {
						numMismatch++;
						continue;
					}
				}
			}
			
			if(numMismatch < minAway) {
				minAway = numMismatch;
			}
			
		}
		
		return minAway;
	}
	
	
	public static int[] getLocationToFillMinNumAwayAtTopLeftLocation(int i, int j, int playerIndex) {

		int ret[] = null;
		
		int minAway = 6;
		for(int k=0; k<BoardStateStatic.allSnakes.length; k++) {
			
			int numMismatch = 0;
			int potential[] = new int[2];
			
			for(int i3=0; i3<BoardStateStatic.allSnakes[k].length; i3++) {
				for(int j3=0; j3<BoardStateStatic.allSnakes[k][0].length; j3++) {
					
					int effectiveI = i + i3;
					int effectiveJ = j + j3;
					
					
					if(effectiveI >= Constants.NUM_CELLS_VERT || effectiveJ>=Constants.NUM_CELLS_HORI) {
						numMismatch=7;
						continue;
						
					} else if(BoardStateStatic.allSnakes[k][i3][j3] == true && BoardStateStatic.get(effectiveI, effectiveJ) == getOtherPlayer(playerIndex)) {
						numMismatch=7;
						continue;
					}
					
					if(BoardStateStatic.allSnakes[k][i3][j3] == true && BoardStateStatic.get(effectiveI, effectiveJ) == Constants.EMPTY) {
						numMismatch++;
						potential[0] = effectiveI;
						potential[1] = effectiveJ;
						continue;
					}
				}
			}
			
			if(numMismatch < minAway) {
				minAway = numMismatch;
				ret = new int[2];
				ret[0] = potential[0];
				ret[1] = potential[1];
			}
			
		}

		if(ret == null) {
			System.out.println("WARNING: ret is null!");
		}
		return ret;
	}
	
	public static int getOtherPlayer(int playerIndex) {
		if(playerIndex == Constants.PLAYER_1) {
			return Constants.PLAYER_2;
		} else {

			return Constants.PLAYER_1;
		}
	}
}
