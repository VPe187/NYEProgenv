package hu.nye.vpe.nyeprogenv.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * User Repository.
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    //@Query("SELECT u FROM User u WHERE u.email = ?1")
    Long countById(final Integer id);
    User findByEmail(final String email);

}
