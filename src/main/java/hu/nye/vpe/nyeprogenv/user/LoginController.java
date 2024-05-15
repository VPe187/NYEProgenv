package hu.nye.vpe.nyeprogenv.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Login controller.
 */
@Controller
public class LoginController {
    @GetMapping("/loginform")
    public String login(Model model) {
        model.addAttribute("pageTitle", "Belépés");
        return "loginform";
    }
}
