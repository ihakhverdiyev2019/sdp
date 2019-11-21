package ada.spd.startup.Repositories;


import ada.spd.startup.Domains.Investor;
import ada.spd.startup.Domains.Startupper;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
    public interface InvestorRepository extends CrudRepository<Investor, Long> {

    @Query("SELECT c FROM Investor c where c.login = :login and c.password=:pass")
    Optional<Investor> findByCredentials(@Param("login") String login, @Param("pass") String pass);

    }
