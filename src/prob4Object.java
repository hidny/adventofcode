

public class prob4Object implements Comparable{

	public static int NUM_LETTERS = 26;
	
	char c;
	
	int number;
	
	public static prob4Object[] initLetters() {
		prob4Object ret[] = new prob4Object[26];
		for(int i=0; i<ret.length; i++) {
			ret[i] = new prob4Object((char)((int)('a') + i), 0);
		}
		return ret;
	}
	
	public prob4Object(char c) {
		this.c = c;
		this.number = 0;
	}
	
	public prob4Object(char c, int number) {
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
		prob4Object other = (prob4Object)o;
		
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
