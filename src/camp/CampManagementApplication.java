package camp;

import camp.model.Score;
import camp.model.Student;
import camp.model.Subject;

import java.net.SecureCacheResponse;
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
    // 데이터 저장소
    private static List<Student> studentStore;
    private static List<Subject> subjectStore;
    private static List<Score> scoreStore;

    // 과목 타입(필수와 선택)
    private static String SUBJECT_TYPE_MANDATORY = "MANDATORY";
    private static String SUBJECT_TYPE_CHOICE = "CHOICE";

    // index 관리 필드
    private static int studentIndex;
    private static final String INDEX_TYPE_STUDENT = "ST";
    private static int subjectIndex;
    private static final String INDEX_TYPE_SUBJECT = "SU";
    private static int scoreIndex;
    private static final String INDEX_TYPE_SCORE = "SC";

    //몇개의 과목을 담아놓을지.
    private List<String> strSubject;

    // 스캐너
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        setInitData();
        try {
            displayMainView();
        } catch (Exception e) {
            System.out.println("\n오류 발생!\n프로그램을 종료합니다.");
        }
    }

    // 초기 데이터 생성
    private static void setInitData() {

        studentStore = new ArrayList<>();

        // 선택과목 - SU6 = "디자인 패턴"  SU9 = "MongoDB"
        subjectStore = List.of(new Subject(sequence(INDEX_TYPE_SUBJECT), "Java", SUBJECT_TYPE_MANDATORY), new Subject(sequence(INDEX_TYPE_SUBJECT), "객체지향", SUBJECT_TYPE_MANDATORY),
                new Subject(sequence(INDEX_TYPE_SUBJECT), "Spring", SUBJECT_TYPE_MANDATORY), new Subject(sequence(INDEX_TYPE_SUBJECT), "JPA", SUBJECT_TYPE_MANDATORY),
                new Subject(sequence(INDEX_TYPE_SUBJECT), "MySQL", SUBJECT_TYPE_MANDATORY),
                new Subject(sequence(INDEX_TYPE_SUBJECT), "디자인 패턴", SUBJECT_TYPE_CHOICE), new Subject(sequence(INDEX_TYPE_SUBJECT), "Spring Security", SUBJECT_TYPE_CHOICE),
                new Subject(sequence(INDEX_TYPE_SUBJECT), "Redis", SUBJECT_TYPE_CHOICE), new Subject(sequence(INDEX_TYPE_SUBJECT), "MongoDB", SUBJECT_TYPE_CHOICE));

        scoreStore = new ArrayList<>();
        //Student student = new Student(sequence(INDEX_TYPE_STUDENT), "bo"); // 수강생 인스턴스 생성 예시 코드
        //studentStore.add(student);
        Score scoreID = new Score(sequence(INDEX_TYPE_SCORE), "ST1", 1, 95, "SU1");
        scoreStore.add(scoreID);
    }

    // index 자동 증가
    private static String sequence(String type) {
        switch (type) {
            case INDEX_TYPE_STUDENT -> {
                studentIndex++;
                return INDEX_TYPE_STUDENT + studentIndex;
            }
            case INDEX_TYPE_SUBJECT -> {
                subjectIndex++;
                return INDEX_TYPE_SUBJECT + subjectIndex;
            }
            default -> {
                scoreIndex++;
                return INDEX_TYPE_SCORE + scoreIndex;
            }
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
                case 0 -> conditionStudent(); //수강생 컨디션 확인
                case 1 -> createStudent(); // 수강생 등록
                case 2 -> inquireStudent(); // 수강생 목록 조회
                case 3 -> updateStudent();//수강생 정보 수정
                case 4 -> getStudentInfo(); //수강생 정보 조회
                case 5 -> deleteStudent(); // 수강생 삭제
                case 6 -> inquireCondition(); //수강생 상태목록조회
                case 7 -> flag = false; // 메인 화면 이동
                default -> {
                    System.out.println("잘못된 입력입니다.\n메인 화면 이동...");
                    flag = false;
                }
            }
        }
    }
    //수강생 컨디션 확인 (0)
    private static void conditionStudent() {
        System.out.println("수강생의 컨디션을 확인합니다...");
        System.out.println("수강생 번호는 ‘ST’로 시작합니다.");
        System.out.print("수강생 번호 입력: ");
        String studentId = sc.next();

        boolean exist = false;
        for (Student i : studentStore) {
            if (i.getStudentId().equals(studentId)) {
                exist = true;
                System.out.println(i.getCondition());
                break;
            }
        }
        if (!exist) {
            System.out.println("수강생을 찾을 수 없습니다.");
        } else {
            System.out.println("수강생을 찾았습니다.");
        }
    }

    // 수강생 등록 (1)
    private static void createStudent() {
        System.out.println("\n수강생을 등록합니다...");
        System.out.print("수강생 이름 입력: ");
        String studentName = sc.next();
        List<Subject> subjectList = new ArrayList<>();
        System.out.println("최소 3개 이상의 필수 과목과 2개 이상의 선택 과목을 선택하세요…");
        //선택한 필수 과목 갯수
        int count_mandatory = 0;
        while (true) {
            try {
                //입력받은 과목이 3개 이상일 경우 선택과목으로 넘어갈 수 있는 입력을 받음
                if (count_mandatory >= 3) {
                    System.out.println("최소한의 조건을 만족하는 필수 과목을 선택했습니다. 선택 과목으로 넘어가려면 ‘q’를 입력하세요…");
                    System.out.println("=======필수 과목 코드=======");
                    System.out.println("SU1  SU2   SU3   SU4  SU5");
                } else {
                    System.out.println("필수 과목 코드를 입력하세요…");
                    System.out.println("=======필수 과목 코드=======");
                    System.out.println("SU1  SU2   SU3   SU4  SU5");
                }
                String mandatoryCode = sc.next();
                //정해진 과목 코드 이외의 문자 입력시 예외 throw
                if(mandatoryCode.equals("q")||mandatoryCode.equals("Q")) {
                    System.out.println("q를 입력받았습니다, 선택 과목 입력으로 넘어갑니다.");
                    break;
                }
                if (!mandatoryCode.equals("SU1") && !mandatoryCode.equals("SU2") && !mandatoryCode.equals("SU3") && !mandatoryCode.equals("SU4") && !mandatoryCode.equals("SU5")) {
                    throw new IllegalArgumentException("잘못된 과목 코드 입력입니다.");
                }
                int subjectIdx = mandatoryCode.charAt(2) - '0';
                //고유 번호는 idx+1값을 가짐
                Subject subject = subjectStore.get(subjectIdx - 1);
                subjectList.add(subject);
                count_mandatory++;
                //필수 과목은 최대 5종류
                //입력 최소 조건인 3개를 입력할때까지와, 3개~5개를 입력한 경우를 나눠 메시지 출력.ㄴ
                if (count_mandatory == 5) {
                    System.out.println("필수 과목 5개를 모두 선택했습니다.");
                    System.out.print("선택한 과목 코드 :");
                    for (Subject s : subjectList) {
                        System.out.print(" " + s.getSubjectId());
                    }
                    System.out.println();
                    System.out.println("선택 과목 선택창으로 넘어갑니다.");
                    break;
                } else if (count_mandatory < 3) {
                    System.out.println("필수 과목을 선택했습니다.");
                    continue;
                } else {
                    //필수 과목이 3개 이상 5개 미만이면 저장된 과목 목록을 출력.
                    System.out.println("현재까지 선택한 필수 과목은 " + count_mandatory + "개 입니다. 최대 5개 까지 선택할 수 있습니다.");
                    System.out.print("선택한 과목 코드 :");
                    for (Subject s : subjectList) {
                        System.out.print(" " + s.getSubjectId());
                    }
                    System.out.println();
                    continue;
                }
            } catch (IllegalArgumentException e) {
                //필수 과목 이외에 잘못된 코드 입력시 재입력
                System.out.println(e.getMessage());
                continue;
            }
        }
        int count_choice = 0;
        while (true) {
            try {
                //입력받은 과목이 2개 이상일 경우 입력을 종료할 수 있는 입력을 받음
                if (count_choice >= 3) {
                    System.out.println("최소한의 조건을 만족하는 필수 과목을 선택했습니다. 입력을 종료하려면 q를 입력해주세요...");
                    System.out.println("선택 과목 코드를 입력하세요…");
                    System.out.println("=======선택 과목 코드=======");
                    System.out.println("SU6    SU7    SU8    SU9");
                } else {
                    System.out.println("선택 과목 코드를 입력하세요…");
                    System.out.println("=======선택 과목 코드=======");
                    System.out.println("SU6    SU7    SU8    SU9");
                }

                String choiceCode = sc.next();
                //정해진 과목 코드 이외의 문자 입력시 예외 throw
                if(choiceCode.equals("q")||choiceCode.equals("Q")) {
                    System.out.println("q를 입력받았습니다, 선택 과목 입력을 종료합니다...");
                    break;
                }
                if (!choiceCode.equals("SU6") && !choiceCode.equals("SU7") && !choiceCode.equals("SU8") && !choiceCode.equals("SU9")) {
                    throw new IllegalArgumentException("잘못된 과목 코드 입력입니다.");
                }

                int subjectIdx = choiceCode.charAt(2) - '0';
                Subject subject = subjectStore.get(subjectIdx - 1);
                subjectList.add(subject);
                count_choice++;
                //선택 과목은 최대 4종류.
                if (count_choice == 4) {
                    System.out.println("선택 과목 4개를 모두 선택했습니다.");
                    System.out.print("선택한 과목 코드 :");
                    for (Subject s : subjectList) {
                        System.out.print(" " + s.getSubjectId());
                    }
                    System.out.println();
                    break;
                } else if (count_choice < 2) {
                    System.out.println("선택 과목을 선택했습니다.");
                    continue;
                } else {
                    System.out.println("입력한 선택 과목은 " + count_choice + "개 입니다, 최대 4개까지 선택할 수 있습니다.");
                    System.out.print("선택한 과목 코드 :");
                    for (Subject s : subjectList) {
                        System.out.print(" " + s.getSubjectId());
                    }
                    System.out.println();
                    continue;
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                continue;
            }

        }

        System.out.println("과목 입력이 종료되었습니다.");

        Student student = new Student(sequence(INDEX_TYPE_STUDENT), studentName, subjectList); // 수강생 인스턴스 생성 예시 코드

        // 기능 구현
        studentStore.add(student);
        System.out.println("수강생 등록 성공!\n");
    }

    // 수강생 목록 조회(2)
    private static void inquireStudent() {
        System.out.println("\n수강생 목록을 조회합니다...");
        System.out.println("수강생 번호는 ‘ST’로 시작합니다.");
        // 기능 구현
        for (Student element : studentStore) {
            System.out.println("수강생 이름 : " + element.getStudentName());
            System.out.println("수강생 번호 : " + element.getStudentId());
            System.out.print("수강 신청 과목 : ");
            List<Subject> subjectList = element.getSubjectList();
            for (int i = 0; i < subjectList.size(); i++) {
                System.out.print(" " + subjectList.get(i).getSubjectName());
            }
            System.out.println();
        }
        System.out.println("\n수강생 목록 조회 성공!");
    }

    // 수강생 정보를 수정(3)
    private static void updateStudent() {
        System.out.println("\n수강생 정보를 수정합니다...");
        System.out.println("수강생 번호는 ‘ST’로 시작합니다.");
        String studentId = getStudentId();
        //studentStore에서 해당 studentId를 가진 수강생이 있는지 확인
        Student stuname = null;
        for (int i = 0; i < studentStore.size(); i++) {
            if (studentStore.get(i).getStudentId().equals(studentId)) {
                stuname = studentStore.get(i);
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
                        stuname.setStudentName(newName);
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
                        stuname.setCondition(Student.condition.valueOf(newCondition));
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
        System.out.print("정보를 조회할 수강생의 번호를 입력하세요 :(ST로 시작합니다.) ");
        String studentId = sc.next();
        boolean flag = false;
        Student student = null;
        try {
            for (Student element : studentStore) {
                if (studentId.equals(element.getStudentId())) {
                    System.out.println("조회할 수강생의 정보를 찾았습니다.");
                    flag = true;
                    student = element;
                    break;
                }
            }
            //studentStore에 수강생이 없다면 예외 throw후 빠져나옴.
            if (!flag) {
                throw new Exception("조회할 수강생의 정보가 없습니다.");
            }

            //수강생이 있다면, 정보를 출력
            System.out.println("수강생 번호 : " + student.getStudentId());
            System.out.println("수강생 이름 : " + student.getStudentName());
            System.out.println("수강생 상태 : " + student.getCondition());
            System.out.print("수강 신청 과목 :");
            List<Subject> subjectList = student.getSubjectList();
            for (int i = 0; i < subjectList.size(); i++) {
                System.out.print(" " + subjectList.get(i).getSubjectName());
            }
            System.out.println();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("수강생 정보 조회를 마칩니다.");
        }
    }

    // 수강생 삭제(5)
    private static void deleteStudent() {
        System.out.println("\n수강생을 삭제합니다...");
        System.out.println("삭제할 수강생 번호 입력: (ST로 시작합니다.)");
        String studentId = sc.next();

        // 해당 수강생을 studentStored에서 제거 / 점수 기록 부분도 삭제가 되야되는데...?
        boolean removed = studentStore.removeIf(student -> student.getStudentId().equals(studentId));
        if (removed) {
            scoreStore.removeIf(score -> score.getStudentId().equals(studentId));
            System.out.println("수강생 및 점수 기록 삭제 성공!\n");
        } else {
            System.out.println("해당 번호의 수강생을 찾을 수 없습니다.\n");

        }
    }

    // 상태별 수강생 목록 조회(6)
    private static void inquireCondition() {
        System.out.print("조회할 상태를 입력하세요…(Green, Red, Yellow)");
        String condition = sc.next();
        while (true) {
            if (condition.equalsIgnoreCase("Green") || condition.equalsIgnoreCase("Red") || condition.equalsIgnoreCase("Yellow")) {
                break;
            } else {
                System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
                break;
            }
        }

        boolean found = false;
        for (Student student : studentStore) {
            if (student.getCondition().name().equalsIgnoreCase(condition)) {
                if (!found) {
                    System.out.println(condition + " 상태 수강생 목록을 조회합니다...");
                }
                System.out.println("수강생 번호: " + student.getStudentId());
                System.out.println("이름: " + student.getStudentName());
                System.out.println("상태: " + student.getCondition());
                found = true;
            }
        }
        //false = 수강생 목록이 비어있음
        if (!found) {
            System.out.println("수강생을 찾을 수 없습니다.");
        }

    }


    private static void displayScoreView() {
        boolean flag = true;
        while (flag) {
            System.out.println("==================================");
            System.out.println("점수 관리 실행 중...");
            System.out.println("1. 수강생의 과목별 시험 회차 및 점수 등록");
            System.out.println("2. 수강생의 과목별 회차 점수 수정");
            System.out.println("3. 수강생의 특정 과목 회차별 등급 조회");
            System.out.println("4. 메인 화면 이동");
            System.out.print("관리 항목을 선택하세요...");
            int input = sc.nextInt();

            switch (input) {
                case 1 -> createScore(); // 수강생의 과목별 시험 회차 및 점수 등록
                case 2 -> updateRoundScoreBySubject(); // 수강생의 과목별 회차 점수 수정
                case 3 -> inquireRoundGradeBySubject(); // 수강생의 특정 과목 회차별 등급 조회
                case 4 -> flag = false; // 메인 화면 이동
                default -> {
                    System.out.println("잘못된 입력입니다.\n메인 화면 이동...");
                    flag = false;
                }
            }
        }
    }

    private static String getStudentId() {
        System.out.print("\n관리할 수강생의 번호를 입력하시오..(ST로 시작합니다.)");
        return sc.next();
    }

    // 수강생의 과목별 시험 회차 및 점수 등록
    private static void createScore() {
        String studentId;
        String subjectID;
        List<Subject> subjectList = new ArrayList<>();

        int round;
        int score;
        while (true) {
            studentId = getStudentId();// 관리할 수강생 고유 번호
            try{
                for(Student s : studentStore){
                    if (studentId.equals(s.getStudentId())) {
                        System.out.println("수강생 코드 입력 완료!");
                        subjectList = s.getSubjectList();
                    }
                    else {
                        throw new Exception("수강생 코드가 존재하지 않습니다.");
                    }
                }
            }catch (Exception exception){
                System.out.println("에러메세지:" + exception.getMessage());
                continue;
            }
            System.out.print("과목 코드를 입력하세요(SU로 시작 최대 9번까지.) : ");
            subjectID = sc.next();
            try {
                boolean flag = false;
                for (int i = 0; i < subjectList.size(); i++) {
                    if (subjectList.get(i).getSubjectId().equals(subjectID)) {
                        System.out.println("과목 코드 입력 완료!");
                        flag = true;
                        break;
                        //선택 과목만 가능하게 하기.
                        //IF 학생이 선택한 과목이 맞다면....
                    }
                }
                if (!flag){
                    throw new Exception("수강신청을 하지 않았습니다.");
                }
            } catch (Exception exception) {
                System.out.println("에러메세지:" + exception.getMessage());
                continue;
            }
            System.out.print("회차를 입력하세요 (1~10) : ");
            round = sc.nextInt();
            try{
                if(round >=1 && round <=10){
                    System.out.println("회차 입력 완료!");
                }
                else{
                    throw new Exception("회차 정보와 다릅니다.");
                    }
            }
            catch(Exception exception){
                System.out.println("에러메세지:" + exception.getMessage());
                continue;
            }
            System.out.println("점수를 입력하세요 : ");
            score = sc.nextInt();
            try{
                if(score>=0 && score<= 100){
                    System.out.println("점수 입력 완료!");
                    break;
                }
                else{
                    throw new Exception("점수는 0부터 100까지입니다.");
                }
            }catch (Exception exception){
                System.out.println("에러메세지:" + exception.getMessage());
            }
        }
        Score scoreID = new Score(sequence(INDEX_TYPE_SCORE), studentId, round, score, subjectID);
        scoreStore.add(scoreID);

        System.out.println("시험 점수를 등록합니다...");

        System.out.println(scoreID.getGrade());

        System.out.println("\n점수 등록 성공!");


    }

    // 수강생의 과목별 회차 점수 수정
    private static void updateRoundScoreBySubject() {
        String studentId = getStudentId(); // 관리할 수강생 고유 번호
        // 기능 구현 (수정할 과목 및 회차, 점수)
        // studentStore에서 해당 studentId를 가진 수강생이 있는지 확인
        boolean exist = false;
        for (int i = 0; i < studentStore.size(); i++) {
            if (studentStore.get(i).getStudentId().equals(studentId)) {
                exist = true;
                break;
            }
        }
        if (!exist) {
            System.out.println("수강생을 찾을 수 없습니다.");
            return;
        } else {
            System.out.println("수강생을 찾았습니다.");
        }

        System.out.print("수정할 과목코드을 입력하세요: ");
        String subjectId = sc.next();

        boolean exist2 = false;
        for (int i = 0; i < subjectStore.size(); i++) {
            if (subjectStore.get(i).getSubjectId().equals(subjectId)) {
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
        // 선택된 학생의 모든 과목과 회차, 점수 출력
        System.out.println("해당 수강생의 " + subjectId + " 과목 점수 목록:");
        boolean exist3 = false;

        for (int i = 0; i < scoreStore.size(); i++) {
            Score score = scoreStore.get(i);
            if (score.getStudentId().equals(studentId) && score.getSubjectId().equals(subjectId)) {
                System.out.println(", 회차: " + score.getRound() + ", 점수: " + score.getScore() + ", 등급: " + score.getGrade());
                exist3 = true;
            }
        }

        if (!exist3) {
            System.out.printf("해당 수강생의 점수가 비어있습니다.");
        }

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
        boolean update = false;
        for (int i = 0; i < scoreStore.size(); i++) {
            Score score = scoreStore.get(i);
            if (score.getStudentId().equals(studentId) && score.getRound() == round) {
                score.setScore(newScore);
                update = true;
                System.out.println("\n점수 수정 성공!");
                System.out.println("[수정사항] 회차: " + score.getRound() + ", 점수: " + score.getScore() + ", 등급: " + score.getGrade());
                break;
            }
        }
        if (!update) {
            System.out.println("\n해당하는 점수를 찾을 수 없습니다.");
        }
        // 기능 구현
        System.out.println("\n점수 수정 성공!");
    }

    // 수강생의 특정 과목 회차별 등급 조회
    private static void inquireRoundGradeBySubject() {
        // 기능 구현 (조회할 특정 과목)
        // 조회(관리)할 수강생의 고유 번호
        System.out.println("조회할 학생의 고유 번호를 입력해주세요 : ");
        String studentId = getStudentId();
        //과목명이 아니라 과목 코드 입력
        System.out.print("조회할 과목코드를 입력해주세요 : ");
        String subjectId = sc.next();
        sc.nextLine();

        //해당 학생Id를 기본키로 데이터 조회 - 그 학생Id의 특정 과목에 대한 모든 데이터를 가져와야함.
        //가져온 Score인스턴스들을 담을 저장소
        List<Score> inquiredScoreList = new ArrayList<>();

        //학생고유번호와 과목명이 일치하는 모든 회차의 데이터를 가져옴
        try {
            for (int i = 0; i < scoreStore.size(); i++) {
                //학생 고유번호와 과목명이 일치할 때.
                if (scoreStore.get(i).getStudentId().equals(studentId) && scoreStore.get(i).getSubjectId().equals(subjectId)) {
                    Score score = scoreStore.get(i);
                    inquiredScoreList.add(score);
                }
            }
        } catch (Exception e) {
            System.out.println("등급 조회 정보를 불러오는 중 문제가 발생했습니다.");
            e.printStackTrace();
        }
        System.out.println();
        System.out.println("회차별 등급을 조회합니다...");

        //가져온 데이터 리스트를 순회하며 출력 - 조회 형식 : 자유
        for (int i = 0; i < inquiredScoreList.size(); i++) {
            Score score = inquiredScoreList.get(i);
            System.out.println("회차 정보 : " + score.getRound());
            System.out.println("등급 정보 : " + score.getGrade());
        }
        System.out.println();
        // 기능 구현
        System.out.println("\n등급 조회 성공!");
    }

}
