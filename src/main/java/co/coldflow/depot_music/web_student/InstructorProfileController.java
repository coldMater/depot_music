package co.coldflow.depot_music.web_student;

import co.coldflow.depot_music.dto.InstructorResponseDto;
import co.coldflow.depot_music.dto.ReportResponseDto;
import co.coldflow.depot_music.service.InstructorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class InstructorProfileController {

    private final InstructorService instructorService;

    public InstructorProfileController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @GetMapping("/student/instructor_profile")
    public String getInstructorProfile(Model model) {
        List<InstructorResponseDto> instructorResponseDtoList = instructorService.selectInstructorsProfileOfStudent();

        model.addAttribute(instructorResponseDtoList);
        return "student/instructor_profile/instructor_profile";
    }
}
