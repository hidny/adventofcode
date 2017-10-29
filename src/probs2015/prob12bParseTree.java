package probs2015;

import java.util.ArrayList;

public class prob12bParseTree {

	private boolean isRed = false;
	
	private boolean isCurly = false;
	
	private int count = 0;
	
	private int endIndex = -1;
	
	private ArrayList<prob12bParseTree> subTrees = new ArrayList<prob12bParseTree>();
	
	
	public int sumNonRed() {
		if(isRed) {
			return 0;
		}
		int total = count;
		
		for(int i=0; i<subTrees.size(); i++) {
			total += subTrees.get(i).sumNonRed();
		}
		
		return total;
		
	}
	
	public prob12bParseTree(String line) {
		this(line, 0);
	}
	
	public prob12bParseTree(String line, int index) {
		if(line.charAt(index) == '{') {
			isCurly = true;
		} else if(line.charAt(index) == '[') {
			isCurly = false;
		}
		int mult = 1;
		int tempNum = 0;
		for(int i=index+1; i<line.length(); i++) {
			//Collect number
			
			if(line.charAt(i) >= '0' && line.charAt(i) <= '9') {
				tempNum = 10 * tempNum + (int)(line.charAt(i) - '0');
			} else if(tempNum > 0) {
				count += mult*tempNum;
				tempNum = 0;
				mult = 1;
			}
			//Handle negative numbers
			if(line.charAt(i) == '-' ) {
				mult = -1;
			}
			
			//collect subtree
			if(line.charAt(i) == '{' || line.charAt(i) == '[') {
				subTrees.add(new prob12bParseTree(line, i));
				
				i = subTrees.get(subTrees.size()-1).endIndex;
				
			} else if(line.charAt(i) == '}' || line.charAt(i) == ']') {
				endIndex = i;
				return;
			}
			
			if(i > 2 && isCurly && prob12b.PART2 == true) {
				if(line.substring(i-2, i+1).equals("red")) {
					isRed = true;
				}
			}
		}
		
	}
	

}
