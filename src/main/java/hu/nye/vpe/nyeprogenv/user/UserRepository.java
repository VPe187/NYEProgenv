package hu.nye.vpe.nyeprogenv.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * User Repository.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    Long countById(final Long id);

    Optional<User> findByEmail(final String email);

}
