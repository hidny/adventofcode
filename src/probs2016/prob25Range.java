package probs2016;

import java.util.ArrayList;
import java.util.Hashtable;

import arithmetic.ArithNode;

public class prob25Range {
	
	public static int NUM_REGISTERS = 4;
	
	private long min = 0;
	private boolean hasMax = false;
	private long max = 0;
	
	boolean includesRangeOfValues = true;
	
	ArrayList <ArithNode> included = new ArrayList<ArithNode>();
	ArrayList <ArithNode> excluded = new ArrayList<ArithNode>();
	
	//TODO: be able to do operations on this object.
	private int currentIndex;
	private ArrayList<String> lines;
	
	Hashtable<String, ArithNode> equations = new Hashtable<String, ArithNode>();
	
	public prob25Range(ArrayList<String> lines, int currentIndex) {
		this.lines = lines;
		this.currentIndex = currentIndex;
		
		for(int i=0; i<NUM_REGISTERS; i++) {
			if(i > 0 ) {
				equations.put( "" + (char)('a' + i), new ArithNode(0));
			} else {
				equations.put( "" + (char)('a' + i), new ArithNode("a"));
			}
		}
		
	}

	public ArrayList<String> getLines() {
		return lines;
	}
	
	public Hashtable<String, ArithNode> getCurrentEffects() {
		return equations;
	}
	
	public void setCurrentEffects(ArithNode effects[]) {
		if(effects.length != NUM_REGISTERS) {
			System.out.println("ERROR: effects size is not the right size");
			System.exit(1);
		}
		
		for(int i=0; i<NUM_REGISTERS; i++) {
			equations.remove("" + (char)('a' + i));
			equations.put( "" + (char)('a' + i), effects[i]);
		}
	}
	
	public void printCurrentEffect() {
		System.out.println("Current Changes in range object:");
		for(int i=0; i<NUM_REGISTERS; i++) {
			System.out.println( "" + (char)('a' + i) + " = " + equations.get("" + (char)('a' + i)));
		}
		System.out.println("------------");
	}
	
	public void addToExcl(ArithNode a) {
		excluded.add(a);
		System.out.println("0 = " + a.simplifiedCopy() + " is excluded.");
	}

}
