package antColony.subjects;

import java.time.LocalTime;
import java.util.Comparator;

public class Subject  {

    private String subjectName;

    private int day;

    private String type;

    private LocalTime start;

    private LocalTime end;

    private String teacher;

    private String classNumber;

    public Subject(String subjectName, int day, String type, LocalTime start, LocalTime end, String teacher, String classNumber) {
        this.subjectName = subjectName;
        this.day = day;
        this.type = type;
        this.start = start;
        this.end = end;
        this.teacher = teacher;
        this.classNumber = classNumber;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(String classNumber) {
        this.classNumber = classNumber;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "subjectName='" + subjectName + '\'' +
                ", day=" + day +
                ", type='" + type + '\'' +
                ", start=" + start +
                ", end=" + end +
                ", teacher='" + teacher + '\'' +
                ", classNumber='" + classNumber + '\'' +
                '}' + '\n';
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }



}
