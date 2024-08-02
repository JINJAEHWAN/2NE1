package camp.model;

import java.util.List;

public class Student {
    private String studentId;
    private String studentName;
    private List<Subject> subjectList;
    private condition c;


    public Student(String seq, String studentName, List<Subject> subjectList) {
        this.studentId = seq;
        this.studentName = studentName;
        this.subjectList = subjectList;
        this.c = condition.Green;
    }


    // Getter
    public String getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public List<Subject> getSubjectList() {
        return subjectList;
    }

    public condition getCondition() {
        return c;
    }


    // Setter
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public void setCondition(condition condition) {
        this.c = condition;
    }

    public void setSubject(Subject subject) {
        this.subjectList.add(subject);
    }

    public enum condition {
        Green, Red, Yellow
    }
}

