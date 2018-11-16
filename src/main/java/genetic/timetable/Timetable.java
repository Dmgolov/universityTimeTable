package genetic.timetable;

import genetic.storage.SubjectStorage;
import genetic.subjects.Subject;
import genetic.util.Utility;

import java.util.*;

public class Timetable {

    private HashMap<Integer, List<Subject>> days = new HashMap<>();

    private long fitness = 0;

    public Timetable() {
        // initialize lists in days HashMap
        for (int day = 0; day < 7; day++) {
            days.put(day, new ArrayList<>());
        }
    }

    public long getFitness() {
        return fitness;
    }

    public void calculateFitness() {
        fitness = 0;
        int subjectsNumber = 0;
        long maxConflicts;
        long totalConflicts = 0;
        for (List<Subject> day : days.values()) {
            long dayConflicts = 0;
            for (int i = 0; i < day.size(); i++) {
                subjectsNumber++;
                for (int j = i + 1; j < day.size(); j++) {
                    // check if subjects are overlapping
                    // (if s1 end is 10:30 and s2 start is 10:30 then they are not overlapping)
                    // used https://stackoverflow.com/questions/17106670/how-to-check-a-timeperiod-is-overlapping-another-time-period-in-java
                    if (day.get(i).getStart().isBefore(day.get(j).getEnd()) &&
                            day.get(j).getStart().isBefore(day.get(i).getEnd())) {
                        dayConflicts++;
                    }
                }
            }
            totalConflicts += dayConflicts;
        }
        maxConflicts = Utility.findCombinationsNumber(subjectsNumber, 2).longValue();
        fitness = maxConflicts - totalConflicts;
    }

    public void initializeRandomTimetable(SubjectStorage storage) {
        Random random = new Random(System.currentTimeMillis());
        Set<String> usedSubjects = new HashSet<>();
        String subjectName = "#placeholder#";

        for (int i = 0; i < storage.getSize(); i++) {
            while (usedSubjects.contains(subjectName) && usedSubjects.size() < storage.getSize()
                    || subjectName.equals("#placeholder#")) {
                subjectName = new ArrayList<>(storage.getSubjectNames()).get(random.nextInt(storage.getSize()));
            }

            usedSubjects.add(subjectName);

            List<Subject> subjectsVariants = storage.getSubjectPossibleVariants(subjectName);
            Subject subject = subjectsVariants.get(random.nextInt(subjectsVariants.size()));

            days.get(subject.getDay()).add(subject);
        }

        // sorting for normal chain of subjects by start time
        for (List<Subject> day : days.values()) {
            day.sort(Comparator.comparing(Subject::getStart));
        }
    }

    public void printTimetable() {
        for (Map.Entry<Integer, List<Subject>> day : days.entrySet()) {
            List<Subject> schedule = day.getValue();
            for (Subject subject : schedule) {
                System.out.printf("%d %tH:%tM - %tH:%tM   %s \n",
                        subject.getDay(), subject.getStart(), subject.getStart(),
                        subject.getEnd(), subject.getEnd(), subject.getName());
            }
            System.out.println("-------");
        }
    }
}