package utils;

import java.util.ArrayList;

public class Mapping {

	//TODO: can't this mapping file be better
	//with just get and set?
	
	public ArrayList <String> label = new ArrayList<String>();
	
	public ArrayList <Long> number = new ArrayList<Long>();
	
	
	
	public void set(String varName, long value) {
		if(getIndexFromLabel(varName) == -1) {
			addLabel(varName, value);
		} else {
			number.set(getIndexFromLabel(varName), value);
		}
	}
	
	public long get(String varName) {
		if(getIndexFromLabel(varName) != -1) {
			return number.get(getIndexFromLabel(varName));
		} else {
			return 0;
		}
	}
	
	public boolean has(String varName) {
		if(getIndexFromLabel(varName) != -1) {
			return true;
		} else {
			return false;
		}
	}

	public int size() {
		return label.size();
	}
	
	public String getLabel(long value) {
		for(int i=0; i<label.size(); i++) {
			if(number.get(i).equals(value)) {
				return label.get(i);
			}
		}
		
		return "";
	}
	
	public int getIndexFromLabel(String thislabel) {
		for(int i=0; i<label.size(); i++) {
			if(label.get(i).equals(thislabel)) {
				return i;
			}
		}
		
		return -1;
	}
	
	private void addLabel(String varName, long value) {
		label.add(varName);
		number.add(value);
	}
	
	
	
}
