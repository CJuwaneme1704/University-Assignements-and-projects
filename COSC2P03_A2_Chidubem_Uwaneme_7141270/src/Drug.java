
/**
 * Name Jerome Uwaneme
 * Course COSC2P03
 * Assignment 2
 * Student Number 7141270
 *
 * This class defines the drug type as well as its attributes
 *
 *
 *
 */
public class Drug {
    String genericName;
    String SMILES;
    String drugBankID;
    String url;
    String drugGroups;
    String score;

    public void displayDrug(){
        System.out.println(genericName + "\t" + SMILES + "\t" +  drugBankID + "\t" + url + "\t" + drugGroups + "\t" + score);
    }
}
