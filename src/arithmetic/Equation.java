package arithmetic;

import java.util.ArrayList;

public class Equation {

	private ArrayList<Term> equation = new ArrayList<Term>();
	
	public Equation() {
		
	}

	public Equation(Term term) {
		equation.add(term.makeHardCopy());
	}

	public static  Equation add(Equation a, Equation b) {
		Equation newEq = new Equation();
		for(int i=0; i<a.equation.size(); i++) {
			newEq.equation.add(a.equation.get(i).makeHardCopy());
		}
		
		for(int i=0; i<b.equation.size(); i++) {
			newEq.equation.add(b.equation.get(i).makeHardCopy());
		}
		
		newEq.cancelOutTerms();
		
		return newEq;
	}
	public static Equation subtract(Equation a, Equation b) {
		Equation newEq = new Equation();
		for(int i=0; i<a.equation.size(); i++) {
			newEq.equation.add(a.equation.get(i).makeHardCopy());
		}
		
		
		for(int i=0; i<b.equation.size(); i++) {
			newEq.equation.add(b.equation.get(i).flipSign());
		}
		
		newEq.cancelOutTerms();
		
		return newEq;
	}
	
	public static Equation multiply(Equation a, Equation b) {
		
		Equation newEq = new Equation();
		
		for(int i=0; i<a.equation.size(); i++) {
			for(int j=0; j<b.equation.size(); j++) {
				newEq.equation.add(a.equation.get(i).multiply(b.equation.get(j)));
			}
		}
		newEq.cancelOutTerms();
		
		return newEq;
	}
	
	public static  Equation divide(Equation a, Equation b) {
		Equation newEq = new Equation();
		//TODO: for now, just assume we dividing with only 1 term.
		for(int i=0; i<a.equation.size(); i++) {
			if(b.equation.size() > 2) {
				System.out.println("ERROR: trying to divide by 2 terms... This is too complex for me right now. :(");
				System.exit(1);
			}
			for(int j=0; j<b.equation.size(); j++) {
				newEq.equation.add(a.equation.get(i).divide(b.equation.get(j)));
			}
		}
		
		newEq.cancelOutTerms();
		
		return newEq;
	}
	
	public void cancelOutTerms() {
		for(int i=0; i<this.equation.size(); i++) {
			if(this.equation.get(i).getCoef() == 0) {
				this.equation.remove(i);
			}
		}
		
		for(int i=0; i<this.equation.size(); i++) {
			this.equation.get(i).sortFactors();
		}
		
		nextTerm:
		for(int i=0; i<this.equation.size(); i++) {
			for(int j=i+1; j<this.equation.size(); j++) {
				if(this.equation.get(i).hasSameFactors(this.equation.get(j))) {
					this.equation.get(i).addCoef(this.equation.get(j).getCoef());
					
					this.equation.remove(j);
					j--;
					if(this.equation.get(i).getCoef() == 0) {
						this.equation.remove(i);
						i--;
						continue nextTerm;
					}
				}
			}
		}
		
	}
	
	public String toString() {
		if(this.equation.size() == 0) {
			return "0";
		}
		String ret = "";
		for(int i=0; i<this.equation.size(); i++) {
			ret += this.equation.get(i);
			if(i+1<this.equation.size()) {
				ret += " + ";
			}
		}
		return ret;
	}
	
	public ArithNode toArithNode() {
		ArithNode ret;
		if(this.equation.size() > 0) {
			ret = this.equation.get(0).toArithNode();
	
			for(int i=1; i<this.equation.size(); i++) {
				ret = new ArithNode('+', ret, this.equation.get(i).toArithNode());
				
			}
		} else {
			ret = new ArithNode(0);
		}
		return ret;
	}
}