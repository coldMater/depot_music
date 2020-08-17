package co.coldflow.depot_music.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StudentController {
    @GetMapping("/students")
    public String getInstructors(Model model) {
        return "student/students";
    }
}
