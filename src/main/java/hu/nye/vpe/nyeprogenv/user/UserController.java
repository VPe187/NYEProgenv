package hu.nye.vpe.nyeprogenv.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
        model.addAttribute("pageTitle", "Új felhasználó hozzáadása");
        return "user_form";
    }

    /**
     * Method save user.
     *
     * @param user
     *
     * @param ra
     *
     * @return redirect
     */
    @PostMapping("/users/save")
    public String saveUser(@ModelAttribute("user") User user, RedirectAttributes ra) {
        userService.save(user);
        ra.addFlashAttribute("message", "Felhasználó felvitele sikerült");
        return "redirect:/users";
    }

    /**
     * Method edit user.
     *
     * @param id
     *
     * @param ra
     *
     * @return redirect
     */
    @GetMapping("/users/edit/{id}")
    public String showEditUserForm(@PathVariable int id, Model model, RedirectAttributes ra) {
        try {
            User user = userService.getUser(id);
            model.addAttribute("user", user);
            model.addAttribute("pageTitle", "Felhasználó szerkesztése (ID: " + id + ")");
            return "user_form";
        }  catch (UserNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/users";
        }
    }

    /**
     * Method delete user.
     *
     * @param id
     *
     * @param ra
     *
     * @return redirect
     */
    @GetMapping("/users/delete/{id}")
    public String delteUser(@PathVariable int id, RedirectAttributes ra) {
        try {
            userService.delete(id);
            ra.addFlashAttribute("message", "A felhasználó sikeresen törölve (ID: " + id + ")");
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());

        }
        return "redirect:/users";
    }

}
