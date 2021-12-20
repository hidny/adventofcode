package utils;

import java.util.Enumeration;
import java.util.Hashtable;

public class HashTableTally {

	public Hashtable<String, Long> hashTable = new Hashtable<String, Long>();
	
	
	
	public void add1ToHash(String key) {
		if(hashTable.containsKey(key)) {
			long val = hashTable.get(key);
			hashTable.remove(key);
			hashTable.put(key, val + 1L);
		} else {
			hashTable.put(key, 1L);
		}
	}
	

	public void addXToHash(String key, long x) {
		if(hashTable.containsKey(key)) {
			long val = hashTable.get(key);
			hashTable.remove(key);
			hashTable.put(key, val + x);
		} else {
			hashTable.put(key, x);
		}
	}
	
	public void clearKeyOfHash(String key) {
		if(hashTable.containsKey(key)) {
			hashTable.remove(key);
		}
	}
	
	public long get(String key) {
		return hashTable.get(key);
	}
	
	
	//How to enumerate:
	/*
	 * Enumeration<String> e = hashTable.keys();
			
			while(e.hasMoreElements() ) {
				String curKey = e.nextElement();
				if(hashTable.get(curKey) > 1) {
					count++;
				}
			}
	 */
	
	public Enumeration<String> getKeysInList() {
		return hashTable.keys();
	}
}
