package hu.nye.vpe.nyeprogenv;

import java.util.Arrays;
import java.util.List;

import hu.nye.vpe.nyeprogenv.user.User;
import hu.nye.vpe.nyeprogenv.user.UserRepository;
import hu.nye.vpe.nyeprogenv.user.UserService;
import hu.nye.vpe.nyeprogenv.user.exceptions.UserNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks( this );
    }

    @Test
    public void testListAllUser() {
        // Given
        User user1 = new User();
        User user2 = new User();
        User user3 = new User();
        User user4 = new User();
        User user5 = new User();
        List< User > users = Arrays.asList(user1, user2, user3, user4, user5);

        // When
        when(userRepository.findAll()).thenReturn(users);
        List<User> result = userService.listAll();

        // Then
        assertEquals(5, result.size());
    }

    @Test
    public void testSaveUser() {
        // Given
        User user = new User();

        // When
        userService.save(user);

        //Then
        verify(userRepository).save(user);
    }

    @Test
    public void testGetUser() throws UserNotFoundException {
        // Given
        User user = new User();

        // When
        when(userRepository.findById(1L)).thenReturn(java.util.Optional.of(user));

        // Then
        User result = userService.getUser(1);
        assertEquals(user, result);
    }

    @Test
    public void testDeleteUser() throws UserNotFoundException {
        // Given
        User user = new User();

        // When
        when(userRepository.findById(1L)).thenReturn(java.util.Optional.of(user));
        userService.delete(1L);

        // Then
        verify(userRepository).deleteById(1L);
    }
}
