package ada.spd.startup.Repositories;


import ada.spd.startup.Domains.VerificationToken;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface VerificationTokenRepository extends CrudRepository<VerificationToken,Long> {
//    @Query("SELECT c FROM Verification c where c.token = :token")
//    VerificationToken findByToken(@Param("token") String token);
}
