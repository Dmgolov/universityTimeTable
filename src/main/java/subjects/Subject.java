package subjects;

import java.time.LocalTime;

public class Subject {

    private String subjectName;

    private int day;

    private LocalTime start;

    private LocalTime end;

    private String classNumber;

    private String teacher;

    public Subject(String subjectName, int day, LocalTime start, LocalTime end, String classNumber, String teacher) {
        this.subjectName = subjectName;
        this.day = day;
        this.start = start;
        this.end = end;
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

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
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
}
