package utils;

import java.util.ArrayList;

public class Mapping {

	private ArrayList <String> label = new ArrayList<String>();
	
	private ArrayList <Integer> number = new ArrayList<Integer>();
	
	public void Mapping() {
		
	}
	
	public void addLabel(String varName, int value) {
		label.add(varName);
		number.add(value);
	}
	
	public int getNumberFromLabel(String varName) {
		for(int i=0; i<label.size(); i++) {
			if(label.get(i).equals(varName)) {
				return number.get(i);
			}
		}
		
		return -1;
	}
	
	public int size() {
		return label.size();
	}
	
	public String getLabel(int index) {
		return label.get(index);
	}
	
	public int getValue(int index) {
		return number.get(index);
	}
	
	public int getIndexFromLabel(String thislabel) {
		for(int i=0; i<label.size(); i++) {
			if(label.get(i).equals(thislabel)) {
				return i;
			}
		}
		
		return -1;
	}
}
