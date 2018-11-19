package antColony;

import antColony.algorithm.AntColony;
import antColony.subjects.Subject;
import antColony.timetable.Timetable;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        Subject ISS0110 = new Subject("Väljendusoskus", 4, "har",
                LocalTime.of(8, 50),
                LocalTime.of(11, 5), "Rei", "U05");
        Subject ISS0110_2 = new Subject("Väljendusoskus", 4, "har",
                LocalTime.of(11, 30),
                LocalTime.of(13, 45), "Rei", "U05");
        Subject IDU0075 = new Subject("Veebiteenused", 2, "lec",
                LocalTime.of(9, 50),
                LocalTime.of(11, 20), "Rei", "U05");
        Subject IDU0075_1 = new Subject("Veebiteenused", 2, "pra",
                LocalTime.of(14, 0),
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
        Subject IAY0150 = new Subject("Digitaalsüsteemid", 4, "lec",
                LocalTime.of(9, 50),
                LocalTime.of(11, 20), "Peeter", "U05");
        Subject IAY0150_pra = new Subject("Digitaalsüsteemid", 4, "pra",
                LocalTime.of(17, 45),
                LocalTime.of(19, 15), "Peeter", "U05");
        Subject IAY0150_pra2 = new Subject("Digitaalsüsteemid", 4, "pra",
                LocalTime.of(17, 45),
                LocalTime.of(19, 15), "Peeter", "U05");
        Subject IAY0150_pra3 = new Subject("Digitaalsüsteemid", 5, "pra",
                LocalTime.of(8, 0),
                LocalTime.of(9, 30), "Peeter", "U05");
        Subject IAY0150_pra4 = new Subject("Digitaalsüsteemid", 5, "pra",
                LocalTime.of(10, 0),
                LocalTime.of(11, 30), "Peeter", "U05");
        Subject IAY0150_pra5 = new Subject("Digitaalsüsteemid", 5, "pra",
                LocalTime.of(12, 0),
                LocalTime.of(13, 30), "Peeter", "U05");
        Subject ISS0121 = new Subject("Automaatjuhtimissüsteemid", 1, "pra",
                LocalTime.of(11, 50),
                LocalTime.of(13, 20), "Aleksei", "U05");
        Subject ISS0121_lec = new Subject("Automaatjuhtimissüsteemid", 2, "lec",
                LocalTime.of(11, 50),
                LocalTime.of(13, 20), "Aleksei", "U05");
        Subject ISS0121_pra = new Subject("Automaatjuhtimissüsteemid", 3, "pra",
                LocalTime.of(14, 0),
                LocalTime.of(15, 30), "Andres", "U05");
        Subject ISS0121_pra2 = new Subject("Automaatjuhtimissüsteemid", 5, "pra",
                LocalTime.of(16, 0),
                LocalTime.of(17, 30), "Andres", "U05");


        List<Subject> subjects = new ArrayList<>();
        subjects.add(ISS0110);
        subjects.add(ISS0110_2);
        subjects.add(IDU0075);
        subjects.add(IDU0075_1);
        subjects.add(ITI0021);
        subjects.add(ITI0021_1);
        subjects.add(ITI0021_2);
        subjects.add(ITI0021_3);

        subjects.add(IAY0150);
        subjects.add(IAY0150_pra);
        subjects.add(IAY0150_pra2);
        subjects.add(IAY0150_pra3);
        subjects.add(IAY0150_pra4);
        subjects.add(IAY0150_pra5);

        subjects.add(ISS0121);
        subjects.add(ISS0121_lec);
        subjects.add(ISS0121_pra);
        subjects.add(ISS0121_pra2);


        AntColony antColony = new AntColony();
        antColony.setSubjects(subjects);
        List<Subject> done = new ArrayList<>(antColony.AntColonyOptimization(1, 1).values());
//        System.out.println(antColony.AntColonyOptimization(1, 1));
        done.sort(new Comparator<Subject>() {
            @Override
            public int compare(Subject o1, Subject o2) {
                return o1.getDay() - (o2.getDay());
            }
        });
        for (int x = 0; x < done.size(); x++) {
            for (int i = 1; i < done.size(); i++) {
                if (done.get(x).getSubjectName().equals(done.get(i).getSubjectName()) &&
                        done.get(x).getType().equals(done.get(i).getType())) {
                    done.remove(x);
                }
            }
        }
        System.out.println(done.toString());

        Timetable timetable = new Timetable();

//        System.out.println(timetable.initialTimetable(subjects));
    }
}
