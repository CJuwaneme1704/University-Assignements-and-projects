
/**
 * Name Jerome Uwaneme
 * Course COSC2P03
 * Assignment 2
 * Student Number 7141270
 *
 * This class is used to define the BinaryNode type
 *
 *
 *
 */
public class BinaryNode {
    Drug drug;
    BinaryNode left;
    BinaryNode right;


    public BinaryNode( Drug d, BinaryNode l, BinaryNode r) {
        drug = d;
        left = l;
        right = r;
    }

    public  void displayNode(BinaryNode T){
       System.out.println(T.drug.genericName);
    }

}
