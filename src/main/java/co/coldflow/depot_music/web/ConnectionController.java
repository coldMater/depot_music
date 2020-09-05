package co.coldflow.depot_music.web;

import co.coldflow.depot_music.service.InstructorService;
import co.coldflow.depot_music.web.dto.InstructorResponseDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Controller
public class ConnectionController implements WebMvcConfigurer {
    private final InstructorService instructorService;

    public ConnectionController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @GetMapping("/admin/connections")
    public String getInstructorListForConnection(Model model) {
        model.addAttribute("instructors", instructorService.selectInstructorList());
        return "admin/connection/connections";
    }

    @GetMapping("/admin/connections/{instructorId}")
    public String getStudent(@PathVariable long instructorId, Model model){
        InstructorResponseDto instructorResponseDto = instructorService.selectInstructor(instructorId);

        model.addAttribute(instructorResponseDto);

        return "admin/connection/connection";
    }
//
//
//    @GetMapping("/students/{id}/edit")
//    public String getStudentUpdateForm(@PathVariable long id, Model model) {
//        StudentResponseDto studentResponseDto = studentService.selectStudent(id);
//
//        model.addAttribute(studentResponseDto);
//
//        return "student/student_form_update";
//    }
//
//    @PostMapping("/students/{id}/edit")
//    public String putStudentForm(@PathVariable long id, StudentRequestDto studentRequestDto) {
//        studentService.updateStudent(id, studentRequestDto);
//        return "redirect:/students/" + id;
//    }
}
