

import java.security.*;

public class prob5 {
	public static void main(String args[]) {
		
		
		String password = "";
		
		SEARCHPASS:
		for(int i=0; true; i++) {
			//String ID = "abc" + i;
			String ID = "uqwqemis" + i;
			
			try {
				byte[] bytesOfMessage = ID.getBytes("UTF-8");
		
				MessageDigest md = MessageDigest.getInstance("MD5");
				byte[] thedigest = md.digest(bytesOfMessage);
				
				if(thedigest.length % 4 != 0) {
					System.out.println(ID);
					System.out.println("Not right length");
					System.out.println(thedigest.length);
					System.exit(1);
				}
				int num = 0;
				for(int j=0; j<3 && j<thedigest.length; j++) {
					if(j < 2) {
						if(thedigest[j] != 0) {
							break;
						}
					} else if(j == 2){
						if(thedigest[j] >=0 && thedigest[j] < 16) {
							num = thedigest[j];
							System.out.println("Num: " + num);
							System.out.println("i: " + i);
							if(num >= 10) {
								password += (char)(num - 10 + 'a');
							} else {
								password += num + "";
							}
							
							if(password.length() >= 8) {
								break SEARCHPASS;
							}
						}
					}
				}
				
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("Password: " + password);
	}
}
