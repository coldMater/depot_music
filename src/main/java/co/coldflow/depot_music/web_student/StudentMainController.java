package co.coldflow.depot_music.web_student;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StudentMainController {
    @GetMapping("/student")
    public String getInstructorMainPage(Model model) {
        return "student/main";
    }
}
