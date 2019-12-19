package probs2019;

import java.util.ArrayList;
import aStar.AstarNode;

public class prob18edge implements Comparable {

	private int i;
	private int j;
	private int weigth;
	
	public prob18edge(int i, int j, int weigth) {
		super();
		this.i = i;
		this.j = j;
		this.weigth = weigth;
	}


	public int getWeigth() {
		return weigth;
	}


	public int getI() {
		return i;
	}


	public int getJ() {
		return j;
	}


	//AHA:
	//This helps. I have to override equals so the hash will use hashCode... :(
	public boolean  equals (Object object) {
		if(this.toString().equals(object.toString())) {
			return true;
		} else {
			return false;
		}
	}
	
	
	public String toString() {
		if(i < j) {
			return "( " + this.i + ", " + this.j + ")";
		} else {
			return "( " + this.j + ", " + this.i + ")";
			
		}
	}

	@Override
	public int compareTo(Object o) {
		if(this.weigth > ((prob18edge)o).weigth) {
			return 1;
		} else if(this.weigth < ((prob18edge)o).weigth) {
			return -1;
		} else {
			return 0;
		}
	}
}
