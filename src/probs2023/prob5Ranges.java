package probs2023;

import java.util.ArrayList;

public class prob5Ranges {

	
	
	long start;
	
	long end;
	
	public prob5Ranges(long start, long end) {
		
		this.start = start;
		this.end = end;
		//prev.add(this);
	}
	
	public String toString() {
		return "(" + this.start +", " + this.end + ")";
	}
	
	public prob5Ranges hardCopy() {
		return new prob5Ranges(this.start, this.end);
	}
	
	/*
	private static ArrayList<prob5Ranges> prev = new ArrayList<prob5Ranges>();
	
	private static ArrayList<prob5Ranges> cur = new ArrayList<prob5Ranges>();
	private static ArrayList<prob5Ranges> prevUsed = new ArrayList<prob5Ranges>();

	public static void addRange(int dest, int source, int length) {
		
		for(int i=0; i<prev.size(); i++) {
			if(source + length >= prev.get(i).start || source < prev.get(i).end ) {
				
				long startSourceRange = Math.max(source, prev.get(i).start);
				long endSourceRange = Math.min(source + length, prev.get(i).end);
				
				long diff = dest - source;

				prob5Ranges prevUsedOne = new prob5Ranges(startSourceRange, endSourceRange);
				
				prob5Ranges newOne = new prob5Ranges(startSourceRange + diff, endSourceRange + diff);
				cur.add(newOne);
				
				
				prevUsed.add(prevUsedOne);
				
			}
		}
	}
	
	public static void addPrevUnused() {
		
		for(int i=0; i<prev.size(); i++) {
			for(int j=0; j<prevUsed.size(); j++) {
				
				//if(prev.get(i).start <= prevUsed.end)
				
			}
		}
	}
	*/
	
	
}
