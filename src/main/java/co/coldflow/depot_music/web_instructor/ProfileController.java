package co.coldflow.depot_music.web_instructor;

import co.coldflow.depot_music.dto.InstructorRequestDto;
import co.coldflow.depot_music.dto.InstructorResponseDto;
import co.coldflow.depot_music.service.InstructorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProfileController {
    private final InstructorService instructorService;

    public ProfileController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @GetMapping("/instructor/profile")
    public String getInstructorsProfile(Model model) {
        InstructorResponseDto instructorResponseDto = instructorService.selectInstructorsProfile();

        model.addAttribute(instructorResponseDto);

        return "/instructor/profile/profile";
    }

    @GetMapping("/instructor/profile/edit")
    public String updateInstructorsProfileForm(Model model, InstructorRequestDto instructorRequestDto) {
        InstructorResponseDto instructorResponseDto = instructorService.selectInstructorsProfile();

        model.addAttribute(instructorResponseDto);

        return "instructor/profile/profile_form";
    }

    @PostMapping("/instructor/profile/edit")
    public String updateInstructorsProfile(InstructorRequestDto instructorRequestDto) {
        instructorService.updateInstructorsProfile(instructorRequestDto);
        return "redirect:/instructor/profile";
    }
}
