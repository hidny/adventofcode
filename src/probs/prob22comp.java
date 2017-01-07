package probs;

public class prob22comp {


	private prob22Cluster cluster;
	
	private int x = -1;
	private int y = -1;

	private int size;
	private int used;
	private int avail;
	
	public prob22comp(int x, int y, int size, int used, int avail) {
		this.x =x;
		this.y = y;
		this.size = size;
		this.used = used;
		this.avail = avail;
		
	}
	
	public String toString2() {
		return "( " + this.x + ", " + this.y + ") size: " + this.size + " used: (" + this.used + ", " + this.avail + ")";
		
	}
	
	public String toString() {
		String temp = "(" + this.used + ", " + this.avail + ")      ";
		//String temp = ""+ this.size+ "                      ";
		if(temp.length() > 10) {
			temp = temp.substring(0, 10);
		}
		
		return temp;
	}
	
	public boolean isUsedZero() {
		if(this.used == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean fitsInside(prob22comp other) {
		if(this.used <= other.avail) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean couldFitInside(prob22comp other) {
		if(this.used <= other.size) {
			return true;
		} else {
			return false;
		}
	}
	
	
	
}
