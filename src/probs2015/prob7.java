package probs2015;

import java.io.File;
import java.util.Scanner;

public class prob7 {
	public static int TwoPowerSixteen = (int)Math.pow(2,  16);
	
	
	public static void main(String[] args) {
		Scanner in;
		try {
			System.out.println("Start");
			
			in = new Scanner(new File("in2015/prob2015in7.txt"));
			 
			boolean part2 = false;
			
			while(in.hasNextLine()) {
				prob7Gate.addToList(in.nextLine());
			}
			int answerPart1 = 0;

			answerPart1 = prob7Gate.wires[prob7Gate.convertLetterToIndex("a")].getValue();
			
			for(int i=0; i<prob7Gate.wires.length; i++) {
				if(prob7Gate.wires[i] != null) {
					System.out.println( prob7Gate.ConvertIndexToString(i) + ": " + prob7Gate.wires[i].getValue());
				}
						
			}
			
			if(part2 == false) {
				System.out.println("Answer: " + answerPart1);
			} else {
				//part 2:
				for(int i=0; i<prob7Gate.wires.length; i++) {
					if(prob7Gate.wires[i] != null) {
						prob7Gate.wires[i].resetValue();
					}
							
				}
				prob7Gate.wires[prob7Gate.convertLetterToIndex("b")].setValue(answerPart1);
				
				int answerPart2 = prob7Gate.wires[prob7Gate.convertLetterToIndex("a")].getValue();
				System.out.println("Answer: " + answerPart2);
			}
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}

}

/*
 * (ns adventofcode.day7
  (:require [loom.graph :as graph]
            [loom.io :as lio]
            [clojure.string :as string]
            [clojure.edn :as edn]))

(defn lines []
  (string/split-lines
    (slurp "resources/input-day7.txt")))

(defn parse-line [l]
  (let [parts (string/split l #" ")
        to (last parts)
        from (disj (set (remove
                         (comp integer? edn/read-string)
                         (butlast parts)))
                   "->" "NOT" "AND" "RSHIFT" "LSHIFT" "OR")]
    {to from}))

(defn graph []
  (graph/digraph (into {} (map parse-line) (lines))))

(comment (lio/dot (graph) "/tmp/wires.dot"))
*/
