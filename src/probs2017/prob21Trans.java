package probs2017;

import java.io.File;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

import utils.Mapping;

public class prob21Trans {

	boolean start[][];
	
	boolean end[][];
	public prob21Trans(String line) {
		String start = line.split("=>")[0].trim();

		String end = line.split("=>")[1].trim();
		
		this.start = turnToArray(start);
		this.end = turnToArray(end);
		
	}
	
	public static boolean[][] turnToArray(String desc) {
		String lines[] = desc.split("/");
		boolean ret[][] = new boolean[lines.length][lines.length];
		
		for(int i=0; i<lines.length; i++) {
			for(int j=0; j<lines[i].length(); j++) {
				if(lines[i].charAt(j) == '#') {
					ret[i][j] = true;
				} else {
					ret[i][j] = false;
				}
			}
		}
		
		return ret;
	}
	
	public static boolean[][] transform(ArrayList<prob21Trans> list, boolean current[][]) {
		int newLength = 0;
		int subGridLength = 0;
		if(current.length % 2 ==0) {
			newLength = 3 * current.length / 2;
			subGridLength = 2;
		} else if(current.length % 3 ==0) {
			newLength = 4 * current.length / 3;
			subGridLength = 3;
			
		} else {
			System.out.println("Broken!");
			System.exit(1);
		}
		boolean ret[][] = new boolean[newLength][newLength];
		
		
		for(int i=0; i<current.length / subGridLength; i+=1) {
			for(int j=0; j<current.length / subGridLength; j+=1) {
				
				boolean key[][] = new boolean[subGridLength][subGridLength];
				for(int a=0; a<subGridLength; a++) {
					for(int b=0; b<subGridLength; b++) {
						key[a][b] = current[i*subGridLength + a][j*subGridLength + b];
					}
				}
				
				boolean replace[][] = getMatching(list, key);
				
				for(int a=0; a<replace.length; a++) {
					for(int b=0; b<replace.length; b++) {
						ret[i * (1+ subGridLength) + a][j * (1+ subGridLength) + b] = replace[a][b];
					}
				}
			}
		}
		
		return ret;
	}
	
	public static boolean[][] getMatching(ArrayList<prob21Trans> list, boolean key[][]) {
		for(int i=0; i<list.size(); i++) {
			if(list.get(i).matches(key)) {
				return list.get(i).end;
			}
		}
		
		
		return null;
	}
	
	public boolean matches(boolean key[][]) {
		if(key.length != this.getSize()) {
			return false;
		}
		
		boolean matches = true;
		for(int i=0; i<key.length; i++) {
			for(int j=0; j<key[0].length; j++) {
				if(key[i][j] != start[i][j]) {
					matches = false;
				}
			}
		}
		if(matches) {
			return true;
		}
		
		matches = true;
		for(int i=0; i<key.length; i++) {
			for(int j=0; j<key[0].length; j++) {
				if(key[i][j] != start[start.length - 1 - i][j]) {
					matches = false;
				}
			}
		}
		if(matches) {
			return true;
		}
		
		matches = true;
		for(int i=0; i<key.length; i++) {
			for(int j=0; j<key[0].length; j++) {
				if(key[i][j] != start[i][start.length - 1 - j]) {
					matches = false;
				}
			}
		}
		if(matches) {
			return true;
		}
		
		matches = true;
		for(int i=0; i<key.length; i++) {
			for(int j=0; j<key[0].length; j++) {
				if(key[i][j] != start[start.length - 1 - i][start.length - 1 - j]) {
					matches = false;
				}
			}
		}
		if(matches) {
			return true;
		}
		
		matches = true;
		
		for(int i=0; i<key.length; i++) {
			for(int j=0; j<key[0].length; j++) {
				if(key[i][j] != start[j][i]) {
					matches = false;
				}
			}
		}
		if(matches) {
			return true;
		}
		
		matches = true;
		for(int i=0; i<key.length; i++) {
			for(int j=0; j<key[0].length; j++) {
				if(key[i][j] != start[j][start.length - 1 - i]) {
					matches = false;
				}
			}
		}
		if(matches) {
			return true;
		}
		
		matches = true;
		for(int i=0; i<key.length; i++) {
			for(int j=0; j<key[0].length; j++) {
				if(key[i][j] != start[start.length - 1 - j][i]) {
					matches = false;
				}
			}
		}
		if(matches) {
			return true;
		}
		
		matches = true;
		for(int i=0; i<key.length; i++) {
			for(int j=0; j<key[0].length; j++) {
				if(key[i][j] != start[start.length - 1 - j][start.length - 1 - i]) {
					matches = false;
				}
			}
		}
		if(matches) {
			return true;
		}
		
		return matches;
	}
	
	public int getSize() {
		return start.length;
	}
}
