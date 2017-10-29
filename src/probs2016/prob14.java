package probs2016;

import java.io.File;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Scanner;

import java.security.*;

public class prob14 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		Scanner in;
		Scanner in2;
		try {

			 in = new Scanner(new File("prob14in.txt"));
			boolean isPart1 = true;
				
			 in2 = new Scanner(System.in);
			 String line;
			
			 
			String salt = "";
			System.out.println("Start");
			 while(in.hasNextLine()) {
				 line = in.nextLine();
				 salt = line;
			 }
			 

			 System.out.println("Salt: " + salt);
			int keysFound = 0;

			
			int answer = 0;
			
			
			ArrayList<String> md5Array = new ArrayList<String>();
			
			String md5Hex = "";
			
			NEXTHASH:
			for(int i=0; true; i++) {
				String ID = salt + i;

				//System.out.println("Index: " + i);
				
				try {
					if(md5Array.size() <= i) {
						md5Array.add(generateMD5Hash(ID, isPart1));
					}
					md5Hex = md5Array.get(i);
					
					int numRepeats = 0;
					char lastChar = 'p';
					for(int j=0; j<md5Hex.length(); j++) {
						if(j==0) {
							lastChar = md5Hex.charAt(j);
							numRepeats = 1;
						} else if(lastChar == md5Hex.charAt(j)) {
							numRepeats++;
							if(numRepeats == 3) {
								//search for repeat of 5:
								answer = i;
								
								for(int k=1; k<=1000; k++) {

									ID = salt + (i + k);
									if(md5Array.size() <= i + k) {
										md5Array.add(generateMD5Hash(ID, isPart1));
									}
									md5Hex = md5Array.get(i + k);
									
									String search = "";
									for(int m=0; m<5; m++) {
										search += lastChar;
									}
									if(md5Hex.contains(search)) {
										keysFound++;
										if(keysFound == 64) {
											break NEXTHASH;
										}
									}
									
									
								}
								//end search repeat of 5
								
								//only consider first triple:
								break;
							}
						} else {
							lastChar = md5Hex.charAt(j);
							numRepeats = 1;
						}
					}
					
				} catch(Exception e) {
					e.printStackTrace();
				}
			}

			System.out.println("Answer: " + answer);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static String generateMD5Hash(String ID, boolean isPart1) {
		if(isPart1 == true) {
			return generateMD5Hashpart1(ID);
		} else {
			return generateMD5HashPart2(ID);
		}
	}
	
	public static String generateMD5Hashpart1(String ID) {
		try {
			byte[] bytesOfMessage = ID.getBytes("UTF-8");
			
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] thedigest = md.digest(bytesOfMessage);
			String md5Hex = getHexFromMD5(thedigest);

			return md5Hex;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return "0";
	}
	
	public static String generateMD5HashPart2(String ID) {
		try {
			String md5Hex = ID;
			for(int i=0; i<2017; i++) {
				byte[] bytesOfMessage = md5Hex.getBytes("UTF-8");
				
				MessageDigest md = MessageDigest.getInstance("MD5");
				byte[] thedigest = md.digest(bytesOfMessage);
				md5Hex = getHexFromMD5(thedigest);
			}

			return md5Hex;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return "0";
	}
	
	public static String getHexFromMD5(byte[] thedigest) {
		
		
		String ret = "";
		int temp;
		
		for(int i=0; i<thedigest.length; i++) {
			temp = thedigest[i];
		
			//first number:
			if(thedigest[i] < 0) {
				temp += 128;

				ret += getHexFromNumber(temp/16 + 8);
			} else {
				ret += getHexFromNumber(temp/16);
			}
			
			ret += getHexFromNumber(temp % 16);
			
			
		}
		
		return ret;
	}
	
	public static String getHexFromNumber(int number) {
		if(number < 10) {
			return "" + (char)((int)'0' + number);
		} else {
			return "" + (char)((int)'a' + number - 10);
		}
	}
}
