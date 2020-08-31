package co.coldflow.depot_music.web;

import co.coldflow.depot_music.service.ParentService;
import co.coldflow.depot_music.web.dto.ParentRequestDto;
import co.coldflow.depot_music.web.dto.json_response.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class StudentParentController {
    @Autowired
    ParentService parentService;

    @PostMapping("/students/parent")
    public ResponseEntity<CustomResponse> postParentFromStudentForm(@Valid ParentRequestDto parentRequestDto, Errors errors, Model model) {
        if (null != errors && errors.getErrorCount() > 0) {
            return new ResponseEntity(new CustomResponse("", errors.getFieldErrors()), HttpStatus.BAD_REQUEST);
        } else {
            long id = parentService.insertParent(parentRequestDto);

            model.addAttribute(parentRequestDto);
            return ResponseEntity.ok(new CustomResponse());
        }
    }
}
