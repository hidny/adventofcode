package probs2017;

import java.io.File;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

import utils.Mapping;

public class prob14b {

	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2017/prob2017in14.txt"));
			
			int count = 0;
			boolean part2 = true;
			String lineIn = "";
			
			LinkedList queue = new LinkedList();
			
			
			int array[] = new int[256];
			
			int map[][] = new int[128][128];
			
			while(in.hasNextLine() ) {
				lineIn = in.nextLine();
				
				for(int num=0; num<128; num++) {
					array = new int[256];
					
					String lineIt = lineIn + "-" + num;
					
					//TODO: PUT INTO FUNCTION
					for(int i=0; i<array.length; i++) {
						array[i] = i;
					}
					
					
					int curPos = 0;
					int skipSize = 0;
					
					for(int b=0; b<64; b++) {
						for(int i=0; i<lineIt.length(); i++) {
							int length = (int)(lineIt.charAt(i));
							
							array = reverse(array, curPos, length);
							curPos += length  + skipSize;
							skipSize++;
							
						}
						
						int temp[] = new int[] {17, 31, 73, 47, 23};
						
						for(int i=0; i<temp.length; i++) {
							int length = temp[i];
							
							array = reverse(array, curPos, length);
							curPos += length  + skipSize;
							skipSize++;
							
						}
					}
					
					
					int arrayDense[] = new int[16];
					for(int i=0; i<16; i++) {
						
						arrayDense[i] = array[16*i];
						for(int j=1; j<16; j++) {
	
							
							arrayDense[i] ^= array[16*i + j];
							
							
						}
					}
					//TODO: END PUT INTO FUNCTION
					
					System.out.println("TEST:");
					printBin(arrayDense[0]);
					
					for(int i=0; i<16; i++) {
						count+=countOnes(arrayDense[i]);
					}
					
					map[num] = getLineOfMap(arrayDense);
					
					
					//System.out.println(ret);
					//2b0c9cc0449507a0 db 3b ab d5 7a d9 e8 d8
				}
			}
			System.out.println("----");
			for(int y=0; y<8; y++) {
				for(int x=0; x<8; x++) {
					System.out.print(map[y][x]);
				}
				System.out.println();
			}
			
			
			int numRegions = 0;
			int currentGroupNumber = 2;
			
			
			for(int i=0; i<map.length; i++) {
				for(int j=0; j<map[0].length; j++) {
					if(map[i][j] ==1) {
						map[i][j] = currentGroupNumber;
						numRegions++;
						queue.add(i*map[0].length + j);
						
						while(queue.isEmpty() == false) {
							int currentLocation = (Integer)queue.pop();
							int y = currentLocation /map[0].length;
							int x = currentLocation % map[0].length;
							
							for(int starty=y-1; starty<=y+1; starty++) {
								for(int startx=x-1; startx<=x+1; startx++) {
									if(starty == y && startx == x) {
										continue;
									}
									
									//Don't include diag
									if(starty != y && startx != x) {
										continue;
									}
									
									if(hasElement(map, starty, startx)) {
										queue.add(starty*map[0].length + startx);
										map[starty][startx] = currentGroupNumber;
									}
								}
							}
							
							
						}
						
						currentGroupNumber++;
					}
				}
			}
			System.out.println(" ---------");
			for(int y=0; y<8; y++) {
				for(int x=0; x<8; x++) {
					if(map[y][x] == 0){
						System.out.print(map[y][x]);
						
					} else {
						System.out.print(map[y][x] - 1);
					}
				}
				System.out.println();
			}
			
			System.out.println("Answer: " + count);
			
			System.out.println("Answer2: " + numRegions);
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	
	public static boolean hasElement(int map[][], int i, int j) {
		if(i< 0 || j<0) {
			return false;
		}
		if(i>=map.length || j>=map[0].length) {
			return false;
		}
		
		if(map[i][j] == 1) {
			return true;
		} else {
			return false;
		}
	}
	
	//TODO: PUT INTO OTHER UTILS CLASS
	public static int[] reverse(int a[], int curPos, int length) {
		int temp[] = new int[length];
		for(int i=0; i<temp.length; i++) {
			temp[i] = a[(i + curPos) % a.length];
		}
		
		for(int i=0; i<temp.length; i++) {
			a[(i + curPos) % a.length] = temp[temp.length -1 -i];
		}
		
		return a;
		
	}
	
	//TODO: PUT INTO OTHER UTILS CLASS
	private static void printBin(int num) {
		
		
		String ret = "";
		for(int i=128; i>0; i/=2) {
			if(num >= i) {
				ret += "1";
				num -= i;
			} else {
				ret +="0";
			}
		}
		
		System.out.println(ret);
	}
	
	//TODO: PUT INTO OTHER UTILS CLASS
	private static int countOnes(int num) {
		int ret = 0;
		
		
		for(int i=128; i>0; i/=2) {
			if(num >= i) {
				ret++;
				num -= i;
			}
		}
		
		return ret;
	}
	
	public static int[] getLineOfMap(int arrayDense[]) {
		int ret[] = new int[128];
		for(int i=0; i<arrayDense.length; i++) {
			int temp[] = getBin(arrayDense[i]);
			for(int j=0; j<temp.length; j++) {
				ret[8*i + j] = temp[j];
			}
		}
		
		return ret;
	}
	
	private static int[] getBin(int num) {
		
		String ret = "";
		for(int i=128; i>0; i/=2) {
			if(num >= i) {
				ret += "1";
				num -= i;
			} else {
				ret +="0";
			}
		}
		
		int ret2[] = new int[ret.length()];
		for(int i=0; i<ret.length(); i++) {
			if(ret.charAt(i) == '1') {
				ret2[i] = 1;
			} else {
				ret2[i] = 0;
			}
		}
		
		return ret2;
	}
}
