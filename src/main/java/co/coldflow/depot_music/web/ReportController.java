package co.coldflow.depot_music.web;

import co.coldflow.depot_music.service.ReportService;
import co.coldflow.depot_music.service.StudentService;
import co.coldflow.depot_music.web.dto.ReportResponseDto;
import co.coldflow.depot_music.web.dto.StudentRequestDto;
import co.coldflow.depot_music.web.dto.StudentResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.validation.Valid;


@Controller
public class ReportController implements WebMvcConfigurer {
    @Autowired
    ReportService reportService;

    @GetMapping("/reports")
    public String getReportList(Model model) {
        model.addAttribute("reports", reportService.selectReportList());
        return "report/reports";
    }

    @GetMapping("/reports/{id}")
    public String getReport(Model model, @PathVariable long id) {
        ReportResponseDto reportResponseDto = reportService.selectReport(id);
        model.addAttribute(reportResponseDto);
        return "report/report";
    }
}
