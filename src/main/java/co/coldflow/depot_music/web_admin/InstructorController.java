package co.coldflow.depot_music.web_admin;

import co.coldflow.depot_music.service.InstructorService;
import co.coldflow.depot_music.dto.InstructorRequestDto;
import co.coldflow.depot_music.dto.InstructorResponseDto;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class InstructorController {
    private final InstructorService instructorService;

    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @GetMapping("/admin/instructors")
    public String getInstructors(Model model) {
        model.addAttribute("instructors", instructorService.selectInstructorList());
        return "/admin/instructor/instructors";
    }

    @GetMapping("/admin/instructors/{id}")
    public String getInstructorById(@PathVariable long id, Model model) {
        InstructorResponseDto instructorResponseDto = instructorService.selectInstructor(id);

        model.addAttribute(instructorResponseDto);

        return "/admin/instructor/instructor";
    }

    @PostMapping("/admin/instructors")
    public String postInstructor(InstructorRequestDto instructorRequestDto) throws IOException {

        Long id = instructorService.insertInstructor(instructorRequestDto);

        return "redirect:/admin/instructors/"+id;
    }

    @GetMapping("/admin/instructors/new")
    public String getInstructorForm() {
        return "admin/instructor/instructor_form";
    }

    @GetMapping("/admin/instructors/{id}/edit")
    public String getInstructorUpdateForm(@PathVariable long id, Model model) {
        InstructorResponseDto instructorResponseDto = instructorService.selectInstructor(id);

        model.addAttribute(instructorResponseDto);

        return "admin/instructor/instructor_form_update";
    }

    @PostMapping("/admin/instructors/{id}/edit")
    public String putInstructor(@PathVariable long id, InstructorRequestDto instructorRequestDto) {
        instructorService.updateInstructor(id, instructorRequestDto);
        return "redirect:/admin/instructors/" + id;
    }

    @GetMapping("/portrait/{id}")
    public @ResponseBody ResponseEntity<Resource> getPortrait(@PathVariable long id) throws IOException {
        String imagePath = instructorService.getImagePath(id);

        File imageFile = new File(imagePath);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(imageFile));
        return ResponseEntity.ok()
                .contentLength(imageFile.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
}
