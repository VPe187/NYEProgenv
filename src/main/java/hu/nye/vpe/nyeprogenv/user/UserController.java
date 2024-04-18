package hu.nye.vpe.nyeprogenv.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * User Controller.
 */
@Controller
public class UserController {
    @Autowired private UserService userService;

    /**
     * Method showUserList.
     *
     * @param model as Model
     *
     * @return users
     */
    @GetMapping("/users")
    public String showUserList(Model model) {
        List<User> listUsers = userService.listAll();
        model.addAttribute("listUsers", listUsers);
        return "users";
    }

    /**
     * Method showNewUserForm.
     *
     * @param model as Model
     *
     * @return users
     */
    @GetMapping("/users/new")
    public String showNewUserForm(Model model) {
        model.addAttribute("user", new User());

        return "user_form";
    }

    @PostMapping("/users/save")
    public String saveUser(@ModelAttribute("user") User user, RedirectAttributes ra) {
        userService.save(user);
        ra.addFlashAttribute("message", "User saved successfully");
        return "redirect:/users";
    }
}
