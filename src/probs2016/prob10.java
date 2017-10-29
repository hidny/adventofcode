package probs2016;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class prob10 {

	public static String result = "";
	
	public static int NUM_BOTS = 500;
	public static prob10Bot bots[] = new prob10Bot[NUM_BOTS];
	public static prob10Output outputs[] = new prob10Output[NUM_BOTS];
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		for(int i=0; i<bots.length; i++) {
			bots[i] = new prob10Bot();
			outputs[i] = new prob10Output();
		}
		Scanner in;
		Scanner in2;
		try {
			 in = new Scanner(new File("prob10in.txt"));
			 //in = new Scanner(new File("prob10intest.txt"));
			 in2 = new Scanner(System.in);
			 String line;
			 
			 int answer = 0;
				System.out.println("Start");
			 while(in.hasNextLine()) {
				 line = in.nextLine();
				 
				 System.out.println("Line: " + line);
				 
				 String token[] = line.split(" ");
				 
				 if(line.startsWith("value")) {
					 int value = Integer.parseInt(token[1]);
					 int bot = Integer.parseInt(token[5]);
					 
					 bots[bot].add(value, bot, bots, outputs);
					 
					 
				 } else {
					 int giverIndex = Integer.parseInt(token[1]);
					 boolean lowbot = false;
					 if(token[5].equals("bot")) {
						 lowbot = true;
					 }
					 int lowValue = Integer.parseInt(token[6]);
					 
					 bots[giverIndex].setLow(lowbot, lowValue);
					 
					 boolean highbot = false;
					 if(token[10].equals("bot")) {
						 highbot = true;
					 }

					 int highValue = Integer.parseInt(token[11]);
					 

					 bots[giverIndex].setHigh(highbot, highValue);
					 
					 if(bots[giverIndex].isFull()) {
						 prob10Bot.botGive(bots[giverIndex], giverIndex, bots, outputs);

						 
					 }
				 }
				
				 /*
				 in2.next();
				 */
			 }
			 
			 System.out.println(outputs[0].output.size());
			 System.out.println(outputs[1].output.size());
			 System.out.println(outputs[2].output.size());
			 
			 System.out.println("Answer: " + outputs[0].output.get(0) * outputs[1].output.get(0) * outputs[2].output.get(0));
			 in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
			 
	}
	
	//You got rank 208 on this star's leaderboard.
//1:03
}
