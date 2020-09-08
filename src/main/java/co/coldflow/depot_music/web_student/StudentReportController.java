package co.coldflow.depot_music.web_student;

import co.coldflow.depot_music.dto.PaginationDto;
import co.coldflow.depot_music.dto.ReportListResponseWithPageInfoDto;
import co.coldflow.depot_music.dto.ReportResponseDto;
import co.coldflow.depot_music.service.ReportService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Controller
public class StudentReportController implements WebMvcConfigurer {
    private final ReportService reportService;

    public StudentReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/student/reports")
    public String getReportList(Model model, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size) {
        ReportListResponseWithPageInfoDto reportListResponseWithPageInfoDto = reportService.selectStudentsReportList(page, size);
        List<ReportResponseDto> reports = reportListResponseWithPageInfoDto.getReports();
        PaginationDto pagination = reportListResponseWithPageInfoDto.getPagination();

        model.addAttribute("reports", reports);
        model.addAttribute("pagination", pagination);
        return "student/report/reports";
    }

    @GetMapping("/student/reports/{id}")
    public String getReport(Model model, @PathVariable long id) {
        ReportResponseDto reportResponseDto = reportService.selectReportFromStudent(id);
        model.addAttribute(reportResponseDto);
        return "student/report/report";
    }
}
