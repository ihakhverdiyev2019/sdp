package ada.spd.startup.Repositories;

import ada.spd.startup.Domains.Startupper;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StartupperRepository extends CrudRepository<Startupper, Long> {
    @Query("SELECT c FROM Startupper c where c.login = :login and c.password=:pass")
    Optional<Startupper> findByCredentials(@Param("login") String login, @Param("pass") String pass);

    @Query("SELECT code FROM Startupper")
    List<String> getAllCode();





}
