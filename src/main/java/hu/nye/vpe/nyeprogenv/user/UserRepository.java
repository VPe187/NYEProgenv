package hu.nye.vpe.nyeprogenv.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * User Repository.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    Long countById(final Long id);

    Optional<User> findByEmail(final String email);

}
