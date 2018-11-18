package genetic.timetable;

import genetic.storage.SubjectStorage;
import genetic.subjects.Subject;
import genetic.util.Utility;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Timetable {

    // subjects are used as genes
    private LinkedHashMap<Integer, List<Subject>> days = new LinkedHashMap<>();

    private int fitness = 0;

    private int maxFitness;

    public Timetable() {
        // initialize lists in days HashMap
        for (int day = 0; day < 7; day++) {
            days.put(day, new ArrayList<>());
        }
    }

    public int getFitness() {
        return fitness;
    }

    public Subject getSubjectByIndex(int index) {
        int counter = 0;
        for (Map.Entry<Integer, List<Subject>> day : days.entrySet()) {
            List<Subject> subjects = day.getValue();
            for (Subject subject : subjects) {
                if (counter == index) {
                    return subject;
                } else {
                    counter++;
                }
            }
        }
        return new Subject("Subject not found by index", 0,
                LocalTime.of(0, 0), LocalTime.of(0, 0));
    }

    // replaces subject located in timetable with given subject and returns old subject (which was in timetable)
    public Subject replaceSubjectWithGiven(Subject subject) {
        for (Map.Entry<Integer, List<Subject>> day : days.entrySet()) {
            List<Subject> subjects = day.getValue();
            for (int i = 0; i < subjects.size(); i++) {
                Subject currentSubject = subjects.get(i);
                if (currentSubject.getName().equals(subject.getName())) {
                    days.get(currentSubject.getDay()).remove(i);
                    days.get(currentSubject.getDay()).sort(Comparator.comparing(Subject::getStart));
                    days.get(subject.getDay()).add(subject);
                    days.get(subject.getDay()).sort(Comparator.comparing(Subject::getStart));
                    return currentSubject;
                }
            }
        }
        return new Subject("Subject is missing", 0,
                LocalTime.of(0, 0), LocalTime.of(0, 0));
    }

    public void calculateFitness() {
        fitness = 0;
        int subjectsNumber = 0;
        int maxConflicts;
        int totalConflicts = 0;
        int maxTimeGaps;
        int totalTimeGaps = 0;
        for (List<Subject> day : days.values()) {
            long dayConflicts = 0;
            for (int i = 0; i < day.size(); i++) {
                subjectsNumber++;
                for (int j = i + 1; j < day.size(); j++) {
                    // check if subjects are overlapping
                    // (if s1 end is 10:30 and s2 start is 10:30 then they are not overlapping)
                    // used https://stackoverflow.com/questions/17106670/how-to-check-a-timeperiod-is-overlapping-another-time-period-in-java
                    if (day.get(i).getStart().isBefore(day.get(j).getBreakEnd()) &&
                            day.get(j).getStart().isBefore(day.get(i).getBreakEnd())) {
                        dayConflicts++;
                    }
                    // compare subject and it's next subject if between them is time gap
                    // (compare only not conflicting subjects)
                    // used https://stackoverflow.com/questions/28353725/java-subtract-localtime
                    else if (j == i + 1 &&
                            ChronoUnit.SECONDS.between(day.get(i).getBreakEnd(), day.get(j).getStart()) > 0) {
                        totalTimeGaps++;
                    }
                }
            }
            totalConflicts += dayConflicts;
        }
        maxConflicts = Utility.findCombinationsNumber(subjectsNumber, 2).intValue();
        maxTimeGaps = subjectsNumber -1;
        fitness = maxConflicts + maxTimeGaps - totalConflicts - totalTimeGaps;
        maxFitness = maxConflicts + maxTimeGaps;
        // System.out.println(maxConflicts + ", " + maxTimeGaps + ", " + totalConflicts + ", " + totalTimeGaps);
    }

    public int getMaxFitness() {
        calculateFitness();
        return maxFitness;
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
                System.out.printf("%d %tH:%tM - %tH:%tM - %tH:%tM   %s\n",
                        subject.getDay(), subject.getStart(), subject.getStart(),
                        subject.getEnd(), subject.getEnd(),
                        subject.getBreakEnd(), subject.getBreakEnd(), subject.getName());
            }
            System.out.println("-------");
        }
    }
}