package probs2024;

public class prob25obj {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public boolean table[][];
	
	public int heights[] = null;
	
	public void addTable(String input) {
		
		String tokens[] = input.split("\n");
		
		table = new boolean[tokens.length][tokens[0].length()];
		for(int i=0; i<tokens.length; i++) {
			for(int j=0; j<tokens[0].length(); j++) {
				
				if(tokens[i].charAt(j) == '#') {
					table[i][j] = true;
				} else {
					table[i][j] = false;
				}
			}
		}
	}
	
	//10825
	public boolean noOverlap(prob25obj other) {
	
		int minHeight = Math.min(this.table.length,  other.table.length);
		
		for(int j=0; j<this.table[0].length; j++) {
			
			if(heights[j] + other.heights[j] >= minHeight - 1) {
				
				return false;
			}
		}
		
		return true;
	}
	
	public int[] getHeights(boolean isLock) {
		
		if(heights != null) {
			return heights;
		}
		
		heights = new int[table[0].length];
		
		int startI = 1;
		int multI = 1;
		if(isLock == false) {
			startI = table.length - 2;
			multI = -1;
		}
		
		
		for(int j=0; j<table[0].length; j++) {
			int height = 0;
			for(int i=startI; i < table.length && i>=0 && table[i][j]; i+=multI) {
				height++;
			}
			
			heights[j] = height;
			//System.out.println(heights[j]);
		}

		//System.out.println();
		
		
		return heights;
	}
}
