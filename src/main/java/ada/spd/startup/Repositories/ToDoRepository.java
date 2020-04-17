package ada.spd.startup.Repositories;

import ada.spd.startup.Domains.Startup;
import ada.spd.startup.Domains.ToDo;
import ada.spd.startup.ENUMS.ToDoEnum;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToDoRepository extends CrudRepository<ToDo, Long> {


    Iterable<ToDo> findAllByStartup(Startup startup);

    Iterable<ToDo> findAllByProgress(ToDoEnum toDoEnum);


    @Query(value = "select u from ToDo u where u.progress= :toDoEnum and u.startup.id=:userId")
    List<ToDo> findByStartup(long userId, ToDoEnum toDoEnum);

}
