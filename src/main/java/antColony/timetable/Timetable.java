package antColony.timetable;

import antColony.algorithm.AntColony;
import antColony.subjects.Subject;

import java.util.HashMap;
import java.util.List;

public class Timetable {

    private HashMap<String, Subject> timeTable = new HashMap<>();

    AntColony antColony;


    public HashMap<String, Subject> initialTimetable(List<Subject> subjects) {
        for (Subject subject : subjects) {
            if (!timeTable.containsKey("DAY"+subject.getDay()+"START"+subject.getStart().getHour()+"END"+subject.getEnd().getHour())){
                timeTable.put("DAY"+subject.getDay()+"START"+subject.getStart().getHour()+"END"+subject.getEnd().getHour(), subject);
            } else {
                continue;
            }
        }
        return timeTable;
    }

    public boolean findProblemsInTimetable(HashMap<String, Subject> subjectHashMap){
        return false;
    }

}
