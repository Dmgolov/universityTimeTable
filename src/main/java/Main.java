import genetic.algorithm.GeneticAlgorithm;
import genetic.storage.SubjectStorage;
import genetic.subjects.Subject;
import genetic.timetable.Timetable;

import java.time.LocalTime;

public class Main {
    public static void main(String[] args) {

        SubjectStorage storage = new SubjectStorage();
//        storage.addSubject(new Subject("Mathematics", 0, LocalTime.of(10, 0), LocalTime.of(11, 30), LocalTime.of(12, 0)));
//        storage.addSubject(new Subject("Mathematics", 2, LocalTime.of(12, 0), LocalTime.of(13, 30), LocalTime.of(14, 0)));
//        storage.addSubject(new Subject("Mathematics", 2, LocalTime.of(17, 30), LocalTime.of(19, 0), LocalTime.of(19, 30)));
//        storage.addSubject(new Subject("Mathematics", 3, LocalTime.of(8, 0), LocalTime.of(9, 30), LocalTime.of(10, 0)));
//        storage.addSubject(new Subject("Java", 0, LocalTime.of(10, 0), LocalTime.of(11, 30), LocalTime.of(12, 0)));
//        storage.addSubject(new Subject("Java", 3, LocalTime.of(8, 0), LocalTime.of(9, 30), LocalTime.of(10, 0)));
//        storage.addSubject(new Subject("Enesejuhtimine", 3, LocalTime.of(18, 0), LocalTime.of(19, 30), LocalTime.of(20, 0)));
//        storage.addSubject(new Subject("Materjalid", 3, LocalTime.of(18, 0), LocalTime.of(19, 30), LocalTime.of(20, 0)));
//        storage.addSubject(new Subject("Veebiteenused", 3, LocalTime.of(18, 0), LocalTime.of(19, 30), LocalTime.of(20, 0)));
//        storage.addSubject(new Subject("Veebiteenused", 4, LocalTime.of(18, 0), LocalTime.of(19, 30), LocalTime.of(20, 0)));
//        storage.addSubject(new Subject("Python algkursus", 3, LocalTime.of(18, 0), LocalTime.of(19, 30), LocalTime.of(20, 0)));
//        storage.addSubject(new Subject("Python algkursus", 0, LocalTime.of(10, 0), LocalTime.of(11, 30), LocalTime.of(12, 0)));
//        storage.addSubject(new Subject("Physics", 2, LocalTime.of(16, 0), LocalTime.of(17, 30)));

//        Timetable timetable = new Timetable();
//        timetable.initializeRandomTimetable(storage);
//        timetable.printTimetable();
//        timetable.calculateFitness();
//        System.out.println(timetable.getFitness());

//        System.out.println((char)27 + "[31m" + "ERROR MESSAGE IN RED");
//        System.out.println(ChronoUnit.SECONDS.between(LocalTime.of(10, 30), LocalTime.of(10, 30)));

        storage.addSubject(new Subject("Algoritmid ja andmestruktuurid (ITI0050) loeng", 0, "10:00", "11:30", "12:00"));
        storage.addSubject(new Subject("Algoritmid ja andmestruktuurid (ITI0050) praktikum", 2, "8:00", "9:30", "10:00"));
        storage.addSubject(new Subject("Algoritmid ja andmestruktuurid (ITI0050) praktikum", 2, "16:00", "17:30", "18:00"));
//        storage.addSubject(new Subject("Algoritmid ja andmestruktuurid (ITI0050) harjutus", 2, "8:00", "9:30", "10:00"));
//        storage.addSubject(new Subject("Algoritmid ja andmestruktuurid (ITI0050) harjutus", 4, "14:00", "15:30", "16:00"));
        storage.addSubject(new Subject("Automaatjuhtimissüsteemid (ISS0121) loeng", 1, "14:00", "15:30", "16:00"));
        storage.addSubject(new Subject("Automaatjuhtimissüsteemid (ISS0121) praktikum", 0, "11:50", "13:20", "14:00"));
        storage.addSubject(new Subject("Automaatjuhtimissüsteemid (ISS0121) praktikum", 2, "12:00", "13:30", "14:00"));
        storage.addSubject(new Subject("Automaatjuhtimissüsteemid (ISS0121) praktikum", 4, "16:00", "17:30", "18:00"));
        storage.addSubject(new Subject("Andmebaasid II (IDU0230) loeng", 2, "14:00", "15:30", "16:00"));
        storage.addSubject(new Subject("Andmebaasid II (IDU0230) harjutus", 0, "12:00", "13:30", "14:00"));
        storage.addSubject(new Subject("Andmebaasid II (IDU0230) harjutus", 0, "16:00", "17:30", "18:00"));
        storage.addSubject(new Subject("Andmebaasid II (IDU0230) harjutus", 1, "8:00", "9:30", "10:00"));
        storage.addSubject(new Subject("Andmebaasid II (IDU0230) harjutus", 1, "12:00", "13:30", "14:00"));
        storage.addSubject(new Subject("Andmebaasid II (IDU0230) harjutus", 2, "10:00", "11:30", "12:00"));
        storage.addSubject(new Subject("Andmebaasid II (IDU0230) harjutus", 2, "12:00", "13:30", "14:00"));
        storage.addSubject(new Subject("Veebiteenused (IDU0075) loeng", 1, "9:50", "11:20", "12:00"));
        storage.addSubject(new Subject("Veebiteenused (IDU0075) praktikum", 1, "14:00", "15:30", "16:00"));
        storage.addSubject(new Subject("Väljendusoskus (ISS0110) loeng", 1, "12:00", "13:30", "14:00"));
//        storage.addSubject(new Subject("Väljendusoskus (ISS0110) harjutus", 3, "8:50", "11:05"));
//        storage.addSubject(new Subject("Väljendusoskus (ISS0110) harjutus", 3, "11:30", "13:45"));
        storage.addSubject(new Subject("Loogiline programmeerimine (ITI0021) loeng", 2, "10:00", "11:30", "12:00"));
        storage.addSubject(new Subject("Loogiline programmeerimine (ITI0021) praktikum", 1, "10:00", "11:30", "12:00"));
        storage.addSubject(new Subject("Loogiline programmeerimine (ITI0021) praktikum", 1, "14:00", "15:30", "16:00"));
        storage.addSubject(new Subject("Loogiline programmeerimine (ITI0021) praktikum", 2, "12:00", "13:30", "14:00"));
        storage.addSubject(new Subject("Digitaalsüsteemid (IAY0150) loeng", 3, "9:50", "11:20", "12:00"));
        storage.addSubject(new Subject("Digitaalsüsteemid (IAY0150) praktikum+harjutus", 3, "17:45", "19:15", "20:00"));
        storage.addSubject(new Subject("Digitaalsüsteemid (IAY0150) praktikum+harjutus", 4, "8:00", "9:30", "10:00"));
        storage.addSubject(new Subject("Digitaalsüsteemid (IAY0150) praktikum+harjutus", 4, "10:00", "11:30", "12:00"));
        storage.addSubject(new Subject("Digitaalsüsteemid (IAY0150) praktikum+harjutus", 4, "12:00", "13:30", "14:00"));

        GeneticAlgorithm algorithm = new GeneticAlgorithm(1000, 100, storage);
        Timetable timetable = algorithm.findFittestTimetable();
    }
}
