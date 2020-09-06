package co.coldflow.depot_music.web_admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminMainController {
    @GetMapping("/admin")
    public String getAdminMainPage(Model model) {
        return "admin/main";
    }
}
