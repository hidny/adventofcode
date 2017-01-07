package utils;

import java.security.MessageDigest;

public class MD5 {
public static String getHexFromMD5(String ID) {
		
		try {
			byte[] bytesOfMessage = ID.getBytes("UTF-8");
			
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] thedigest = md.digest(bytesOfMessage);
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
		} catch(Exception e) {
			e.printStackTrace();
		}
		return "0";
	}

	
	public static String getHexFromNumber(int number) {
		if(number < 10) {
			return "" + (char)((int)'0' + number);
		} else {
			return "" + (char)((int)'a' + number - 10);
		}
	}
}
