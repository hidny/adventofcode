package practice.snaky_problem.AI.utils;

import java.util.ArrayList;

import practice.snaky_problem.GUI.Constants;
import practice.snaky_problem.logic.BoardStateStatic;

public class positionCheckUtils {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	
	public static boolean is1Away(int playerIndex) {
		
		
		return get1Away(playerIndex) != null;
	}
	

	public static boolean isNAway(int playerIndex, int n) {
		
		
		return getNAway(playerIndex, n) != null;
	}
	
	public static int[] get1Away(int playerIndex) {
			
		
		return getNAway(playerIndex, 1);
	}

	public static int[] getNAway(int playerIndex, int n) {
		return getNAway(playerIndex, n, BoardStateStatic.getBoard());
	}
	
	public static int[] getNAway(int playerIndex, int n, int board[][]) {
		
		if(BoardStateStatic.allSnakes == null) {
			BoardStateStatic.setupAllSnakes();
		}
		
		
		int minFound = 10;
		for(int i2=0; i2<Constants.NUM_CELLS_VERT; i2++) {
			for(int j2=0; j2<Constants.NUM_CELLS_HORI; j2++) {
				
				int cur = getMinNumAwayAtTopLeftLocation(i2, j2, playerIndex, board);
				if(cur < minFound) {
					minFound = cur;
				}
				
				if(getMinNumAwayAtTopLeftLocation(i2, j2, playerIndex, board) == n) {
					
					return getLocationToFillMinNumAwayAtTopLeftLocation(i2, j2, playerIndex, board);
					
				}
			}
		}
		
		System.out.println("Min found for n = " + n + ": " + minFound);
		return null;
	}
	
	
	
	public static int getMinNumAwayAtTopLeftLocation(int i, int j, int playerIndex, int board[][]) {

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
						
					} else if(BoardStateStatic.allSnakes[k][i3][j3] == true && board[effectiveI][effectiveJ] == getOtherPlayer(playerIndex)) {
						numMismatch=7;
						continue;
					}
					
