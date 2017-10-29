package probs2015;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.Set;

import utils.Mapping;

public class prob19 {

	public static void main(String[] args) {
		Scanner in;
		try {
			in = new Scanner(new File("in2015/prob2015in19.txt"));
			
			ArrayList<String> listStartRules = new ArrayList<String>();
			ArrayList<String> listEndRules = new ArrayList<String>();
			
			Hashtable<String, String> currentStep = new Hashtable<String,String>();
			
			String medicine = "";
			
			while(in.hasNextLine()) {
				
				String line = in.nextLine();
				
				String token[] = line.split(" ");
				
				if(token.length == 3) {
					listStartRules.add(token[0]);
					listEndRules.add(token[2]);
					
				} else if(token.length == 1 ){
					medicine = in.nextLine();
				}
			}
			
		
			String start = medicine;
			
			for(int j=0; j<listStartRules.size(); j++) {
				
				String searchWord = listStartRules.get(j);
				
				for(int k=0; k<start.length(); k++) {
					
					String begin = start.substring(0, k);
					String end = start.substring(k);
					String endReplace;
					
					if(end.startsWith(searchWord)) {
						endReplace = end.replaceFirst(searchWord, listEndRules.get(j));
						String newMolecule = begin + endReplace;
						
						if(currentStep.containsKey(newMolecule) == false) {
							currentStep.put(newMolecule, newMolecule);
							System.out.println(newMolecule);
						}
					}
				}
			}
			
			System.out.println("Answer: " + currentStep.size());
			
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	
	public static boolean listContains(ArrayList<String> list, String arg) {
		for(int i=0; i<list.size(); i++) {
			if(list.get(i).equals(arg)) {
				return true;
			}
		}
		return false;
	}

}
