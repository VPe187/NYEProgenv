package hu.nye.vpe.nyeprogenv.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CustomUserDetailsTest {

    final String EMAIL = "test@test.hu";
    final String PASSWORD = "1234567890";
    final String FIRSTNAME = "Ludwig";
    final String LASTNAME = "Armando";
    final Boolean ENABLED = true;

    private User user;
    private CustomUserDetails customUserDetails;

    @BeforeEach
    public void setUp() {
        user = new User();
        user.setEmail(EMAIL);
        user.setPassword(PASSWORD);
        user.setFirstName(FIRSTNAME);
        user.setLastName(LASTNAME);
        user.setEnabled(ENABLED);
        customUserDetails = new CustomUserDetails(user);
    }

    @Test
    public void testGetAuthorities() {
        Collection<? extends GrantedAuthority> authorities = customUserDetails.getAuthorities();
        assertNull(authorities); // Mivel az getAuthorities() null-t ad vissza, az ellenőrzés is ennek megfelelő.
    }

    @Test
    public void testGetPassword() {
        assertEquals(PASSWORD, customUserDetails.getPassword());
    }

    @Test
    public void testGetUsername() {
        assertEquals(EMAIL, customUserDetails.getUsername());
    }

    @Test
    public void testIsAccountNonExpired() {
        assertTrue(customUserDetails.isAccountNonExpired());
    }

    @Test
    public void testIsAccountNonLocked() {
        assertTrue(customUserDetails.isAccountNonLocked());
    }

    @Test
    public void testIsCredentialsNonExpired() {
        assertTrue(customUserDetails.isCredentialsNonExpired());
    }

    @Test
    public void testIsEnabled() {
        assertTrue(customUserDetails.isEnabled());
    }

    @Test
    public void testGetFullName() {
        assertEquals(FIRSTNAME + " " + LASTNAME, customUserDetails.getFullName());
    }
}
