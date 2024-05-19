package hu.nye.vpe.nyeprogenv;

import hu.nye.vpe.nyeprogenv.user.User;
import hu.nye.vpe.nyeprogenv.user.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {
    @Autowired private UserRepository userRepository;

    private final long USER_ID_1 = 1;
    private final long USER_ID_2 = 2;
    private final String EMAIL_1 = "vpe@integra.hu";
    private final String EMAIL_2 = "vpe187@gmail.com";
    private final String PASSWORD = "vpe187";
    private final String FIRST_NAME = "Varga";
    private final String LAST_NAME = "Peter";

    @Test
    public void testAddNewUser() {
        // When
        User user = new User();
        user.setEmail(EMAIL_1);
        user.setPassword(PASSWORD);
        user.setFirstName(FIRST_NAME);
        user.setLastName(LAST_NAME);
        user.setEnabled(true);
        // Given
        User savedUser = userRepository.save(user);
        // Then
        Assertions.assertNotNull(savedUser);
        Assertions.assertNotEquals(savedUser.getId(), 0);
    }

    @Test
    public void testAddAnotherUser() {
        // When
        User user2 = new User();
        user2.setEmail(EMAIL_2);
        user2.setPassword(PASSWORD);
        user2.setFirstName(FIRST_NAME);
        user2.setLastName(LAST_NAME);
        user2.setEnabled(true);
        // Given
        User savedUser2 = userRepository.save(user2);
        // Then
        Assertions.assertNotNull(savedUser2);
        Assertions.assertNotEquals(savedUser2.getId(), 0);
    }

    @Test
    public void testListAllUsers() {
        // When
        List<User> users = (List<User>) userRepository.findAll();
        // Then
        Assertions.assertNotNull(users);
        Assertions.assertFalse(users.isEmpty());
    }

    @Test
    public void testUpdateUser() {
        // When
        Optional<User> optionalUser = userRepository.findById(USER_ID_1);
        User user = optionalUser.get();
        // Given
        user.setEmail("vpe187@integra.hu");
        userRepository.save(user);
        User updatedUser = userRepository.findById(USER_ID_1).get();
        // Then
        Assertions.assertNotNull(updatedUser);
        Assertions.assertEquals(updatedUser.getEmail(), "vpe187@integra.hu");
    }

    @Test
    public void testGetUserById() {
        // When
        // Given
        Optional<User> optionalUser = userRepository.findById(USER_ID_2);
        User user = optionalUser.get();
        // Then
        Assertions.assertNotNull(user);
    }

    @Test
    public void testDeleteUser() {
        // When
        // Given
        userRepository.deleteById(USER_ID_2);
        Optional<User> optionalUser = userRepository.findById(USER_ID_2);
        // Then
        Assertions.assertNotNull(optionalUser);
    }
}
