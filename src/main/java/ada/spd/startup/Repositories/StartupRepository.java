package ada.spd.startup.Repositories;

import ada.spd.startup.Domains.Startup;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface StartupRepository extends CrudRepository<Startup, Long> {


    List<Startup> findByStartupperId (long id);

}
