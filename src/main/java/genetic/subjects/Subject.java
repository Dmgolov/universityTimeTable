package genetic.subjects;

import java.time.LocalTime;

public class Subject {

    // if school subject then -- subject and type (examples: Java OOP practice, Java OOP lecture, Mathematics practice)
    private String name;

    private int day;

    private LocalTime start;

    private LocalTime end;

    // time between end and breakEnd is free time which is needed after subject end.
    // (for example break time between subjects, or time to drive to another location)
    private LocalTime breakEnd;

    private String classNumber;

    private String teacher;

    private String adaptTimeFormat(String localTime) {
        if (localTime.length() < 5) {
            return "0" + localTime;
        }
        return localTime;
    }

    public Subject(String name, int day, String start, String end) {
        this.name = name;
        this.day = day;
        this.start = LocalTime.parse(adaptTimeFormat(start));
        this.end = LocalTime.parse(adaptTimeFormat(end));
        this.breakEnd = LocalTime.parse(adaptTimeFormat(end));
    }

    public Subject(String name, int day, String start, String end, String breakEnd) {
        this.name = name;
        this.day = day;
        this.start = LocalTime.parse(adaptTimeFormat(start));
        this.end = LocalTime.parse(adaptTimeFormat(end));
        this.breakEnd = LocalTime.parse(adaptTimeFormat(breakEnd));
    }

    public Subject(String name, int day, LocalTime start, LocalTime end, LocalTime breakEnd) {
        this.name = name;
        this.day = day;
        this.start = start;
        this.end = end;
        this.breakEnd = breakEnd;
    }

    public Subject(String name, int day, String start, String end, String classNumber, String teacher) {
        this.name = name;
        this.day = day;
        this.start = LocalTime.parse(adaptTimeFormat(start));
        this.end = LocalTime.parse(adaptTimeFormat(end));
        this.breakEnd = LocalTime.parse(adaptTimeFormat(end));
        this.classNumber = classNumber;
        this.teacher = teacher;
    }

    public LocalTime getStart() {
        return start;
    }

    public void setStart(LocalTime start) {
        this.start = start;
    }

    public LocalTime getEnd() {
        return end;
    }

    public void setEnd(LocalTime end) {
        this.end = end;
    }

    public LocalTime getBreakEnd() {
        return breakEnd;
    }

    public void setBreakEnd(LocalTime breakEnd) {
        this.breakEnd = breakEnd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(String classNumber) {
        this.classNumber = classNumber;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "name='" + name + '\'' +
                ", day=" + day +
                ", start=" + start +
                ", end=" + end +
                ", classNumber='" + classNumber + '\'' +
                ", teacher='" + teacher + '\'' +
                '}';
    }
}
