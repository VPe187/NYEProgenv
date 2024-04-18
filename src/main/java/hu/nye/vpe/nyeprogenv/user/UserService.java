package hu.nye.vpe.nyeprogenv.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * User services.
 */
@Service
public class UserService {
    @Autowired private UserRepository userRepository;

    public List<User> listAll() {
        return (List<User>) userRepository.findAll();
    }

    public void save( final User user ) {
        userRepository.save( user );
    }
}
