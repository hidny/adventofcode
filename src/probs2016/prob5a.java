package probs2016;


import java.security.*;

public class prob5a {
	public static void main(String args[]) {
		
		
		char password[] = new char[8];

		for(int i=0; i<8; i++) {
			password[i] = ' ';
		}
		boolean continueSearching;
		
		boolean isNeg;
		
		char charToAdd;
		
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

				for(int j=0; j<3 && j<thedigest.length; j++) {
					if(j < 2) {
						if(thedigest[j] != 0) {
							break;
						}
					} else if(j == 2){
						if(thedigest[j] >=0 && thedigest[j] < 8) {
							System.out.println("Hello " + i);
							
							isNeg = false;
							if(thedigest[3] < 0) {
								isNeg = true;
								thedigest[3] += 128;
							}
							thedigest[3] /= 16;
							
							int num = 0;
							int mult = 1;
							while(thedigest[3] > 0) {
								if(thedigest[3] % 2 == 0) {
									
								} else {
									num += mult;
								}
								mult*=2;
								thedigest[3] /= 2;
							}
							if(isNeg) {
								num += 8;
							}
							
							if(num >= 10) {
								charToAdd =(char)(num - 10 + 'a');
							} else {
								charToAdd = (char)(num + '0');
							}
							
							if(password[thedigest[j]] == ' ') {
								password[thedigest[j]] = charToAdd;
							}
							
							continueSearching = false;
							for(int k=0; k<8; k++) {
								if(password[k] == ' ') {
									continueSearching = true;
								}
								System.out.print(password[k]);
							}
							System.out.println();
							
							if(continueSearching == false) {
								break SEARCHPASS;
							}
						}
					}
				}
				
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		String answer = "";
		for(int i=0; i<8; i++) {
			answer += password[i];
		}
		System.out.println("Password: " + answer);
	}
}
