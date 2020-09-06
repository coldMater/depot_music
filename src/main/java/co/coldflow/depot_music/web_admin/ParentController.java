package co.coldflow.depot_music.web_admin;

import co.coldflow.depot_music.service.ParentService;
import org.springframework.stereotype.Controller;

@Controller
public class ParentController {
    private final ParentService parentService;

    public ParentController(ParentService parentService) {
        this.parentService = parentService;
    }

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
