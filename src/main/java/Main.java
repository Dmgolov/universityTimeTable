import genetic.storage.SubjectStorage;
import genetic.subjects.Subject;
import genetic.timetable.Timetable;

import java.time.LocalTime;

public class Main {
    public static void main(String[] args) {

        SubjectStorage storage = new SubjectStorage();
        storage.addSubject(new Subject("Mathematics", 0, LocalTime.of(10, 0), LocalTime.of(11, 30)));
        storage.addSubject(new Subject("Mathematics", 2, LocalTime.of(12, 0), LocalTime.of(13, 30)));
        storage.addSubject(new Subject("Mathematics", 2, LocalTime.of(17, 30), LocalTime.of(19, 0)));
        storage.addSubject(new Subject("Mathematics", 3, LocalTime.of(8, 0), LocalTime.of(9, 30)));
        storage.addSubject(new Subject("Java", 0, LocalTime.of(10, 0), LocalTime.of(11, 30)));
        storage.addSubject(new Subject("Java", 3, LocalTime.of(8, 0), LocalTime.of(9, 30)));
        storage.addSubject(new Subject("Enesejuhtimine", 3, LocalTime.of(18, 0), LocalTime.of(19, 30)));
        storage.addSubject(new Subject("Materjalid", 3, LocalTime.of(18, 0), LocalTime.of(19, 30)));
        storage.addSubject(new Subject("Veebiteenused", 3, LocalTime.of(18, 0), LocalTime.of(19, 30)));
        storage.addSubject(new Subject("Veebiteenused", 4, LocalTime.of(18, 0), LocalTime.of(19, 30)));
        storage.addSubject(new Subject("Python algkursus", 3, LocalTime.of(18, 0), LocalTime.of(19, 30)));
        storage.addSubject(new Subject("Python algkursus", 0, LocalTime.of(10, 0), LocalTime.of(11, 30)));

        Timetable timetable = new Timetable();
        timetable.initializeRandomTimetable(storage);
        timetable.printTimetable();
        timetable.calculateFitness();
        System.out.println(timetable.getFitness());
//        System.out.println((char)27 + "[31m" + "ERROR MESSAGE IN RED");
    }
}
