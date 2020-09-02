package co.coldflow.depot_music.web;

import co.coldflow.depot_music.service.InstructorService;
import co.coldflow.depot_music.service.ParentService;
import co.coldflow.depot_music.service.StudentService;
import co.coldflow.depot_music.web.dto.ParentRequestDto;
import co.coldflow.depot_music.web.dto.ParentResponseDto;
import co.coldflow.depot_music.web.dto.StudentResponseDto;
import co.coldflow.depot_music.web.dto.json_response.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class StudentParentController {
    @Autowired
    ParentService parentService;

    @Autowired
    StudentService studentService;

    @Autowired
    InstructorService instructorService;

    @PostMapping("/students/parent")
    public ResponseEntity<CustomResponse> postParentFromStudentForm(@Valid ParentRequestDto parentRequestDto, Errors errors, Model model) {
        if (null != errors && errors.getErrorCount() > 0) {
            return new ResponseEntity(new CustomResponse("", errors.getFieldErrors()), HttpStatus.BAD_REQUEST);
        } else {
            ParentResponseDto savedParentInfo = parentService.insertParent(parentRequestDto);

            return ResponseEntity.ok(new CustomResponse(savedParentInfo));
        }
    }

    @GetMapping("/students/parent")
    public ResponseEntity<CustomResponse> getParentFromStudentForm(Model model, @RequestParam(value = "keyword") String keyword ){
        List<ParentResponseDto> parentList = parentService.selectParentList(keyword);
        return ResponseEntity.ok(new CustomResponse(parentList));
    }

    @GetMapping("/students/student")
    public ResponseEntity<CustomResponse> getStudentFromConnectionForm(Model model, @RequestParam(value = "keyword") String keyword){
        List<StudentResponseDto> studentList = studentService.selectStudentListByKeyword(keyword);
        return ResponseEntity.ok(new CustomResponse(studentList));
    }

    @PostMapping("/connections/{instructorId}/{studentId}")
    public ResponseEntity<CustomResponse> linkStudent(@PathVariable long instructorId, @PathVariable long studentId, Model model) {
        instructorService.linkStudent(instructorId, studentId);
        return ResponseEntity.ok(new CustomResponse());
    }

    @PostMapping("/connections/release/{instructorId}/{studentId}")
    public ResponseEntity<CustomResponse> unlinkStudent(@PathVariable long instructorId, @PathVariable long studentId, Model model) {
        instructorService.unlinkStudent(instructorId, studentId);
        return ResponseEntity.ok(new CustomResponse());
    }
}
