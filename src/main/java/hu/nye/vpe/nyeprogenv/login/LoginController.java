package hu.nye.vpe.nyeprogenv.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Login controller.
 */
@Controller
public class LoginController {
    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("pageTitle", "Belépés");
        return "login_form";
    }
}
