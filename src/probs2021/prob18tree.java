package probs2021;
import java.io.File;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

import number.IsNumber;
import utils.Mapping;
import utils.Sort;

public class prob18tree {

	boolean isleaf= false;
	
	long num = -7;
	
	prob18tree parent = null;
	prob18tree lhs = null;
	
	prob18tree rhs = null;
	
	public prob18tree(long num, prob18tree parent) {
		this.isleaf = true;
		this.num = num;
		this.parent = parent;
	}
	
	public prob18tree(prob18tree lhs, prob18tree rhs) {
		this.isleaf = false;
		this.lhs = lhs;
		this.rhs = rhs;
		this.lhs.parent = this;
		this.rhs.parent = this;
		
	}
	
	public prob18tree(String tree, int index, prob18tree parent) {
		
		this.parent = parent;
		
		if(tree.charAt(index) == '[') {
			this.lhs = new prob18tree(tree, index + 1, this);
			isleaf=false;
			
			int depth = 0;
			index=index+1;
			for(; index<tree.length(); index++) {
				
				if(depth == 0 && tree.charAt(index) ==',') {
					index++;
					break;
				} else if(tree.charAt(index) ==']') {
					
					depth--;
					if(depth < 0) {
						System.out.println("oops!");
						System.exit(1);
					}
				} else if(tree.charAt(index) =='[') {
					depth++;
				}
			}
			
			this.rhs = new prob18tree(tree, index, this);
			
		} else if(tree.charAt(index) >= '0' && tree.charAt(index) <= '9') {
			
			this.num = (long) (tree.charAt(index) - '0');
			this.isleaf = true;
			
			if(tree.charAt(index+1) >= '0' && tree.charAt(index+1) <= '9') {
				//TODO do something!
				sopl("2 digit number!");
				System.exit(1);
			}
			
		}
		
	}
	
	
	//public static long explodeToRightNum = -1;
	
	
	
	
	
	public static prob18tree reduce(prob18tree root) {
	
		while(hasExplodeOrSplit(root, 0)) {
			
			//sopl("Do reduce:");
			
			if(hasExplode(root, 0)) {
				//sopl("Explode:");
				root = reduceBy1ExplodeAction(root);
				if(reducedBy1ActionHappened == false) {
					sopl("oops!");
					System.exit(1);
				}
				
			} else {
				//sopl("Split:");
				root = reduceBy1SplitAction(root);
			}
			
			//printTree(root);
			//sopl();
			//sopl("-- (next reduce)");
			//sopl();
		}
		
		return root;
	}


	
	public static boolean hasExplodeOrSplit(prob18tree subtree, int depth) {
		
		
		if(subtree.isleaf) {
			if(subtree.num > 9) {
				return true;
			} else {
				return false;
			}
		} else if(depth == 4){
			//sopl("depth 4 tree: ");
			//printTree(subtree);
			//sopl();
			//sopl();
			return true;
		} else {
			return hasExplodeOrSplit(subtree.lhs, depth + 1) || hasExplodeOrSplit(subtree.rhs, depth + 1);
		}
	}


	public static boolean hasExplode(prob18tree subtree, int depth) {
		if(depth == 4 && subtree.isleaf == false){
			//sopl("depth 4 tree: ");
			//printTree(subtree);
			//sopl();
			//sopl();
			return true;
			
		} else if(subtree.isleaf == false) {
			return hasExplode(subtree.lhs, depth + 1) || hasExplode(subtree.rhs, depth + 1);
			
		} else {
			return false;
		}
	}
	
	public static boolean reducedBy1ActionHappened = false;
	//public static prob18tree leftNum = null;
	
	public static prob18tree reduceBy1ExplodeAction(prob18tree root) {
		reducedBy1ActionHappened = false;
		return reduceBy1ExplodeAction(root, 0);
	}
	

