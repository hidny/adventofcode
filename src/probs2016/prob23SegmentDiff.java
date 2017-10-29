package probs2016;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;

import arithmetic.ArithNode;

public class prob23SegmentDiff {

	public static int NUM_REGISTERS = 4;
	
	private ArithNode registerChanges[][][];
	
	private int startIndex;
	private int endIndex;
	
	private int exitIndex;
	
	private boolean splitsOnInput = false;
	private boolean lineChangedOnTgl = false;
	
	private ArithNode numTimesRun[];
	
	
	//Include start line and incl end line...
	//there's endIndex + 2 - startIndex
	public prob23SegmentDiff(int startIndex, int endIndex) {

		this.startIndex = startIndex;
		this.endIndex = endIndex;
		
		this.exitIndex = endIndex + 1;
		
		registerChanges = new ArithNode[this.endIndex-this.startIndex + 2][this.endIndex-this.startIndex + 2][NUM_REGISTERS];
		
		for(int i=0; i<registerChanges.length; i++) {
			for(int j=0; j<registerChanges[i][0].length; j++) {
				registerChanges[i][i][j] = new ArithNode("" + (char)('a' + j));
			}
		}
		
		this.numTimesRun = new ArithNode[this.endIndex - this.startIndex + 2];
		for(int i=0; i<this.numTimesRun.length; i++) {
			this.numTimesRun[i] = new ArithNode(0);
		}
	}

	public prob23SegmentDiff makeHardCopy() {
		prob23SegmentDiff newRegisterDiff = new prob23SegmentDiff(this.startIndex, this.endIndex);
		for(int i=0; i<this.registerChanges.length; i++) {
			for(int j=0; j<this.registerChanges[0].length; j++) {
				for(int k=0; k<this.registerChanges[i][j].length; k++) {
					if(this.registerChanges[i][j][k] != null) {
						newRegisterDiff.registerChanges[i][j][k] = this.registerChanges[i][j][k].simplifiedCopy();
					} else {
						newRegisterDiff.registerChanges[i][j][k] = null;
					}
				}
			}
		}
		return newRegisterDiff;
	}
	
	public ArithNode[] getRegisterChanges() {
		return this.getRegisterChanges(this.startIndex, this.endIndex + 1);
	}
	
	
	//Get register change from right before startline to right before endLine.
	public boolean alreadyAnalysed() {
		if(getRegisterChanges(this.startIndex, this.endIndex + 1)[0] != null) {
			return true;
		} else {
			return false;
		}
	}
	
	public ArithNode[] getRegisterChanges(int startLine, int endLine) {
		if(startLine > endLine) {
			System.err.println("WARNING: trying to find backwards changes.");
			return null;
		}
		
		//Make a hard copy
		ArithNode ret[] = new ArithNode[this.registerChanges[startLine - this.startIndex][endLine - this.startIndex].length];
		for(int i=0; i<ret.length; i++) {
			ret[i] = this.registerChanges[startLine - this.startIndex][endLine - this.startIndex][i];
		}
		
		return ret;
	}
	
	public void setRegisterChange(int startLine, int endLine, int registerIndex, ArithNode change) {
		
		this.registerChanges[startLine - this.startIndex][endLine - this.startIndex][registerIndex] = change.simplifiedCopy();
		System.out.println("From: " + startLine + " to " + endLine + " for var index " + registerIndex + ":");
		System.out.println(this.registerChanges[startLine - this.startIndex][endLine - this.startIndex][registerIndex].getEquation());
	}
	
	public ArithNode getNumTimesRan(int lineIndex) {
		return this.numTimesRun[lineIndex - this.startIndex];
	}
	
	public void setNumTimesRanToValue(int lineIndex, ArithNode value) {
		this.numTimesRun[lineIndex - this.startIndex] = value.simplifiedCopy();
	}
	
	public void multNumTimesRan(int lineIndex, ArithNode mult) {
		this.numTimesRun[lineIndex - this.startIndex] = new ArithNode('*', this.numTimesRun[lineIndex], mult).simplifiedCopy();
	}
	
	
	
	public String toString() {
		String ret = "";
		ret += "Registers:" + "\n";
		
		int j;
		
		for(int i=0; i<NUM_REGISTERS; i++) {
			j =0;
			for(j=this.endIndex + 1; j>=this.startIndex; j--) {
				if(this.getRegisterChanges(this.startIndex, j)[0] != null) {
					break;
				}
			}
			ret += ((char)('a' + i)) + " = " + this.getRegisterChanges(this.startIndex, j)[i].getEquation() + "\n";
		}
		
		ret += "--------------------\n";
		return ret;
	}
	
	public int getStartIndex() {
		return this.startIndex;
	}
	
	public int getEndIndex() {
		return this.endIndex;
	}
	
	public int getExitIndex() {
		return this.exitIndex;
	}
	
	//Set the exit index to something non obvious if it ends in a jump
	public void setExitIndex(int exitInd) {
		this.exitIndex = exitInd;
	}
	
	public void changeEndIndexLocation(int endIndex) {

		this.endIndex = endIndex;
		this.exitIndex = endIndex + 1;
		
		
		ArithNode newRegisterChanges[][][] = new ArithNode[this.endIndex-this.startIndex + 2][this.endIndex-this.startIndex + 2][NUM_REGISTERS];
		
		for(int i=0; i<newRegisterChanges.length; i++) {
			for(int j=0; j<newRegisterChanges[i].length; j++) {
				for(int k=0; k<newRegisterChanges[i][j].length; k++) {
					if(i<this.registerChanges.length && j<this.registerChanges[i].length) {
						newRegisterChanges[i][j][k] = this.registerChanges[i][j][k];
					} else {
						
						if(i != j) {
							newRegisterChanges[i][j][k] = null;
						} else {
							//register changes after no movement is always 
							//a = a
							//b = b
							//c = c
							//d = d
							newRegisterChanges[i][j][k] = new ArithNode("" + (char)('a' + k));
							
						}
					}
					//System.out.println(this.registerChanges[i][j][k]);
				}
				//System.out.println("--------");
			}
		}
		
		this.registerChanges = newRegisterChanges;
		
		ArithNode newNumTimesRun[] = new ArithNode[this.endIndex - this.startIndex + 2];
		
		for(int i=0; i<newNumTimesRun.length; i++) {
			if(i< this.numTimesRun.length) {
				newNumTimesRun[i] = this.numTimesRun[i];
			} else {
				newNumTimesRun[i] = new ArithNode(0);
			}
		}
		this.numTimesRun = newNumTimesRun;
		
	}

	public boolean isSplitsOnInput() {
		return splitsOnInput;
	}

	public void setCasesSplitOnInput() {
		this.splitsOnInput = true;
	}

	public boolean isLineChangedOnTgl() {
		return lineChangedOnTgl;
	}

	public void setLineChangedOnTgl(boolean lineChangedOnTgl) {
		this.lineChangedOnTgl = lineChangedOnTgl;
	}
	
	public void extendLength(ArrayList<String> lines) {
		this.changeEndIndexLocation(lines.size());
		
	}
}
