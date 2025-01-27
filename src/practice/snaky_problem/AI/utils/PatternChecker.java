package practice.snaky_problem.AI.utils;

import practice.snaky_problem.GUI.Constants;

public class PatternChecker {
	
	//TODO: maybe check around?
	
	//TODO: Bug at the edge. Not returning false when it should

	public static boolean hasNInARow(int table[][], int playerIndex, int n) {
		return hasNInARow(table, playerIndex, n, false);
	}

	public static boolean hasNInARow(int table[][], int playerIndex, int n, boolean checkOpenEnded) {
		
		for(int i=0; i<table.length; i++) {
			for(int j=0; j<table[0].length; j++) {
				
				boolean matchSoFar = true;
				boolean openEndSoFar = false;
				
				for(int i2=0; i2<n && i+i2<table.length; i2++) {
					
					if(table[i + i2][j] != playerIndex) {
						matchSoFar = false;
						break;
					}
				}
				
				if(i-1 >= 0                    && table[i-1][j] == Constants.EMPTY) {
					openEndSoFar = true;
				} else if(i + n < table.length && table[i+n][j] == Constants.EMPTY) {
					openEndSoFar = true;
				}
				
				if(matchSoFar) {
					System.out.println("Found "+ n +" in a row vert");
				}
				
				if(matchSoFar && (! checkOpenEnded || openEndSoFar)) {
					return true;
				}
				
				matchSoFar = true;
				openEndSoFar = false;
				
				for(int j2=0; j2<n && j+j2<table[0].length; j2++) {

					if(table[i][j + j2] != playerIndex) {
						matchSoFar = false;
						break;
					}
				}
				if(matchSoFar) {
					System.out.println("Found "+ n +" in a row hori");
				}
				

				if(j-1 >= 0                       && table[i][j-1] == Constants.EMPTY) {
					openEndSoFar = true;
				} else if(j + n < table[0].length && table[i][j+n] == Constants.EMPTY) {
					openEndSoFar = true;
				}

				if(matchSoFar && (! checkOpenEnded || openEndSoFar)) {
					return true;
				}
				
			}
		}
		
		return false;
	}
	
	
	
	public static int[] getOpenEndOfNInARow(int table[][], int playerIndex, int n) {
		
		int ret[] = new int[2];
		
		for(int i=0; i<table.length; i++) {
			for(int j=0; j<table.length; j++) {
				
				boolean matchSoFar = true;
				boolean openEndSoFar = false;
				
				for(int i2=0; i2<n && i+i2<table.length; i2++) {
					
					if(table[i + i2][j] != playerIndex) {
						matchSoFar = false;
						break;
					}
				}
				
				if(i-1 >= 0                    && table[i-1][j] == Constants.EMPTY) {
					openEndSoFar = true;
					ret[0] = i-1;
					ret[1] = j;
				} else if(i + n < table.length && table[i+n][j] == Constants.EMPTY) {
					openEndSoFar = true;
					ret[0] = i+n;
					ret[1] = j;
				}
				
				if(matchSoFar && (openEndSoFar)) {
					return ret;
				}
				
				matchSoFar = true;
				openEndSoFar = false;
				
				for(int j2=0; j2<n && j+j2<table[0].length; j2++) {

					if(table[i][j + j2] != playerIndex) {
						matchSoFar = false;
						break;
					}
				}
				
				if(j-1 >= 0                       && table[i][j-1] == Constants.EMPTY) {
					openEndSoFar = true;
					ret[0] = i;
					ret[1] = j-1;
				} else if(j + n < table[0].length && table[i][j+n] == Constants.EMPTY) {
					openEndSoFar = true;
					ret[0] = i;
					ret[1] = j+n;
				}

				if(matchSoFar && ( openEndSoFar)) {
					return ret;
				}
				
			}
		}
		
		return null;
	}
	
	
	//TODO: get doubly open-ended...
	
	public static int[] getDoubleOpenEndOfNInARow(int table[][], int playerIndex, int n) {
		
		int ret[] = new int[2];
		
		for(int i=0; i<table.length; i++) {
			for(int j=0; j<table.length; j++) {
				
				boolean matchSoFar = true;
				boolean openEndSoFar = false;
				
				for(int i2=0; i2<n && i+i2<table.length; i2++) {
					
					if(table[i + i2][j] != playerIndex) {
						matchSoFar = false;
						break;
					}
				}
				
				if(i-1 >= 0                    && table[i-1][j] == Constants.EMPTY
						&& i + n < table.length && table[i+n][j] == Constants.EMPTY) {
					openEndSoFar = true;
					ret[0] = i+n;
					ret[1] = j;
				}
				
				if(matchSoFar && (openEndSoFar)) {
					return ret;
				}
				
				matchSoFar = true;
				openEndSoFar = false;
				
				for(int j2=0; j2<n && j+j2<table[0].length; j2++) {

					if(table[i][j + j2] != playerIndex) {
						matchSoFar = false;
						break;
					}
				}
				
				if(j-1 >= 0                       && table[i][j-1] == Constants.EMPTY
						&& j + n < table[0].length && table[i][j+n] == Constants.EMPTY) {
					openEndSoFar = true;
					ret[0] = i;
					ret[1] = j+n;
				}

				if(matchSoFar && ( openEndSoFar)) {
					return ret;
				}
				
			}
		}
		
		return null;
	}
	
}
