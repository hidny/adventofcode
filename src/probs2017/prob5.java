package probs2017;

import java.io.File;

import java.util.ArrayList;
import java.util.Scanner;

public class prob5 {

	//Took way too long to read the instructions :(
	//  5   00:04:53   254      0   00:05:39   169      0
	
	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2017/prob2017in5.txt"));
			
			int count = 0;
			boolean part2 = true;
			String line = "";
			ArrayList <String>lines = new ArrayList<String>();
			
			while(in.hasNextLine()) {
				line = in.nextLine();
				String token[] = line.split(" ");
				
				lines.add(line);
			}
			
			int progCounter = 0;
			
			
			while(0 <= progCounter && progCounter < lines.size()) {
				int offset = Integer.parseInt(lines.get(progCounter));
				
				int next = progCounter + offset;
				
				if(part2 && offset >=3) {
					lines.set(progCounter, "" + (offset - 1));
				} else {
					lines.set(progCounter, "" + (offset + 1));
				}
				progCounter = next;
				count++;
			}
			
			System.out.println("Answer: " + count);
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	

}

/*//Mike Harris' solution in python:
//It's so much smaller. So much less typing!
a = open('input.txt', 'r').readlines()
b = map(int, a)
i = 0
count = 0

while 1:
	if i >= len(b) or i < 0:
		break
	count += 1
	n = i + b[i]
	b[i] += 1
	i = n
	
print count	
*/
