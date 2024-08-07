package camp.controller;

import camp.model.Score;
import camp.model.Student;
import camp.model.Subject;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentDAO implements modelDAO {
    //DB연동이 없기 때문에 데이터는 각 모델의 DAO 클래스가 직접 저장을 합니다.
    //원래는 DAO 클래스를 싱글톤 패턴으로 하나의 인스턴스만 존재하게 구현되게 하여 저장소의 무결성을 보증하는데
    //현재의 프로그램 구조 상 두개 이상의 인스턴스화가 생길 여지가 없기때문에 싱글톤 패턴은 제외합니다.
    private final List<Student> studentStore = new ArrayList<>();

    private static int studentIndex;
    private static final String INDEX_TYPE_STUDENT = "ST";


    // 과목 타입(필수와 선택)
    private static String SUBJECT_TYPE_MANDATORY = "MANDATORY";
    private static String SUBJECT_TYPE_CHOICE = "CHOICE";


    //0806 ST10 찍히는 이유 여기의 sequence 호출이 원인. 얘네가 9로 만들어버린다.
    // 과목코드 값에 SU가 아닌 ST가 들어감.
    // 선택과목 - SU6 = "디자인 패턴"  SU9 = "MongoDB" - 결국 enum이나 마찬가지 상수값,
    List<Subject> subjectStore = List.of(new Subject("SU1", "Java", SUBJECT_TYPE_MANDATORY), new Subject("SU2", "객체지향", SUBJECT_TYPE_MANDATORY),
            new Subject("SU3", "Spring", SUBJECT_TYPE_MANDATORY), new Subject("SU4", "JPA", SUBJECT_TYPE_MANDATORY),
            new Subject("SU5", "MySQL", SUBJECT_TYPE_MANDATORY),
            new Subject("SU6", "디자인 패턴", SUBJECT_TYPE_CHOICE), new Subject("SU7", "Spring Security", SUBJECT_TYPE_CHOICE),
            new Subject("SU8", "Redis", SUBJECT_TYPE_CHOICE), new Subject("SU9", "MongoDB", SUBJECT_TYPE_CHOICE));


    //interface에 맞게 수정해야함
    @Override
    public void createModel(String... studentName) {
        Scanner sc = new Scanner(System.in);
        //등록할 학생이 수강할 과목 리스트
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
                    System.out.print("선택한 과목 코드 :");
                    for (Subject s : subjectList) {
                        System.out.print(" " + s.getSubjectId());
                    }
                    System.out.println();
                    System.out.println("=======필수 과목 코드=======");
                    System.out.println("SU1  SU2   SU3   SU4  SU5");
                }
                String mandatoryCode = sc.next();
                //정해진 과목 코드 이외의 문자 입력시 예외 throw
                if (mandatoryCode.equals("q") || mandatoryCode.equals("Q")) {
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
                if (count_choice >= 2) {
                    System.out.println("최소한의 조건을 만족하는 선택 과목을 선택했습니다. 입력을 종료하려면 q를 입력해주세요...");
                    System.out.println("선택 과목 코드를 입력하세요…");
                    System.out.println("=======선택 과목 코드=======");
                    System.out.println("SU6    SU7    SU8    SU9");
                } else {
                    System.out.println("선택 과목 코드를 입력하세요…");
                    System.out.print("선택한 과목 코드 :");
                    for (Subject s : subjectList) {
                        System.out.print(" " + s.getSubjectId());
                    }
                    System.out.println();
                    System.out.println("=======선택 과목 코드=======");
                    System.out.println("SU6    SU7    SU8    SU9");
                }

                String choiceCode = sc.next();
                //정해진 과목 코드 이외의 문자 입력시 예외 throw
                if (choiceCode.equals("q") || choiceCode.equals("Q")) {
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

        Student student = new Student(sequence(INDEX_TYPE_STUDENT), studentName[0], subjectList); // 수강생 인스턴스 생성 예시 코드

        // 기능 구현
        studentStore.add(student);
        System.out.println("수강생 등록 성공!\n");

    }

    //Key : 학생코드 value : 한명             -- 함수 기능 분리 필요 수강생 한명의 정보만 가져오는것.
    @Override
    public void readModel(String studentId) {
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

    //Key : 학생코드, 이름 || 상태 정보를 수정.
    @Override
    public void updateModel(String key, String value, String... id) {
        //학생 찾아서 정보 수정  id[0]
        Student stuname = null;
        for (int i = 0; i < studentStore.size(); i++) {
            if (studentStore.get(i).getStudentId().equals(id[0])) {
                stuname = studentStore.get(i);
                break;
            }
        }
        if (key.equals("이름")) {
            stuname.setStudentName(value);
        } else {
            stuname.setCondition(Student.condition.valueOf(value));
        }

    }

    @Override
    public boolean deleteModel(String studentId) {
        // studentStore에서 제거
        // 해당 수강생을 studentStored에서 제거
        boolean removed = studentStore.removeIf(student -> student.getStudentId().equals(studentId));

        // 점수 기록 부분도 삭제가 되야되는데...? -> main으로 studentDAO의 처리 성공 여부 boolean 값을 리턴
        if (removed) {
            System.out.println("수강생 기록 삭제 성공!\n");
        } else {
            System.out.println("해당 번호의 수강생을 찾을 수 없습니다.\n");
            return false;

        }

        return removed;
    }

    @Override
    public List<Student> getAllModelList() {
        return studentStore;
    }

    //Key : none, DAO의 studentStore 정보 가져오기
    public void readModelAll() {
        System.out.println("\n수강생 목록을 조회합니다...");
        // 기능 구현
        if (studentStore.size() != 0) {
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
        } else {
            System.out.println("\n현재 등록된 수강생 정보가 없습니다.");
        }

    }

    public boolean deleteModelAll(String studentId) {
        return true;
    }

    public void readConditionStudent() {
        Scanner sc = new Scanner(System.in);
        System.out.println("수강생의 컨디션을 확인합니다...");
        System.out.println("수강생 번호는 ‘ST’로 시작합니다.");
        System.out.print("수강생 번호 입력: ");
        String studentId = sc.next();

        boolean exist = false;
        for (Student i : studentStore) {
            if (i.getStudentId().equals(studentId)) {
                System.out.println("수강생을 찾았습니다.");
                exist = true;
                System.out.println(i.getStudentName() + "의 현재 상태 정보 : " + i.getCondition());
                break;
            }
        }
        if (!exist) {
            System.out.println("수강생을 찾을 수 없습니다.");
        }

    }


    //studentID 값을 가지는 수강생 객체를 반환
    public Student getModel(String studentId) {
        Student student = null;
        for (Student element : studentStore) {
            if (element.getStudentId().equals(studentId)) {
                student = element;
                return student;
            }
        }
        return student;
    }

    //인자로 전달받은 condition 값을 가지는 Student 리스트를 리턴.
    public List<Student> inquireConditionList(String condition) {

        boolean found = false;
        //반환할 수강생 리스트
        List<Student> inquiredStudentList = new ArrayList<>();

        for (Student student : studentStore) {
            if (student.getCondition().name().equalsIgnoreCase(condition)) {
                if (!found) {
                    System.out.println(condition + " 상태 수강생 목록을 조회합니다...");
                }
                inquiredStudentList.add(student);
                found = true;
            }
        }
        //false = 수강생 목록이 비어있음
        if (!found) {
            System.out.println("수강생을 찾을 수 없습니다.");
        }
        return inquiredStudentList;
    }


    //index 자동 증가 함수    -- subject랑 student 구분 생각해봐야함.
    //@Override
    public String sequence(String type) {
        studentIndex++;
        return INDEX_TYPE_STUDENT + studentIndex;
    }

}
