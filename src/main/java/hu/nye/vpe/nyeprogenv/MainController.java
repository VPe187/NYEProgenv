package hu.nye.vpe.nyeprogenv;

import hu.nye.vpe.nyeprogenv.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * /* Main controller.
 **/
@Controller
public class MainController {

    @Autowired
    private UserService userService;

    @GetMapping("")
    public String showMain() {
        return "index";
    }
}
