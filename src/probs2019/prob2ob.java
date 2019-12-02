package probs2019;

import java.util.ArrayList;

import probs2018.prob4guardShift;
import utils.Sort;

public class prob2ob implements Comparable {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public prob2ob(int num) {
		super();
		this.num = num;
	}

	public int num;
	@Override
	public int compareTo(Object o) {
		
		if(((prob2ob)o).num > this.num) {
			return -1;
		} else if(((prob2ob)o).num < this.num) {
			return 1;
		} else {
			return 0;
		}
	}

}
