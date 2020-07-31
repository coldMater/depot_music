package co.coldflow.depot_music.web;

import co.coldflow.depot_music.web.dto.InstructorDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class InstructorController {

    @GetMapping("/instructors")
    public String getInstructors() {
        return "instructors";
    }

    @PostMapping("/instructors")
    public String postInstructor(InstructorDto instructorDto) {
        System.out.println(instructorDto);
        return "redirect:/instructors/new";
    }

    @GetMapping("/instructors/new")
    public String getInstructorForm() {
        return "instructor/instructor_form";
    }
}
