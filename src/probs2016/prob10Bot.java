package probs2016;

public class prob10Bot {

	boolean lowBot;
	int low;

	boolean highBot;
	int high = -1;
	
	int slots[] = new int[2];
	
	public prob10Bot() {
		slots[0] = -1;
		slots[1] = -1;
		
	}

	public boolean isFull() {
		if(slots[1] != -1) {
			return true;
		} else {
			return false;
		}
	}
	
	public void add(int num, int giverIndex, prob10Bot bots[], prob10Output outputs[]) {
		System.out.println("Add " + num + " to bot " + giverIndex);
		if(slots[0] == -1) {
			slots[0] = num;
		} else if(slots[1] == -1){
			slots[1] = num;
			if(high >=0) {
				prob10Bot.botGive(this, giverIndex, bots, outputs);
			}
		} else {
			System.out.println("AAH!");
			System.exit(1);
			
		}
	}

	public void setLow(boolean lowbot, int low) {
		if(lowbot) {
			System.out.println("Set low to bot " + low);
		} else {
			System.out.println("Set low to output " + low);
			
		}
		this.lowBot = lowbot;
		this.low = low;
	}
	
	public void setHigh(boolean highbot, int high) {
		if(highbot) {
			System.out.println("Set high to bot " + high);
		} else {
			System.out.println("Set high to output " + high);
			
		}
		this.highBot = highbot;
		this.high = high;
	}
	
	public static void botGive(prob10Bot giver, int giverIndex, prob10Bot bots[], prob10Output outputs[]) {
		int lowValue;
		int highValue;
		
		
		if(giver.slots[0] < giver.slots[1]) {
			lowValue = giver.slots[0];
			highValue = giver.slots[1];
		} else {
			lowValue = giver.slots[1];
			highValue = giver.slots[0];
		}
		System.out.println("**bot " + giverIndex + " is giving " + lowValue + " and " + highValue);
		
		if(lowValue == 17 && highValue ==61) {
			System.out.println("Answer to giver index: " + giverIndex);
			//System.exit(1);
		}
		if(lowValue == 17) {
			System.out.println("***" + 17);
		}
		if(highValue == 61) {
			System.out.println("***" + 17);
		}
		
		if(giver.lowBot) {
			System.out.println("low to bot");
			bots[giver.low].add(lowValue, giver.low, bots, outputs);
		} else {
			System.out.println("low to output");
			outputs[giver.low].addOutput(lowValue);
		}
		
		if(giver.highBot) {
			System.out.println("high to bot");
			bots[giver.high].add(highValue, giver.high, bots, outputs);
		} else {
			System.out.println("high to output");
			outputs[giver.high].addOutput(highValue);
		}
		

		giver.slots[0] = -1;
		giver.slots[1] = -1;
	}
}
