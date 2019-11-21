package ada.spd.startup.Domains;

import ada.spd.startup.ENUMS.Status;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "STARTUPPER")
public class Startupper {
@Id()
@GeneratedValue(strategy= GenerationType.AUTO)
    private long    id;
    private String name;
    private String surname;
    private String phoneNo;
    private String email;
    private String birth;
    private String country;
    private String city;
    private String nationallity;
    private String address;
    private String gender;
    private String role;
    private String login;
    private String password;
    private Status status;
    private int code;

    @OneToMany(mappedBy="startupper",cascade=CascadeType.ALL)
    private List<Startup> startups = new ArrayList<>();



    public Startupper(long id, String name, String surname, String phoneNo, String email, String birth, String country, String city, String nationallity, String address, String gender, String role, String login, String password) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.phoneNo = phoneNo;
        this.email = email;
        this.birth = birth;
        this.country = country;
        this.city = city;
        this.nationallity = nationallity;
        this.address = address;
        this.gender = gender;
        this.role = role;
        this.login = login;
        this.password = password;
    }

    public Startupper() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getNationallity() {
        return nationallity;
    }

    public void setNationallity(String nationallity) {
        this.nationallity = nationallity;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<Startup> getStartups() {
        return startups;
    }

    public void setStartups(List<Startup> startups) {
        this.startups = startups;
    }


    public void addStartup(Startup startup) {
        startups.add(startup);
    }





}
