package dict;

import java.util.Iterator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ayah
 */
public class AVLTree {

    private AVLNode root; // The tree root

    public AVLTree() // Construct the tree
    {
        root = null;
    }

    public void makeEmpty() // Make the tree logically empty.
    {
        root = null;
    }

    /*
Test if the tree is logically empty.
return true if empty, false otherwise.
     */
    public boolean isEmpty() {
        return root == null;
    }

    /*
Print the tree contents in sorted order.
     */
    public void printTree() {
        System.out.println("\n\nAVL Tree !!!!");
        if (isEmpty()) {
            System.out.println("Empty tree");
        } else  {
            printTree(root);
        }
    }

    
    /*
method to print the tree in sorted order.
t the node that roots the tree.
     */
    private void printTree(AVLNode t) // inorder traversal
    {
        if (t != null) {
            printTree(t.getLeft());
            System.out.println(t.getWord() + " (" + height(t) + ")");
            printTree(t.getRight());
        }
    }

    public void stringify(AVLNode t, StringBuilder s) {

        if (t != null) {
            stringify(t.getLeft(), s);
            int n = t.getWord().getSyn().size();
            s.append(t.getWord().getW() + ":");
            for (int i = 0; i < n; i++) {
                String syn = t.getWord().getSyn().get(i);
                s.append(syn);
                if (i < n - 2) {
                    s.append(",");
                } else if (i < n - 1) {
                    s.append("/");
                } else {

                }
            }
            s.append("*" + t.getWord().getOps());

            s.append("\n");
            stringify(t.getRight(), s);
        }

    }

    /*
Insert into the tree; duplicates are ignored.
x the item to insert.
     */
    public void insert(Word x) {
        root = insert(x, root);
    }

    /*
method to insert into a subtree.
x the item to insert.
t the node that roots the subtree.
return the new root of the subtree.
     */
    AVLNode insert(Word x, AVLNode t) {

        if (t == null) {
            return new AVLNode(x, null, null);
        }
        if (x.getW().equalsIgnoreCase(t.word.getW())) {
            System.out.println("Word is exist");
        }
        if (x.getW().charAt(0) <= t.getWord().getW().charAt(0)) {
            t.setLeft(insert(x, t.getLeft()));
            if (height(t.getLeft()) - height(t.getRight()) == 2) {
                if (x.getW().charAt(0) < t.getLeft().getWord().getW().charAt(0)) {
                    t = rotateLeft(t);
                } else {
                    t = doubleLeft(t);
                }
            }
        } else if (x.getW().charAt(0) > t.getWord().getW().charAt(0)) {
            t.setRight(insert(x, t.getRight()));
            if (height(t.getRight()) - height(t.getLeft()) == 2) {
                if (x.getW().charAt(0) > t.getRight().getWord().getW().charAt(0)) {
                    t = rotateRight(t);
                } else {
                    t = doubleRight(t);
                }
            }

        }

        t.setHeight(Math.max(height(t.getLeft()), height(t.getRight())) + 1);
        balance(t);
        return t;
    }

    /*
Find an item in the tree.
x the item to search for.
return true if x is found.
     */
 /*
Find an item in the tree.
x the item to search for.
return true if x is found.
     */
    public boolean search(Word x) {
        return search(x, root);
    }

    /*
method to find an item in a subtree.
x is item to search for.
t the node that roots the tree.
return true if x is found in subtree.
     */
    private boolean search(Word x, AVLNode t) {
        while (t != null) {
            if (x.getW().charAt(0) < t.getWord().getW().charAt(0)) {
                t = t.getLeft();
            } else if (x.getW().charAt(0) > t.getWord().getW().charAt(0)) {
                t = t.getRight();
            } else {
                return true; // Match
            }
        }
        return false; // No match
    }
    
   
    

    public AVLNode delete(String data2, AVLNode curr) {
        if (curr == null) {
            return null;
        }
        if (data2.charAt(0) > curr.getWord().getW().charAt(0)) {
            curr.setRight(delete(data2, curr.getRight()));
        } else if (data2.charAt(0) < curr.getWord().getW().charAt(0)) {
            curr.setLeft(delete(data2, curr.getLeft()));
        } else if (curr.getLeft() != null && curr.getRight() != null) {
            curr.setWord(findMin(curr.getRight()).getWord());
            curr.setRight(delete(curr.getWord().getW(), curr.getRight()));
        } else {
            curr = (curr.getLeft() == null) ? curr.getRight() : curr.getLeft();
        }
        return balance(curr);
    }

