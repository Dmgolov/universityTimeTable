package genetic.timetable;

import genetic.storage.SubjectStorage;
import genetic.subjects.Subject;
import genetic.util.Utility;

import java.time.temporal.ChronoUnit;
import java.util.*;

public class Timetable {

    // subjects are used as genes
    private LinkedHashMap<Integer, List<Subject>> days = new LinkedHashMap<>();

    private long fitness = 0;

    private long maxFitness;

    private static final int CONFLICTS_WEIGHT = 3;
    private static final int TIME_GAPS_WEIGHT = 1;
    private static final int TIME_GAPS_DURATION_WEIGHT = 2;

    public Timetable() {
        // initialize lists in days HashMap
        for (int day = 0; day < 7; day++) {
            days.put(day, new ArrayList<>());
        }
    }

    public Timetable(LinkedHashMap<Integer, List<Subject>> days, long fitness, long maxFitness) {
        this.days = days;
        this.fitness = fitness;
        this.maxFitness = maxFitness;
    }

    public Timetable getDeepCopy() {
        LinkedHashMap<Integer, List<Subject>> days = new LinkedHashMap<>();
        for (Map.Entry<Integer, List<Subject>> day : this.days.entrySet()) {
            List<Subject> subjects = new ArrayList<>(day.getValue());
            days.put(new Integer(day.getKey()), subjects);
        }
        return new Timetable(days, fitness, maxFitness);
    }

    public long getFitness() {
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
        return new Subject("Subject not found by index", 0, "00:00", "00:00");
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
        return new Subject("Subject is missing", 0, "00:00", "00:00");
    }

    private boolean subjectsAreOverlapping(Subject s1, Subject s2) {
        return s1.getStart().isBefore(s2.getBreakEnd()) &&
                s2.getStart().isBefore(s1.getBreakEnd());
    }

    public void calculateFitness() {
        fitness = 0;
        long subjectsNumber = 0;
        // One conflict is one unit
        long maxConflicts;
        long totalConflicts = 0;
        // One time gap is one unit
        long maxTimeGaps;
        long totalTimeGaps = 0;
        // One minute is one unit
        long maxOneTimeGapDuration = 1440; // 24 hour = 1440 minutes
        long maxTimeGapsDuration;
        long totalTimeGapsDuration = 0;
        for (List<Subject> day : days.values()) {
            long dayConflicts = 0;
            for (int i = 0; i < day.size(); i++) {
                subjectsNumber++;
                for (int j = i + 1; j < day.size(); j++) {
                    // check if subjects are overlapping
                    // (if s1 end is 10:30 and s2 start is 10:30 then they are not overlapping)
                    // used https://stackoverflow.com/questions/17106670/how-to-check-a-timeperiod-is-overlapping-another-time-period-in-java
                    if (subjectsAreOverlapping(day.get(i), day.get(j))) {
                        dayConflicts++;
                    }
                    // compare subject and it's next subject if between them is time gap
                    // (compare only not conflicting subjects)
                    // used https://stackoverflow.com/questions/28353725/java-subtract-localtime
                    else if (j == i + 1 &&
                            ChronoUnit.SECONDS.between(day.get(i).getBreakEnd(), day.get(j).getStart()) > 0) {

                        totalTimeGaps++;

                        totalTimeGapsDuration += ChronoUnit.MINUTES.between(day.get(i).getBreakEnd(), day.get(j).getStart());
                    }
                }
            }
            totalConflicts += dayConflicts;
        }

        maxConflicts = Utility.findCombinationsNumber(subjectsNumber, 2).longValue();
        maxTimeGaps = subjectsNumber -1;
        maxTimeGapsDuration = maxTimeGaps * maxOneTimeGapDuration;  // amount of time gaps multiplied by max duration of one time gap in minutes

        // for conflicts, time gaps and time gaps duration units balance
        // need to multiply max conflicts, conflicts and max time gaps, time gaps with max one time gap duration
        maxConflicts *= maxOneTimeGapDuration;
        totalConflicts *= maxOneTimeGapDuration;
        maxTimeGaps *= maxOneTimeGapDuration;
        totalTimeGaps *= maxOneTimeGapDuration;

        // adding multipliers (weight) to conflict, time gap, time gap duration
        maxConflicts *= CONFLICTS_WEIGHT;
        totalConflicts *= CONFLICTS_WEIGHT;
        maxTimeGaps *= TIME_GAPS_WEIGHT;
        totalTimeGaps *= TIME_GAPS_WEIGHT;
        maxTimeGapsDuration *= TIME_GAPS_DURATION_WEIGHT;
        totalTimeGapsDuration *= TIME_GAPS_DURATION_WEIGHT;

        fitness = maxConflicts + maxTimeGaps + maxTimeGapsDuration - totalConflicts - totalTimeGaps - totalTimeGapsDuration;
        maxFitness = maxConflicts + maxTimeGaps + maxTimeGapsDuration;
        // System.out.println(maxConflicts + ", " + maxTimeGaps + ", " + totalConflicts + ", " + totalTimeGaps);
    }

    public long getMaxFitness() {
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
        String printColor = (char)27 + "[30m";
        for (Map.Entry<Integer, List<Subject>> day : days.entrySet()) {
            List<Subject> schedule = day.getValue();
            for (int i = 0; i < schedule.size(); i++) {

                Subject subject = schedule.get(i);
                if (i + 1 < schedule.size()) {
                    Subject nextSubject = schedule.get(i + 1);
                    if (subjectsAreOverlapping(subject, nextSubject)) {
                        printColor = (char) 27 + "[31m";
                    }
                }
                if (i - 1 >= 0){
                    Subject previousSubject = schedule.get(i - 1);
                    if (subjectsAreOverlapping(previousSubject, subject)) {
                        printColor = (char)27 + "[31m";
                    }
                }

                System.out.printf("%s %d %tH:%tM - %tH:%tM - %tH:%tM   %s\n",
                        printColor,
                        subject.getDay(), subject.getStart(), subject.getStart(),
                        subject.getEnd(), subject.getEnd(),
                        subject.getBreakEnd(), subject.getBreakEnd(), subject.getName());

                printColor = (char)27 + "[30m";
            }
            System.out.println((char)27 + "[30m" + "-------");
        }
    }
}