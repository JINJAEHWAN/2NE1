package camp.model;

public class Score {    //DTO VO BEAN POJO
    // 멤버 변수 - 기본키 : scoreId, 외래키 : studentId, subjectId
    private String scoreId;
    private String studentId;
    private String subjectId;
    private int round;
    private int score;
    private char grade;

    // Score 생성자 - 회차정보(사용자입력), score 점수를 기반으로 Score 인스턴스 생성시 자동으로 grade 값 할당.
    // subjectID값에 따라 필수 과목, 선택 과목 구분하여 등급 부여
    public Score(String scoreId, String studentId, String subjectId, int round, int score) {
        this.scoreId = scoreId;
        this.studentId = studentId;
        this.subjectId = subjectId;
        this.round = round;
        this.score = score;

        //과목 코드가 선택 과목 일 경우
        if (subjectId.equals("SU6") || subjectId.equals("SU7") || subjectId.equals("SU8") || subjectId.equals("SU9")) {
            if (score >= 90 && score <= 100) {
                this.grade = 'A';
            } else if (score >= 80 && score <= 89) {
                this.grade = 'B';
            } else if (score >= 70 && score <= 79) {
                this.grade = 'C';
            } else if (score >= 60 && score <= 69) {
                this.grade = 'D';
            } else if (score >= 50 && score <= 59) {
                this.grade = 'F';
            } else {
                this.grade = 'N';
            }
        }//과목 코드가 필수 과목 일 경우
        else {
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


    // 점수 수정 메서드
    public void setScore(int newScore) {
        this.score = newScore;
        // 점수에 따라 등급을 다시 설정
        //this.subjectId (자신의 과목코드)가 선택 과목이면
        if (this.subjectId.equals("SU6") || this.subjectId.equals("SU7") || this.subjectId.equals("SU8") || this.subjectId.equals("SU9")) {
            if (newScore >= 90 && newScore <= 100) {
                this.grade = 'A';
            } else if (newScore >= 80 && newScore <= 89) {
                this.grade = 'B';
            } else if (newScore >= 70 && newScore <= 79) {
                this.grade = 'C';
            } else if (newScore >= 60 && newScore <= 69) {
                this.grade = 'D';
            } else if (newScore >= 50 && newScore <= 59) {
                this.grade = 'F';
            } else {
                this.grade = 'N';
            }
            //자신의 과목코드가 필수 과목이면.
        } else {
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
}
