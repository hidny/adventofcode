package probs2023;
import java.io.File;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

import number.IsNumber;
import utils.Mapping;
import utils.Sort;

public class prob25TooSlow {

	//day1 part 1
	//2:38.01
	
	public static void main(String[] args) {
		Scanner in;
		try {
			in = new Scanner(new File("in2023/prob2023in25.txt"));
			//in = new Scanner(new File("in2023/prob2023in26.txt"));
			int numTimes = 0;
			 
			int count = 0;
			boolean part2 = false;
			String line = "";

			LinkedList queue = new LinkedList();
			Stack stack = new Stack();
			HashSet set = new HashSet();
			
			
			Hashtable<Long, Integer> trail = new Hashtable<Long, Integer>();
			
			ArrayList <String>lines = new ArrayList<String>();
			
			
			int LIMIT = 20000;
			boolean table342[][] = new boolean[LIMIT][LIMIT];
			
			
			//dir: 0 up
			//1 right
			//2 down
			//3 left
			
			while(in.hasNextLine()) {
				line = in.nextLine();
				lines.add(line);
				
			}

			int most = 0;
			int most2 = 0;
			int most3 = 0;
			long cur = 0L;
			
			
			Hashtable<String, ArrayList<String>> hash = new Hashtable<String, ArrayList<String>>();

			ArrayList<String> componentNameList = new ArrayList<String>();
			
			for(int i=0; i<lines.size(); i++) {
				
				
				line = lines.get(i);
				
				String tokens[] = line.split(":");
				
				String component = tokens[0];
				
				String list[] = tokens[1].trim().split(" ");
				
				ArrayList<String> componentsSoFar = new ArrayList<String>();
				
				componentNameList.add(component);
				
				for(int j=0; j<list.length; j++) {
					componentsSoFar.add(list[j]);
				}
				
				hash.put(component, componentsSoFar);
				
				
			}
			sopl();
			sopl();
			for(int i=0; i<componentNameList.size(); i++) {
				sopl(componentNameList.get(i));
			}
			
			//By dir:
			for(int i=0; i<lines.size(); i++) {
				
				
				line = lines.get(i);
				
				String tokens[] = line.split(":");
				
				String componentExtra = tokens[0];
				
				String list[] = tokens[1].trim().split(" ");
				
				ArrayList<String> componentsSoFar = new ArrayList<String>();
				for(int j=0; j<list.length; j++) {
					
					boolean alreadyThere = false;
					
					sopl("list[j] = " + list[j]);
					
					if(hash.containsKey(list[j]) == false) {
						hash.put(list[j], new ArrayList<String>());

						if(componentNameList.contains(list[j])) {
							sopl("?>>");
							exit(1);
						}
						componentNameList.add(list[j]);
						sopl("Putting " + list[j]);
					}
					
					for(int k=0; k<hash.get(list[j]).size(); k++) {
						
						if(hash.get(list[j]).get(k).equals(componentExtra)) {
							sopl("Already there.");
							alreadyThere = true;
						}
					}
					
					if(alreadyThere == false) {
						hash.get(list[j]).add(componentExtra);
						
						sopl("Adding " + componentExtra + " to " + list[j] + " extra");
					}
					
				}
				
				
				
			}
			
			sopl();
			sopl();

			
			for(int i=0; i<componentNameList.size(); i++) {
				sopl(componentNameList.get(i));
				getIndexString.put(componentNameList.get(i), i);
			}
			
			sopl("Total: " + componentNameList.size());
			
			HashSet<String> disc;
			
			for(int i=0; i<componentNameList.size(); i++) {
				for(int i2=0; i2<hash.get(componentNameList.get(i)).size(); i2++) {
					
					for(int j=i; j<componentNameList.size(); j++) {
						
						sopl("j = " + j);
						for(int j2=0; j2<hash.get(componentNameList.get(j)).size(); j2++) {
							
							if(i == j && j2 <= i2) {
								continue;
							}
							
							for(int k=j; k<componentNameList.size(); k++) {

								sopl("k = " + k);
								for(int k2=0; k2<hash.get(componentNameList.get(k)).size(); k2++) {
									
									if(j == k && k2 <= j2) {
										continue;
									}
									
									
									disc = new HashSet<String>();
									
									disc.add(componentNameList.get(i) + "," + hash.get(componentNameList.get(i)).get(i2));
									disc.add(hash.get(componentNameList.get(i)).get(i2) + "," +  componentNameList.get(i));

									disc.add(componentNameList.get(j) + "," +  hash.get(componentNameList.get(j)).get(j2));
									disc.add(hash.get(componentNameList.get(j)).get(j2) + "," +  componentNameList.get(j));

									disc.add(componentNameList.get(k) + "," +  hash.get(componentNameList.get(k)).get(k2));
									disc.add(hash.get(componentNameList.get(k)).get(k2) + "," +  componentNameList.get(k));
									
									//TODO
									
									if(isDisconnected(componentNameList, disc, hash)) {
										sopl("found answer");
									}
										
									//}
									
								}
							}
						}
					}
					
				}
			}

			sopl("Answer: " + cur);
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	
	public static boolean isDisconnected(ArrayList<String> componentNameList, HashSet<String> disc, Hashtable<String, ArrayList<String>> hash) {
		
		if(componentNameList.size() < 15) {
			sopl(componentNameList.size());
			
			exit(1);
		}
		boolean visited[] = new boolean[componentNameList.size()];
		
		int startIndex = 0;
		visited[0] = true;
		
		
		visited = getMaxConnections( componentNameList, 
				disc, 
				hash, 
				visited,
				startIndex);
		
		boolean ret = false;
		int numVisited = 0;
		int numNotVisited = 0;
		
		//sopl(visited.length);
		for(int i=0; i<visited.length; i++) {
			if(visited[i] == false) {
				ret = true;
				numNotVisited++;
			} else {
				numVisited++;
			}
		}
		
		if(ret == true) {
			sopl(numNotVisited);
			sopl(numVisited);
			
			sopl("Answer: " + (numNotVisited * numVisited));
			
			if((numNotVisited * numVisited) != 14) {
				exit(1);
			}
		}
		
		return ret;
	}
	
	public static boolean[] getMaxConnections(ArrayList<String> componentNameList, HashSet<String> disc, Hashtable<String, ArrayList<String>> hash, boolean visited[],
			int curIndex) {
		
		ArrayList<String> componentList = hash.get(componentNameList.get(curIndex));
		
		for(int i=0; i<componentList.size(); i++) {
			
			if(disc.contains(componentNameList.get(curIndex) + "," + componentList.get(i))) {
				continue;
			}
			
			int newIndex = getIndex(componentList.get(i));
			if(visited[newIndex] == false) {
				visited[newIndex] = true;
				
				getMaxConnections(componentNameList,disc, hash, visited,
						newIndex);
			}
		}
		
		
		return visited;
	}
	
	public static Hashtable<String, Integer> getIndexString = new Hashtable<String, Integer>();
	
	public static int getIndex(String component) {
		
		return getIndexString.get(component);
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
			sop("Error: (" + s + ") is not a number");
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
