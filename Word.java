/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dict;

import java.util.ArrayList;

/**
 *
 * @author ayah
 */
public class Word {

    private String w;
    private ArrayList <String> syn= new ArrayList<>();
    private String ops;
    private String key;

    Word() {
    }

    Word(String word, ArrayList <String> syn, String ops) {
        this.w = word; 
        this.syn= syn; 
        this.ops= ops; 
        
    }

    Word(String key) {
        this.key=key;
        
         }

    public String getW() {
        return w;
    }

    public void setW(String w) {
        this.w = w;
    }
    
    public String getOps() {
        return ops;
    }

    public void setOps(String ops) {
        this.ops = ops;
    }
        public ArrayList <String> getSyn() {
        return syn;
    }

    public void setSyn(ArrayList <String> syn) {
        this.syn = syn;
    }


    @Override
    public String toString() {
        return "Word{" + "word=" + w + ", syn=" + syn + ", ops=" + ops + '}';
    }


 


}
