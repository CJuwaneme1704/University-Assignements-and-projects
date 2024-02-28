import java.util.*;


public class Pop_Initializer {

    double popSize ;

    double individual_size;



    Random random;

    public Pop_Initializer(Random random) {
        this.random = random;
    }


    public void setPopSize(double d ){
        popSize = d;

    }
    public double getPopSize(){
        return popSize;
    }

    public void  setIndividual_size(double d){
        individual_size = d;
    }


    public double getIndividual_Size(){
        return individual_size;
    }
    public char[] individualGenerator(double len ){
        String  pool = "abcdefghijklmnopqrstuvwxyz";
        char[] result = new char[(int)len];
        for (int i=0;i<len;i++){
            result[i] = pool.charAt(this.random.nextInt((pool.length())));
        }
        return result;
    }

    public ArrayList<char[]> genPopulation(){
        ArrayList<char[]> list = new ArrayList<>();
        for(int i=0;i<popSize;i++){
            char [] individual = individualGenerator(this.individual_size);
            list.add(individual);
        }
        return list;
    }


}
