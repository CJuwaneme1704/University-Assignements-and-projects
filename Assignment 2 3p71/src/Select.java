import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;

public class Select {
    Random random;


    public Select(Random random) {
        this.random = random;
    }
    //  Elitism
    public ArrayList<char[]>  Elitism (int k, ArrayList<char[]> initial_POP, String text ) {
        ArrayList<char[]> survivors = new ArrayList<char[]>(k);
        ArrayList<char[]> copy = new ArrayList<>(initial_POP.size());
        for (char[] chromosome : initial_POP) {
            char[] chromosomeCopy = Arrays.copyOf(chromosome, chromosome.length);
            copy.add(chromosomeCopy);
        }
        while(survivors.size()<k){
            double fittestScore = Double.POSITIVE_INFINITY;
            char [] fittestChromosome = null;
            Iterator<char []> iterator = copy.iterator();
            while(iterator.hasNext()){
                char [] chromosome = iterator.next();
                double eval_Score = Evaluation.fitness( new String(chromosome),text);
                if(eval_Score<fittestScore){
                    fittestScore = eval_Score;
                    fittestChromosome = chromosome;
                }
            }
            if(fittestChromosome!=null){
                survivors.add(fittestChromosome);
                copy.remove(fittestChromosome);
            }else{
                break;
            }
        }
        return survivors;
    }

    public char [] Tournament_Select(int k, ArrayList<char[]> POP, String text) {
        double BestFitness = Double.POSITIVE_INFINITY;
        char[] fittestChromosome = null;
        for (int i = 0; i < k; i++) {
            int index = this.random.nextInt(POP.size());
            char[] contestant = POP.get(index);
            double fitness = Evaluation.fitness(new String(contestant), text);
            if (fitness < BestFitness) {
                BestFitness = fitness;
                fittestChromosome = contestant;
            }
        }
        return fittestChromosome;
    }
}
