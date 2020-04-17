package ada.spd.startup.Domains;


import javax.persistence.*;


@Entity
@Table(name = "VERIFICATION")
public class VerificationToken {

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int token;

    public VerificationToken(int token) {
        this.token = token;
    }

    public VerificationToken(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getToken() {
        return token;
    }

    public void setToken(int token) {
        this.token = token;
    }

}