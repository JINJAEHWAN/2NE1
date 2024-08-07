package camp.controller;

import camp.CampManagementApplication;
import camp.model.Score;
import camp.model.Student;
import camp.model.Subject;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ScoreDAO implements modelDAO {

    private static int scoreIndex;
    private static final String INDEX_TYPE_SCORE = "SC";

    private final List<Score> scoreStore = new ArrayList<>();

    @Override
    public void createModel(String... id) {
        Scanner sc = new Scanner(System.in);
        List<Subject> subjectList = new ArrayList<>();

        // 수강생 코드 입력 검색.
        int round;
        int score;
        while (true) {

            //학생과 과목이 정해졌을때 입력할 회차 정보를 받음.              -- 0807 scoreStore에 이미 있는 회차에 대해 또 입력을 받게하면안됨.
            System.out.print("회차를 입력하세요 (1~10) : ");
            round = sc.nextInt();

            //입력 정보에 대한 유효성 검사
            try {
                if (round >= 1 && round <= 10) {
                    System.out.println("회차 입력 완료!");
                } else {
                    throw new Exception("회차 정보와 다릅니다.");
                }
            } catch (Exception exception) {
                System.out.println("에러메세지:" + exception.getMessage());
                continue;
            }

            //점수 입력
            System.out.print("점수를 입력하세요 : ");
            score = sc.nextInt();

            //입력 점수에 대한 유효성 검사
            try {
                if (score >= 0 && score <= 100) {
                    System.out.println("점수 입력 완료!");
                    break;
                } else {
                    throw new Exception("점수는 0부터 100까지입니다.");
                }
            } catch (Exception exception) {
                System.out.println("에러메세지:" + exception.getMessage());
            }
        }

        //입력한 값을 토대로 score 객체 생성 및 저장.
        Score scoreID = new Score(sequence(INDEX_TYPE_SCORE), id[0], id[1], round, score);
        scoreStore.add(scoreID);

        System.out.println("시험 점수를 등록합니다...");

        //입력한 점수에 대한 등급도 출력 - 등급은 생성자 호출과 동시에 정해지므로 생성했던 인스턴스(scoreID.getGrade()로 호출해야함)
        System.out.println("점수에 대한 등급 : " + scoreID.getGrade());

        System.out.println("\n점수 등록 성공!");


    }

    @Override
    public void readModel(String studentId) {

    }


    @Override
    public void updateModel(String key, String value, String... id) {
        // id[0] = studentId, id[1] = subjectId key, value는 int로 바꿔줘야함
        //두 키 값으로 Score객체를 찾아 key value를 삭제
        for (int i = 0; i < scoreStore.size(); i++) {
            Score score = scoreStore.get(i);
            //studentId와 subjectId, round가 전달받은 값과 동일한 score 객체를 탐색
            if (id[0].equals(score.getStudentId()) && id[1].equals(score.getSubjectId()) && Integer.parseInt(key) == score.getRound()) {
                score.setScore(Integer.parseInt(value));

                System.out.println("\n점수 수정 성공!");
                System.out.println("[수정사항] 회차: " + score.getRound() + ", 점수: " + score.getScore() + ", 등급: " + score.getGrade());
                break;
            }
        }


//        boolean update = false;
//
//        for (int i = 0; i < scoreStore.size(); i++) {
//            Score score = scoreStore.get(i);
//            if (score.getStudentId().equals(studentId) && score.getRound() == round) {
//                score.setScore(newScore);
//                update = true;
//                System.out.println("\n점수 수정 성공!");
//                System.out.println("[수정사항] 회차: " + score.getRound() + ", 점수: " + score.getScore() + ", 등급: " + score.getGrade());
//                break;
//            }
//        }
//        if (!update) {
//            System.out.println("\n해당하는 점수를 찾을 수 없습니다.");
//        }
        System.out.println("\n점수 수정 성공!");

    }

    @Override
    public boolean deleteModel(String studentId) {
        return true;
    }

    //index 자동 증가
    //@Override
    public String sequence(String type) {
        return type + scoreIndex;
    }

    //인자로 전달받은 studentId 값을 key로 가지는 score 객체들을 삭제 (studentId값이 일치하는 모든 Score를 삭제)
    public boolean deleteModelAll(String studentId) {
        //람다함수 사용 good
        //애초에 저장소가 비어있다면
        if (scoreStore.size() == 0) {
            System.out.println("해당 수강생의 삭제할 점수 정보가 존재하지 않습니다.");
            return true;
        }
        //저장소가 채워져있다면 탐색하여 삭제를 진행 결과 bool값을 반환
        boolean removed = scoreStore.removeIf(score -> score.getStudentId().equals(studentId));
        return removed;
    }

    //학생코드, 과목코드 를 key값으로 scoreStore에서 일치하는 정보를 찾아 리스트로 리턴
    @Override
    public List<Score> getAllModelList() {
        Scanner sc = new Scanner(System.in);
        //수강생 코드와 과목 코드가 일치하는 Score를 담을 리스트
        List<Score> scoreList = new ArrayList<>();

        // 조회(관리)할 수강생의 고유 번호
        System.out.print("\n조회할 학생의 고유 번호를 입력해주세요..(ST로 시작합니다.) : ");
        String studentId = sc.next();
        //과목 코드 입력
        System.out.print("조회할 과목코드를 입력해주세요 : ");
        String subjectId = sc.next();
        sc.nextLine();

        //학생고유번호와 과목명이 일치하는 모든 회차의 데이터를 가져옴
        if (scoreStore.size() != 0) {
            try {
                for (int i = 0; i < scoreStore.size(); i++) {
                    //학생 고유번호와 과목명이 일치할 때.
                    if (scoreStore.get(i).getStudentId().equals(studentId) && scoreStore.get(i).getSubjectId().equals(subjectId)) {
                        Score score = scoreStore.get(i);
                        scoreList.add(score);
                    }
                }
            } catch (Exception e) {
                System.out.println("등급 조회 정보를 불러오는 중 문제가 발생했습니다.");
                e.printStackTrace();
            }
            System.out.println();
            System.out.println("회차별 등급을 조회합니다...");
        } else {
            System.out.println("현재 조회할 점수 정보가 존재하지 않습니다.");
        }

        return scoreList;
    }


    //인자로 전달받은 studentId 값을 가지는 Student 정보를 찾아서
    //해당 학생의 수강 과목과 회차에 대한 점수를 출력
    public void inquireRoundScore(String studentId, String subjectId) {

        System.out.println("해당 수강생의 " + subjectId + " 과목 점수 목록:");
        boolean exist3 = false;

        for (int i = 0; i < scoreStore.size(); i++) {
            Score score = scoreStore.get(i);
            if (score.getStudentId().equals(studentId) && score.getSubjectId().equals(subjectId)) {
                System.out.println("회차: " + score.getRound() + ", 점수: " + score.getScore() + ", 등급: " + score.getGrade());
                exist3 = true;
            }
        }

        if (!exist3) {
            System.out.printf("해당 수강생의 점수가 비어있습니다.");
        }

    }



    public void calculateAvgGrade(Student student) {
        try {
            //과목별 평균 등급 조회 - 특정 과목별 1~10회차 의 합 / 시행한 회차
            //inquiredSubjectList에는 학생이 수강신청한 과목이 순서대로
            List<Subject> inquiredSubjectList = student.getSubjectList();
            int[] avgScore = new int[inquiredSubjectList.size()];
            char[] avgGrade = new char[inquiredSubjectList.size()];
            for (int i = 0; i < avgGrade.length; i++) {
                avgGrade[i] = ' ';
            }

            //inquiredScoreList는 inquiredSubjectList를 하나 꺼낼때마다 그 과목의 회차별 점수를 저장.
            for (int i = 0; i < inquiredSubjectList.size(); i++) {
                Subject inquiredSubject = inquiredSubjectList.get(i);
                //꺼낸 과목의 회차 별 Score 누적 합을 저장할 변수 sum, 회차 수를 판단할 변수 count - 이 둘은 판단할 과목마다 초기화되어야함
                int sum = 0;
                int count = 0;
                String subjecttype = inquiredSubject.getSubjectType();

                // score 저장소를 탐색
                for (int j = 0; j < scoreStore.size(); j++) {
                    Score iterScore = scoreStore.get(j);

                    // 순서대로 꺼낸 iterScore의 과목 코드와 inquiredSubject의 과목코드가 일치하고, 학생 코드 값이 일치하면 카운트
                    if (iterScore.getSubjectId().equals(inquiredSubject.getSubjectId()) && iterScore.getStudentId().equals(student.getStudentId())) {
                        sum += iterScore.getScore();
                        count++;
                    }

                }

                //평균값 저장 0으로 나누는 경우에 대한 처리는 if문 이외에는 불필요.
                if (count != 0) {
                    avgScore[i] = sum / count;
                    if (subjecttype.equals("MANDATORY")) {
                        if (avgScore[i] >= 95 && avgScore[i] <= 100) {
                            avgGrade[i] = 'A';
                        } else if (avgScore[i] >= 90 && avgScore[i] <= 94) {
                            avgGrade[i] = 'B';
                        } else if (avgScore[i] >= 80 && avgScore[i] <= 89) {
                            avgGrade[i] = 'C';
                        } else if (avgScore[i] >= 70 && avgScore[i] <= 79) {
                            avgGrade[i] = 'D';
                        } else if (avgScore[i] >= 60 && avgScore[i] <= 69) {
                            avgGrade[i] = 'F';
                        } else {
                            avgGrade[i] = 'N';
                        }
                    } else {
                        if (avgScore[i] >= 90 && avgScore[i] <= 100) {
                            avgGrade[i] = 'A';
                        } else if (avgScore[i] >= 80 && avgScore[i] <= 89) {
                            avgGrade[i] = 'B';
                        } else if (avgScore[i] >= 70 && avgScore[i] <= 79) {
                            avgGrade[i] = 'C';
                        } else if (avgScore[i] >= 60 && avgScore[i] <= 69) {
                            avgGrade[i] = 'D';
                        } else if (avgScore[i] >= 50 && avgScore[i] <= 59) {
                            avgGrade[i] = 'F';
                        } else {
                            avgGrade[i] = 'N';
                        }

                    }
                }

            }
            System.out.println("학생의 과목별 평균 등급을 조회합니다");
            System.out.println("==================================");
            //저장된 과목 리스트와 평균값 출력
            //두 저장소는 동일한 길이를 가짐
            for (int i = 0; i < avgScore.length; i++) {
                System.out.println(inquiredSubjectList.get(i).getSubjectName() + "의 평균 점수 : " + avgScore[i]);
                System.out.println(inquiredSubjectList.get(i).getSubjectName() + "의 평균 등급 : " + avgGrade[i]);
            }


        } catch (Exception e) {
            System.out.println("수강생의 과목별 평균 등급을 구하는 과정에 문제가 발생했습니다.");
            System.out.println("이전 단계로 돌아갑니다.");
            return;
        }

    }
}
