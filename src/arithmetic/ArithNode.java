package arithmetic;

import java.util.ArrayList;
import java.util.Hashtable;

import probs.prob23SegmentDiff;

public class ArithNode {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String xName = "x";
		String varNames[] = new String[6];
		varNames[0] = "a";
		varNames[1] = "b";
		varNames[2] = "c";
		varNames[3] = "d";
		varNames[4] = "e";
		varNames[5] = "x";
		
		long values[] = new long[7];
		for(int i=0; i<7; i++) {
			values[i] = 0;
		}
		
		//Test 1:
		
		ArithNode x = new ArithNode(xName);
		ArithNode five = new ArithNode(5);
		
		ArithNode seven = new ArithNode(7);
		
		ArithNode equation = new ArithNode('+', seven, new ArithNode('*', x, five));
		
		System.out.println(equation);
		
		values[5] = 2;
		System.out.println("test 1:");
		System.out.println("got: " + equation.eval(varNames, values));
		System.out.println("expected: 7");
		System.out.println("");
		
		values[5] = -2;
		System.out.println("test 1:");
		System.out.println("got: " + equation.eval(varNames, values));
		System.out.println("expected: -3");
		System.out.println("");
		
		//arithNode equation = new arithNode('+', seven, new arithNode('*', x, five));
	}

	//If Leaf:
	String value = null;
	
	//If non-leaf
	private ArithNode leftNode = null;
	private ArithNode rightNode = null;

	char operation;
	
	public ArithNode(long number) {
		this.value = number + "";
	}
	
	public ArithNode(String var) {
		this.value = var;
	}
	
	//Scheme style... :)
	public ArithNode(char operation, ArithNode leftNode, ArithNode rightNode) {
		this.operation = operation;
		this.leftNode = leftNode.makeHardCopy();
		this.rightNode = rightNode.makeHardCopy();
		
	}

	public long eval() {
		return eval(new String[0], new long[0]);
	}
	public long eval(String varNames[], long vars[]) {
		if(this.isLeaf()) {
			for(int i=0; i<varNames.length; i++) {
				if(value.equals(varNames[i])) {
					return vars[i];
				}
			}
			return Long.parseLong(value);
		} else {
			if(operation == '+') {
				return leftNode.eval(varNames, vars) + rightNode.eval(varNames, vars);
			} else if(operation == '-') {
				return leftNode.eval(varNames, vars) - rightNode.eval(varNames, vars);
			} else if(operation == '*') {
				return leftNode.eval(varNames, vars) * rightNode.eval(varNames, vars);
			} else if(operation == '=') {
				return leftNode.eval(varNames, vars) / rightNode.eval(varNames, vars);
			} else {
				System.out.println("ERROR: unknown operation!");
				System.exit(1);
				return 0;
			}
		}
	}
	
	private boolean isLeaf() {
		if(leftNode == null && rightNode == null) {
			return true;
		} else if(leftNode == null || rightNode == null) {
			System.out.println("ERROR: leftNode and rightNode aren't both null");
			System.exit(1);
		}
		return false;
	}
	
	public String toString() {
		if(this.isLeaf()) {
			return value;
		} else {
			return leftNode.toStringInRecursion() + " " + this.operation + " " + rightNode.toStringInRecursion();
		}
	}
	
	public String toStringInRecursion() {
		if(this.isLeaf()) {
			return value;
		} else {
			return "(" + leftNode + " " + this.operation + " " + rightNode + ")";
		}
	}
	
	
	
	public boolean isZero() {
		if(this.hasVariable() == false && this.eval() == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isOne() {
		if(this.hasVariable() == false && this.eval() == 1) {
			return true;
		} else {
			return false;
		}
	}
	
	
	public boolean hasVariable() {
		
		if(this.isLeaf())  {
			for(int i=0; i<value.length(); i++) {
				if( (value.charAt(i) >= 'A' && value.charAt(i) <= 'Z') || (value.charAt(i) >= 'a' && value.charAt(i) <= 'z')) {
					return true;
				}
			}
			return false;
		} else {
			return leftNode.hasVariable() || rightNode.hasVariable();
		}
	}
	
	public ArithNode substitute(Hashtable<String, ArithNode> variablesToSub) {
		
		if(this.isLeaf()) {
			if(variablesToSub.get(this.value) != null) {
				return variablesToSub.get(this.value).makeHardCopy();
			} else {
				return this.makeHardCopy();
			}
		} else {
			return new ArithNode(this.operation, this.leftNode.substitute(variablesToSub), this.rightNode.substitute(variablesToSub));
		}
		
	}
	
	public Equation getEquation() {
		if(this.isLeaf()) {
			if( this.hasVariable()) {
				ArrayList<String> factors = new ArrayList<String>();
				ArrayList<Integer> expo = new ArrayList<Integer>();
				factors.add(this.value);
				expo.add(1);
				return new Equation(new Term(1, 1, factors, expo));
			} else {
				return new Equation(new Term(Integer.parseInt(this.value)));
			}
		} else {
			if(this.operation == '+') {
				return Equation.add(this.leftNode.getEquation(), this.rightNode.getEquation());
			} else if(this.operation == '-') {
				return Equation.subtract(this.leftNode.getEquation(), this.rightNode.getEquation());
			} else if(this.operation == '*') {
				return Equation.multiply(this.leftNode.getEquation(), this.rightNode.getEquation());
			} else if(this.operation == '/') {
				//System.out.println(leftNode.getEquation());
				//System.out.println(rightNode.getEquation());
				return Equation.divide(this.leftNode.getEquation(), this.rightNode.getEquation());
			} else {
				System.out.println("ERROR: unknow operation in getEquation();");
				return null;
			}
		}
	}
	
	public ArithNode makeHardCopy() {
		if(this.isLeaf()) {
			return new ArithNode(this.value);
		} else {
			return new ArithNode(this.operation, this.leftNode.makeHardCopy(), this.rightNode.makeHardCopy());
		}
	}
	
	public ArithNode simplifiedCopy() {
		ArithNode temp =  this.getEquation().toArithNode();
		return getRidOfTimes1(temp);
	}
	
	public static ArithNode getRidOfTimes1(ArithNode input) {
		if(input.isLeaf()) {
			return input.makeHardCopy();
		} else {
			if(input.leftNode.isOne() && (input.operation == '*' || input.operation == '/')) {
				return getRidOfTimes1(input.rightNode);
			} else if(input.rightNode.isOne() && (input.operation == '*' || input.operation == '/')) {
				return getRidOfTimes1(input.leftNode);
			} else {
				return new ArithNode(input.operation, getRidOfTimes1(input.leftNode), getRidOfTimes1(input.rightNode));
			}
		}
	}
	
}
