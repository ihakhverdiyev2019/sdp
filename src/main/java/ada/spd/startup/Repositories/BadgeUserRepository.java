package ada.spd.startup.Repositories;

import ada.spd.startup.Domains.BadgeUser;
import ada.spd.startup.Domains.User;
import ada.spd.startup.ENUMS.UserRoleInit;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BadgeUserRepository extends CrudRepository<BadgeUser, Long> {

    @Query("SELECT c FROM BadgeUser c where c.user.id = :userId")
    List<BadgeUser> findBadgeUserById(long userId);


}
