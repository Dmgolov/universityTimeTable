package genetic.population;

import genetic.storage.SubjectStorage;
import genetic.timetable.Timetable;

public class Population {

    private int populationSize = 10;
    private Timetable[] population = new Timetable[populationSize];
    private int fittest = 0;
    private SubjectStorage storage;

    public Population(SubjectStorage storage) {
        this.storage = storage;
    }

    public void initializePopulation(int size) {
        for (int i = 0; i < population.length; i++) {
            population[i] = new Timetable();
        }
    }


}
