package co.coldflow.depot_music.web_instructor;

import co.coldflow.depot_music.dto.json_response.CustomResponse;
import co.coldflow.depot_music.service.InstructorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class PortraitController {
    private final InstructorService instructorService;

    public PortraitController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @PutMapping("/instructor/portrait")
    public ResponseEntity<CustomResponse> changePortrait(@RequestParam("portrait") MultipartFile portraitFile) throws IOException {
        instructorService.changePortrait(portraitFile);

        return ResponseEntity.ok(new CustomResponse());
    }
}
