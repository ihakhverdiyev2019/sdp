package ada.spd.startup.Repositories;

import ada.spd.startup.Domains.Startup;
import ada.spd.startup.Domains.User;
import ada.spd.startup.Domains.UserStartup;
import ada.spd.startup.ENUMS.RoleENUM;
import ada.spd.startup.ENUMS.StartupJoin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.management.relation.Role;
import java.util.List;

@Repository
public interface UserStartupRepository extends CrudRepository<UserStartup, Long> {


    @Query(value = "select u.startup from UserStartup u where u.user.id= :userId")
    List<Startup> findByUserId(long userId);


    @Query(value = "select u.user from UserStartup u where u.startup.id= :startupId")
    List<User> findByStartupId(long startupId);

    @Query(value = "select u from UserStartup u where u.startup.id= :startupId")
    List<UserStartup> findUserStartupByStartupId(long startupId);


    @Query(value = "select u.user from UserStartup u where u.startup.id=:startupId and u.rights =:roleENUM")
    List<User> findByStartupIdAndUserRights(long startupId, RoleENUM roleENUM);


    @Query(value = "select u.user from UserStartup u where u.startup.id=:startupId and u.rights =:roleENUM")
    User findFounderByStartupIdAndUserRights(long startupId, RoleENUM roleENUM);


    @Query(value = "select u from UserStartup u where u.startup.id=:startupId and u.rights =:roleENUM and u.startupJoin =:startupJoin")
    List<UserStartup> findUserStartupByStartupId(long startupId, RoleENUM roleENUM,StartupJoin startupJoin);

    @Query(value = "select u from UserStartup u where u.user.id= :userId")
    List <UserStartup> findUserStartupByUserId(long userId);

    @Query(value = "select u from UserStartup u where u.startup.id=:startupId and u.user.id =:userId")
    UserStartup findByStartupAndUser(long startupId, long userId);

    @Query(value = "select u.startup from UserStartup u where u.user.id= :userId and  u.startupJoin =:startupJoin")
    List<Startup> findByStartupAndRights(long userId, StartupJoin startupJoin);
}
