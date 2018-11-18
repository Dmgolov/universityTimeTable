package antColony.algorithm;

import antColony.subjects.Subject;

import java.util.*;

public class Ant {

    private List<Subject> subjects;

    public Ant(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public HashMap<String, Subject> AntWalk(int alpha, int beta, int trail) {
        HashMap<String, Subject> subjectHashMap = new HashMap<>();
        while (!subjects.isEmpty()) {
            Random random = new Random();
            int currentSubject = random.nextInt(subjects.size());
            if (subjectHashMap.size() > 0) {
                List<Subject> subjectList = new ArrayList<>(subjectHashMap.values());
                Subject subject = subjects.get(currentSubject);
                for (Subject subjectFromList : subjectList) {
                    if (subject.getDay() == subjectFromList.getDay()) {
                        if (subject.getStart().isAfter(subjectFromList.getStart()) &&
                                subject.getEnd().isAfter(subjectFromList.getEnd())) {
                            subjectHashMap.put("DAY" + subject.getDay() + "START" + subject.getStart()
                                    , subject);
                        }
                        if (subject.getStart().isBefore(subjectFromList.getStart()) &&
                                subject.getEnd().isBefore(subjectFromList.getEnd())) {
                            subjectHashMap.put("DAY" + subject.getDay() + "START" + subject.getStart()
                                    , subject);
                        }
                    }
                }
                subjects.remove(currentSubject);

            } else {
                Subject subject = subjects.get(currentSubject);
                subjects.remove(currentSubject);
                subjectHashMap.put("DAY" + subject.getDay() + "START" + subject.getStart()
                        , subject);
            }
        }
        return subjectHashMap;
    }
}
