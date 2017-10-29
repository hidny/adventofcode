package probs2015;

import java.util.ArrayList;

public class prob15Ingredient {

	private int capacity;
	private int durability;
	private int flavor;
	private int texture;
	private int calories;
	
	public prob15Ingredient(String line) {
		String token[] = line.split(" ");
		this.capacity = Integer.parseInt(token[2].substring(0, token[2].length() - 1));
		this.durability = Integer.parseInt(token[4].substring(0, token[4].length() - 1));
		this.flavor = Integer.parseInt(token[6].substring(0, token[6].length() - 1));
		this.texture = Integer.parseInt(token[8].substring(0, token[8].length() - 1));
		this.calories = Integer.parseInt(token[10]);
		
		System.out.println(this.capacity + " " + this.durability + " " + this.flavor + " " + this.texture + " " + this.calories);
	}

	public static long calcScore(ArrayList<prob15Ingredient> list, int amounts[], boolean part2) {
		long capacity = 0; 
		long durability = 0;
		long flavor = 0;
		long texture = 0;
		long calories = 0;
		for(int i=0; i<list.size(); i++) {
			capacity = capacity + list.get(i).capacity * amounts[i];
			durability = durability + list.get(i).durability * amounts[i];
			flavor = flavor + list.get(i).flavor * amounts[i];
			texture = texture + list.get(i).texture * amounts[i];
			calories = calories + list.get(i).calories * amounts[i];
			
		}
		
		if(capacity <= 0 || durability<=0 || flavor<=0 || texture<=0) {
			return 0;
		}
		
		if(part2 && calories != 500) {
			return 0;
		}
		
		long ret = capacity* durability * flavor * texture;
		if(ret < 0) {
			ret = 0;
		}
		
		return ret;
	}
}
