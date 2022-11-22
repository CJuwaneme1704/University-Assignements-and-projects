/**
 * Name Jerome Uwaneme
 * Course COSC-2P03
 * Assignment 2
 * Student Number 7141270
 *
 * This is a binary search tree class
 *
 *
 *
 */

import java.io.*;
import java.util.*;

public class DrugBank {
    ArrayList<Drug> data = new ArrayList<Drug>();
    BinaryNode root;//root of the binary tree
    File F = new File("dockedApprovedSorted.tab");// output file for inOrder
    // traverse
    PrintStream stream;

    {
        try {
            stream = new PrintStream(F);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
 * readData reads the given file and loads all the information into an
 * arraylist called data
     *
     * readData requires the path of the text file  to work
 */

    public void readData() {
        String s;
        File current = new File("C:\\Users\\uwane\\IdeaProjects\\COSC2P03_A2_Chidubem_Uwaneme_7141270\\src\\dockedApproved.tab");
        try {
            int n = 23;
            BufferedReader br = new BufferedReader(new FileReader(current));
            s = br.readLine();
            while (s != null) {
                if (n != 23) {
                    String[] arr;
                    arr = s.split("\\t", 6);
                    Drug d = new Drug();
                    d.genericName = arr[0];
                    d.SMILES = arr[1];
                    d.drugBankID = arr[2];
                    d.url = arr[3];
                    d.drugGroups = arr[4];
                    d.score = arr[5];
                    this.data.add(d);
                } else {
                    n = 0;
                }
                s = br.readLine();
            }
            br.close();
        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("cant find file");

        }
    }

/**
 * insert adds a drug  into a binary search tree using the drugbankID
 * as key. It parses the numeric part  of the drugbankID
 * (WHICH IS A STRING)  to an int and compares it with the root's drug's
 * drugbankID to determine where it goes.
 * greater being right and lesser being left
 */

    public BinaryNode insert(BinaryNode T, Drug d) {
        if (T == null) {
            T = new BinaryNode(d, null, null);
            return T;
        } else if (Integer.parseInt(d.drugBankID.substring(2, 6)) < Integer.parseInt(T.drug.drugBankID.substring(2, 6))) {
            T.left = insert(T.left, d);
        } else if (Integer.parseInt(d.drugBankID.substring(2, 6)) > Integer.parseInt(T.drug.drugBankID.substring(2, 6))) {
            T.right = insert(T.right, d);
        }
        return T;
    }

/**
 * create inserts all the drugs in data(arraylsit of drugs) into a binary
 * search tree
 */

    public void create() {
        for (Drug x : data) {
            root = insert(root, x);
        }
    }

/**
 * traverses the BST in inOrder traversal and prints the BST SORTED to a file
 */

    public void inOrderTraverse(BinaryNode T) {

        if (T == null) {
            return;
        }
        inOrderTraverse(T.left);
        System.setOut(stream);
        T.drug.displayDrug();
        inOrderTraverse(T.right);
    }


    /**
     * search traverses the binary search tree looking for a specific key.
     * When found it prints the generic name of the drug with the given
     * drugbanKID
     */


    public void search(String s) {
        String n = s.substring(2, 6);
        BinaryNode pointer = root;
        while (Integer.parseInt(pointer.drug.drugBankID.substring(2, 6)) != Integer.parseInt(n)) {
            if (Integer.parseInt(n) < Integer.parseInt(pointer.drug.drugBankID.substring(2, 6))) {
                pointer = pointer.left;
            } else{
                pointer = pointer.right;
            }
            if(pointer == null){
                System.out.println("Does not exist");
                return;
            }
        }
        System.out.println(pointer.drug.genericName);
    }

/**
 * delete traverses the binary search tree and deletes the node that
 * contains a drug with the given drugbankID
 */

    public BinaryNode delete( BinaryNode  T, String s) {
        String n = s.substring(2, 6);

        if( T == null){
            return null;
        }
        if (Integer.parseInt(n) < Integer.parseInt(T.drug.drugBankID.substring(2, 6))) {
            T.left = delete(T.left,s);
        }else if (Integer.parseInt(n) > Integer.parseInt(T.drug.drugBankID.substring(2, 6))){
            T.right = delete(T.right,s);
        }

        else{
            if(T.left != null && T.right!= null){
                T.drug.drugBankID = findMin(T.right).drug.drugBankID;
                T.right = delete(T.right,T.drug.drugBankID);
            }
            else
                T = (T.left!=null) ? T.left : T.right;
        }
        return T;

    }
/**
 * finds the left most leaf node of a bst
 */

    public BinaryNode findMin(BinaryNode T){
        if(T!= null) {
            while(T.left!= null){
                T = T.left;
            }
        }
        return T;
    }
/**
 * depth1 finds the depth of a node with the given drugbankID
 */

    public void depth1(String s){

        String n = s.substring(2, 6);
        BinaryNode pointer = root;
        int d = 0;
        while (Integer.parseInt(pointer.drug.drugBankID.substring(2, 6)) != Integer.parseInt(n)) {

            if (Integer.parseInt(n) < Integer.parseInt(pointer.drug.drugBankID.substring(2, 6))) {
                pointer = pointer.left;
            } else{
                pointer = pointer.right;
            }
            d++;
            if(pointer == null){
                System.out.println("Does not exist");
            }
        }
        System.out.println(d);

    }
/**
 * depth2 finds the depth of the deepest node  in the BST
 */

    public int depth2(BinaryNode T){
        if(T==null){
            return -1;
        }
        if(T.left == null & T.right == null){
            return 0;
        }
        int d = Math.max(depth2(T.left), depth2(T.right));
        return d+1;
    }


}
