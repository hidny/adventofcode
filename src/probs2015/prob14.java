package probs2015;

import java.io.File;
import java.util.Scanner;

public class prob14 {

	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2015/prob2015in14.txt"));
			
			boolean part2 = true;
			
			int TOTAL_TIME = 2503;
			
			int bestDist = 0;
			
			//100 is large enough for this problem...
			
			int dist[][] = new int[100][TOTAL_TIME];
			int point[] = new int[100];
			for(int i=0; i<100; i++) {
				point[i] = 0;
			}
			
			int reindeerInd = 0;
			
			String line = "";
			while(in.hasNextLine()) {
				line = in.nextLine();
				
				String token[] = line.split(" ");
				int speed = Integer.parseInt(token[3]);
				int speedTime = Integer.parseInt(token[6]);
				int restTime =  Integer.parseInt(token[13]);
				if(part2 == false) {
					if(getDistance(TOTAL_TIME, speed, speedTime, restTime) > bestDist) {
						bestDist = getDistance(TOTAL_TIME, speed, speedTime, restTime);
					}
				} else {
					
					for(int i=0; i<TOTAL_TIME; i++) {
						dist[reindeerInd][i] = getDistance(i+1, speed, speedTime, restTime);
					}
					
					reindeerInd++;
				}
				
				
			}
			
			if(part2 == false) {
			
				System.out.println("Answer: " + bestDist);
			} else {
				for(int i=0; i<TOTAL_TIME; i++) {
					bestDist = 0;
					for(int j=0; j<dist.length; j++) {
						if(dist[j][i] > bestDist) {
							bestDist = dist[j][i];
						}
					}
					
					for(int j=0; j<dist.length; j++) {
						if(dist[j][i] == bestDist) {
							point[j]++;
						}
					}
					
					
				}
				
				int bestCount = 0;
				for(int j=0; j<point.length; j++) {
					if(point[j] > bestCount) {
						bestCount = point[j];
					}
				}
				
				System.out.println("Answer: " + bestCount);
				
			}
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	
	public static int getDistance(int totalTime, int speed, int speedTime, int restTime) {
		int ret = 0;
		ret += (speed * speedTime) * (totalTime / (speedTime + restTime));
		
		ret += speed * Math.min(speedTime, totalTime % (speedTime + restTime));
		
		return ret;
	}
}
