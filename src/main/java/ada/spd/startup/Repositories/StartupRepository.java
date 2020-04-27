package ada.spd.startup.Repositories;

import ada.spd.startup.Domains.Startup;
import ada.spd.startup.Domains.UserStartup;
import ada.spd.startup.ENUMS.RoleENUM;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface StartupRepository extends CrudRepository<Startup, Long> {





    @Query(value = "select u from Startup u where u.category=:category ")
    List<Startup> findByCategory(String category);


    List<Startup> findByStartupNameContainingIgnoreCase(String startupName);


}
