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

    @Test
    public void testAddNew() {
        User user = new User();
        user.setEmail("vpe@integra.hu");
        user.setPassword("vpe");
        user.setFirstName("Peter");
        user.setLastName("Varga");
        User savedUser = userRepository.save(user);
        Assertions.assertNotNull(savedUser);
        Assertions.assertNotEquals(savedUser.getId(), 0);
    }

    @Test
    public void testListAll() {
        List<User> users = (List<User>) userRepository.findAll();
        Assertions.assertNotNull(users);
        Assertions.assertFalse(users.isEmpty());
    }

    @Test
    public void testUpdate() {
        Integer userId = 1;
        Optional<User> optionalUser = userRepository.findById(userId);
        User user = optionalUser.get();
        user.setEmail("vpe187@integra.hu");
        userRepository.save(user);
        User updatedUser = userRepository.findById(userId).get();
        Assertions.assertNotNull(updatedUser);
        Assertions.assertEquals(updatedUser.getEmail(), "vpe187@integra.hu");
    }

    @Test
    public void testGetUserById() {
        Integer userId = 1;
        Optional<User> optionalUser = userRepository.findById(userId);
        User user = optionalUser.get();
        Assertions.assertNotNull(user);
    }

    @Test
    public void testDeleteUser() {
        Integer userId = 1;
        userRepository.deleteById(userId);
        Optional<User> optionalUser = userRepository.findById(userId);
        Assertions.assertNotNull(optionalUser);
    }
}