      public AVLNode deleteByChar(char data, AVLNode curr) {


        if (curr == null) {
            return null;
        }
        if (data > curr.getWord().getW().charAt(0)) {
            curr.setRight(deleteByChar(data, curr.getRight()));
        } else if (data < curr.getWord().getW().charAt(0)) {
            curr.setLeft(deleteByChar(data, curr.getLeft()));
        } else if (curr.getLeft() != null && curr.getRight() != null) {
            curr.setWord(findMin(curr.getRight()).getWord());
            curr.setRight(deleteByChar(curr.getWord().getW().charAt(0), curr.getRight()));
        } else {
            curr = (curr.getLeft() == null) ? curr.getRight() : curr.getLeft();
        }
        return balance(curr);
    }
    /**
     * Find the smallest item in the tree.
     *
     * @return smallest item or null if empty.
     */
    public AVLNode search(String newdata, AVLNode curr) {
        if (curr == null) {
            return curr;
        } else if (newdata.compareToIgnoreCase((curr.getWord().getW())) > 0) {
            return search(newdata, curr.getRight());
        } else if (newdata.compareToIgnoreCase(curr.getWord().getW()) < 0) {
            return search(newdata, curr.getLeft());
        } else {
            return curr;
        }

    }
    
   

    private int height(AVLNode t) // return height of node t, or -1, if null.
    {
        if (t == null) {
            return -1;
        } else {
            return t.getHeight();
        }
    }

    /*
Rotate binary tree node with left child.
For AVL trees, this is a single rotation.
Update heights, then return new root.
     */
    private AVLNode rotateLeft(AVLNode node2) {
        AVLNode node1 = node2.getLeft();
        node2.setLeft(node1.getRight());
        node1.setRight(node2);
        node2.setHeight(Math.max(height(node2.getLeft()),
                height(node2.getRight())) + 1);
        node1.setHeight(Math.max(height(node1.getLeft()), node2.getHeight()) + 1);
        return node1;
    }

    /*
Rotate binary tree node with right child.
For AVL trees, this is a single rotation.
Update heights, then return new root.
     */
    private AVLNode rotateRight(AVLNode node1) {
        AVLNode node2 = node1.getRight();
        node1.setRight(node2.getLeft());
        node2.setLeft(node1);
        node1.setHeight(Math.max(height(node1.getLeft()),
                height(node1.getRight())) + 1);
        node2.setHeight(Math.max(height(node2.getRight()), node1.getHeight()) + 1);
        return node2;
    }

    /*
Double rotate binary tree node: first left child with its right
child;
then node node3 with new left child.
For AVL trees, this is a double rotation.
Update heights, then return new root.
     */
    private AVLNode doubleLeft(AVLNode node3) {
        node3.setLeft(rotateRight(node3.getLeft()));
        return rotateLeft(node3);
    }

    /*
Double rotate binary tree node: first right child with its left
child;
then node node1 with new right child.
For AVL trees, this is a double rotation.
Update heights, then return new root.
     */
    private AVLNode doubleRight(AVLNode node1) {
        node1.setRight(rotateLeft(node1.getRight()));
        return rotateRight(node1);
    }

    public AVLNode getRoot() {
        return root;
    }

    public void setRoot(AVLNode root) {
        this.root = root;
    }

    public AVLNode findMin(AVLNode curr) {
        if (curr.getLeft() == null) {
            return curr;
        } else {
            return findMin(curr.getLeft());
        }
    }

    public AVLNode balance(AVLNode root) {
        if (root == null) {
            return root;
        }

        if (height(root.getLeft()) - height(root.getRight()) > 1) {
            if (height(root.getLeft().getLeft()) >= height(root.getLeft().getRight())) {
                root = rotateRight(root);
            } else {
                root = doubleRight(root);
            }
        } else if (height(root.getRight()) - height(root.getLeft()) > 1) {
            if (height(root.getRight().getRight()) >= height(root.getRight().getLeft())) {
                root = rotateLeft(root);
            } else {
                root = doubleLeft(root);
            }
        }

        return root;

    }

    public AVLNode findMax(AVLNode curr) {
        if (curr.getRight() == null) {
            return curr;
        } else {
            return findMin(curr.getRight());
        }
    }

    public void inorder(AVLNode r) {

        if (r != null) {
            inorder(r.left);
            System.out.print(r.word + " ");
            inorder(r.right);
        }
        
        

    }
    
      public void printTreeByCh(char x, AVLNode t) // inorder traversal
    {
        if (t != null && (t.word.getW().charAt(0)== x)) {
            printTreeByCh(x,t.getLeft());
            System.out.println(t.getWord());
            printTreeByCh(x,t.getRight());
        }
    }
    
    

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        stringify(root, s);
        return s.toString();
    }
}
