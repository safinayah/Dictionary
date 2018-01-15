/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dict;


/**
 *
 * @author ayah
 */

public class HashMap {
	int TABLE_SIZE;
	int current = 0;
	HashEntry[] table;
        HashMap(){}

	HashMap(int size) {

		table = new HashEntry[size];
		for (int i = 0; i < TABLE_SIZE; i++)
			table[i] = null;
		TABLE_SIZE = size;
		current = 0;
	}

	public int tablesize() {
		return TABLE_SIZE;
	}

	private int size() {
		return current;
	}

	private int getHash(String key) { // hash function
		int hashValue = 0; // array index
		for (int i = 0; i < key.length(); i++)
			hashValue = (2 << 5) * hashValue + key.charAt(i);
		hashValue %= TABLE_SIZE;
		if (hashValue < 0)
			hashValue += TABLE_SIZE;
		return hashValue;
	}

	public void delete(String key) {
		int hashValue = getHash(key);

		for (int i = 0; i < key.length(); i++)
			if (table[hashValue] != null && table[hashValue].getValue() != 0
					&& !table[hashValue].getWord().getW().equalsIgnoreCase(key))
				hashValue = (2 << 5) * hashValue + key.charAt(i);
		current--;
		// ++hashValue;
		table[hashValue] = null;

	}

	private String getKey(String key) {
		int index = getHash(key);
		if (table[index] == null) {
			return null;
		}
		return table[index].getWord().getW();
	}

	public void insert(Word key) {
	
		int hashValue = getHash(key.getW()); // hash the key
		if (table[hashValue] != null) {
			++hashValue;
			hashValue %= TABLE_SIZE; // if there is a collision choose a new
										// location
		}

		current++;
		table[hashValue] = new HashEntry(key);

	}



	public boolean Search(String name) {
		for (int i = 0; i < TABLE_SIZE; i++) {
			HashEntry entr = table[i];
			if (entr != null && entr.getWord().getW().equalsIgnoreCase(name)) {
      				return true;
			}
		}
		return false;
	}

	public int findPos(Word x) {
		int collisionNum = 1;
		int currentPos = getHash(x.getW());

		while (table[currentPos] != null && !table[currentPos].getWord().getW().equalsIgnoreCase(x.getW())) {
			currentPos += collisionNum;
			collisionNum += 2;

			if (currentPos >= table.length)
				currentPos -= table.length;
		}

		return currentPos;
	}

	public void printHashTable() {
		System.out.println("\n\nHash Table : ");
		for (int i = 0; i < TABLE_SIZE; i++)
			System.out.println(table[i]);
	}
        public boolean contains(String name) {
		for (int i = 0; i < TABLE_SIZE; i++) {
			HashEntry entr = table[i];
			if (entr != null && entr.getWord().getW().equalsIgnoreCase(name)) {
				return true;
			}
		}
		return false;
	}


}