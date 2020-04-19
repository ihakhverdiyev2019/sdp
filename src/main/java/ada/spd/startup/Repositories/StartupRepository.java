package ada.spd.startup.Repositories;

import ada.spd.startup.Domains.Startup;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface StartupRepository extends CrudRepository<Startup, Long> {


//    Iterable<Startup> findByUserId(long id);


    @Query(value = "select u from Startup u where u.category=:category and u.investAmount =:amount")
    List<Startup> findByCategoryOrInvestAmount(String category, double amount);


}