	public static prob18tree reduceBy1ExplodeAction(prob18tree subtree, int depth) {
		
		if(reducedBy1ActionHappened) {
			return subtree;
		}
		
		if(depth == 4 && subtree.isleaf == false) {
			
			//sopl("Depth 4 [" + subtree.lhs.num +"," + subtree.rhs.num + "]");
			
			subtree.isleaf = true;
			subtree.num = 0;
			
			//sopl("Find left landing zone");
			prob18tree leftExplodeLandingZone = findLeftExplodeLandingZone(subtree);
			
			if(leftExplodeLandingZone != null) {
				leftExplodeLandingZone.num += subtree.lhs.num;
				
			} else {
				//Do nothing
				
			}
			
			
			//sopl("Find right landing zone");
			prob18tree rightExplodeLandingZone = findRightExplodeLandingZone(subtree);
			
			if(rightExplodeLandingZone != null) {
				rightExplodeLandingZone.num += subtree.rhs.num;
				
			} else {
				//Do nothing
				
			}
			
			
			if(leftExplodeLandingZone == null && rightExplodeLandingZone == null) {
				sopl("oops! Both landing zones null!");
				System.exit(1);
			}
			subtree.lhs = null;
			subtree.rhs = null;
			
			reducedBy1ActionHappened = true;
			
		} else if(subtree.isleaf == false) {
			subtree.lhs = reduceBy1ExplodeAction(subtree.lhs, depth+1);
			

			subtree.rhs = reduceBy1ExplodeAction(subtree.rhs, depth+1);
			
		
		}
		
		return subtree;
	}

	public static prob18tree reduceBy1SplitAction(prob18tree root) {
		reducedBy1ActionHappened = false;
		return reduceBy1SplitAction(root, 0);
	}
	
	public static prob18tree reduceBy1SplitAction(prob18tree subtree, int depth) {

		if(reducedBy1ActionHappened) {
			return subtree;
		}
		
		if(depth == 4 && subtree.isleaf == false) {
			sopl("oops! Explode!");
			System.exit(1);
			
		} else if(subtree.isleaf == false) {
			subtree.lhs = reduceBy1SplitAction(subtree.lhs, depth+1);
			

			subtree.rhs = reduceBy1SplitAction(subtree.rhs, depth+1);
			
	
		} else if(subtree.num > 9) {
			//sopl("split " + subtree.num );
			
			subtree.isleaf = false;
			subtree.lhs = new prob18tree(subtree.num / 2, subtree);
			subtree.rhs = new prob18tree((int)(subtree.num+1) / 2, subtree);
			subtree.num = -24;
			
			reducedBy1ActionHappened = true;
			
		}
		
		return subtree;
	}
	
	
	//I could make a mirror of this to find left explode landing zone...
	public static prob18tree findRightExplodeLandingZone(prob18tree origNode) {
		
		if(origNode.isleaf == false) {
			sopl("oops!");
			System.exit(1);
		}
		

		prob18tree prevNode;
		prob18tree curNode = origNode;
		do {
			prevNode = curNode;
			curNode = curNode.parent;
			
		} while (curNode != null && curNode.rhs == prevNode);
		
		if(curNode==null) {
			return null;
		} else {
			
			prob18tree keysubtree = curNode.rhs;
			
			while(keysubtree.isleaf == false) {
				keysubtree = keysubtree.lhs;
			}
			
			return keysubtree;
			
		}
	}
	
	public static prob18tree findLeftExplodeLandingZone(prob18tree origNode) {
		
		if(origNode.isleaf == false) {
			sopl("oops!");
			System.exit(1);
		}
		

		prob18tree prevNode;
		prob18tree curNode = origNode;
		do {
			prevNode = curNode;
			curNode = curNode.parent;
			
		} while (curNode != null && curNode.lhs == prevNode);
		
		if(curNode==null) {
			return null;
		} else {
			
			prob18tree keysubtree = curNode.lhs;
			
			while(keysubtree.isleaf == false) {
				keysubtree = keysubtree.rhs;
			}
			
			return keysubtree;
			
		}
	}
	
	
	
	public static void sopl(Object a) {
		System.out.println(a.toString());
	}
	public static void sopl() {
		System.out.println();
	}
	
	
	public static long getMagnitude(prob18tree a) {
		if(a.isleaf) {
			return a.num;
		} else {
			return 3 * getMagnitude(a.lhs) + 2 * getMagnitude(a.rhs);
		}
	}
	
	public static void printTree(prob18tree a) {
		
		if(a.isleaf) {
			sop(a.num);
		} else {
			sop("[");
			printTree(a.lhs);
			if(a.lhs.parent != a) {
				sopl("doh! 1");
				System.exit(1);
			}
			sop(",");
			if(a.rhs.parent != a) {
				sopl("doh! 2");
				System.exit(1);
			}
			printTree(a.rhs);
			sop("]");
		}
		
		
	}
	
	public static void sop(Object a) {
		System.out.print(a.toString());
	}
	
	
	public static prob18tree addTrees(prob18tree lhs, prob18tree rhs) {
		prob18tree newTree = new prob18tree(lhs, rhs);
		//TODO
		return null;
	}
	
	
}
