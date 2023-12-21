package probs2023;
import java.io.File;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

import number.IsNumber;
import utils.Mapping;
import utils.Sort;

public class prob20obj {

	
	
	public ArrayList<String> inputLabels = new ArrayList<String>();
	
	public ArrayList<Boolean> setting = new ArrayList<Boolean>();
	
	
	public boolean allOn() {
		
		for(int i=0; i<setting.size(); i++) {
			if(setting.get(i) == false) {
				return false;
			}
		}
		
		return true;
	}
	
	public void modifyLabel(String source, boolean pulse) {
		
		for(int i=0; i<inputLabels.size(); i++) {
			if(source.equals(inputLabels.get(i))) {
				setting.set(i, pulse);
				return;
			}
		}
		
		System.out.println();
		
		System.out.println("Oops! Not there! " + source);
		for(int i=0; i<inputLabels.size(); i++) {
			System.out.println(inputLabels.get(i));
		}
		System.exit(1);
	}
	
	public void addLabel(String source) {
		inputLabels.add(source);
		setting.add(false);
	}
}
