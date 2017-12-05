package probs2015;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.Set;

import utils.Mapping;
import utilsFromProjectEuler.Fraction;

public class prob19b {

	public static void main(String[] args) {
		Scanner in;
		try {
			in = new Scanner(new File("in2015/prob2015in19.txt"));
			
			String medicine = "";
			
			int numRules = 0;
			Mapping vars = new Mapping();
			
			ArrayList <String>rules = new ArrayList<String>();
			
			//Step 1 cound and get all the labels:
			while(in.hasNextLine()) {
				
				String line = in.nextLine();
				
				String token[] = line.split(" ");
				
				if(token.length == 3) {
					rules.add(line);
					
					if(vars.get(token[0]) == -1) {
						vars.set(token[0], 0);
					}
					String element;
					int lastIndex = 0;
					for(int i=1; i<token[2].length(); i++) {
						if(token[2].charAt(i) >='A' && token[2].charAt(i) <= 'Z') {
							element = token[2].substring(lastIndex, i);
							lastIndex = i;
							
							if(vars.get(element) == -1) {
								vars.set(element, 0);
							}
							
						}
					}
					//Handle last var:
					element = token[2].substring(lastIndex, token[2].length());
					lastIndex = token[2].length();
					if(vars.get(element) == -1) {
						vars.set(element, 0);
					}
					
					numRules++;
					
				} else if(token.length == 1 ){
					medicine = in.nextLine();
				}
			}
			
			//Step 2: figure out the values of the labels
			//labels have values because the creator of this puzzle was nice
			
			for(int i=0; i<vars.size(); i++) {
				System.out.println(vars.getLabel(i));
			}
			
			Fraction matrix[][] = new Fraction[numRules][vars.size() + 1];
			
			for(int i=0; i<matrix.length; i++) {
				for(int j=0; j<matrix[0].length; j++) {
					matrix[i][j] = Fraction.ZERO;
				}
			}
			
			for(int i=0; i<matrix.length; i++) {
				matrix[i][vars.size()] = Fraction.ONE;
			}
			
			for(int i=0; i<rules.size(); i++) {
				String token[] = rules.get(i).split(" ");
				int indexVar = vars.getIndexFromLabel(token[0]);
				
				matrix[i][indexVar] = Fraction.minus( matrix[i][indexVar], Fraction.ONE);
				
				String element;
				int lastIndex = 0;
				for(int j=1; j<token[2].length(); j++) {
					if(token[2].charAt(j) >='A' && token[2].charAt(j) <= 'Z') {
						element = token[2].substring(lastIndex, j);
						lastIndex = j;
						
						indexVar = vars.getIndexFromLabel(element);
						
						matrix[i][indexVar] = Fraction.plus( matrix[i][indexVar], Fraction.ONE);
						
						
					}
				}
				//Handle last var:
				element = token[2].substring(lastIndex, token[2].length());
				indexVar = vars.getIndexFromLabel(element);
				matrix[i][indexVar] = Fraction.plus( matrix[i][indexVar], Fraction.ONE);
				
			}
			
			
			for(int i=0; i<matrix.length; i++) {
				for(int j=0; j<matrix[0].length; j++) {
					System.out.print(matrix[i][j] + "\t");
				}
				System.out.println();
			}
			
			Fraction matrixAnswer[] =  utilsFromProjectEuler.Matrix.solveMatrixSlow(matrix);
			
			for(int i=0; i<vars.size(); i++) {
				System.out.println( "( " + vars.getLabel(i) + ") = " +matrixAnswer[i] + "\t");
			}
			
			///Step 3:
			//Count up the answer:
			
			String element;
			double count = 0.0;
			int lastIndex = 0;
			for(int i=1; i<medicine.length(); i++) {
				if(medicine.charAt(i) >='A' && medicine.charAt(i) <= 'Z') {
					element = medicine.substring(lastIndex, i);
					lastIndex = i;
					
					int indexVar = vars.getIndexFromLabel(element);
					
					count += matrixAnswer[indexVar].getDecimalFormat();
					
				}
			}
			//handle last element
			element = medicine.substring(lastIndex, medicine.length());
			int indexVar = vars.getIndexFromLabel(element);
			count += matrixAnswer[indexVar].getDecimalFormat();
			
			System.out.println("Answer to part 2: " + count);;
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	
}

//CRnCaSiRnCaCaCaFYFArFYFArRnFArF

//CRnCaSiRnBSiRnFArTiBPTiTiBFArPBCaSiThSiRnTiBPBPMgArCaSiRnTiMgArCaSiThCaSiRnFArRnSiRnFArTiTiBFArCaCaSiRnSiThCaCaSiRnMgArFYSiRnFYCaFArSiThCaSiThPBPTiMgArCaPRnSiAlArPBCaCaSiRnFYSiThCaRnFArArCaCaSiRnPBSiRnFArMgYCaCaCaCaSiThCaCaSiAlArCaCaSiRnPBSiAlArBCaCaCaCaSiThCaPBSiThPBPBCaSiRnFYFArSiThCaSiRnFArBCaCaSiRnFYFArSiThCaPBSiThCaSiRnPMgArRnFArPTiBCaPRnFArCaCaCaCaSiRnCaCaSiRnFYFArFArBCaSiThFArThSiThSiRnTiRnPMgArFArCaSiThCaPBCaSiRnBFArCaCaPRnCaCaPMgArSiRnFYFArCaSiThRnPBPMgAr
//Plan: maybe go backwards...