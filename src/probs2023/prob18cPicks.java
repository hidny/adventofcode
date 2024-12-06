package probs2023;
import java.io.File;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

import number.IsNumber;
import utils.Mapping;
import utils.Sort;

public class prob18cPicks {

	//TODO: Redo day 18 with the triangle formula...
	// See: https://www.reddit.com/r/adventofcode/comments/18l2tap/2023_day_18_the_elves_and_the_shoemaker/
	//day1 part 1
	//2:38.01
	
	public static void main(String[] args) {
		Scanner in;
		try {
			in = new Scanner(new File("in2023/prob2023in18.txt"));
			//in = new Scanner(new File("in2023/prob2023in0.txt"));

			String line;
			
			ArrayList <String>lines = new ArrayList<String>();
			
			
			//dir: 0 up
			//1 right
			//2 down
			//3 left
			
			while(in.hasNextLine()) {
				line = in.nextLine();
				lines.add(line);
				
			}
			
			int curi = 0;
			int curj = 0;
			
			int maxj= curi;
			int maxi= curj;

			int minj= curi;
			int mini= curj;
			
			int oldi = -1;
			int oldj = -1;
			
			char dir = '3';
			char olddir='2';

			for(int i=0; i<lines.size(); i++) {
				
				line = lines.get(i);
				//sopl(line);
				
				
				String token[] = line.split(" ");
				
				olddir = dir;
				dir = token[2].charAt(token[2].length() - 2);
				
				if(olddir == dir) {
					//Check that we don't go the same direction twice in a row.
					exit(1);
				}
				
				String amountHex = token[2].substring(2,7);
				
				int amount = hexToInt(amountHex);

				oldi = curi;
				oldj = curj;
				
				
				
				//sopl(dir + " " + amount);
				
				if(dir == 'U'  || dir == '3') {
					
					curi-= amount;
					
				} else if(dir == 'R' || dir == '0') {
					curj+= amount;
				} else if(dir == 'D' || dir == '1') {
					curi+= amount;
					
				} else if(dir == 'L' || dir == '2') {
					curj-= amount;
				}
				
				if(curi >maxi) {
					maxi = curi;
				}
				
				if(curi <mini) {
					mini = curi;
				}
				
				if(curj<minj) {
					minj=curj;
				}
				
				if(curj>maxj) {
					maxj =curj;
				}
				
			}
			
			
			sopl("(length i, length j) = (" + (maxi - mini + 1) + ", " + (maxj - minj + 1) + ")");
			
			double cur = 0.0;
			
			double centerI = mini;
			double centerJ = minj;
			
			long perimeter = 0L;

			for(int i=0; i<lines.size() + 1; i++) {
				
				line = lines.get(i % lines.size());
				//sopl(line);
				
				String token[] = line.split(" ");
				
				dir = token[2].charAt(token[2].length() - 2);
				
				if(i > 0 && olddir == dir) {
					sopl("Same as olddir");
					//Check that we don't go the same direction twice in a row.
					exit(1);
				}

				olddir = dir;
				
				String amountHex = token[2].substring(2,7);
				
				int amount = hexToInt(amountHex);

				oldi = curi;
				oldj = curj;
				
				sopl(dir + " " + amount);
				
				if(dir == 'U'  || dir == '3') {
					
					curi-= amount;
					
				} else if(dir == 'R' || dir == '0') {
					curj+= amount;
				} else if(dir == 'D' || dir == '1') {
					curi+= amount;
					
				} else if(dir == 'L' || dir == '2') {
					curj-= amount;
				}
				
				if(i >0) {
					
					perimeter += Math.abs(curj - oldj) + Math.abs(curi - oldi);
					
					if(curj != oldj) {

						sopl("j: " + oldj + " to " + curj);
						double tmp= (centerI - curi) * ( 0.0 + curj - oldj) / 2.0;
						
						sopl(tmp);
						
						cur += tmp;
						
					} else {
						
						sopl("i: " + oldi + " to " + curi);
						
						double tmp = (centerJ - curj) * ((0.0 + oldi - curi) / 2.0);
						sopl(tmp);
						
						cur += tmp;
					}
					
				}
				
				
			}

			sopl("Done");
			
			long answer = (long)cur;
			//Real answer:
			//45757884535661
			sopl(cur);
			sopl("Answer: " + answer);
			//45757884535661
			//45755978050114
			//45756897443785
			
			sopl("Diff:     " + (answer - 45757884535661L));
			sopl("Perimeter: " + perimeter);
			
			sopl("perimeter divided 2: " + (perimeter / 2));
			//Plan: 
			//45757884535661
			
			//952407302304
			
			//TODO: why does this work?
			sopl("Real answer: " + (answer + perimeter/2 + 1));
			
			//Answer:
			/*
			 * [Language: Python]

I ran through the list of instructions and found the coordinates of each vertex. I then used the shoelace formula to calculate the area based on the coordinates of each vertex. To account for the thickness of the paint I needed to add half the perimeter of the polygon to the vertices I found, and to account for the missing exterior corners I needed to add one to that, giving a final formula of

(
    ((xs * (np.roll(ys, 1) - np.roll(ys, -1))).sum()) / 2
    + sum(abs(np.diff(ys)) + abs(np.diff(xs))) / 2
    + 1
)
Part 2 was very similar

------
[–]CutOnBumInBandHere9 3 points 11 days ago 
the correct answer is 62, the zero-width line area is 42, the perimeter is 38. Divided by 2 gives 19. 19+42 is 61, which isn't right.

It's very close to being right though! And the off-by-one-ness isn't an accident. When you add 0.5 * the perimeter, you're saying that to each segment of your polygon, you add a rectangle that has a width of 0.5. And that's basically what you want.

The problem happens at the corners: At each exterior corner, you're going to be missing a 0.5x0.5 piece. Similarly, at each interior corner the rectangles sticking out of the sides are going to overlap - by the same 0.5x0.5.

You need to account for these errors as well, by looking at how many exterior vs interior corners there are. When you go all the way around the polygon you go through one full rotation, so there will always be four more exterior corners than interior corners, giving a missing area of 4x0.5x0.5 = 1
			 */

		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	
	
	

	public static int hexToInt(String hex) {
		int ret = 0;
		for(int i=0; i<hex.length(); i++) {
			
			char cur = hex.charAt(i);
			
			if(cur >= '0' && cur <='9') {
				ret = 16*ret + (int)(cur - '0');
			} else if(cur >= 'a' && cur <= 'f') {
				ret = 16*ret + (int)(cur - 'a' + 10);
				
				
			} else {
				sopl(cur);
				exit(1);
			}
		}
		return ret;
	}

	public static void sop(Object a) {
		System.out.print(a.toString());
	}
	public static void sopl(Object a) {
		System.out.println(a.toString());
	}

	public static void sopl() {
		System.out.println();
	}
	
	public static int pint(String s) {
		if (IsNumber.isNumber(s)) {
			return Integer.parseInt(s);
		} else {
			sop("Error: (" + s + ") is not a number");
			return -1;
		}
	}
	
	public static void exit() {
		exit(0);
	}
	public static void exit(int code) {
		sop("Exit with code " + code);
		
		System.exit(code);
	}

}

/*Reddit:
Boojum
OP

 * Here's a little animation showing the working of the shoelace formula applied to Part 2.

The key to the shoelace formula is that you trace along the polygon and for each edge (x1,y1) to (x2,y2) you accumulate half of the determinant of the 2x2 matrix formed from the those two vectors. In other words, you add (x1*y2 - x2*y1)/2 to your area total.

This works because geometrically the determinant computes double the area of the triangle between (0,0), (x1,y1), and (x2,y2). (Really it's computing the area of the parallelogram between (0,0), (x1,y1), (x2,y2), and (x1+x2,y1+y2).)

But note that the area that you get from the determinant is signed! Here I add a green triangle for where the signed area is positive and red when it's negative (you'll see that the running total sometimes decreases.) Geometrically, the sign of the area corresponds to which way the triangle winds. Here, that means that green is clockwise and red is counterclockwise.

Because of inclusion and exclusion, everything ends up accounted for correctly when you total up the signed area. Each of the red (negative area) triangles eventually gets covered by a green triangle a little further out, which cancels the negative area and then some.

That only gotcha is needing to augment the shoelace formula with Pick's theorem, since the shoelace formula only computes the interior area, while the puzzle includes the tiles along the border.

This was made with a small Python visualization framework that I wrote during last year's Advent of Code. See here for details. Full source for this visualization is in the link below.

Source


14


Reply
Share


User avatar
level 2
echols021
·
7 hr. ago
This is an awesome visualization!

Rather than ending with Pick's theorem, I ended with shoelace_area + (perimeter / 2) + 1. My thinking was to add padding with width 0.5 to the full perimeter of the polygon.

For the straight portions of the perimeter, the area of the padding is just distance * 0.5, which in total comes out to perimeter / 2.

For corners:

Each convex corner contributes an extra little square with area 0.25

Each concave corner has an overlap between the padding from the two straight portions of the perimeter, so you have to subtract an area of 0.25

Since the shape is a closed loop, there are 4 more convex corners than concave, so it all cancels out down to 0.25 * 4, meaning the corners' padding in total contributes an extra area of 1
*/
