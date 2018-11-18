package antColony;

import antColony.subjects.Subject;
import antColony.timetable.Timetable;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        Subject subject = new Subject("Algoritmid ja andmestruktuurid", 1, "lec",
                LocalTime.of(10, 0), LocalTime.of(11, 30), "Mark", "U06");
        Subject subject1 = new Subject("Java", 1, "lec", LocalTime.of(10, 0),
                LocalTime.of(11, 30), "Jaan", "U05");
        Subject subject3 = new Subject("Veebiteenused", 1, "lec", LocalTime.of(9, 50),
                LocalTime.of(11, 20), "Tomas", "U05");
        Subject subject4 = new Subject("Andmebaasid", 1, "lec", LocalTime.of(15, 0),
                LocalTime.of(16, 30), "Jaan", "U05");
        Subject ISS0110 = new Subject("Väljendusoskus", 4, "har", LocalTime.of(8, 50),
                LocalTime.of(11, 5), "Rei", "U05");
        Subject ISS0110_2 = new Subject("Väljendusoskus", 4, "har", LocalTime.of(11, 30),
                LocalTime.of(13, 45), "Rei", "U05");
        Subject IDU0075 = new Subject("Veebiteenused", 2, "lec", LocalTime.of(9, 50),
                LocalTime.of(11, 20), "Rei", "U05");
        Subject IDU0075_1 = new Subject("Veebiteenused", 2, "pra", LocalTime.of(14, 00),
                LocalTime.of(15, 30), "Rei", "U05");
        Subject ITI0021 = new Subject("Loogiline programmeerimine ", 2, "pra",
                LocalTime.of(10, 0),
                LocalTime.of(11, 30), "Rei", "U05");
        Subject ITI0021_1 = new Subject("Loogiline programmeerimine ", 2, "pra",
                LocalTime.of(14, 0),
                LocalTime.of(15, 30), "Rei", "U05");
        Subject ITI0021_2 = new Subject("Loogiline programmeerimine ", 3, "lec",
                LocalTime.of(10, 0),
                LocalTime.of(11, 30), "Rei", "U05");
        Subject ITI0021_3 = new Subject("Loogiline programmeerimine ", 3, "pra",
                LocalTime.of(12, 0),
                LocalTime.of(13, 30), "Rei", "U05");
        Subject subject5 = new Subject("Väljendus", 1, "lec", LocalTime.of(12, 0),
                LocalTime.of(13, 30), "Rein", "U05");
        Subject subject6 = new Subject("AJS", 1, "lec", LocalTime.of(14, 0),
                LocalTime.of(15, 30), "Andres", "U05");
        Subject subject2 = new Subject("Python", 1, "lec", LocalTime.of(13, 0),
                LocalTime.of(14, 30), "Jaan", "U05");




        List<Subject> subjects = new ArrayList<>();
        subjects.add(subject);
        subjects.add(subject1);
        subjects.add(subject2);
        subjects.add(subject3);
        subjects.add(subject4);
        subjects.add(subject5);

        Timetable timetable = new Timetable();

        System.out.println(timetable.initialTimetable(subjects));
    }
}
