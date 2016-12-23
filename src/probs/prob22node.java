package probs;

public class prob22node {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public static prob22node array[][] = new prob22node[100][100];
	
	private int x = -1;
	private int y = -1;

	private int size;
	private int used;
	private int avail;
	
	public prob22node(int x, int y, int size, int used, int avail) {
		this.x =x;
		this.y = y;
		this.size = size;
		this.used = used;
		this.avail = avail;
		
		array[y][x] = this;
	}
	
	public String toString() {
		return "( " + this.x + ", " + this.y + ") size: " + this.size + " used: " + this.used + " avail: " + this.avail;
		
	}
	
	public boolean isUsedZero() {
		if(this.used == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean fitsInside(prob22node other) {
		if(this.used <= other.avail) {
			return true;
		} else {
			return false;
		}
	}
}
