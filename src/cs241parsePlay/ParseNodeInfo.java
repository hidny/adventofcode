package cs241parsePlay;

import java.util.ArrayList;

import cs241parse.ParseNode;



//This class extends the parse node by adding more information about every node.
//For now, it has information about the # of decendants it has.


public class ParseNodeInfo extends cs241parse.ParseNode{
	
	public ParseNodeInfo(String name) {
		super(name);
	}
	
	public ParseNodeInfo(String name, boolean isTerminal) {
		super(name);
	}
	
	public ParseNodeInfo(String name, String derivation) {
		super(name, derivation);
	}

	public ParseNodeInfo(String name, String derivation, ArrayList <ParseNode> children) {
		super(name, derivation, children);
	}
	
	//Number of decendants that are changeable: 
	private int numChangeableDescendants = 0;

	//if this is true, then this node is susceptible to change.
	private boolean changeable = false;
	
	//pre: numDecendants >=0
	public void setNumChangeableDescendants(int numDescendants) {
		this.numChangeableDescendants = numDescendants;
	}
	
	public int getNumChangeableDescendants() {
		return numChangeableDescendants;
	}
	
	//pre: the Num Changeable descendants in the children fields are accurate.
	//post: updates the number of changeable descendants.
	/*public void updateNumChangeableDescendants() {
		ParseNodeInfo temp;
		int numDescendants =0;
		for(int i=0; i<this.getChildren().size(); i++) {
			temp = (ParseNodeInfo)this.getChildren().get(i);
			if(temp.isChangeable()) {
				numDescendants++;
			}
			numDescendants += temp.getNumChangeableDescendants();
		}
		
		this.setNumChangeableDescendants(numDescendants);
	}*/

	//pre: the parse tree has node that are ParseNodeInfo type.
	//post: sets the number of decendants in all nodes of the Parse tree
	public void updateAllNumChangeableDescendants() {
		int totalChangeableDescendants=0;
		ParseNodeInfo temp;
		
		if(this.isTerminal() == false) {
			// Recursively count the number of decendants.
			for(int i=0; i<this.getChildren().size(); i++) {
				temp =(ParseNodeInfo)this.getChildren().get(i);
				if(temp.isChangeable()) {
					totalChangeableDescendants += 1;
				}
				temp.updateAllNumChangeableDescendants();
				totalChangeableDescendants += temp.getNumChangeableDescendants();
			}
		}
		
		this.setNumChangeableDescendants(totalChangeableDescendants);
	}

	public boolean isChangeable() {
		return changeable;
	}

	public void setChangeable(boolean changeable) {
		this.changeable = changeable;
	}
		
	//post: makes a hard copy of the parse tree from the root node specified.
	public static ParseNodeInfo makeHardCopy(ParseNode root) {
		
		String name =root.getName();
		String derivation = root.getDerivation();
		boolean isTerminal = root.isTerminal();
		
		ParseNodeInfo ret;
		
		//If the node is non-terminal:
		if(isTerminal == false) {
			ArrayList<ParseNode> children = new ArrayList<ParseNode>();
			
			
			for(int i=0; i<root.getChildren().size(); i++) {
				children.add(makeHardCopy(root.getChildren().get(i)) );
			}
			
			ret = new ParseNodeInfo(name, derivation, children);
		
			//is terminal:
		} else {
			ret = new ParseNodeInfo(name, derivation);	
		}
		
		if(root instanceof ParseNodeInfo) {
			ret.setChangeable(((ParseNodeInfo) root).isChangeable());
			ret.setNumChangeableDescendants(((ParseNodeInfo) root).getNumChangeableDescendants());
		}
		
		return ret;
	}
	

	
	
	public String toString() {
		String ret = this.getDerivation();
		if(this.numChangeableDescendants == 1) {
			ret += " (" + this.numChangeableDescendants + " changeable descendant)";
		} else if(this.numChangeableDescendants > 1) {
			ret += " (" + this.numChangeableDescendants + " changeable descendants)";
		}
		//Add new line:
		ret += "\n";
		if(this.isTerminal() == false) {
			for(int i=0; i<this.getChildren().size(); i++) {
				ret += this.getChildren().get(i);
			}
		}
		
		return ret;
	}
	
	//pre: the parent has the child.
	//post: returns the index of the child.
	public int getIndexOfChild(ParseNode child) {
		ArrayList<ParseNode> children = this.getChildren();
		for(int i=0; i<children.size(); i++) {
			if(children.get(i) == child) {
				return i;
			}
		}
		
		System.err.println("ERROR: int getIndexOfChild. The parent node doesn't have the child node.");
		
		System.exit(0);
		
		return -1;
	}
	
	
	public static ParseNodeInfo createNonTerminal(String name) {
		ParseNodeInfo newNode;
		
		newNode = new ParseNodeInfo(name);
			
		
		newNode.setTerminal(false);
		
		return newNode;
	}
	
	
	//post: creates a ParseNode for every child of node.
	public static void createNewChildrenNodes(ParseNode node, cs241parse.Grammar grammar) {
		
		ArrayList<cs241parse.ParseNode> newChildren = new ArrayList<cs241parse.ParseNode>();
		String derivationTokens[] = node.getDerivation().split(" ");
	
		ParseNodeInfo newChild;
		//Create new leaf node: (i=0 is the LHS of the derivation.)
		for(int i=1; i<derivationTokens.length; i++) {
			newChild = new ParseNodeInfo(derivationTokens[i]);
			
			//set whether the new node is a terminal or not.
			newChild.setTerminal(!grammar.isNonTerminal(derivationTokens[i]));
			
			newChildren.add(newChild);	
		}
		
		node.setChildren(newChildren);
	}
	
}
