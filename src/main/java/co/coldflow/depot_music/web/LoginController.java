package co.coldflow.depot_music.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
    @RequestMapping("/login")
    public String login(Model model){
        model.addAttribute("loginError", false);
        return "login";
    }

    @RequestMapping("/login-error")
    public String loginError(Model model){
        model.addAttribute("loginError", true);
        return "login";
    }
}
