package arithmetic;

import java.util.ArrayList;

public class Term {

	private ArrayList<String> factors = new ArrayList<String>();
	private ArrayList<Integer> expo = new ArrayList<Integer>();
	private long coef;
	
	//TODO: later:
	//private long divisor;
	
	public Term() {
		this.coef = 1;
		//this.divisor = 1;
	}
	
	public Term(long coef) {
		this.coef = coef;
		//this.divisor = 1;
	}
	
	public Term(long coef, long divisor, ArrayList<String> factors, ArrayList<Integer> expo) {
		this.coef = coef;
		//this.divisor = divisor;
		for(int i=0; i<factors.size(); i++) {
			this.factors.add(factors.get(i) + "");
		}
		for(int i=0; i<expo.size(); i++) {
			this.expo.add(expo.get(i));
		}
	}
	
	
	public Term makeHardCopy() {
		Term newTerm = new Term();
		newTerm.coef = this.coef;
		//newTerm.divisor = this.divisor;
		
		for(int i=0; i<factors.size(); i++) {
			newTerm.factors.add(factors.get(i) + "");
		}

		for(int i=0; i<expo.size(); i++) {
			newTerm.expo.add(expo.get(i));
		}
		
		return newTerm;
	}

	public String toString() {
		String ret = "";
		if(this.coef != 1 || factors.size() == 0) {
			if(this.coef < 0) {
				ret += "(-" + Math.abs(this.coef) + ") ";
			} else {
				ret += this.coef + " ";
			}
			
			if(factors.size() > 0) {
				ret += "* ";
			}
		}
		for(int i=0; i<factors.size(); i++) {
			ret += factors.get(i);
			if(expo.get(i) != 1) {
				ret += "^" + expo.get(i);
			}
			if(i+1<factors.size()) {
				ret += " * ";
			}
		}
		return ret;
	}
	
	public boolean isEqual(Term other) {
		//if((1.0 * this.coef/ 1.0 *this.divisor) != (1.0 * other.coef/ 1.0 *other.divisor)) {
		if((1.0 * this.coef) != (1.0 * other.coef)) {
			return false;
		}
		
		if(factors.size() != other.factors.size()) {
			return false;
		}
		
		return hasSameFactors(other);
		
	}
	
	public boolean hasSameFactors(Term otherTerm) {
		boolean foundMatch;
		
		for(int i=0; i<factors.size(); i++) {
			foundMatch = false;
			
			nextFactor:
			for(int j=0; j<otherTerm.factors.size(); j++) {
				if(factors.get(i).equals(otherTerm.factors.get(j))) {
					foundMatch = true;
					if(expo.get(i).equals(otherTerm.expo.get(j)) == false) {
						return false;
					}
					continue nextFactor;
				}
				
				
			}
			if(foundMatch == false) {
				return false;
			}
		}
		
		for(int i=0; i<otherTerm.factors.size(); i++) {
			foundMatch = false;
			
			nextFactor2:
			for(int j=0; j<factors.size(); j++) {
				if(otherTerm.factors.get(i).equals(factors.get(j))) {
					foundMatch = true;
					if(otherTerm.factors.get(i).equals(factors.get(j)) == false) {
						return false;
					}
					continue nextFactor2;
				}
				
				
			}
			if(foundMatch == false) {
				return false;
			}
		}
		
		return true;
	}
	
	public Term flipSign() {
		Term ret = this.makeHardCopy();
		ret.coef = -1 * ret.coef;
		return ret;
	}
	
	public void addCoef(long coef/*, long divisor*/) {
		this.coef += coef;
	}
	
	public long getCoef() {
		return this.coef;
	}
	
	public Term multiply(Term other) {
		Term newTerm = new Term();
		
		newTerm.coef = this.coef * other.coef;
		
		for(int i=0; i<this.factors.size(); i++) {
			newTerm.factors.add(this.factors.get(i));
			newTerm.expo.add(this.expo.get(i));
		}
		
		for(int i=0; i<other.factors.size(); i++) {
			newTerm.factors.add(other.factors.get(i));
			newTerm.expo.add(other.expo.get(i));
		}
		
		nextFactor:
		for(int i=0; i<newTerm.factors.size(); i++) {
			for(int j=i+1; j<newTerm.factors.size(); j++) {
				if(newTerm.factors.get(i).equals(newTerm.factors.get(j))) {
					newTerm.expo.set(i, newTerm.expo.get(i) + newTerm.expo.get(j));

					newTerm.factors.remove(j);
					newTerm.expo.remove(j);
					j--;
					if(newTerm.expo.get(i).equals(0)) {
						
						newTerm.factors.remove(i);
						newTerm.expo.remove(i);
						i--;
						continue nextFactor;
					}
				}
			}
			
			if(newTerm.expo.get(i).equals(0)) {
				
				newTerm.factors.remove(i);
				newTerm.expo.remove(i);
				i--;
				continue nextFactor;
			}
		}
		
		return newTerm;
	}
	
	public Term divide(Term other) {
		Term invTerm = other.makeHardCopy();
		
		if(other.coef != 1 && other.coef != -1) {
			System.out.println("ERROR: not prepared to divide by a coef other than 1");
			System.exit(1);
		}
		
		for(int i=0; i<invTerm.expo.size(); i++) {
			invTerm.expo.set(i, 0 - invTerm.expo.get(i));
		}
		
		return this.multiply(invTerm);
	}
	
	public void sortFactors() {
		String temp;
		int tempExp;
		for(int i=0; i<factors.size(); i++) {
			for(int j=i+1; j<factors.size(); j++) {
				if(factors.get(i).compareTo(factors.get(j)) > 0) {
					temp = factors.get(i);
					tempExp = expo.get(i);
					
					factors.set(i,  factors.get(j));
					expo.set(i,  expo.get(j));
					

					factors.set(j,  temp);
					expo.set(j,  tempExp);
				}
			}
		}
	}
	//this.equation.get(i).getCoef())
	
public ArithNode toArithNode() {
		
		ArithNode ret = new ArithNode(this.coef);
			
		for(int i=0; i<this.factors.size(); i++) {
			if(this.expo.get(i) > 0) {
				for(int j=0; j<this.expo.get(i); j++) {
					ret = new ArithNode('*', ret, new ArithNode(this.factors.get(i)));
				}
			} else {
				for(int j=0; j< 0 -this.expo.get(i); j++) {
					ret = new ArithNode('/', ret, new ArithNode(this.factors.get(i)));
				}
			}
		
		}
		
		return ret;
	}
}
