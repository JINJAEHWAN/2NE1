package camp.model;

public class Student {
    private String studentId;
    private String studentName;
    private condition c;

    public Student(String seq, String studentName) {
        this.studentId = seq;
        this.studentName = studentName;
        this.c = condition.Green;
    }

    // Getter
    public String getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public condition getCondition() {
        System.out.println(this.c);
        return c;
    }

    public enum condition {
        Green, Red, Yellow
    }
}


