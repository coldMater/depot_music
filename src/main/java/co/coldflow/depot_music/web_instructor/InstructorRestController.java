package co.coldflow.depot_music.web_instructor;

import co.coldflow.depot_music.dto.ParentRequestDto;
import co.coldflow.depot_music.dto.ParentResponseDto;
import co.coldflow.depot_music.dto.StudentResponseDto;
import co.coldflow.depot_music.dto.json_response.CustomResponse;
import co.coldflow.depot_music.service.InstructorService;
import co.coldflow.depot_music.service.ParentService;
import co.coldflow.depot_music.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class InstructorRestController {
    private final ParentService parentService;
    private final StudentService studentService;
    private final InstructorService instructorService;

    public InstructorRestController(ParentService parentService, StudentService studentService, InstructorService instructorService) {
        this.parentService = parentService;
        this.studentService = studentService;
        this.instructorService = instructorService;
    }

//    @PostMapping("/admin/students/parent")
//    public ResponseEntity<CustomResponse> postParentFromStudentForm(@Valid ParentRequestDto parentRequestDto, Errors errors, Model model) {
//        if (null != errors && errors.getErrorCount() > 0) {
//            return new ResponseEntity(new CustomResponse("", errors.getFieldErrors()), HttpStatus.BAD_REQUEST);
//        } else {
//            ParentResponseDto savedParentInfo = parentService.insertParent(parentRequestDto);
//
//            return ResponseEntity.ok(new CustomResponse(savedParentInfo));
//        }
//    }
//
//    @GetMapping("/admin/students/parent")
//    public ResponseEntity<CustomResponse> getParentFromStudentForm(Model model, @RequestParam(value = "keyword") String keyword ){
//        List<ParentResponseDto> parentList = parentService.selectParentList(keyword);
//        return ResponseEntity.ok(new CustomResponse(parentList));
//    }
//
    @GetMapping("/instructor/students/student")
    public ResponseEntity<CustomResponse> getStudentFromReportForm(Model model, @RequestParam(value = "keyword") String keyword){
        List<StudentResponseDto> studentList = studentService.selectStudentListByKeyword(keyword);
        return ResponseEntity.ok(new CustomResponse(studentList));
    }
//
//    @PostMapping("/admin/connections/{instructorId}/{studentId}")
//    public ResponseEntity<CustomResponse> linkStudent(@PathVariable long instructorId, @PathVariable long studentId, Model model) {
//        instructorService.linkStudent(instructorId, studentId);
//        return ResponseEntity.ok(new CustomResponse());
//    }
//
//    @PostMapping("/admin/connections/release/{instructorId}/{studentId}")
//    public ResponseEntity<CustomResponse> unlinkStudent(@PathVariable long instructorId, @PathVariable long studentId, Model model) {
//        instructorService.unlinkStudent(instructorId, studentId);
//        return ResponseEntity.ok(new CustomResponse());
//    }
}
