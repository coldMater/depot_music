package co.coldflow.depot_music.service;

import co.coldflow.depot_music.entity.Parent;
import co.coldflow.depot_music.entity.Report;
import co.coldflow.depot_music.repository.ReportRepository;
import co.coldflow.depot_music.web.dto.ParentResponseDto;
import co.coldflow.depot_music.web.dto.ReportResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ReportService {
    @Autowired
    ReportRepository reportRepository;

    public List<ReportResponseDto> selectReportList() {
        List<Report> reportList = reportRepository.findAll();

        List<ReportResponseDto> reportListToBeReturned = new ArrayList<>();

        for(Report report: reportList){
            reportListToBeReturned.add(new ReportResponseDto(report));
        }

        return reportListToBeReturned;
    }

    public ReportResponseDto selectReport(long id) {
        Report report = reportRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("요청하신 ID의 수강일지가 존재하지 않습니다. id="+id));

        return new ReportResponseDto(report);
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
