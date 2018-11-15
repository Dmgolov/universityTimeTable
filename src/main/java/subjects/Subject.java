package subjects;

public class Subject {

    private String subjectName;

    private int day;

    private String time;

    private String classNumber;

    private String teacher;

    public Subject(String subjectName, int day, String time, String classNumber, String teacher) {
        this.subjectName = subjectName;
        this.day = day;
        this.time = time;
        this.classNumber = classNumber;
        this.teacher = teacher;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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
