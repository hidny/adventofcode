package probs;

import java.util.ArrayList;

import utils.MD5;

public class prob17obj {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	private ArrayList<String> directions = new ArrayList<String>();
	private ArrayList<String> md5Hash = new ArrayList<String>();
	
	public prob17obj() {
		
	}
	
	public void add(String passcode, String direction) {
		this.directions.add(direction);
		this.md5Hash.add(MD5.getHexFromMD5(passcode + direction));
	}
	
	
	public boolean isUpOpen(int i) {
		return isOpen(i, 0);
	}
	

	public boolean isDownOpen(int i) {
		return isOpen(i, 1);
	}

	public boolean isLeftOpen(int i) {
		return isOpen(i, 2);
	}

	public boolean isRightOpen(int i) {
		return isOpen(i, 3);
	}
	
	public boolean isOpen(int i, int dirIndex) {
		if(this.md5Hash.get(i).charAt(dirIndex) >= 'b' && this.md5Hash.get(i).charAt(dirIndex) <= 'f') {
			return true;
		} else {
			return false;
		}
	}
	
	public int getSize() {
		return directions.size();
	}
	
	public String getDirection(int i) {
		return directions.get(i);
	}
}
