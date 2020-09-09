package co.coldflow.depot_music.web_student;

import co.coldflow.depot_music.dto.BasicInformationResponseDto;
import co.coldflow.depot_music.dto.InstructorResponseDto;
import co.coldflow.depot_music.service.BasicInformationService;
import co.coldflow.depot_music.service.InstructorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class OrganizationInfoController {
    private BasicInformationService basicInformationService;

    public OrganizationInfoController(BasicInformationService basicInformationService) {
        this.basicInformationService = basicInformationService;
    }

    @GetMapping("/student/organization_info")
    public String getOrganizationInfo(Model model) {
        BasicInformationResponseDto basicInformationResponseDto = basicInformationService.selectBasicInformation();
        model.addAttribute(basicInformationResponseDto);

        return "/student/organization_info/organization_info";
    }
}
