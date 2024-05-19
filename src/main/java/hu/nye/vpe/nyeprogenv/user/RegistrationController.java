package hu.nye.vpe.nyeprogenv.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

/**
 * Registration controller.
 */
@Controller
public class RegistrationController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Registration path.
     *
     * @param model
     *
     * @return register
     */
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new RegistrationDto());
        model.addAttribute("pageTitle", "Regisztráció");
        return "register_form";
    }

    /**
     * Registration POST path.
     *
     * @param registrationDto
     *
     * @return redirect login
     */
    @PostMapping("/register")
    public String registerUserAccount(@ModelAttribute("user") RegistrationDto registrationDto) {
        Optional<User> existingUser = userRepository.findByEmail(registrationDto.getEmail());
        if (existingUser != null) {
            return "redirect:/register?error";
        }
        User user = new User();
        user.setEmail(registrationDto.getEmail());
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        user.setFirstName(registrationDto.getFirstName());
        user.setLastName(registrationDto.getLastName());
        user.setEnabled(true);
        userRepository.save(user);
        return "redirect:/login?success";
    }
}
