package co.coldflow.depot_music.web;

import co.coldflow.depot_music.service.StudentService;
import co.coldflow.depot_music.web.dto.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import javax.validation.Valid;


@Controller
public class StudentController implements WebMvcConfigurer {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("students/new").setViewName("admin/student/student_form");
    }

    @GetMapping("/admin/students")
    public String getStudents(Model model, StudentRequestDto studentRequestDto) {
        model.addAttribute("students", studentService.selectStudentList());
        return "admin/student/students";
    }

    @GetMapping("/admin/students/new")
    public String postStudent(Model model, StudentRequestDto studentRequestDto) { return "/admin/student/student_form";}

    @PostMapping("/admin/students")
    public String postStudent(@Valid StudentRequestDto studentRequestDto, Errors errors, Model model) {
        if (null != errors && errors.getErrorCount() > 0) {
            return "admin/student/student_form";
        } else {
            long id = studentService.insertStudent(studentRequestDto);

            model.addAttribute(studentRequestDto);
            return "redirect:/admin/students/"+id;
        }
    }

    @GetMapping("/admin/students/{id}")
    public String getStudent(@PathVariable long id, Model model){
        StudentResponseDto studentResponseDto = studentService.selectStudent(id);

        model.addAttribute(studentResponseDto);

        return "admin/student/student";
    }

    @GetMapping("/admin/students/{id}/edit")
    public String getStudentUpdateForm(@PathVariable long id, Model model) {
        StudentResponseDto studentResponseDto = studentService.selectStudent(id);

        model.addAttribute(studentResponseDto);

        return "admin/student/student_form_update";
    }

    @PostMapping("/admin/students/{id}/edit")
    public String putStudentForm(@PathVariable long id, StudentRequestDto studentRequestDto) {
        studentService.updateStudent(id, studentRequestDto);
        return "redirect:/admin/students/" + id;
    }
}
