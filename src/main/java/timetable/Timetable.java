package timetable;

import subjects.Subject;

import java.util.HashMap;

public class Timetable {

    private HashMap<Integer, Subject> subjects;


    public Timetable() {
        this.subjects = new HashMap<Integer, Subject>();
    }

    public Timetable(Timetable child) {
        this.subjects = child.getSubjects();
    }

    public HashMap<Integer, Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(HashMap<Integer, Subject> subjects) {
        this.subjects = subjects;
    }
}
