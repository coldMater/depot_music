package co.coldflow.depot_music.web;

import co.coldflow.depot_music.service.InstructorService;
import co.coldflow.depot_music.web.dto.InstructorRequestDto;
import co.coldflow.depot_music.web.dto.InstructorResponseDto;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

@Controller
public class InstructorController {
    @Autowired
    InstructorService instructorService;


    @GetMapping("/instructors")
    public String getInstructors() {
        return "instructor/instructors";
    }

    @GetMapping("/instructors/{id}")
    public String getInstructorById(@PathVariable long id, Model model) {
        InstructorResponseDto instructorResponseDto = instructorService.selectInstructor(id);

        model.addAttribute(instructorResponseDto);

        return "instructor/instructor";
    }

    @PostMapping("/instructors")
    public String postInstructor(InstructorRequestDto instructorRequestDto) throws IOException {
        MultipartFile file = instructorRequestDto.getPortrait();

        String directoryPath = "portrait";
        Path directory = Paths.get(directoryPath).toAbsolutePath().normalize();
        Files.createDirectories(directory);
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Assert.state(!fileName.contains(".."), "Name of file cannot contain '..'");
        Path targetPath = directory.resolve(fileName).normalize();

        file.transferTo(targetPath);

        Long id = instructorService.insertInstructor(instructorRequestDto, targetPath);

        return "redirect:/instructors/"+id;
    }

    @GetMapping("/instructors/new")
    public String getInstructorForm() {
        return "instructor/instructor_form";
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