					if(BoardStateStatic.allSnakes[k][i3][j3] == true && board[effectiveI][effectiveJ] == Constants.EMPTY) {
						numMismatch++;
					}
				}
			}
			
			if(numMismatch < minAway) {
				minAway = numMismatch;
			}
			
		}
		
		return minAway;
	}
	
	
	public static int[] getLocationToFillMinNumAwayAtTopLeftLocation(int i, int j, int playerIndex, int board[][]) {

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
						
					} else if(BoardStateStatic.allSnakes[k][i3][j3] == true && board[effectiveI][effectiveJ] == getOtherPlayer(playerIndex)) {
						numMismatch=7;
						continue;
					}
					
					if(BoardStateStatic.allSnakes[k][i3][j3] == true && board[effectiveI][effectiveJ] == Constants.EMPTY) {
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
	
	public static ArrayList<String> getListToFillNAway(int playerIndex, int n, int board[][]) {
		
		if(BoardStateStatic.allSnakes == null) {
			BoardStateStatic.setupAllSnakes();
		}
		
		ArrayList<String> ret = new ArrayList<String>();
		
		int minFound = 10;
		for(int i2=0; i2<Constants.NUM_CELLS_VERT; i2++) {
			for(int j2=0; j2<Constants.NUM_CELLS_HORI; j2++) {
				
				int curMinFound = getMinNumAwayAtTopLeftLocation(i2, j2, playerIndex, board);
				if(curMinFound < minFound) {
					minFound = curMinFound;
				}
				
				if(curMinFound == n) {
					
					ret.addAll(getListLocationToFillMinNumAwayAtTopLeftLocation(i2, j2, playerIndex, board));
					
				}
			}
		}
		
		//Deduplicate:
		for(int i2=0; i2<ret.size(); i2++) {
			for(int j2=i2+1; j2<ret.size(); j2++) {
				if(ret.get(i2).equals(ret.get(j2))) {
					ret.remove(j2);
					j2--;
				}
			}
		}
		//End Deduplicate
		
		if(ret.size() > 0) {
			System.out.println("Potential double threat locations:");
			for(int i=0; i<ret.size(); i++) {
				System.out.println(ret.get(i));
			}
		}
		System.out.println("Min found for n = " + n + ": " + minFound);
		return ret;
	}
	
	public static ArrayList<String> getListLocationToFillMinNumAwayAtTopLeftLocation(int i, int j, int playerIndex, int board[][]) {

		ArrayList<String> list = new ArrayList<String>();
		
		int minAway = 6;
		for(int k=0; k<BoardStateStatic.allSnakes.length; k++) {
			
			int numMismatch = 0;
			int potential[] = new int[2];
			
			ArrayList<String> tmplist = new ArrayList<String>();
			
			for(int i3=0; i3<BoardStateStatic.allSnakes[k].length; i3++) {
				for(int j3=0; j3<BoardStateStatic.allSnakes[k][0].length; j3++) {
					
					int effectiveI = i + i3;
					int effectiveJ = j + j3;
					
					
					if(effectiveI >= Constants.NUM_CELLS_VERT || effectiveJ>=Constants.NUM_CELLS_HORI) {
						numMismatch=7;
						continue;
						
					} else if(BoardStateStatic.allSnakes[k][i3][j3] == true && board[effectiveI][effectiveJ] == getOtherPlayer(playerIndex)) {
						numMismatch=7;
						continue;
					}
					
					if(BoardStateStatic.allSnakes[k][i3][j3] == true && board[effectiveI][effectiveJ] == Constants.EMPTY) {
						numMismatch++;
						potential[0] = effectiveI;
						potential[1] = effectiveJ;
						
						tmplist.add(potential[0] + "," + potential[1]);
						continue;
					}
				}
			}
			
			if(numMismatch < minAway) {
				minAway = numMismatch;
				list = new ArrayList<String>();
				
				list.addAll(tmplist);
				
			} else if(numMismatch == minAway){
				list.addAll(tmplist);
			}
			
		}
		
	
		return list;
	}
	
	public static int getOtherPlayer(int playerIndex) {
		if(playerIndex == Constants.PLAYER_1) {
			return Constants.PLAYER_2;
		} else {

			return Constants.PLAYER_1;
		}
	}
	
	
	public static boolean hasDoubleThreat(int playerIndex) {
		
		if(isNAway(playerIndex, 2)) {
			System.out.println("2 away somewhere");
			
			if(getDoubleThreatWinLocation(playerIndex) != null) {
				return true;
			}
			
		} else {
			System.out.println("not 2 away...");
		}
		
		return false;
	}
	
	public static int[] getDoubleThreatWinLocation(int playerIndex) {
		
		int board[][] = hardcodeBoard();
		
		ArrayList<String> listTwoAway = getListToFillNAway(playerIndex, 2, board);
		
		for(int index=0; index < listTwoAway.size(); index++) {
			
			int curLocation[] = covertStringToCoord(listTwoAway.get(index));
			
			board[curLocation[0]][curLocation[1]] = playerIndex;
			
			int tmpLocationReply[] = getNAway(playerIndex, 1, board);
			
			if(tmpLocationReply != null) {
				
				if(board[tmpLocationReply[0]][tmpLocationReply[1]] != Constants.EMPTY) {
					System.out.println("ERROR in getDoubleThreatWinLocation: expected empty, but not empty");
					System.exit(1);
				}
				board[tmpLocationReply[0]][tmpLocationReply[1]] = getOtherPlayer(playerIndex);
				
				if(getNAway(playerIndex, 1, board) != null) {
					

					board[tmpLocationReply[0]][tmpLocationReply[1]] = Constants.EMPTY;
					board[curLocation[0]][curLocation[1]] = Constants.EMPTY;
					
					System.out.println("FOUND DOUBLE THREAT for player " + playerIndex + "!");
					return curLocation;
				}
				
			
			} else {
				System.out.println("ERROR in getDoubleThreatWinLocation: expected 1 cell away, but not 1 cell away");
				System.exit(1);
			}
			
			board[tmpLocationReply[0]][tmpLocationReply[1]] = Constants.EMPTY;
			board[curLocation[0]][curLocation[1]] = Constants.EMPTY;
			
			System.out.println("loop!");
		}
		
		return null;
	}
	
	public static int[][] hardcodeBoard() {
		int board[][] = new int[BoardStateStatic.state.length][BoardStateStatic.state[0].length];
		
		for(int i=0; i<board.length; i++) {
			for(int j=0; j<board[0].length; j++) {
				board[i][j] = BoardStateStatic.get(i, j);
			}
		}
		
		return board;
	}
	
	public static int[] covertStringToCoord(String scoord) {
		return new int[] {Integer.parseInt(scoord.split(",")[0]), Integer.parseInt(scoord.split(",")[1])};
	}
	
}
