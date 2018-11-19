package antColony.algorithm;

import antColony.subjects.Subject;

import java.util.*;

public class Ant {

    private List<Subject> subjects;
    private double pheromone[][];
    private double total[][];  //keeps heuristic information times pheromone for each arc

    protected int trailSize;
    protected int trail[];
    protected boolean visited[];

    public Ant(int tourSize) {
        this.trailSize = tourSize;
        this.trail = new int[tourSize];
        this.visited = new boolean[tourSize];
    }

    protected void visitSubject(int currentIndex, int subject) {
        trail[currentIndex + 1] = subject;
        visited[subject] = true;
    }

    protected boolean visited(int i) {
        return visited[i];
    }

    protected double trailLength(double graph[][]) {
        double length = graph[trail[trailSize - 1]][trail[0]];
        for (int i = 0; i < trailSize - 1; i++) {
            length += graph[trail[i]][trail[i + 1]];
        }
        return length;
    }

    protected void clear() {
        for (int i = 0; i < trailSize; i++)
            visited[i] = false;
    }


    public Ant(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public HashMap<String, Subject> AntWalk(int alpha, int beta, int trail) {
//        HashMap<String, Subject> subjectHashMap = new HashMap<>();
//        while (!this.subjects.isEmpty()) {
//            Random random = new Random();
//            int currentSubject;
//            if (subjects.size() != 1) {
//                currentSubject = random.nextInt(subjects.size());
//            } else {
//                currentSubject = 0;
//            }
//            if (subjectHashMap.size() > 0) {
//                List<Subject> subjectList = new ArrayList<>(subjectHashMap.values());
//                Subject subject = subjects.get(currentSubject);
//                for (Subject subjectFromList : subjectList) {
//                    if (subject.getDay() == subjectFromList.getDay()) {
//                        if (subject.getStart().isAfter(subjectFromList.getStart()) &&
//                                subject.getEnd().isAfter(subjectFromList.getEnd())) {
//                            subjectHashMap.put("DAY" + subject.getDay() + "START" + subject.getStart()
//                                    + "END" + subject.getEnd(), subject);
//                            System.out.println("PUT");
//                            subjects.remove(currentSubject);
//                        }
//                        if (subject.getStart().isBefore(subjectFromList.getStart()) &&
//                                subject.getEnd().isBefore(subjectFromList.getEnd())) {
//                            subjectHashMap.put("DAY" + subject.getDay() + "START" + subject.getStart()
//                                    + "END" + subject.getEnd(), subject);
//                            System.out.println("PUT");
//                            subjects.remove(currentSubject);
//                        }
//                    } else {
//                        subjectHashMap.put("DAY" + subject.getDay() + "START" + subject.getStart()
//                                + "END" + subject.getEnd(), subject);
//                        subjects.remove(currentSubject);
//
//                    }
//                }
//                System.out.println("LIST S " + subjects.size());
//            } else {
//                Subject subject = subjects.get(currentSubject);
//                subjects.remove(currentSubject);
//                subjectHashMap.put("DAY" + subject.getDay() + "START" + subject.getStart()
//                        + "END" + subject.getEnd(), subject);
//            }
//        }
//        System.out.println(subjectHashMap.size());
//        return subjectHashMap;
        HashMap<String, Subject> subjectHashMap = new HashMap<>();
        for (Subject subject : this.subjects){
            switch (subject.getDay()){
                case (1):  subjectHashMap.put("DAY"+subject.getDay()+"NAME"+subject.getSubjectName()+"TYPE"+subject.getType(), subject);
            }

        }
        return subjectHashMap;
    }
}
