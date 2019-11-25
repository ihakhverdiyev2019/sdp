package ada.spd.startup.Repositories;

import ada.spd.startup.Domains.Startup;
import ada.spd.startup.Domains.Startupper;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface StartupRepository extends CrudRepository<Startup, Long> {
    @Query("SELECT c FROM Startup c where c.refferalCode = :refferalCode")
    Startup findByRefferalCode(@Param("refferalCode") String refferalCode);


    Iterable<Startup> findByStartupperId (long id);

}
