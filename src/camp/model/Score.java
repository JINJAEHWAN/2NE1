package camp.model;

public class Score {
    private String scoreId;
    private String studentId;
    private int round;
    private int score;
    private char grade;


    public Score(String seq, int round, int score, String studentId) {
        this.scoreId = seq;
        this.score = score;
        this.round = round;

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

    // 점수 수정 메서드
    public void setScore(int newScore) {
        this.score = newScore;
        // 점수에 따라 등급을 다시 설정
        if (newScore >= 95 && newScore <= 100) {
            this.grade = 'A';
        } else if (newScore >= 90 && newScore <= 94) {
            this.grade = 'B';
        } else if (newScore >= 80 && newScore <= 89) {
            this.grade = 'C';
        } else if (newScore >= 70 && newScore <= 79) {
            this.grade = 'D';
        } else if (newScore >= 60 && newScore <= 69) {
            this.grade = 'F';
        } else {
            this.grade = 'N';
        }
    }
}
