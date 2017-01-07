package cs241parse;
import java.util.*;

public class CFGRparser {
	
	//final public static String RULES_FILE_NAME = "A8P4rules.txt";

	
	//Context-free grammar rules:
	private Grammar rules;
	
	//Only here for testing purposes:
	private Stack <String>prodRules = new Stack<String>();
	
	private ParseNode root;
	
	public CFGRparser(Grammar grammar) {
		this.rules = grammar;
	}
	
	//Create a parse Tree:
	//pre: the input agrees with the CFG rules.
	public void parseCodeDerivation(Scanner in) {
		parseCodeDerivation(in, rules.getStartState(), true);
	}
	
	//pre: the input agrees with the CFG rules starting at the given startState:
	public void parseCodeDerivation(Scanner in, int startState, boolean isWholeFile) {
		Stack <ParseNode>currentStack = new Stack<ParseNode>();
		
		Stack <ParseNode>inputStack = new Stack<ParseNode>();
		Stack <Integer> states = new Stack<Integer>();
		
		
		//Next token:
		String token;
		
		//Vars to create a parse tree:
		String codeToken;
		Stack <ParseNode>childrenStack = new Stack<ParseNode>();
		ArrayList <ParseNode>children = new ArrayList<ParseNode>();
		
		String prodRule = null;
		String prodRuleToken[];
		
		Integer newState;
		
		int inputNumber = 0;
		states.push(startState);
		
		while(in.hasNext()) {
			inputNumber++;
		
			token = in.next();
			//The code that comes with the token should come right after:
			codeToken = in.next();
			//String name, int state, String derivation
			inputStack.push(new ParseNode(token, token + " " + codeToken));
			
			 do {
				//Check for Reduction if there's something on the stack.
				if(currentStack.size() > 0) {
				 prodRule = rules.getReduction(states.peek().intValue(), inputStack.peek().getName());
				}
				
				while (prodRule != null) {
					//System.out.println(prodRule);
					prodRules.push(prodRule);
					
					prodRuleToken = prodRule.split(" ");
					
					//Make the reduction in the stack:
					for(int i=prodRuleToken.length - 1; i>0; i--) {
						//System.out.println("Popped: " + currentStack.peek().getName() + "\nExpected: " + prodRuleToken[i]);
						childrenStack.push(currentStack.pop());
						
						states.pop();
					}
					
					children = toArrayList(childrenStack);
					inputStack.push(new ParseNode(prodRuleToken[0], prodRule, children));
					
					children.clear();
					
					prodRule = rules.getReduction(states.peek().intValue(), inputStack.peek().getName());
					
				}
				//END check for reduction.
				
				//shift the state
				newState = new Integer(rules.getShift(states.peek().intValue(), inputStack.peek().getName()));
				states.push(newState);
				
				//Add token to the current stack:
				currentStack.push(inputStack.pop());
				
				//Check for errors:
				if(states.peek().intValue() == -1) {
					System.out.println("ERROR at " + inputNumber + " (" + token + ", " + codeToken + ")");
					System.exit(0);
				}
				
				//While there's nothing more to read:
			} while(inputStack.isEmpty() == false);
		}
		
	//CREATE THE ROOT:	
		//Once the parsing has reached the end, it still has to create the root:
		assert(currentStack.size() > 1);
		
		prodRule = getProdRuleFromStack(currentStack, isWholeFile);
		
		prodRules.push(prodRule);
		
		prodRuleToken = prodRule.split(" ");
		
		for(int i=prodRuleToken.length - 1; i>0; i--) {
			//System.out.println("Popped: " + currentStack.peek().getName() + "\nExpected: " + prodRuleToken[i]);
			childrenStack.push(currentStack.pop());
			
			states.pop();
		}
		
		children = toArrayList(childrenStack);
		root = new ParseNode(prodRuleToken[0], prodRule, children);
		
		//do NOT assert that the whole of currentStack is empty. (It might not be.)
	}
	
	private String getProdRuleFromStack(Stack <ParseNode>currentStack, boolean isWholeFile) {
		Object tokens[] = currentStack.toArray();
		String children = "";
		
		//If we're parsing the whole file, then the whole stack should be used.
		if(isWholeFile == true) {
			for(int i=0; i<tokens.length; i++) {
				children += ((ParseNode)tokens[i]).getName() + " ";	
			}
			
			//TODO: better bug fix.
			//Technical thing:
			//ugly bug fix... :(
		//If we're just implementing a code segment, the whole stack doesn't need to be used.
		} else {
			for(int i=1; i<tokens.length; i++) {
				children += ((ParseNode)tokens[i]).getName() + " ";	
			}
		}
		//For testing:
		//System.out.println(children);
		this.rules.findLHS(children);
		
		return this.rules.findLHS(children);
	}

	//Post: returns the top-down derivation:
	private String getTopDownLeftDerivation() {
		return getTopDownLeftDerivation(this.root);
	}
	
	//It's an overloaded method. :)
	private String getTopDownLeftDerivation(ParseNode node) {
		String out = node.getDerivation() + "\n";
		if(node.isTerminal() == false) {
			for(int i=0; i<node.getChildren().size(); i++) {
				out += getTopDownLeftDerivation(node.getChildren().get(i));
			}
		}
		
		return out;
	}
	
	private void printProdRulesCFGL() {
		Stack<String> temp = new Stack<String>();
		
		//Print it out
		while(this.prodRules.isEmpty()==false) {
			System.out.println(this.prodRules.peek());
			temp.push(this.prodRules.pop());
		}
		
		//Put the stack back the way it was:
		while(temp.isEmpty()==false) {
			this.prodRules.push(temp.pop());
		}
		
		
	}
	
	private ArrayList<ParseNode> toArrayList(Stack<ParseNode> childrenStack) {
		ArrayList<ParseNode> alist = new ArrayList<ParseNode>();
		while(childrenStack.isEmpty()==false) {
			alist.add(childrenStack.pop());
		}
		return alist;
	}
	
	//private ParseNode root;
	public ParseNode getRoot() {
		return root;
	}
	
}
