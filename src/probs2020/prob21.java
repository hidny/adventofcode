package probs2020;
import java.io.File;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

import number.IsNumber;
import utils.Mapping;
import utils.Sort;

public class prob21 {

	
	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2020/prob2020in21.txt"));
			 //in = new Scanner(new File("in2020/prob2020in21.txt.test"));
			int numTimes = 0;
			 
			int count = 0;
			boolean part2 = false;

			LinkedList queue = new LinkedList();
			Stack stack = new Stack();
			HashSet set = new HashSet();
			
			
			
			ArrayList <String>lines = new ArrayList<String>();
			
			
			//int LIMIT = 20000;
			//boolean table[][] = new boolean[LIMIT][LIMIT];
			
			
			//dir: 0 up
			//1 right
			//2 down
			//3 left
			
			while(in.hasNextLine()) {
				String line = in.nextLine();
				lines.add(line);
				
				
			}
			
			
			ArrayList <String>foods = new ArrayList<String>();
			ArrayList <String>allegens = new ArrayList<String>();
			
			Hashtable<String, Integer> link = new Hashtable<String, Integer>();
			
			
			ArrayList ints = new ArrayList<Integer>();
			
			for(int i=0; i<lines.size(); i++) {
				
				//int temp = Integer.parseInt(lines.get(i));
				//count+=temp;
				
				String line = lines.get(i);
				String lineCopy = String.copyValueOf(line.toCharArray());

				
				String foodLine = line.substring(0, line.indexOf('(')).trim();;
				
				String foodToken[] = foodLine.split(" ");
				
				String allergensLine = line.substring(line.indexOf('(') + 1, line.indexOf(')')).trim();
				
				sopl(foodLine);
				sopl(allergensLine);
				
				String allergensToken[] = allergensLine.substring("contains ".length()).split(",");
				
				/*
				 * ArrayList <String>foods = new ArrayList<String>();
			ArrayList <String>allegens = new ArrayList<String>();
			
			Hashtable<Integer, Integer> link = new Hashtable<Integer, Integer>();
			
			
				 */
					
				for(int j=0; j<foodToken.length; j++) {
					
					foodToken[j] = foodToken[j].trim();
					String curFood = foodToken[j].trim();
					
					if(foods.contains(curFood) == false) {
						foods.add(curFood);
					}
				}
				
				for(int j=0; j<allergensToken.length; j++) {
					
					allergensToken[j] = allergensToken[j].trim();
					String curAllergen = allergensToken[j];
					if(allegens.contains(curAllergen) == false) {
						allegens.add(curAllergen);
					}
				}
				
				for(int j=0; j<foodToken.length; j++) {
					for(int k=0; k<allergensToken.length; k++) {
						
						int foodIndex = foods.indexOf(foodToken[j]);
						int index2 = allegens.indexOf(allergensToken[k]);
						
						String key = i + "," + foodIndex +"," +index2;
						if(link.containsKey(key) == false) {
							link.put(key, 1);
						}
						
						sopl(foodToken[j] + "->" + allergensToken[k]);
						
						if(foodIndex < 0 || index2 < 0) {
							sopl("ahh!");
							exit(1);
						}
					}
				}
				
				
			}
			
			ArrayList<String> allergenKnown = new ArrayList<String>();
			ArrayList<String> foodWithAllergen = new ArrayList<String>();
			
			sopl();
			
			boolean foundFoodForAllergen[] = new boolean[foods.size()];
			//boolean foodNoAllergen[] = new boolean[foods.size()];
			
			boolean foodAccountedFor[] = new boolean[allegens.size()];
			//Plan:
			//Go through all foods and see 
			
			boolean progress = true;
			

			boolean poss[][] = new boolean[allegens.size()][foods.size()];
			
			for(int i=0; i<poss.length; i++) {
				for(int j=0; j<poss[0].length; j++) {
					poss[i][j] = true;
				}
			}
			
			while(progress) {
				
				progress = false;
				
				for(int i=0; i<allegens.size(); i++) {
					
					
					if(foundFoodForAllergen[i]) {
						continue;
					}

					
					Set<String> keys = link.keySet();
			        for(String key: keys){

						//sopl("key: " + key);
			        	String tokens[] = key.split(",");
			        	
			        	if(pint(tokens[2]) == i) {
			        		
			        		//sopl("list");
			        		listPosFoodInLine(poss[i], pint(tokens[0]), keys);
			        	}
			        	//TODO
			        	
			            //System.out.println(key + "-> " + value + "  or " + StringWithXToNum(value));
			           
			        }
			        
			        
			        int numPoss = 0;
			        
			        int indexFound = -1;
			        for(int j=0; j<poss[0].length; j++) {
			        	if(poss[i][j]) {
			        		numPoss++;
			        		indexFound = j;
			        	}
			        }
			        
			        
			        if(numPoss == 0) {
			        	
			        	sopl("ERROR: no possible foods for: " + allegens.get(i));
			        	exit(1);
			        	
			        } else if(numPoss == 1) {

			        	//exit(1);

			        	sopl("Accounted for: " + allegens.get(i) + " i == " + i);
			        	sopl("It is : " + foods.get(indexFound) );
			        	progress = true;
			
			        	foundFoodForAllergen[i] = true;
			        	
			        	//For part 2:
			        	allergenKnown.add(allegens.get(i));
			        	foodWithAllergen.add(foods.get(indexFound));
			        	
			        	
			        	for(int i2=0; i2<poss.length; i2++) {
			        		if(i2 != i) {
			        			poss[i2][indexFound] = false;
			        		}
			        	}
			        	
			        }
			        
			        
			        
				}
				//TODO:

		        sopl();
		        sopl();
				
			}
			
			
			boolean foodNoAllergens[] = new boolean[foods.size()];
			
