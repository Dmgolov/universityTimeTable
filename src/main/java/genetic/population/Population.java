package genetic.population;

import genetic.storage.SubjectStorage;
import genetic.timetable.Timetable;

public class Population {

    private int populationSize;
    private Timetable[] timetables;
    private int fittestValue = 0;
    private SubjectStorage storage;

    public Population(int populationSize, SubjectStorage storage) {
        this.populationSize = populationSize;
        this.timetables = new Timetable[populationSize];
        this.storage = storage;
    }

    public int getSize() {
        return populationSize;
    }

    public Timetable[] getTimetables() {
        return timetables;
    }

    public void setTimetables(Timetable[] timetables) {
        this.timetables = timetables;
    }

    public int getFittestValue() {
        return fittestValue;
    }

    public SubjectStorage getStorage() {
        return storage;
    }

    public void initializePopulation() {
        for (int i = 0; i < timetables.length; i++) {
            timetables[i] = new Timetable();
            timetables[i].initializeRandomTimetable(storage);
        }
    }

    public Timetable getFittest() {
        int maxFittestValue = Integer.MIN_VALUE;
        int maxFittestIndex = 0;
        for (int i = 0; i < timetables.length; i++) {
            if (maxFittestValue <= timetables[i].getFitness()) {
                maxFittestValue = timetables[i].getFitness();
                maxFittestIndex = i;
            }
        }
        fittestValue = timetables[maxFittestIndex].getFitness();
        return timetables[maxFittestIndex];
    }

    public Timetable getSecondFittest() {
        int maxFittestFirst = 0;
        int maxFittestSecond = 0;
        for (int i = 0; i < timetables.length; i++) {
            if (timetables[i].getFitness() > timetables[maxFittestFirst].getFitness()) {
                maxFittestSecond = maxFittestFirst;
                maxFittestFirst = i;
            } else if (timetables[i].getFitness() > timetables[maxFittestSecond].getFitness()) {
                maxFittestSecond = i;
            }
        }
        return timetables[maxFittestSecond];
    }

    public int getLeastFittestIndex() {
        int minFitnessValue = Integer.MAX_VALUE;
        int minFitnessIndex = 0;
        for (int i = 0; i < timetables.length; i++) {
            if (minFitnessValue >= timetables[i].getFitness()) {
                minFitnessValue = timetables[i].getFitness();
                minFitnessIndex = i;
            }
        }
        return minFitnessIndex;
    }

    public void calculateFitness() {
        for (int i = 0; i < timetables.length; i++) {
            timetables[i].calculateFitness();
        }
        getFittest();
    }
}
