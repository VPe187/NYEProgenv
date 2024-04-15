package hu.nye.vpe.nyeprogenv;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
/* Main controller.
**/
@Controller
public class MainController {

    @GetMapping("")
    public String showMain() {
        return "index";
    }
}
