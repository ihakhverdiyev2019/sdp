package ada.spd.startup.Repositories;


import ada.spd.startup.Domains.Investor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
    public interface InvestorRepository extends CrudRepository<Investor, Long> {



    }
