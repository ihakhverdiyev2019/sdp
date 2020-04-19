package ada.spd.startup.Domains;


import ada.spd.startup.ENUMS.RoleENUM;
import ada.spd.startup.ENUMS.StartupJoin;

import javax.persistence.*;

@Entity
public class UserStartup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;

    private RoleENUM rights;


    private String role;

    private StartupJoin startupJoin;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "startup_id", nullable = false)
    private Startup startup;


    public UserStartup(RoleENUM rights, String role, StartupJoin startupJoin) {
        this.rights = rights;
        this.role = role;
        this.startupJoin = startupJoin;
    }

    public UserStartup() {
    }


    public void setId(long id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setStartup(Startup startup) {
        this.startup = startup;
    }


    public RoleENUM getRights() {
        return rights;
    }

    public void setRights(RoleENUM rights) {
        this.rights = rights;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Startup getStartup() {
        return startup;
    }

    public StartupJoin getStartupJoin() {
        return startupJoin;
    }

    public void setStartupJoin(StartupJoin startupJoin) {
        this.startupJoin = startupJoin;
    }
}
