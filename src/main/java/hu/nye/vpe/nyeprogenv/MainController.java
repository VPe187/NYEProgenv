package hu.nye.vpe.nyeprogenv;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("")
    public String showMain() {
        return "index";
    }
}
