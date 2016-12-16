package utilsFromProjectEuler;


public class LetterCount implements Comparable{

	public static int NUM_LETTERS = 26;
	
	char c;
	
	public int number;
	
	public static LetterCount[] initLetters() {
		LetterCount ret[] = new LetterCount[26];
		for(int i=0; i<ret.length; i++) {
			ret[i] = new LetterCount((char)((int)('a') + i), 0);
		}
		return ret;
	}
	
	public LetterCount(char c) {
		this.c = c;
		this.number = 0;
	}
	
	public LetterCount(char c, int number) {
		this.c = c;
		this.number = number;
	}
	
	public void increment() {
		this.number++;
	}
	
	public char getChar() {
		return c;
	}

	@Override
	public int compareTo(Object o) {
		LetterCount other = (LetterCount)o;
		
		if(this.number != other.number) {
			if(this.number < other.number) {
				return 1;
			} else {
				return -1;
			}
		}
		String temp1 = (this.c + "").toLowerCase();
		String temp2 = (other.c + "").toLowerCase();
		
		if(temp1.toCharArray()[0] > temp2.toCharArray()[0]) {
			return 1;
		} else {
			return -1;
		}
		
	}
}
