/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dict;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author ayah
 */
public class FXMLDocumentController implements Initializable {

    Word c = new Word();
    AVLTree tree = new AVLTree();
    HashMap h = new HashMap(isPrime());
    HashEntry he = new HashEntry();
    @FXML
    private Button Insert;
    @FXML
    private Button deleteAVLNode;
    @FXML
    private Button printTree;
    @FXML
    private TextField AVLInsertLbl;
    @FXML
    private TextField DelWordLbl;
    @FXML
    private Label AVLTree;
    @FXML
    private Button HSize;
    @FXML
    private Button HInsert;
    @FXML
    private Button SearchHH;
    @FXML
    private Button HDel;
    @FXML
    private Button HPrint;
    @FXML
    private Label size;
    @FXML
    private Label DisplayHash;
    @FXML
    private TextField HashInsert;
    @FXML
    private TextField Hashser;
    @FXML
    private TextField HashDel;
    private TextField AvlDelChLbl;
    @FXML
    private Button DelByCha;
    @FXML
    private TextField dletteeeeeeee;
    @FXML
    private Button Displaaaaaaay;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //pane2.setVisible(false);
    }

    @FXML
    private void InsertToAVL(ActionEvent event) {
        FileAccess F = new FileAccess();
        Word[] w = F.pass();

        int i;
        for (i = 0; i < w.length; i++) {
            // System.out.println(w[i].toString());

            tree.insert(w[i]);

        }

        ArrayList<String> syn = new ArrayList<>();
        String line = AVLInsertLbl.getText();

        String[] word = line.split(":");
        String[] term = word[1].split("/");
        String[] syn1 = term[0].split(",");
        String[] s = term[1].split("\\*");
        // System.out.println(s[1]);
        for (i = 0; i < syn1.length; i++) {
            syn.add(syn1[i]);

        }
        c.setW(word[0]);
        c.setSyn(syn);
        syn.add(s[0]);
        c.setOps(s[1]);

        if (tree.search(c.getW(), tree.getRoot()) == null) {
            tree.setRoot(tree.insert(c, tree.getRoot()));
            // System.out.println(tree.toString());
        } else {
            System.out.println("Word alredy exists");
        }
        //h.printHashTable();
        tree.printTree();

    }

    @FXML
    private void AVLNodeDel(ActionEvent event) {
        String text2 = DelWordLbl.getText();
        AVLNode search = tree.search(text2, tree.getRoot());
        if (search != null) {
            tree.delete(search.word.getW(), tree.getRoot());
            DelWordLbl.appendText("Word has been deleted \n \n ");

            tree.printTree();
        } else {
            DelWordLbl.appendText("Word is not found ");

        }
    }

    @FXML
    private void PrtintTree(ActionEvent event) throws IOException {
        FileAccess F = new FileAccess();
        F.print(tree);
        AVLTree.setText(tree.toString());

    }

    @FXML
    private void HSize(ActionEvent event) {
        HSize.setText("" + isPrime());

    }

    @FXML
    private void InsertH(ActionEvent event) {
        FileAccess F = new FileAccess();
        Word[] w = F.pass();
        int i;
        for (i = 0; i < w.length; i++) {

            h.insert(w[i]);
        }
        ArrayList<String> syn = new ArrayList<>();
        String line = HashInsert.getText();

        String[] word = line.split(":");
        String[] term = word[1].split("/");
        String[] syn1 = term[0].split(",");
        String[] s = term[1].split("\\*");
        // System.out.println(s[1]);
        for (i = 0; i < syn1.length; i++) {
            syn.add(syn1[i]);

        }
        c.setW(word[0]);
        c.setSyn(syn);
        syn.add(s[0]);
        c.setOps(s[1]);
        h.insert(c);
        h.printHashTable();

    }

    @FXML
    private void HSearch(ActionEvent event) {
        String key = Hashser.getText();
        if (h.Search(key) == true) {
            Hashser.setText("Found");

        } else {
            Hashser.setText("Not Found");
        }

    }

    @FXML
    private void HashDel(ActionEvent event) {
        h.delete(HashDel.getText());
    }

    @FXML
    private void PrintHash(ActionEvent event) {
        h.printHashTable();
        DisplayHash.setText("look down :D :D ");

    }

    public static int isPrime() {
        FileAccess F = new FileAccess();
        int size = F.numOfLines() * 2;
        int i = 0;
        while (size % 2 == 0) {
            size++;

        }

        return size;

    }

    @FXML
    private void DeleteByCh(ActionEvent event) {
        char x = dletteeeeeeee.getText().charAt(0);

        tree.deleteByChar(x, tree.getRoot());

        tree.printTree();

    }

}
