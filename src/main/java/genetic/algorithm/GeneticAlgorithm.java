package genetic.algorithm;

import genetic.population.Population;
import genetic.storage.SubjectStorage;
import genetic.subjects.Subject;
import genetic.timetable.Timetable;

import java.util.List;
import java.util.Random;

/**
 * Used article https://towardsdatascience.com/introduction-to-genetic-algorithms-including-example-code-e396e98d8bf3
 */
public class GeneticAlgorithm {

    private Population population;
    private Timetable fittest;
    private Timetable secondFittest;
    private int generationCount = 0;
    private int generationsLimit;
    private long previousFittestValue = 0;
    private int stepsAfterFittestValueChange = 0;
    private int fromFittestValueChangeMaxSteps;

    public GeneticAlgorithm(int populationSize, int generationsLimit, int fromFittestValueChangeMaxSteps, SubjectStorage storage) {
        this.population = new Population(populationSize, storage);
        this.generationsLimit = generationsLimit;
        this.fromFittestValueChangeMaxSteps = fromFittestValueChangeMaxSteps;
    }

    private void selection() {

        //Select the most fittest individual
        fittest = population.getFittest();

        //Select the second most fittest individual
        secondFittest = population.getSecondFittest();
    }

    //Crossover
    private void crossover() {
        Random random = new Random(System.currentTimeMillis());

        //Select a random crossover point
        // Here Timetable is in role of Individual and it's genes are Subject objects,
        // so chromosome is a set of subjects
        int crossOverPoint = random.nextInt(population.getStorage().getSize());

        //Swap values among parents
        // We take subjects from first parent and search them in second parent and swap them
        // so crossover point in this case is used only for first parent
        // (subject names are same to avoid same subject different variants in one timetable)
        for (int i = 0; i < crossOverPoint; i++) {
            Subject subjectFromFirst = fittest.getSubjectByIndex(i);
            Subject subjectFromSecond = secondFittest.replaceSubjectWithGiven(subjectFromFirst);
            fittest.replaceSubjectWithGiven(subjectFromSecond);
        }
    }

    //Mutation
    private void mutation() {
        Random random = new Random(System.currentTimeMillis());

        // --First fittest mutation
        //Select a random mutation point
        int mutationPoint = random.nextInt(population.getStorage().getSize());

        //Flip values at the mutation point
        // Get name of subject to mutate and get all variants of this subject from SubjectStorage then
        // get random variant and replace current subject with chosen variant
        // (subject names are same to avoid same subject different variants in one timetable)
        Subject currentSubject = fittest.getSubjectByIndex(mutationPoint);
        List<Subject> subjectVariants = population.getStorage().getSubjectPossibleVariants(currentSubject.getName());
        Subject mutatedSubject = subjectVariants.get(random.nextInt(subjectVariants.size()));
        fittest.replaceSubjectWithGiven(mutatedSubject);

        // --Second fittest mutation
        mutationPoint = random.nextInt(population.getStorage().getSize());

        currentSubject = secondFittest.getSubjectByIndex(mutationPoint);
        subjectVariants = population.getStorage().getSubjectPossibleVariants(currentSubject.getName());
        mutatedSubject = subjectVariants.get(random.nextInt(subjectVariants.size()));
        secondFittest.replaceSubjectWithGiven(mutatedSubject);
    }

    //Get fittest offspring
    private Timetable getFittestOffspring() {
        if (fittest.getFitness() > secondFittest.getFitness()) {
            return fittest;
        }
        return secondFittest;
    }


    //Replace least fittest individual with most fittest offspring
    private void addFittestOffspring() {

        //Update fitness values of offspring
        fittest.calculateFitness();
        secondFittest.calculateFitness();

        //Get index of least fit individual
        int leastFittestIndex = population.getLeastFittestIndex();

        //Replace least fittest individual from most fittest offspring
        population.getTimetables()[leastFittestIndex] = getFittestOffspring();
    }

    public Timetable findFittestTimetable() {
        Random rn = new Random(System.currentTimeMillis());

        //Initialize population
        population.initializePopulation();

        //Calculate fitness of each individual
        population.calculateFitness();

        System.out.println("Generation: " + generationCount + " Fittest: " + population.getFittestValue());

        Timetable maxFitnessTimetable = new Timetable();
        long maxFitnessValue = 0;

        long maxFitness = population.getTimetables()[0].getMaxFitness();

        previousFittestValue = population.getFittest().getFitness();

        //While population does not get an individual with maximum fitness
        // and generation counter is smaller than generations number limit
        while (population.getFittestValue() < maxFitness && (generationCount < generationsLimit && stepsAfterFittestValueChange < fromFittestValueChangeMaxSteps)) {
            ++generationCount;

            //Do selection
            selection();

            //Do crossover
            crossover();

            //Do mutation under a random probability
            if (rn.nextInt()%7 < 5) {
                mutation();
            }

            //Add fittest offspring to population
            addFittestOffspring();

            //Calculate new fitness value
            population.calculateFitness();

            if (maxFitnessValue <= population.getFittest().getFitness()) {
                maxFitnessValue = population.getFittest().getFitness();
                // System.out.println(maxFitnessValue + "----------------------");
                maxFitnessTimetable = population.getFittest().getDeepCopy();
            }

            stepsAfterFittestValueChange++;

            if (previousFittestValue != population.getFittest().getFitness()) {
                previousFittestValue = population.getFittest().getFitness();
                stepsAfterFittestValueChange = 0;
            }

            System.out.println("Generation: " + generationCount + " Fittest: " + population.getFittestValue());
        }

        System.out.println("\nSolution found in generation " + generationCount);
        System.out.println("Fitness: " + population.getFittest().getFitness() + "   Max Fitness: " + population.getTimetables()[0].getMaxFitness());
        System.out.println("Genes: ");
        population.getFittest().printTimetable();

        System.out.println("");

        System.out.println("\nMaximal fitness timetable:");
        System.out.println("Fitness: " + maxFitnessTimetable.getFitness() + "   Max Fitness: " + population.getTimetables()[0].getMaxFitness());
        System.out.println("Genes: ");
        maxFitnessTimetable.printTimetable();

        return maxFitnessTimetable;
    }

}
