package hu.nye.vpe.nyeprogenv.user;

import org.springframework.data.repository.CrudRepository;

/**
 * User Repository.
 */
public interface UserRepository extends CrudRepository<User, Integer> {

    public Long countById(final Integer id);

}
