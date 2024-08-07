package camp;

import camp.controller.ScoreDAO;
import camp.controller.StudentDAO;
import camp.controller.SubjectDAO;
import camp.controller.modelDAO;
import camp.model.Score;
import camp.model.Student;
import camp.model.Subject;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * Notification
 * Java, 객체지향이 아직 익숙하지 않은 분들은 위한 소스코드 틀입니다.
 * main 메서드를 실행하면 프로그램이 실행됩니다.
 * model 의 클래스들과 아래 (// 기능 구현...) 주석 부분을 완성해주세요!
 * 프로젝트 구조를 변경하거나 기능을 추가해도 괜찮습니다!
 * 구현에 도움을 주기위한 Base 프로젝트입니다. 자유롭게 이용해주세요!
 */


//git branch dev

public class CampManagementApplication {
    //Dao 관리 필드
    private static ScoreDAO scoredao = new ScoreDAO();
    private static StudentDAO studentdao = new StudentDAO();

    // 스캐너
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        try {
            displayMainView();
        } catch (Exception e) {
            System.out.println("\n오류 발생!\n프로그램을 종료합니다.");
        }
    }

    private static void displayMainView() throws InterruptedException {

        boolean flag = true;
        while (flag) {
            System.out.println("\n==================================");
            System.out.println("내일배움캠프 수강생 관리 프로그램 실행 중...");
            System.out.println("1. 수강생 관리");
            System.out.println("2. 점수 관리");
            System.out.println("3. 프로그램 종료");
            System.out.print("관리 항목을 선택하세요...");
            int input = sc.nextInt();

            switch (input) {
                case 1 -> displayStudentView(); // 수강생 관리
                case 2 -> displayScoreView(); // 점수 관리
                case 3 -> flag = false; // 프로그램 종료
                default -> {
                    System.out.println("잘못된 입력입니다.\n되돌아갑니다!");
                    Thread.sleep(2000);
                }
            }
        }
        System.out.println("프로그램을 종료합니다.");
    }

    private static void displayStudentView() {
        boolean flag = true;
        while (flag) {
            System.out.println("==================================");
            System.out.println("수강생 관리 실행 중...");

            System.out.println("0. 수강생 상태");
            System.out.println("1. 수강생 등록");
            System.out.println("2. 수강생 목록 조회");
            System.out.println("3. 수강생 정보 수정");
            System.out.println("4. 수강생 정보 조회");
            System.out.println("5. 수강생 정보 삭제");
            System.out.println("6. 수강생 상태 목록 조회");
            System.out.println("7. 메인 화면 이동");
            System.out.print("관리 항목을 선택하세요...");
            int input = sc.nextInt();

            switch (input) {
                case 0 -> conditionStudent();           // 수강생 컨디션 확인
                case 1 -> createStudent();              // 수강생 등록
                case 2 -> inquireStudent();             // 수강생 목록 조회
                case 3 -> updateStudent();              // 수강생 정보 수정
                case 4 -> getStudentInfo();             // 수강생 정보 조회
                case 5 -> deleteStudent();              // 수강생 삭제
                case 6 -> inquireCondition();           // 수강생 상태목록조회
                case 7 -> flag = false;                 // 메인 화면 이동
                default -> {
                    System.out.println("잘못된 입력입니다.\n메인 화면 이동...");
                    flag = false;
                }
            }
        }
    }


    //수강생 컨디션 확인 (0)
    private static void conditionStudent() {

        //학생 코드 입력

        //입력 코드 값을 바탕으로 studentdao.readModel()로 탐색 학생 검색

        //조회 가능하면 getCondition() 출력

        //조회가 안될때의 처리
        studentdao.readConditionStudent();
    }


    // 수강생 등록(1)
    private static void createStudent() {
        System.out.println("\n수강생을 등록합니다...");
        System.out.print("수강생 이름 입력: ");
        String studentName = sc.next();
        studentdao.createModel(studentName);
    }

    // 수강생 목록 조회(2)
    private static void inquireStudent() {
        //key값 없이 DAO의 studentstore 값 조회.
        studentdao.readModelAll();
    }


    // 수강생 정보 수정(3)
    private static void updateStudent() {
        //학생 코드 입력받기
        System.out.println("\n수강생 정보를 수정합니다...");
        System.out.println("수강생 번호는 ‘ST’로 시작합니다.");
        System.out.print("\n관리할 수강생의 번호를 입력하시오..(ST로 시작합니다.)");
        String studentId = sc.next();
        //학생 코드를 key 값으로 studentdao의 학생 리스트 불러오기
        //studentStore에서 해당 studentId를 가진 수강생이 있는지 확인
        List<Student> studentList = studentdao.getAllModelList();
        Student stuname = null;
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getStudentId().equals(studentId)) {
                stuname = studentList.get(i);
                break;
            }
        }
        // 해당 ID를 가진 수강생을 찾지 못한 경우
        if (stuname == null) {
            System.out.println("수강생을 찾을 수 없습니다.");
            return;
        } else {
            System.out.println("수강생을 찾았습니다.");
        }

        //수정할 학생 정보 입력
        System.out.println("수정할 항목을 입력하세요…(이름 또는 상태를 입력하세요.)");
        String change = sc.next();

        switch (change) {
            case "이름" -> {
                while (true) {
                    System.out.println("수정할 이름을 입력하세요…");
                    String newName = sc.next();
                    if (newName.isEmpty()) {
                        System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
                    } else {
                        studentdao.updateModel(change, newName, studentId);
                        System.out.println("이름 수정 성공!");
                        break;
                    }
                }
            }
            case "상태" -> {
                while (true) {
                    System.out.println("수정할 상태를 입력하세요 (Green, Red, Yellow): ");
                    String newCondition = sc.next();
                    if (newCondition.equals("Green") || newCondition.equals("Red") || newCondition.equals("Yellow")) {

                        studentdao.updateModel(change, newCondition, studentId);
                        System.out.println("상태 수정 성공!");
                        System.out.println(stuname.getCondition());
                        break;
                    } else {
                        System.out.println("잘못된 상태 입력입니다. 다시 입력해주세요.");
                    }
                }
            }
            default -> {
                System.out.println("잘못된 입력입니다.");
            }
        }


    }


    // 수강생 정보 조회(4)
    private static void getStudentInfo() {
        //학생 코드 입력
        Scanner sc = new Scanner(System.in);
        System.out.print("정보를 조회할 수강생의 번호를 입력하세요 :(ST로 시작합니다.) ");
        String studentId = sc.next();
        //입력 코드값으로 studentdao.readModel()로 검색한 학생 정보 조회
        studentdao.readModel(studentId);
    }

    // 수강생 삭제(5)
    private static void deleteStudent() {
        //StudentDAO에서 수강생 객체 삭제하기
        //학생 코드 입력받기
        System.out.println("\n수강생을 삭제합니다...");
        System.out.println("삭제할 수강생 번호 입력: (ST로 시작합니다.)");
        String studentId = sc.next();

        //studentdao에서 삭제에 성공했다면 (수강생이 존재했다면)
        if (studentdao.deleteModel(studentId)) {
            //scoredao를 호출하여 studentId key를 가지는 Score 객체를 scoreStore에서 삭제
            if (scoredao.deleteModelAll(studentId)) {
                System.out.println("수강생 점수 기록 삭제 성공");
            } else {
                System.out.println("해당 수강생의 삭제할 점수 정보가 존재하지 않습니다.");
            }
        } else {
            System.out.println("삭제할 수강생 정보가 존재하지 않습니다.");
        }
    }

    // 상태별 수강생 목록 조회(6)
    private static void inquireCondition() {
        //key 값을 입력받음.
        System.out.print("조회할 상태를 입력하세요…(Green, Red, Yellow) : ");
        String condition = sc.next();
        while (true) {
            if (condition.equalsIgnoreCase("Green") || condition.equalsIgnoreCase("Red") || condition.equalsIgnoreCase("Yellow")) {
                break;
            } else {
                System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
                break;
            }
        }

        //입력받은 상태정보 key 값으로 DAO를 사용하여 검색을 진행.
        List<Student> studentStore = studentdao.inquireConditionList(condition);

        //검색 결과가 없으면 빈 ArrayList가 반환되므로 컬렉션의 길이가 0
        if (studentStore.size() > 0) {
            for (Student student : studentStore) {
                System.out.println("수강생 번호: " + student.getStudentId());
                System.out.println("이름: " + student.getStudentName());
                System.out.println("상태: " + student.getCondition());
            }
        } else {
            System.out.println("수강생을 찾을 수 없습니다.");
        }

    }


    //점수 관리 메뉴 화면
    private static void displayScoreView() {
        boolean flag = true;
        while (flag) {
            System.out.println("==================================");
            System.out.println("점수 관리 실행 중...");
            System.out.println("1. 수강생의 과목별 시험 회차 및 점수 등록");
            System.out.println("2. 수강생의 과목별 회차 점수 수정");
            System.out.println("3. 수강생의 특정 과목 회차별 등급 조회");
            System.out.println("4. 수강생의 과목별 평균 등급 조회");
            System.out.println("5. 메인 화면 이동");
            System.out.print("관리 항목을 선택하세요...");
            int input = sc.nextInt();

            switch (input) {
                case 1 -> createScore();                    // 수강생의 과목별 시험 회차 및 점수 등록
                case 2 -> updateRoundScoreBySubject();      // 수강생의 과목별 회차 점수 수정
                case 3 -> inquireRoundGradeBySubject();     // 수강생의 특정 과목 회차별 등급 조회
                case 4 -> inquireAverageGradeBySubject();   // 수강생의 과목별 평균 등급 조회
                case 5 -> flag = false; // 메인 화면 이동
                default -> {
                    System.out.println("잘못된 입력입니다.\n메인 화면 이동...");
                    flag = false;
                }
            }
        }
    }

    private static String getStudentId() {
        System.out.print("\n관리할 수강생의 번호를 입력하시오..(ST로 시작합니다.) : ");
        return sc.next();
    }


    // 수강생의 과목별 시험 회차 및 점수 등록                                   **** 0806 1차 리팩토링 주의부분 ****
    private static void createScore() {
        Scanner sc = new Scanner(System.in);
        String studentId;
        String subjectID;
        List<Subject> subjectList;

        // 수강생 코드 입력 검색.
        while (true) {
            System.out.print("\n관리할 수강생의 번호를 입력하시오..(ST로 시작합니다.) : ");
            studentId = sc.next();// 관리할 수강생 고유 번호

            try {
                //studentdao를 사용하여 입력받은 학생 코드값을 가지는 학생을 studentStore에서 검색
                Student student = studentdao.getModel(studentId);
                //null이면 예외처리
                if (student == null) {
                    throw new Exception("수강생 코드가 존재하지 않습니다.");
                }
                //null이 아니면 과목리스트를 가져옴
                System.out.println("수강생 정보 검색 완료!");
                subjectList = student.getSubjectList();

            } catch (Exception exception) {
                System.out.println("에러메세지:" + exception.getMessage());
                continue;
            }

            System.out.println("--수강 중인 과목 리스트--");
            //점수를 입력할 과목 코드를 입력합니다.
            for (Subject subject : subjectList) {
                System.out.print(subject.getSubjectName() + " ");
            }
            System.out.println();
            System.out.println("-------------------------------------------");
            System.out.print("과목 코드를 입력하세요(SU로 시작 최대 9번까지.) : ");
            subjectID = sc.next();

            //입력한 과목코드가 가져온 과목 코드 리스트 안에 포함됨다면 다음으로, 아니라면 다시 돌아감.
            try {
                boolean flag = false;
                for (int i = 0; i < subjectList.size(); i++) {
                    if (subjectList.get(i).getSubjectId().equals(subjectID)) {
                        System.out.println("과목 코드 검색 성공!");
                        System.out.println("입력한 과목명 : " + subjectList.get(i).getSubjectName() + ", 과목 코드 : " + subjectList.get(i).getSubjectId());
                        flag = true;
                        break;
                        //선택 과목만 가능하게 하기.
                        //IF 학생이 선택한 과목이 맞다면....
                    }
                }
                if (!flag) {
                    throw new Exception("수강신청을 하지 않았습니다.");
                }
            } catch (Exception exception) {
                System.out.println("에러메세지:" + exception.getMessage());
                continue;
            }

            //여기에서 dao를 호출하여 String 가변인자로 저장해야 할 studentId, subjectId값을 전달.
            scoredao.createModel(studentId, subjectID);
            break;
        }

    }

    // 수강생의 과목별 회차 점수 수정
    private static void updateRoundScoreBySubject() {

        //수강생 검색    -   studentdao.getModel() 사용
        String studentId = getStudentId();
        Student student = studentdao.getModel(studentId);

        // 기능 구현 (수정할 과목 및 회차, 점수)
        System.out.print("수정할 과목코드을 입력하세요: ");
        String subjectId = sc.next();

        //조회한 Student 객체의 메소드를 호출하여 해당 학생이 신청한 수강 정보 리스트를 불러옴
        List<Subject> subjectList = student.getSubjectList();

        //수정할 과목코드가 리스트에 존재하는지 체크
        boolean exist2 = false;
        for (int i = 0; i < subjectList.size(); i++) {
            if (subjectList.get(i).getSubjectId().equals(subjectId)) {
                exist2 = true;
                break;
            }
        }
        if (!exist2) {
            System.out.println("과목을 찾을 수 없습니다.");
            return;
        } else {
            System.out.println("과목을 찾았습니다.");
        }

        // 선택된 학생(학생코드를 전달)의 특정 과목에 대한 회차, 점수 출력
        scoredao.inquireRoundScore(studentId, subjectId);

        //수정할 회차 입력
        int round;
        while (true) {
            System.out.print("수정할 회차를 입력하세요: ");
            round = sc.nextInt();
            if (round > 10 || round < 1) {
                System.out.println("잘못된 회차 입력입니다.");
                continue;
            }
            break;
        }

        //수정할 점수 입력
        int newScore;
        while (true) {
            System.out.print("수정할 점수를 입력하세요: ");
            newScore = sc.nextInt();
            if (newScore > 100 || newScore < 1) {
                System.out.println("잘못된 점수 입력입니다.");
                continue;
            }
            break;
        }

        //점수수정
        //학생코드와 회차 번호를 key로 검색
        //UPDATE 함수 기능 부분
        scoredao.updateModel(round + "", newScore + "", studentId, subjectId);     //Score 객체 하나에 대한 기능.

    }

    // 수강생의 특정 과목 회차별 등급 조회
    private static void inquireRoundGradeBySubject() {
        // 기능 구현 (조회할 특정 과목)
        //해당 학생Id를 기본키로 데이터 조회 - 그 학생Id의 특정 과목에 대한 모든 데이터를 가져와야함.
        //가져온 Score인스턴스들을 담을 저장소
        List<Score> inquiredScoreList = scoredao.getAllModelList();

        //가져온 데이터 리스트를 순회하며 출력 - 조회 형식 : 자유
        for (int i = 0; i < inquiredScoreList.size(); i++) {
            Score score = inquiredScoreList.get(i);
            System.out.println("회차 정보 : " + score.getRound());
            System.out.println("등급 정보 : " + score.getGrade());
        }
        System.out.println("\n등급 조회 성공!");
    }

    // 수강생의 과목별 평균 등급 조회
    private static void inquireAverageGradeBySubject() {
        // 기능 구현 (조회할 특정 학생)
        // 조회(관리)할 수강생의 고유 번호
        String studentId = getStudentId();

        //studentStore에서 해당 studentId를 가진 수강생이 있는지 확인
        Student student = studentdao.getModel(studentId);

        // 해당 ID를 가진 수강생을 찾지 못한 경우
        if (student == null) {
            System.out.println("수강생을 찾을 수 없습니다.");
            return;
        } else {
            System.out.println("수강생을 찾았습니다.");
        }
        System.out.println();

        //검색한 학생 객체를 전달
        System.out.println("과목별 평균 등급을 조회할 학생 : " + student.getStudentName());
        scoredao.calculateAvgGrade(student);

    }

}
