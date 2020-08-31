package co.coldflow.depot_music.web;

import co.coldflow.depot_music.service.StudentService;
import co.coldflow.depot_music.web.dto.ParentRequestDto;
import co.coldflow.depot_music.web.dto.StudentRequestDto;
import co.coldflow.depot_music.web.dto.StudentResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.validation.Valid;


@Controller
public class StudentController implements WebMvcConfigurer {
    @Autowired
    StudentService studentService;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("students/new").setViewName("student/student_form");
    }

    @GetMapping("/students")
    public String getStudents(Model model, StudentRequestDto studentRequestDto) {
        return "student/students";
    }

    @GetMapping("/students/new")
    public String postStudent(Model model, StudentRequestDto studentRequestDto) { return "student/student_form";}

    @PostMapping("/students")
    public String postStudent(@Valid StudentRequestDto studentRequestDto, Errors errors, Model model) {
        if (null != errors && errors.getErrorCount() > 0) {
            return "student/student_form";
        } else {
            long id = studentService.insertStudent(studentRequestDto);

            model.addAttribute(studentRequestDto);
            return "redirect:/students/"+id;
        }
    }

    @GetMapping("/students/{id}")
    public String getStudent(@PathVariable long id, Model model){
        StudentResponseDto studentResponseDto = studentService.selectStudent(id);

        model.addAttribute(studentResponseDto);

        return "student/student";
    }

}
