package co.coldflow.depot_music.web_instructor;

import co.coldflow.depot_music.dto.*;
import co.coldflow.depot_music.service.ReportService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
public class InstructorReportController {
    private final ReportService reportService;

    public InstructorReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/instructor/reports")
    public String getInstructorsReportList(Model model, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size) {
        ReportListResponseWithPageInfoDto reportListResponseWithPageInfoDto = reportService.selectInstructorsReportList(page, size);
        List<ReportResponseDto> reports = reportListResponseWithPageInfoDto.getReports();
        PaginationDto pagination = reportListResponseWithPageInfoDto.getPagination();

        model.addAttribute("reports", reports);
        model.addAttribute("pagination", pagination);
        return "instructor/report/reports";
    }

    @GetMapping("/instructor/reports/{id}")
    public String getInstructorsReport(Model model, @PathVariable long id) {
        ReportResponseDto reportResponseDto = reportService.selectInstructorsReport(id);
        model.addAttribute(reportResponseDto);
        return "instructor/report/report";
    }

    @GetMapping("/instructor/reports/new")
    public String createInstructorsReport(Model model, ReportRequestDto reportRequestDto) {
        String instructorName = reportService.getInstructorName();
        model.addAttribute("instructorName", instructorName);
        return "/instructor/report/report_form";
    }

    @PostMapping("/instructor/reports/new")
    public String postReport(@Valid ReportRequestDto reportRequestDto, Errors errors, Model model) {
        if (null != errors && errors.getErrorCount() > 0) {
            String instructorName = reportService.getInstructorName();
            model.addAttribute("instructorName", instructorName);
            return "instructor/report/report_form";
        } else {
            long id = reportService.insertReport(reportRequestDto);

            model.addAttribute(reportRequestDto);
            return "redirect:/instructor/reports/"+id;
        }
    }

    @GetMapping("/instructor/reports/{id}/edit")
    public String getInstructorReportUpdateForm(Model model, @PathVariable long id) {
        ReportResponseDto reportResponseDto = reportService.selectInstructorsReport(id);
        model.addAttribute(reportResponseDto);
        return "instructor/report/report_form_update";
    }

    @PostMapping("/instructor/reports/{id}/edit")
    public String updateInstructorReport(Model model, @PathVariable long id, ReportRequestDto reportRequestDto){
        reportService.updateReportRequest(id, reportRequestDto);
        return "redirect:/instructor/reports/" + id;
    }
}
