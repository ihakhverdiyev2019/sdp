package ada.spd.startup.Repositories;

import ada.spd.startup.Domains.User;
import ada.spd.startup.ENUMS.StatusEnum;
import ada.spd.startup.ENUMS.UserRoleInit;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    @Query("SELECT c FROM User c where c.login = :login and c.password=:pass")
    User findByCredentials(@Param("login") String login, @Param("pass") String pass);


    @Query("SELECT c FROM User c where c.email = :email")
    User findByEmail(String email);

    @Query("SELECT c FROM User c where c.userRoleInit = :userRoleInit")
    List<User> findByUserRoleInit(UserRoleInit userRoleInit);


    @Query("SELECT c FROM User c where c.userRoleInit = :userRoleInit and c.gender=:gender")
    List<User> findByUserRoleInitandGender(UserRoleInit userRoleInit, String gender);

    @Query("SELECT c FROM User c where c.userRoleInit = :userRoleInit and c.name=:name")
    List<User> findByUserRoleInitandName(UserRoleInit userRoleInit, String name);


    @Query("SELECT c FROM User c where c.userRoleInit = :userRoleInit and c.nationallity=:nationality")
    List<User> findByUserRoleInitAndNationallity(UserRoleInit userRoleInit, String nationality);


    @Query("SELECT c FROM User c where c.userRoleInit = :userRoleInit and c.nationallity=:nationality and c.gender=:gender")
    List<User> findByUserRoleInitAndNationallityAndGender(UserRoleInit userRoleInit, String nationality, String gender);


    @Query("SELECT c FROM User c where c.userRoleInit = :userRoleInit and c.nationallity=:nationality and c.statusEnum=:statusEnum")
    List<User> findByUserRoleInitAndNationallityAndStatus(UserRoleInit userRoleInit, String nationality, StatusEnum statusEnum);


    @Query("SELECT c FROM User c where c.userRoleInit = :userRoleInit and c.statusEnum=:statusEnum")
    List<User> findByUserRoleInitAndStatusEnum(UserRoleInit userRoleInit, StatusEnum statusEnum);


    List<User> findByNameContainingIgnoreCaseAndUserRoleInit(String name, UserRoleInit userRoleInit);


}
