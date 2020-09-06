package co.coldflow.depot_music.service;

import co.coldflow.depot_music.dto.ReportRequestDto;
import co.coldflow.depot_music.entity.*;
import co.coldflow.depot_music.repository.AccountRepository;
import co.coldflow.depot_music.repository.InstructorRepository;
import co.coldflow.depot_music.repository.ReportRepository;
import co.coldflow.depot_music.dto.ReportListResponseWithPageInfoDto;
import co.coldflow.depot_music.dto.ReportResponseDto;
import co.coldflow.depot_music.repository.StudentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ReportService {
    private final ReportRepository reportRepository;
    private final AccountRepository accountRepository;
    private final InstructorRepository instructorRepository;
    private final StudentRepository studentRepository;

    public ReportService(ReportRepository reportRepository, AccountRepository accountRepository, InstructorRepository instructorRepository, StudentRepository studentRepository) {
        this.reportRepository = reportRepository;
        this.accountRepository = accountRepository;
        this.instructorRepository = instructorRepository;
        this.studentRepository = studentRepository;
    }

    public ReportListResponseWithPageInfoDto selectReportList(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1 , size);
        Page<Report> reportPage = reportRepository.findAllByOrderByIdDesc(pageable);

        List<ReportResponseDto> reportListToBeReturned = new ArrayList<>();

        for(Report report: reportPage){
            reportListToBeReturned.add(new ReportResponseDto(report));
        }

        return new ReportListResponseWithPageInfoDto(reportPage.getTotalPages(), reportPage.getPageable(), reportListToBeReturned);
    }

    public ReportResponseDto selectReport(long id) {
        Report report = reportRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("요청하신 ID의 수강일지가 존재하지 않습니다. id="+id));

        return new ReportResponseDto(report);
    }

    /**
     * 강사의 수강일지를 모두 불러오기
     */
    public ReportListResponseWithPageInfoDto selectInstructorsReportList(int page, int size) {
        Instructor instructor = getInstructorFromContext();

        Pageable pageable = PageRequest.of(page - 1 , size);
        Page<Report> reportPage = reportRepository.findAllByInstructorOrderByIdDesc(instructor, pageable);

        List<ReportResponseDto> reportListToBeReturned = new ArrayList<>();

        for(Report report: reportPage){
            reportListToBeReturned.add(new ReportResponseDto(report));
        }

        return new ReportListResponseWithPageInfoDto(reportPage.getTotalPages(), reportPage.getPageable(), reportListToBeReturned);
    }

    public ReportResponseDto selectInstructorsReport(long id) {
        Report report = reportRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("요청하신 ID의 수강일지가 존재하지 않습니다. id="+id));

        Instructor instructor = getInstructorFromContext();

        if(report.getInstructor() != instructor){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "본인의 수강일지만을 열람할 수 있습니다.");
        }

        return new ReportResponseDto(report);
    }

    private Instructor getInstructorFromContext() {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Account account = accountRepository.findByUsername(principal.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("접속한 사용자의 정보가 없습니다."));

        return instructorRepository.findByAccount(account)
                .orElseThrow(() -> new IllegalArgumentException("접속한 사용자는 강사 정보를 가지고 있지 않습니다."));
    }

    public void updateReportRequest(long id, ReportRequestDto reportRequestDto) {
        Report report = reportRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("요청하신 ID의 수강일지가 존재하지 않습니다. id="+id));

        report.setProgram(reportRequestDto.getProgram());
        report.setSubject(reportRequestDto.getSubject());
        report.setResult(reportRequestDto.getResult());
    }

    public String getInstructorName() {
        Instructor instructor = getInstructorFromContext();
        return instructor.getRealName();
    }

    public long insertReport(ReportRequestDto reportRequestDto) {
        Report report = new Report();
        Instructor instructor = getInstructorFromContext();

        Student student = studentRepository.findById(reportRequestDto.getStudentId())
            .orElseThrow(()->new ResponseStatusException(HttpStatus.NO_CONTENT, "선택하신 수강생의 ID 가 존재하지 않습니다. id="+reportRequestDto.getStudentId()));

        report.setName(instructor.getRealName());
        report.setClassTime(LocalDateTime.now());
        report.setRunningTime(reportRequestDto.getRunningTime());
        report.setProgram(reportRequestDto.getProgram());
        report.setSubject(reportRequestDto.getSubject());
        report.setResult(reportRequestDto.getResult());
        report.setInstructor(instructor);
        report.setStudent(student);
        report.setStudentType(student.getStudentType());

        reportRepository.save(report);

        return report.getId();
    }


