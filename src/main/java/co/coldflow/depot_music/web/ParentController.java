package co.coldflow.depot_music.web;

import co.coldflow.depot_music.service.ParentService;
import co.coldflow.depot_music.service.StudentService;
import co.coldflow.depot_music.web.dto.ParentRequestDto;
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
public class ParentController {
    @Autowired
    ParentService parentService;

//    @GetMapping("/students")
//    public String getStudents(Model model, StudentRequestDto studentRequestDto) {
//        return "student/students";
//    }

//    @GetMapping("/students/new")
//    public String postStudent(Model model, StudentRequestDto studentRequestDto) { return "student/student_form";}


//    @GetMapping("students/{id}")
//    public String getStudent(@PathVariable long id, Model model){
//        StudentResponseDto studentResponseDto = studentService.selectStudent(id);
//
//        model.addAttribute(studentResponseDto);
//
//        return "student/student";
//    }
}
