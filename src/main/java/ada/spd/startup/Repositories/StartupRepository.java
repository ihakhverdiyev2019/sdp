package ada.spd.startup.Repositories;

import ada.spd.startup.Domains.Startup;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StartupRepository extends CrudRepository<Startup, Long> {


//    Iterable<Startup> findByUserId(long id);



}
