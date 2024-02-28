

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;


public class Genetic_Alg_System {
    static int max_gen ;
    static int pop_size ;
    static int elitism_count ; // The number of elite individuals to carry over to the next generation
    static int tournament_size ;

    static double crossoverRate ;

    static double mutationRate;


    public  void UserInputGeneticAlgorithm( String FilePath, long seed  ) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter crossover rate (e.g., 0.5): ");
        crossoverRate = scanner.nextDouble();

        System.out.println("Enter mutation rate (e.g., 0.1): ");
        mutationRate = scanner.nextDouble();

        System.out.println("Enter population size (e.g., 100): ");
        pop_size = scanner.nextInt();

        System.out.println("Enter maximum generations (e.g., 50): ");
        max_gen = scanner.nextInt();

        System.out.println("Enter elitism count (e.g., 2): ");
        elitism_count = scanner.nextInt();

        System.out.println("Enter tournament size (e.g., 3): ");
        tournament_size = scanner.nextInt();


        ReadData read_doc = new ReadData();
        String Data1 = read_doc.read(FilePath);
        double len1 = read_doc.extractChromosomelength(Data1); //extract chromosome length from Datafile
        // Read problem instance data
        Random r = new Random(seed);
        Pop_Initializer init = new Pop_Initializer(r); //population initializer
        init.setPopSize(pop_size); //set pop_size
        init.setIndividual_size(len1);//set individual size
        ArrayList<char[]> POP = init.genPopulation(); //create initial  population


        Select select = new Select(r);
        CrossMut crossMut = new CrossMut(r);
        //Classes that will be used in GA
        char [] overallBestIndividual = null; //best chromosome in all generations
        double bestFitness = Double.POSITIVE_INFINITY;// best fitness in all generations

