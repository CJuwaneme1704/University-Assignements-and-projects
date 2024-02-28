import java.util.ArrayList;
import java.util.Random;

public class CrossMut {
    Random random;


    public CrossMut(Random random) {
        this.random = random;
    }
    public char [] reciprocalExchangeMutation( char [] chromosome){

        int index1 = this.random.nextInt(chromosome.length);
        int index2 = this.random.nextInt(chromosome.length);

        if (index1 == index2) {
            index2 = this.random.nextInt(chromosome.length); // Ensure they are distinct
        }

        // Swap the elements at these indices
        char temp = chromosome[index1];
        chromosome[index1] = chromosome[index2];
        chromosome[index2] = temp;

        return chromosome;
    }

    public ArrayList<char []> OnePointCrossover(char[] chromosome1, char [] chromosome2 ){

        int point = this.random.nextInt(chromosome1.length);

        char [] child1 = new char[chromosome1.length];
        char [] child2 = new char [chromosome1.length];

        for(int i=0; i<point;i++){
            child1[i] = chromosome1[i];
            child2[i] = chromosome2[i];
        }
        // After the crossover point, swap the genes from the second parent into the first child and vice versa
        for(int i = point; i< child1.length;i++){
            child1[i] = chromosome2[i];
            child2[i] = chromosome1[i];
        }
        // Create an ArrayList to hold both children
        ArrayList<char []> children = new ArrayList<>();
        children.add(child1);
        children.add(child2);
        // Return the ArrayList containing the new child chromosomes
        return children ;


    }

    public ArrayList<char[]> UniformCrossOver(char[] parent1, char [] parent2){
        char [] child1 = new char[parent1.length];
        char [] child2 = new char [parent1.length];
        char [] mask = generateMask(parent1.length);
        ArrayList<char []> children = new ArrayList<>();

         for(int i=0; i<parent1.length;i++){
             if(mask[i] =='1' ){
                 child1[i] = parent1[i];
                 child2[i] = parent2[i];
             }else if(mask[i] == '0'){
                child1[i] = parent2[i];
                child2[i] = parent1[i];
             }
         }
         children.add(child1);
         children.add(child2);

         return children;

    }

    public char [] generateMask(int len){
        char []  mask =  new  char[len];
        for(int i=0; i<len; i++){
            mask[i] = this.random.nextBoolean() ? '1':'0';
        }
        return mask;
    }

}
