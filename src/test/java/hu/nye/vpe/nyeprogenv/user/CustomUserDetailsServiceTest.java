package hu.nye.vpe.nyeprogenv.user;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class CustomUserDetailsServiceTest {
    final String EMAIL = "test@test.hu";
    final String PASSWORD = "1234567890";

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CustomUserDetailsService customUserDetailsService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testLoadUserByUsernameUserFound() {
        // Given
        User user = new User();
        user.setEmail(EMAIL);
        user.setPassword(PASSWORD);

        // When
        when(userRepository.findByEmail(EMAIL)).thenReturn(Optional.of(user));

        // Then
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(EMAIL);
        assertNotNull(userDetails);
        assertEquals(EMAIL, userDetails.getUsername());
        assertEquals(PASSWORD, userDetails.getPassword());
    }

    @Test
    public void testLoadUserByUsernameUserNotFound() {
        // Given

        // When
        when(userRepository.findByEmail(EMAIL)).thenReturn(Optional.empty());

        // Then
        assertThrows(org.springframework.security.core.userdetails.UsernameNotFoundException.class, () -> {
            customUserDetailsService.loadUserByUsername(EMAIL);
        });
    }
}
