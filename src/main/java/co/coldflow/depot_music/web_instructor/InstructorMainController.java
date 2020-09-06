package co.coldflow.depot_music.web_instructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InstructorMainController {
    @GetMapping("/instructor")
    public String getInstructorMainPage(Model model) {
        return "instructor/main";
    }
}
