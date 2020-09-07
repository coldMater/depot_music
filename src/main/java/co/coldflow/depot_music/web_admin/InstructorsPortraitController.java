package co.coldflow.depot_music.web_admin;

import co.coldflow.depot_music.dto.json_response.CustomResponse;
import co.coldflow.depot_music.service.InstructorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class InstructorsPortraitController {
    private final InstructorService instructorService;

    public InstructorsPortraitController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @PutMapping("/admin/portrait/{instructorId}")
    public ResponseEntity<CustomResponse> changePortrait(@RequestParam("portrait") MultipartFile portraitFile, @PathVariable long instructorId) throws IOException {
        instructorService.changeInstructorsPortrait(portraitFile, instructorId);

        return ResponseEntity.ok(new CustomResponse());
    }
}
