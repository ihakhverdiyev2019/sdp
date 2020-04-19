package ada.spd.startup.Domains;


import ada.spd.startup.ENUMS.Status;
import ada.spd.startup.ENUMS.StatusEnum;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "USERS")
public class User {


    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String surname;
    private String phoneNo;
    private String email;
    private String emailPAss;
    private String birth;
    private String country;
    private String city;
    private String nationallity;
    private String address;
    private String gender;
    private String login;
    private String password;
    private String VOEN;
    private String companyName;
    private String avatar;

    private StatusEnum statusEnum;
    private String joinDate;
    private Status status;
    private int code;


    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<UserStartup> userStartups = new ArrayList<>();




    public User(String name, String surname, String emailPAss, String phoneNo, String email, String birth, String country, String city, String nationallity, String address, String gender, String login, String password, String VOEN, String companyName, String avatar, StatusEnum statusEnum, String joinDate, Status status, int code) {
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
        this.login = login;
        this.password = password;
        this.VOEN = VOEN;
        this.companyName = companyName;
        this.avatar = avatar;
        this.statusEnum = statusEnum;
        this.joinDate = joinDate;
        this.status = status;
        this.code = code;
        this.emailPAss = emailPAss;
    }

    public User() {

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

    public String getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }

    public StatusEnum getStatusEnum() {
        return statusEnum;
    }

    public void setStatusEnum(StatusEnum statusEnum) {
        this.statusEnum = statusEnum;
    }

    public List<UserStartup> getUserStartups() {
        return userStartups;
    }

    public void setStartupList(List<UserStartup> userStartups) {
        this.userStartups = userStartups;
    }


    public void addStartupList(UserStartup userStartup) {
        userStartups.add(userStartup);
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getEmailPAss() {
        return emailPAss;
    }

    public void setEmailPAss(String emailPAss) {
        this.emailPAss = emailPAss;
    }
}
