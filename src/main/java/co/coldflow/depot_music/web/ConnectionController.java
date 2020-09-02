package co.coldflow.depot_music.web;

import co.coldflow.depot_music.service.InstructorService;
import co.coldflow.depot_music.service.StudentService;
import co.coldflow.depot_music.web.dto.InstructorResponseDto;
import co.coldflow.depot_music.web.dto.StudentRequestDto;
import co.coldflow.depot_music.web.dto.StudentResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Controller
public class ConnectionController implements WebMvcConfigurer {
    @Autowired
    InstructorService instructorService;

    @GetMapping("/connections")
    public String getInstructorListForConnection(Model model) {
        model.addAttribute("instructors", instructorService.selectInstructorList());
        return "connection/connections";
    }

    @GetMapping("/connections/{instructorId}")
    public String getStudent(@PathVariable long instructorId, Model model){
        InstructorResponseDto instructorResponseDto = instructorService.selectInstructor(instructorId);

        model.addAttribute(instructorResponseDto);

        return "connection/connection";
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
