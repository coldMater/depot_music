package co.coldflow.depot_music.web_instructor;

import co.coldflow.depot_music.dto.ChangePasswordRequestDto;
import co.coldflow.depot_music.dto.ChangePasswordResponseDto;
import co.coldflow.depot_music.service.AccountService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ChangePasswordController {

    private AccountService accountService;

    public ChangePasswordController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/instructor/change_password")
    public String changePasswordForm(Model model, ChangePasswordRequestDto changePasswordRequestDto) {
        model.addAttribute((changePasswordRequestDto));
        return "instructor/change_password/change_password_form";
    }

    @PostMapping("/instructor/change_password")
    public String changePassword(Model model, ChangePasswordRequestDto changePasswordRequestDto){
        ChangePasswordResponseDto changePasswordResponseDto = accountService.changePassword(changePasswordRequestDto);

        model.addAttribute(changePasswordResponseDto);
        return "instructor/change_password/change_password_form";
    }
}
