

public class Main {
    public static void main(String[] args) {
        Genetic_Alg_System gas = new Genetic_Alg_System();
       // gas.UserInputGeneticAlgorithm("src/Data1.txt", 4567L);//Uncomment to test implemented  User InputGA

       // Experiment
        long [] seeds = {3244L,2789L,9610L,8746L,3647L}; // Random seeds
        String [] paths = {"src/Data1.txt","src/Data2.txt"}; // file paths
        double [][] setting= {
                {1.0, 0.0}, // 100% crossover, 0% mutation
                {1.0, 0.1}, // 100% crossover, 10% mutation
                {0.9, 0.0}, // 90% crossover, 0% mutation
                {0.9, 0.1}, // 90% crossover, 10% mutation
                {0.7,0.3} // my preferred setting
        };
        //Iterate over each file in the array
        for (String path: paths) {
            System.out.println("Running experiment for file: " + path); //print path
            //for each seed in the seed array use each parameter setting  in the array
            for(long seed:seeds){
                for(double [] params: setting){
                    double crossRate = params[0];
                    double mutRate = params[1];

                    // Test both ONE_POINT and UNIFORM crossover methods
                    for (CrossoverType crossoverType : CrossoverType.values()){
                        System.out.printf("Seed: %d, Crossover Type: %s, Crossover Rate: %.1f, Mutation Rate: %.1f%n",
                                seed, crossoverType, crossRate, mutRate);
                        gas.ExperimentGeneticAlgorithm(seed,path,crossRate,mutRate,crossoverType);
                    }
                }
                System.out.println();
            }
            System.out.println("---------------------------------------------------------");
        }
   }
}
