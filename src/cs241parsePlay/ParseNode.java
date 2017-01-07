package cs241parsePlay;

import java.util.*;


public class ParseNode {
	private String name;
	
	//The derivation. (ID -> var )
	//				or: expr -> expr PLUS term
	private String derivation = "";
	
	//True if the derivation is a string in the code.
	private boolean isTerminal = true;
	
	//A pointer towards the parent.
	//ParseNode parent;
	
	//parseNode Children:
	private ArrayList<ParseNode> children;
	
	
	

	public ParseNode(String name) {
		this(name, "");
	}
	
	public ParseNode(String name, String derivation) {
		this.name = name;
		this.derivation = derivation;
		this.isTerminal = true;
	}
	
	public ParseNode(String name, String derivation, ArrayList <ParseNode> children) {
		this.name = name;
		this.derivation = derivation;
		
		this.children = new ArrayList<ParseNode>();
		
		for(int i=0; i<children.size(); i++) {
			this.children.add(children.get(i));
		}
		
		this.isTerminal = false;
	}
	
	//Return children.
	public ArrayList<ParseNode> getChildren() {
		if(this.isTerminal() == false) {
			return this.children;
		} else {
			System.err.println("ERROR: getChildren was called for a leaf in the Parse tree. (" + this.derivation +")");
			System.exit(0);
			return new ArrayList<ParseNode>();
		}
	}
	
	public String getDerivation() {
		return derivation;
	}

	public void setDerivation(String derivation) {
		this.derivation = derivation;
	}

	public boolean isTerminal() {
		return isTerminal;
	}

	public void setTerminal(boolean isTerminal) {
		this.isTerminal = isTerminal;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
		

	public void setChildren(ArrayList<ParseNode> children) {
		this.children = children;
	}
	
	public void setChild(int index, ParseNode child) {
		this.children.set(index, child);
	}
	
	//post: makes a hard copy of the parse tree from the root node specified.
	public static ParseNode makeHardCopy(ParseNode root) {
		String name =root.getName();
		String derivation = root.getDerivation();
		boolean isTerminal = root.isTerminal();
		
		
		//If the node is non-terminal:
		if(isTerminal == false) {
			ArrayList<ParseNode> children = new ArrayList<ParseNode>();
			
			for(int i=0; i<root.getChildren().size(); i++) {
				children.add(makeHardCopy(root.getChildren().get(i)) );
			}
			return new ParseNode(name, derivation, children);
		} else {
			return new ParseNode(name, derivation);
		}
		
	}
	
	
	public static ParseNode createNonTerminal(String name) {
		ParseNode newNode;
		
		newNode = new ParseNode(name);
			
		newNode.setTerminal(false);
		
		return newNode;
	}
	
	
	
	//post: creates a ParseNode for every child of node.
	public void createNewChildrenNodes(cs241parse.Grammar grammar) {
		
		ArrayList<ParseNode> newChildren = new ArrayList<ParseNode>();
		String derivationTokens[] = this.getDerivation().split(" ");
		
		ParseNode newChild;
		//Create new leaf node: (i=0 is the LHS of the derivation.)
		for(int i=1; i<derivationTokens.length; i++) {
			newChild = new ParseNode(derivationTokens[i]);
			
			//set wether the new node is a terminal or not.
			newChild.setTerminal(!grammar.isNonTerminal(derivationTokens[i]));
			
			newChildren.add(newChild);	
		}
		
		this.setChildren(newChildren);
	}
}