			for(int j=0; j<poss[0].length; j++) {
				
				boolean noPosAllergen = true;
				for(int i=0; i<poss.length; i++) {
					
						if(poss[i][j]) {
							noPosAllergen = false;
							break;
						}
					
				}
				
				if(noPosAllergen) {
					foodNoAllergens[j] = true;
				} else {
					foodNoAllergens[j] = false;
				}
			}
			
			sopl("Count the times it's added:");
			//foodNoAllergen
			//TODO: count them up...
			
			for(int i=0; i<lines.size(); i++) {
				
				
				String line = lines.get(i);

				
				String foodLine = line.substring(0, line.indexOf('(')).trim();;
				
				String foodToken[] = foodLine.split(" ");
				
				//sopl(foodLine);
				
					
				for(int j=0; j<foodToken.length; j++) {
					
					foodToken[j] = foodToken[j].trim();
					String curFood = foodToken[j].trim();
					
					if(foodNoAllergens[foods.indexOf(curFood)]) {
						sopl("Found: " + curFood);
						count++;
					}
				}
				
				
			}
			
			
			sopl("Answer part 1: " + count);
			

        	//For part 2:
        	//allergenKnown.add(allegens.get(i));
        	//foodWithAllergen.add(foods.get(indexFound));
        	
			for(int i=0; i<allergenKnown.size(); i++) {
				
				int bestIndex = i;
				
				for(int j=i+1; j<allergenKnown.size(); j++) {
					
					if(allergenKnown.get(bestIndex).compareTo(allergenKnown.get(j)) > 0 ) {
						bestIndex = j;
					}
				}
				
				String tmp = allergenKnown.get(i);
				allergenKnown.set(i, allergenKnown.get(bestIndex));
				allergenKnown.set(bestIndex, tmp);
				

				tmp = foodWithAllergen.get(i);
				foodWithAllergen.set(i, foodWithAllergen.get(bestIndex));
				foodWithAllergen.set(bestIndex, tmp);
			}
			
			
			sopl("Answer for part 2:");
			for(int i=0; i<foodWithAllergen.size(); i++) {
				sop(foodWithAllergen.get(i));
				
				if(i == foodWithAllergen.size() - 1) {
					sopl();
				} else {
					sop(",");
				}
			}
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}

	
	public static boolean[] listPosFoodInLine(boolean pos[], int lineIndex, Set<String> keys) {
		
		ArrayList <Integer>foods = new ArrayList<Integer>();
		
		for(int i=0; i<pos.length; i++) {
			//sopl("start pos[" + i + "] = " + pos[i]);
		}
			
		for(String key: keys){
        	
        	String tokens[] = key.split(",");
        	
        	if(pint(tokens[0]) == lineIndex) {
        		//sopl("line index " + lineIndex + ": " + pint(tokens[2]));
        		foods.add(pint(tokens[1]));
        	}
        	
            //System.out.println(key + "-> " + value + "  or " + StringWithXToNum(value));
           
        }
		
		
		for(int i=0; i<pos.length; i++) {
			if(foods.contains(i) == false) {
				pos[i] = false;
			}
		}

		for(int i=0; i<pos.length; i++) {
			//sopl("start end[" + i + "] = " + pos[i]);
		}
		return pos;
	}

	

	public static void sop(Object a) {
		System.out.print(a.toString());
	}
	public static void sopl(Object a) {
		System.out.println(a.toString());
	}

	public static void sopl() {
		System.out.println();
	}
	
	public static int pint(String s) {
		if (IsNumber.isNumber(s)) {
			return Integer.parseInt(s);
		} else {
			sop("Error: (" + s + " is not a number");
			return -1;
		}
	}

	public static long plong(String s) {
		if (IsNumber.isLong(s)) {
			return Long.parseLong(s);
		} else {
			sop("Error: (" + s + " is not a number");
			return -1;
		}
	}
	
	public static void exit() {
		exit(0);
	}
	public static void exit(int code) {
		sop("Exit with code " + code);
		
		System.exit(code);
	}

}
