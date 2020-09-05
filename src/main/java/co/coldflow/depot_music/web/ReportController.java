package co.coldflow.depot_music.web;

import co.coldflow.depot_music.service.ReportService;
import co.coldflow.depot_music.web.dto.ReportResponseDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Controller
public class ReportController implements WebMvcConfigurer {
    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/admin/reports")
    public String getReportList(Model model) {
        model.addAttribute("reports", reportService.selectReportList());
        return "admin/report/reports";
    }

    @GetMapping("/admin/reports/{id}")
    public String getReport(Model model, @PathVariable long id) {
        ReportResponseDto reportResponseDto = reportService.selectReport(id);
        model.addAttribute(reportResponseDto);
        return "admin/report/report";
    }
}
