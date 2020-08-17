package co.coldflow.depot_music.web;

import co.coldflow.depot_music.service.InstructorService;
import co.coldflow.depot_music.web.dto.InstructorRequestDto;
import co.coldflow.depot_music.web.dto.InstructorResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class InstructorController {
    @Autowired
    InstructorService instructorService;

    @GetMapping("/instructors")
    public String getInstructors(Model model) {
        model.addAttribute("instructors", instructorService.selectInstructorList());
        return "instructor/instructors";
    }

    @GetMapping("/instructors/{id}")
    public String getInstructorById(@PathVariable long id, Model model) {
        InstructorResponseDto instructorResponseDto = instructorService.selectInstructor(id);

        model.addAttribute(instructorResponseDto);

        return "instructor/instructor";
    }

    @PostMapping("/instructors")
    public String postInstructor(InstructorRequestDto instructorRequestDto) {
        Long id = instructorService.insertInstructor(instructorRequestDto);
        return "redirect:/instructors/"+id;
    }

    @GetMapping("/instructors/new")
    public String getInstructorForm() {
        return "instructor/instructor_form";
    }

    @GetMapping("/instructors/{id}/edit")
    public String getInstructorForm(@PathVariable long id, Model model) {
        InstructorResponseDto instructorResponseDto = instructorService.selectInstructor(id);

        model.addAttribute(instructorResponseDto);

        return "instructor/instructor_form_update";
    }

    @PostMapping("/instructors/{id}/edit")
    public String getInstructorForm(@PathVariable long id, InstructorRequestDto instructorRequestDto) {
        instructorService.updateInstructor(id, instructorRequestDto);
        return "redirect:/instructors/" + id;
    }
}
