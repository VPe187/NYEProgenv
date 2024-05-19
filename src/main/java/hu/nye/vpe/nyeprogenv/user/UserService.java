package hu.nye.vpe.nyeprogenv.user;

import java.util.List;
import java.util.Optional;

import hu.nye.vpe.nyeprogenv.user.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * User services.
 */
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> listAll() {
        return (List<User>) userRepository.findAll();
    }

    public void save(final User user) {
        userRepository.save(user);
    }

    /**
     * Get user by id.
     *
     * @param id
     *
     * @return user
     * @throws UserNotFoundException is user not found exception
     */
    public User getUser(final long id) throws UserNotFoundException {
        Optional<User> result = userRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        throw new UserNotFoundException("Could not find any users with this ID " + id);
    }

    /**
     * Delete user by id.
     *
     * @param id
     *
     * @throws UserNotFoundException is not found exception
     */
    public void delete(final long id) throws UserNotFoundException {
        Long count = userRepository.countById(id);
        if (count == null || count == 0) {
            throw new UserNotFoundException("Could not find any users with this ID " + id);
        }
        userRepository.deleteById(id);
    }
}
