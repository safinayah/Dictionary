/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dict;

import dict.Word;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author ayah
 */
public class FileAccess {
     String line;
     Word[] arr;
     HashMap[] array;

    /**
     *
     * @param str
     * @throws java.io.FileNotFoundException
     */
     
     public void print(Object o,String file) throws FileNotFoundException, IOException
     {
        String str = o.toString();
        try{
            FileOutputStream outputStream = new FileOutputStream(file);
            try{
                 byte[] strToBytes = str.getBytes();
                 outputStream.write(strToBytes);
            }catch(IOException d){
                System.out.println("Error While Printing File");
            }
        
        }catch(FileNotFoundException e){
             
             System.out.println("File not found");
         }
       
       
     }
     
     public void print(Object o) throws IOException{
         
         print(o,"output.txt");
     }
     
    public Word[] pass(){
        return pass("data.txt");
    }
       public Word[] passH(){
        return pass("output.txt");
    }
   
     
     
    public Word[]  pass(String name) {
  
        int n = numOfLines();
        arr = new Word[numOfLines()];
  

        File x = new File(name);
        if (x.exists()) {//checks of the file exist

            Scanner input = null;//initilalize input scanner
            try {
                input = new Scanner(x); // Scanning

            } catch (FileNotFoundException ex) {
                System.out.println("noooooo data!!!!!!!!!!!");
            }
            int j =0;
            while (input.hasNext()) {//checks if the file has nextLine
                arr[j] = new Word();
               line = input.nextLine(); // This is used to get to the next line and trim thm from the sides
                ArrayList<String> syn = new ArrayList<>();

                String[] word = line.split(":");
                String[] term = word[1].split("/");
                String[] syn1 = term[0].split(",");
                String[] s = term[1].split("\\*"); 
               // System.out.println(s[1]);
                for (int i = 0; i < syn1.length; i++) {
                    syn.add(syn1[i]);
                    
                }
                arr[j].setW(word[0]);
                arr[j].setSyn(syn);
                syn.add(s[0]);
                arr[j].setOps(s[1]);
                
                
           // arr[i]=w;
            
               j++;
            }

        }
        
        return  arr;

    }
    
    
     public int numOfLines() {//calculates num of file's line
        int num = 0;//initialize num

        File x = new File("data.txt"); //This is used to input the movies text

        if (x.exists()) {//checks of the file exist

            Scanner input = null;//initilalize input scanner
            try {
                input = new Scanner(x);
            } catch (FileNotFoundException ex) {
                System.out.println("File not found");
            }

            while (input.hasNextLine()) {//checks if the file has nextLine
                input.nextLine(); // This is used to get to the next line
                num++;
              
            }//end of while loop
            input.close();
        }//end of class

        return num;//returns number of file's lines
}//end of method
}