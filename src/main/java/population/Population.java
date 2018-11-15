package population;

import timetable.Timetable;

public class Population {

    private int populationSize = 10;
    private Timetable[] timetables = new Timetable[10];
    private int fittest = 0;

    public void initializePopulation(int size) {
        for (int i = 0; i < timetables.length; i++) {
            timetables[i] = new Timetable();
        }
    }


}
