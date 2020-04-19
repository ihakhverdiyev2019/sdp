package ada.spd.startup.Repositories;

import ada.spd.startup.Domains.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    @Query("SELECT c FROM User c where c.login = :login and c.password=:pass")
    Optional<User> findByCredentials(@Param("login") String login, @Param("pass") String pass);


    @Query("SELECT c FROM User c where c.email = :email")
    User findByEmail(String email);


}
