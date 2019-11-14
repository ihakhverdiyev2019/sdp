package ada.spd.startup.Domains;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Investor {


    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private String name;
    private String surname;
    private String birth;
    private String VOEN;
    private String companyName;
    private String phoneNo;
    private String email;
    private String nationallity;
    private String country;


    public Investor(int id, String name, String surname, String birth, String VOEN, String companyName, String phoneNo, String email, String nationallity, String country) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.birth = birth;
        this.VOEN = VOEN;
        this.companyName = companyName;
        this.phoneNo = phoneNo;
        this.email = email;
        this.nationallity = nationallity;
        this.country = country;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getVOEN() {
        return VOEN;
    }

    public void setVOEN(String VOEN) {
        this.VOEN = VOEN;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNationallity() {
        return nationallity;
    }

    public void setNationallity(String nationallity) {
        this.nationallity = nationallity;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
