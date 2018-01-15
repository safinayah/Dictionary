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
public class AVLNode {

     Word word;
// Data in the node
     AVLNode left;
// Left child
     AVLNode right;
// right child
     int height;
// Height
// Constructors
    public AVLNode(){}//no args const
    public AVLNode(Word word) {
        this(word, null, null);
    }

    public AVLNode(Word word, AVLNode left, AVLNode right) {
      this.word= word;
      this.left=left; 
      this.right=right; 
      this.height=0;
      
 
      
      
    }

    public Word getWord() {
        return word;
    }

    public void setWord(Word word) {
        this.word = word;
    }

    public AVLNode getLeft() {
        return left;
    }

    public void setLeft(AVLNode left) {
        this.left = left;
    }

    public AVLNode getRight() {
        return right;
    }

    public void setRight(AVLNode right) {
        this.right = right;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "AVLNode{" + "word=" + word + '}';
    }
    

  
}
