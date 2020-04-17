package ada.spd.startup.Repositories;

import ada.spd.startup.Domains.JoinStartup;
import ada.spd.startup.Domains.Startup;
import ada.spd.startup.Domains.ToDo;
import ada.spd.startup.Domains.User;
import ada.spd.startup.ENUMS.ToDoEnum;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JoinStartupRepository extends CrudRepository<JoinStartup, Long> {


    @Query(value = "select u.user from JoinStartup u where u.startup= :startup")
    User findByStartup(Startup startup);


    @Query(value = "select u from JoinStartup u where u.user= :user and u.startup=:startup")
    JoinStartup findByUserAndStartup(User user, Startup startup);


    @Query(value = "select u from JoinStartup u where u.user= :user")
    List<JoinStartup> findByUser(User user);
}
