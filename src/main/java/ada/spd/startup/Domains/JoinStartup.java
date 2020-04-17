package ada.spd.startup.Domains;


import ada.spd.startup.ENUMS.StartupInvitation;

import javax.persistence.*;

@Entity
public class JoinStartup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "startup_id", nullable = false)
    private Startup startup;


    private StartupInvitation startupInvitation;

    public JoinStartup(StartupInvitation startupInvitation) {
        this.startupInvitation = startupInvitation;
    }

    public JoinStartup() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Startup getStartup() {
        return startup;
    }

    public void setStartup(Startup startup) {
        this.startup = startup;
    }

    public StartupInvitation getStartupInvitation() {
        return startupInvitation;
    }

    public void setStartupInvitation(StartupInvitation startupInvitation) {
        this.startupInvitation = startupInvitation;
    }
}
