

/*'
 * --- Day 4: Security Through Obscurity ---

Finally, you come across an information kiosk with a list of rooms. Of course, the list is encrypted and full of decoy data, but the instructions to decode the list are barely hidden nearby. Better remove the decoy data first.

Each room consists of an encrypted name (lowercase letters separated by dashes) followed by a dash, a sector ID, and a checksum in square brackets.

A room is real (not a decoy) if the checksum is the five most common letters in the encrypted name, in order, with ties broken by alphabetization. For example:

aaaaa-bbb-z-y-x-123[abxyz] is a real room because the most common letters are a (5), b (3), and then a tie between x, y, and z, which are listed alphabetically.
a-b-c-d-e-f-g-h-987[abcde] is a real room because although the letters are all tied (1 of each), the first five are listed alphabetically.
not-a-real-room-404[oarel] is a real room.
totally-real-room-200[decoy] is not.
Of the real rooms from the list above, the sum of their sector IDs is 1514.

What is the sum of the sector IDs of the real rooms?

To begin, get your puzzle input.
 */
import java.io.File;
import java.util.Collections;
import java.util.Scanner;

public class prob4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int answer = 0;
		Scanner in;
		try {
			 in = new Scanner(new File("prob4in.txt"));
			 
			 String tokens[];
			 
			 prob4Object objects[];
			 
			 while(in.hasNextLine()) {
				 String line = in.nextLine().toLowerCase();
				 tokens = line.split("-");
				 
				 objects = prob4Object.initLetters();
				 
				 
				 for(int i=0; i<tokens.length - 1; i++) {
					 for(int j=0; j<tokens[i].length(); j++) {
						 objects[(int)(tokens[i].charAt(j) - 'a')].increment();
					 }
				 }
				 
				 prob4Object temp;
				 
				 for(int i=0; i<objects.length; i++) {
					 for(int j=i+1; j< objects.length; j++) {
						 if(objects[i].compareTo(objects[j]) > 0) {
							 temp = objects[i];
							 objects[i] = objects[j];
							 objects[j] = temp;
						 }
					 }
				 }
				 
				 //Testing line:
				 //System.out.print(line + "   ");
				 String calcAns = "";
				 for(int i=0; i<5; i++) {
					 calcAns += objects[i].getChar();
					 
				 }
				 //System.out.println(calcAns);
				 
				 
				 String lastToken = tokens[tokens.length - 1];
				 String numberPart = lastToken.substring(0, lastToken.indexOf('['));
				 String codePart = lastToken.substring(lastToken.indexOf('[') + 1, lastToken.indexOf('[') + 6);
				 
				 int number = Integer.parseInt(numberPart);
				 
				 if(codePart.equals(calcAns)) {
					 answer += number;
				 } else {
					 continue;
					 //not real...
				 }
				 
				 //At this point, we've got the real rooms:
				 String decode = "";
				 
				 int temp2;
				 //part 2:
				 for(int i=0; i<tokens.length - 1; i++) {
					 for(int j=0; j<tokens[i].length(); j++) {
						 temp2 = (int)(tokens[i].charAt(j) - 'a');
						 temp2 = (temp2 + number) % 26;
						 decode += (char)('a' + temp2);
					 }
					 decode += " ";
				 }
				 System.out.println("Decode: " + decode + " Sector ID: " + number);
				 
				 //AHA:
				 //Decode: northpole object storage  Sector ID: 984
			 }
			 
			 
			 
			 System.out.println("Answer to part 1: " + answer);
					
					
			 in.close();
		
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
		}

}
