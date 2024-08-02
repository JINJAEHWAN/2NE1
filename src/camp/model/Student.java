package camp.model;

public class Student {
    private String studentId;
    private String studentName;
    private Condition condition;

    public Student(String seq, String studentName) {
        this.studentId = seq;
        this.studentName = studentName;
        this.condition = Condition.GREEN;
    }

    // Getter
    public String getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public enum Condition {
        GREEN, RED, YELLOW
    }
}
