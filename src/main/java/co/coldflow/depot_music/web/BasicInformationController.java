package co.coldflow.depot_music.web;

import co.coldflow.depot_music.service.BasicInformationService;
import co.coldflow.depot_music.web.dto.BasicInformationRequestDto;
import co.coldflow.depot_music.web.dto.BasicInformationResponseDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BasicInformationController {
    private BasicInformationService basicInformationService;

    public BasicInformationController(BasicInformationService basicInformationService) {
        this.basicInformationService = basicInformationService;
    }

    @GetMapping("/admin/basic_information")
    public String getBasicInformation(Model model) {
        BasicInformationResponseDto basicInformationResponseDto = basicInformationService.selectBasicInformation();
        model.addAttribute(basicInformationResponseDto);

        return "/admin/basic_information/basic_information";
    }

    @GetMapping("/admin/basic_information/edit")
    public String getUpdateBasicInformationForm(Model model) {
        BasicInformationResponseDto basicInformationResponseDto = basicInformationService.selectBasicInformation();
        model.addAttribute(basicInformationResponseDto);
        return "/admin/basic_information/basic_information_form_update";
    }

    @PostMapping("/admin/basic_information/edit")
    public String updateBasicInformation(BasicInformationRequestDto basicInformationRequestDto) {
        basicInformationService.updateBasicInformation(basicInformationRequestDto);

        return "redirect:/admin/basic_information";
    }
}