        for (int i=0; i<max_gen; i++){
           ArrayList<char []> elite =  select.Elitism(elitism_count,POP,Data1);  //Contains set of best chromosomes
           ArrayList<char[]> New_population = new ArrayList<>(elite); //adds elite chromosomes to new population

            double totalFitness = 0.0;
            double currentBestFitness = Double.POSITIVE_INFINITY;
            char[] currentBestIndividual = null;

            //Loop through each individual in POP to find the chromosome with the best fitness and the total fitness
            for(char [] individuals:POP){
                double fitness = Evaluation.fitness(new String(individuals),Data1);
                totalFitness+= fitness;
                if(fitness<currentBestFitness){
                    currentBestFitness = fitness;
                    currentBestIndividual = individuals.clone();
                }
            }
            //updates the best fitness and the best individual if something better is found
            if(currentBestFitness<bestFitness){
                bestFitness = currentBestFitness;
                overallBestIndividual = currentBestIndividual.clone();
            }
            //calculates the average fitness
            double averageFitness = totalFitness/ POP.size();
            //Prints the current generation, the best fitness in said gen and the average fitness
            System.out.println("Generation " + i + ": Best Fitness = " + currentBestFitness + ", Average Fitness = " + averageFitness);

          //Ensures New_population is full before staring for loop again
          while(New_population.size()< POP.size()){
              //Parents are gotten using tournament select.
              char [] parent1 = select.Tournament_Select(tournament_size,POP,Data1);
              char [] parent2 = select.Tournament_Select(tournament_size,POP,Data1);
              while(Arrays.equals(parent1,parent2)){//Ensure parent1 and parent2 are distinct
                  parent2 = select.Tournament_Select(tournament_size,POP,Data1);
              }
              ArrayList<char []> children = new ArrayList<>(); //holds the offspring form parent 1 and parent2
              // Perform crossover with a probability equal to crossoverRate
              if(r.nextDouble()<crossoverRate){
                  // Randomly choose which type of crossover to perform
                  if(r.nextBoolean()){
                      // Perform one-point crossover
                      children = crossMut.OnePointCrossover(parent1,parent2);
                  }else{
                      // Perform uniform crossover
                      children = crossMut.UniformCrossOver(parent1,parent2);
                  }
              }else{
                  // If no crossover was performed, just copy the parents as new children
                  children.add(parent1.clone());
                  children.add(parent2.clone());
              }
              //Iterate over each child to check for mutation
              for(char [] child: children){
                  if(r.nextDouble()<mutationRate){
                      child = crossMut.reciprocalExchangeMutation(child);
                     // System.out.println("Child after mutation: " + Arrays.toString(child));
                  }
                  // Add the child to the new population, ensuring it doesn't exceed the population size
                  if(New_population.size()< POP.size()){
                      New_population.add(child);
                  }
              }
          }
          //Make POP the new population and repeat entire process
          POP = New_population;
       }
        scanner.close();
        if (overallBestIndividual != null) {
            String bestChromosome = new String(overallBestIndividual);
            System.out.println("Best Chromosome: " + bestChromosome);
            String decryptedText = Evaluation.decrypt(bestChromosome,Data1);
            System.out.println("Decrypted Text Using Best Chromosome: " + decryptedText);
        }
    }

        public  void ExperimentGeneticAlgorithm(long seed, String FilePath, double assigned_crossoverRate, double assigned_mutationRate, CrossoverType crossovertype ) {

        max_gen = 10;
        elitism_count = 5;
        tournament_size =3;
        pop_size = 15;


        ReadData read_doc = new ReadData();
        String Data1 = read_doc.read(FilePath);
        double len1 = read_doc.extractChromosomelength(Data1); //extract chromosome length from Datafile
        // Read problem instance data
        Random r = new Random(seed);
        Pop_Initializer init = new Pop_Initializer(r); //population initializer
        init.setPopSize(pop_size); //set pop_size
        init.setIndividual_size(len1);//set individual size
        ArrayList<char[]> POP = init.genPopulation(); //create initial  population
        Select select = new Select(r);
        CrossMut crossMut = new CrossMut(r);
        //Classes that will be used in GA
        char [] overallBestIndividual = null; //best chromosome in all generations
        double bestFitness = Double.POSITIVE_INFINITY;// best fitness in all generations

        for (int i=0; i<max_gen; i++){
            ArrayList<char []> elite =  select.Elitism(elitism_count,POP,Data1);  //Contains set of best chromosomes
            ArrayList<char[]> New_population = new ArrayList<>(elite); //adds elite chromosomes to new population

            double totalFitness = 0.0;
            double currentBestFitness = Double.POSITIVE_INFINITY;
            char[] currentBestIndividual = null;

            //Loop through each individual in POP to find the chromosome with the best fitness and the total fitness
            for(char [] individuals:POP){
                double fitness = Evaluation.fitness(new String(individuals),Data1);
                totalFitness+= fitness;
                if(fitness<currentBestFitness){
                    currentBestFitness = fitness;
                    currentBestIndividual = individuals.clone();
                }
            }
            //updates the best fitness and
            // the best individual if something better is found
            if(currentBestFitness<bestFitness){
                bestFitness = currentBestFitness;
                overallBestIndividual = currentBestIndividual.clone();
            }
            //calculates the average fitness
            double averageFitness = totalFitness/ POP.size();
            //Prints the current generation, the best fitness in said gen and the average fitness
            System.out.println("Generation " + i + ": Best Fitness = " + currentBestFitness + ", Average Fitness = " + averageFitness);

            //Ensures New_population is full before staring for loop again
            while(New_population.size()< POP.size()){
                //Parents are gotten using tournament select.
                char [] parent1 = select.Tournament_Select(tournament_size,POP,Data1);
                char [] parent2 = select.Tournament_Select(tournament_size,POP,Data1);
                while(Arrays.equals(parent1,parent2)){//Ensure parent1 and parent2 are distinct
                    parent2 = select.Tournament_Select(tournament_size,POP,Data1);
                }
                ArrayList<char []> children = new ArrayList<>(); //holds the offspring form parent 1 and parent2
                // Perform crossover with a probability equal to crossoverRate
                if(r.nextDouble()<assigned_crossoverRate){
                    // Randomly choose which type of crossover to perform
                    if(crossovertype == CrossoverType.ONE_POINT ){
                        // Perform one-point crossover
                        children = crossMut.OnePointCrossover(parent1,parent2);
                    }else if(crossovertype == CrossoverType.UNIFORM){
                        // Perform uniform crossover
                        children = crossMut.UniformCrossOver(parent1,parent2);
                    }
                }else{
                    // If no crossover was performed, just copy the parents as new children
                    children.add(parent1.clone());
                    children.add(parent2.clone());
                }
                //Iterate over each child to check for mutation
                for(char [] child: children){
                    if(r.nextDouble()<assigned_mutationRate){
                        child = crossMut.reciprocalExchangeMutation(child);
                        // System.out.println("Child after mutation: " + Arrays.toString(child));
                    }
                    // Add the child to the new population, ensuring it doesn't exceed the population size
                    if(New_population.size()< POP.size()){
                        New_population.add(child);
                    }
                }
            }
            //Make POP the new population and repeat entire process
            POP = New_population;
        }

        if (overallBestIndividual != null) {
            String bestChromosome = new String(overallBestIndividual);
            System.out.println("Best Chromosome: " + bestChromosome);
            String decryptedText = Evaluation.decrypt(bestChromosome,Data1);
           // System.out.println("Decrypted Text Using Best Chromosome: " + decryptedText);
        }
    }
}














