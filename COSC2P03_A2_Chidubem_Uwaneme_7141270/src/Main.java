import java.io.IOException;

/**
 * Name Jerome Uwaneme
 * Course COSC2P03
 * Assignment 2
 * Student Number 7141270
 *
 * This class is used to the test the methods of the Drug_bank Binary search
 * tree  class
 *
 *
 *
 */


public class Main {
    public static void main(String[] args)  {
        DrugBank db = new DrugBank();
        db.readData();
        db.create();
       db.depth1("DB01050");
       int n = db.depth2(db.root);
       System.out.println(n);
       db.search("DB01050");
       db.search("DB00316");
       db.delete(db.root,"DB01065");
        db.inOrderTraverse(db.root);
    }
}