//    public Long insertReport(ReportRequestDto studentRequestDto){
//        Report student = new Report();
//
//        student.setAddress(studentRequestDto.getAddress());
//        student.setBirthDate(studentRequestDto.getBirthDate());
//        student.setEmail(studentRequestDto.getEmail());
//        student.setName(studentRequestDto.getName());
//        student.setReportType(studentRequestDto.getReportType());
//        student.setTel(studentRequestDto.getTel());
//
//        if(studentRequestDto.getReportType() == EReportType.CHILDREN && studentRequestDto.getParentId() != null){
//            //수강생의 타입이 "자녀-Children"인 경우, 부모 정보 추가
//            Parent parent = parentRepository.findById(studentRequestDto.getParentId())
//                    .orElseThrow(() -> new IllegalArgumentException("입력하신 부모의 ID가 존재하지 않습니다. id=" + studentRequestDto.getParentId()));
//
//            student.setParent(parent);
//        }
//
//        studentRepository.save(student);
//
//        return student.getId();
//    }
//
//    public ReportResponseDto selectReport(long id) {
//        Report student = studentRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("입력하신 학생의 ID가 존재하지 않습니다. id=" + id));
//
//        Parent parent = student.getParent();
//
//        return new ReportResponseDto(
//            student.getId(),
//            student.getName(),
//            student.getBirthDate().toString(),
//            student.getTel(),
//            student.getEmail(),
//            student.getAddress(),
//            student.getReporReport,
//            parent == null ? null : new ParentResponseDto(student.getParent().getId(), student.getParent().getName(), student.getParent().getTel())
//        );
//    }
//
//    public void updateReport(long id, ReportRequestDto studentRequestDto) {
//        Report student = studentRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("입력하신 학생의 ID가 존재하지 않습니다. id=" + id));
//
//        Optional<Parent> parent = studentRequestDto.getParentId() != null ? parentRepository.findById(studentRequestDto.getParentId()) : Optional.empty();
//
//        student.setName(studentRequestDto.getName());
//        student.setBirthDate(studentRequestDto.getBirthDate());
//        student.setTel(studentRequestDto.getTel());
//        student.setEmail(studentRequestDto.getEmail());
//        student.setAddress(studentRequestDto.getAddress());
//        student.setReportType(studentRequestDto.getReportType());
//        student.setParent(parent.isPresent() ? parent.get() : null);
//    }
//
//    public List<ReportResponseDto> selectReportList() {
//        List<Report> existingReportList = studentRepository.findAll();
//
//        ArrayList<ReportResponseDto> studentResponseDtoArrayList = new ArrayList<>();
//
//        for(Report student: existingReportList){
//            studentResponseDtoArrayList.add(new ReportResponseDto(student));
//        }
//
//        return studentResponseDtoArrayList;
//    }
//
//    public List<ReportResponseDto> selectReportListByKeyword(String keyword) {
//        List<Report> existingReportList = studentRepository.findAllByNameContains(keyword);
//
//        ArrayList<ReportResponseDto> studentResponseDtoArrayList = new ArrayList<>();
//
//        for(Report student: existingReportList){
//            studentResponseDtoArrayList.add(new ReportResponseDto(student));
//        }
//
//        return studentResponseDtoArrayList;
//    }
}
