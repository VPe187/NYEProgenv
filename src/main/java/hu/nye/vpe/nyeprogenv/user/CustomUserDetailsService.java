package hu.nye.vpe.nyeprogenv.user;

import hu.nye.vpe.nyeprogenv.user.exceptions.UsernameNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 *  CustomUserDetailsService class.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(final String userEmail) {
        User user = userRepository.findByEmail(userEmail);
        if (user == null) try {
            throw new UsernameNotFoundException(userEmail);
        } catch (UsernameNotFoundException e) {
            throw new RuntimeException(e);
        }
        return new CustomUserDetails(user);
    }

}
