/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dict;

import dict.Word;

/**
 *
 * @author ayah
 */

public class HashEntry {
	private Word word;
	double value;

	public HashEntry() {}

	public HashEntry(Word word, double value) {
		
		this.word = this.word;
		this.value = value;
	}

	public HashEntry(Word word) {
		
		this.word = word;

	}



	public double getValue() {
		return value;
	}

	public void setDeleteValue(double value) {
		 value = 2;
	}

    public Word getWord() {
        return word;
    }

    public void setWord(Word word) {
        this.word = word;
    }

    @Override
    public String toString() {
        return "HashEntry{" + "word=" + word + '}';
    }

	
}