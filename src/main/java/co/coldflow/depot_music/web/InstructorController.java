package co.coldflow.depot_music.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class InstructorController {

    @GetMapping("/instructors")
    public String getInstructors() {
        return "instructors";
    }

    @GetMapping("/instructors/new")
    public String getInstructorForm() {
        return "instructor/instructor_form";
    }

}
