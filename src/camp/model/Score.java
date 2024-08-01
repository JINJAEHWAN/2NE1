package camp.model;

public class Score {
    // 멤버 변수 - 기본키 : scoreId, 외래키 : studentId, subjectId
    private String scoreId;
    private String studentId;
    private String subjectId;
    private int round;
    private int score;
    private char grade;

    // Score 생성자 - 회차정보(사용자입력), score 점수를 기반으로 Score 인스턴스 생성시 자동으로 grade 값 할당.
    public Score(String scoreId, String studentId, String subjectId, int round, int score) {
        this.scoreId = scoreId;
        this.studentId = studentId;
        this.subjectId = subjectId;
        this.round = round;
        this.score = score;
        if (score >= 95 && score <= 100) {
            this.grade = 'A';
        } else if (score >= 90 && score <= 94) {
            this.grade = 'B';
        } else if (score >= 80 && score <= 89) {
            this.grade = 'C';
        } else if (score >= 70 && score <= 79) {
            this.grade = 'D';
        } else if (score >= 60 && score <= 69) {
            this.grade = 'F';
        } else {
            this.grade = 'N';
        }


    }

    // Getter
    public String getScoreId() {
        return scoreId;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getSubjectId() {
        return subjectId;
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


}