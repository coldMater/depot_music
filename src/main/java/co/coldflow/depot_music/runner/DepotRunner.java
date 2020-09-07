package co.coldflow.depot_music.runner;

import co.coldflow.depot_music.entity.*;
import co.coldflow.depot_music.repository.*;
import co.coldflow.depot_music.service.AccountService;
import co.coldflow.depot_music.service.InstructorService;
import co.coldflow.depot_music.service.ParentService;
import co.coldflow.depot_music.service.StudentService;
import co.coldflow.depot_music.dto.InstructorRequestDto;
import co.coldflow.depot_music.dto.ParentRequestDto;
import co.coldflow.depot_music.dto.StudentRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import javax.transaction.Transactional;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
@Transactional
public class DepotRunner implements ApplicationRunner{
    @Autowired
    DataSource dataSource;
    @Autowired
    InstructorRepository instructorRepository;
    @Autowired
    InstructorService instructorService;
    @Autowired
    ParentRepository parentRepository;
    @Autowired
    ParentService parentService;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    StudentService studentService;
    @Autowired
    ReportRepository reportRepository;
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    AccountService accountService;
    @Autowired
    BasicInformationRepository basicInformationRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        final long PARENT_NUMBER = 5L;
        final long STUDENT_NUMBER = 7L;
        final long INSTRUCTOR_NUMBER = 3L;
        final long REPORT_NUMBER = 1234L;

        Connection connection = dataSource.getConnection();

        System.out.println(connection.getMetaData().getURL());
        System.out.println(connection.getMetaData().getUserName());

        for(int i = 0; i < INSTRUCTOR_NUMBER; i++){
            instructorService.insertInstructor(new InstructorRequestDto(
                "닉네임"+i,
                randomHangulName(),
                "010-1234-432"+i%10,
                "메모"+i,
                "프로필 인포메이션"+i,
                "username"+i,
                "password"+i
            ));
        }

        for(int i = 0; i < PARENT_NUMBER; i++){
            parentService.insertParent(new ParentRequestDto(
                    randomHangulName(), "010-7777-777"+(i%10)
            ));
        }

        for(int i = 0; i < STUDENT_NUMBER; i++){
            Long parentId = null;
            EStudentType type = Math.random()>0.5?EStudentType.ADULT:EStudentType.CHILDREN;
            if(type == EStudentType.CHILDREN) {
                parentId = parentRepository.findById(Math.round(Math.ceil(Math.random()*PARENT_NUMBER))).get().getId();
            }

            long studentId = studentService.insertStudent(
                    new StudentRequestDto(
                        randomHangulName(),
                        LocalDate.now().minusYears(15).minusDays(Math.round(Math.random()*365)),
                        "010-0000-000"+i%10,
                        "email"+i+"@depot.com",
                        "주소 광주 어딘가 섬웨어 "+i,
                        type,
                        parentId
                    )
            );

            Instructor instructor = instructorRepository.findById(Math.round(Math.ceil(Math.random()*INSTRUCTOR_NUMBER))).get();
            instructor.getStudents().add(studentRepository.findById(studentId).get());
        }

        for(long i = 0L; i<REPORT_NUMBER; i++){
            Report report = new Report();
            report.setClassTime(LocalDateTime.now().minusSeconds(Math.round((Math.random()*10000000))));
            Instructor instructor = instructorRepository.findById(Math.round(Math.ceil(Math.random()*INSTRUCTOR_NUMBER))).get();
            report.setInstructor(instructor);

            List<Student> studentList = instructor.getStudents();
            if(studentList.size() != 0){
                report.setStudent(studentList.get((int)Math.round(Math.floor(Math.random()*studentList.size()))));
            }
            report.setProgram("test_"+i);

            for(long j = 0L; j<2L; j++){
                Comment comment = new Comment();
                comment.setAuthorName("댓글러"+i+"_"+j);
                comment.setContent("댓글입니다. 댓글은 좀 길어야 제맛이죠. 선생님 제 말을 듣고 계신가요? 듣고 계신다면 연락 바랍니다. 감사합니다. 그럼 아디오스"+i+"_"+j);
                comment.setReport(report);
                commentRepository.save(comment);
            }
            reportRepository.save(report);
        }

        accountService.createAccount("admin", "1234", EUserRole.ROLE_ADMIN);
        accountService.loadUserByUsername("admin");
    }

    public static String randomHangulName() {
        List<String> 성 = Arrays.asList("김", "이", "박", "최", "정", "강", "조", "윤", "장", "임", "한", "오", "서", "신", "권", "황", "안",
                "송", "류", "전", "홍", "고", "문", "양", "손", "배", "조", "백", "허", "유", "남", "심", "노", "정", "하", "곽", "성", "차", "주",
                "우", "구", "신", "임", "나", "전", "민", "유", "진", "지", "엄", "채", "원", "천", "방", "공", "강", "현", "함", "변", "염", "양",
                "변", "여", "추", "노", "도", "소", "신", "석", "선", "설", "마", "길", "주", "연", "방", "위", "표", "명", "기", "반", "왕", "금",
                "옥", "육", "인", "맹", "제", "모", "장", "남", "탁", "국", "여", "진", "어", "은", "편", "구", "용");
        List<String> 이름 = Arrays.asList("가", "강", "건", "경", "고", "관", "광", "구", "규", "근", "기", "길", "나", "남", "노", "누", "다",
                "단", "달", "담", "대", "덕", "도", "동", "두", "라", "래", "로", "루", "리", "마", "만", "명", "무", "문", "미", "민", "바", "박",
                "백", "범", "별", "병", "보", "빛", "사", "산", "상", "새", "서", "석", "선", "설", "섭", "성", "세", "소", "솔", "수", "숙", "순",
                "숭", "슬", "승", "시", "신", "아", "안", "애", "엄", "여", "연", "영", "예", "오", "옥", "완", "요", "용", "우", "원", "월", "위",
                "유", "윤", "율", "으", "은", "의", "이", "익", "인", "일", "잎", "자", "잔", "장", "재", "전", "정", "제", "조", "종", "주", "준",
                "중", "지", "진", "찬", "창", "채", "천", "철", "초", "춘", "충", "치", "탐", "태", "택", "판", "하", "한", "해", "혁", "현", "형",
                "혜", "호", "홍", "화", "환", "회", "효", "훈", "휘", "희", "운", "모", "배", "부", "림", "봉", "혼", "황", "량", "린", "을", "비",
                "솜", "공", "면", "탁", "온", "디", "항", "후", "려", "균", "묵", "송", "욱", "휴", "언", "령", "섬", "들", "견", "추", "걸", "삼",
                "열", "웅", "분", "변", "양", "출", "타", "흥", "겸", "곤", "번", "식", "란", "더", "손", "술", "훔", "반", "빈", "실", "직", "흠",
                "흔", "악", "람", "뜸", "권", "복", "심", "헌", "엽", "학", "개", "롱", "평", "늘", "늬", "랑", "얀", "향", "울", "련");
        Collections.shuffle(성);
        Collections.shuffle(이름);
        return 성.get(0) + 이름.get(0) + 이름.get(1);
    }
}
