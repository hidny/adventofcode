/*
   CFGrl Version 2 -- converts .cfg-r file to equivalent .cfg file

   Author:   Ondrej Lhotak
   Version:  2-071105.0

   Input:  .cfg-r file with a single derivation
   Output: .cfg file with abbreviated forward leftmost version of the derivation

   Supersedes Version 1, available at

     http://www.student.cs.uwaterloo.ca/~cs241/a08/obsolete/CFGrl.java

*/
package cs241parse;

import java.util.*;
import java.io.*;


public class Grammar {
    
	/*public static void main(String[] args) {
    	try {
    		new Grammar(new Scanner(new File("C:/workspace/CS241/A8P3rules.txt")));
    	} catch(Exception e) {
	    	e.printStackTrace();
			System.err.println("ERROR: Couldn't open file");
		}
    }*/
	
    public  Grammar(Scanner input) {
		this.input = input;
    	this.getGrammar();
    }


    private Scanner input;
    private ArrayList<String> terms = new ArrayList<String>(); // terminals
    private ArrayList<String> nonterms = new ArrayList<String>(); // nonterminals
    private ArrayList<String> prods = new ArrayList<String>(); // production rules
    private String startSymbol; // start symbol
    private int numStates;
    private int startState = 0;
    private ArrayList<String> shiftRules[];
    private ArrayList<String> reduceRules[];
    

    
    // read n symbols into set t
    private void readsymbols(int n, ArrayList<String> t) {
        for(int i = 0; i < n; i++) {
            t.add(input.nextLine().trim());
        }
    }

    // read single line containing integer
    private int readInt() {
       return Integer.parseInt(input.nextLine());
    }


    private void collectRules(int n) {
    	shiftRules = new ArrayList[numStates];
    	reduceRules = new ArrayList[numStates];
    	
    	for(int i=0; i<numStates; i++) {
    		shiftRules[i] = new ArrayList<String>();
    		reduceRules[i] = new ArrayList<String>();
    	}
    	String nextRule;
    	String tokenizedRule[];
    	
    	
    	for(int i = 0; i < n; i++) {
    		nextRule = input.nextLine();
    		tokenizedRule = nextRule.split(" ");
    		if(tokenizedRule[2].equals("reduce")) {
    			reduceRules[Integer.parseInt(tokenizedRule[0])].add(nextRule);
    		} else {
    			shiftRules[Integer.parseInt(tokenizedRule[0])].add(nextRule);
    		}
         }
    }
    
  //pre: 0<=state<numStates
    //post: return -1 if there is no shift rule.
    public int getShift(int state, String token){
    	String splitLine[];
    	
    	 for(int i=0; i<shiftRules[state].size(); i++) {
    		 splitLine=shiftRules[state].get(i).split(" ");
    		 
    		 if((splitLine[1]).equals(token) ) {
    			 return Integer.parseInt(splitLine[3]);
    		 }
    	 }
    	
    	return -1;
    }
    
    //TODO: (EFFICIENTCY) Seeing that split is a very slow method, should I work around it to make thing go faster?
    //pre: 0<=state<numStates
    //post: return a production rule if it exists. Else, return null
    public String getReduction(int state, String token){
    	String splitLine[];
    	
    	 for(int i=0; i<reduceRules[state].size(); i++) {
    		 splitLine=reduceRules[state].get(i).split(" ");
    		 
    		 if((splitLine[1]).equals(token) ) {
    			 return prods.get(Integer.parseInt(splitLine[3]));
    		 }
    	 }
    	
    	return null;
    }
    
    public String findLHS(String tokens) {
    	//Allow for improper input:
    	tokens = tokens.trim();
    	
    	String RHSofProd;
    	for(int i=0; i<this.prods.size(); i++) {
    		RHSofProd = this.prods.get(i).substring(this.prods.get(i).indexOf(' ') + 1);
    		if(tokens.equals(RHSofProd)) {
    			return this.prods.get(i);
    		}
    	}
    	System.out.println("ERROR: In CFGrules.findLHS(): prod rule not found.");
    	System.exit(1);
    	return "ERROR: prod rule not found.";
    }
    
  
    
    
    public int getStartState() {
    	return startState;
    }
    
    //TODO: adjust a8rules.txt so the first production could be at index 0.
    public String getFirstProd() {
    	return prods.get(/*0*/1);
    }
    
    public void getGrammar() {
    	
        readsymbols(readInt(), terms); // read terminals
        readsymbols(readInt(), nonterms); // read nonterminals
        startSymbol = input.nextLine(); // read start symbol
        readsymbols(readInt(), prods); // read production rules
        
        numStates =  Integer.parseInt(input.nextLine());
        
        collectRules(readInt());
        
    }
    
  

  //post:returns the first
    public String getStartSymbol() {
    	return this.startSymbol;
    }
    
    public boolean isNonTerminal(String symbol) {
		for(int i=0; i<nonterms.size(); i++) {
			if(nonterms.get(i).equals(symbol)) {
				return true;
			}
		}
		return false;
	}
    
    
    public int getIndexOfProduction(String nextProduction) {
		nextProduction = nextProduction.trim();
		int ret = -1;
		for(int i=0; i<prods.size(); i++) {
			if(prods.get(i).equals(nextProduction)) {
				return i;
			}
		}
		
		return ret;
	}
	
	public void printGrammar() {
		System.out.println(terms.size());
		for(int i=0; i<this.terms.size(); i++) {
			System.out.println(terms.get(i));
		}
		
		System.out.println(this.nonterms.size());
		for(int i=0; i<this.nonterms.size(); i++) {
			System.out.println(nonterms.get(i));
		}
		
		System.out.println(this.startSymbol);
		
		System.out.println(this.prods.size());
		for(int i=0; i<this.prods.size(); i++) {
			System.out.println(this.prods.get(i));
		}
	}
    
}
