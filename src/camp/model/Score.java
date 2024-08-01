package camp.model;

public class Score {
    private String scoreId;
    private int round;
    private int score;
    private char grade;
    private String studentId;

    public Score(String seq, int round, int score, char grade, String studentId) {
        this.scoreId = seq;
        this.score = score;
        this.round = round;
        this.grade = grade;
    }

    // Getter
    public String getScoreId() {
        return scoreId;
    }

    public int getRound() {
        return round;
    }

    public int getScore() {
        return score;
    }

    public char getGrade() {
        return grade;
    }

    public String getStudentId() {
        return studentId;
    }
}