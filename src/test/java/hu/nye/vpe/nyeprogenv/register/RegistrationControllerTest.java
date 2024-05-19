package hu.nye.vpe.nyeprogenv.register;

import java.util.Optional;

import hu.nye.vpe.nyeprogenv.user.User;
import hu.nye.vpe.nyeprogenv.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class RegistrationControllerTest {

    private final String EMAIL = "email@email.com";
    private final String PASSWORD = "0123456789";
    private final String FIRSTNAME = "Peter";
    private final String LASTNAME = "Pellegrini";


    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private Model model;

    @InjectMocks
    private RegistrationController registrationController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testShowRegistrationForm() {
        String viewName = registrationController.showRegistrationForm(model);
        verify(model, times(1)).addAttribute(eq("user"), any(RegistrationDto.class));
        verify(model, times(1)).addAttribute("pageTitle", "Regisztráció");
        assertEquals("register_form", viewName);
    }

    @Test
    public void testRegisterUserAccount_Success() {
        RegistrationDto registrationDto = new RegistrationDto();
        registrationDto.setEmail(EMAIL);
        registrationDto.setPassword(PASSWORD);
        registrationDto.setFirstName(FIRSTNAME);
        registrationDto.setLastName(LASTNAME);

        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");

        String viewName = registrationController.registerUserAccount(registrationDto);

        verify(userRepository, times(1)).save(any(User.class));
        assertEquals("redirect:/login?success", viewName);
    }

    @Test
    public void testRegisterUserAccount_UserAlreadyExists() {
        RegistrationDto registrationDto = new RegistrationDto();
        registrationDto.setEmail("existing@example.com");

        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(new User()));

        String viewName = registrationController.registerUserAccount(registrationDto);

        verify(userRepository, times(0)).save(any(User.class));
        assertEquals("redirect:/register?error", viewName);
    }
}
